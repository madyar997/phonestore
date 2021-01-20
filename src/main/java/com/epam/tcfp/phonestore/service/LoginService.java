package com.epam.tcfp.phonestore.service;

import com.epam.tcfp.phonestore.constants.Constants;
import com.epam.tcfp.phonestore.dao.UserDao;
import com.epam.tcfp.phonestore.entity.User;
import org.apache.commons.codec.digest.DigestUtils;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;

import static com.epam.tcfp.phonestore.constants.Constants.IS_LOGIN_FOR_CHECKOUT;

public class LoginService implements Service{
    UserDao userDao = null;
    HttpSession session = null;
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ParseException, SQLException {
        userDao = new UserDao();
        session = request.getSession();
        String email = request.getParameter(Constants.EMAIL);
        String password = DigestUtils.md5Hex(request.getParameter(Constants.PASSWORD));
        User user = userDao.getUserByEmail(email);
        if(user != null && user.getEmail().equals(email) && user.getPassword().equals(password)){
            session.setAttribute(Constants.USER, user);
        }
        if(session.getAttribute(IS_LOGIN_FOR_CHECKOUT) != null && session.getAttribute(IS_LOGIN_FOR_CHECKOUT).equals("Yes")){
            session.removeAttribute(IS_LOGIN_FOR_CHECKOUT);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/phonestore/checkout");
            dispatcher.forward(request, response);
        }
        else{
            RequestDispatcher dispatcher = request.getRequestDispatcher("/phonestore");
            dispatcher.forward(request, response);
        }
    }
}
