package com.ghstk.filter;

import com.ghstk.utils.JDBCUtils;

import javax.servlet.*;
import java.io.IOException;

/**
 * 2020/12/2 14:17
 */
public class TransactFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) {
        try {
            filterChain.doFilter(servletRequest, servletResponse);
            JDBCUtils.commitAndClose(); //没有异常,则提交并关闭
        } catch (Exception e) {
            JDBCUtils.rollbackAndClose(); //出现异常,则回滚事务
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public void destroy() {

    }
}
