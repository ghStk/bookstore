package com.ghstk.dao.impl;

import com.ghstk.dao.OrderItemDAO;
import com.ghstk.domain.OrderItem;

import java.util.List;

/**
 * 2020/12/1 15:18
 */
public class OrderItemDAOImpl extends BaseDAO<OrderItem> implements OrderItemDAO {
    @Override
    public void addOrderItem(OrderItem orderItem) {
        String sql = "insert into t_order_item(`id`,`name`,`count`,`price`,`total_price`,`order_id`) values(?,?,?,?,?,?)";
        update(sql, null, orderItem.getName(), orderItem.getCount(), orderItem.getPrice(), orderItem.getTotalPrice(), orderItem.getOrderId());
    }

    @Override
    public void deleteOrderItemById(int id) {
        String sql = "delete from t_order_item where id=?";
        update(sql, id);
    }

    @Override
    public List<OrderItem> queryOrderItemForListByOrderId(String orderId) {
        String sql = "select `id`,`name`,`count`,`price`,`total_price` `totalPrice`,`order_id` `orderId` from t_order_item where `order_id`=?";
        return queryForList(sql, orderId);
    }
}
