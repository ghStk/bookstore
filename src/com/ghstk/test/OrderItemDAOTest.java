package com.ghstk.test;

import com.ghstk.dao.OrderItemDAO;
import com.ghstk.dao.impl.OrderItemDAOImpl;
import com.ghstk.domain.OrderItem;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

/**
 * 2020/12/1 15:54
 */
public class OrderItemDAOTest {
    private OrderItemDAO dao = new OrderItemDAOImpl();
    private OrderItem item = new OrderItem(null, "java从入门到精通", 1, new BigDecimal(100), "1234567890");
    private OrderItem item2 = new OrderItem(null, "java从精通到入门", 1, new BigDecimal(60), "1234567890");


    @Test
    public void addOrderItem() {
//        dao.addOrderItem(item);
        dao.addOrderItem(item2);
    }

    @Test
    public void deleteOrderItemById() {
        dao.deleteOrderItemById(2);
    }

    @Test
    public void queryOrderItemForListByOrderId() {
        dao.queryOrderItemForListByOrderId("1234567890").forEach(System.out::println);
    }
}