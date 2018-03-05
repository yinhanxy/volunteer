<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="webroot" value="${pageContext.request.contextPath}/" />
<%-- 静态资源绝对地址 --%>
<c:set var="sr" value="${pageContext.request.contextPath}/static/"/>
<!DOCTYPE html>
<html lang="zh-CN">
  <head>
  
    <jsp:include page="include/title.jsp"></jsp:include>

    <link href="${sr}css/font-awesome/css/font-awesome.min.css" rel="stylesheet">
	<link href="${sr}css/newlogin.css" rel="stylesheet">
    <!--[if lt IE 9]>
      <script src="${sr}js/html5shiv.min.js"></script>
      <script src="${sr}js/respond.min.js"></script>
    <![endif]-->
  </head>
  <body>
  	
  	<div class="main">
		<a href=""><img src="${sr}images/login_logo.png" alt="湖北省文化志愿者管理系统"></a>
		<form id="loginForm">
		<div class="deng">
			<div class="inp">
				<input type="text" id="username" name="username" placeholder="用户名" class="inp1">
				<span><img src="${sr}images/login_user.png" alt="请输入用户名"></span>
			</div>
			<div class="inp">
				<input type="password" id="password" name="password" placeholder="密码">
				<span class="inp2"><img src="${sr}images/login_pass.png" alt="请输入密码"></span>
			</div>
			<div class="inp">
				<input type="text" name="validateCode" id="validateCode" placeholder="验证码" class="inp3 fl">
				<div   onclick="setRom()" class="fr"><img id="verify" src="${webroot}verify?c=login" alt="重新获取验证码"></div>
				<input type="hidden" name="codeType" value="login"/>
			</div>
			<div style="clear:both"></div>
			<button id="button">立即登录</button>
		</div>
		</form>
	</div>

    <script src="${sr}js/jquery.min.js"> type="text/javascript"</script>
    <script src="${sr}js/bootstrap.min.js" type="text/javascript"></script>
    <script src="${sr}js/jquery.placeholder.js" type="text/javascript"></script>
    <script src="${sr}js/baseutil.js" type="text/javascript"></script>
    <script src="${sr}js/config.js" type="text/javascript"></script>
    <script src="${sr}layui.js" type="text/javascript"></script>
    <script src="${sr}js/login.js" type="text/javascript"></script>
    <script type="text/javascript">
	
    layui.use(['layer'], function(){
    	$('input').placeholder(); 
	});
    	
	function showMessage(type, content) {
        var layer = layui.layer;
        layer.alert(content, {icon:2});
    }
	
	function setRom(){
		var $verify=$("#verify");
		$verify.attr("src","${webroot}verify?c=login&t="+new Date().getTime());
	}
	</script>
  </body>
</html>
