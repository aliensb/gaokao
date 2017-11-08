package win.ccav.utils.jredis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * 链接公共的主从redis
 * @author ztzh_xiaoyb
 *
 */
@Component
public class JedisPoolFactory {
	@Autowired
	private JedisPool jedisPool;

	public JedisPool getJedisPool() {
		return jedisPool;
	}

	public void setJedisPool(JedisPool jedisPool) {
		this.jedisPool = jedisPool;
	}
	public Jedis getJedis(){
		if(jedisPool!=null){
			Jedis jedis=jedisPool.getResource();
			jedis.select(1);
			return jedis;
		}
		return null;
	}
	public void release(Jedis jedis){
		if (jedis!=null){
			jedis.close();
		}
	}
}