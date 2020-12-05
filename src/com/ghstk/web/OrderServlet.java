package com.ghstk.web;

import com.ghstk.domain.Cart;
import com.ghstk.service.OrderService;
import com.ghstk.service.impl.OrderServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 2020/12/1 19:38
 */
public class OrderServlet extends BaseServlet {
    private final OrderService orderService = new OrderServiceImpl();

    protected void createOrder(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = (String) req.getSession().getAttribute("loginUserName");
        //用户未登录,转发到登录界面
        if (username == null) {
            req.getRequestDispatcher("/pages/user/login.jsp").forward(req, resp);
            return;
        }
        Integer loginUserId = (Integer) req.getSession().getAttribute("loginUserId");
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        //创建订单,并把订单号返回
        String orderId = orderService.createOrder(cart, loginUserId);
        req.getSession().setAttribute("orderId", orderId);
        //清空购物车
        cart.clearCart();
        resp.sendRedirect(req.getContextPath() + "/pages/cart/checkout.jsp");
    }
}
