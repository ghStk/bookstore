package com.ghstk.dao.impl;

import com.ghstk.dao.BookDAO;
import com.ghstk.domain.Book;

import java.util.List;

/**
 * 2020/11/25 15:16
 */
public class BookDAOImpl extends BaseDAO<Book> implements BookDAO {
    @Override
    public int addBook(Book book) {
        String sql = "insert into t_book(`name`,`author`,`price`,`sales`,`stock`,`img_path`) values(?,?,?,?,?,?)";
        return update(sql, book.getName(), book.getAuthor(),
                book.getPrice(), book.getSales(), book.getStock(), book.getImgPath());
    }

    @Override
    public int deleteBookById(int id) {
        String sql = "delete from t_book where id = ?";
        return update(sql, id);
    }

    @Override
    public int updateBook(Book book) {
        String sql = "update t_book set `name`=?,`author`=?,`price`=?,`sales`=?,`stock`=?,`img_path`=? where id = ?";
        return update(sql, book.getName(), book.getAuthor(),
                book.getPrice(), book.getSales(), book.getStock(), book.getImgPath(), book.getId());

    }

    @Override
    public int queryCountOfBook() {
        String sql = "select count(*) from t_book";
        Number number = (Number) queryForSingleValue(sql);
        return number.intValue();
    }

    @Override
    public int queryCountOfBookByPrice(double minPrice, double maxPrice) {
        String sql = "select count(*) from t_book where price between ? and ?";
        Number number = (Number) queryForSingleValue(sql, minPrice, maxPrice);
        return number.intValue();
    }

    @Override
    public Book queryBookById(int id) {
        String sql = "select `id`,`name`,`author`,`price`,`sales`,`stock`,`img_path` imgPath from t_book where id = ?";
        return queryForOne(sql, id);
    }

    @Override
    public Book queryBookByName(String name) {
        String sql = "select `id`,`name`,`author`,`price`,`sales`,`stock`,`img_path` imgPath from t_book where name = ?";
        return queryForOne(sql, name);
    }

    @Override
    public List<Book> queryBooksByPage(int beginPage, int pageSize) {
        String sql = "select `id`,`name`,`author`,`price`,`sales`,`stock`,`img_path` imgPath from t_book limit ?,?";
        return queryForList(sql, pageSize * (beginPage - 1), pageSize);
    }

    @Override
    public List<Book> queryBooksByPrice(int beginPage, int pageSize, double minPrice, double maxPrice) {
        String sql = "select `id`,`name`,`author`,`price`,`sales`,`stock`,`img_path` imgPath from t_book " +
                "where price between ? and ? order by `price` limit ?,?";
        return queryForList(sql, minPrice, maxPrice, pageSize * (beginPage - 1), pageSize);
    }

    @Override
    public List<Book> queryAllBooks(int count) {
        String sql = "select `id`,`name`,`author`,`price`,`sales`,`stock`,`img_path` imgPath from t_book limit ?";
        return queryForList(sql, count);
    }
}
