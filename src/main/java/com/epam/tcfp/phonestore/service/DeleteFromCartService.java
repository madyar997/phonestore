package com.epam.tcfp.phonestore.service;

import com.epam.tcfp.phonestore.constants.Constants;
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
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class DeleteFromCartService implements Service {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ParseException, SQLException {
        HttpSession session = request.getSession();
        Cart cart = (Cart) session.getAttribute(Constants.CART);
        int id = Integer.parseInt(request.getParameter(Constants.ID));

        Map<Phone, Integer> productList = ((Cart) session.getAttribute(Constants.CART)).getProductList();
        Set<Map.Entry<Phone, Integer>> setOfEntries = productList.entrySet();
        setOfEntries.removeIf(entry -> id == entry.getKey().getId());

        cart.setProductList(productList);
        cart.setTotalQuantity(cart.getTotalQuantity());
        cart.setTotalCost(cart.getTotalCost() + cart.getShipping());
        session.setAttribute(Constants.CART, cart);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/cart.jsp");
        dispatcher.forward(request, response);
    }
}