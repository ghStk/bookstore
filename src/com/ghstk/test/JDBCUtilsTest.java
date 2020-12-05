package com.ghstk.test;

import com.ghstk.utils.JDBCUtils;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * 2020/11/20 17:08
 */
public class JDBCUtilsTest {
    @Test
    public void test1() throws SQLException {
        for (int i = 0; i < 50; i++) {

            Connection conn = JDBCUtils.getConnection();
            System.out.println(conn);
            JDBCUtils.commitAndClose();

        }
    }
}
