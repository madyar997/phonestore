package com.epam.tcfp.phonestore.service.user;

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

public class UserPersonalDataFormService implements Service {
    UserDao userDao = new UserDao();
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ParseException, SQLException {
        User user = userDao.selectUser(Integer.parseInt(request.getParameter(Constants.ID)));
    request.setAttribute(Constants.USER,user);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/user-personal-data-form.jsp");
        dispatcher.forward(request, response);
    }
}
