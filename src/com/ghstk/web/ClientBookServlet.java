package com.ghstk.web;

import com.ghstk.domain.Book;
import com.ghstk.domain.Page;
import com.ghstk.service.BookService;
import com.ghstk.service.impl.BookServiceImpl;
import com.ghstk.utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 2020/11/26 23:28
 */
public class ClientBookServlet extends BaseServlet {
    private final BookService service = new BookServiceImpl();

    public void page(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int pageNo = WebUtils.parseToInt(req.getParameter("pageNo"), 1);
        int pageSize = WebUtils.parseToInt(req.getParameter("pageSize"), Page.PAGE_SIZE);
        Page<Book> page = service.queryBooksByPage(pageNo, pageSize);
        page.setUrl("client/bookServlet?action=page");
        req.setAttribute("page", page);
        req.getRequestDispatcher("/pages/client/index.jsp").forward(req, resp);
    }

    public void pageByPrice(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int minPrice = WebUtils.parseToInt(req.getParameter("min"), 0);
        int maxPrice = WebUtils.parseToInt(req.getParameter("max"), 0);
        int pageNo = WebUtils.parseToInt(req.getParameter("pageNo"), 1);
        int pageSize = WebUtils.parseToInt(req.getParameter("pageSize"), Page.PAGE_SIZE);
        Page<Book> page = service.queryBooksByPrice(pageNo, pageSize, minPrice, maxPrice);
        page.setUrl("client/bookServlet?action=pageByPrice&min=" + minPrice + "&max=" + maxPrice);
        req.setAttribute("page", page);
        req.getRequestDispatcher("/pages/client/index.jsp").forward(req, resp);
    }

}
