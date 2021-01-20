package com.epam.tcfp.phonestore.filter;

import com.epam.tcfp.phonestore.constants.Constants;
import com.epam.tcfp.phonestore.dao.BrandDao;
import com.epam.tcfp.phonestore.dao.PhoneDao;
import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.LinkedHashSet;

public class SearchFilter implements Filter {
    private static final Logger log = Logger.getLogger(SearchFilter.class.getName());
    PhoneDao phoneDao = null;
    BrandDao brandDao = null;
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("Search filter initialized");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        phoneDao = new PhoneDao();
        brandDao = new BrandDao();
        if(req.getParameter(Constants.SEARCH) != null){
            req.setAttribute(Constants.BRANDS, new LinkedHashSet<>(brandDao.getBrandNames()));
            req.setAttribute(Constants.PHONES, phoneDao.getPhoneByModel(req.getParameter(Constants.SEARCH)));
            RequestDispatcher dispatcher = req.getRequestDispatcher("/home.jsp");
            dispatcher.forward(req, resp);
        }
        log.info("Search filter filtering");
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
        log.info("Search filter destroyed");
    }
}
