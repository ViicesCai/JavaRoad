package edu.fdzc.dao;

import edu.fdzc.bean.User;
import org.springframework.stereotype.Repository;

/**
 * @author CAI
 * @time 2021/1/13
 */
@Repository
public class UserDao extends BaseDao<User> {

    @Override
    public void save() {
        System.out.println("UserDao：保存用户");
    }
}
