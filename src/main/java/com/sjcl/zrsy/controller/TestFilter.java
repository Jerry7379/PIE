package com.sjcl.zrsy.controller;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class TestFilter  implements Filter {
    @Override
    public void init(FilterConfig filterConfig)throws ServletException{
    }
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {


        HttpServletRequest req = (HttpServletRequest) servletRequest;
        try{
            req.getSession().getAttribute("userInfo").toString();
            filterChain.doFilter(servletRequest, servletResponse);
        }catch(Exception e){
            HttpServletResponse rep=(HttpServletResponse)servletResponse;
            rep.getOutputStream().write("请先登录".getBytes());
        }
    }
    @Override
    public void destroy() {

    }



}
