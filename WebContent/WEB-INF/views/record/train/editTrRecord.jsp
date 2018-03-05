<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
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
				<form id="editTrRecordForm" action="${webroot}record/editTrRecord.do">
					<div class="row">
					<input type="hidden" name="id" value="${trRecord.id}">
					<input type="hidden" name="teamId" value="${trRecord.teamId}">
					<table class="table table-bordered dataTable no-footer">
						<tr>
							<td class="col-xs-2"><label>培训名称<small><code >*</code></small></label></td>
							<td class="col-xs-10" colspan="3">
								<input type="text"  class="form-control" name="trName" value="${trRecord.trName }"   
									data-validation="required custom" data-validation-regexp="^[a-zA-Z\u4e00-\u9fa5]{2,50}$"
			                   		data-validation-error-msg-required="服务队名称为必填项"
			                   		data-validation-error-msg-custom="此项必须为2-50位的汉字与字母"
			                   		data-validation-error-msg-container="#nError"/>
                   			</td>
                   			
						</tr>
						<tr>
							<td style="vertical-align: middle"><label>培训内容<small><code >*</code></small></label></td>
							<td colspan="3">
								<textarea id="text-area" class="form-control" rows="5"  name="trContent">${trRecord.trContent }</textarea>
				      			<div>(剩余<label id="max-len">200</label>字符)</div>
                   			</td>
						</tr>
						<tr>
							<td  ><label>主讲人<small><code >*</code></small></label></td>
							<td >
								<input type="text"  class="form-control" name="presenter" value="${trRecord.presenter }"   
									data-validation="required length"  data-validation-length="1-10"  
							      	data-validation-error-msg-required="主讲人名称为必填项"
							      	data-validation-error-msg-length="主讲人名称长度在1-10个字符" 
			                   		data-validation-error-msg-container="#nError"/>
                   			</td>
                   			<td ><label>培训时间<small><code >*</code></small></label></td>
							<td >
								<input type="text"  class="form-control" id="trTime" name="trTime" value="${trRecord.trTime }"   
									data-validation="required"
									data-validation-error-msg-required="培训时间为必填项"
									data-validation-error-msg-container="#nError"/>
                   			</td>
						</tr>
						<tr>
							<td ><label>培训地点<small><code >*</code></small></label></td>
							<td colspan="3">
								<input type="text"  class="form-control" name="trLocation" value="${trRecord.trLocation }"   
			                   		data-validation="required"
			                   		data-validation-error-msg-required="培训地点为必填项"
			                   		data-validation-error-msg-container="#nError"/>
                   			</td>
                   			
						</tr>
						
						<tr>
							<td><label>负责人<small><code >*</code></small></label></td>
							<td>
								<input type="text"  class="form-control" name="principal" value="${trRecord.principal }"   
									data-validation="required length"  data-validation-length="1-10"  
							      	data-validation-error-msg-required="负责人名称为必填项"
							      	data-validation-error-msg-length="负责人名称长度在1-10个字符"
			                   		data-validation-error-msg-container="#nError"/>
                   			</td>
                   			<td><label>负责人联系方式<small><code >*</code></small></label></td>
							<td>
								<input type="text"  class="form-control" name="principalTel" value="${trRecord.principalTel }"   
									data-validation="required teleNumber"
									data-validation-error-msg-required="联系电话为必填项"
									data-validation-error-msg-container="#nError"/>
                   			</td>
						</tr>
						
						<tr>
							<td><label>联系人<small><code >*</code></small></label></td>
							<td>
								<input type="text"  class="form-control" name="contact" value="${trRecord.contact }"   
									data-validation="required length"  data-validation-length="1-10"  
							      	data-validation-error-msg-required="联系人名称为必填项"
							      	data-validation-error-msg-length="联系人名称长度在1-10个字符"
			                   		data-validation-error-msg-container="#nError"/>
                   			</td>
                   			<td><label>联系人联系方式<small><code >*</code></small></label></td>
							<td>
								<input type="text"  class="form-control" name="contactTel" value="${trRecord.contactTel }"   
									data-validation="required teleNumber"
									data-validation-error-msg-required="联系电话为必填项"
									data-validation-error-msg-container="#nError"/>
                   			</td>
						</tr>
						
						<tr>
							<td ><label>创建时间<small><code >*</code></small></label></td>
							<td >
								<input type="text" disabled="disabled"  class="form-control" name="crTime" value="<fmt:formatDate value="${trRecord.crTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"   
									data-validation="required"
									data-validation-error-msg-required="创建时间为必填项"
									data-validation-error-msg-container="#nError"/>
								
                   			</td>
                   			<td ><label>状态<small><code >*</code></small></label></td>
							<td >
								<select class="form-control input-sm" name="status" >
				           					<option value="1" ${trRecord.status==1? 'selected':'' }>待进行</option>
				           					<option value="2" ${trRecord.status==2? 'selected':'' }>进行中</option>
				           					<option value="3" ${trRecord.status==3? 'selected':'' }>已完成</option>
			           			</select>
                   			</td>
						</tr>
					</table>
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
		$("#editTrRecordForm input,select").on("change",function(){
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
			startDate:new Date()
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
			if( !$('#editTrRecordForm').isValid({},conf, true)) {
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
				postFormData("editTrRecordForm",true,editResult);
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
	function editResult(data){
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
			clearValid("editTrRecordForm");
		}
		$("#sub").prop("disabled",false);
	}
	</script>
</body>
</html>