package com.cafe24.mysite.validator;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import com.cafe24.mysite.validator.constraints.GenderValidator;

@Retention(RUNTIME)
@Target({ FIELD })
@Constraint(validatedBy=GenderValidator.class)
public @interface ValidGender {

	String message() default "Invalid Gender";
	
	Class<?>[] groups() default {};
	Class<? extends Payload>[] payload() default{};
	
	
}
