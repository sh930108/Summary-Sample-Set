package tech.xiying.template.service.cach;

import java.util.concurrent.TimeUnit;

/**
 * @interface ICacheService
 * @Description
 * @Author shanghao5
 * @Date 2021/3/8 22:00
 **/
public interface ICacheService {

    String getString(String key);

    Integer getInteger(String key);

    Long getLong(String key);

    <T> T getObject(String key);

    /**
     * 获取过期时间
     * @param key key
     * @return 毫秒数
     */
    Long getExpire(String key);

    <T> void set(String key, T value);

    /**
     * 添加超时时间
     *
     * @param key
     * @param value
     * @param timeout
     * @param unit
     * @param <T>
     */
    <T> void set(String key, T value, long timeout, TimeUnit unit);

    /**
     * 原子自增
     * @param key
     * @return
     */
    Long incr(String key);

    <T> Boolean setIfAbsent(String key, T value);

    <T> Boolean setIfAbsent(String key, T value, long timeout, TimeUnit timeUnit);

    Boolean delete(String key);

    void clearCacheLikeKey(String key);

}
