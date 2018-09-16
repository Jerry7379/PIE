package com.sjcl.zrsy.tendermint;

import java.lang.annotation.*;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ActionMethod {
    String value() default "";
}
