package com.topstar.volunteer.util.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 自定义表单重复提交"@Token"注解
 * @author Administrator
 *
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Token {
	
	/**
	 * 是否保存token至session中,默认不保存
	 * @return
	 */
    boolean save() default false;
    
    /**
     * 是否验证token,并移除保存在session中的token,默认不执行
     * @return
     */
    boolean remove() default false;
    
    /**
     * session中保存token的key值中的自定义部分,格式为"methodName+'_token'"
     * @return
     */
    String customKey();
}