package com.epam.tcfp.phonestore.service.admin;

import com.epam.tcfp.phonestore.constants.Constants;
import com.epam.tcfp.phonestore.dao.BrandDao;
import com.epam.tcfp.phonestore.entity.Brand;
import com.epam.tcfp.phonestore.service.Service;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.LinkedHashSet;
import java.util.Set;

public class AdminProductAddFormService implements Service {
    BrandDao brandDao = null;
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ParseException, SQLException {
        brandDao = new BrandDao();
        Set<Brand> brands = new LinkedHashSet<>(brandDao.getBrandNames());
        request.setAttribute(Constants.BRANDS, brands);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/admin-add-product.jsp");
        dispatcher.forward(request, response);
    }
}
