package com.topstar.volunteer.util;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.math.BigInteger;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.topstar.volunteer.util.annotations.Token;

public class TokenUtil {
	private static final Random RANDOM = new Random();
	private static Logger log = Logger.getLogger(TokenUtil.class);

	/**
	 * 保存token
	 * 
	 * @param request
	 * @return token
	 */
	public static String setToken(HttpServletRequest request, String methodName) {
		String token = generateGUID();
		try {
			request.getSession(true).setAttribute(methodName + "_token", token);
			return token;
		} catch (Exception e) {
			log.error("保存token失败", e);
		}
		return "";
	}

	/**
	 * 生成36位随机数
	 * 
	 * @return
	 */
	public static String generateGUID() {
		return new BigInteger(165, RANDOM).toString(36).toUpperCase();
	}

	/**
	 * 判断是否为Ajax请求
	 * 
	 * @param request
	 *            HttpServletRequest
	 * @return 是：true；否：false
	 */
	public static boolean isAjaxRequest(HttpServletRequest request) {
		String header = request.getHeader("X-Requested-With");
		if (header != null && "XMLHttpRequest".equals(header)) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 验证token是否是重复提交,并在验证完成后将session中所保存的"methodName + '_token'"移除
	 * 
	 * @param request
	 * @param response
	 * @param handler
	 * @param methodName
	 * @return
	 * @throws Exception
	 */
	public static boolean checkToken(HttpServletRequest request, HttpServletResponse response, Object handler,
			String methodName) throws Exception {
		request.setAttribute("token_tag", true);// 默认设置token_tag的值为true
		// 判断是否是重复提交
		if (isRepeatSubmit(request, methodName)) {
			log.debug("是重复提交...");
			return handleInvalidToken(request, response, handler);
		}
		log.debug("不是重复提交...");

		// 不管是不是重复提交,验证完成后都将session中所保存的"methodName + '_token'"移除
		request.getSession(true).removeAttribute(methodName + "_token");
		return true;
	}

	/**
	 * 获取注解@Token中的customKey的值
	 * 
	 * @param methodName
	 * @return
	 */
	public static String getTokenName(String methodName, Class<?> t) {
		String name = "";
		try {
			Method[] methods = t.getDeclaredMethods(); // 取得这个类的所有方法
			if (methods != null) {
				for (int i = 0; i < methods.length; i++) {
					Method method = methods[i];
					if (methodName.equals(method.getName())) { // 取得方法
						Token annotation = method.getAnnotation(Token.class);
						name = annotation.customKey();
						break;
					}
				}
			}
		} catch (SecurityException e) {
			log.debug("获取" + methodName + "方法的customKey的值时出错");
		}
		return name;
	}

	/**
	 * 判断重复提交
	 * 
	 * @param request
	 * @param methodName
	 * @return true：重复提交或者非法提交 false：正常第一次提交
	 */
	private static boolean isRepeatSubmit(HttpServletRequest request, String methodName) {
		String serverToken = (String) request.getSession(true).getAttribute(methodName + "_token");
		if (serverToken == null) {
			return true;
		}
		String clinetToken = request.getParameter("token");
		if (clinetToken == null) {
			return true;
		}
		if (!serverToken.equals(clinetToken)) {
			return true;
		}
		return false;
	}

	/**
	 * 是重复或非法提交,接着判断请求是否是Ajax请求
	 * 
	 * @param request
	 * @param response
	 * @param handler
	 * @return
	 * @throws Exception
	 */
	private static boolean handleInvalidToken(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		boolean isAjaxRequest = isAjaxRequest(request);// 判断是否是Ajax请求
		if (isAjaxRequest) {
			log.debug("重复的提交请求是Ajax请求...");
			ResultData data = new ResultData();
			data.setSuccess(false);
			data.setMessage("您已提交表单，请不要重复提交!");
			writeMessageUtf8(response, data);
			return false;
		}
		log.debug("重复的提交请求不是Ajax请求...");
		// 不是Ajax请求,设置token_tag的值为false
		request.setAttribute("token_tag", false);
		return true;
	}

	/**
	 * 输出utf-8编码的Json
	 * 
	 * @param response
	 * @param data
	 * @throws IOException
	 */
	private static void writeMessageUtf8(HttpServletResponse response, ResultData data) throws IOException {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json; charset=utf-8");
		PrintWriter out = null;
		try {
			out = response.getWriter();
			out.append(data.toJSONString());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (out != null) {
				out.close();
			}
		}
	}

}