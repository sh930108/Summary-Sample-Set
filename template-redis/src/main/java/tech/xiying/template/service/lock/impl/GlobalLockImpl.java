package tech.xiying.template.service.lock.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.xiying.template.constant.ErrorCode;
import tech.xiying.template.exception.CommonRuntimeException;
import tech.xiying.template.service.cach.ICacheService;
import tech.xiying.template.service.lock.GlobalLock;

import java.util.concurrent.TimeUnit;

/**
 * @ClassName GlobalLockImpl
 * @Description
 * @Author shanghao5
 * @Date 2021/3/17 16:27
 **/
@Service
public class GlobalLockImpl implements GlobalLock {

    private static Logger logger = LoggerFactory.getLogger(GlobalLockImpl.class);

    private static final int MAX_LOCK_TIME = 120;

    @Autowired
    private ICacheService redisService;

    @Override
    public boolean lock(String lockKey, long timeout) throws CommonRuntimeException {
        long nanoTime = System.nanoTime();
        if(timeout >= MAX_LOCK_TIME){
            timeout = MAX_LOCK_TIME;
        }

        do {
            if (redisService.setIfAbsent(lockKey, 1 ,timeout,TimeUnit.MILLISECONDS)) {
                if (logger.isDebugEnabled()) {
                    logger.debug("global locked,lockKey:{} 耗时:{} 毫秒",
                            lockKey,TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - nanoTime)+"");
                }
                return true;
            }
        } while ((System.nanoTime() - nanoTime) < TimeUnit.SECONDS.toNanos(timeout));
        throw new CommonRuntimeException(ErrorCode.ERR_COMMON_ERROR.getErrorCode(),"获取锁超时, 消耗时间: " + TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - nanoTime)+ "毫秒");
    }

    @Override
    public boolean lock(String lockKey) throws CommonRuntimeException {
        return lock(lockKey,MAX_LOCK_TIME);
    }

    @Override
    public void unlock(String lockKey) {
        redisService.delete(lockKey);
    }
}
