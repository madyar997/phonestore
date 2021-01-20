package com.epam.tcfp.phonestore.service.admin;

import com.epam.tcfp.phonestore.constants.Constants;
import com.epam.tcfp.phonestore.dao.BrandDao;
import com.epam.tcfp.phonestore.dao.CharacteristicsDao;
import com.epam.tcfp.phonestore.dao.PhoneDao;
import com.epam.tcfp.phonestore.entity.Brand;
import com.epam.tcfp.phonestore.entity.Characteristics;
import com.epam.tcfp.phonestore.entity.Phone;
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

public class AdminProductEditFormService implements Service {
    PhoneDao phoneDao = null;
    CharacteristicsDao characteristicsDao = null;
    BrandDao brandDao = null;
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ParseException, SQLException {
        phoneDao = new PhoneDao();
        characteristicsDao = new CharacteristicsDao();
        brandDao = new BrandDao();
        int id = Integer.parseInt(request.getParameter(Constants.ID));
        request.setAttribute(Constants.ID,id);
        Phone phone = phoneDao.selectPhoneById(id);
        Characteristics phoneCharacteristics = characteristicsDao.getCharacteristicsById(phone.getCharacteristicsId());
        Set<Brand> brands = new LinkedHashSet<>(brandDao.getBrandNames());
        request.setAttribute(Constants.BRANDS, brands);
        request.setAttribute(Constants.PHONE, phone);
        request.setAttribute(Constants.CHARACTERISTICS, phoneCharacteristics);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/admin-product-edit-form.jsp");
        dispatcher.forward(request, response);
    }
}
