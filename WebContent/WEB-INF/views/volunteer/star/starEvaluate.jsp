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
			<form id="addStarForm" action="${webroot}star/addStarEvaluate.do" >
				<input type="hidden" value="${volunteer.id}" name="volunteerId">
				<div class="row">
					<table class="table table-bordered dataTable no-footer">
						<tr>
							<td class="col-xs-3">
						    	<label >姓名</label>
						    </td>
						    <td class="col-xs-8">
						    	${volunteer.realName}
						    </td>
					    </tr>
				    	<tr>
							<td>
					    		<label >证件号</label>
					   		</td>
					   		<td>
						      	${volunteer.idcard}
		                   	</td>
				    	 </tr>
				    
				    	<tr>
							<td>
						    	<label >性别</label>
						    </td>
						    <td>
								${volunteer.sex}
		                   	</td>
				    	</tr>
				    	<tr>
				    		<td>
						    	<label >服务时长</label>
						    </td>
							<td>
								${empty volunteer.serviceHour?0:volunteer.serviceHour}(小时)
						    </td>
					    </tr>
				    	<tr>
				    		<td>
						    	<label >星级</label>
						    </td>
							<td>
						     	<select class="form-control" name="star" id="star">
		           					<option value="0" >无星</option>
		           					<option value="1" >一星</option>
		           					<option value="2" >二星</option>
		           					<option value="3" >三星</option>
		           					<option value="4" >四星</option>
		           					<option value="5" >五星</option>
           						</select>
						    </td>
					    </tr>
					    <tr>
					    	<td height="150px" style="vertical-align: middle;">
						    	<label >评价理由</label>
						    </td>
							<td>
								<textarea style="width: 100%;height: 130px" name="evaluateContent"></textarea>
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
		
		var currDate=getFormatDate(null,"yyyy-MM-dd");
		$("#certDate").val(currDate);
		
		$("#addStarForm input,select").on("change",function(){
				$(this).validate(function(valid, elem) {
			});
		});
	 
		$.validate({
		 	form : "#addStarForm",
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
			if( !$('#addStarForm').isValid({},conf, true)) {
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
				postFormData("addStarForm",true,addResult);
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
	**添加志愿者星级评定信息的回调函数
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
			clearValid("addStarForm");
		}
		$("#sub").prop("disabled",false);
	}
	
	</script>
</body>
</html>