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
				<form id="addAreaForm" action="${webroot}area/addArea.do">
					<div class="row">
					<table class="table table-bordered dataTable no-footer">
						<tr>
							<td class="col-xs-2"><label for="inputEmail1">上级区域<small><code >*</code></small></label></td>
							<td class="col-xs-4">
								<div class="input-group" onclick="selectArea()">
									<input type="hidden" name="parentId"  id="parentId"/>
									<input type="text"  class="form-control" name="parentName"  id="parentName" readonly="readonly" placeholder="上级区域名称"
									data-validation="required" data-validation-error-msg-required="此项为必填项" data-validation-error-msg-container="#nError"/>
									<span class="input-group-btn">
							       		 <button type="button" id="areaButton" class="btn   btn-primary  "><i class="fa fa-search"></i>
							             </button> 
						       		</span>
								</div>
                   			</td>
						</tr>
						<tr>
							<td><label for="inputEmail2">区域名称<small><code >*</code></small></label></td>
							<td>
								<input type="text" class="form-control" name="name" placeholder="区域名称(至少由两个汉字组成)" data-validation="required custom" data-validation-regexp="^[\u4e00-\u9fa5]{2,50}$"
								      	data-validation-error-msg-required="此项为必填项"
			                   			data-validation-error-msg-custom="该选项必须至少由两个汉字组成" 
			                   			data-validation-error-msg-container="#nError"/>
                   			</td>
						</tr>
						<tr>
							<td style="vertical-align: middle;"><label for="inputEmail3">区域编码</label></td>
							<td><input name="code" class="form-control" data-validation="custom" data-validation-regexp="^[0-9]{3,20}|[0-9]{0}$"
		                   			data-validation-error-msg-custom="区域编码格式不合法" 
		                   			data-validation-error-msg-container="#nError1"/>
                   			</td>
						</tr>
						<tr>
							<td style="vertical-align: middle;"><label for="inputEmail3">区域类型</label></td>
							<td>
								<select class="form-control" name="type" >
		           					<option value="0">省份</option>
		           					<option value="1">地市州</option>
		           					<option value="2">区县</option>
	           					</select>
                   			</td>
						</tr>
						<tr>
							<td style="vertical-align: middle;"><label for="inputEmail3">备注</label></td>
							<td>
								<textarea id="text-area" class="form-control" rows="5" name="remark"></textarea>
				      			<div>(剩余<label id="max-len">100</label>字符)</div>
                   			</td>
						</tr>
					</table>
					</div>
			    </form>
		</div>
	</div>
	
	<div class="v-form-footer">
	  			<button type="button" id="sub" class="btn btn-sm btn-success" ><i class="fa fa-plus"></i>新增</button>
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
		$("#addAreaForm input,select").on("change",function(){
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
			if( !$('#addAreaForm').isValid({},conf, true)) {
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
				postFormData("addAreaForm",true,addArea);
		    }
			
		});
});
	
	function clearValid(formId){
		var els = $("#"+formId+" :input");
		els.removeClass("valid");
	}
	
	function selectArea(){
		var layer = parent.layer;
		//iframe层-父子操作
		layer.open({
		  type: 2,
		  title:"选择区域",
		  area: ['300px', '450px'],
		  maxmin: true,
		  scrollbar: false,
		  content: ["${webroot}area/selectArea.html","no"],
		  btn : [ '确定', '取消' ], 
		  yes : function(index, layero) {
				var iframeWin = top.window[layero.find('iframe')[0]['name']]; 
				var data=iframeWin.selectArea();
				$("#parentName").val(data.name);
				var parentId=data.id;
				if(!parentId){
					parentId=0;
				}
				$("#parentId").val(parentId);
		  }
		});
	}
	
	function closeFrame(){
		var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
		parent.layer.close(index);
	}
	
	/**
	**添加区域的回调函数
	*/
	function addArea(data){
		var layer = layui.layer;
		var message;
		if(data && data.success){
			message=data.message;
			layer.alert(message, {icon: 6},function(index){
			    closeFrame();
			    parent.refresh();
			  });
		}else{
			errorTips(data);
			clearValid("addAreaForm");
		}
		$("#sub").prop("disabled",false);
	}
	</script>
</body>
</html>