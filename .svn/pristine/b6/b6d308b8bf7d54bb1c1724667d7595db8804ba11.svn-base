package com.topstar.volunteer.validator.constraints;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.CONSTRUCTOR;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import com.topstar.volunteer.validator.constraintvalidators.OracleLengthValidator;

/**
 * 自定义约束<br/>
 * 值要在min和max之间<br/>
 * 针对Oracle中UTF-8编码的字符长度的问题，改写Length注解的校验<br/>
 * 英文为一个字节，中文（包括韩文、日文等）为三个字节
 */
@Documented
@Constraint(validatedBy = {OracleLengthValidator.class})
@Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER })
@Retention(RUNTIME)
public @interface OracleLength{
  
	int min() default 0;

	int max() default Integer.MAX_VALUE;

	String message() default "{org.hibernate.validator.constraints.OracleLength.message}";

	Class<?>[] groups() default { };

	Class<? extends Payload>[] payload() default { };

	@Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER })
	@Retention(RUNTIME)
	@Documented
	public static @interface List{
		public abstract OracleLength[] value();
	}
}