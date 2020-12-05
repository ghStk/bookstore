package com.ghstk.dao.impl;

import com.ghstk.utils.JDBCUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * 2020/11/20 21:50
 */
public abstract class BaseDAO<T> {
    private final Class<T> type;
    private final QueryRunner queryRunner = new QueryRunner();

    {
        Type genericSuperclass = this.getClass().getGenericSuperclass();
        ParameterizedType parameterizedType = (ParameterizedType) genericSuperclass;
        Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();
        type = (Class<T>) actualTypeArguments[0];
    }

    /**
     * 执行增删改操作
     *
     * @param sql  sql语句
     * @param args sql对应的参数
     * @return 影响的行数
     */
    public int update(String sql, Object... args) {
        Connection connection = JDBCUtils.getConnection();
        try {
            return queryRunner.update(connection, sql, args);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    /**
     * 执行查询操作,获得单个实例
     *
     * @param sql  sql语句
     * @param args sql对应参数
     * @return 单个实例
     */
    public T queryForOne(String sql, Object... args) {
        Connection connection = JDBCUtils.getConnection();
        try {
            return queryRunner.query(connection, sql, new BeanHandler<T>(type), args);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    /**
     * 执行查询操作,获得多个实例
     *
     * @param sql  sql语句
     * @param args sql对应参数
     * @return 多个实例组成的表
     */
    public List<T> queryForList(String sql, Object... args) {
        Connection connection = JDBCUtils.getConnection();
        try {
            return queryRunner.query(connection, sql, new BeanListHandler<T>(type), args);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    /**
     * 执行查询操作,获得单个值
     *
     * @param sql  sql语句
     * @param args sql对应参数
     * @return 单个值
     */
    public Object queryForSingleValue(String sql, Object... args) {
        Connection connection = JDBCUtils.getConnection();
        try {
            return queryRunner.query(connection, sql, new ScalarHandler(), args);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
