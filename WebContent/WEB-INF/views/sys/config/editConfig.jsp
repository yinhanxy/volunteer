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
	<link href="${sr}css/xenon-forms.css" rel="stylesheet">
	<script src="${sr}js/jquery.min.js" type="text/javascript"></script>
	<script src="${sr}js/baseutil.js" type="text/javascript"></script>
    <!--[if lt IE 9]>
      <script src="${sr}js/html5shiv.min.js"></script>
      <script src="${sr}js/respond.min.js"></script>
    <![endif]-->
</head>
<body class="v-page-body">
	<div class="page-container ">
		<div class="panel">
			<form id="editConfigForm" action="${webroot}config/editConfig.do" class="form-horizontal">
				<input type="hidden" name="id" value="${config.id}">
				<input type="hidden"  name="name" value="${config.name}">
				<div class="row">
					<table class="table table-bordered dataTable no-footer">
						<tr>			
							<td class="col-xs-2">				
							    <label >名称</label>
							</td>
							<td class="col-xs-10">
								<input type="text" class="form-control" disabled value="${config.name}">
							 </td>
						</tr>
						<tr>								    
							<td style="vertical-align: middle;">
							    <label >内容<small><code >*</code></small></label>
							</td>
							<td>
							      <textarea id="text-area" class="form-control" rows="3" placeholder="配置内容" name="content" style="resize: none"
							      		data-validation="required" 
							      		data-validation-error-msg-required="配置内容为必填项"
			                			data-validation-error-msg-container="#nError">${config.content }</textarea>
			                			<div>(剩余<label id="content-max">200</label>字符)</div>
							 </td>
						</tr>   
						<tr>	
								<td style="vertical-align: middle;">
							    	<label>说明<small><code>*</code></small></label>
								</td>
								<td>
							      <textarea id="text-area" class="form-control" rows="3" placeholder="配置说明" name="remark" style="resize: none"
							      	data-validation="required" data-validation-error-msg-required="配置说明为必填选项" data-validation-error-msg-container="#nError">${config.remark}</textarea>
							      	<div>(剩余<label id="remark-max">50</label>字符)</div>
								</td>
						</tr>
						<tr>
								<td>
							    	<label >类型</label>
							    </td>
							    <td>
							      <input type="text"  class="form-control" name="type" placeholder="配置类型(最大长度为30个字符)"  value="${config.type}" 
			                   			data-validation="length" data-validation-length="0-30"
			                   			data-validation-error-msg-length="配置类型最大长度为30位" 
			                   			data-validation-error-msg-container="#nError"/>
			                   	</td>  
						 </tr>
						 <tr>
								<td>
							    	<label >排序号<small><code>*</code></small></label>
							    </td>
							    <td>
							      <input type="text"  class="form-control" name="orderNo" placeholder="配置排序号(最大为5位正整数)"   value="${config.orderNo}"
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
  		<button type="button" id="sub" class="btn btn-sm btn-success"><i class="fa fa-floppy-o"></i>保存</button>
   		<button type="button" class="btn btn-sm" onclick="closeFrame()"><i class="fa fa-remove"></i>关闭</button>
	</div>
	<script src="${sr}js/config.js" type="text/javascript"></script>
	
	<script src="${sr}js/jquery.form-validator.min.js" type="text/javascript"></script>
	<script src="${sr}js/jquery-ui.min.js" type="text/javascript"></script>
	<script src="${sr}layui.js" type="text/javascript"></script>
	<script src="${sr}js/select2.min.js" type="text/javascript"></script>
	
	<script type="text/javascript">
	layui.use(['jquery','layer','laytpl'], function(){

		$("#text-area[name='content']").restrictLength($('#content-max'));
		$("#text-area[name='remark']").restrictLength($('#remark-max'));
		
		var errors =[],
		conf = {
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
			
			if( !$('#editConfigForm').isValid({},conf, true)) {
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
				postFormData("editConfigForm",true,editResult);
		    }
			
		})
		
		
		
	});
		
	function clearValid(formId){
		var els = $("#"+formId+" :input");
		els.removeClass("valid");
	}
	
	/**
	**编辑角色的回调函数
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
			clearValid("addUserForm");
		}
		$("#sub").prop("disabled",false);
	}
		
	
	function closeFrame(){
		var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
		parent.layer.close(index);
	}
	
	</script>
</body>
</html>