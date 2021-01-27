package com.epam.tcfp.phonestore.service.admin;

import com.epam.tcfp.phonestore.constants.Constants;
import com.epam.tcfp.phonestore.dao.UserDao;
import com.epam.tcfp.phonestore.entity.User;
import com.epam.tcfp.phonestore.service.Service;
import org.apache.commons.codec.digest.DigestUtils;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

public class AdminCreateUserService implements Service {
    UserDao userDao = new UserDao();
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ParseException, SQLException {
        User user = new User();
        user.setFirstName(request.getParameter(Constants.FIRST_NAME));
        user.setSecondName(request.getParameter(Constants.SECOND_NAME));
    user.setEmail(request.getParameter(Constants.EMAIL));
        user.setPhoneNumber(request.getParameter(Constants.PHONE_NUMBER));
        user.setRole(Integer.parseInt(request.getParameter(Constants.ROLE)));
        user.setAddress(request.getParameter(Constants.ADDRESS));
        user.setPassword(DigestUtils.md5Hex(request.getParameter(Constants.PASSWORD)));
        userDao.insertUser(user);
        List<User> users = userDao.selectAllUsers();
        request.setAttribute(Constants.USERS, users);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/admin-list-user.jsp");
        dispatcher.forward(request, response);
    }
}
