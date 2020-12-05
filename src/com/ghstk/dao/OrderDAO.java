package com.ghstk.dao;

import com.ghstk.domain.Order;

import java.util.List;

/**
 * 2020/12/1 11:50
 */
public interface OrderDAO {

    public void addOrder(Order order);

    public void updateOrder(Order order);

    public Order queryOrderByOrderId(String orderId);

    public Order queryOrderByUserId(int userId);

    public List<Order> queryOrderForList();

}
