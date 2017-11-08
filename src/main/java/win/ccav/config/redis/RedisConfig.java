package win.ccav.config.redis;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * Created by john on 2017/10/25.
 */
@Configuration
@PropertySource("classpath:application.properties")
public class RedisConfig {
//    @Value("${redis.host}")
//    public String host;
//    @Value("${redis.port}")
//    public int port;
//    @Value("${redis.timeout}")
//    public int timeout;
//    @Value("${redis.password}")
//    public String password;



    @Bean
    public JedisPoolConfig jedisPoolConfig(){
        JedisPoolConfig config=new JedisPoolConfig();
        config.setMaxWaitMillis(5000);
        config.setMaxIdle(40);
        config.setMaxTotal(60);
        config.setMinIdle(10);
        config.setTestOnBorrow(false);
        config.setTestOnReturn(false);
        return config;
    }
    @Bean
    public JedisPool jedisPool(){
        JedisPool jedisPool=new JedisPool(jedisPoolConfig(),"123.000.107.191",6379,2000,"123456");
        return jedisPool;
    }
}
