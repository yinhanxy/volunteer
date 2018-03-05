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
	<link href="${sr}css/bootstrap-datetimepicker.min.css" rel="stylesheet">
    <!--[if lt IE 9]>
      <script src="${sr}js/html5shiv.min.js"></script>
      <script src="${sr}js/respond.min.js"></script>
    <![endif]-->
</head>
<body class="v-page-body">
	<div class="page-container ">
		<div class="panel">	
				<form name="editCheckTime" id="editCheckTimeForm" action="${webroot}cert/editCheckTime.do" >
						<input type="hidden" name="id" value="${checkTime.id}">
						<div class="row">
							<table class="table table-bordered dataTable no-footer">
							<tr>
								<td class="col-xs-4">
						    		<label for="inputEmail1" >年份<small><code >*</code></small></label>
						    	</td>
						    	<td class="col-xs-8">
									${checkTime.year}年
						    	</td>
				   			</tr>
				    		<tr>
				    			<td style="vertical-align: middle;">
						    	 <label for="inputEmail2" >开始时间<small><code >*</code></small></label>
							    </td>
							    <td>
							   		<input type="text"  class="form-control" name="startTime" id="startTime" placeholder="开始时间" 
							   			value="<fmt:formatDate value="${checkTime.startTime}" pattern="yyyy-MM-dd HH:mm"/>"  
								      	data-validation="required" 
								      	data-validation-error-msg-required="此项必填"
			                   			data-validation-error-msg-container="#nError"
			                   			/>
							    </td>
				    		</tr>
				    		<tr>
				    			<td style="vertical-align: middle;">
				    				<label for="inputEmail3">结束时间<small><code >*</code></small></label>
					    		</td>
					    		<td >
								   	<input type="text"  class="form-control" name="endTime" id="endTime" placeholder="结束时间"   
								      	value="<fmt:formatDate value="${checkTime.endTime}" pattern="yyyy-MM-dd HH:mm"/>"
								      	data-validation="required"
								      	data-validation-error-msg-required="此项必填"
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
	<script type="text/javascript" src="${sr}js/bootstrap-datetimepicker.min.js" charset="UTF-8"></script>
	<script type="text/javascript" src="${sr}js/locales/bootstrap-datetimepicker.zh-CN.js" charset="UTF-8"></script>
	<script type="text/javascript">
	layui.use(['jquery','layer','laytpl'], function(){
		$('#startTime').datetimepicker({
			language:  'zh-CN',
	        weekStart: 1,
			autoclose: 1,
			todayHighlight: 1,
			startView: 2,
			minView: 0,
			forceParse: true,
			pickerPosition:"bottom-right",
		}).on('click', function(ev){
	        $("#startTime").datetimepicker("setEndDate",$("#endTime").val());
	    });
		$('#endTime').datetimepicker({
			language:  'zh-CN',
	        weekStart: 1,
			autoclose: 1,
			todayHighlight: 1,
			startView: 2,
			minView: 0,
			forceParse: true,
			pickerPosition:"bottom-right",
		}).on('click', function(ev){
	        $("#endTime").datetimepicker("setStartDate",$("#startTime").val());
	    });
		
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
			
			if( !$('#editCheckTimeForm').isValid({},conf, true)) {
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
				postFormData("editCheckTimeForm",true,editResult);
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
			clearValid("editCheckTimeForm");
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