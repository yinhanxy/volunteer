<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
	<link href="${sr}css/font-awesome/css/linecons.css" rel="stylesheet">
	<link href="${sr}css/font-awesome/css/font-awesome.min.css" rel="stylesheet">
    <link href="${sr}css/bootstrap.min.css" rel="stylesheet">
	<link href="${sr}css/xenon.min.css" rel="stylesheet">
	<link href="${sr}css/base.css" rel="stylesheet">
	<link href="${sr}css/new.css" rel="stylesheet">
	<link href="${sr}css/jquery-ui.min.css" rel="stylesheet">
	<link href="${sr}css/theme-default.min.css" rel="stylesheet">
	<link href="${sr}ztree/metroStyle/style.css" rel="stylesheet">
	<link href="${sr}css/jquery.mCustomScrollbar.min.css" rel="stylesheet">
    <!--[if lt IE 9]>
      <script src="${sr}js/html5shiv.min.js"></script>
      <script src="${sr}js/respond.min.js"></script>
    <![endif]-->
    <style type="text/css">
		ul.ztree {margin-top: 10px;border: 1px solid #ccc;border-radius:4px;background: #f0f6e4;width:220px;height:320px;overflow-x:auto;}
		.ztree span{display:inline-block;}
    </style>
</head>
<body class="v-page-body">
	<div class="page-container">
		<div class="panel">
				<div class="row">
				<input type="hidden" name="jobId" value="${schedule.jobId }">
				<table class="table table-bordered dataTable no-footer">
					<tr>
						<td class="col-xs-2"><label for="inputEmail1">任务ID</label></td>
						<td class="col-xs-10">${log.jobId }
                  		</td>
					</tr>
					<tr>
						<td class="col-xs-2"><label for="inputEmail1">任务名称</label></td>
						<td class="col-xs-10">${log.jobName }
                  		</td>
					</tr>
					<tr>
						<td class="col-xs-2"><label for="inputEmail1">JAVA BEAN</label></td>
						<td class="col-xs-10">
							${log.beanName}
                  			</td>
					</tr>
					
					<tr>
						<td><label for="inputEmail2">参数值<small></small></label></td>
						<td >
						${log.params }
                  		</td>
					</tr>
					<tr>
						<td class="col-xs-2"><label for="inputEmail2">开始时间</label></td>
						<td class="col-xs-10">
						<fmt:formatDate value="${log.beginTime }" type="both"/>
                  		</td>
					</tr>
					<tr>
						<td class="col-xs-2"><label for="inputEmail2">结束时间</label></td>
						<td class="col-xs-10">
							<fmt:formatDate value="${log.endTime }" type="both"/>
							
                  		</td>
					</tr>
					<tr>
						<td class="col-xs-2"><label for="inputEmail2">耗时(毫秒)</label></td>
						<td class="col-xs-10">
							${log.useTime }
                  		</td>
					</tr>
					<tr>
						<td class="col-xs-2"><label for="inputEmail2">执行状态</label></td>
						<td class="col-xs-10">
							${log.statusName }
                  		</td>
					</tr>
					<tr>
						<td class="col-xs-2"><label for="inputEmail2">错误信息</label></td>
						<td class="col-xs-10">
							${log.message }
                  		</td>
					</tr>
				</table>
				</div>
		</div>		
	</div>
	
	<div class="v-form-footer">
	   	<button type="button" class="btn btn-sm" onclick="closeFrame()"><i class="fa fa-remove"></i>关闭</button>
	  	<button type="button" id="sub" class="btn btn-sm btn-success"><i class="fa fa-plus"></i>返回</button>
	</div>
	

	
	<script src="${sr}js/jquery.min.js" type="text/javascript"></script>
	<script src="${sr}js/config.js" type="text/javascript"></script>
	<script src="${sr}js/baseutil.js" type="text/javascript"></script>
	<script src="${sr}js/jquery.form-validator.min.js" type="text/javascript"></script>
	<script src="${sr}js/jquery-ui.min.js" type="text/javascript"></script>
	<script src="${sr}layui.js" type="text/javascript"></script>
	<script src="${sr}ztree/jquery.ztree.core.js" type="text/javascript"></script>
	<script src="${sr}js/jquery.mousewheel-3.0.6.min.js" type="text/javascript"></script>
	<script src="${sr}js/jquery.mCustomScrollbar.min.js" type="text/javascript"></script>
	<script type="text/javascript">
	layui.use(['jquery','layer','laytpl'],function (){
		$("#sub").click(function(){
			history.back();
		})
	});


	function closeFrame(){
		var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
		parent.layer.close(index);
	}
	</script>
</body>
</html>