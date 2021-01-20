package com.epam.tcfp.phonestore.service.admin;

import com.epam.tcfp.phonestore.constants.Constants;
import com.epam.tcfp.phonestore.dao.BrandDao;
import com.epam.tcfp.phonestore.dao.CharacteristicsDao;
import com.epam.tcfp.phonestore.dao.PhoneDao;
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

public class AdminProductAddService implements Service {
    BrandDao brandDao = null;
    PhoneDao phoneDao = null;
    CharacteristicsDao characteristicsDao = null;
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ParseException, SQLException {
        brandDao = new BrandDao();
        phoneDao = new PhoneDao();
        characteristicsDao = new CharacteristicsDao();

        Characteristics characteristics = new Characteristics();
        characteristics.setMemory(request.getParameter(Constants.MEMORY));
        characteristics.setDisplay(request.getParameter(Constants.DISPLAY));
        characteristics.setScreenSize(request.getParameter(Constants.SCREEN_SIZE));
        characteristics.setCamera(request.getParameter(Constants.CAMERA));
        characteristics.setFrontCamera(request.getParameter(Constants.FRONT_CAMERA));
        characteristics.setRam(request.getParameter(Constants.RAM));
        characteristics.setProcessor(request.getParameter(Constants.PROCESSOR));
        characteristics.setBattery(request.getParameter(Constants.BATTERY));
        characteristics.setSizes(request.getParameter(Constants.SIZES));
        characteristics.setWeight(request.getParameter(Constants.WEIGHT));
        characteristicsDao.insertCharacteristics(characteristics);

        Phone phone = new Phone();
        phone.setBrandId(brandDao.parseIdbyBrandName(request.getParameter(Constants.BRAND)));
        phone.setColor(request.getParameter(Constants.COLOR));
        phone.setModel(request.getParameter(Constants.MODEL));
        phone.setPrice(Float.parseFloat(request.getParameter(Constants.PRICE)));
        phone.setModelYear(Integer.parseInt(request.getParameter(Constants.MODEL_YEAR)));
        phone.setDescription(request.getParameter(Constants.PHONE_DESCRIPTION));
        phone.setPicture(request.getParameter(Constants.PICTURE_PATH));
        phone.setQuantity(Integer.parseInt(request.getParameter(Constants.QUANTITY)));
        phone.setCharacteristicsId(characteristics.getId());
        phoneDao.insertPhone(phone);

        request.setAttribute(Constants.BRANDS, new LinkedHashSet<>(brandDao.getBrandNames()));
        request.setAttribute(Constants.PHONES, phoneDao.getPhones());
        RequestDispatcher dispatcher = request.getRequestDispatcher("/admin-product-management.jsp");
        dispatcher.forward(request, response);
    }
}
