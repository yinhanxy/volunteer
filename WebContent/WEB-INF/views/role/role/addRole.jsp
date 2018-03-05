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
				<form id="addRoleForm" action="${webroot}role/addRole.do">
					<div class="row">
					<table class="table table-bordered dataTable no-footer">
						<tr>
							<td class="col-xs-2"><label for="inputEmail1">角色名称<small><code >*</code></small></label></td>
							<td class="col-xs-10">
								<input type="text"  class="form-control" name="roleName" placeholder="角色名称(2-10位的汉字与字母)"   
									data-validation="custom" data-validation-regexp="^[a-zA-Z\u4e00-\u9fa5]{2,10}$"
			                   		data-validation-error-msg-custom="此项必须为2-10位的汉字与字母"
			                   		data-validation-error-msg-container="#nError"/>
                   			</td>
						</tr>
						<tr>
							<td><label for="inputEmail2">角色类型<small><code >*</code></small></label></td>
							<td><select class="form-control" name="roleType" data-validation="required"
						    	data-validation-error-msg-required="此选项为必填" data-validation-error-msg-container="#nError1">
						    	<option value="">请选择</option>
	           					<option value="0">普通角色</option>
	           					<option value="1">系统角色</option>
	           				</select>
                   			</td>
						</tr>
						<tr>
							<td style="vertical-align: middle;"><label for="inputEmail3">角色描述</label></td>
							<td><textarea id="text-area" class="form-control" rows="3" placeholder="角色描述" name="roleDesc"></textarea>
				      			<div>(剩余<label id="max-len">100</label>字符)</div>
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
		$("#addRoleForm input,select").on("change",function(){
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
		
		
	    $('#text-area').restrictLength($('#max-len'));
		
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
			if( !$('#addRoleForm').isValid({},conf, true)) {
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
				postFormData("addRoleForm",true,addResult);
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
	**添加角色的回调函数
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
			clearValid("addRoleForm");
		}
		$("#sub").prop("disabled",false);
	}
	</script>
</body>
</html>