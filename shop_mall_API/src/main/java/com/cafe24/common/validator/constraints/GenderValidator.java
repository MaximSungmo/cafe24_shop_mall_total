package com.cafe24.common.validator.constraints;

import java.util.regex.Pattern;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.cafe24.common.validator.ValidGender;


public class GenderValidator implements ConstraintValidator<ValidGender, String> {

	private Pattern pattern = Pattern.compile("M|F|NONE");
	
	
	@Override
	public void initialize(ValidGender constraintAnnValidGender) {
		
	}
	
	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		// Annotation이 붙은 곳에서 value에 값이 없거나 길이가 0 또는 정보가 공백이라면 return false; 
		if(value == null || value.length() == 0 || "".contentEquals(value)) {
			return false;
		}
		return pattern.matcher(value).matches(); 
	}



}
