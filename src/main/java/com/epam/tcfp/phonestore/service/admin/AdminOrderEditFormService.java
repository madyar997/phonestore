package com.epam.tcfp.phonestore.service.admin;

import com.epam.tcfp.phonestore.constants.Constants;
import com.epam.tcfp.phonestore.dao.CustomerOrderDao;
import com.epam.tcfp.phonestore.dao.OrderPhoneDao;
import com.epam.tcfp.phonestore.entity.CustomerOrder;
import com.epam.tcfp.phonestore.service.Service;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;

public class AdminOrderEditFormService implements Service {
    CustomerOrderDao orderDao;
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ParseException, SQLException {
        orderDao = new CustomerOrderDao();
        int id = Integer.parseInt(request.getParameter(Constants.ID));
        CustomerOrder order = orderDao.selectOrderById(id);
        request.setAttribute(Constants.ORDER, order);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/admin-edit-order-form.jsp");
        dispatcher.forward(request, response);
    }
}
