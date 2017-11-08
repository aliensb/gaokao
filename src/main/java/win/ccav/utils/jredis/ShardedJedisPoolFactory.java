package win.ccav.utils.jredis;

import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;

import javax.annotation.Resource;

//@Component
public class ShardedJedisPoolFactory {

	@Resource
	private ShardedJedisPool shardedJedisPool;
	/**
	 * 获取一个jedis连接
	 * @return jedis连接
	 */
	public ShardedJedis getJedis(){
		return shardedJedisPool.getResource();
	}
	
	public void release(ShardedJedis jedis){
		if (jedis!=null){
			jedis.close();
		}
	}
}
