package com.ghstk.web;

import com.ghstk.domain.User;
import com.ghstk.service.UserService;
import com.ghstk.service.impl.UserServiceImpl;
import com.ghstk.utils.WebUtils;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY;

/**
 * 2020/11/25 0:46
 */
public class UserServlet extends BaseServlet {
    private final UserService service = new UserServiceImpl();

    public void isExistUsername(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        Map<String, Boolean> result = new HashMap<>();
        if (service.existUsername(username)) {
            resp.getWriter().write("{\"isExist\":true,\"username\":\"" + username + "\"}");
        } else {
            resp.getWriter().write("{\"isExist\":false,\"username\":\"" + username + "\"}");
        }
    }

    public void register(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //验证码是否正确
        String code = (String) req.getSession().getAttribute(KAPTCHA_SESSION_KEY);
        req.getSession().removeAttribute(KAPTCHA_SESSION_KEY);
        if (code.equalsIgnoreCase(req.getParameter("code"))) {
            //用户名是否已存在
            if (service.existUsername(req.getParameter("username"))) {
                //用户名已存在!
                req.setAttribute("msg", "用户名已存在!");
                req.setAttribute("username", req.getParameter("username"));
                req.setAttribute("password", req.getParameter("password"));
                req.setAttribute("email", req.getParameter("email"));
                req.getRequestDispatcher("/pages/user/register.jsp").forward(req, resp);
            } else {
                //用户名不存在
                //表单数据是否合法 todo
                //写入数据库
                service.registerUser(WebUtils.populate(new User(), req.getParameterMap()));
                req.getRequestDispatcher("/pages/user/register_success.jsp").forward(req, resp);
            }
        } else {
            //验证码不正确
            req.setAttribute("msg", "验证码不正确!");
            req.setAttribute("username", req.getParameter("username"));
            req.setAttribute("password", req.getParameter("password"));
            req.setAttribute("email", req.getParameter("email"));
            req.getRequestDispatcher("/pages/user/register.jsp").forward(req, resp);
        }
    }

    public void login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取请求参数
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        //验证用户名与密码是否匹配
        if (service.login(username, password)) {
            //登录成功,将用户名&id保存到session域中
            req.getSession().setAttribute("loginUserName", username);
            req.getSession().setAttribute("loginUserId", service.getUserByUsername(username).getId());
            //转发到登录成功页面
            req.getRequestDispatcher("/pages/user/login_success.jsp").forward(req, resp);
        } else {
            //登录失败
            req.setAttribute("msg", "用户名或密码不正确!");
            req.setAttribute("username", req.getParameter("username"));
            req.setAttribute("password", req.getParameter("password"));
            req.getRequestDispatcher("/pages/user/login.jsp").forward(req, resp);
        }
    }

    public void logout(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //删除所有session
        req.getSession().invalidate();
        //重定向到首页
        resp.sendRedirect(req.getContextPath());
    }

    public void search(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String searchValue = req.getParameter("searchValue");
        User user = service.getUserByUsername(searchValue);
        req.setAttribute("user", user);
        req.setAttribute("searchValue", searchValue);
        req.getRequestDispatcher("/pages/user/search.jsp").forward(req, resp);
    }
}