package com.ghstk.dao.impl;

import com.ghstk.dao.OrderDAO;
import com.ghstk.domain.Order;

import java.util.List;

/**
 * 2020/12/1 14:48
 */
public class OrderDAOImpl extends BaseDAO<Order> implements OrderDAO {
    @Override
    public void addOrder(Order order) {
        String sql = "insert into t_order(`order_id`,`create_time`,`price`,`status`,`user_id`) values(?,?,?,?,?) ";
        update(sql, order.getOrderId(), order.getCreateTime(), order.getPrice(), order.getStatus(), order.getUserId());
    }

    @Override
    public void updateOrder(Order order) {
        String sql = "update t_order set `price`=?,`status`=? where `order_id`=?";
        update(sql, order.getPrice(), order.getStatus(), order.getOrderId());
    }

    @Override
    public Order queryOrderByOrderId(String orderId) {
        String sql = "select `order_id` `orderId`,`create_time` `createTime`,`price`,`status`,`user_id` `userId` from t_order where `order_id`=?";
        return queryForOne(sql, orderId);
    }

    @Override
    public Order queryOrderByUserId(int userId) {
        String sql = "select `order_id` `orderId`,`create_time` `createTime`,`price`,`status`,`user_id` `userId` from t_order where `user_id`=?";
        return queryForOne(sql, userId);
    }

    @Override
    public List<Order> queryOrderForList() {
        String sql = "select `order_id` `orderId`,`create_time` `createTime`,`price`,`status`,`user_id` `userId` from t_order";
        return queryForList(sql);
    }
}
