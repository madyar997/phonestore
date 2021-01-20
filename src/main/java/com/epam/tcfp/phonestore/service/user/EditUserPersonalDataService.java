package com.epam.tcfp.phonestore.service.user;

import com.epam.tcfp.phonestore.constants.Constants;
import com.epam.tcfp.phonestore.dao.UserDao;
import com.epam.tcfp.phonestore.entity.User;
import com.epam.tcfp.phonestore.service.Service;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;

public class EditUserPersonalDataService implements Service {
    UserDao userDao = new UserDao();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ParseException, SQLException {
        HttpSession session = request.getSession();
        User updateduser = new User();
        User currentUser = (User)session.getAttribute(Constants.USER);
        updateduser.setUserId(Integer.parseInt(request.getParameter(Constants.ID)));
        updateduser.setFirstName(request.getParameter(Constants.FIRST_NAME));
        updateduser.setSecondName(request.getParameter(Constants.SECOND_NAME));
        updateduser.setEmail(request.getParameter(Constants.EMAIL));
        updateduser.setPhoneNumber(request.getParameter(Constants.PHONE_NUMBER));
        updateduser.setRole(currentUser.getRole());
        updateduser.setPassword(currentUser.getPassword());
        updateduser.setAddress(request.getParameter(Constants.ADDRESS));
        userDao.updateUser(updateduser);
        session.setAttribute(Constants.USER, updateduser);
        request.setAttribute(Constants.USER, updateduser);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/user-home.jsp");
        dispatcher.forward(request, response);
    }
}
