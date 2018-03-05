<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="webroot" value="${pageContext.request.contextPath}/" />
<%-- 静态资源绝对地址 --%>
<c:set var="sr" value="${pageContext.request.contextPath}/static/"/>
<!DOCTYPE html>
<html lang="zh-CN">
  <head>
  
    <jsp:include page="include/title.jsp"></jsp:include>
	
	<link href="${sr}css/font-awesome/css/linecons.css" rel="stylesheet">
	<link href="${sr}css/font-awesome/css/font-awesome.min.css" rel="stylesheet">
    <link href="${sr}css/bootstrap.min.css" rel="stylesheet">
	<link href="${sr}css/xenon.min.css" rel="stylesheet">
	<link href="${sr}css/base.css" rel="stylesheet">
	<link href="${sr}css/new.css" rel="stylesheet">
    <!--[if lt IE 9]>
      <script src="${sr}js/html5shiv.min.js"></script>
      <script src="${sr}js/respond.min.js"></script>
    <![endif]-->
	</head>
	<body class="page-body skin-facebook">
		<div class="page-container">
	        <div class="main-content" style="height: 100%;margin-left: 10px;">
	        	<div class="page-container" style="width: 100%;margin-left: 10px;margin-right: 10px;">
	        		<div class="row">
	        			您没有足够的权限执行该操作！
	        		</div>
	        		<div class="panel-heading">
					 	<div class="btn-toolbar">
	            			<button type="button" class="btn btn-sm" onclick="closeFrame()"><i class="glyphicon glyphicon-remove"></i>关闭</button>
	                 	</div>
	                 </div>
				</div>
				
			</div>
		</div>
	    
	    <script src="${sr}js/jquery.min.js" type="text/javascript"></script>
	    <script src="${sr}js/config.js" type="text/javascript"></script>
	    <script src="${sr}js/baseutil.js" type="text/javascript"></script>
	    <script src="${sr}layui.js" type="text/javascript"></script>
	    <script type="text/javascript">
	    layui.use(['layer'], function(){
		});
		function closeFrame(){
			var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
			parent.layer.close(index);
		}
		</script>
	</body>
</html>