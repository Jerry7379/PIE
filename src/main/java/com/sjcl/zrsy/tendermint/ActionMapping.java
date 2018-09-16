package com.sjcl.zrsy.tendermint;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

@Component
public class ActionMapping {

    private Map<String, Action> mapping;

    public ActionMapping() {
        mapping = new HashMap<>();
    }

    public void registet(ApplicationContext context) {
        Map<String, Object> actionBeans = context.getBeansWithAnnotation(ActionClass.class);
        for (Map.Entry<String, Object> beanEntry : actionBeans.entrySet()) {
            Object bean = beanEntry.getValue();
            Method[] methods = bean.getClass().getDeclaredMethods();

            for (Method method : methods) {
                if (method.isAnnotationPresent(ActionMethod.class)) {
                    Method actionMethod = method;
                    String actionName = actionMethod.getAnnotation(ActionMethod.class).value();
                    Action action = new Action(bean, actionMethod);
                    mapping.put(actionName, action);
                }
            }
        }
    }

    public Action getAction(String actionName) {
        return mapping.getOrDefault(actionName, null);
    }

}
