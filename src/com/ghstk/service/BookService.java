package com.ghstk.service;

import com.ghstk.domain.Book;
import com.ghstk.domain.Page;

import java.util.List;

/**
 * 2020/11/25 16:06
 */
public interface BookService {
    public void addBook(Book book);

    public void deleteBookById(int id);

    public void updateBook(Book book);

    public Book queryBookById(int id);

    public Page<Book> queryBooksByPage(int pageNo, int pageSize);

    public Page<Book> queryBooksByPrice(int pageNo, int pageSize, double minPrice, double maxPrice);
}
