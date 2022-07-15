package tech.xiying.template.h2test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import tech.xiying.template.dao.UserDao;
import tech.xiying.template.entity.User;
import tech.xiying.template.init.EntityInit;

import java.util.List;

/**
 * @ClassName UserTest
 * @Description
 * @Author shanghao5
 * @Date 2021/3/20 10:52
 **/
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserTest {

    @Autowired
    private UserDao userDao;

    @Test
    public void testInsert(){
        List<User> userList = EntityInit.getDefaultInstanceList();
        userDao.saveAll(userList);
    }

    @Test
    public void testSelect(){
        Iterable<User> all = userDao.findAll();
        System.out.println("============");
    }
}
