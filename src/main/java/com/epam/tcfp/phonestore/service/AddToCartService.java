package com.epam.tcfp.phonestore.service;

import com.epam.tcfp.phonestore.constants.Constants;
import com.epam.tcfp.phonestore.dao.PhoneDao;
import com.epam.tcfp.phonestore.entity.Cart;
import com.epam.tcfp.phonestore.entity.Phone;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

public class AddToCartService implements Service {
    PhoneDao phoneDao = new PhoneDao();


    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ParseException, SQLException {
        HttpSession session = request.getSession();
        Cart cart;
        Map<Phone, Integer> productList;
        if (session.getAttribute(Constants.CART) == null) {
            cart = new Cart();
            productList = new HashMap<>();
            int id = Integer.parseInt(request.getParameter(Constants.ID));
            productList.put(phoneDao.selectPhoneById(id), Constants.DEFAULT_QUANTITY);
            cart.setProductList(productList);
            cart.setTotalQuantity(cart.getTotalQuantity());
            cart.setTotalCost(cart.getTotalCost() + cart.getShipping());
        } else {
            cart = (Cart) session.getAttribute(Constants.CART);
            int id = Integer.parseInt(request.getParameter(Constants.ID));
            Phone phone = phoneDao.selectPhoneById(id);
            productList = cart.getProductList();
            if (!productList.containsKey(phone)) {
                productList.put(phoneDao.selectPhoneById(id), Constants.DEFAULT_QUANTITY);
                cart.setProductList(productList);
                cart.setTotalQuantity(cart.getTotalQuantity());
                cart.setTotalCost(cart.getTotalCost() + cart.getShipping());
            }
        }
        session.setAttribute(Constants.CART, cart);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/cart.jsp");
        dispatcher.forward(request, response);
    }




}
