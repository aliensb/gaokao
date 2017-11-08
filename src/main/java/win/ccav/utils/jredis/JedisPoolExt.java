package win.ccav.utils.jredis;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class  JedisPoolExt {

    private HostAndPort hostAndPort;

    private JedisPool jedisPool;

    public JedisPoolExt(GenericObjectPoolConfig poolConfig, String host, int port, int connectionTimeout, int soTimeout, String password, int defaultDatabase, String clientName) {
        this.hostAndPort = new HostAndPort(host, port);
        this.jedisPool = new JedisPool(poolConfig, host, port, connectionTimeout, soTimeout, password, defaultDatabase, clientName);
    }

    public Jedis getResource() {
        return jedisPool.getResource();
    }

    public void close() {
        jedisPool.close();
    }

	public HostAndPort getHostAndPort() {
		return hostAndPort;
	}

	public JedisPool getJedisPool() {
		return jedisPool;
	}
    
    
}
