package com.epam.tcfp.phonestore.service;

import com.epam.tcfp.phonestore.constants.Constants;
import com.epam.tcfp.phonestore.dao.BrandDao;
import com.epam.tcfp.phonestore.dao.PhoneDao;
import com.epam.tcfp.phonestore.entity.Brand;
import com.epam.tcfp.phonestore.entity.Phone;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.LinkedHashSet;
import java.util.Set;

public class HomeService implements Service {
    BrandDao brandDao;
    PhoneDao phoneDao;

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ParseException, SQLException {
        brandDao = new BrandDao();
        phoneDao = new PhoneDao();
        Set<Brand> brands = new LinkedHashSet<>(brandDao.getBrandNames());
        Set<Phone> phones = phoneDao.getPhones();
        request.setAttribute(Constants.BRANDS, brands);
        request.setAttribute(Constants.PHONES, phones);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/home.jsp");
        dispatcher.forward(request, response);
    }
}
