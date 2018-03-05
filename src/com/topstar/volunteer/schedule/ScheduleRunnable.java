package com.topstar.volunteer.schedule;

import java.lang.reflect.Method;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ReflectionUtils;

/**
 * 执行定时任务
 * 
 */
public class ScheduleRunnable implements Runnable {

	Logger logger = LoggerFactory.getLogger(ScheduleRunnable.class);
	private Object target;
	private Method method;
	private Object[] params;

	public ScheduleRunnable(Class<?> bean, String methodName, String... params) throws Exception {
		try {
			this.target = bean.newInstance();
		} catch (Exception e) {
			throw new Exception("无法实例化类【" + bean.getName() + "】");
		}
		this.params = params;

		if (null != params && params.length > 0) {
			Method[] methods = this.target.getClass().getMethods();// 全部方法
			Class<?>[] paramTypes = null;
			for (int i = 0; i < methods.length; i++) {
				if (methodName.equals(methods[i].getName())) {// 和传入方法名匹配
					paramTypes = new Class[params.length];
					for (int j = 0; j < params.length; j++) {
						paramTypes[j] =String.class;
					}
					break;
				}
			}
			this.method = target.getClass().getDeclaredMethod(methodName,
					paramTypes);
		} else {
			this.method = target.getClass().getDeclaredMethod(methodName);
		}
	}

	@Override
	public void run() {
		try {
			ReflectionUtils.makeAccessible(method);
			if (null != params && params.length > 0) {
				method.invoke(target,params);
			} else {
				method.invoke(target);
			}
		} catch (Exception e) {
			logger.error("执行定时任务失败", e);
		}
	}

}
