package com.ghstk.test;

import com.ghstk.dao.impl.UserDAOImpl;
import com.ghstk.domain.User;
import org.junit.Test;

/**
 * 2020/11/20 22:50
 */
public class UserDAOTest {
    public UserDAOImpl userDAO = new UserDAOImpl();


    @Test
    public void queryUserByUsername() {
        User admin = userDAO.queryUserByUsername("大b哥");
        System.out.println(admin);
    }

    @Test
    public void queryUserByUsernameAndPassword() {
        User admin = userDAO.queryUserByUsernameAndPassword("admin","admin");
        System.out.println(admin);
    }

    @Test
    public void saveUser() {
        int i = userDAO.saveUser(new User("火鸡", "123456", "23333@ghstk.com"));
        System.out.println(i);
    }
}