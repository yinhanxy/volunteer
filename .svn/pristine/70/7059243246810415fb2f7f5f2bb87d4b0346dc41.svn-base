<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tps" uri="/pageTag"%>
<c:set var="webroot" value="${pageContext.request.contextPath}/" />
<%-- 静态资源绝对地址 --%>
<c:set var="sr" value="${pageContext.request.contextPath}/static/"/>
<!DOCTYPE html>
<html lang="zh-CN">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,Chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    
    <title>湖北省文化志愿者管理系统</title>
    <link href="${sr}css/bootstrap.min.css" rel="stylesheet">
	<link href="${sr}css/xenon.min.css" rel="stylesheet">
	<link href="${sr}css/base.css" rel="stylesheet">
	<link href="${sr}css/new.css" rel="stylesheet">
	<script src="${sr}js/jquery.min.js" type="text/javascript"></script>
	<script src="${sr}js/baseutil.js" type="text/javascript"></script>
    <!--[if lt IE 9]>
      <script src="${sr}js/html5shiv.min.js"></script>
      <script src="${sr}js/respond.min.js"></script>
    <![endif]-->
     
</head>
<body class="v-page-body">
	<div class="page-container ">
		<div class="panel" style="display: none" id="mainform">
			<form id="editUserForm" action="${webroot}userInfo/editUserInfo.do" >
			<div class="row">
				<input type="hidden" name="id" value="${user.id}">
				<table class="table table-bordered dataTable no-footer">
					<tr>
						<td class="col-xs-3">
					    	<label>用户名：</label>
					    </td>
					    <td class="col-xs-9">
					      	${user.userName}
					    </td>
				    </tr>
					
					<tr>
						<td>				    
					    	<label>昵称：</label>
					    </td>
						<td>
					      	${user.nickName}
					    </td>
				    </tr>
				    
					<tr>
						<td>
					    	<label>真实姓名：</label>
					    </td>
						<td>
					      	${user.realName }
					    </td>
				    </tr>
				    <tr>
				    	<td>
					    	<label>联系电话：</label>
					    </td>
					    <td>
						    ${user.mobile}
					    </td>
				    </tr>
					<tr>
						<td>				    
					    	<label>电子邮箱：</label>
					    </td>
					    <td>
							${user.email }
					    </td>
				    </tr>
					<tr>
						<td>
					    	<label>角色：</label>
					    </td>
					    <td>
					    	${rolesName } 
					    </td>
				    </tr>
				</table>
				</div>
				
				</form>
			</div>
		</div>
	<script src="${sr}js/jquery-ui.min.js" type="text/javascript"></script>
	<script src="${sr}layui.js" type="text/javascript"></script>
	<script type="text/javascript">
	layui.use(['jquery','layer','laytpl'], function(){
		$("#mainform").show();
	});
	
	//关闭
	function closeFrame(){
		var index = parent.layer.getFrameIndex(window.name);//先得到当前iframe层的索引
		parent.layer.close(index);
	}
	
	</script>
</body>
</html>