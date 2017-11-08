package win.ccav.utils.jredis;

import org.slf4j.Logger;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Transaction;
import win.ccav.utils.SerializationUtil;
import win.ccav.utils.json.JsonUtil;

import javax.annotation.Resource;
import java.util.*;


@Component(value="jedisTemplate")
public class JedisTemplate {
	private static final Logger logger
		= org.slf4j.LoggerFactory.getLogger(JedisTemplate.class);
	@Resource
	private JedisPoolFactory jedisPoolFactory;

	public JedisPoolFactory getJedisPoolFactory() {
		return jedisPoolFactory;
	}

	public void setJedisPoolFactory( JedisPoolFactory jedisPoolFactory ) {
		this.jedisPoolFactory = jedisPoolFactory;
	}

	public String get( String type, String key ) {
		Jedis jedis = null;
		try {
			jedis = jedisPoolFactory.getJedis();
			//jedis = jedisPoolFactory.getReadJedis();
			return jedis.get( type + ":" + key );

		} finally {
			jedisPoolFactory.release( jedis );
		}
	}

	public void set( String type, String key, String value ) {
		Jedis jedis = null;
		try {
			jedis = jedisPoolFactory.getJedis();

			jedis.set( type + ":" + key, value );

		} finally {
			jedisPoolFactory.release( jedis );
		}
	}
	
	public void mset( String ...keyValues ) {
		Jedis jedis = null;
		try {
			if(keyValues.length > 2){
				jedis = jedisPoolFactory.getJedis();
				jedis.mset(keyValues);
			}else{}
		} finally {
			jedisPoolFactory.release( jedis );
		}
	}
	
	public List<String> mget( String ...keys ) {
		Jedis jedis = null;
		try {
			jedis = jedisPoolFactory.getJedis();
			return jedis.mget(keys);
		} finally {
			jedisPoolFactory.release( jedis );
		}
	}
	
	public void setObj(String type,String key ,Object obj){
		Jedis jedis = null;
		try {
			jedis = jedisPoolFactory.getJedis();

			jedis.set( (type + ":" + key).getBytes(), SerializationUtil.serialize(obj) );
				
		} finally {
			jedisPoolFactory.release( jedis );
		}
	}
	
	/**
	 * 保存对象
	 * 如果存在则删除后再保存
	 * @param type
	 * @param key
	 * @param obj
	 * @param deadTime
	 */
	public void setObj(String type,String key ,Object obj, int deadTime){
		Jedis jedis = null;
		try {
			jedis = jedisPoolFactory.getJedis();
			byte[] jkey = (type + ":" + key).getBytes();
			
			if (jedis.exists( jkey)) {
				jedis.del(jkey);
			}
			jedis.set( jkey
					, SerializationUtil.serialize(obj), "NX".getBytes(),
					"EX".getBytes(), deadTime );
		} finally {
			jedisPoolFactory.release( jedis );
		}
	}
	
	public <T extends Object> T getObj(String type,String key){
		Jedis jedis = null;
		try {
			jedis = jedisPoolFactory.getJedis();
//			jedis = jedisPoolFactory.getReadJedis();
			byte[] bytes = jedis.get((type + ":" + key).getBytes());
			return 	(T) SerializationUtil.deserialize(bytes);
		} finally {
			jedisPoolFactory.release( jedis );
		}
	}

	public void del( String type, String key ) {
		Jedis jedis = null;
		try {
			jedis = jedisPoolFactory.getJedis();

			jedis.del( type + ":" + key );

		} finally {
			jedisPoolFactory.release( jedis );
		}
	}
	
	/**
	 * 批量删除以key开头的所有redis值
	 * 比如 key为abc，则会删除所有以abc开头的之都会删除，请慎重使用
	 * @param key
	 */
	public void batchDel(String key) {
		Jedis jedis = null;
		try {
			jedis = jedisPoolFactory.getJedis();

			Set<String> set = jedis.keys(key+"*");
			Iterator<String> it = set.iterator();
			while(it.hasNext()) {
				String k = it.next();
				System.out.println("del key:"+k);
				jedis.del(k);
			}

		} finally {
			jedisPoolFactory.release( jedis );
		}
	}

	public synchronized void set( String type, String key, Object obj, int deadTime ) {
		Jedis jedis = null;
		try {
			jedis = jedisPoolFactory.getJedis();
			String value = null;
			if ( !obj.getClass().equals( String.class ) ) {
				value = JsonUtil.toJSON( obj );
			} else {
				value = obj.toString();
			}
			jedis.set( type + ":" + key, value, "NX", "EX", deadTime );

		} finally {
			jedisPoolFactory.release( jedis );
		}
	}
	
