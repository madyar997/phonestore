package com.epam.tcfp.phonestore.service;

import com.epam.tcfp.phonestore.constants.Constants;
import com.epam.tcfp.phonestore.dao.UserDao;
import com.epam.tcfp.phonestore.entity.User;
import org.apache.commons.codec.digest.DigestUtils;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;

public class RegisterService implements Service {
    UserDao userDao = null;
    User user = null;

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ParseException, SQLException {
        userDao = new UserDao();
        String result = null;
        if (!userDao.isRegisteredCheck(request.getParameter(Constants.EMAIL))) {
            if (DigestUtils.md5Hex(request.getParameter(Constants.PASSWORD)).equals(DigestUtils.md5Hex(request.getParameter(Constants.CONFIRM_PASSWORD)))) {
                user = new User();
                user.setFirstName(request.getParameter(Constants.FIRST_NAME));
                user.setSecondName(request.getParameter(Constants.SECOND_NAME));
                user.setEmail(request.getParameter(Constants.EMAIL));
                user.setPhoneNumber(request.getParameter(Constants.PHONE_NUMBER));
                user.setAddress(request.getParameter(Constants.ADDRESS));
                user.setPassword(DigestUtils.md5Hex(request.getParameter(Constants.PASSWORD)));
                if (userDao.registerUser(user)) {
                    result = "registered successfully";
                }
            } else {
                result = "Passwords do not match";
            }
        } else {
            result = "User with this email is already registered";
        }
        request.setAttribute(Constants.RESULT, result);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/showRegisterResult.jsp");
        dispatcher.forward(request, response);
    }
}
