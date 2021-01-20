package com.epam.tcfp.phonestore.service.admin;

import com.epam.tcfp.phonestore.constants.Constants;
import com.epam.tcfp.phonestore.dao.BrandDao;
import com.epam.tcfp.phonestore.dao.PhoneDao;
import com.epam.tcfp.phonestore.service.Service;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.LinkedHashSet;

public class AdminProductManagementService implements Service {
    BrandDao brandDao = null;
    PhoneDao phoneDao = null;
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ParseException, SQLException {
        brandDao = new BrandDao();
        phoneDao = new PhoneDao();
        request.setAttribute(Constants.BRANDS, new LinkedHashSet<>(brandDao.getBrandNames()));
        request.setAttribute(Constants.PHONES, phoneDao.getPhones());
        RequestDispatcher dispatcher = request.getRequestDispatcher("/admin-product-management.jsp");
        dispatcher.forward(request, response);
    }
}
