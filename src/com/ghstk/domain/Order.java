package com.ghstk.domain;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 2020/12/1 11:46
 */
public class Order {
    private String orderId;
    private Date createTime;
    private BigDecimal price;
    private int userId;
    private int status; //0-未发货 1-已发货 2-已签收

    public Order() {
    }

    public Order(String orderId, Date createTime, BigDecimal price, int userId, int status) {
        this.orderId = orderId;
        this.createTime = createTime;
        this.price = price;
        this.userId = userId;
        this.status = status;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId=" + orderId +
                ", createTime=" + createTime +
                ", price=" + price +
                ", userId=" + userId +
                ", status=" + status +
                '}';
    }
}
