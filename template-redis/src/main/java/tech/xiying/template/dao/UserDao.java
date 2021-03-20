package tech.xiying.template.dao;

import org.springframework.data.repository.PagingAndSortingRepository;
import tech.xiying.template.entity.User;

/**
 * @author shanghao5
 */
public interface UserDao extends PagingAndSortingRepository<User,Long> {


}
