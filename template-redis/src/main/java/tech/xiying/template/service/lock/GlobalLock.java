package tech.xiying.template.service.lock;


import tech.xiying.template.exception.CommonRuntimeException;

/**
 * @interface GlobalLock
 * @Description
 * @Author shanghao5
 * @Date 2021/3/17 16:18
 **/
public interface GlobalLock {

    /**
     * 申请加锁,此操作为阻塞操作,且不响应线程中断
     * 超过指定时间会抛出异常,程序需要对异常处理, 最大锁住时间为1分钟
     * @throws CommonRuntimeException 在指定的时间内获取锁失败
     */
    boolean lock(String lockKey, long timeout) throws CommonRuntimeException;

    /**
     * 申请加锁,此操作为阻塞操作,且不响应线程中断
     * 超过指定时间会抛出异常,程序需要对异常处理, 最大锁住时间为1分钟
     * @throws CommonRuntimeException 在指定的时间内获取锁失败
     */
    boolean lock(String lockKey) throws CommonRuntimeException;

    /**
     * 释放锁
     */
    void unlock(String lockKey);
}
