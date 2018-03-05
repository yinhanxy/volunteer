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
						    	${retreatTeam.realName}
						    </td>
						    <td class="col-xs-2">
						    	<label >证件号</label>
						    </td>
						    <td class="col-xs-4">
						    	${retreatTeam.idcard}
						    </td>
					    </tr>
				    	<tr>
							<td class="col-xs-2">
						    	<label >性别</label>
						    </td>
						    <td class="col-xs-4">
						    	${retreatTeam.sex}
						    </td>
						    <td class="col-xs-2">
						    	<label >出生年月</label>
						    </td>
						    <td class="col-xs-4">
						    	<fmt:formatDate value="${retreatTeam.birthday}" pattern="yyyy-MM-dd"/>
						    </td>
				    	 </tr>
				    	 <tr>
							<td class="col-xs-2">
						    	<label >所属服务队</label>
						    </td>
						    <td class="col-xs-4">
						    	${retreatTeam.serTeamName}
						    </td>
						    <td class="col-xs-2">
						    	<label >注册时间</label>
						    </td>
						    <td class="col-xs-4">
						    	<fmt:formatDate value="${retreatTeam.regTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
						    </td>
				    	 </tr>
				    	 <tr>
							<td class="col-xs-1">
						    	<label >服务时长</label>
						    </td>
						    <td class="col-xs-2">
								${retreatTeam.serviceHour}小时
						    </td>
						    <td class="col-xs-1">
						    	<label >发证时间</label>
						    </td>
						    <td class="col-xs-2">
						    	<fmt:formatDate value="${retreatTeam.certTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
						    </td>
				    	 </tr>
				    	 <tr>
							<td class="col-xs-1">
						    	<label >申请退队时间</label>
						    </td>
						    <td class="col-xs-2">
								<fmt:formatDate value="${retreatTeam.applyTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
						    </td>
						    <td class="col-xs-1">
						    	<label ></label>
						    </td>
						    <td class="col-xs-2">
						    	
						    </td>
				    	 </tr>
				    	 <tr>
							<td class="col-xs-1" style="vertical-align: middle;height: 80px">
						    	<label >退队原因</label>
						    </td>
						    <td class="col-xs-2" colspan="3">
						    	${retreatTeam.retreatReason}
						    </td>
				    	 </tr>
					</table>
				</div>
				<div class="row">
					<form id="dealForm" action="${webroot}retreatTeam/deal.do" method="POST">
					<input type="hidden" name="id" value="${retreatTeam.dealId}" />
					<input type="hidden" name="dealStatus" id="dealStatus"/>
					<input type="hidden" name="volunteerId"  id="volunteerId" value="${retreatTeam.id}"/>
					<table class="table table-bordered dataTable no-footer">
						<tr>
							<th colspan="4" style="text-align:center;background-color:rgba(248, 248, 248, 1)">
						       	处理意见
						     </th>
				    	 </tr>
						<tr>
						    <td class="col-xs-4" colspan="4">
						    	<textarea rows="4"  style="width:100%;height:100%" name="dealOpinion"></textarea>
						    </td>
					    </tr>
					</table>
					</form>
				</div>
				<div class="v-form-footer" style="margin-bottom: 8px;">
				  			<button type="button" class="btn btn-sm btn-success" onclick="passVolunteer()"><i class="fa fa-check-circle"></i>通过</button>
				   			<button type="button" class="btn btn-danger btn-sm" onclick="denyVolunteer()"><i class="fa fa-ban"></i>不通过</button>
				   			<button type="button" class="btn btn-sm" onclick="closeFrame()"><i class="fa fa-remove"></i>关闭</button>
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
	layui.use(['jquery','layer','laytpl'], function(){
		var layer = layui.layer;
	});
	function closeFrame(){
		var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
		parent.layer.close(index);
	}
	
	//通过志愿者
	function passVolunteer(){
		var layer = layui.layer;
		$("#dealStatus").val("1");
		layer.confirm('确定要审核通过指定的志愿者吗？', {icon: 3, title:'系统提示',
			  btn: ['确定','取消'] //按钮
			},
		function(){
				postFormData("dealForm",true,dealResult);
			},
		function(){
				  
			})
	}
	
	//不通过志愿者
	function denyVolunteer(){
		var layer = layui.layer;
		$("#dealStatus").val("0");
		layer.confirm('确定要驳回指定的志愿者吗？', {icon: 3, title:'系统提示',
			  btn: ['确定','取消'] //按钮
			},
		function(){
				postFormData("dealForm",true,dealResult);
			},
		function(){
				  
			})
	}
	
	function dealResult(data){
		var laytpl = layui.laytpl;
		var layer = layui.layer;
		var message = data.message;
		if (data.success) {
			layer.msg(message, {time: 1000});
			parent.loadPage();
			closeFrame();
		}else{
			layer.msg(message, {time: 1000});
		}
		
	}
	</script>
</body>
</html>