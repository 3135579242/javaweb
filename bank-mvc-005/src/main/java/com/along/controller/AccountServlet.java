package com.along.controller;

import com.along.exception.MoneyNotEnoughException;
import com.along.service.AccountService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

/*
    这个servlet 当作一个controller
 */
@WebServlet("/transfer")
public class AccountServlet extends HttpServlet {

    AccountService accountService = new AccountService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String fromActno = req.getParameter("fromActno");
        String toActno = req.getParameter("toActno");
        double money = Double.parseDouble(req.getParameter("money"));
            try {
            accountService.transfer(fromActno, toActno, money);
            resp.sendRedirect(req.getContextPath() + "/seccess.jsp");
        } catch (MoneyNotEnoughException e) {
            //余额不足
            resp.sendRedirect(req.getContextPath() + "/moneynotenough.jsp");
        } catch (Exception e) {
            //其他错误
            resp.sendRedirect(req.getContextPath() + "/error.jsp");
        }
    }



}
