package com.topstar.volunteer.web.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.lang.reflect.Method;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.topstar.volunteer.util.annotations.Token;
import com.topstar.volunteer.util.TokenUtil;

/**
 * 防止表单重复提交拦截器
 * 
 * @author Administrator
 *
 */
public class TokenInterceptor extends HandlerInterceptorAdapter {

	/**
	 * 在进入控制器前进行调用 这种中断方式是令preHandle的返回值为false，当preHandle的返回值为false的时候整个请求就结束了。
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// instanceof通过返回一个布尔值来指出，这个对象是否是这个特定类或者是它的子类的一个实例
		if (handler instanceof HandlerMethod) {
			HandlerMethod handlerMethod = (HandlerMethod) handler;
			Method method = handlerMethod.getMethod();
			Token annotation = method.getAnnotation(Token.class);// 得到注解@Token
			if (annotation != null) {

				boolean needSaveSession = annotation.save();
				String methodName = annotation.customKey();
				if (needSaveSession) {
					// 生成methodName_token的值
					TokenUtil.setToken(request, methodName);
					return true;
				}

				// 验证token
				boolean needRemoveSession = annotation.remove();
				if (needRemoveSession) {
					return TokenUtil.checkToken(request, response, handler, methodName);
				}
			}
			return true;
		} else {
			return super.preHandle(request, response, handler);
		}
	}

}
