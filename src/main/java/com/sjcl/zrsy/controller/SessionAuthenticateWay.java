package com.sjcl.zrsy.controller;

import javax.servlet.http.HttpServletRequest;

public class SessionAuthenticateWay implements Authenticator.AuthenticateWay {

    /**
     * 判断request中的session中的信息
     * @param request
     * @return
     */
    @Override
    public boolean authenticate(HttpServletRequest request) {
        return request.getSession().getAttribute("userInfo") != null;
    }
}
