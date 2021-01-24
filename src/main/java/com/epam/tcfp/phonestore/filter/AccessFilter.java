package com.epam.tcfp.phonestore.filter;

import com.epam.tcfp.phonestore.constants.Constants;
import com.epam.tcfp.phonestore.entity.User;
import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AccessFilter implements Filter {
    private static final Logger log = Logger.getLogger(AccessFilter.class.getName());
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("Access filter initialized");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        String currentUrl = req.getRequestURI();
        User user = (User)req.getSession().getAttribute(Constants.USER);
        if((user == null) ||
                (user.getRole() == 1 && currentUrl.startsWith("/phonestore/user/"))||
                (user.getRole() == 0 && currentUrl.startsWith("/phonestore/admin/"))||
                (user.getRole() == 2 && (currentUrl.startsWith("/phonestore/admin/")||
                        currentUrl.startsWith("/phonestore/user/")))){
            RequestDispatcher dispatcher = req.getRequestDispatcher("/phonestore/login/form");
            dispatcher.forward(req, resp);
        }
        log.info("Access filter filtering");
        filterChain.doFilter(servletRequest, servletResponse);

    }

    @Override
    public void destroy() {
        log.info("Access filter destroyed");
    }
}
