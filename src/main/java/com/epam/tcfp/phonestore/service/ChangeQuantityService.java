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

public class ChangeQuantityService implements Service{
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ParseException, SQLException {
        HttpSession session = request.getSession();
        int id = Integer.parseInt(request.getParameter(Constants.ID));
        String action = request.getParameter("action");
        Cart cart = (Cart) session.getAttribute(Constants.CART);
        Map<Phone, Integer> productList = cart.getProductList();
        if(action.equals("plus")) {
            for (Map.Entry<Phone, Integer> productItem : productList.entrySet()) {
                if (id == productItem.getKey().getId()) {
                    productList.replace(productItem.getKey(), productItem.getValue() + 1);
                }
            }
        }
        else{
            Set<Map.Entry<Phone, Integer>> setOfEntries = productList.entrySet();
            Iterator<Map.Entry<Phone, Integer>> iterator = setOfEntries.iterator();
            while(iterator.hasNext()){
                Map.Entry<Phone, Integer> entry = iterator.next();
                if(id == entry.getKey().getId()){
                    productList.replace(entry.getKey(), entry.getValue()-1);
                    if(entry.getValue() == 0){
                        productList.replace(entry.getKey(), 1);
                        //iterator.remove();
                    }
                }
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
