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

import com.topstar.volunteer.validator.constraintvalidators.PhoneValidator;

/**
 * 自定义手机号码约束
 */
@Documented
@Constraint(validatedBy = {PhoneValidator.class})
@Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER })
@Retention(RUNTIME)
public @interface Phone{
  
	String regexp() default "^[0][1-9]{2,3}-[0-9]{5,10}$|^[1-9]{1}[0-9]{5,8}$|^[1][3,4,5,7,8][0-9]{9}$";

	String message() default "{org.hibernate.validator.constraints.Phone.message}";

	Class<?>[] groups() default { };

	Class<? extends Payload>[] payload() default { };

	@Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER })
	@Retention(RUNTIME)
	@Documented
	public static @interface List{
		public abstract Phone[] value();
	}
}