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
	<link href="${sr}css/bootstrap-datetimepicker.min.css" rel="stylesheet">
    <!--[if lt IE 9]>
      <script src="${sr}js/html5shiv.min.js"></script>
      <script src="${sr}js/respond.min.js"></script>
    <![endif]-->
</head>
<body class="v-page-body">
	<div class="page-container">
		<div class="panel">	
				<form id="addTrRecordForm" action="${webroot}record/addTrRecord.do">
					<div class="row">
					<table class="table table-bordered dataTable no-footer">
						<tr>
							<td class="col-xs-2"><label>培训名称<small><code >*</code></small></label></td>
							<td class="col-xs-10" colspan="3">
								<input type="text"  class="form-control" name="trName" placeholder="培训记录名称"   
									data-validation="required custom" data-validation-regexp="^[a-zA-Z\u4e00-\u9fa5]{2,50}$"
			                   		data-validation-error-msg-required="服务队名称为必填项"
			                   		data-validation-error-msg-custom="此项必须为2-50位的汉字与字母,不能有空格"
			                   		data-validation-error-msg-container="#nError"/>
                   			</td>
                   			
						</tr>
						<tr>
							<td style="vertical-align: middle"><label>培训内容<small><code >*</code></small></label></td>
							<td colspan="3">
								<textarea id="text-area" class="form-control" rows="7" placeholder="培训内容" name="trContent"></textarea>
				      			<div>(剩余<label id="max-len">300</label>字符)</div>
                   			</td>
						</tr>
						<tr>
							<td  ><label>主讲人<small><code >*</code></small></label></td>
							<td >
								<input type="text"  class="form-control" name="presenter" placeholder="主讲人名称"   
									data-validation="required length"  data-validation-length="1-10"  
							      	data-validation-error-msg-required="主讲人名称为必填项"
							      	data-validation-error-msg-length="主讲人名称长度在1-10个字符" 
			                   		data-validation-error-msg-container="#nError"/>
                   			</td>
                   			<td ><label>培训时间<small><code >*</code></small></label></td>
							<td >
								<input type="text"  class="form-control" id="trTime" name="trTime" placeholder="培训时间"   
									data-validation="required"
									data-validation-error-msg-required="培训时间为必填项"
									data-validation-error-msg-container="#nError"/>
                   			</td>
						</tr>
						<tr>
							<td ><label>培训地点<small><code >*</code></small></label></td>
							<td colspan="3">
								<input type="text"  class="form-control" name="trLocation" placeholder="培训地点"   
			                   		data-validation="required"
			                   		data-validation-error-msg-required="培训地点为必填项"
			                   		data-validation-error-msg-container="#nError"/>
                   			</td>
                   			
						</tr>
						
						<tr>
							<td><label>负责人<small><code >*</code></small></label></td>
							<td>
								<input type="text"  class="form-control" name="principal" placeholder="负责人名称"   
									data-validation="required length"  data-validation-length="1-10"  
							      	data-validation-error-msg-required="负责人名称为必填项"
							      	data-validation-error-msg-length="负责人名称长度在1-10个字符"
			                   		data-validation-error-msg-container="#nError"/>
                   			</td>
                   			<td><label>负责人联系方式<small><code >*</code></small></label></td>
							<td>
								<input type="text"  class="form-control" name="principalTel" placeholder="负责人联系电话"   
									data-validation="required teleNumber"
									data-validation-error-msg-required="联系电话为必填项"
									data-validation-error-msg-container="#nError"/>
                   			</td>
						</tr>
						
						<tr>
							<td><label>联系人<small><code >*</code></small></label></td>
							<td>
								<input type="text"  class="form-control" name="contact" placeholder="联系人名称"   
									data-validation="required length"  data-validation-length="1-10"  
							      	data-validation-error-msg-required="联系人名称为必填项"
							      	data-validation-error-msg-length="联系人名称长度在1-10个字符"
			                   		data-validation-error-msg-container="#nError"/>
                   			</td>
                   			<td><label>联系人联系方式<small><code >*</code></small></label></td>
							<td>
								<input type="text"  class="form-control" name="contactTel" placeholder="联系人联系电话"   
									data-validation="required teleNumber"
									data-validation-error-msg-required="联系电话为必填项"
									data-validation-error-msg-container="#nError"/>
                   			</td>
						</tr>
						<tr>
						<td ><label>状态<small><code >*</code></small></label></td>
							<td colspan="3">
								<select class="form-control input-sm" name="status">
			           					<option value="1" selected="selected">待进行</option>
			           					<option value="2">进行中</option>
			           					<option value="3">已完成</option>
			           			</select>
                   			</td>
                   		</tr>	
					</table>
					<input type="hidden" id="token" name="token" value="${TrRecordController_addTrRecord_token}"/>
					</div>
			    </form>
		</div>
	</div>
	
	<div class="v-form-footer">
	  			<button type="button" id="sub" class="btn btn-sm btn-success"><i class="fa fa-floppy-o"></i>保存</button>
	   			<button type="button" class="btn btn-sm" onclick="closeFrame()"><i class="fa fa-remove"></i>关闭</button>
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
		$("#addTrRecordForm input,select").on("change",function(){
			$(this).validate(function(valid, elem) {
			});
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
		
		$('#trTime').datetimepicker({
	        language:  'zh-CN',
	        weekStart: 1,
			autoclose: 1,
			todayHighlight: 1,
			startView: 2,
			minView: 0,
			forceParse: true,
			format:"yyyy-mm-dd hh:ii",
			pickerPosition:"bottom-left",
	    })
		
	    $('#text-area').restrictLength($('#max-len'));
		
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
	    
		var errors=[];
		var conf = {
				validateOnBlur : false, 
				onElementValidate : function(valid, $el, $form, errorMess) {
			     if( !valid ){
			    	errors.push({el: $el, error: errorMess});
			     }
			  }
		 };
		$('#sub').on('click', function() {
			errors=[];
			if( !$('#addTrRecordForm').isValid({},conf, true)) {
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
		   		$("#sub").prop("disabled",true);
				postFormData("addTrRecordForm",true,addResult);
		     } 
			
		});
});
	
	
	function clearValid(formId){
		var els = $("#"+formId+" :input");
		els.removeClass("valid");
	}
	
	function closeFrame(){
		var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
		parent.layer.close(index);
	}
	
	/**
	**添加培训记录的回调函数
	*/
	function addResult(data){
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
			clearValid("addTrRecordForm");
		}
		$("#sub").prop("disabled",false);
	}
	</script>
</body>
</html>