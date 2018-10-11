package com.sjcl.zrsy.controller;

import javax.servlet.http.HttpServletRequest;

public class SessionAuthenticateWay implements Authenticator.AuthenticateWay {
    @Override
    public boolean authenticate(HttpServletRequest request) {
        return request.getSession().getAttribute("userInfo") != null;
    }
}