	/**
	 * 保存对象
	 * 如果存在则删除后再添加
	 * @param type
	 * @param key
	 * @param obj
	 * @param deadTime
	 */
	public synchronized void set2( String type, String key, Object obj, int deadTime ) {
		Jedis jedis = null;
		try {
			jedis = jedisPoolFactory.getJedis();
			String value = null;
			if ( !obj.getClass().equals( String.class ) ) {
				value = JsonUtil.toJSON( obj );
			} else {
				value = obj.toString();
			}
			String key1 = type + ":" + key;
			if (jedis.exists(key1)) {
				jedis.del(key1);
			}
			jedis.set( key1, value, "NX", "EX", deadTime );

		} finally {
			jedisPoolFactory.release( jedis );
		}
	}

	public void expire( String type, String key, int deadTime ) {
		Jedis jedis = null;
		try {
			jedis = jedisPoolFactory.getJedis();

			jedis.expire( type + ":" + key, deadTime );
		} finally {
			jedisPoolFactory.release( jedis );
		}
	}

	public boolean exists( String type, String key ) {
		Jedis jedis = null;
		try {
			jedis = jedisPoolFactory.getJedis();
//			jedis = jedisPoolFactory.getReadJedis();
			return jedis.exists( type + ":" + key );

		} finally {
			jedisPoolFactory.release( jedis );
		}
	}
	public void multi( String type, String value ) {
		Jedis jedis = null;
		try {
			jedis = jedisPoolFactory.getJedis();
			
			Transaction t = jedis.multi();
			t.set(type, value);
			t.exec();
			
		} finally {
			jedis.close();
		}
	}

	public void set( String key, String value ) {
		Jedis jedis = null;
		try {
			jedis = jedisPoolFactory.getJedis();
			jedis.set( key, value );
		} catch ( Throwable e ) {
			e.printStackTrace();
		} finally {
			jedis.close();
		}
	}
	
	public List<String> lrange(String key,final long start, final long end){
		Jedis jedis = null;
		try {
			jedis = jedisPoolFactory.getJedis();
			return jedis.lrange(key, start, end);
		} finally {
			jedis.close();
		}
	}	
	public void hmset(String type ,String key,Map<String,String> values){
		Jedis jedis = null;
		try {
			jedis = jedisPoolFactory.getJedis();
			jedis.hmset(type +":"+key, values);
		} catch ( Throwable e ) {
			e.printStackTrace();
		} finally {
			jedis.close();
		}
	}
	public void hmset(String type ,String key,Map<String,String> values,int expire){
		Jedis jedis = null;
		try {
			jedis = jedisPoolFactory.getJedis();
			jedis.hmset(type +":"+ key, values);
			jedis.expire(type +":"+ key, expire);
		} catch ( Throwable e ) {
			e.printStackTrace();
		} finally {
			jedis.close();
		}
	}
	
	public String hget(String key,String field){
		Jedis jedis = null;
		try {
			jedis = jedisPoolFactory.getJedis();
			return jedis.hget( key, field );
		} finally {
			jedis.close();
		}
	}	
	public void hset(String key,String field,String value){
		Jedis jedis = null;
		try {
			jedis = jedisPoolFactory.getJedis();
			jedis.hset( key, field,value );
		} finally {
			jedis.close();
		}
	}
	public List<String> hmget(String key,String... field){
		Jedis jedis = null;
		try {
			jedis = jedisPoolFactory.getJedis();
			return jedis.hmget( key, field );
		} finally {
			jedis.close();
		}
	}
	public Map<String,String> hmget(String type){
		Jedis jedis = null;
		Map<String,String> values = new HashMap<String,String>();
		try {
			jedis = jedisPoolFactory.getJedis();
			values = jedis.hgetAll(type);
		} catch ( Throwable e ) {
			e.printStackTrace();
		} finally {
			jedis.close();
		}
		return values;
	}
	/**
	 * redis 集合存储
	 * @param key
	 * @param values
	 */
	public void sadd( String key, List<String> values ) {
		String[] value = values.toArray(new String[values.size()]);
		Jedis jedis = null;
		try {
			jedis = jedisPoolFactory.getJedis();
			jedis.sadd(key, value);
		} catch ( Throwable e ) {
			e.printStackTrace();
		} finally {
			jedis.close();
		}
	}
	
