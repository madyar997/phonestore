package com.epam.tcfp.phonestore.service;

import com.epam.tcfp.phonestore.constants.Constants;
import com.epam.tcfp.phonestore.dao.CustomerOrderDao;
import com.epam.tcfp.phonestore.dao.OrderPhoneDao;
import com.epam.tcfp.phonestore.entity.*;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.SQLOutput;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

public class OrderConfirmationService implements Service{
    CustomerOrderDao orderDao = null;
    OrderPhoneDao orderPhoneDao = null;
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ParseException, SQLException {
        orderDao = new CustomerOrderDao();
        orderPhoneDao = new OrderPhoneDao();
        HttpSession session = request.getSession();
        CustomerOrder order = new CustomerOrder();
        Cart cart = (Cart) session.getAttribute(Constants.CART);

        float totalCost = cart.getTotalCost();
        int totalQuantity = cart.getTotalQuantity();
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

        order.setFullName(request.getParameter(Constants.FULL_NAME));
        order.setPhoneNumber(request.getParameter(Constants.PHONE_NUMBER));;
        order.setAddress(request.getParameter(Constants.ADDRESS));
        order.setCity(request.getParameter(Constants.CITY));
        order.setZip(request.getParameter(Constants.ZIP));
        order.setTotalCost(totalCost);
        order.setCustomerId(((User) session.getAttribute(Constants.USER)).getUserId());
        order.setOrderDateTime(formatter.format(new Date()));
        order.setStatus(Constants.IN_PROCESS);
        orderDao.insertOrder(order);

        int orderId = order.getId();
        request.setAttribute(Constants.ORDER_ID, orderId);
        Map<Phone, Integer> productList = cart.getProductList();
        orderPhoneDao.insertProductListToOrder(productList,orderId);

        session.removeAttribute(Constants.CART);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/order-number.jsp");
        dispatcher.forward(request, response);
    }
}
