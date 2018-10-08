package com.sjcl.zrsy.controller;

import com.sjcl.zrsy.bigchaindb.KeyPairHolder;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

public class LoginFilter implements Filter {
    private static final Set<String> ALLOWED_PATHS = Collections.unmodifiableSet(new HashSet<>(
                    Arrays.asList("http://localhost:8080/search",
                            "http://localhost:8080/register",
                            "http://localhost:8080/Login.html")
            )
    );

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        String path = req.getRequestURL().substring(req.getContextPath().length()).replaceAll("[/]+$", "");


        if (ALLOWED_PATHS.contains(path)) {
            filterChain.doFilter(servletRequest, servletResponse);
        } else if (KeyPairHolder.isLogin()) {
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            resp.getOutputStream().write("请先登录".getBytes());
            resp.sendRedirect("http://localhost:8080/PorkTraceability/login/Login.html");
        }

    }

    @Override
    public void destroy() {
    }
}
