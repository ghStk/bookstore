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
 * 2020/11/25 16:13
 */
public class BookServlet extends BaseServlet {
    private final BookService service = new BookServiceImpl();

    public void add(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Book book = WebUtils.populate(new Book(), req.getParameterMap());
        service.addBook(book);
        String contextPath = req.getContextPath();
        resp.sendRedirect(contextPath + "/manager/bookServlet?action=page&pageNo=" + req.getParameter("pageNo"));
    }

    public void delete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        service.deleteBookById(WebUtils.parseToInt(req.getParameter("id"), -1));
        String contextPath = req.getContextPath();
        resp.sendRedirect(contextPath + "/manager/bookServlet?action=page&pageNo=" + req.getParameter("pageNo"));
    }

    public void update(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Book book = WebUtils.populate(new Book(), req.getParameterMap());
        service.updateBook(book);
        String contextPath = req.getContextPath();
        resp.sendRedirect(contextPath + "/manager/bookServlet?action=page&pageNo=" + req.getParameter("pageNo"));
    }

    public void getBook(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        Book book = service.queryBookById(WebUtils.parseToInt(req.getParameter("id"), -1));
        req.setAttribute("book", book);
        req.getRequestDispatcher("/pages/manager/book_edit.jsp?action=update").forward(req, resp);
    }

    public void page(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int pageNo = WebUtils.parseToInt(req.getParameter("pageNo"), 1);
        int pageSize = WebUtils.parseToInt(req.getParameter("pageSize"), Page.PAGE_SIZE);
        Page<Book> page = service.queryBooksByPage(pageNo, pageSize);
        page.setUrl("manager/bookServlet?action=page");
        req.setAttribute("page", page);
        req.getRequestDispatcher("/pages/manager/book_manager.jsp").forward(req, resp);
    }

}
