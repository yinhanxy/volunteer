<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %> 
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
	<link href="${sr}css/bootstrap-datetimepicker.min.css" rel="stylesheet">
    <!--[if lt IE 9]>
      <script src="${sr}js/html5shiv.min.js"></script>
      <script src="${sr}js/respond.min.js"></script>
    <![endif]-->
</head>
<body class="v-page-body"  style="overflow-x: hidden">
	<div class="page-container">
		<div class="panel" style="padding-bottom: 0px;margin-bottom: 0px;">	
				<form id="addRecruitForm" action="${webroot}activity/addRecruit.do">
					<div>
					<table class="table table-bordered dataTable no-footer">
						<tr class="row">
							<td class="col-xs-2"><label for="inputEmail1">活动名称<small><code >*</code></small></label></td>
							<td class="col-xs-4">
			                   		<div class="input-group" onclick='selectLongActivity()'>
									<input type="hidden" name="id"  id="id"/>
									<input type="text"  class="form-control" name="name" id="name" readonly="readonly" placeholder="活动名称" value="${org.name}"
									data-validation="required" data-validation-error-msg-required="此项为必填项" data-validation-error-msg-container="#nError"/>
									<span class="input-group-btn">
							       		 <button type="button" id="orgButton" class="btn   btn-primary  "><i class="fa fa-search"></i>
							             </button> 
						       		</span>
								</div>
                   			</td>
                   			<td class="col-xs-2"><label for="inputEmail2">活动类型<small><code >*</code></small></label></td>
							<td class="col-xs-4">
								<label>长期活动</label>
                   			</td>
						</tr>
						<tr class="row">
							<td style="vertical-align: middle;"><label for="inputEmail3">招募条件<small><code >*</code></small></label></td>
							<td colspan="3">
								<textarea class="form-control" rows="3" name="requirements" id="requirements" placeholder="招募条件"></textarea>
                   			</td>
						</tr>
						<tr class="row">
                   			<td style="vertical-align: middle;"><label for="inputEmail3">活动类别<small><code >*</code></small></label></td>
							<td>
								<select class="form-control" name="activityClass" data-validation="required" id="activityClass"
						    	data-validation-error-msg-required="此选项为必填" data-validation-error-msg-container="#nError1">
	           					</select>
                   			</td>
                   			<td class="col-xs-2"><label for="inputEmail1">招募人数<small><code >*</code></small></label></td>
							<td class="col-xs-4">
									<input type="text"  class="form-control" name="recruitNum"  id="recruitNum" placeholder="招募人数" data-validation="required"
								data-validation-error-msg-required="此选项为必填" 
								data-validation-error-msg-container="#nError6"/>
                   			</td>
						</tr>
						<tr class="row">
							<td class="col-xs-2"><label for="inputEmail1">联系人<small><code >*</code></small></label></td>
							<td class="col-xs-4">
									<input type="text"  class="form-control" name="contactPerson"   placeholder="联系人" id="contactPerson"
									data-validation="required" data-validation-error-msg-required="此选项为必填" data-validation-error-msg-container="#nError1"/>
                   			</td>
                   			<td class="col-xs-2"><label for="inputEmail1">联系方式<small><code >*</code></small></label></td>
							<td class="col-xs-4">
									<input type="text"  class="form-control" name="contact" id="contact"  placeholder="联系电话" data-validation="required teleNumber"
								data-validation-error-msg-required="联系电话为必填项" 
								data-validation-error-msg-container="#nError6"/>
                   			</td>
						</tr>
						<tr class="row">
							<td class="col-xs-2"><label for="inputEmail1">地点<small><code >*</code></small></label></td>
							<td class="col-xs-4">
									<input type="text"  class="form-control" name="address"   placeholder="活动地址" id="address"
									data-validation="required" data-validation-error-msg-required="此选项为必填" data-validation-error-msg-container="#nError1"/>
                   			</td>
                   			<td class="col-xs-2"><label for="inputEmail1">服务时长<small><code >*</code></small></label></td>
							<td class="col-xs-4">
								<input type="text"  class="form-control" name="hours"   id="hours" placeholder="活动服务时长(小时)" data-validation="required custom"
								data-validation-regexp="^[0-9]+(.[0-9]{1,3})?$"
								data-validation-error-msg-required="此选项为必填" 
								data-validation-error-msg-custom="服务时长只能是数字"
								data-validation-error-msg-container="#nError6"/>
                   			</td>
						</tr>
						<tr class="row">
							<td class="col-xs-2"><label for="inputEmail1">招募开始时间<small><code >*</code></small></label></td>
							<td class="col-xs-4">
									<input type="text"  class="form-control" name="recruitSt" id="recruitSt" readonly="readonly" placeholder="招募开始时间"
									data-validation="required" data-validation-error-msg-required="此选项为必填" data-validation-error-msg-container="#nError1"/>
                   			</td>
                   			<td class="col-xs-2"><label for="inputEmail1">招募结束时间<small><code >*</code></small></label></td>
							<td class="col-xs-4">
									<input type="text"  class="form-control" name="recruitEt" id="recruitEt" readonly="readonly" placeholder="招募结束时间" data-validation="required"
								data-validation-error-msg-required="此选项为必填" 
								data-validation-error-msg-container="#nError6"/>
                   			</td>
						</tr>
						<tr class="row">
							<td class="col-xs-2"><label for="inputEmail1">活动开始时间<small><code >*</code></small></label></td>
							<td class="col-xs-4">
									<input type="text"  class="form-control" name="activitySt"  id="activitySt" readonly="readonly"  placeholder="活动开始时间"
									data-validation="required" data-validation-error-msg-required="此选项为必填" data-validation-error-msg-container="#nError1"/>
                   			</td>
                   			<td class="col-xs-2"><label for="inputEmail1">活动结束时间<small><code >*</code></small></label></td>
							<td class="col-xs-4">
									<input type="text"  class="form-control" name="activityEt" id="activityEt"  readonly="readonly" placeholder="活动结束时间" data-validation="required"
								data-validation-error-msg-required="此选项为必填" 
								data-validation-error-msg-container="#nError6"/>
                   			</td>
						</tr>
						<tr class="row">
							<td style="vertical-align: middle;"><label for="inputEmail3">招募范围<small><code >*</code></small></label></td>
							<td><select class="form-control" name="recruitRange" id="recruitRange" data-validation="required"
						    	data-validation-error-msg-required="此选项为必填" data-validation-error-msg-container="#nError1">
		           					<option value="">请选择</option>
		           					<option value="1">公开招募</option>
		           					<option value="2">只限本服务队</option>
	           					</select>
                   			</td>
						</tr>
						<tr class="row">
							<td style="vertical-align: middle;"><label for="inputEmail3">活动简介<small><code >*</code></small></label></td>
							<td colspan="3">
								<textarea class="form-control" rows="4" name="summary" id="summary" placeholder="活动简介"></textarea>
                   			</td>
						</tr>
					</table>
					<input type="hidden" name="cType" id="cType">
					<input type="hidden" id="token" name="token" value="${ActivityRegistController_addRecruit_token}"/>
					</div>
			    </form>
		</div>
		<div style="text-align: right;margin: 10px 30px 0 0;">
	  			<button type="button" id="add" class="btn btn-sm btn-success" ><i class="fa fa-plus"></i>添加</button>
				<shiro:hasPermission name="/activity/submitActivity.do">
				<button type="button" id="addAndSubmit" class="btn btn-sm btn-success" ><i class="fa fa-plus"></i>添加并提交</button>
				</shiro:hasPermission>
				<shiro:hasPermission name="/activity/publication.do">
				<button type="button" id="addAndPublish" class="btn btn-sm btn-success" ><i class="fa fa-plus"></i>添加并发布</button>
				</shiro:hasPermission>
	   			<button type="button" class="btn btn-sm" onclick="closeFrame()"><i class="fa fa-remove"></i>关闭</button>
		</div>
	</div>
	
	<script src="${sr}js/jquery.min.js" type="text/javascript"></script>
	<script src="${sr}js/config.js" type="text/javascript"></script>
	<script src="${sr}js/baseutil.js" type="text/javascript"></script>
	<script src="${sr}js/jquery.form-validator.min.js" type="text/javascript"></script>
	<script src="${sr}js/jquery-ui.min.js" type="text/javascript"></script>
	<script src="${sr}layui.js" type="text/javascript"></script>
	<script type="text/javascript" src="${sr}js/bootstrap-datetimepicker.min.js" charset="UTF-8"></script>
	<script type="text/javascript" src="${sr}js/locales/bootstrap-datetimepicker.zh-CN.js" charset="UTF-8"></script>
	<script type="text/javascript">
	layui.use(['jquery','layer','laytpl'], function(){
		$("#addRecruitForm input,select").on("change",function(){
			$(this).validate(function(valid, elem) {
			});
		});
		$('#recruitSt').datetimepicker({
	        language:  'zh-CN',
	        weekStart: 1,
			autoclose: 1,
			todayHighlight: 1,
			startView: 2,
			minView: 0,
			forceParse: true,
			format:"yyyy-mm-dd hh:ii",
			pickerPosition:"top-left",
			startDate:new Date()
	    }).on('click', function(ev){
	        $("#recruitSt").datetimepicker("setEndDate",$("#recruitEt").val());
	    });
		$('#recruitEt').datetimepicker({
			language:  'zh-CN',
	        weekStart: 1,
			autoclose: 1,
			todayHighlight: 1,
			startView: 2,
			minView: 0,
			forceParse: true,
			format:"yyyy-mm-dd hh:ii",
			pickerPosition:"top-left"
	    }).on('click', function(ev){
	        $("#recruitEt").datetimepicker("setStartDate",$("#recruitSt").val());
    	});
		$('#activitySt').datetimepicker({
			language:  'zh-CN',
	        weekStart: 1,
			autoclose: 1,
			todayHighlight: 1,
			startView: 2,
			minView: 0,
			forceParse: true,
			format:"yyyy-mm-dd hh:ii",
			pickerPosition:"top-left"
	    }).on('click', function(ev){
	        $("#activitySt").datetimepicker("setStartDate",$("#recruitEt").val());
    	});
		$('#activityEt').datetimepicker({
			language:  'zh-CN',
	        weekStart: 1,
			autoclose: 1,
			todayHighlight: 1,
			startView: 2,
			minView: 0,
			forceParse: true,
			format:"yyyy-mm-dd hh:ii",
			pickerPosition:"top-left"
	    }).on('click', function(ev){
	        $("#activityEt").datetimepicker("setStartDate",$("#activitySt").val());
    	});
		
		$.validate({
			validateOnBlur : false, 
			onElementValidate : function(valid, $el, $form, errorMess) {
				 if(!valid){
					 layui.layer.tips(errorMess, $el,{
			    		  tips: [1, '#f57a78']
			    	 });
				 }
			}
		});
		
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
		 
		
		var errors=[];
		var conf = {
				validateOnBlur : false, 
				onElementValidate : function(valid, $el, $form, errorMess) {
			     if( !valid ){
			    	errors.push({el: $el, error: errorMess});
			     }
			  }
		 };
		$('#add').on('click', function() {
			errors=[];
			if( !$('#addRecruitForm').isValid({},conf, true)) {
				isForm=false;
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
				postFormData("addRecruitForm",true,addRecruit);
		    }
			
		});
		$('#addAndSubmit').on('click', function() {
			errors=[];
			if( !$('#addRecruitForm').isValid({},conf, true)) {
				isForm=false;
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
		   		$("#cType").val("ss");
				postFormData("addRecruitForm",true,addRecruit);
		    }
			
		});
		$('#addAndPublish').on('click', function() {
			errors=[];
			if( !$('#addRecruitForm').isValid({},conf, true)) {
				isForm=false;
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
		   		$("#cType").val("sp");
				postFormData("addRecruitForm",true,addRecruit);
		    }
			
		});
		showActivityClass();
});
	
	//选择招募的活动
	function selectLongActivity(id){
		var layer = parent.layer;
		//iframe层-父子操作
		var index=layer.open({
		  type: 2,
		  title:"选择活动",
		  //skin: 'v-page-class',
		  area: ['800px', '500px'],
		  maxmin: true,
		  scrollbar: false,
		  content: ["${webroot}activity/recruitActivityList.html"],
		  btn : [ '确定', '取消' ], 
		  yes : function(index, layero) {
				var iframeWin = top.window[layero.find('iframe')[0]['name']]; 
				var data=iframeWin.selectActivity();
				if(data){
					$("#name").val(data.name);
					$("#activityClass").val(data.activityClass);
					$("#contact").val(data.contact);
					$("#contactPerson").val(data.contactPerson);
					$("#recruitNum").val(data.recruitNum);
					$("#address").val(data.address);
					$("#hours").val(data.hours);
					$("#recruitRange").val(data.recruitRange);
					$("#id").val(data.activityId);
					$("#summary").val(data.summary);
					$("#requirements").val(data.requirements);
					iframeWin.closeFrame();
				}
		  }
		});
	}
	
	//显示活动类别
	function showActivityClass(){
		var url="${webroot}activity/activityClass.do";
		postData(url, null, true, showActivityClassResult); 
	}
	
	function showActivityClassResult(data){
		if (data.success) {
			var html="";
			var list =data.activityClassList;
			for (var i = 0; i < list.length; i++) {
				html+="<option value='"+list[i].id+"'>"+list[i].name+"</option>";
			}
			$("#activityClass").prepend(html);
		} 
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
	**添加活动的回调函数
	*/
	function addRecruit(data){
		var layer = layui.layer;
		var message;
		if(data && data.success){
			message=data.message;
			layer.alert(message, {icon: 6},function(index){
			    closeFrame();
			    parent.loadPage();
			  });
		}else{
			$("#token").val(data.token);
			errorTips(data);
			clearValid("addRecruitForm");
		}
	}
	</script>
</body>
</html>