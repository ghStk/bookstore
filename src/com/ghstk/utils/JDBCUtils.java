package com.ghstk.utils;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class JDBCUtils {
    private static DruidDataSource dataSource;
    private static final ThreadLocal<Connection> conns = new ThreadLocal<>();

    static {
        try {
            //读取数据库配置文件
            InputStream inputStream = JDBCUtils.class.getClassLoader().getResourceAsStream("jdbc.properties");
            Properties properties = new Properties();
            properties.load(inputStream);
            //创建数据库连接池
            dataSource = (DruidDataSource) DruidDataSourceFactory.createDataSource(properties);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取连接池中的连接
     *
     * @return 连接实例
     */
    public static Connection getConnection() {
        Connection conn = conns.get();
        //如果当前线程,未获取过连接
        if (conn == null) {
            try {
                conn = dataSource.getConnection();
                conn.setAutoCommit(false); //不自动提交事务
                conns.set(conn); //将连接保存到ThreadLocal
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return conn;
    }

    public static void commitAndClose() {
        Connection conn = conns.get();
        if (conn != null) {
            try {
                conn.commit(); //提交事务
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try {
                    conn.close(); //关闭连接
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        conns.remove();
    }

    public static void rollbackAndClose() {
        Connection conn = conns.get();
        if (conn != null) {
            try {
                conn.rollback(); //回滚事务
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try {
                    conn.close(); //关闭连接
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        conns.remove();
    }

}
