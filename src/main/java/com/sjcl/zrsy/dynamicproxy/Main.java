package com.sjcl.zrsy.dynamicproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

public class Main {
    public static void main(String[] args) {
        Jiaoable jiao1 = new Dog();
        Jiaoable jiao2 = new Cat();

        Jiaoable obj = (Jiaoable)Proxy.newProxyInstance(Main.class.getClassLoader(), new Class[]{Jiaoable.class}, new Handler());
        obj.jiao();

//        hhhh(jiao1);
//        hhhh(jiao2);
    }

    public static void hhhh(Jiaoable jiaoable) {
        jiaoable.jiao();
        jiaoable.toString();
    }
}
