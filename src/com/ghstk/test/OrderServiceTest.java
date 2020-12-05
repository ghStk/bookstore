package com.ghstk.test;

import com.ghstk.domain.Cart;
import com.ghstk.domain.CartItem;
import com.ghstk.service.OrderService;
import com.ghstk.service.impl.OrderServiceImpl;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

/**
 * 2020/12/1 16:51
 */
public class OrderServiceTest {

    @Test
    public void createOrder() {
        OrderService service = new OrderServiceImpl();
        Cart cart = new Cart();
        cart.addItem(new CartItem(1, "java 从入门到精通", 1, new BigDecimal(1000), new BigDecimal(1000)));
        cart.addItem(new CartItem(1, "java 从入门到精通", 1, new BigDecimal(1000), new BigDecimal(1000)));
        cart.addItem(new CartItem(2, " 数据结构与算法", 1, new BigDecimal(100), new BigDecimal(100)));
        String orderId = service.createOrder(cart, 1);
        System.out.println(orderId);
    }
}