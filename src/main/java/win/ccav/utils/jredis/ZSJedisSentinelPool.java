package win.ccav.utils.jredis;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import redis.clients.jedis.*;
import redis.clients.jedis.exceptions.JedisConnectionException;
import redis.clients.jedis.exceptions.JedisException;
import redis.clients.util.Pool;
import win.ccav.utils.StringUtil;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ZSJedisSentinelPool extends Pool<Jedis> {

    protected GenericObjectPoolConfig poolConfig;
    protected int connectionTimeout = Protocol.DEFAULT_TIMEOUT;
    protected int soTimeout = Protocol.DEFAULT_TIMEOUT;
    protected String password;
    protected int database = Protocol.DEFAULT_DATABASE;
    protected String clientName;

    protected Set<MasterListener> masterListeners = new HashSet<>();
    protected Set<SlaveDownListener> slaveDownListeners = new HashSet<>();
    protected Set<SlaveUpListener> slaveUpListeners = new HashSet<>();

    protected List<HostAndPort> readHaps = new ArrayList<>();
    protected List<JedisPoolExt> jedisReadPools = new CopyOnWriteArrayList<>();
    protected Set<String> _sentinels = new LinkedHashSet<>();
    private String masterName;
    protected boolean masterWriteOnly;
    private Random rand = new Random();

    protected Logger log = Logger.getLogger(getClass().getName());

    private volatile JedisFactory factory;
    private volatile HostAndPort currentHostMaster;
    
    public static boolean DEFAULT_MASTER_WRITE_ONLY = false;

    public ZSJedisSentinelPool(String masterName, Set<String> sentinels, final GenericObjectPoolConfig poolConfig) {
        this(masterName, sentinels, poolConfig, null, Protocol.DEFAULT_TIMEOUT, Protocol.DEFAULT_TIMEOUT, DEFAULT_MASTER_WRITE_ONLY, null);
    }

    public ZSJedisSentinelPool(String masterName, Set<String> sentinels, final GenericObjectPoolConfig poolConfig, boolean masterWriteOnly) {
        this(masterName, sentinels, poolConfig, null, Protocol.DEFAULT_TIMEOUT, Protocol.DEFAULT_TIMEOUT, masterWriteOnly, null);
    }

    public ZSJedisSentinelPool(String masterName, Set<String> sentinels, final GenericObjectPoolConfig poolConfig, String password) {
        this(masterName, sentinels, poolConfig, password, Protocol.DEFAULT_TIMEOUT, Protocol.DEFAULT_TIMEOUT, DEFAULT_MASTER_WRITE_ONLY, null);
    }

    public ZSJedisSentinelPool(String masterName, Set<String> sentinels, final GenericObjectPoolConfig poolConfig, String password, boolean masterWriteOnly) {
        this(masterName, sentinels, poolConfig, password, Protocol.DEFAULT_TIMEOUT, Protocol.DEFAULT_TIMEOUT, masterWriteOnly, null);
    }

    public ZSJedisSentinelPool(String masterName, Set<String> sentinels, final GenericObjectPoolConfig poolConfig, int connectionTimeout, int soTimeout, boolean masterWriteOnly) {
        this(masterName, sentinels, poolConfig, null, connectionTimeout, soTimeout, masterWriteOnly, null);
    }

    public ZSJedisSentinelPool(String masterName, Set<String> sentinels, final GenericObjectPoolConfig poolConfig, int connectionTimeout, int soTimeout, boolean masterWriteOnly, String clientName) {
        this(masterName, sentinels, poolConfig, null, connectionTimeout, soTimeout, masterWriteOnly, clientName);
    }

    public ZSJedisSentinelPool(String masterName, Set<String> sentinels, final GenericObjectPoolConfig poolConfig, final String password, int connectionTimeout, int soTimeout, boolean masterWriteOnly) {
        this(masterName, sentinels, poolConfig, password, connectionTimeout, soTimeout, masterWriteOnly, null);
    }
    public ZSJedisSentinelPool(String masterName, Set<String> sentinels, final GenericObjectPoolConfig poolConfig, final String password, int connectionTimeout, int soTimeout, boolean masterWriteOnly, String clientName) {

    	if (sentinels!=null && sentinels.size()>0){
    		_sentinels.addAll(sentinels);
    	}

        this.poolConfig = poolConfig;
        this.connectionTimeout = connectionTimeout;
        this.soTimeout = soTimeout;
        if (!StringUtil.isBlank(password)) {
            this.password = password;
        }
        this.database = Protocol.DEFAULT_DATABASE;
        this.masterName = masterName;
        this.masterWriteOnly = masterWriteOnly;
        if (!StringUtil.isBlank(clientName)) {
            this.clientName = clientName;
        }

        HostAndPort master = initSentinels(_sentinels, masterName);
        initPool(master);

        initReadPool();
    }

    private void initReadPool() {
        for (HostAndPort hap : readHaps) {
            insertReadPool(hap);
        }
    }

    /**
     * 获取写实例
     * @return
     */
    public Jedis getWriteResource() {
        Jedis result = getResource();
//        System.out.printf("++++++++++[%s] getWriteResource:[%s]\n",Thread.currentThread().getId(),result.hashCode());
        return result;
    }

    /**
     * 获取读实例
     * @return
     */
    public Jedis getReadResource() {
        if (jedisReadPools.size() == 0) {
            throw new RuntimeException("there is no jedis for read");
        }

        // 从redis读池中随机获取一个实例
        int randNum = rand.nextInt(jedisReadPools.size());
        JedisPoolExt jedisPoolExt = jedisReadPools.get(randNum);
//        System.out.printf("++++++++++ [%s] getReadResource jedisPoolExt:host:port[%s:%s:%s] \n",Thread.currentThread().getId(),jedisPoolExt.hashCode(), jedisPoolExt.getHostAndPort().getHost(),jedisPoolExt.getHostAndPort().getPort());
        JedisPool jedisPool = jedisPoolExt.getJedisPool();
//        System.out.printf("++++++++++ [%s] getReadResource jedisPool[%s] \n",Thread.currentThread().getId(),jedisPool.hashCode());
        Jedis result = jedisPool.getResource();
//        System.out.printf("++++++++++ [%s] getReadResource jedis[%s] \n",Thread.currentThread().getId(),result.hashCode());
        return result;
    }

    public void resetReadPool() {
        initSentinels(_sentinels, masterName);
        initReadPool();
    }

    public void destroy() {
        for (MasterListener m : masterListeners) {
            m.shutdown();
        }
        for (SlaveDownListener m : slaveDownListeners) {
            m.shutdown();
        }
        for (SlaveUpListener m : slaveUpListeners) {
            m.shutdown();
        }
        super.destroy();
    }

    public HostAndPort getCurrentHostMaster() {
        return currentHostMaster;
    }

    protected void initPool(HostAndPort master) {
        if (!master.equals(currentHostMaster)) {
            currentHostMaster = master;
            if (factory == null) {
                factory = new JedisFactory(master.getHost(), master.getPort(), connectionTimeout,
                        soTimeout, password, database, clientName);
                initPool(poolConfig, factory);
            } else {
                factory.setHostAndPort(currentHostMaster);
                // although we clear the pool, we still have to check the
                // returned object
                // in getResource, this call only clears idle instances, not
                // borrowed instances
                internalPool.clear();
            }

            log.info("Created JedisPool to master at " + master);
        }
    }

    protected HostAndPort initSentinels(Set<String> sentinels, final String masterName) {

        HostAndPort master = null;
        boolean sentinelAvailable = false;

        log.info("Trying to find master from available Sentinels...");

        for (String sentinel : sentinels) {
            final HostAndPort hap = toHostAndPort(Arrays.asList(sentinel.split(":")));

            log.fine("Connecting to Sentinel " + hap);

            Jedis jedis = null;
            try {
                jedis = new Jedis(hap.getHost(), hap.getPort());


                List<String> masterAddr = jedis.sentinelGetMasterAddrByName(masterName);

                // connected to sentinel...
                sentinelAvailable = true;

                if (masterAddr == null || masterAddr.size() != 2) {
                    log.warning("Can not get master addr, master name: " + masterName + ". Sentinel: " + hap
                            + ".");
                    continue;
                }

                master = toHostAndPort(masterAddr);
                log.fine("Found Redis master at " + master);

                initReadHaps(jedis, masterName, master);

                break;
            } catch (JedisConnectionException e) {
                log.warning("Cannot connect to sentinel running @ " + hap + ". Trying next one.");
            } finally {
                if (jedis != null) {
                    jedis.close();
                }
            }
        }

        if (master == null) {
            if (sentinelAvailable) {
                // can connect to sentinel, but master name seems to not
                // monitored
                throw new JedisException("Can connect to sentinel, but " + masterName
                        + " seems to be not monitored...");
            } else {
                throw new JedisConnectionException("All sentinels down, cannot determine where is "
                        + masterName + " master is running...");
            }
        }

        log.info("Redis master running at " + master + ", starting Sentinel listeners...");

        for (MasterListener masterListener : masterListeners) {
            masterListener.shutdown();
        }
        masterListeners.clear();

        for (SlaveDownListener slaveDownListener : slaveDownListeners) {
            slaveDownListener.shutdown();
        }
        slaveDownListeners.clear();

        for (SlaveUpListener slaveUpListener : slaveUpListeners) {
            slaveUpListener.shutdown();
        }
        slaveUpListeners.clear();

        for (String sentinel : sentinels) {
            final HostAndPort hap = toHostAndPort(Arrays.asList(sentinel.split(":")));
            MasterListener masterListener = new MasterListener(masterName, hap.getHost(), hap.getPort());
            masterListeners.add(masterListener);
            masterListener.start();

            SlaveDownListener slaveDownListener = new SlaveDownListener(masterName, hap.getHost(), hap.getPort());
            slaveDownListeners.add(slaveDownListener);
            slaveDownListener.start();

            SlaveUpListener slaveUpListener = new SlaveUpListener(masterName, hap.getHost(), hap.getPort());
            slaveUpListeners.add(slaveUpListener);
            slaveUpListener.start();

        }

        return master;
    }

    private void initReadHaps(Jedis jedis, String masterName, HostAndPort master) {
        List<Map<String, String>> slaveList = jedis.sentinelSlaves(masterName);
        
        // 去掉master以及宕掉的slave(master可能在slaveList中，可能是jedis的bug)
        
        for (Iterator<Map<String, String>> iterator = slaveList.iterator(); iterator.hasNext();) {
			Map<String, String> slave = iterator.next();
	        if(!slave.get("flags").equals("slave")){
	        	iterator.remove();
	        }
		}
        
        initReadHaps(slaveList, master);
    }

    /**
     * 初始化读池
     * @param slaveList
     * @param master
     * @return
     */
    private List<HostAndPort> initReadHaps(List<Map<String, String>> slaveList, HostAndPort master) {
        // master允许读时,才放入读池
        if (!masterWriteOnly) {
            readHaps = createReadHaps(slaveList, master);
        } else {
            readHaps = createReadHaps(slaveList);
        }
        return readHaps;
    }

    /**
     * 将slave节点作为读节点放入读池
     * @param slaveList
     * @return
     */
    private List<HostAndPort> createReadHaps(List<Map<String, String>> slaveList) {
        List<HostAndPort> haps = new ArrayList<>();

        for (Map<String, String> slave : slaveList) {
            HostAndPort hap = new HostAndPort(slave.get("ip"), Integer.valueOf(slave.get("port")));
            haps.add(hap);
        }

        return haps;
    }

    private List<HostAndPort> createReadHaps(List<Map<String, String>> slaveList, HostAndPort master) {
        List<HostAndPort> haps = createReadHaps(slaveList);
        haps.add(master);
        return haps;
    }

    /**
     * 读池增加新节点
     * @param hostAndPort
     */
    protected synchronized void insertReadPool(HostAndPort hostAndPort) {
        boolean exists = false;
        for (JedisPoolExt oldJedisPool : jedisReadPools) {
            if (oldJedisPool.getHostAndPort().equals(hostAndPort)) {
                exists = true;
                break;
            }
        }
        if (!exists) {
            JedisPoolExt newJedisPool = new JedisPoolExt(poolConfig, hostAndPort.getHost(), hostAndPort.getPort(), connectionTimeout, soTimeout, password, Protocol.DEFAULT_DATABASE, clientName);
            jedisReadPools.add(newJedisPool);

            log.info("Add JedisReadPool at " + hostAndPort);
        }
    }

    /**
     * 读池中去掉一个读节点
     * @param hostAndPort
     */
    protected synchronized void removeFromReadPool(HostAndPort hostAndPort) {
        for (JedisPoolExt jedisPoolExt : jedisReadPools) {
            if (jedisPoolExt.getHostAndPort().equals(hostAndPort)) {
                jedisReadPools.remove(jedisPoolExt);
                log.info("Remove JedisReadPool at " + hostAndPort);
                break;
            }
        }
    }

    protected HostAndPort toHostAndPort(List<String> getMasterAddrByNameResult) {
        String host = getMasterAddrByNameResult.get(0);
        int port = Integer.parseInt(getMasterAddrByNameResult.get(1));
        return new HostAndPort(host, port);
    }

    @Override
    public Jedis getResource() {
        while (true) {
            Jedis jedis = super.getResource();
            jedis.setDataSource(this);

            // get a reference because it can change concurrently
            final HostAndPort master = currentHostMaster;
            final HostAndPort connection = new HostAndPort(jedis.getClient().getHost(), jedis.getClient()
                    .getPort());

            if (master.equals(connection)) {
                // connected to the correct master
                return jedis;
            } else {
                jedis.close();
            }
        }
    }

    /**
     * @deprecated starting from Jedis 3.0 this method won't exist. Resouce cleanup should be done
     * using @see {@link Jedis#close()}
     */
    public void returnBrokenResource(final Jedis resource) {
        if (resource != null) {
            returnBrokenResourceObject(resource);
        }
    }

    /**
     * @deprecated starting from Jedis 3.0 this method won't exist. Resouce cleanup should be done
     * using @see {@link Jedis#close()}
     */
    public void returnResource(final Jedis resource) {
        if (resource != null) {
            resource.resetState();
            returnResourceObject(resource);
        }
    }

    /**
     * master切换事件监听
     */
    protected class MasterListener extends Thread {

        protected String masterName;
        protected String host;
        protected int port;
        protected long subscribeRetryWaitTimeMillis = 5000;
        protected Jedis j;
        protected AtomicBoolean running = new AtomicBoolean(false);

        public MasterListener(String masterName, String host, int port) {
            super(String.format("MasterListener-%s-[%s:%d]", masterName, host, port));
            this.masterName = masterName;
            this.host = host;
            this.port = port;
        }

		public MasterListener(String masterName, String host, int port,
				long subscribeRetryWaitTimeMillis) {
			this(masterName, host, port);
			this.subscribeRetryWaitTimeMillis = subscribeRetryWaitTimeMillis;
		}       
        
        public void run() {

            running.set(true);

            while (running.get()) {

                j = new Jedis(host, port);

                try {
                	
                    // double check that it is not being shutdown
                    if (!running.get()) {
                      break;
                    }
                	
                    j.subscribe(new JedisPubSub() {
                        @Override
                        public void onMessage(String channel, String message) {
                            log.info("Sentinel " + host + ":" + port + " published: " + channel + " " + message + ".");

                            String[] switchMasterMsg = message.split(" ");

                            if (switchMasterMsg.length > 3) {

                                if (masterName.equals(switchMasterMsg[0])) {
                                    initPool(toHostAndPort(Arrays.asList(switchMasterMsg[3], switchMasterMsg[4])));

                                    if (masterWriteOnly) {
                                        // 如果master只做写,则将新的master从读池去掉
                                        removeFromReadPool(toHostAndPort(Arrays.asList(switchMasterMsg[3], switchMasterMsg[4])));
                                    } else {
                                        // 如果master同时允许读写,则将旧master从读池去掉
                                        removeFromReadPool(toHostAndPort(Arrays.asList(switchMasterMsg[1], switchMasterMsg[2])));
                                    }
                                } else {
                                    log.fine("Ignoring message on +switch-master for master name "
                                            + switchMasterMsg[0] + ", our master name is " + masterName);
                                }

                            } else {
                                log.severe("Invalid message received on Sentinel " + host + ":" + port
                                        + " on channel +switch-master: " + message);
                            }

                        }
                    }, "+switch-master");
                } catch (JedisConnectionException e) {
                    if (running.get()) {
                        log.severe("Lost connection to Sentinel at " + host + ":" + port
                            + ". Sleeping "+ subscribeRetryWaitTimeMillis +"ms and retrying.");
                        try {
                          Thread.sleep(subscribeRetryWaitTimeMillis);
                        } catch (InterruptedException e1) {
                          e1.printStackTrace();
                        }
                      } else {
                        log.fine("Unsubscribing from Sentinel at " + host + ":" + port);
                      }                	
                    runningSleep(running.get(), host, port, subscribeRetryWaitTimeMillis);
                }
            }
        }

        public void shutdown() {
            shutdownListener(j, running, host, port);
        }
    }

    /**
     * slave故障恢复&新增slave节点 事件监听
     */
    protected class SlaveUpListener extends Thread {

        protected String masterName;
        protected String host;
        protected int port;
        protected long subscribeRetryWaitTimeMillis = 5000;
        protected Jedis j;
        protected AtomicBoolean running = new AtomicBoolean(false);

        public SlaveUpListener(String masterName, String host, int port) {
        	super(String.format("SlaveUpListener-%s-[%s:%d]", masterName, host, port));
            this.masterName = masterName;
            this.host = host;
            this.port = port;
        }
        
		public SlaveUpListener(String masterName, String host, int port,
				long subscribeRetryWaitTimeMillis) {
			this(masterName, host, port);
			this.subscribeRetryWaitTimeMillis = subscribeRetryWaitTimeMillis;
		}    

        public void run() {
            running.set(true);

            while (running.get()) {

                j = new Jedis(host, port);

                try {
                    // double check that it is not being shutdown
                    if (!running.get()) {
                      break;
                    }                	
                	
                    j.subscribe(new JedisPubSub() {
                        @Override
                        public void onMessage(String channel, String message) {
                            log.info("Sentinel " + host + ":" + port + " published: " + channel + " " + message + ".");

                            String[] switchMasterMsg = message.split(" ");

                            if (switchMasterMsg.length > 7) {
                                String slaveHost = switchMasterMsg[2];
                                int slavePort = Integer.valueOf(switchMasterMsg[3]);
                                HostAndPort hap = new HostAndPort(slaveHost, slavePort);
                                insertReadPool(hap);
                            } else {
                                log.severe("Invalid message received on Sentinel " + host + ":" + port
                                        + " on channel " + channel + ": " + message);
                            }
                        }
                    }, "-sdown"/*, "+slave"*/);//去掉+slave信息，因为主从切换通过+slave和+sdown实现，多sentinel时信息不能保证顺序收到，造成失效的slave在链接池中。以后通过cluster方式提高性能，不会通过增加slave方式。
                } catch (JedisConnectionException e) {
                    runningSleep(running.get(), host, port, subscribeRetryWaitTimeMillis);
                } finally {
                    j.close();
                }
            }
        }

        public void shutdown() {
            shutdownListener(j, running, host, port);
        }
    }

    protected void shutdownListener(Jedis j, AtomicBoolean running, String host, int port) {
        try {
            log.fine("Shutting down listener on " + host + ":" + port);
            running.set(false);
            j.disconnect();
        } catch (Exception e) {
            log.log(Level.SEVERE, "Caught exception while shutting down: ", e);
        }
    }

    protected void runningSleep(boolean running, String host, int port, long waitTimeMillis) {
        if (running) {
            log.severe("Lost connection to Sentinel at " + host + ":" + port
                    + ". Sleeping " + waitTimeMillis + "ms and retrying.");
            try {
                Thread.sleep(waitTimeMillis);
            } catch (InterruptedException e1) {
                e1.printStackTrace();
            }
        } else {
            log.fine("Unsubscribing from Sentinel at " + host + ":" + port);
        }
    }

    /**
     * slave挂掉事件监听
     */
    protected class SlaveDownListener extends Thread {

        protected String masterName;
        protected String host;
        protected int port;
        protected long subscribeRetryWaitTimeMillis = 5000;
        protected Jedis j;
        protected AtomicBoolean running = new AtomicBoolean(false);

        public SlaveDownListener(String masterName, String host, int port) {
        	super(String.format("SlaveDownListener-%s-[%s:%d]", masterName, host, port));
            this.masterName = masterName;
            this.host = host;
            this.port = port;
        }

		public SlaveDownListener(String masterName, String host, int port,
				long subscribeRetryWaitTimeMillis) {
			this(masterName, host, port);
			this.subscribeRetryWaitTimeMillis = subscribeRetryWaitTimeMillis;
		}  
		
        public void run() {

            running.set(true);

            while (running.get()) {

                j = new Jedis(host, port);

                try {
                	
                    // double check that it is not being shutdown
                    if (!running.get()) {
                      break;
                    }   
                    
                    j.subscribe(new JedisPubSub() {
                        @Override
                        public void onMessage(String channel, String message) {
                            log.info("Sentinel " + host + ":" + port + " published: " + channel + " " + message + ".");

                            String[] switchMasterMsg = message.split(" ");

                            if (switchMasterMsg.length > 7) {
                                String slaveHost = switchMasterMsg[2];
                                int slavePort = Integer.valueOf(switchMasterMsg[3]);
                                HostAndPort hap = new HostAndPort(slaveHost, slavePort);
                                removeFromReadPool(hap);
                            } else if (switchMasterMsg.length != 4) {
                                // master +sdown length=4
                                log.severe("Invalid message received on Sentinel " + host + ":" + port
                                        + " on channel +sdown: " + message);
                            }
                        }
                    }, "+sdown");
                } catch (JedisConnectionException e) {
                    runningSleep(running.get(), host, port, subscribeRetryWaitTimeMillis);
                }
            }
        }

        public void shutdown() {
            shutdownListener(j, running, host, port);
        }
    }

}