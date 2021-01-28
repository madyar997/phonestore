package com.epam.tcfp.phonestore.service.user;

import com.epam.tcfp.phonestore.constants.Constants;
import com.epam.tcfp.phonestore.dao.CustomerOrderDao;
import com.epam.tcfp.phonestore.entity.CustomerOrder;
import com.epam.tcfp.phonestore.entity.Phone;
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
import java.util.Map;

public class UserHomeService implements Service {
    CustomerOrderDao orderDao = null;
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ParseException, SQLException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(Constants.USER);
        orderDao = new CustomerOrderDao();
        int userId = user.getUserId();
        Map<CustomerOrder, Map<Phone, Integer>> orders = orderDao.selectAllOrdersByUserId(userId);
        request.setAttribute(Constants.USER, user);
        request.setAttribute(Constants.ORDERS, orders);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/user-home.jsp");
        dispatcher.forward(request, response);
    }
}
