package com.ghstk.dao.impl;

import com.ghstk.dao.UserDAO;
import com.ghstk.domain.User;

import java.util.List;

/**
 * 2020/11/20 22:19
 */
public class UserDAOImpl extends BaseDAO<User> implements UserDAO {

    @Override
    public User queryUserByUsername(String username) {
        String sql = "select `id`,`username`,`password`,`email` from t_user where `username` = ?";
        return queryForOne(sql, username);
    }

    @Override
    public User queryUserByUsernameAndPassword(String username, String password) {
        String sql = "select `id`,`username`,`password`,`email` from t_user where `username` = ? and `password` = ?";
        return queryForOne(sql, username, password);
    }

    @Override
    public int saveUser(User user) {
        String sql = "insert into t_user(`username`,`password`,`email`) values( ?, ?, ?)";
        return update(sql, user.getUsername(), user.getPassword(), user.getEmail());
    }

    @Override
    public List<User> getAllUser(int count) {
        String sql = "select `username`,`password`,`email` from t_user limit ?";
        return queryForList(sql, count);
    }
}
