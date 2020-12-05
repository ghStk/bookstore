package com.ghstk.dao;

import com.ghstk.domain.User;

import java.sql.SQLException;
import java.util.List;

/**
 * 2020/11/20 22:14
 */
public interface UserDAO {

    /**
     * 保存用户到数据库
     *
     * @param user 用户实例
     * @return 影响的行数; -1表示操作失败
     */
    public int saveUser(User user);

//    public int deleteUserById(int id);

    /**
     * 查询用户,根据用户名
     *
     * @param username 用户名
     * @return 用户实例
     */
    public User queryUserByUsername(String username);

    /**
     * 查询用户,根据用户名和密码
     *
     * @param username 用户名
     * @param password 密码
     * @return 用户实例
     */
    public User queryUserByUsernameAndPassword(String username, String password);

    /**
     * 获取所有用户列表
     *
     * @param count 获取的用户数量
     * @return 用户实例组成的List
     */
    public List<User> getAllUser(int count);

}
