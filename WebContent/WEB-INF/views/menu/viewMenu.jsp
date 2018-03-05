<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="tps" uri="/pageTag"%>
<c:set var="webroot" value="${pageContext.request.contextPath}/" />
<%-- 静态资源绝对地址 --%>
<c:set var="sr" value="${pageContext.request.contextPath}/static/"/>
<!DOCTYPE html>
<html lang="zh-CN">
  <head>
    <meta charset="utf-8" >
    <meta http-equiv="X-UA-Compatible" content="IE=edge,Chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    
    <title>湖北省文化志愿者管理系统</title>
	
	<link href="${sr}css/font-awesome/css/linecons.css" rel="stylesheet">
	<link href="${sr}css/font-awesome/css/font-awesome.min.css" rel="stylesheet">
    <link href="${sr}css/bootstrap.min.css" rel="stylesheet">
    <link href="${sr}css/xenon.min.css" rel="stylesheet">
	<link href="${sr}css/base.css" rel="stylesheet">
	<link href="${sr}css/new.css" rel="stylesheet">
	<link href="${sr}css/theme-default.min.css" rel="stylesheet">

    <!--[if lt IE 9]>
      <script src="${sr}js/html5shiv.min.js"></script>
      <script src="${sr}js/respond.min.js"></script>
    <![endif]-->
</head>
<body class="v-page-body">
	<div class="page-container">
		<div class="panel" style="padding-bottom: 0px;margin-bottom: 0px;">
			<div class="row">
				<table class="table table-bordered dataTable no-footer">
				    <tr>
						<td class="col-xs-2"><label for="inputEmail1">菜单名称</label></td>
						<td class="col-xs-3">
							${menu.menuName}
					    </td>
						<td class="col-xs-2"><label for="inputEmail2">菜单描述</label></td>
						<td class="col-xs-3">${menu.menuDesc }</td>
					</tr>
					<tr>
						<td style="vertical-align: middle;" ><label for="inputEmail3">菜单URL</label></td>
						<td colspan="3">${menu.url}</td>
					</tr>
					 <tr>
						<td style="vertical-align: middle;"><label for="inputEmail3">菜单类型</label></td>
						<td><c:if test="${menu.menuType==0}">菜单</c:if><c:if test="${menu.menuType==1}">按钮</c:if></td>

						<td style="vertical-align: middle;"><label for="inputEmail3">菜单图标</label></td>
						<td><i class="fa ${menu.icon}"></i></td>
					</tr>
					<tr>
						<td style="vertical-align: middle;"><label for="inputEmail3">是否显示</label></td>
						<td><c:if test="${menu.isShow==0}">不</c:if>可见</td>
		
						<td style="vertical-align: middle;"><label for="inputEmail3">排序号</label></td>
						<td>${menu.orderNo}</td>
					</tr>
					<tr>
						<td style="vertical-align: middle;"><label for="inputEmail3">创建用户</label></td>
						<td>${menu.crUser}</td>
		
						<td style="vertical-align: middle;"><label for="inputEmail3">创建时间</label></td>
						<td><fmt:formatDate value="${menu.crTime}" pattern="yyyy-MM-dd HH:mm:ss"/> </td>
					</tr>
				    </table>
				</div>
		</div>
	</div>
	
	<script src="${sr}js/jquery.min.js" type="text/javascript"></script>
	<script src="${sr}js/config.js" type="text/javascript"></script>
	<script src="${sr}js/baseutil.js" type="text/javascript"></script>
	<script src="${sr}js/jquery-ui.min.js" type="text/javascript"></script>

	<script src="${sr}layui.js" type="text/javascript"></script>
	<script type="text/javascript">
	layui.use(['jquery','layer','laytpl'], function(){
		function closeFrame(){
			var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
			parent.layer.close(index);
		}
	});
	
	</script>
</body>
</html>