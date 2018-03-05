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
				<form name="addConfig" id="addConfigForm" action="${webroot}config/addConfig.do" >
						<div class="row">
							<table class="table table-bordered dataTable no-footer">
							<tr>
								<td class="col-xs-2">
						    		<label for="inputEmail1" >名称<small><code >*</code></small></label>
						    	</td>
						    	<td class="col-xs-10">
								      <input type="text"  class="form-control" name="name" placeholder="配置名称(4-30位的数字、字母和下划线组合)"   
								      	data-validation="required custom" data-validation-regexp="^[a-zA-Z_0-9]{4,30}$"
								      	data-validation-error-msg-required="此项必须为4-30位的数字、字母和下划线"
			                   			data-validation-error-msg-custom="此项必须为4-30位的数字、字母和下划线" 
			                   			data-validation-error-msg-container="#nError"
			                   			/>
						    	</td>
				   			</tr>
				    		<tr>
				    			<td style="vertical-align: middle;">
						    	 <label for="inputEmail2" >内容<small><code >*</code></small></label>
							    </td>
							    <td>
							   		<textarea id="text-area" class="form-control" rows="3" placeholder="配置内容" name="content" style="resize: none"
										data-validation="required "
							      		data-validation-error-msg-required="配置内容为必填项"
			                			data-validation-error-msg-container="#nError"></textarea>
							    	<div>(剩余<label id="content-max">200</label>字符)</div>
							    </td>
				    		</tr>
				    		<tr>
				    			<td style="vertical-align: middle;">
				    				<label for="inputEmail3"  >说明<small><code >*</code></small></label>
					    		</td>
					    		<td >
								   
								      <textarea id="text-area" class="form-control" rows="3" placeholder="配置说明" name="remark" style="resize: none"
								      data-validation="required" data-validation-error-msg-required="配置说明为必填选项" data-validation-error-msg-container="#nError"></textarea>
								      <div >(剩余<label id="remark-max">50</label>字符) </div>
					    		</td>
					    	</tr>
					    	<tr>
					    		<td>
				    	 			<label for="inputEmail3" >类型</label>
					    		</td>
					    		<td>
								      <input type="text"  class="form-control" name="type" placeholder="配置类型(最大长度为30个字符)"   
				                   			data-validation="length" data-validation-length="0-30"
				                   			data-validation-error-msg-length="配置类型最大长度为30个字符" 
				                   			data-validation-error-msg-container="#nError"/>  
								      	
					    		</td>
							</tr>
							<tr>
								<td>
				    				<label for="inputEmail3" >排序号<small><code >*</code></small></label>
					    		</td>
					    		<td >
								    	 <input type="text"  class="form-control" name="orderNo" placeholder="配置排序号(最大为5位正整数)"   
									      	data-validation="required number length" data-validation-length="1-5"
									      	data-validation-error-msg-required="此项为必填项"
				                   			data-validation-error-msg-number="内容必须为正整数"
				                   			data-validation-error-msg-length="排序号最大为5位的正整数" 
				                   			data-validation-error-msg-container="#nError"
				                   			/>  	
					    		</td>
					    	</tr>
           					</table>
           				</div>
				    </form>
		</div>
	</div>
	<div class="v-form-footer">
	  			<button type="button" id="sub" class="btn btn-sm btn-success"><i class="fa fa-plus"></i>新增</button>
	   			<button type="button" class="btn btn-sm" onclick="closeFrame()"><i class="fa fa-remove"></i>关闭</button>
	</div>
	<script src="${sr}js/jquery.min.js" type="text/javascript"></script>
	<script src="${sr}js/config.js" type="text/javascript"></script>
	<script src="${sr}js/baseutil.js" type="text/javascript"></script>
	<script src="${sr}js/jquery.form-validator.min.js" type="text/javascript"></script>
	<script src="${sr}js/jquery-ui.min.js" type="text/javascript"></script>
	<script src="${sr}layui.js" type="text/javascript"></script>
	<script type="text/javascript">
	layui.use(['jquery','layer','laytpl'], function(){
		
		$("#text-area[name='content']").restrictLength($('#content-max'));
		$("#text-area[name='remark']").restrictLength($('#remark-max'));
		
		$("#addConfigForm input,select ").on("change",function(){
			$(this).validate(function(valid, elem) {
			});
		});
		
		$.validate({
			validateOnBlur : false, 
			onElementValidate : function(valid, $el, $form, errorMess) {
				 if(!valid){
					 layui.layer.tips(errorMess, $el,{
			    		  tips: [1, '#f57a78'] //还可配置颜色
			    	 });
				 }
			}
		});
		
		
		var errors=[],
		conf = {
				validateOnBlur : false, 
				onElementValidate : function(valid, $el, $form, errorMess) {
			     if( !valid ){
			    	errors.push({el: $el, error: errorMess});
			     }
			  }
		 };
		$('#sub').on('click', function() {
			errors=[];
			if( !$('#addConfigForm').isValid({},conf, true)) {
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
				postFormData("addConfigForm",true,addResult);
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
	**添加系统配置参数的回调函数
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
			errorTips(data);
			clearValid("addUserForm");
		}
		$("#sub").prop("disabled",false);
	}
	</script>
</body>
</html>