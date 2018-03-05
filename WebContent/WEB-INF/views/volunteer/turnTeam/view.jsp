<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
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
	<link href="${sr}css/select2-bootstrap.min.css" rel="stylesheet">
	<link href="${sr}css/select2.min.css" rel="stylesheet"> 
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
		<div class="panel" style="margin-bottom: 0;padding-top: 10px;" id="mainform">
			
				<div class="row mn5">
					<table class="table table-bordered dataTable no-footer">
						<tr>
							<td class="col-xs-2">
						    	<label >姓名</label>
						    </td>
						    <td class="col-xs-4">
						    	${turnTeam.realName}
						    </td>
						    <td class="col-xs-2">
						    	<label >证件号</label>
						    </td>
						    <td class="col-xs-4">
						    	${turnTeam.idcard}
						    </td>
					    </tr>
				    	<tr>
							<td class="col-xs-2">
						    	<label >性别</label>
						    </td>
						    <td class="col-xs-4">
						    	${turnTeam.sex}
						    </td>
						    <td class="col-xs-2">
						    	<label >出生年月</label>
						    </td>
						    <td class="col-xs-4">
						    	<fmt:formatDate value="${turnTeam.birthday}" pattern="yyyy-MM-dd"/>
						    </td>
				    	 </tr>
				    	 <tr>
							<td class="col-xs-2">
						    	<label >所属服务队</label>
						    </td>
						    <td class="col-xs-4">
						    	${turnTeam.sourceTeam}
						    </td>
						    <td class="col-xs-2">
						    	<label >注册时间</label>
						    </td>
						    <td class="col-xs-4">
						    	<fmt:formatDate value="${turnTeam.regTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
						    </td>
				    	 </tr>
				    	 <tr>
							<td class="col-xs-1">
						    	<label >服务时长</label>
						    </td>
						    <td class="col-xs-2">
								${empty turnTeam.serviceHour?0:turnTeam.serviceHour}小时
						    </td>
						    <td class="col-xs-1">
						    	<label >申请转队时间</label>
						    </td>
						    <td class="col-xs-2">
						    	<fmt:formatDate value="${turnTeam.applyTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
						    </td>
				    	 </tr>
				    	 <tr>
							<td class="col-xs-1">
						    	<label >申请转入服务队</label>
						    </td>
						    <td class="col-xs-2">
						    	${turnTeam.targetTeam}
						    </td>
						    <td class="col-xs-1">
						    	<label >转入服务队属性</label>
						    </td>
						    <td class="col-xs-2">
						    	${turnTeam.property=='2'?"跨平台":"本平台"}
						    </td>
				    	 </tr>
				    	 <tr>
							<td class="col-xs-1" style="vertical-align: middle;">
						    	<label >转队原因</label>
						    </td>
						    <td class="col-xs-2" colspan="3">
						    	${turnTeam.applyReason}
						    </td>
				    	 </tr>
					</table>
				</div>
				<div class="row">
					<table class="table table-bordered dataTable no-footer">
						<tr>
							<th colspan="4" style="text-align:center;background-color:rgba(248, 248, 248, 1)">
						       	转队处理信息
						     </th>
				    	 </tr>
				    	 <c:if test="${empty turnTeam.sourceResult}">
				    	 	<tr style="text-align:center;">
				    	 		<td colspan="4">无</td>
				    	 	</tr>
				    	 </c:if>
				    	 <c:if test="${!empty turnTeam.sourceResult}">
						<tr>
							<td class="col-xs-2">
						    	<label >处理人</label>
						    </td>
						    <td class="col-xs-4">
						    	${turnTeam.sdealUser}
						    </td>
						    <td class="col-xs-2">
						    	<label >处理服务队</label>
						    </td>
						    <td class="col-xs-4">
						    	${turnTeam.sdealTeam}
						    </td>
					    </tr>
					    <tr>
							<td class="col-xs-1">
						    	<label >处理时间</label>
						    </td>
						    <td class="col-xs-2">
						    	<fmt:formatDate value="${turnTeam.sdealTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
						    </td>
						    <td class="col-xs-1">
						    	<label >结果</label>
						    </td>
						    <td class="col-xs-2">
						    	${turnTeam.exitResult}
						    </td>
					    </tr>
				    	 <tr>
							<td class="col-xs-1">
						    	<label >处理意见</label>
						    </td>
						    <td class="col-xs-2" colspan="3">
						    	${turnTeam.sourceOpinion}
						    </td>
				    	 </tr>
				    	 </c:if>
				    	  <c:if test="${!empty turnTeam.targetResult}">
						<tr>
							<td class="col-xs-1">
						    	<label >处理人</label>
						    </td>
						    <td class="col-xs-2">
						    	${turnTeam.tdealUser}
						    </td>
						    <td class="col-xs-1">
						    	<label >处理服务队</label>
						    </td>
						    <td class="col-xs-2">
						    	${turnTeam.tdealTeam}
						    </td>
					    </tr>
					    <tr>
							<td class="col-xs-1">
						    	<label >处理时间</label>
						    </td>
						    <td class="col-xs-2">
						    	<fmt:formatDate value="${turnTeam.tdealTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
						    </td>
						    <td class="col-xs-1">
						    	<label >结果</label>
						    </td>
						    <td class="col-xs-2">
						    	${turnTeam.addResult}
						    </td>
					    </tr>
				    	 <tr>
							<td class="col-xs-1">
						    	<label >处理意见</label>
						    </td>
						    <td class="col-xs-2" colspan="3">
						    	${turnTeam.targetOpinion}
						    </td>
				    	 </tr>
				    	 </c:if>
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