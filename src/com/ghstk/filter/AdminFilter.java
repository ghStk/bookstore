package com.ghstk.filter;


import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * 2020/12/1 20:42
 */
public class AdminFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        Object username = req.getSession().getAttribute("loginUserName");
        if (username == null) {
            //如果没有登录,转发到登录页面
            req.getRequestDispatcher("/pages/user/login.jsp").forward(servletRequest, servletResponse);
            return;
        } else {
            //已经登录,继续访问资源
            filterChain.doFilter(servletRequest,servletResponse);
        }
    }

    @Override
    public void destroy() {

    }
}