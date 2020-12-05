package com.ghstk.domain;

import java.math.BigDecimal;

/**
 * 2020/11/30 16:16
 */
public class CartItem {
    private int id;
    private String name;
    private int count;
    private BigDecimal price;
    private BigDecimal totalPrice;

    /***
     * 根据最新数量刷新价格
     */
    public void refreshTotalPrice() {
        this.totalPrice = price.multiply(BigDecimal.valueOf(count));
    }

    public CartItem() {
    }

    public CartItem(int id, String name, int count, BigDecimal price, BigDecimal totalPrice) {
        this.id = id;
        this.name = name;
        this.count = count;
        this.price = price;
        this.totalPrice = totalPrice;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCount() {
        return count;
    }

    //设置新数量后,需要刷新总价
    public void setCount(int count) {
        this.count = count;
        refreshTotalPrice();
    }

    public BigDecimal getPrice() {
        return price;
    }

    //设置新价格后,需要刷新总价
    public void setPrice(BigDecimal price) {
        this.price = price;
        refreshTotalPrice();
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }
}
