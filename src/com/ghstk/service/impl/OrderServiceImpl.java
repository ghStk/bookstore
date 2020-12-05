package com.ghstk.service.impl;

import com.ghstk.dao.OrderDAO;
import com.ghstk.dao.OrderItemDAO;
import com.ghstk.dao.impl.OrderDAOImpl;
import com.ghstk.dao.impl.OrderItemDAOImpl;
import com.ghstk.domain.*;
import com.ghstk.service.BookService;
import com.ghstk.service.OrderService;

import java.util.Date;
import java.util.Map;

/**
 * 2020/12/1 16:38
 */
public class OrderServiceImpl implements OrderService {
    private final OrderDAO orderDAO = new OrderDAOImpl();
    private final OrderItemDAO orderItemDAO = new OrderItemDAOImpl();
    private final BookService bookService = new BookServiceImpl();

    @Override
    public String createOrder(Cart cart, Integer userId) {
        //保证唯一性,orderId
        String orderId = System.currentTimeMillis() + "" + userId;
        //新建order
        orderDAO.addOrder(new Order(orderId, new Date(), cart.getTotalPrice(), userId, 0));
        //遍历购物车中的items
        cart.getItems().forEach((key, value) -> {
            //将cart中的cartItem塞入orderItems
            orderItemDAO.addOrderItem(new OrderItem(null, value.getName(), value.getCount(), value.getPrice(), orderId));
            //更新库存和销售量
            Book book = bookService.queryBookById(key);
            book.setSales(book.getSales() + value.getCount());
            book.setStock(book.getStock() - value.getCount());
            bookService.updateBook(book);
        });
        return orderId;
    }
}
