package tech.xiying.template.init;

import org.assertj.core.util.Lists;
import tech.xiying.template.entity.Teacher;
import tech.xiying.template.entity.User;

import java.util.List;

/**
 * @ClassName EntityInit
 * @Description
 * @Author shanghao5
 * @Date 2021/3/20 10:46
 **/
public class EntityInit {

    /**
     * 默认初始化实例
     *
     * @return
     */
    public static List<User> getDefaultInstanceList(){
        Teacher teacher = Teacher.builder()
                .name("GaoMissTeacher")
                .phone("13751032645")
                .build();

        List<User> users = Lists.newArrayList();
        User xiaoMing = User.builder()
                .name("xiaoMing")
                .phone("13254687878")
                .teacher(teacher)
                .build();

        User xiaoWang = User.builder()
                .name("xiaoWang")
                .phone("13254687878")
                .teacher(teacher)
                .build();
        users.add(xiaoMing);
        users.add(xiaoWang);
        return users;
    }

}
