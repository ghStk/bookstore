package com.ghstk.domain;

import java.math.BigDecimal;

/**
 * 2020/12/1 14:53
 */
public class OrderItem {
    private Integer id;
    private String name;
    private int count;
    private BigDecimal price;
    private BigDecimal totalPrice;
    private String orderId;

    public OrderItem() {
    }

    public OrderItem(Integer id, String name, int count, BigDecimal price, String orderId) {
        this.id = id;
        this.name = name;
        this.count = count;
        this.price = price;
        this.orderId = orderId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public void setCount(int count) {
        this.count = count;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getTotalPrice() {
        setTotalPrice();
        return totalPrice;
    }

    public void setTotalPrice() {
        totalPrice = price.multiply(BigDecimal.valueOf(count));
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    @Override
    public String toString() {
        return "OrderItem{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", count=" + count +
                ", price=" + price +
                ", totalPrice=" + totalPrice +
                ", orderId='" + orderId + '\'' +
                '}';
    }
}
