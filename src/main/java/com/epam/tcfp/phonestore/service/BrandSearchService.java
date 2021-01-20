package com.epam.tcfp.phonestore.service;

import com.epam.tcfp.phonestore.constants.Constants;
import com.epam.tcfp.phonestore.dao.BrandDao;
import com.epam.tcfp.phonestore.dao.PhoneDao;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.LinkedHashSet;

public class BrandSearchService implements Service{
    PhoneDao phoneDao = null;
    BrandDao brandDao = null;
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ParseException, SQLException {
        phoneDao = new PhoneDao();
        brandDao = new BrandDao();
        request.setAttribute(Constants.BRANDS, new LinkedHashSet<>(brandDao.getBrandNames()));
        request.setAttribute(Constants.PHONES, phoneDao.getPhonesByBrandId(Integer.parseInt(request.getParameter("brandId"))));
        RequestDispatcher dispatcher = request.getRequestDispatcher("/home.jsp");
        dispatcher.forward(request, response);
    }
}
