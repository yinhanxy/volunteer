package com.topstar.volunteer.shiro.filter;

import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authz.AuthorizationFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.topstar.volunteer.common.Constant;
import com.topstar.volunteer.model.BaseUser;
import com.topstar.volunteer.util.ResultData;

/**
 * @title 自定义权限过滤器
 * @Description 继承shiro本来的登录过滤器，改下权限判断规则，并增加对AJAX请求的提示
 */
public class RoleAuthorizationFilter extends AuthorizationFilter{
	

	private static Logger logger = LoggerFactory.getLogger(RoleAuthorizationFilter.class);

	/**
	 * 判断是否拥有访问资源的权限
	 */
	@Override
	protected boolean isAccessAllowed(ServletRequest request,
			ServletResponse response, Object object)  throws Exception {
		
		HttpServletRequest httpReq = (HttpServletRequest) request;
		String uri = httpReq.getRequestURI();
		//根据请求响应获取已登录的Subject对象
		Subject subject = getSubject(request, response);
		BaseUser user = (BaseUser) subject.getPrincipal();
		logger.debug("userName:"+user.getUserName() + ";uri:"+uri);
		if(user.isAdmin()){
			return true;
		}
		String menuUrl = uri.substring(Constant.CONFIG_PATH.length());
		boolean isPermit = subject.isPermitted(menuUrl);
		logger.debug("userName:"+user.getUserName() + ";menuUrl:"+menuUrl);
		return isPermit;
	}

	/**
	 * 没有权限时的处理方式
	 */
	@Override
	protected boolean onAccessDenied(ServletRequest req, ServletResponse res)
			throws IOException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;
		boolean isAjaxRequest = isAjaxRequest(request);  
        if (isAjaxRequest){
        	response.reset();
        	response.setCharacterEncoding("UTF-8");
        	response.setContentType("text/json;charset=UTF-8"); 
        	response.setHeader("Cache-Control", "no-cache");
        	response.addHeader("Access-Control-Allow-Origin", "*");
        	response.addHeader("Access-Control-Allow-Headers", "x-requested-with"); 
            ResultData data = ResultData.fail();
            data.setMessage("您没有足够的权限执行该操作！");
            data.put("status", 403);
            OutputStream out= response.getOutputStream();
            out.write(data.toJSONString().getBytes("UTF-8"));
            out.close();
            return false;
        }
		return super.onAccessDenied(request, response);
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