	/**
	 * 给集合中添加元素
	 * @param key
	 * @param value
	 */
	public void sadd(String key, String value){
		Jedis jedis = null;
		try {
			jedis = jedisPoolFactory.getJedis();
			jedis.sadd(key, value);
		} catch ( Throwable e ) {
			e.printStackTrace();
		} finally {
			jedis.close();
		}
	}

	public boolean sismember(String key,String member){
		Jedis jedis = null;
		try {
			jedis = jedisPoolFactory.getJedis();
			return jedis.sismember(key, member);
		} catch ( Throwable e ) {
			e.printStackTrace();
		} finally {
			jedis.close();
		}
		return false;
	}
	/**
	 * 获取集合中的所有元素
	 * @param key
	 * @return
	 */
	public Set<String> smember(String key){
		Jedis jedis = null;
		try {
			jedis = jedisPoolFactory.getJedis();
			return jedis.smembers(key);
		} catch ( Throwable e ) {
			e.printStackTrace();
		} finally {
			jedis.close();
		}
		return new HashSet<String>();
	}
	public String get( String key ) {
		Jedis jedis = null;
		try {
			jedis = jedisPoolFactory.getJedis();
//			jedis = jedisPoolFactory.getReadJedis();
			return jedis.get( key );
		} catch ( Throwable e ) {
			e.printStackTrace();
		} finally {
			jedis.close();
		}

		return null;
	}
	/**
	 * 自增
	 * @param type
	 * @param key
	 * @return
	 */
	public void incr(String type, String key){
		Jedis jedis = null;
		try {
			jedis = jedisPoolFactory.getJedis();
			jedis.incr( type + ":" + key );
		} finally {
			jedisPoolFactory.release( jedis );
		}
	}
	
	public void incrBy(String type, String key,long value){
		Jedis jedis = null;
		try {
			jedis = jedisPoolFactory.getJedis();
			jedis.incrBy(type + ":" + key, value);
		} finally {
			jedisPoolFactory.release( jedis );
		}
	}
	
	public void decrby(String type, String key,Long value){
		Jedis jedis = null;
		try {
			jedis = jedisPoolFactory.getJedis();
			jedis.decrBy(type + ":" + key, value);
		} finally {
			jedisPoolFactory.release( jedis );
		}
	}
	
	public Set<String> getKeySet( String key ) {
		Jedis jedis = null;
		try {
			jedis = jedisPoolFactory.getJedis();
//			jedis = jedisPoolFactory.getReadJedis();
			return jedis.keys( key );
		} catch ( Throwable e ) {
			e.printStackTrace();
		} finally {
			jedis.close();
		}

		return null;
	}

	public boolean del( String key ) {
		Jedis jedis = null;
		try {
			jedis = jedisPoolFactory.getJedis();
			return jedis.del( key ) > 0 ? true : false;
		} catch ( Throwable e ) {
			e.printStackTrace();
		} finally {
			jedis.close();
		}
		return false;
	}

	/**
	 * 存储到redis队列中，插入到表头
	 * 
	 * @param key
	 * @param value
	 */
	public void lpush( byte [] key, byte [] value ) {
		Jedis jedis = null;
		try {
			jedis = jedisPoolFactory.getJedis();
			jedis.lpush( key, value );
		} catch ( Throwable e ) {
			e.printStackTrace();
		} finally {
			jedis.close();
		}
	}
	
	/**
	 * 存储到redis队列中，插入到表头
	 * @param key
	 * @param values
	 */
	public void lpush(String key,List<String> values){
		String[] value = values.toArray(new String[values.size()]);
		Jedis jedis = null;
		try{
			jedis = jedisPoolFactory.getJedis();
			if(jedis.exists(key)){
				jedis.del(key);
			}
			jedis.lpush( key, value );
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			jedis.close();
		}
	}
	
	/**
	 * 存储到redis队列中，插入到表尾
	 * @param key
	 * @param values
	 */
	public void rpush(String key,List<String> values){
		String[] value = values.toArray(new String[values.size()]);
		Jedis jedis = null;
		try{
			jedis = jedisPoolFactory.getJedis();
			if(jedis.exists(key)){
				jedis.del(key);
			}
			jedis.rpush( key, value );
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			jedis.close();
		}
	}
	
	/**
	 * 存储到redis队列中，插入到表头
	 * 
	 * @param key
	 * @param value
	 */
	public void lpush( String key, String value ) {
		Jedis jedis = null;
		try {
			jedis = jedisPoolFactory.getJedis();
			jedis.lpush( key, value );
		} catch ( Throwable e ) {
			e.printStackTrace();
		} finally {
			jedis.close();
		}
	}

