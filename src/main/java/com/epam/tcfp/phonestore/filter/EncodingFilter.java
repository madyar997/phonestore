package com.epam.tcfp.phonestore.filter;

import org.apache.log4j.Logger;

import javax.servlet.*;
import java.io.IOException;

public class EncodingFilter implements Filter {
    public static final String CODE_ENCODING = "UTF-8";
    private static final Logger log = Logger.getLogger(EncodingFilter.class.getName());
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("Encoding filter initialized");
    }
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        servletResponse.setContentType(CODE_ENCODING);
        servletRequest.setCharacterEncoding(CODE_ENCODING);
        servletResponse.setCharacterEncoding(CODE_ENCODING);
        log.info("Encoding filter filtering");
        filterChain.doFilter(servletRequest, servletResponse);
    }
    @Override
    public void destroy() {
        log.info("Encoding filter destroyed");
    }
}
