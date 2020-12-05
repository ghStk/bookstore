package com.ghstk.service.impl;

import com.ghstk.dao.BookDAO;
import com.ghstk.dao.impl.BookDAOImpl;
import com.ghstk.domain.Book;
import com.ghstk.domain.Page;
import com.ghstk.service.BookService;

import java.util.List;

/**
 * 2020/11/25 16:09
 */
public class BookServiceImpl implements BookService {
    private final BookDAO bookDAO = new BookDAOImpl();

    @Override
    public void addBook(Book book) {
        bookDAO.addBook(book);
    }

    @Override
    public void deleteBookById(int id) {
        bookDAO.deleteBookById(id);
    }

    @Override
    public void updateBook(Book book) {
        bookDAO.updateBook(book);
    }

    @Override
    public Book queryBookById(int id) {
        return bookDAO.queryBookById(id);
    }

    @Override
    public Page<Book> queryBooksByPage(int pageNo, int pageSize) {
        Page<Book> page = new Page<>();
        //设置总item个数
        int bookTotal = bookDAO.queryCountOfBook();
        page.setItemCount(bookTotal);
        //设置总页码
        int pageTotal = bookTotal / pageSize;
        if (pageTotal * pageSize < bookTotal) {
            pageTotal++;
        }
        page.setPageTotal(pageTotal);
        //设置当前页码
        page.setPageNo(pageNo);
        //设置当前页的item
        List<Book> books = bookDAO.queryBooksByPage(page.getPageNo(), pageSize);
        page.setItems(books);
        return page;
    }

    @Override
    public Page<Book> queryBooksByPrice(int pageNo, int pageSize, double minPrice, double maxPrice) {
        Page<Book> page = new Page<>();
        //设置总item个数
        int bookTotal = bookDAO.queryCountOfBookByPrice(minPrice, maxPrice);
        page.setItemCount(bookTotal);
        //设置总页码
        int pageTotal = bookTotal / pageSize;
        if (pageTotal * pageSize < bookTotal) {
            pageTotal++;
        }
        page.setPageTotal(pageTotal);
        //设置当前页码
        page.setPageNo(pageNo);
        //设置当前页的item
        List<Book> books = bookDAO.queryBooksByPrice(page.getPageNo(), pageSize, minPrice, maxPrice);
        page.setItems(books);
        return page;
    }

}
