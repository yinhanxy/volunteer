package com.topstar.volunteer.shiro.filter;

import java.io.OutputStream;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.web.filter.authc.UserFilter;

import com.topstar.volunteer.util.ResultData;

/**
 * 自定义登录过滤器<br/>
 * 继承shiro本来的登录过滤器，并增加AJAX的提示
 */
public class LoginFilter extends UserFilter {

	/**
	 * 验证是否登录
	 */
	@Override
	protected boolean isAccessAllowed(ServletRequest req,
			ServletResponse res, Object mappedValue) {
		return super.isAccessAllowed(req, res, mappedValue);
	}

	/**
	 * 未登录的处理方法
	 */
	@Override
	protected boolean onAccessDenied(ServletRequest req,
			ServletResponse res) throws Exception {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;
		boolean isAjaxRequest = isAjaxRequest(request);
        if (isAjaxRequest){
        	response.reset();
        	response.setCharacterEncoding("UTF-8");
        	response.setContentType("text/xml;charset=UTF-8"); 
        	response.setHeader("Cache-Control", "no-cache");
        	response.addHeader("Access-Control-Allow-Origin", "*");
        	response.addHeader("Access-Control-Allow-Headers", "x-requested-with"); 
            ResultData data = ResultData.fail();
            data.setMessage("您未登录或登录状态失效，请重新登录！");
            data.put("status", 401);
            OutputStream out= response.getOutputStream();
            out.write(data.toJSONString().getBytes("UTF-8"));
            out.close();
            return false;
        }
		return super.onAccessDenied(req, res);
	}

	/** 判断是否为Ajax请求
     * @param request HttpServletRequest
     * @return 是：true；否：false  
     */  
    public static boolean isAjaxRequest(HttpServletRequest request){  
        String header = request.getHeader("X-Requested-With");   
        if (header != null && "XMLHttpRequest".equals(header)){
           return true;
        }else{
           return false;
        }
    }
	
	
}
