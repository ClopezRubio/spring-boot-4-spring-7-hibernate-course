package com.clopezrubio.springdemo.mvc.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class CourseCodeConstraintValidator implements ConstraintValidator<CourseCode, String> {

    private String coursePrefix;

    @Override
    public boolean isValid(String theCode, ConstraintValidatorContext constraintValidatorContext) {

        boolean result;

        if(theCode != null){
            result = theCode.regionMatches(true, 0, coursePrefix, 0, coursePrefix.length());
        }else{
            result = true;
        }

        return result;
    }

    @Override
    public void initialize(CourseCode theCourseCode) {
        this.coursePrefix = theCourseCode.value();
    }
}
