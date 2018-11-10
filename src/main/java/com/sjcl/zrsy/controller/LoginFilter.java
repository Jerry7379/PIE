package com.sjcl.zrsy.controller;

import com.sjcl.zrsy.bigchaindb.KeyPairHolder;
import sun.management.jmxremote.ConnectorBootstrap;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

public class LoginFilter implements Filter {
    private Authenticator authenticator;
    private static final Set<String> ALLOWED_PATHS = Collections.unmodifiableSet(new HashSet<>(
                    Arrays.asList("/search",
                            "/register",
                            "/login",
                            "/Login.html")
            )
    );

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        authenticator = new Authenticator();
        authenticator.addWay(new SessionAuthenticateWay());
        authenticator.addWay(new SignatureAuthenticateWay());
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;

        //设置跨域访问
        resp.setHeader("Access-Control-Allow-Origin", "*");
        resp.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE, PUT, GET");
        resp.setHeader("Access-Control-Max-Age", "3600");
        resp.setHeader("Access-Control-Allow-Headers", "x-requested-with");


        String path = req.getServletPath();


        if (ALLOWED_PATHS.contains(path)) {
            filterChain.doFilter(servletRequest, servletResponse);
        } else if (authenticator.authenticate(req)) {
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            resp.sendRedirect("/PorkTraceability/login/Login.html");
        }

    }

    @Override
    public void destroy() {
    }
}
