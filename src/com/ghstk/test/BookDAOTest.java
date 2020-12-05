package com.ghstk.test;

import com.ghstk.dao.BookDAO;
import com.ghstk.dao.impl.BookDAOImpl;
import com.ghstk.domain.Book;
import org.junit.Test;

import java.math.BigDecimal;

/**
 * 2020/11/25 15:52
 */
public class BookDAOTest {
    private BookDAO dao = new BookDAOImpl();

    @Test
    public void saveBook() {
        Book book = new Book(null, "新书", "赵总", new BigDecimal("32.8"), 60, 50, null);
        dao.addBook(book);
    }

    @Test
    public void deleteBookById() {
        Book book = new Book(null, "新书", "赵总", new BigDecimal("32.8"), 60, 50, null);
        dao.deleteBookById(21);
    }

    @Test
    public void updateBook() {
        Book book = new Book(22, "新书2", "赵总2", new BigDecimal("32.8"), 60, 50, null);
        dao.updateBook(book);
    }

    @Test
    public void queryBookById() {
        System.out.println(dao.queryBookById(22));
    }

    @Test
    public void queryBookByName() {
        System.out.println(dao.queryBookByName("新书2"));
    }

    @Test
    public void getAllBook() {
        dao.queryAllBooks(500).forEach(System.out::println);
    }

    @Test
    public void queryCountOfBook() {
        System.out.println(dao.queryCountOfBook());
    }

}