package com.ghstk.service;

import com.ghstk.domain.Cart;

/**
 * 2020/12/1 16:37
 */
public interface OrderService {
    public String createOrder(Cart cart, Integer userId);
}
