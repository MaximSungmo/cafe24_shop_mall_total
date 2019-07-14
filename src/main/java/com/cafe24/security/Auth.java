package com.cafe24.security;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Auth {
	public enum Role {MEMBER, ADMIN}
	public Role role() default Role.MEMBER;
//	String value() default "USER";
//	int test() default 1;
}
