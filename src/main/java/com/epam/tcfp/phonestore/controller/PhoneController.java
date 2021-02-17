package com.epam.tcfp.phonestore.controller;

import com.epam.tcfp.phonestore.service.Service;
import com.epam.tcfp.phonestore.service.factory.ServiceFactory;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;

public class PhoneController extends HttpServlet{
    private static final Logger log = Logger.getLogger(PhoneController.class.getName());
    private final ServiceFactory serviceFactory = ServiceFactory.getInstance();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String requestUri = req.getRequestURI().toLowerCase();
        Service currentService = serviceFactory.getService(requestUri);
        try{
            currentService.execute(req, resp);
        } catch (ParseException | SQLException e){
            log.error("current service is not invoked",e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}