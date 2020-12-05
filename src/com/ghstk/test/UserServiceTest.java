package com.ghstk.test;

import com.ghstk.domain.User;
import com.ghstk.service.impl.UserServiceImpl;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * 2020/11/21 13:01
 */
public class UserServiceTest {
public UserServiceImpl service = new UserServiceImpl();
    @Test
    public void registerUser() {
        System.out.println(service.registerUser(new User("heheda", "123456", "heheda@ghstk.com")));
    }

    @Test
    public void login() {
        System.out.println(service.login("user13", "123"));
    }

    @Test
    public void existUsername() {
        System.out.println(service.existUsername("sadfsdfdsf"));
        System.out.println(service.existUsername("admin"));
    }
}