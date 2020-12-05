package com.ghstk.dao;

import com.ghstk.domain.Book;

import java.util.List;

/**
 * 2020/11/25 15:08
 */
public interface BookDAO {
    public int addBook(Book book);

    public int deleteBookById(int id);

    public int updateBook(Book book);

    public int queryCountOfBook();

    public int queryCountOfBookByPrice(double minPrice, double maxPrice);

    public Book queryBookById(int id);

    public Book queryBookByName(String name);

    public List<Book> queryBooksByPage(int beginPage, int pageSize);

    public List<Book> queryBooksByPrice(int beginPage, int pageSize, double minPrice, double maxPrice);

    public List<Book> queryAllBooks(int count);
}
