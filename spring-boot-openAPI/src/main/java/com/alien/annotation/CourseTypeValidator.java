package com.alien.annotation;

import java.util.Arrays;
import java.util.List;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class CourseTypeValidator implements ConstraintValidator<CourseTypeValidation, String> {

	@Override
	public boolean isValid(String courseType, ConstraintValidatorContext constraintValidatorContext) {
		List list = Arrays.asList("LIVE", "RECORDING");

		return list.contains(courseType);
	}

}
