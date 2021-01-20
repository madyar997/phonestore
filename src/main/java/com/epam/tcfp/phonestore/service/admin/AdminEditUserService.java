package com.epam.tcfp.phonestore.service.admin;

import com.epam.tcfp.phonestore.constants.Constants;
import com.epam.tcfp.phonestore.dao.UserDao;
import com.epam.tcfp.phonestore.entity.User;
import com.epam.tcfp.phonestore.service.Service;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

public class AdminEditUserService implements Service {
    UserDao userDao = new UserDao();
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ParseException, SQLException {
        User user = userDao.selectUser(Integer.parseInt(request.getParameter(Constants.ID)));
        User updatedUser = new User();
        updatedUser.setUserId(Integer.parseInt(request.getParameter(Constants.ID)));
        updatedUser.setFirstName(request.getParameter(Constants.FIRST_NAME));
        updatedUser.setSecondName(request.getParameter(Constants.SECOND_NAME));
        updatedUser.setEmail(request.getParameter(Constants.EMAIL));
        updatedUser.setPhoneNumber(request.getParameter(Constants.PHONE_NUMBER));
        updatedUser.setRole(Integer.parseInt(request.getParameter(Constants.ROLE)));
        updatedUser.setPassword(user.getPassword());
        updatedUser.setAddress(request.getParameter(Constants.ADDRESS));
        userDao.updateUser(updatedUser);
        List<User> users = userDao.selectAllUsers();
        request.setAttribute(Constants.USERS, users);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/admin-list-user.jsp");
        dispatcher.forward(request, response);
    }
}
