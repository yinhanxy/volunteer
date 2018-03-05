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
	<link href="${sr}css/layui.css" rel="stylesheet">
	<link href="${sr}css/bootstrap-datetimepicker.min.css" rel="stylesheet">
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
		.v-form-footer {
		    padding-top: 0;
		    height: 50px;
		    position: static;
		    line-height: 60px;
		    background: #fff;
		    right: 20px;
		    bottom: 0px;
		}
    </style>
</head>
<body class="v-page-body">
	<div class="page-container">
		<div class="panel" style="margin-bottom: 0" id="mainform">
			<form id="saveVolunteerForm" action="${webroot}vols/editVolunteer.do" >
				<input type="hidden" name="id" value="${volunteer.id}">
				<div class="row">
					<table class="table table-bordered dataTable no-footer">
						<tr>
							<td class="col-xs-1">
						    	<label >姓名</label>
						    </td>
						    <td class="col-xs-2">
						      	${volunteer.realName}
						    </td>
						    <td class="col-xs-1">
						    	<label >性别<small ><code>*</code></small></label>
						    </td>
						    <td class="col-xs-2">
							     <select class="form-control" name="sex" data-validation="required"
							    	data-validation-error-msg-required="此选项为必填" data-validation-error-msg-container="#nError1">
		           					<option value="男" ${volunteer.sex=="男"?"selected":""}>男</option>
		           					<option value="女" ${volunteer.sex=="女"?"selected":""}>女</option>
		           				</select>
						    </td>
						    <td class="col-xs-1" rowspan="4" style="vertical-align:middle">
						    	<label >照片</label>
						    </td>
						    <td class="col-xs-2" rowspan="4" style="" align="center">
					              <div style="width:164px;height:124px;margin-bottom: 10px">
					              	<span id="content" style='${!empty volunteer.imgUrl?"display:none":""}'><img width="164" height="124" border="0" src="${sr}images/photo.png" style="margin-bottom: 5px ;"></span>
									<img id="pic" alt="志愿者头像" width="164" height="124" border="0" src="" style="margin-bottom: 5px ;${empty volunteer.imgUrl?'display:none':''}" >
								</div>
								<input type="file" name="file" class="layui-upload-file"   lay-title="修改头像">
								<input type="hidden" id="avatarUrl" name="imgUrl">
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
						    	<label >手机号<small ><code>*</code></small></label>
						    </td>
						    <td class="col-xs-2">
						      <input type="text" class="form-control" id="mobile" name="mobile"  value="${volunteer.mobile}"
						      		data-validation="required teleNumber"
						      		data-validation-error-msg-required="手机号为必填项"
						      		data-validation-error-msg-teleNumber="手机号不合法,请重新输入"
		                   			data-validation-error-msg-container="#nError1"/>
						    </td>
				    	 </tr>
				    	 <tr>
							<td class="col-xs-1">
						    	<label >民族<small ><code>*</code></small></label>
						    </td>
						    <td class="col-xs-2">
						     	<select class="form-control" name="nation" data-validation="required" 
							    	data-validation-error-msg-required="此选项为必填" data-validation-error-msg-container="#nError1">
							          <option value="" ${empty volunteer.nation?'selected':''}>请选择</option>
							          <option value="01" ${volunteer.nation=='01'?'selected':''}>汉族</option>
							          <option value="02" ${volunteer.nation=='02'?'selected':''}>蒙古族</option>
							          <option value="03" ${volunteer.nation=='03'?'selected':''}>满族</option>
							          <option value="04" ${volunteer.nation=='04'?'selected':''}>朝鲜族</option>
							          <option value="05" ${volunteer.nation=='05'?'selected':''}>赫哲族</option>
							          <option value="06" ${volunteer.nation=='06'?'selected':''}>达斡尔族</option>
							          <option value="07" ${volunteer.nation=='07'?'selected':''}>鄂温克族</option>
							          <option value="08" ${volunteer.nation=='08'?'selected':''}>鄂伦春族</option>
							          <option value="09" ${volunteer.nation=='09'?'selected':''}>回族</option>
							          <option value="10" ${volunteer.nation=='10'?'selected':''}>东乡族</option>
							          <option value="11" ${volunteer.nation=='11'?'selected':''}>土族</option>
							          <option value="12" ${volunteer.nation=='12'?'selected':''}>撒拉族</option>
							          <option value="13" ${volunteer.nation=='13'?'selected':''}>保安族</option>
							          <option value="14" ${volunteer.nation=='14'?'selected':''}>裕固族</option>
							          <option value="15" ${volunteer.nation=='15'?'selected':''}>维吾尔族</option>
							          <option value="16" ${volunteer.nation=='16'?'selected':''}>哈萨克族</option>
							          <option value="17" ${volunteer.nation=='17'?'selected':''}>柯尔克孜族</option>
							          <option value="18" ${volunteer.nation=='18'?'selected':''}>锡伯族</option>
							          <option value="19" ${volunteer.nation=='19'?'selected':''}>塔吉克族</option>
							          <option value="20" ${volunteer.nation=='20'?'selected':''}>乌孜别克族</option>
							          <option value="21" ${volunteer.nation=='21'?'selected':''}>俄罗斯族</option>
							          <option value="22" ${volunteer.nation=='22'?'selected':''}>塔塔尔族</option>
							          <option value="23" ${volunteer.nation=='23'?'selected':''}>藏族</option>
							          <option value="24" ${volunteer.nation=='24'?'selected':''}>门巴族</option>
							          <option value="25" ${volunteer.nation=='25'?'selected':''}>珞巴族</option>
							          <option value="26" ${volunteer.nation=='26'?'selected':''}>羌族</option>
							          <option value="27" ${volunteer.nation=='27'?'selected':''}>彝族</option>
							          <option value="28" ${volunteer.nation=='28'?'selected':''}>白族</option>
							          <option value="29" ${volunteer.nation=='29'?'selected':''}>哈尼族</option>
							          <option value="30" ${volunteer.nation=='30'?'selected':''}>傣族</option>
							          <option value="31" ${volunteer.nation=='31'?'selected':''}>傈僳族</option>
							          <option value="32" ${volunteer.nation=='32'?'selected':''}>佤族</option>
							          <option value="33" ${volunteer.nation=='33'?'selected':''}>拉祜族</option>
							          <option value="34" ${volunteer.nation=='34'?'selected':''}>纳西族</option>
							          <option value="35" ${volunteer.nation=='35'?'selected':''}>景颇族</option>
							          <option value="36" ${volunteer.nation=='36'?'selected':''}>布朗族</option>
							          <option value="37" ${volunteer.nation=='37'?'selected':''}>阿昌族</option>
							          <option value="38" ${volunteer.nation=='38'?'selected':''}>普米族</option>
							          <option value="39" ${volunteer.nation=='39'?'selected':''}>怒族</option>
							          <option value="40" ${volunteer.nation=='40'?'selected':''}>德昂族</option>
							          <option value="41" ${volunteer.nation=='41'?'selected':''}>独龙族</option>
							          <option value="42" ${volunteer.nation=='42'?'selected':''}>基诺族</option>
							          <option value="43" ${volunteer.nation=='43'?'selected':''}>苗族</option>
							          <option value="44" ${volunteer.nation=='44'?'selected':''}>布依族</option>
							          <option value="45" ${volunteer.nation=='45'?'selected':''}>侗族</option>
							          <option value="46" ${volunteer.nation=='46'?'selected':''}>水族</option>
							          <option value="47" ${volunteer.nation=='47'?'selected':''}>仡佬族</option>
							          <option value="48" ${volunteer.nation=='48'?'selected':''}>壮族</option>
							          <option value="49" ${volunteer.nation=='49'?'selected':''}>瑶族</option>
							          <option value="50" ${volunteer.nation=='50'?'selected':''}>仫佬族</option>
							          <option value="51" ${volunteer.nation=='51'?'selected':''}>毛南族</option>
							          <option value="52" ${volunteer.nation=='52'?'selected':''}>京族</option>
							          <option value="53" ${volunteer.nation=='53'?'selected':''}>土家族</option>
							          <option value="54" ${volunteer.nation=='54'?'selected':''}>黎族</option>
							          <option value="55" ${volunteer.nation=='55'?'selected':''}>畲族</option>
							          <option value="56" ${volunteer.nation=='56'?'selected':''}>高山族</option>
							    </select>
						    </td>
						    <td class="col-xs-1">
						    	<label >政治面貌</label>
						    </td>
						    <td class="col-xs-2">
						       <select class="form-control" name="polstatus" data-validation="required" 
							    	data-validation-error-msg-required="此选项为必填" data-validation-error-msg-container="#nError1">
		           					<option value="">请选择</option>
						        	<option value="01" ${volunteer.polstatus=='01'?'selected':''}>中共党员</option>
							        <option value="02" ${volunteer.polstatus=='02'?'selected':''}>中共预备党员</option>
							        <option value="03" ${volunteer.polstatus=='03'?'selected':''}>共青团员</option>
							        <option value="04" ${volunteer.polstatus=='04'?'selected':''}>民主党派</option>
							        <option value="05" ${volunteer.polstatus=='05'?'selected':''}>无党派民主人士</option>
							        <option value="06" ${volunteer.polstatus=='06'?'selected':''}>群众</option>
		           				</select>
						    </td>
				    	 </tr>
				    	 <tr>
							<td class="col-xs-1">
						    	<label >籍贯<small ><code>*</code></small></label>
						    </td>
						    <td class="col-xs-2">
						      <input type="text" class="form-control" id="naplace" name="naplace" value="${volunteer.naplace}"
						      		data-validation="required"
						      		data-validation-error-msg-required="此项为必填项"
		                   			data-validation-error-msg-container="#nError1"/>
						    </td>
						    <td class="col-xs-1">
						    	<label >出生日期</label>
						    </td>
						    <td class="col-xs-2">
						      <input type="text" class="form-control" id="birthday" name="birthday" value="<fmt:formatDate value="${volunteer.birthday}" pattern="yyyy-MM-dd"/>"
						      		data-validation="custom" data-validation-regexp="^[0-9]{4}-[0-9]{2}-[0-9]{2}$"
		                   			data-validation-error-msg-custom="出生日期必须符合YYYY-MM-DD且合法的书写要求" 
		                   			data-validation-error-msg-container="#nError1"/>
						    </td>
				    	 </tr>
				    	 <tr>
							<td class="col-xs-1">
						    	<label >学历<small ><code>*</code></small></label>
						    </td>
						    <td class="col-xs-2">
						      	<select class="form-control" name="education" data-validation="required"
							    	data-validation-error-msg-required="此选项为必填" data-validation-error-msg-container="#nError1">
		           					  <option value="" >请选择</option>
							          <option value="01" ${volunteer.education=='01'?'selected':''}>博士</option>
							          <option value="02" ${volunteer.education=='02'?'selected':''}>硕士</option>
							          <option value="03" ${volunteer.education=='03'?'selected':''}>本科</option>
							          <option value="04" ${volunteer.education=='04'?'selected':''}>大专</option>
							          <option value="05" ${volunteer.education=='05'?'selected':''}>中专</option>
							          <option value="06" ${volunteer.education=='06'?'selected':''}>职业高中</option>
							          <option value="07" ${volunteer.education=='07'?'selected':''}>普通高中</option>
							          <option value="08" ${volunteer.education=='08'?'selected':''}>初中</option>
							          <option value="09" ${volunteer.education=='09'?'selected':''}>小学</option>
							          <option value="10" ${volunteer.education=='10'?'selected':''}>其他</option>
		           				</select>
						    </td>
						    <td class="col-xs-1">
						    	<label >毕业学校</label>
						    </td>
						    <td class="col-xs-2">
						      <input type="text" class="form-control" id="grasch" name="grasch" value="${volunteer.grasch}"
						      		/>
						    </td>
						    <td class="col-xs-1">
						    	<label >所学专业</label>
						    </td>
						    <td class="col-xs-2">
						      <input type="text" class="form-control" id="profession" name="profession"  value="${volunteer.profession}"
						      		/>
						    </td>
				    	 </tr>
				    	 <tr>
							<td class="col-xs-1">
						    	<label >居住地址<small ><code>*</code></small></label>
						    </td>
						    <td class="col-xs-2" colspan="3">
						      <input type="text" class="form-control" id="address" name="address" value="${volunteer.address}"
						      		data-validation="required" 
						      		data-validation-error-msg-required="居住地址为必填项"
		                   			data-validation-error-msg-container="#nError1"/>
						    </td>
						    <td class="col-xs-1">
						    	<label >邮政编码</label>
						    </td>
						    <td class="col-xs-2">
						      <input type="text" class="form-control" id="postcode" name="postcode" value="${volunteer.postcode}"
						      		/>
						    </td>
				    	 </tr>
				    	 <tr>
							<td class="col-xs-1">
						    	<label >工作单位</label>
						    </td>
						    <td class="col-xs-2">
						      <input type="text" class="form-control" id="work" name="work" value="${volunteer.work}"
						      		/>
						    </td>
						    <td class="col-xs-1">
						    	<label >工作地址</label>
						    </td>
						    <td class="col-xs-2">
						      <input type="text" class="form-control" id="jobAddress" name="jobAddress" value="${volunteer.jobAddress}"
						      		/>
						    </td>
						    <td class="col-xs-1">
						    	<label >电子邮箱</label>
						    </td>
						    <td class="col-xs-2">
						      <input type="text" class="form-control" id="email" name="email" value="${volunteer.email}"
						        data-validation-error-msg-email="邮件格式不正确"
						      	data-validation-error-msg-container="#nError6"/>
						    </td>
				    	 </tr>
				    	 <tr>
							<td class="col-xs-1">
						    	<label >职称</label>
						    </td>
						    <td class="col-xs-2">
							      <select class="form-control" name="jobTitle" >
			           					  <option value="" >请选择</option>
								          <option value="01" ${volunteer.jobTitle=='01'?'selected':''}>正高</option>
								          <option value="02" ${volunteer.jobTitle=='02'?'selected':''}>副高</option>
								          <option value="03" ${volunteer.jobTitle=='03'?'selected':''}>中级</option>
								          <option value="04" ${volunteer.jobTitle=='04'?'selected':''}>初级</option>
			           				</select>
						    </td>
						    <td class="col-xs-1">
						    	<label >职务</label>
						    </td>
						    <td class="col-xs-2">
						      <input type="text" class="form-control" id="duties" name="duties" value="${volunteer.duties}"
						      		/>
						    </td>
						    <td class="col-xs-1">
						    	<label >QQ号码<small ><code>*</code></small></label>
						    </td>
						    <td class="col-xs-2">
						      <input type="text" class="form-control" id="qq" name="qq" value="${volunteer.qq}"
						      		data-validation="required"
						      		data-validation-error-msg-required="QQ号为必填项"
		                   			data-validation-error-msg-container="#nError1"/>
						    </td>
				    	 </tr>
				    	 <tr>
							<td class="col-xs-1" style="vertical-align: middle">
						    	<label >擅长技能<small ><code>*</code></small></label>
						    </td>
						    <td class="col-xs-2" colspan="3">
						      <input type="text" class="form-control" id="specility" name="specility" value="${volunteer.specility}"
						      		data-validation="required"
						      		data-validation-error-msg-required="此项为必填项"
		                   			data-validation-error-msg-container="#nError1"/>
						    </td>
						    <td class="col-xs-1">
						    	<label >分类</label>
						    </td>
						    <td class="col-xs-2">
						      <input type="text" class="form-control" id="specility" name="serviceClass" value="${volunteer.serviceClass}"/>
						    </td>
				    	 </tr>
				    	 <tr >
							<td class="col-xs-1" style="vertical-align:middle">
						    	<label >志愿服务经历</label>
						    </td>
						    <td class="col-xs-2" colspan="5" style="padding-bottom: 0">
								<textarea  style="width: 100%;height: 100px" name="serviceExperiment">${volunteer.serviceExperiment}</textarea>
						    </td>
				    	 </tr>
				    	 <tr >
							<td class="col-xs-1" style="vertical-align:middle">
						    	<label >意向服务时间</label>
						    </td>
						    <td class="col-xs-2" colspan="5">
      					        <div class="checkbox">
								    <label>
								      <input type="checkbox" value="oneA" style="margin-top: 8px;" name="serviceTime" 
								     	${fn:contains(volunteer.serviceTime,"oneA")? "checked":""}>周一上午
								    </label>
								    
								    <label>
								      <input type="checkbox" value="twoA" style="margin-top: 8px;" name="serviceTime"
								       ${fn:contains(volunteer.serviceTime,"twoA")? "checked":""}>周二上午
								    </label>
								    <label>
								      <input type="checkbox" value="threeA" style="margin-top: 8px;" name="serviceTime"
								      ${fn:contains(volunteer.serviceTime,"threeA")? "checked":""}>周三上午
							    	</label>
							    	 <label>
								      <input type="checkbox" value="fourA" style="margin-top: 8px;" name="serviceTime"
								      ${fn:contains(volunteer.serviceTime,"fourA")? "checked":""}> 周四上午
								    </label>
								    <label>
								      <input type="checkbox" value="fiveA" style="margin-top: 8px;" name="serviceTime"
								      ${fn:contains(volunteer.serviceTime,"fiveA")? "checked":""}> 周五上午
								    </label>
								    <label>
								      <input type="checkbox" value="sixA" style="margin-top: 8px;" name="serviceTime"
								      ${fn:contains(volunteer.serviceTime,"sixA")? "checked":""}> 周六上午
								    </label>
								    <label>
								      <input type="checkbox" value="sevenA" style="margin-top: 8px;" name="serviceTime"
								      ${fn:contains(volunteer.serviceTime,"sevenA")? "checked":""}> 周日上午
								    </label>
								</div>
								 <div class="checkbox">
								    <label>
								      <input type="checkbox" value="oneP" style="margin-top: 8px;" name="serviceTime"
								      ${fn:contains(volunteer.serviceTime,"oneP")? "checked":""}> 周一下午
								    </label>
								    <label>
								      <input type="checkbox" value="twoP" style="margin-top: 8px;" name="serviceTime"
								      ${fn:contains(volunteer.serviceTime,"twoP")? "checked":""}> 周二下午
								    </label>
								    <label>
								      <input type="checkbox" value="threeP" style="margin-top: 8px;" name="serviceTime"
								      ${fn:contains(volunteer.serviceTime,"threeP")? "checked":""}> 周三下午
								    </label>
								    <label>
								      <input type="checkbox" value="fourP" style="margin-top: 8px;" name="serviceTime"
								      ${fn:contains(volunteer.serviceTime,"fourP")? "checked":""}> 周四下午
								    </label>
								    <label>
								      <input type="checkbox" value="fiveP" style="margin-top: 8px;" name="serviceTime"
								      ${fn:contains(volunteer.serviceTime,"fiveP")? "checked":""}> 周五下午
								    </label>
								    <label>
								      <input type="checkbox" value="sixP" style="margin-top: 8px;" name="serviceTime"
								      ${fn:contains(volunteer.serviceTime,"sixP")? "checked":""}> 周六下午
								    </label>
								    <label>
								      <input type="checkbox" value="sevenP" style="margin-top: 8px;" name="serviceTime"
								      ${fn:contains(volunteer.serviceTime,"sevenP")? "checked":""}> 周日下午
								    </label>
								</div>
						    </td>
				    	 </tr>
				    	 <tr>
							<td class="col-xs-1" style="vertical-align:middle">
						    	<label >备注</label>
						    </td>
						    <td class="col-xs-2" colspan="5">
								<textarea style="width: 100%;height: 100px" name="remark">${volunteer.remark}</textarea>
						    </td>
				    	 </tr>
					</table>
				</div>
			</form>
		</div>
		<!-- <div class="v-form-footer" style="margin-bottom: 8px;margin-right: 8px;">
		  			<button type="button" id="sub" class="btn btn-sm btn-success"><i class="fa fa-save"></i>保存</button>
		   			<button type="button" class="btn btn-sm" onclick="closeFrame()"><i class="fa fa-remove"></i>关闭</button>
		</div> -->
	</div>
	
	<script src="${sr}js/jquery.min.js" type="text/javascript"></script>
	<script src="${sr}js/config.js" type="text/javascript"></script>
	<script src="${sr}js/baseutil.js" type="text/javascript"></script>
	<script src="${sr}js/jquery.form-validator.min.js" type="text/javascript"></script>
	<script src="${sr}js/jquery-ui.min.js" type="text/javascript"></script>
	<script src="${sr}layui.js" type="text/javascript"></script>
	<script src="${sr}js/select2.js" type="text/javascript"></script>
	<script type="text/javascript" src="${sr}js/bootstrap-datetimepicker.min.js" charset="UTF-8"></script>
	<script type="text/javascript" src="${sr}js/locales/bootstrap-datetimepicker.zh-CN.js" charset="UTF-8"></script>
	<script type="text/javascript">
	var conf;
	layui.use(['jquery','layer','upload','laytpl'], function(){
		
		$('#pic').attr('src', '${webroot}file/readPic.do?ataUrl='+encodeURIComponent("${volunteer.imgUrl}")); 
		
		$("#saveVolunteerForm input,select").on("change",function(){
				$(this).validate(function(valid, elem) {
			});
		});
		
		$('#birthday').datetimepicker({
	        language:  'zh-CN',
	        weekStart: 1,
			autoclose: 1,
			todayHighlight: 1,
			startView: 2,
			minView: 2,
			forceParse: true,
			format:"yyyy-mm-dd",
			pickerPosition:"bottom-left",
	    })
		
		$.validate({
		 	form : "#saveVolunteerForm",
			modules : 'security',
			validateOnBlur : false, 
			onElementValidate : function(valid, $el, $form, errorMess) {
			    if( !valid ) {
			    	 layui.layer.tips(errorMess, $el,{
			    		  tips: [1, '#f57a78'] ,time:1000//还可配置颜色
			    	 });
			    	 
			    }
			    if($el.attr("id")=="roles"){
			    	 if( !valid ) {
			    		$("#s2id_autogen1").addClass("error");
			    	 }
			    }
			 },
			onError : function($form) {
			          return false;
			      },  
			onSuccess: function() {
			       return true;
			     }
		  });
		
		layui.upload({
			  url: '${webroot}file/upload.do',
			  ext: 'jpg|png|gif',
			  success: function(data){
			   		var layer = layui.layer;
				 	if(data.success){
				 		var message = data.message;
				 		$("#avatarUrl").val(data.picPath);
				 		$("#content").hide();
						$("#pic").show();
				 		showPic(data.picPath);
						layer.msg(message,{time:1000});
				 	}
				 	else{
						var message = data.message;
						layer.msg(message,{time:1000});
					}
			  }
			}); 
		
		var error="";
		 $.formUtils.addValidator({
	            name : 'teleNumber',
	            validatorFunction : function(value, $el, config, language, $form) {
	            	if(!value || value == ""){
	            		return false;
	            	}
	            	var validate = false;
	            	 var regMobile =/^((\+?86)|(\(\+86\)))?(13[0123456789][0-9]{8}|15[012356789][0-9]{8}|170[0125789][0-9]{7}|17[678][0-9]{8}|18[012356789][0-9]{8}|147[0-9]{8}|1349[0-9]{7})$/;
	            	 var regPhone =/^(0[0-9]{2,3})?(-)?[0-9]{7,8}$/;
	            	 if (regMobile.test(value)) {
	            		 validate = true;
	            	 }else if(regPhone.test(value)){
	            		 validate = true;
	            	 }else{
	            		 validate = false;
	            	 };
	            	 return validate;
	            },
	            errorMessage : "联系电话格式不合法"
	        });
		 
		 
		var errors =[];
		conf = {
			modules : 'security',
			validateOnBlur : false, 
			onElementValidate : function(valid, $el, $form, errorMess) {
			     if( !valid ) {
			    	errors.push({el: $el, error: errorMess});
					return ;
			     }
			  } 
			 
		  };
		/* $('#sub').on('click', function() {
			errors =[];
			if( !$('#saveVolunteerForm').isValid({},conf, true)) {
				if(errors && errors.length>0){
					for (var i = 0; i < errors.length; i++) {
						var el=errors[i].el;
						var errorMess=errors[i].error;
						if(i==0){
							 layui.layer.tips(errorMess, el,{
					    		  tips: [1, '#f57a78'] //还可配置颜色
					    	 });
						}else{
							el.attr("style","");
							el.removeClass("error");
						}
					}
				}
				return false;
		   	}else {
		   		$("#sub").prop("disabled",true);
				postFormData("saveVolunteerForm",true,saveVolunteer);
		    }
			
		}) */
		
		
	});
		
	function save(){
		errors =[];
		var layer = layui.layer;
		if( !$('#saveVolunteerForm').isValid({},conf, true)) {
			if(errors && errors.length>0){
				for (var i = 0; i < errors.length; i++) {
					var el=errors[i].el;
					var errorMess=errors[i].error;
					if(i==0){
						 layui.layer.tips(errorMess, el,{
				    		  tips: [1, '#f57a78'] //还可配置颜色
				    	 });
					}else{
						el.attr("style","");
						el.removeClass("error");
					}
				}
			}
			return false;
	   	}else {
	   		$("#sub").prop("disabled",true);
			postFormData("saveVolunteerForm",true,saveVolunteer);
	    }
	}
	
	//上传后展示图片	
   	function showPic(picPath){
		var src = encodeURIComponent(picPath);
   		$('#pic').attr('src', '${webroot}file/readTempPic.do?ataUrl='+src); 
   	}
	
	function clearValid(formId){
		var els = $("#"+formId+" :input");
		els.removeClass("valid");
	}
	
	
	function closeFrame(){
		var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
		parent.layer.close(index);
	}
	
	/**
	**保存志愿者信息的回调函数
	*/
	function saveVolunteer(data){
		var layer = layui.layer;
		var message;
		if(data && data.success){
			message=data.message;
			layer.alert(message, {icon: 6},function(index){
			    closeFrame();
			    parent.loadPage();
			  });
		}else{
			errorTips(data);
			clearValid("saveVolunteerForm");
		}
		$("#sub").prop("disabled",false);
	}
	
	function selectServiceTime(serviceTimes,time){
		var isExists=serivceTime.indexOf(time);
		if(isExists!=-1){
			return true;
		}
		return false;
	}
	
	
	</script>
</body>
</html>