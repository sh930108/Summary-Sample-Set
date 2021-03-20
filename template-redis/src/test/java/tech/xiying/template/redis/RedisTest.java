package tech.xiying.template.redis;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import tech.xiying.template.service.cach.ICacheService;

import java.util.concurrent.TimeUnit;

/**
 * @ClassName RedisTest
 * @Description
 * @Author shanghao5
 * @Date 2021/3/9 10:13
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisTest {

    @Autowired
    private ICacheService cacheService;

    @Test
    public void test1(){
        cacheService.set("tech.xiying.hello","hello============1111");
        String string = cacheService.getString("tech.xiying.hello");
        System.out.println("===============");
        System.out.println(string);
        System.out.println("===============");
    }

    @Test
    public void testIncr(){
        Long incr = cacheService.incr("tech.xiying.error_time");
        System.out.println("=============");
        System.out.println(incr);
        System.out.println("=============");
    }


    @Test
    public void testGet(){
        Long incr = cacheService.getLong("tech.xiying.error_time");
        System.out.println("=============");
        System.out.println(incr);
        System.out.println("=============");
    }

    @Test
    public void testExpire(){
        cacheService.delete("tech.xiying.test_expire_time");
        cacheService.set("tech.xiying.test_expire_time","hello", 100000L, TimeUnit.MILLISECONDS);
        Long expire = cacheService.getExpire("tech.xiying.test_expire_time");
        String string = cacheService.getString("tech.xiying.test_expire_time");
        System.out.println("=============");
        System.out.println("value:"+string+";expire time:"+expire);
        System.out.println("=============");

    }

    @Test
    public void testDel(){
        cacheService.delete("tech.xiying.error_time");
    }

    @Test
    public void testGlobalLock(){
        cacheService.set("global_lock:123412132",1);
        Boolean aBoolean = cacheService.setIfAbsent("global_lock:123412132", 1);
        Boolean absent = cacheService.setIfAbsent("global_lock:123412133", 1);
        System.out.println("=========================");
        System.out.println("aBoolean:"+aBoolean);
        System.out.println("absent:"+absent);
        System.out.println("=========================");
    }
}
