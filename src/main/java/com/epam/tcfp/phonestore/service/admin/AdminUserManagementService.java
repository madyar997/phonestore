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

public class AdminUserManagementService implements Service {
    UserDao userDao;
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ParseException, SQLException {
        userDao = new UserDao();
        List<User> users = userDao.selectAllUsers();
        request.setAttribute(Constants.USERS, users);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/admin-list-user.jsp");
        dispatcher.forward(request, response);
    }
}
