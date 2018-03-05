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
	<link href="${sr}css/jquery-ui.min.css" rel="stylesheet">
	<link href="${sr}css/theme-default.min.css" rel="stylesheet">
	
    <!--[if lt IE 9]>
      <script src="${sr}js/html5shiv.min.js"></script>
      <script src="${sr}js/respond.min.js"></script>
    <![endif]-->
</head>
<body class="v-page-body">
	<div class="page-container">
		<div class="panel">	
					<div>
					<table class="table table-bordered dataTable no-footer">
						<tr class="row">
							<td class="col-xs-2"><label for="inputEmail1">机构名称</label></td>
							<td class="col-xs-4">
									${org.name}
                   			</td>
                   			<%-- <td class="col-xs-2"><label for="inputEmail1">机构代码</label></td>
							<td class="col-xs-4">
									${org.code}
                   			</td> --%>
                   			<td class="col-xs-2"><label for="inputEmail1">上级机构</label></td>
							<td class="col-xs-4">
								${org.parentName}
                   			</td>
						</tr>
						<tr class="row">
							<td><label for="inputEmail2">机构类型</label></td>
							<td>
								${org.typeName}
                   			</td>
                   			<td style="vertical-align: middle;"><label for="inputEmail3">级别</label></td>
							<td>
								${org.gradeName}
                   			</td>
						</tr>
						<tr class="row">
                   			<td class="col-xs-2"><label for="inputEmail1">所属区域</label></td>
							<td class="col-xs-4">
								${org.areaName}
                   			</td>
                   			<td class="col-xs-2"><label for="inputEmail1">所属系统</label></td>
							<td class="col-xs-4">
								<select class="form-control" name="systemCode" id="systemCode" disabled="disabled" >
	           					</select>
                   			</td>
						</tr>
						
						<tr class="row">
							<td class="col-xs-2"><label for="inputEmail1">联系人</label></td>
							<td class="col-xs-4">
								${org.contact}
                   			</td>
                   			<td class="col-xs-2"><label for="inputEmail1">联系方式</label></td>
							<td class="col-xs-4">
								${org.mobile}
                   			</td>
						</tr>
						<tr class="row">
                   			<td class="col-xs-2"><label for="inputEmail1">电子邮箱</label></td>
							<td class="col-xs-4" colspan="3">
								${org.email}
                   			</td>
						</tr>
						<tr class="row">
							<td class="col-xs-2"><label for="inputEmail1">组织地址</label></td>
							<td class="col-xs-4" colspan="3">
								${org.address}
                   			</td>
                   		</tr>
						<tr class="row">
							<td style="vertical-align: middle;"><label for="inputEmail3">备注</label></td>
							<td colspan="3">
								${org.summary}
                   			</td>
						</tr>
					</table>
					</div>
		</div>
	</div>
	
	
	<script src="${sr}js/jquery.min.js" type="text/javascript"></script>
	<script src="${sr}js/config.js" type="text/javascript"></script>
	<script src="${sr}js/baseutil.js" type="text/javascript"></script>
	<script src="${sr}js/jquery.form-validator.min.js" type="text/javascript"></script>
	<script src="${sr}js/jquery-ui.min.js" type="text/javascript"></script>
	<script src="${sr}layui.js" type="text/javascript"></script>
	<script type="text/javascript">
		$(function(){
			showOrgSystem();
		});
		//显示机构系统
		function showOrgSystem(){
			var url="${webroot}org/orgSystem.do";
			postData(url, null, true, showOrgSystemResult); 
		}
		
		function showOrgSystemResult(data){
			if (data.success) {
				var html="<option value=\"\">请选择</option>";
				var list =data.orgSystemList;
				for (var i = 0; i < list.length; i++) {
					html+="<option value='"+list[i].id+"'>"+list[i].name+"</option>";
				}
				$("#systemCode").prepend(html);
			} 
			var systemCode="${org.systemCode}";
			if(systemCode){
				$("#systemCode").val(systemCode);
			}
		}
	</script>
		
</body>
</html>