<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
						<td class="col-xs-2">
							<label>名称</label>
						</td>
						<td class="col-xs-10">
							${config.name}
						</td>
					</tr>
					<tr>
						<td style="vertical-align: middle;">
							<label>内容</label>
						</td>
						<td>
							${config.content}
						</td>
					</tr>
					<tr>
						<td style="vertical-align: middle;">
							<label>说明</label>
						</td>
						<td>
							${config.remark}
						</td>
					</tr>
					<tr>
						<td>
							<label>类型</label>
						</td>
						<td>
							${config.type}
						</td>
					</tr>
					<tr>
						<td>
							<label>排序号</label>
						</td>
						<td>								
							${config.orderNo}
						</td>
					</tr>
				</table>
			</div>
		</div>
	</div>
	<!-- <div class="v-form-footer">
	   		<button type="button" class="btn btn-sm" onclick="closeFrame()" style="margin-bottom: 0px;"><i class="fa fa-remove"></i>关闭</button>
	</div> -->
	<script src="${sr}js/jquery.min.js" type="text/javascript"></script>
	<script src="${sr}js/config.js" type="text/javascript"></script>
	<script src="${sr}js/baseutil.js" type="text/javascript"></script>
	<script type="text/javascript">
		function closeFrame(){
			var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
			parent.layer.close(index);
		}
	</script>
</body>
</html>