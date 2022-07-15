package tech.xiying.template.service.cach.impl;


import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import tech.xiying.template.service.cach.ICacheService;
import tech.xiying.template.utils.NumberUtils;

import javax.annotation.Resource;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName RedisCacheServiceImpl
 * @Description
 * @Author shanghao5
 * @Date 2021/3/9 9:44
 **/
@Service
public class RedisCacheServiceImpl implements ICacheService {

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    @Override
    public String getString(String key) {
        return StringUtils.isEmpty(key) ? null : Objects.toString(this.redisTemplate.opsForValue().get(key), (String)null);
    }

    @Override
    public Integer getInteger(String key) {
        return StringUtils.isEmpty(key) ? null : NumberUtils.toInteger(this.redisTemplate.opsForValue().get(key), (Integer)null);
    }

    @Override
    public Long getLong(String key) {
        return StringUtils.isEmpty(key) ? null : NumberUtils.toLong(this.redisTemplate.opsForValue().get(key), (Long)null);
    }

    @Override
    public <T> T getObject(String key) {
        return StringUtils.isEmpty(key) ? null : (T) this.redisTemplate.opsForValue().get(key);
    }

    @Override
    public Long getExpire(String key) {
        return redisTemplate.getExpire(key,TimeUnit.MILLISECONDS);
    }

    @Override
    public <T> void set(String key, T value) {
        if (!StringUtils.isEmpty(key) && value != null) {
            this.redisTemplate.opsForValue().set(key, value, 30L, TimeUnit.MINUTES);
        }
    }

    @Override
    public <T> void set(String key, T value, long timeout, TimeUnit unit) {
        if (!StringUtils.isEmpty(key) && value != null) {
            this.redisTemplate.opsForValue().set(key, value, timeout, unit);
        }
    }

    @Override
    public Long incr(String key) {
        return redisTemplate.opsForValue().increment(key);
    }

    @Override
    public <T> Boolean setIfAbsent(String key, T value) {
        return this.redisTemplate.opsForValue().setIfAbsent(key, value);
    }

    @Override
    public <T> Boolean setIfAbsent(String key, T value, long timeout, TimeUnit unit) {
        return this.redisTemplate.opsForValue().setIfAbsent(key, value, timeout, unit);
    }

    @Override
    public Boolean delete(String key) {
        return StringUtils.isNotBlank(key) ? this.redisTemplate.delete(key) : false;
    }

    @Override
    public void clearCacheLikeKey(String key) {
        if (StringUtils.isNotBlank(key)) {
            Set<String> keys = this.redisTemplate.keys(key + "*");
            if (CollectionUtils.isNotEmpty(keys)) {
                this.redisTemplate.delete(keys);
            }
        }
    }
}
