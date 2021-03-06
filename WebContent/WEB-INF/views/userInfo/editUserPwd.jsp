<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
    <!--[if lt IE 9]>
      <script src="${sr}js/html5shiv.min.js"></script>
      <script src="${sr}js/respond.min.js"></script>
    <![endif]-->
</head>
<body class="v-page-body">
	<div class="page-container ">
		<div class="panel" style="display: none" id="mainform">
			<form id="editUserPwdForm" action="${webroot}userInfo/editUserPwd.do" >
			<div class="row">
				<table class="table table-bordered dataTable no-footer">
					<tr>
						<td class="col-xs-3">
					    	<label >旧密码<small ><code>*</code></small></label>
					    </td>
					    <td class="col-xs-9">
							<input type="password" class="form-control" id="userPwd" name="userPwd" placeholder="旧密码"
							data-validation="length"  data-validation-length="5-12" 
		                   	data-validation-error-msg-length="密码长度在5-12个字符之间" 
		                   	data-validation-error-msg-container="#nError3"/>
					    </td>
					</tr>				    
					<tr>
						<td>
						    <label >新密码<small ><code>*</code></small></label>
					    </td>
					    <td>
					      <input type="password" class="form-control" id="userNewPwd" name="userNewPwd" placeholder="新密码" 
					      		data-validation="length"  data-validation-length="5-12" 
	                   			data-validation-error-msg-length="密码长度在5-12个字符之间" 
	                   			data-validation-error-msg-container="#nError2"/>
					    </td>
					</tr>
									    
					<tr>
						<td>
					    	<label >重复密码<small ><code>*</code></small></label>
					    </td>
						<td>
							<input type="password" class="form-control" id="userNewPwdAgain" name="userNewPwdAgain" placeholder="重复密码" 
							data-validation="required confirmation" data-validation-length="5-12"
							 data-validation-confirm="userNewPwd"	
						      data-validation-error-msg-required="此项为必填项"	
						      data-validation-error-msg-confirmation="密码不一致"	
						      data-validation-error-msg-container="#nError4"/>
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
	<script src="${sr}js/baseutil.js" type="text/javascript"></script>
	<script src="${sr}js/jquery.form-validator.min.js" type="text/javascript"></script>
	<script src="${sr}layui.js" type="text/javascript"></script>
	
	<script type="text/javascript">
	layui.use(['layer'], function(){
		$("#mainform").show();
		
		$("#editUserPwdForm input,select").on("change",function(){
			$(this).validate(function(valid, elem) {
			});
		});
 
 		$.validate({
	 	form : "#editUserPwdForm",
		modules : 'security',
		validateOnBlur : false, 
		onElementValidate : function(valid, $el, $form, errorMess) {
		    if( !valid ) {
		    	 layui.layer.tips(errorMess, $el,{
		    		  tips: [1, '#f57a78'] ,time:1000//还可配置颜色
		    	 });
		    	 
		    }
		 },
		onError : function($form) {
		          return false;
		      },  
		onSuccess: function() {
		       return true;
		     }
   		});
		
		
		var errors =[],
		conf = {
			//modules :'security',
			validateOnBlur : false, 
			onElementValidate : function(valid, $el, $form, errorMess) {
			     if( !valid ) {
			    	errors.push({el: $el, error: errorMess});
					return ;
			     }
				  	
			  } 
			 
		  };
		$('#sub').on('click', function() {
			errors =[];
			
			if( !$('#editUserPwdForm').isValid({},conf, true)) {
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
				postFormData("editUserPwdForm",true,editResult);
		    } 
			
		});
		
		
		
	});
			
	/**
	**编辑密码的回调函数
	*/
	function editResult(data){
		var layer = layui.layer;
		var message;
		if(data && data.success){
			message=data.message;
			layer.open({
				  title: '提示信息'
				  ,content: message+',是否重新登录！'
				  ,icon: 6
				  ,btn:['确定']
				  ,btn1:function(){
					  window.top.location.href="/volunteer/logout.html";
				  }
			      ,cancel: function(){ 
			    		  return false; 
			    		}    
				});    
		}else{
		    message=data.message.message;
			var fileName=data.message.propertyName;
			var el=$("input[id='"+fileName+"'][type='password']");
			layui.layer.tips(message, el,{
	    		  tips: [1, '#f57a78'] //还可配置颜色
	    	 });
		}
		$("#sub").prop("disabled",false);
	}
	
	//关闭
	function closeFrame(){
		var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
		parent.layer.close(index);
	}
	
	</script>
</body>
</html>