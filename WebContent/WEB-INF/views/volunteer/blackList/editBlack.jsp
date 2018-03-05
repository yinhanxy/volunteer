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
	<%-- <link href="${sr}css/xenon-forms.css" rel="stylesheet"> --%>
	<link href="${sr}css/select2-bootstrap.min.css" rel="stylesheet">
	<link href="${sr}css/select2.min.css" rel="stylesheet"> 
    <!--[if lt IE 9]>
      <script src="${sr}js/html5shiv.min.js"></script>
      <script src="${sr}js/respond.min.js"></script>
    <![endif]-->
</head>
<body class="v-page-body">
	<div class="page-container">
		<div class="panel" id="mainform">
			<form id="editBlackForm" action="${webroot}blackList/editBlack.do" >
				<input type="hidden" name="userId" value="${userId}"/>
				<div class="row">
					<table class="table table-bordered dataTable no-footer">
						<tr>
							<td class="col-xs-3">
						    	<label >姓名</label>
						    </td>
						    <td class="col-xs-9">
						    
						    	${volunteerView.realName}
						    </td>
					    </tr>
				    	<tr>
							<td>
					    		<label >证件号</label>
					   		</td>
					   		<td>
					   			${volunteerView.idcard}
		                   	</td>
				    	 </tr>
				    
					    <tr>
					    	<td height="150px" style="vertical-align: middle;">
						    	<label >拉黑原因<small ><code>*</code></small></label>
						    </td>
							<td>
								<input type="hidden" name="id"  id="blackId" value="${volunteerView.blackId}"/>
								<textarea style="width:100%;height:146px;" name="reason"
									data-validation="required" 
						      		data-validation-error-msg-required="此项为必填项"
		                   			data-validation-error-msg-container="#nError2">${volunteerView.blackOperateReason}</textarea>
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
	<script src="${sr}js/select2.js" type="text/javascript"></script>
	
	<script type="text/javascript">
	layui.use(['jquery','layer','laytpl'], function(){
		
		$("#editBlackForm input,select").on("change",function(){
				$(this).validate(function(valid, elem) {
			});
		});
	 
		$.validate({
		 	form : "#editBlackForm",
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
		
		
		var error="";
		 
		 
		var errors =[],
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
		$('#sub').on('click', function() {
			errors =[];
			if( !$('#editBlackForm').isValid({},conf, true)) {
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
				postFormData("editBlackForm",true,editResult);
		    }
			
		})
		
		
		
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
	**编辑黑名单信息的回调函数
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
			clearValid("editBlackForm");
		}
		$("#sub").prop("disabled",false);
	}
	
	</script>
</body>
</html>