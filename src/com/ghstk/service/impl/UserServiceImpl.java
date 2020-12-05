package com.ghstk.service.impl;

import com.ghstk.dao.impl.UserDAOImpl;
import com.ghstk.domain.User;
import com.ghstk.service.UserService;

import java.util.List;

/**
 * 2020/11/21 12:53
 */
public class UserServiceImpl implements UserService {
    private final UserDAOImpl userDAO = new UserDAOImpl();

    @Override
    public boolean registerUser(User user) {
        return userDAO.saveUser(user) > 0;
    }

    @Override
    public boolean login(String username, String password) {
        return userDAO.queryUserByUsernameAndPassword(username, password) != null;
    }

    @Override
    public boolean existUsername(String username) {
        return userDAO.queryUserByUsername(username) != null;
    }

    @Override
    public List<User> getAllUser(int count) {
        return userDAO.getAllUser(count);
    }

    @Override
    public User getUserByUsername(String username) {
        return userDAO.queryUserByUsername(username);
    }
}
