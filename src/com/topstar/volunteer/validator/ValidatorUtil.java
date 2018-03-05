package com.topstar.volunteer.validator;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Path;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import com.topstar.volunteer.validator.message.ValidateMessage;
import com.topstar.volunteer.web.context.SpringContextHolder;

/**
 * 验证工具类
 */
public class ValidatorUtil {
	
	private static ValidatorFactory validatorFactory = SpringContextHolder.getBean("validator");
	
	/**
	 * 验证实体对象
	 * @param obj 实体对象
	 * @return
	 */
	public static List<ValidateMessage> validate(Object obj) {
        List<ValidateMessage> errors = new ArrayList<ValidateMessage>();
        Validator validator = validatorFactory.getValidator();
        Set<ConstraintViolation<Object>> constraintViolations = validator.validate(obj);
        Iterator<ConstraintViolation<Object>> iter = constraintViolations.iterator();
        while (iter.hasNext()){
        	ConstraintViolation<Object> violation = iter.next();
        	if(violation != null){
        		Path path = violation.getPropertyPath();
        		ValidateMessage message = new ValidateMessage();
        		message.setPropertyName(path.toString());
        		message.setMessage(violation.getMessage());
        		
        		errors.add(message);
        	}
        }
        return errors;
    }
	
	/**
	 * 按分组验证实体对象
	 * @param obj 实体对象
	 * @param group 分组标记类
	 * @return
	 */
	public static List<ValidateMessage> validate(Object obj,Class group) {
        List<ValidateMessage> errors = new ArrayList<ValidateMessage>();
        Validator validator = validatorFactory.getValidator();
        Set<ConstraintViolation<Object>> constraintViolations = validator.validate(obj,group); 
        Iterator<ConstraintViolation<Object>> iter = constraintViolations.iterator();
        while (iter.hasNext()){
        	ConstraintViolation<Object> violation = iter.next();
        	if(violation != null){
        		Path path = violation.getPropertyPath();
        		ValidateMessage message = new ValidateMessage();
        		message.setPropertyName(path.toString());
        		message.setMessage(violation.getMessage());
        		
        		errors.add(message);
        	}
        }
        return errors;
    } 
	
	/**
	 * 验证实体类的属性
	 * @param obj 实体类对象
	 * @param propertyName 属性名
	 * @return
	 */
	public static List<ValidateMessage> validateProperty(Object obj,String propertyName) {
        List<ValidateMessage> errors = new ArrayList<ValidateMessage>();
        Validator validator = validatorFactory.getValidator();
        Set<ConstraintViolation<Object>> constraintViolations = validator.validateProperty(obj,propertyName);
        Iterator<ConstraintViolation<Object>> iter = constraintViolations.iterator();
        while (iter.hasNext()){
        	ConstraintViolation<Object> violation = iter.next();
        	if(violation != null){
        		Path path = violation.getPropertyPath();
        		ValidateMessage message = new ValidateMessage();
        		message.setPropertyName(path.toString());
        		message.setMessage(violation.getMessage());
        		
        		errors.add(message);
        	}
        }
        return errors;
    } 
	
	
	/**
	 * 验证实体类的属性
	 * @param obj 实体类对象
	 * @param propertyName 属性名
	 * @param group 分组标记类
	 * @return
	 */
	public static List<ValidateMessage> validateProperty(Object obj,String propertyName,Class group) {
        List<ValidateMessage> errors = new ArrayList<ValidateMessage>();
        Validator validator = validatorFactory.getValidator();
        Set<ConstraintViolation<Object>> constraintViolations = validator.validateProperty(obj,propertyName,group);
        Iterator<ConstraintViolation<Object>> iter = constraintViolations.iterator();
        while (iter.hasNext()){
        	ConstraintViolation<Object> violation = iter.next();
        	if(violation != null){
        		Path path = violation.getPropertyPath();
        		ValidateMessage message = new ValidateMessage();
        		message.setPropertyName(path.toString());
        		message.setMessage(violation.getMessage());
        		
        		errors.add(message);
        	}
        }
        return errors;
    } 
}
