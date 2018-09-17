package com.sjcl.zrsy.tendermint;

import com.alibaba.fastjson.JSON;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Action {
    private Method actionMethod;
    private Class<?> paramType;
    private Object bean;

    public Action(Object bean, Method actionMethod){
        this.bean = bean;
        this.actionMethod = actionMethod;
        if (this.actionMethod != null) {
            this.paramType = this.actionMethod.getParameterTypes()[0];
        }
    }

    public Object act(String param) {
        try {
            Object p = JSON.parseObject(param, paramType);
            return actionMethod.invoke(bean, p);
        } catch (IllegalAccessException e) {
            throw new RuntimeException("has not permission");
        } catch (InvocationTargetException e) {
            return e.getTargetException();
        }
    }
}