	/**
	 * 存储到redis队列中，插入到表尾
	 * 
	 * @param key
	 * @param value
	 */
	public void rpush( byte [] key, byte [] value ) {
		Jedis jedis = null;
		try {
			jedis = jedisPoolFactory.getJedis();
			jedis.rpush( key, value );
		} catch ( Throwable e ) {
			e.printStackTrace();
		} finally {
			jedis.close();
		}
	}
	
	/**
	 * 存储到redis队列，如果队列存在，不删除队列
	 * @param key
	 * @param values
	 */
	public void pushToQueue(String key,List<String> values){
		String[] value = values.toArray(new String[values.size()]);
		Jedis jedis = null;
		try{
			jedis = jedisPoolFactory.getJedis();
			jedis.rpush( key, value );
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			jedis.close();
		}
	}
	
	/**
	 * 存储到redis队列末尾
	 * @param key
	 * @param values
	 */
	public void pushToQueue(String key,String... values){
		Jedis jedis = null;
		try{
			jedis = jedisPoolFactory.getJedis();
			jedis.rpush( key, values );
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			jedis.close();
		}
	}

	public byte [] lpop( byte [] key ) {
		Jedis jedis = null;
		try {
			jedis = jedisPoolFactory.getJedis();
			return jedis.lpop( key );
		} catch ( Throwable e ) {
			e.printStackTrace();
		} finally {
			jedis.close();
		}

		return null;
	}

	public String lpop( String key ) {
		Jedis jedis = null;
		try {
			jedis = jedisPoolFactory.getJedis();
			return jedis.lpop( key );
		} catch ( Throwable e ) {
			e.printStackTrace();
		} finally {
			jedis.close();
		}

		return null;
	}
	public byte [] rpop( byte [] key ) {
		Jedis jedis = null;
		try {
			jedis = jedisPoolFactory.getJedis();
			return jedis.rpop( key );
		} catch ( Throwable e ) {
			e.printStackTrace();
		} finally {
			jedis.close();
		}

		return null;
	}

	public Long getLen( byte [] key ) {
		Jedis jedis = null;
		try {
			jedis = jedisPoolFactory.getJedis();
//			jedis = jedisPoolFactory.getReadJedis();
			return jedis.llen( key );
		} catch ( Throwable e ) {
			e.printStackTrace();
		} finally {
			jedis.close();
		}

		return 0L;
	}

	public List<byte []> getRedisList( byte [] key ) {
		Jedis jedis = null;
		try {
			jedis = jedisPoolFactory.getJedis();
//			jedis = jedisPoolFactory.getReadJedis();
			return jedis.lrange( key, 0, -1 );
		} catch ( Throwable e ) {
			e.printStackTrace();
		} finally {
			jedis.close();
		}
		return null;
	}

	public boolean isExistUpdate( final String... param ) {
		Jedis jedis = null;
		try {
			jedis = jedisPoolFactory.getJedis();
			String key = param[ 0 ];
			int expire = 20;
			if ( param.length > 1 ) {
				expire = Integer.parseInt( param[ 1 ] );
			}
			long status = jedis.setnx( "redis_lock_" + key, "true" );
			if ( status > 0 ) {
				jedis.expire( "redis_lock_" + key, expire );
			}

			return status <= 0 ? true : false;
		} catch ( Throwable e ) {
			e.printStackTrace();
		} finally {
			jedis.close();
		}

		return false;

	}

	public Long unLockRedisKey( final String key ) {
		Jedis jedis = null;
		try {
			jedis = jedisPoolFactory.getJedis();
			return jedis.del( "redis_lock_" + key );
		} catch ( Throwable e ) {
			e.printStackTrace();
		} finally {
			jedis.close();
		}

		return -1L;
	}

	public RedisHash getRedisHash( String key ) {
		return new RedisHash( key );
	}

	public void delBinary( byte [] key ) {
		Jedis jedis = null;
		try {
			jedis = jedisPoolFactory.getJedis();
			jedis.del( key );
		} catch ( Throwable e ) {
			e.printStackTrace();
		} finally {
			jedis.close();
		}
	}

	public void lrem( byte [] key, long count, byte [] value ) {
		Jedis jedis = null;
		try {
			jedis = jedisPoolFactory.getJedis();
			jedis.lrem( key, count, value );
		} catch ( Throwable e ) {
			e.printStackTrace();
		} finally {
			jedis.close();
		}
	}

