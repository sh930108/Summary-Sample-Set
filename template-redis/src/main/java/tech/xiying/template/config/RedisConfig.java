package tech.xiying.template.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.*;


/**
 * @ClassName RedisConfig
 * @Description
 * @Author shanghao5
 * @Date 2021/3/8 20:06
 **/
@Configuration
@EnableCaching
public class RedisConfig {

    private static Logger logger = LoggerFactory.getLogger(RedisConfig.class);

    @Value("${spring.redis.host}")
    private String host;
    @Value("${spring.redis.port}")
    private int port;
    @Value("${spring.redis.timeout}")
    private int timeout;
    @Value("${spring.redis.database:0}")
    private int database;
    @Value("${spring.redis.password}")
    private String password;
    @Value("${spring.redis.lettuce.pool.max-active}")
    private int maxActive;
    @Value("${spring.redis.lettuce.pool.max-wait}")
    private int maxWait;
    @Value("${spring.redis.lettuce.pool.max-idle}")
    private int maxIdle;
    @Value("${spring.redis.lettuce.pool.min-idle}")
    private int minIdle;
    @Value("${spring.redis.lettuce.shutdown-timeout}")
    private int shutdownTimeout;

    /**
     * 该bean用于处理将tgt和st的内存缓存迁移至redis中
     * @return
     */
    @Bean
    public RedisTemplate redisTemplate(LettuceConnectionFactory redisConnectionFactory) {
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(redisConnectionFactory);

        RedisSerializer<String> redisKeySerializer = new StringRedisSerializer();

        //定义value的序列化方式
        Jackson2JsonRedisSerializer redisSerializer = new Jackson2JsonRedisSerializer(Object.class);
        ObjectMapper om = new ObjectMapper();
        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        redisSerializer.setObjectMapper(om);

        redisTemplate.setKeySerializer(redisKeySerializer);
        redisTemplate.setHashKeySerializer(redisKeySerializer);
        redisTemplate.setValueSerializer(redisSerializer);
        redisTemplate.setHashValueSerializer(redisSerializer);
        redisTemplate.afterPropertiesSet();

        return redisTemplate;
    }

    /**
     * 服务于分布式锁
     * @param redisConnectionFactory
     * @return
     */
    @Bean
    public StringRedisTemplate stringRedisTemplate(LettuceConnectionFactory redisConnectionFactory) {
        StringRedisTemplate stringRedisTemplate = new StringRedisTemplate();
        stringRedisTemplate.setConnectionFactory(redisConnectionFactory);
        return stringRedisTemplate;
    }


    /**
     * Redis-Standalone的配置参考：{@link RedisStandaloneConfiguration}
     * Redis-Sentinel配置参考：{@link org.springframework.data.redis.connection.RedisSentinelConfiguration}
     * Redis-Cluster配置参考：{@link org.springframework.data.redis.connection.RedisClusterConfiguration}
     */
    @Bean
    public RedisStandaloneConfiguration redisStandaloneConfiguration() {
        RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration();
        redisStandaloneConfiguration.setHostName(host);
        redisStandaloneConfiguration.setPort(port);
        redisStandaloneConfiguration.setPassword(password);
        redisStandaloneConfiguration.setDatabase(database);
        return redisStandaloneConfiguration;
    }

    @Bean
    public LettuceConnectionFactory redisConnectionFactory(RedisStandaloneConfiguration redisStandaloneConfiguration) {
        LettuceConnectionFactory redisConnectionFactory = new LettuceConnectionFactory(redisStandaloneConfiguration);
        return redisConnectionFactory;
    }
}
