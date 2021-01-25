package com.epam.tcfp.phonestore.service;

import com.epam.tcfp.phonestore.constants.Constants;
import com.epam.tcfp.phonestore.dao.BrandDao;
import com.epam.tcfp.phonestore.dao.PhoneDao;
import org.apache.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.LinkedHashSet;

public class SearchService implements Service{
    private static final Logger log = Logger.getLogger(SearchService.class.getName());
    PhoneDao phoneDao = null;
    BrandDao brandDao = null;
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ParseException, SQLException {
        phoneDao = new PhoneDao();
        brandDao = new BrandDao();
        request.setAttribute(Constants.BRANDS, new LinkedHashSet<>(brandDao.getBrandNames()));
        request.setAttribute(Constants.PHONES, phoneDao.getPhoneByModel(request.getParameter(Constants.SEARCH)));

        RequestDispatcher dispatcher = request.getRequestDispatcher("/search-result.jsp");
        dispatcher.forward(request, response);
    }
}
