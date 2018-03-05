<%@page import="org.apache.commons.lang3.StringUtils"%>
<%@page import="org.slf4j.Logger"%>
<%@page import="org.slf4j.LoggerFactory"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" isErrorPage="true" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="webroot" value="${pageContext.request.contextPath}/" />
<%!
    private  Logger logger = LoggerFactory.getLogger(this.getClass());
%>
<%
    String message = "";
    if(exception != null){
        logger.error("访问出现错误", exception);
    }
    if(StringUtils.isBlank(message)){
    	message = "您所访问的页面出错了。";
    }
%>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=Edge">
<title>湖北省文化志愿者管理系统</title>
<link rel="stylesheet" type="text/css" href="${webroot}static/images/qk_bsdt_style.css">
<script src="${webroot}static/images/jquery-1.8.3.min.js" type="text/javascript"></script>
<script src="${webroot}static/images/baseutil.js" type="text/javascript"></script>
<script src="${webroot}static/images/tab.js" type="text/javascript"></script>
</head>
<body>

	<div class="cwym_bg">
	    <div class="w1200">
	        <div class="cwym_cw">
	            <img src="${webroot}static/images/qk_cwym_cw.png">
	        </div>
	        <div class="cwym_wz">
	            <p class="cwym_bt" style="width: 500px;"><img src="${webroot}static/images/qk_cwym_wz.png"><%=message%></p>
	            <p><img src="${webroot}static/images/qk_cwym_jt.png"><a  href="javascript:void(0)" onclick="goToRoot('${webroot}')">返回首页</a></p>
	            <p><img src="${webroot}static/images/qk_cwym_jt.png"><a  href="javascript:void(0)" onclick="window.close();">关闭页面</a></p>
	            <p class="pad_l20">如有任何意见或建议，请您及时反馈给我们</p>
	        </div>
	    </div>
	    
	</div>
</body>
</html>