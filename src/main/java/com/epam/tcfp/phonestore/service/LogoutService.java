package com.epam.tcfp.phonestore.service;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;

public class LogoutService implements Service{
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ParseException, SQLException {
        HttpSession session=request.getSession();
        session.removeAttribute("user");
        RequestDispatcher dispatcher = request.getRequestDispatcher("/logout.jsp");
        dispatcher.forward(request, response);
    }
}
