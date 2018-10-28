package com.sjcl.zrsy.controller;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

public class Authenticator {

    private List<AuthenticateWay> ways = new ArrayList<>();

    public boolean authenticate(HttpServletRequest request) {
        for (AuthenticateWay way : ways) {
            boolean ret = way.authenticate(request);
            if (ret) {
                return true;
            } else {
                continue;
            }
        }
        return false;
    }

    public boolean addWay(AuthenticateWay way) {
        return ways.add(way);
    }

    public interface AuthenticateWay {
        public boolean authenticate(HttpServletRequest request);
    }
}
