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
    	select,select2-container input[type="text"],.uneditable-input{display:inline-block;height:20px;padding:4px 4px;margin-bottom:8px;font-size:14px;line-height:20px;color:#555555;-webkit-border-radius:4px;-moz-border-radius:4px;border-radius:4px;vertical-align:middle;}
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
							<td class="col-xs-1">
						    	<label >姓名</label>
						    </td>
						    <td class="col-xs-2">
						    	${volunteer.realName}
						    </td>
						    <td class="col-xs-1">
						    	<label >性别</label>
						    </td>
						    <td class="col-xs-2">
						    	${volunteer.sex}
						    </td>
						    <td class="col-xs-1" rowspan="4" style="vertical-align:middle">
						    	<label >照片</label>
						    </td>
						    <td class="col-xs-2" rowspan="4" style="" align="center">
						    	<span id="content" style='${!empty volunteer.imgUrl?"display:none":""}'><img width="164" height="150" border="0" src="${sr}images/photo.png" style="margin-bottom: 5px ;"></span>
								<img id="pic" alt="志愿者头像" width="124" height="150" border="0" src="${webroot}file/readPic.do?ataUrl=${volunteer.imgUrl}" style="margin-bottom: 5px ;${empty volunteer.imgUrl?'display:none':''}" >
						    </td>
					    </tr>
				    	<tr>
							<td class="col-xs-1">
						    	<label >身份证号</label>
						    </td>
						    <td class="col-xs-2">
						    	${volunteer.idcard}
						    </td>
						    <td class="col-xs-1">
						    	<label >手机号</label>
						    </td>
						    <td class="col-xs-2">
						    	${volunteer.mobile}
						    </td>
				    	 </tr>
				    	 <tr>
							<td class="col-xs-1">
						    	<label >民族</label>
						    </td>
						    <td class="col-xs-2">
						    	${volunteer.nationName}
						    </td>
						    <td class="col-xs-1">
						    	<label >政治面貌</label>
						    </td>
						    <td class="col-xs-2">
						    	${volunteer.polstatusName}
						    </td>
				    	 </tr>
				    	 <tr>
							<td class="col-xs-1">
						    	<label >籍贯</label>
						    </td>
						    <td class="col-xs-2">
						    	${volunteer.naplace}
						    </td>
						    <td class="col-xs-1">
						    	<label >出生日期</label>
						    </td>
						    <td class="col-xs-2">
						    	<fmt:formatDate value="${volunteer.birthday}" pattern="yyyy-MM-dd HH:mm:ss"/>
						    </td>
				    	 </tr>
				    	 <tr>
							<td class="col-xs-1">
						    	<label >学历</label>
						    </td>
						    <td class="col-xs-2">
						    	${volunteer.educationName}
						    </td>
						    <td class="col-xs-1">
						    	<label >毕业学校</label>
						    </td>
						    <td class="col-xs-2">
						    	${volunteer.grasch}
						    </td>
						    <td class="col-xs-1">
						    	<label >所学专业</label>
						    </td>
						    <td class="col-xs-2">
						    	${volunteer.profession}
						    </td>
				    	 </tr>
				    	 <tr>
							<td class="col-xs-1">
						    	<label >居住地址</label>
						    </td>
						    <td class="col-xs-2" colspan="3">
						    	${volunteer.address}   
						    </td>
						    <td class="col-xs-1">
						    	<label >邮政编码</label>
						    </td>
						    <td class="col-xs-2">
						    	${volunteer.postcode}
						    </td>
				    	 </tr>
				    	 <tr>
							<td class="col-xs-1">
						    	<label >工作单位</label>
						    </td>
						    <td class="col-xs-2">
						    	${volunteer.work}
						    </td>
						    <td class="col-xs-1">
						    	<label >工作地址</label>
						    </td>
						    <td class="col-xs-2">
						    	${volunteer.jobAddress}
						    </td>
						    <td class="col-xs-1">
						    	<label >电子邮箱</label>
						    </td>
						    <td class="col-xs-2">
						    	${volunteer.email}
						    </td>
				    	 </tr>
				    	 <tr>
							<td class="col-xs-1">
						    	<label >职称</label>
						    </td>
						    <td class="col-xs-2">
						    	${volunteer.jobTitleName}
						    </td>
						    <td class="col-xs-1">
						    	<label >职务</label>
						    </td>
						    <td class="col-xs-2">
						   		${volunteer.duties}
						    </td>
						    <td class="col-xs-1">
						    	<label >QQ号码</label>
						    </td>
						    <td class="col-xs-2">
						    	${volunteer.qq}
						    </td>
				    	 </tr>
				    	 <tr>
							<td class="col-xs-1">
						    	<label >擅长技能<small ><code>*</code></small></label>
						    </td>
						    <td class="col-xs-2" colspan="5">
						    	${volunteer.specility}
						    </td>
				    	 </tr>
				    	 <tr >
							<td class="col-xs-1" style="vertical-align:middle">
						    	<label >志愿服务经历</label>
						    </td>
						    <td class="col-xs-2" colspan="5" style="padding-bottom: 0">
								<textarea  style="width: 100%;height: 100px" readonly="readonly">${volunteer.serviceExperiment}</textarea>
						    </td>
				    	 </tr>
				    	 <tr >
							<td class="col-xs-1" style="vertical-align:middle">
						    	<label >志愿服务队</label>
						    </td>
						    <td class="col-xs-2" colspan="5" style="padding-bottom: 0">
								${serName}
						    </td>
				    	 </tr>
				    	 <tr >
							<td class="col-xs-1" style="vertical-align:middle">
						    	<label >意向服务时间</label>
						    </td>
						    <td class="col-xs-2" colspan="5">
      					        <div class="checkbox">
								    <label>
								      <input type="checkbox" value="oneA" style="margin-top: 8px;" name="serviceTime" disabled="disabled"
								     	${fn:contains(volunteer.serviceTime,"oneA")? "checked":""}>周一上午
								    </label>
								    
								    <label>
								      <input type="checkbox" value="twoA" style="margin-top: 8px;" name="serviceTime" disabled="disabled"
								       ${fn:contains(volunteer.serviceTime,"twoA")? "checked":""}>周二上午
								    </label>
								    <label>
								      <input type="checkbox" value="threeA" style="margin-top: 8px;" name="serviceTime" disabled="disabled"
								      ${fn:contains(volunteer.serviceTime,"threeA")? "checked":""}>周三上午
							    	</label>
							    	 <label>
								      <input type="checkbox" value="fourA" style="margin-top: 8px;" name="serviceTime" disabled="disabled"
								      ${fn:contains(volunteer.serviceTime,"fourA")? "checked":""}> 周四上午
								    </label>
								    <label>
								      <input type="checkbox" value="fiveA" style="margin-top: 8px;" name="serviceTime" disabled="disabled"
								      ${fn:contains(volunteer.serviceTime,"fiveA")? "checked":""}> 周五上午
								    </label>
								    <label>
								      <input type="checkbox" value="sixA" style="margin-top: 8px;" name="serviceTime" disabled="disabled"
								      ${fn:contains(volunteer.serviceTime,"sixA")? "checked":""}> 周六上午
								    </label>
								    <label>
								      <input type="checkbox" value="sevenA" style="margin-top: 8px;" name="serviceTime" disabled="disabled"
								      ${fn:contains(volunteer.serviceTime,"sevenA")? "checked":""}> 周日上午
								    </label>
								</div>
								 <div class="checkbox">
								    <label>
								      <input type="checkbox" value="oneP" style="margin-top: 8px;" name="serviceTime" disabled="disabled"
								      ${fn:contains(volunteer.serviceTime,"oneP")? "checked":""}> 周一下午
								    </label>
								    <label>
								      <input type="checkbox" value="twoP" style="margin-top: 8px;" name="serviceTime" disabled="disabled"
								      ${fn:contains(volunteer.serviceTime,"twoP")? "checked":""}> 周二下午
								    </label>
								    <label>
								      <input type="checkbox" value="threeP" style="margin-top: 8px;" name="serviceTime" disabled="disabled"
								      ${fn:contains(volunteer.serviceTime,"threeP")? "checked":""}> 周三下午
								    </label>
								    <label>
								      <input type="checkbox" value="fourP" style="margin-top: 8px;" name="serviceTime" disabled="disabled"
								      ${fn:contains(volunteer.serviceTime,"fourP")? "checked":""}> 周四下午
								    </label>
								    <label>
								      <input type="checkbox" value="fiveP" style="margin-top: 8px;" name="serviceTime" disabled="disabled"
								      ${fn:contains(volunteer.serviceTime,"fiveP")? "checked":""}> 周五下午
								    </label>
								    <label>
								      <input type="checkbox" value="sixP" style="margin-top: 8px;" name="serviceTime" disabled="disabled"
								      ${fn:contains(volunteer.serviceTime,"sixP")? "checked":""}> 周六下午
								    </label>
								    <label>
								      <input type="checkbox" value="sevenP" style="margin-top: 8px;" name="serviceTime" disabled="disabled"
								      ${fn:contains(volunteer.serviceTime,"sevenP")? "checked":""}> 周日下午
								    </label>
								</div>
						    </td>
				    	 </tr>
				    	 <tr id="child">
							<td class="col-xs-1">
								<input type="hidden" id="input_child" value="${volunteer.child}">
						    	<label >是否经过监护人或<br>单位领导人同意<small ><code>*</code></small></label>
						    </td>
						    <td class="col-xs-2" colspan="5" style=" vertical-align: middle;" >
						    	${volunteer.child  ==1?"是":"不是"}
						    </td>
				    	 </tr>
				    	 <tr >
							<td class="col-xs-1" style="vertical-align:middle">
						    	<label >备注</label>
						    </td>
						    <td class="col-xs-2" colspan="5">
								<textarea style="width: 100%;height: 100px" readonly="readonly">${volunteer.remark}</textarea>
						    </td>
				    	 </tr>
				    	 
					</table>
				</div>
				<div class="row">
					<table class="table table-bordered dataTable no-footer">
						<tr>
							<th colspan="6" style="text-align:center;background-color:rgba(248, 248, 248, 1)">
						       	审核意见
						     </th>
				    	 </tr>
				    	 <c:if test="${empty volunteerCheck}">
				    	 	<tr style="text-align:center;">
				    	 		<td colspan="6">无</td>
				    	 	</tr>
				    	 </c:if>
				    	 <c:if test="${!empty volunteerCheck}">
						<tr>
							<td class="col-xs-1" style="text-align:center;">
						    	<label >审核人</label>
						    </td>
						    <td class="col-xs-2">
						    	${volunteerCheck.userName}
						    </td>
						    <td class="col-xs-1" style="text-align:center;">
						    	<label >审核时间</label>
						    </td>
						    <td class="col-xs-2">
						    	<fmt:formatDate value="${volunteerCheck.operTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
						    </td>
						    <td class="col-xs-1" style="text-align:center;">
						    	<label >状态</label>
						    </td>
						    <td class="col-xs-2" >
						    	${volunteerCheck.checkStatus}
						    </td>
					    </tr>
				    	 <tr>
							<td class="col-xs-1" style="text-align:center;vertical-align:middle">
						    	<label >审核意见</label>
						    </td>
						    <td class="col-xs-2" colspan="5">
							    <textarea  style="width: 100%;height: 100px" readonly="readonly" id="checkContent">${volunteerCheck.checkContent}</textarea>
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
	$('#pic').attr('src', '${webroot}file/readPic.do?ataUrl='+encodeURIComponent("${volunteer.imgUrl}"));
	function closeFrame(){
		var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
		parent.layer.close(index);
	}
	
	var val= $("#input_child").val();
	if (val==2 || val==null ||val=="") {
		$("#child").hide();
	}
	</script>
</body>
</html>