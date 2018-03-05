package com.topstar.volunteer.validator.constraintvalidators;


import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.topstar.volunteer.validator.constraints.Phone;

/**
 * 验证手机号码
 * @author Administrator
 *
 */
public class PhoneValidator implements ConstraintValidator<Phone, CharSequence>{
	
	private String regexp;

	public void initialize(Phone parameters){
		this.regexp = parameters.regexp();
	}

	@Override
    public boolean isValid(CharSequence value, ConstraintValidatorContext context) {
        if(value==null){
        	return true;
        }
        if( value.toString().matches(regexp)){
            return true;
        }
        return false;
    }
}