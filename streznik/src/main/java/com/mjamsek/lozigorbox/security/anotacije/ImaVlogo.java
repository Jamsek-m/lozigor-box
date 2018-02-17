package com.mjamsek.lozigorbox.security.anotacije;

import com.mjamsek.lozigorbox.security.config.Vloge;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ImaVlogo {
	Vloge[] value() default {};
}
