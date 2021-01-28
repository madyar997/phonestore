package com.epam.tcfp.phonestore.service.admin;

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
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;
import java.util.Map;

public class AdminOrderEditService implements Service {
    CustomerOrderDao orderDao;
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ParseException, SQLException {
        orderDao = new CustomerOrderDao();
        CustomerOrder updatedOrder = new CustomerOrder();
        updatedOrder.setId(Integer.parseInt(request.getParameter(Constants.ID)));
        updatedOrder.setFullName(request.getParameter(Constants.FULL_NAME));
        updatedOrder.setPhoneNumber(request.getParameter(Constants.PHONE_NUMBER));
        updatedOrder.setAddress(request.getParameter(Constants.ADDRESS));
        updatedOrder.setCity(request.getParameter(Constants.CITY));
        updatedOrder.setZip(request.getParameter(Constants.ZIP));
        updatedOrder.setTotalCost(Float.parseFloat(request.getParameter(Constants.TOTAL_COST)));
        updatedOrder.setOrderDateTime(request.getParameter(Constants.DATE));
        updatedOrder.setCustomerId(Integer.parseInt(request.getParameter(Constants.CUSTOMER_ID)));
        updatedOrder.setStatus(request.getParameter(Constants.STATUS));
        updatedOrder.setDetails(request.getParameter(Constants.DETAILS));
        orderDao.updateOrder(updatedOrder);
        Map<CustomerOrder, Map<Phone, Integer>> orders = orderDao.selectAllOrders();
        request.setAttribute(Constants.ORDERS, orders);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/order-management.jsp");
        dispatcher.forward(request, response);
    }
}
