package com.ghstk.web;

import com.ghstk.domain.User;
import com.google.gson.Gson;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * 2020/11/24 13:06
 */
public class TestServlet extends BaseServlet {
    protected void ajaxRequest(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String value1 = req.getParameter("key1");
        System.out.print(value1);
        System.out.println(" "+System.currentTimeMillis());
        User user1 = new User("u1", "p1", "u1@qq.com");
        resp.getWriter().write(new Gson().toJson(user1));
    }
}
