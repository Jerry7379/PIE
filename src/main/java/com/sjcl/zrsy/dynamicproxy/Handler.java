package com.sjcl.zrsy.dynamicproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Arrays;

public class Handler implements InvocationHandler {

    private Jiaoable jiao = new Dog();

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (method.getName().equals("jiao")) {
            System.out.println("sdf");
            jiao.jiao();
            System.out.println("asdfasdf");
            return null;
        } else {
            return null;
        }

    }
}
