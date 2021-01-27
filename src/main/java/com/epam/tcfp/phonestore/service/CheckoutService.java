package com.epam.tcfp.phonestore.service;

import com.epam.tcfp.phonestore.constants.Constants;
import com.epam.tcfp.phonestore.entity.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;

public class CheckoutService implements Service{
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ParseException, SQLException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(Constants.USER);
        if(user != null){
            RequestDispatcher dispatcher = request.getRequestDispatcher("/order-confirmation-form.jsp");
            dispatcher.forward(request, response);
        }
        else{
            session.setAttribute(Constants.IS_LOGIN_FOR_CHECKOUT, "Yes");
            RequestDispatcher dispatcher = request.getRequestDispatcher("/phonestore/login/form");
            dispatcher.forward(request, response);
        }
    }
}
