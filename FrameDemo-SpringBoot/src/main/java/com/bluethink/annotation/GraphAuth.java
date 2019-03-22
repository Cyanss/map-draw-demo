package com.bluethink.annotation;

import java.lang.annotation.*;

/**
 * @auther BlueThink Cyan
 * @create 2019/3/22
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface GraphAuth {
}
