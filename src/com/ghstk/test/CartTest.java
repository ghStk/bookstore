package com.ghstk.test;

import com.ghstk.domain.Cart;
import com.ghstk.domain.CartItem;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.BiConsumer;

import static org.junit.Assert.*;

/**
 * 2020/11/30 16:46
 */
public class CartTest {
    Cart cart = new Cart();
    CartItem item = new CartItem(1, "name", 1, new BigDecimal(100), new BigDecimal(100));
    CartItem item2 = new CartItem(2, "name2", 1, new BigDecimal(50), new BigDecimal(50));


    {
        cart.addItem(item);
        cart.addItem(item);
        cart.addItem(item2);
    }


    @Test
    public void getTotalCount() {
        System.out.println(cart.getTotalCount());
    }

    @Test
    public void addItem() {
    }

    @Test
    public void removeItem() {
        cart.removeItem(2);
        System.out.println(cart);
    }

    @Test
    public void clearCart() {
        cart.clearCart();
        if (cart.getItems()==null) {
            System.out.println("cart.items=null");
        } else {
            System.out.println(cart.getItems());
        }
        System.out.println(cart);
    }

    @Test
    public void updateCount() {
        cart.updateCount(1,10);
        System.out.println(cart);
    }

    @Test
    public void getTotalPrice() {
        System.out.println(cart.getTotalPrice());

    }

    @Test
    public void getItems() {
    }

    @Test
    public void setItems() {
    }

}