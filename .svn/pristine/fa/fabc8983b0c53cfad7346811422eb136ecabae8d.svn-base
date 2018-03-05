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
				<form id="addOrgForm" action="${webroot}org/addOrg.do">
					<div>
					<table class="table table-bordered dataTable no-footer">
						<tr class="row">
							<td class="col-xs-2"><label for="inputEmail1">机构名称<small><code >*</code></small></label></td>
							<td class="col-xs-4">
									<input type="text"  class="form-control" name="name"   placeholder="机构名称(至少由两个汉字组成)" data-validation="required custom" data-validation-regexp="^[\u4e00-\u9fa5]{2,50}$"
								      	data-validation-error-msg-required="此项为必填项"
			                   			data-validation-error-msg-custom="该选项必须至少由两个汉字组成" 
			                   			data-validation-error-msg-container="#nError"/>
                   			</td>
                   			<!-- <td class="col-xs-2"><label for="inputEmail1">机构代码</label></td>
							<td class="col-xs-4">
									<input type="text"  class="form-control" name="code"   placeholder="机构代码" data-validation="custom" data-validation-regexp="^[0-9]{3,20}|[0-9]{0}$"
		                   			data-validation-error-msg-custom="区域编码格式不合法" 
		                   			data-validation-error-msg-container="#nError1"/>
                   			</td> -->
                   			<td class="col-xs-2"><label for="inputEmail1">上级机构<small><code >*</code></small></label></td>
							<td class="col-xs-4">
								<div class="input-group" ${empty org.id ? "onclick='selectOrg()'":""}>
									<input type="hidden" name="parentId"  id="parentId" value="${org.id}"/>
									<input type="text"  class="form-control" name="parentName" id="parentName" readonly="readonly" placeholder="上级机构名称" value="${org.name}"
									data-validation="required" data-validation-error-msg-required="此项为必填项" data-validation-error-msg-container="#nError"/>
									<span class="input-group-btn">
							       		 <button type="button" id="orgButton" class="btn   btn-primary  "><i class="fa fa-search"></i>
							             </button> 
						       		</span>
								</div>
                   			</td>
						</tr>
						<tr class="row">
							<td><label for="inputEmail2">机构类型<small><code >*</code></small></label></td>
							<td>
							<label class="radio-inline">
							  	<input type="radio" name="type" value="1" style="margin-top: 9px;"/>管理机构
							</label>
							<label class="radio-inline">
								<input type="radio" name="type" value="2" style="margin-top: 9px;"/>业务机构
							</label>
                   			</td>
                   			<td style="vertical-align: middle;"><label for="inputEmail3">级别<small><code >*</code></small></label></td>
							<td>
								<select class="form-control" name="grade" data-validation="required"
						    	data-validation-error-msg-required="此选项为必填" data-validation-error-msg-container="#nError1">
		           					<option value="">请选择</option>
		           					<option value="1">一级</option>
		           					<option value="2">二级</option>
		           					<option value="3">三级</option>
		           					<option value="4">四级</option>
	           					</select>
                   			</td>
						</tr>
						<tr class="row">
                   			<td class="col-xs-2"><label for="inputEmail1">所属区域<small><code >*</code></small></label></td>
							<td class="col-xs-4">
								<div class="input-group" onclick="selectArea()">
									<input type="hidden" name="areaId"  id="areaId"/>
									<input type="text"  class="form-control" name="areaName"  id="areaName" readonly="readonly" placeholder="区域名称"
									data-validation="required" data-validation-error-msg-required="此项为必填项" data-validation-error-msg-container="#nError"/>
									<span class="input-group-btn">
							       		 <button type="button" id="areaButton" class="btn   btn-primary  "><i class="fa fa-search"></i>
							             </button> 
						       		</span>
								</div>
                   			</td>
                   			<td style="vertical-align: middle;"><label for="inputEmail3">所属系统<small id="icon"><code >*</code></small></label></td>
							<td>
								<select class="form-control" name="systemCode" id="systemCode"
						    	data-validation-error-msg-required="此选项为必填" data-validation-error-msg-container="#nError1">
	           					</select>
                   			</td>
						</tr>
						<tr class="row">
							<td class="col-xs-2"><label for="inputEmail1">联系人<small><code >*</code></small></label></td>
							<td class="col-xs-4">
									<input type="text"  class="form-control" name="contact"   placeholder="联系人"
									data-validation="required" data-validation-error-msg-required="此选项为必填" data-validation-error-msg-container="#nError1"/>
                   			</td>
                   			<td class="col-xs-2"><label for="inputEmail1">联系方式<small><code >*</code></small></label></td>
							<td class="col-xs-4">
									<input type="text"  class="form-control" name="mobile"   placeholder="联系电话" data-validation="required"
								data-validation-error-msg-required="联系电话为必填项" 
								data-validation-error-msg-container="#nError6"/>
                   			</td>
						</tr>
						<tr class="row">
                   			<td class="col-xs-2"><label for="inputEmail1">电子邮箱<small><code >*</code></small></label></td>
							<td class="col-xs-4" colspan="3">
									<input type="text"  class="form-control" name="email"   placeholder="邮箱地址" 
								       data-validation-error-msg-email="邮件格式不正确"
								       data-validation-error-msg-container="#nError6"/>
                   			</td>
						</tr>
						<tr class="row">
							<td class="col-xs-2"><label for="inputEmail1">组织地址<small><code >*</code></small></label></td>
							<td class="col-xs-4" colspan="3">
									<input type="text"  class="form-control" name="address"   placeholder="机构地址"
									data-validation="required" data-validation-error-msg-required="此选项为必填" data-validation-error-msg-container="#nError1"/>
                   			</td>
                   		</tr>
						<tr class="row">
							<td style="vertical-align: middle;"><label for="inputEmail3">备注</label></td>
							<td colspan="3">
								<textarea id="text-area" class="form-control" rows="5" name="summary"></textarea>
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
		$("#addOrgForm input,select").on("change",function(){
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
		
		$("#addOrgForm input[name='type']").on("change",function(){
			var typeValue=$("input[name='type']:checked").val();
			if("1"==typeValue){
				$("#systemCode").removeAttr("data-validation");
				$("#icon").hide();
			}
			if("2"==typeValue){
				$("#systemCode").attr("data-validation","required");
				$("#icon").show();
			}
		});
		
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
			var typeEl=$("input[name='type']:checked");
			if( !$('#addOrgForm').isValid({},conf, true)) {
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
		   	}else if(!typeEl.length){
		   		layer.alert("机构类型为必填项", {icon: 5});
		   		return false;
		   	}else {
		   		$("#sub").prop("disabled",true);
				postFormData("addOrgForm",true,addOrg);
		    }
			
		});
		showOrgSystem();
});
	
	//显示机构系统
	function showOrgSystem(){
		var url="${webroot}org/orgSystem.do";
		postData(url, null, true, showOrgSystemResult); 
	}
	
	function showOrgSystemResult(data){
		if (data.success) {
			/* var html=""; */
			var html="<option value=\"\">请选择</option>";
			var list =data.orgSystemList;
			for (var i = 0; i < list.length; i++) {
				html+="<option value='"+list[i].id+"'>"+list[i].name+"</option>";
			}
			$("#systemCode").prepend(html);
		} 
	}
	
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
				$("#areaName").val(data.name);
				var areaId=data.id;
				if(!areaId){
					areaId=0;
				}
				$("#areaId").val(areaId);
		  }
		});
	}
	
	function selectOrg(){
		var layer = parent.layer;
		layer.open({
		  type: 2,
		  title:"选择机构",
		  area: ['300px', '450px'],
		  maxmin: true,
		  scrollbar: false,
		  content: ["${webroot}org/selectOrg.html","no"],
		  btn : [ '确定', '取消' ], 
		  yes : function(index, layero) {
				var iframeWin = top.window[layero.find('iframe')[0]['name']]; 
				var data=iframeWin.selectOrg();
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
	function addOrg(data){
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
			clearValid("addOrgForm");
		}
		$("#sub").prop("disabled",false);
	}
	</script>
</body>
</html>