package com.epam.tcfp.phonestore.filter;

import com.epam.tcfp.phonestore.constants.Constants;
import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class SessionLocaleFilter implements Filter {
    private static final Logger log = Logger.getLogger(SessionLocaleFilter.class.getName());
    int count  = 0;
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
      throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        
        if (req.getParameter(Constants.SESSION_LOCALE) != null) {
            req.getSession().setAttribute(Constants.LANGUAGE, req.getParameter(Constants.SESSION_LOCALE));
        }
        log.info("SessionLocaleFilter filtering");
        chain.doFilter(request, response);
    }
    public void destroy() {
        log.info("SessionLocaleFilter destroyed");
    }
    public void init(FilterConfig arg0) throws ServletException {
        log.info("SessionLocaleFilter initialized");
    }
}