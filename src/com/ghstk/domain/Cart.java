package com.ghstk.domain;

import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 2020/11/30 16:09
 */
public class Cart {
    private int totalCount;
    private BigDecimal totalPrice;
    private Map<Integer, CartItem> items = new LinkedHashMap<>();

    public Cart() {
        this.totalCount = 0;
        this.totalPrice = BigDecimal.valueOf(0);
    }

    public void addItem(CartItem newItem) {
        CartItem cartItem = items.get(newItem.getId());
        if (cartItem == null) {
            //如果购物车中,没有此商品
            items.put(newItem.getId(), newItem);
        } else {
            //如果购物车中,已有此商品
            //修改数量,方法会自动刷新价格
            cartItem.setCount(cartItem.getCount() + newItem.getCount());
        }
    }

    public void removeItem(int itemId) {
        items.remove(itemId);
    }

    public void clearCart() {
        items.clear();
//        totalCount = 0;
//        totalPrice = BigDecimal.valueOf(0);
    }

    public void updateCount(int itemId, int count) {
        CartItem cartItem = items.get(itemId);
        if (cartItem != null) {
            //修改数量,方法会自动刷新价格
            cartItem.setCount(count);
        }
    }

    public int getTotalCount() {
        totalCount = 0;
        items.forEach((key, value) -> totalCount += value.getCount());
        return totalCount;
    }

    public BigDecimal getTotalPrice() {
        totalPrice = BigDecimal.valueOf(0);
        items.forEach((key, value) -> totalPrice = totalPrice.add(value.getTotalPrice()));
        return totalPrice;
    }

    public Map<Integer, CartItem> getItems() {
        return items;
    }

    public void setItems(Map<Integer, CartItem> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "Cart{" +
                "totalCount=" + getTotalCount() +
                ", totalPrice=" + getTotalPrice() +
                ", items=" + items +
                '}';
    }
}
