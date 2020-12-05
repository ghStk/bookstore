package com.ghstk.web;

import com.ghstk.domain.Book;
import com.ghstk.domain.Cart;
import com.ghstk.domain.CartItem;
import com.ghstk.service.BookService;
import com.ghstk.service.impl.BookServiceImpl;
import com.ghstk.utils.WebUtils;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * 2020/11/30 21:15
 */
public class CartServlet extends BaseServlet {
    private final BookService bookService = new BookServiceImpl();

/*    protected void addItem(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        baseAddItem(req);
        //重定向,到原商品页面
        resp.sendRedirect(req.getHeader("Referer"));
    }*/

    private Map<String, Object> baseAddItem(HttpServletRequest req) {
        int count = 1; //默认加入1份到购物车
        int id = WebUtils.parseToInt(req.getParameter("id"), -1); //获取item的id
        Book book = bookService.queryBookById(id); //将查询到的book封装到cartItem中
        Cart cart = (Cart) req.getSession().getAttribute("cart"); //获取原cart
        //如果之前购物车为空
        if (cart == null) {
            cart = new Cart();
            req.getSession().setAttribute("cart", cart);
        }
        //将新建的cartItem加入cart
        CartItem cartItem = new CartItem(book.getId(), book.getName(), count, book.getPrice(), book.getPrice().multiply(BigDecimal.valueOf(count)));
        cart.addItem(cartItem);
        //保存最后一次添加的商品名称到Session
        req.getSession().setAttribute("latestItemName", book.getName());
        //返回值给ajax请求
        Map<String, Object> result = new HashMap<>();
        result.put("status", 0);
        result.put("cartTotalCount", cart.getTotalCount());
        result.put("latestItemName", book.getName());
        return result;
    }

    protected void ajaxAddItem(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Map<String, Object> result = baseAddItem(req);
        resp.getWriter().write(new Gson().toJson(result));
    }

    protected void deleteItem(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        int id = WebUtils.parseToInt(req.getParameter("id"), -1);
        cart.removeItem(id);
        //重定向,到原页面
        resp.sendRedirect(req.getHeader("Referer"));
    }

/*    protected void updateItem(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        int id = WebUtils.parseToInt(req.getParameter("id"), -1);
        int count = WebUtils.parseToInt(req.getParameter("count"), 1);
        if (cart != null) {
            //数量为0,则删除
            if (count == 0) {
                deleteItem(req, resp);
                return;
            }
            count = Math.max(count, 1);
            cart.updateCount(id, count);
        }
        //重定向,到原页面
        resp.sendRedirect(req.getHeader("Referer"));
    }*/

    protected void ajaxUpdateItem(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        //没有购物车对象,返回status(-1)
        if (cart == null) {
            resp.getWriter().write("{\"status\":-1}");
            return;
        }
        int id = WebUtils.parseToInt(req.getParameter("id"), -1);
        int count = WebUtils.parseToInt(req.getParameter("count"), 1);
        //商品数量为0,则删除
        if (count == 0) {
            deleteItem(req, resp);
            return;
        }
        count = Math.max(count, 1);
        cart.updateCount(id, count);
        //回传json
        Map<String, Object> result = new HashMap<>();
        result.put("status", 0);
        result.put("itemTotalPrice", cart.getItems().get(id).getTotalPrice());
        result.put("cartTotalCount", cart.getTotalCount());
        result.put("cartTotalPrice", cart.getTotalPrice());
        resp.getWriter().write(new Gson().toJson(result));
    }

    protected void clearCart(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        cart.clearCart();
        //重定向,到原页面
        resp.sendRedirect(req.getHeader("Referer"));
    }

}
