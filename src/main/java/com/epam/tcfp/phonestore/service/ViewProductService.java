package com.epam.tcfp.phonestore.service;

import com.epam.tcfp.phonestore.constants.Constants;
import com.epam.tcfp.phonestore.dao.PhoneDao;
import com.epam.tcfp.phonestore.entity.Phone;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;

public class ViewProductService implements Service{
    PhoneDao phoneDao = new PhoneDao();
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ParseException, SQLException {
        int id = Integer.parseInt(request.getParameter(Constants.ID));
        request.setAttribute(Constants.ID, id);
        Phone phone = phoneDao.selectPhoneById(id);
        request.setAttribute(Constants.PHONE,phone);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/view.jsp");
        dispatcher.forward(request, response);
    }
}