	/**
	 * Redis 哈希
	 */
	public class RedisHash {

		private String key;

		public RedisHash( String key ) {
			this.key = key;
		}

		/**
		 * 获取指定属性值
		 *
		 * @param field
		 *            属性名
		 *
		 * @return 属性值
		 */
		public String get( final String field ) {
			Jedis jedis = null;
			try {
				jedis = jedisPoolFactory.getJedis();
				return jedis.hget( key, field );
			} catch ( Throwable e ) {
				e.printStackTrace();
			} finally {
				jedis.close();
			}

			return null;

		}

		/**
		 * 获取指定属性值
		 *
		 * @param fields
		 *            属性名
		 *
		 * @return 属性值
		 */
		public List<String> get( final String... fields ) {
			Jedis jedis = null;
			try {
				jedis = jedisPoolFactory.getJedis();
				return jedis.hmget( key, fields );
			} catch ( Throwable e ) {
				e.printStackTrace();
			} finally {
				jedis.close();
			}

			return null;
		}

		/**
		 * 设置属性
		 *
		 * @param field
		 *            属性名
		 * @param value
		 *            属性值
		 */
		public void put( final String field, final String value ) {
			Jedis jedis = null;
			try {
				jedis = jedisPoolFactory.getJedis();
				jedis.hset( key, field, value );
			} catch ( Throwable e ) {
				e.printStackTrace();
			} finally {
				jedis.close();
			}
		}

		/**
		 * 仅当属性名不存在是设置属性
		 *
		 * @param field
		 *            属性名
		 * @param value
		 *            属性值
		 *
		 * @return 0表示属性已存在
		 */
		public int setOnlyIfNotExists( final String field, final String value ) {
			Jedis jedis = null;
			try {
				jedis = jedisPoolFactory.getJedis();
				return jedis.hsetnx( key, field, value ).intValue();
			} catch ( Throwable e ) {
				e.printStackTrace();
			} finally {
				jedis.close();
			}

			return -1;
		}

		/**
		 * 保存多个属性名和属性值
		 *
		 * @param map
		 *            属性
		 */
		public void putAll( final Map<String, String> map ) {
			Jedis jedis = null;
			try {
				jedis = jedisPoolFactory.getJedis();
				jedis.hmset( key, map );
			} catch ( Throwable e ) {
				e.printStackTrace();
			} finally {
				jedis.close();
			}
		}

		/**
		 * 删除一个或多个属性
		 *
		 * @param fields
		 *            属性名
		 *
		 * @return 被删除的属性数量
		 */
		public int delete( final String... fields ) {
			Jedis jedis = null;
			try {
				jedis = jedisPoolFactory.getJedis();
				return jedis.hdel( key, fields ).intValue();
			} catch ( Throwable e ) {
				e.printStackTrace();
			} finally {
				jedis.close();
			}

			return -1;
		}

		/**
		 * 列出所有属性
		 *
		 * @return 所有属性名
		 */
		public List<String> keys() {
			Jedis jedis = null;
			try {
				jedis = jedisPoolFactory.getJedis();
				return new ArrayList<String>( jedis.hkeys( key ) );
			} catch ( Throwable e ) {
				e.printStackTrace();
			} finally {
				jedis.close();
			}

			return null;
		}

		/**
		 * 读取所有属性值并转换为 Map 对象
		 *
		 * @return 所有属性值
		 */
		public Map<String, String> toMap() {
			Jedis jedis = null;
			try {
				jedis = jedisPoolFactory.getJedis();
				return jedis.hgetAll( key );
			} catch ( Throwable e ) {
				e.printStackTrace();
			} finally {
				jedis.close();
			}

			return null;
		}

		/**
		 * 读取key的长度
		 *
		 * @return 所有属性值
		 */
		public Long getLen() {
			Jedis jedis = null;
			try {
				jedis = jedisPoolFactory.getJedis();
				return jedis.hlen( key );
			} catch ( Throwable e ) {
				e.printStackTrace();
			} finally {
				jedis.close();
			}

			return 0L;
		}

		/**
		 * 是否存在一个key
		 *
		 * @return 所有属性值
		 */
		public Boolean isExist( final String field ) {
			Jedis jedis = null;
			try {
				jedis = jedisPoolFactory.getJedis();
				return jedis.hexists( key, field );
			} catch ( Throwable e ) {
				e.printStackTrace();
			} finally {
				jedis.close();
			}

			return false;
		}

	}

}
