package com.noukri.noukriapk.config;

import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SwithDataSource {

    String value() default "";
}
