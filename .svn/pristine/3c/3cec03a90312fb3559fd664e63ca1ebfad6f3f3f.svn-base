<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page import="com.topstar.volunteer.common.AlternativeUtil"%>
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
	<link href="${sr}css/xenon.min.css" rel="stylesheet">
	<link href="${sr}css/base.css" rel="stylesheet">
	<link href="${sr}css/new.css" rel="stylesheet">
	<link href="${sr}css/theme-default.min.css" rel="stylesheet">
	<%-- <link href="${sr}css/xenon-forms.css" rel="stylesheet"> --%>
    <!--[if lt IE 9]>
      <script src="${sr}js/html5shiv.min.js"></script>
      <script src="${sr}js/respond.min.js"></script>
    <![endif]-->
    <style type="text/css">
    	label {
    		display: inline;
    	}
    	.checkbox >label{
    		padding-right: 10px
    	}
    	#photo {
		    border-width: 0px;
		    //position: absolute;
		    left: 0px;
		    top: 0px;
		    padding-bottom:5px;
		    width: 123px;
		    height: 150px;
		}
    </style>
</head>
<body class="v-page-body">
		<div class="panel" style="margin-bottom: 0;padding-top: 10px;" id="mainform" >
			
				<div class="row mn5">
					<table class="table table-bordered dataTable no-footer">
						<tr>
							<td class="col-xs-1">
						    	<label >姓名</label>
						    </td>
						    <td class="col-xs-2">
						    	${volunteerView.userName}
						    </td>
						    <td class="col-xs-1">
						    	<label >性别</label>
						    </td>
						    <td class="col-xs-2">
						    	${volunteerView.sex}
						    </td>
					    </tr>
				    	<tr>
							<td class="col-xs-1">
						    	<label >证件号</label>
						    </td>
						    <td class="col-xs-2">
						    	${volunteerView.idcard}
						    </td>
						    <td class="col-xs-1">
						    	<label >所属服务队</label>
						    </td>
						    <td class="col-xs-2">
						    	${volunteerView.serviceTeam}
						    </td>
				    	 </tr>
				    	 <tr>
							<td class="col-xs-1">
						    	<label >操作人</label>
						    </td>
						    <td class="col-xs-2">
						    	${volunteerView.blackOperateUser}
						    </td>
						    <td class="col-xs-1">
						    	<label >加入黑名单时间</label>
						    </td>
						    <td class="col-xs-2">
						    	<fmt:formatDate value="${volunteerView.blackOperateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
						    </td>
				    	 </tr>
				    	 <tr>
							<td class="col-xs-1" >
						    	<label >操作服务队</label>
						    </td>
						    <td class="col-xs-2">
						    	${serName}
						    </td>
						    <td class="col-xs-1">
						    </td>
						    <td class="col-xs-2">
						    </td>
				    	 </tr>
				    	  <tr >
							<td class="col-xs-1" style="vertical-align:middle">
						    	<label >拉黑原因</label>
						    </td>
						    <td class="col-xs-2" colspan="3">
								<textarea style="width: 100%;height: 100px" readonly="readonly">${volunteerView.blackOperateReason}</textarea>
						    </td>
				    	 </tr>
					</table>
				</div>
		</div>
	<script src="${sr}js/jquery.min.js" type="text/javascript"></script>
	<script src="${sr}js/config.js" type="text/javascript"></script>
	<script src="${sr}js/baseutil.js" type="text/javascript"></script>
	<script src="${sr}js/jquery.form-validator.min.js" type="text/javascript"></script>
	<script src="${sr}js/jquery-ui.min.js" type="text/javascript"></script>
	<script src="${sr}layui.js" type="text/javascript"></script>
	<script src="${sr}js/select2.js" type="text/javascript"></script>
	
	<script type="text/javascript">
	
	function closeFrame(){
		var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
		parent.layer.close(index);
	}
	
	</script>
</body>
</html>