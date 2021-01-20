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
import java.util.List;
import java.util.Map;

public class DeleteFromCartService implements Service {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ParseException, SQLException {
        HttpSession session = request.getSession();
        Cart cart = (Cart) session.getAttribute(Constants.CART);
        int id = Integer.parseInt(request.getParameter(Constants.ID));
        Map<Phone, Integer> productList = ((Cart) session.getAttribute(Constants.CART)).getProductList();
        for (Map.Entry<Phone, Integer> productItem : productList.entrySet()) {
            if (id == productItem.getKey().getId()) {
                productList.remove(productItem.getKey());
            }
        }
        cart.setProductList(productList);
        cart.setTotalQuantity(cart.getTotalQuantity());
        cart.setTotalCost(cart.getTotalCost() + cart.getShipping());
        session.setAttribute(Constants.CART, cart);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/cart.jsp");
        dispatcher.forward(request, response);
    }
}