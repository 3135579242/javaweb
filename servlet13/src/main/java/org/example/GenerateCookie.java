package org.example;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/cookie/generate")
public class GenerateCookie extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //创建Cookie
        Cookie cookie = new Cookie("productid", "123456");
        Cookie cookie2 = new Cookie("username","楼兰");
        cookie.setMaxAge(60 * 60);
        cookie.setPath("/servlet13");
        cookie2.setPath(request.getContextPath());
        //将Cookie响应给浏览器
        response.addCookie(cookie);
        response.addCookie(cookie2);
    }
}
