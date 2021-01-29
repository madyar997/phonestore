package com.epam.tcfp.phonestore.service.admin;

import com.epam.tcfp.phonestore.constants.Constants;
import com.epam.tcfp.phonestore.dao.CustomerOrderDao;
import com.epam.tcfp.phonestore.entity.CustomerOrder;
import com.epam.tcfp.phonestore.entity.Phone;
import com.epam.tcfp.phonestore.service.Service;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Map;


public class AdminOrderDeleteService implements Service{
    CustomerOrderDao orderDao;
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ParseException, SQLException {
        orderDao = new CustomerOrderDao();
        orderDao.deleteOrderById(Integer.parseInt(request.getParameter(Constants.ID)));
        Map<CustomerOrder, Map<Phone, Integer>> orders = orderDao.selectAllOrders();
        request.setAttribute(Constants.ORDERS, orders);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/order-management.jsp");
        dispatcher.forward(request, response);
    }
}
