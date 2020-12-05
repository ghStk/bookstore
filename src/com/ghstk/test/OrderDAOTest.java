package com.ghstk.test;

import com.ghstk.dao.OrderDAO;
import com.ghstk.dao.impl.OrderDAOImpl;
import com.ghstk.domain.Order;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Date;

import static org.junit.Assert.*;

/**
 * 2020/12/1 15:36
 */
public class OrderDAOTest {
    private Order order = new Order("1234567891", new Date(), new BigDecimal(100), 1, 0);
    private Order order2 = new Order("1234567891", new Date(), new BigDecimal(100), 1, 1);
    OrderDAO dao = new OrderDAOImpl();

    @Test
    public void addOrder() {
        for (int i = 0; i < 10; i++) {
            String s = "123456789" + i;
            order.setOrderId(s);
            dao.addOrder(order);
        }

    }

    @Test
    public void updateOrder() {
        dao.updateOrder(order2);
    }

    @Test
    public void queryOrderByOrderId() {
        System.out.println(dao.queryOrderByOrderId("1234567891"));
    }

    @Test
    public void queryOrderByUserId() {
        System.out.println(dao.queryOrderByUserId(1));
    }

    @Test
    public void queryOrderForList() {
        dao.queryOrderForList().forEach(System.out::println);
    }
}