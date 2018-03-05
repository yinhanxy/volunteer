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
	
	<link href="${sr}css/font-awesome/css/linecons.css" rel="stylesheet">
	<link href="${sr}css/font-awesome/css/font-awesome.min.css" rel="stylesheet">
    <link href="${sr}css/bootstrap.min.css" rel="stylesheet">
	<link href="${sr}css/base.css" rel="stylesheet">
	<link href="${sr}css/new.css" rel="stylesheet">
	<script src="${sr}js/jquery.min.js" type="text/javascript"></script>
	<script src="${sr}js/baseutil.js" type="text/javascript"></script>
	<link href="${sr}css/xenon.min.css" rel="stylesheet">
    <!--[if lt IE 9]>
      <script src="${sr}js/html5shiv.min.js"></script>
      <script src="${sr}js/respond.min.js"></script>
    <![endif]-->
    <style type="text/css">
    	.clear{
    		 margin-bottom: 15px;
    	}
    </style>
    <script type="text/javascript">
	    function getUserRoles(){
			var url="${webroot}user/getUserRoles.do";
			var userId=${user.id};
			var params={"userId":userId};
			postData(url, params, true, getUserRoleResult); 
		}
	    
	  //获取角色的回调函数
		function getUserRoleResult(data){
			if(data && data.success){
				var rolesHtml="";
				var roles=data.roleViewsPage.list;
				if(roles){
					for (var i = 0; i < roles.length; i++) {
						if(roles[i].isOwn){
							rolesHtml+=roles[i].roleName+",";
						}
					}
					$("#rList").prepend(rolesHtml.substring(0,rolesHtml.length-1));
				}
			}
		}
		getUserRoles();
    </script>
</head>
<body class="v-page-body">
	<div class="container">
		<div class="panel" style="padding-bottom: 0px;margin-bottom: 0px;">
			<div class="row">
				<table class="table table-bordered dataTable no-footer">
					<tr>
						<td class="col-xs-3">
					    	<label>用户名</label>
					    </td>
					    <td class="col-xs-9">
					      	${user.userName}
					    </td>
				    </tr>
					
					<tr>
						<td>				    
					    	<label>昵称</label>
					    </td>
						<td>
					      	${user.nickName}
					    </td>
				    </tr>
				    
					<tr>
						<td>
					    	<label>真实姓名</label>
					    </td>
						<td>
					      	${user.realName }
					    </td>
				    </tr>
				    <tr>
				    	<td>
					    	<label>联系电话</label>
					    </td>
					    <td>
						    ${user.mobile}
					    </td>
				    </tr>
					<tr>
						<td>				    
					    	<label>电子邮箱</label>
					    </td>
					    <td>
							${user.email }
					    </td>
				    </tr>
				    <tr>
						<td>				    
					    	<label>归属机构</label>
					    </td>
					    <td>
							${org.name }
					    </td>
				    </tr>
					<tr>
						<td>
					    	<label>角色</label>
					    </td>
					    <td id="rList">
					    </td>
				    </tr>
				</table>
			</div>
		</div>
	</div>		
	<div style="text-align: right;margin-right: 15px;bottom: 10px;right: 10px;position: fixed;">
	   		<button type="button" class="btn btn-sm" onclick="closeFrame()" style="margin-bottom: 0px;"><i class="fa fa-remove"></i>关闭</button>
	</div>
	<script src="${sr}js/config.js" type="text/javascript"></script>
	<script src="${sr}js/jquery-ui.min.js" type="text/javascript"></script>
	<script src="${sr}layui.js" type="text/javascript"></script>
	<script type="text/javascript">
		function closeFrame(){
			var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
			parent.layer.close(index);
		}
	</script>
</body>
</html>