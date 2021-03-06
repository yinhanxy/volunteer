package com.topstar.volunteer.validator.constraintvalidators;


import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.validator.internal.util.logging.Log;
import org.hibernate.validator.internal.util.logging.LoggerFactory;

import com.github.pagehelper.util.StringUtil;
import com.topstar.volunteer.util.StringTools;
import com.topstar.volunteer.validator.constraints.NotEmptyAndOracleLength;

/**
 * 针对Oracle中UTF-8编码的字符长度的问题，改写Length注解的校验<br/>
 * 英文为一个字节，中文（包括韩文、日文等）为三个字节
 *
 */
public class NotEmptyAndOracleLengthValidator implements ConstraintValidator<NotEmptyAndOracleLength, CharSequence>{
	
	private static final Log log = LoggerFactory.make();
  
	private int min;
	private int max;

	public void initialize(NotEmptyAndOracleLength parameters){
		this.min = parameters.min();
		this.max = parameters.max();
		validateParameters();
	}

	public boolean isValid(CharSequence value, ConstraintValidatorContext constraintValidatorContext) {
		if (value == null) {
			return false;
		}
		if(StringUtils.isBlank(value.toString())){
			return false;
		}
		int length = StringTools.oracleChineseLength(value.toString());
		return (length >= this.min) && (length <= this.max);
	}

	private void validateParameters() {
		if (this.min < 0) {
			throw log.getMinCannotBeNegativeException();
		}
		if (this.max < 0) {
			throw log.getMaxCannotBeNegativeException();
		}
		if (this.max < this.min)
			throw log.getLengthCannotBeNegativeException();
	}
}