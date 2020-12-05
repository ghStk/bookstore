package com.ghstk.service;

import com.ghstk.domain.User;

import java.util.List;

/**
 * 2020/11/21 12:32
 */
public interface UserService {
    /**
     * 注册用户
     * @param user
     * @return
     */
    public boolean registerUser(User user);

    /**
     * 用户登录
     * @param username 用户名
     * @param password 密码
     * @return         true:登录成功; false:登录失败
     */
    public boolean login(String username, String password);

    /**
     * 检测用户名是否存在
     * @param username 用户名
     * @return         true:存在; false:不存在
     */
    public boolean existUsername(String username);

    /**
     * 获取所有用户列表
     * @param count 获取的数量
     * @return      用户List
     */
    public List<User> getAllUser(int count);

    public User getUserByUsername(String username);
}
