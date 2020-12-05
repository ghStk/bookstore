package com.ghstk.dao;

import com.ghstk.domain.OrderItem;

import java.util.List;

/**
 * 2020/12/1 15:18
 */
public interface OrderItemDAO {
    public void addOrderItem(OrderItem orderItem);

    public void deleteOrderItemById(int id);

    public List<OrderItem> queryOrderItemForListByOrderId(String orderId);

}
