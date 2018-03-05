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
    <style type="text/css">
    	select,select2-container input[type="text"],.uneditable-input{display:inline-block;height:20px;padding:4px 6px;margin-bottom:10px;font-size:14px;line-height:20px;color:#555555;-webkit-border-radius:4px;-moz-border-radius:4px;border-radius:4px;vertical-align:middle;}
    </style>
</head>
<body class="v-page-body">
	<div class="page-container ">
		<div class="panel" style="display: none" id="mainform">
			<form id="editUserForm" action="${webroot}user/editUser.do" >
			<div class="row">
				<input type="hidden" name="id" value="${user.id}">
				<table class="table table-bordered dataTable no-footer" >
					<tr>
						<td class="col-xs-2">
					    	<label >用户名</label>
					    </td>
					    <td class="col-xs-10">
							<input type="text" class="form-control" id="roleN" name="userName" disabled value="${user.userName}">
					    </td>
					</tr>				    
					<tr>
						<td>
						    <label >昵称<small ><code>*</code></small></label>
					    </td>
					    <td>
					      <input type="text" class="form-control" id="id1" name="nickName" placeholder="用户昵称" value="${user.nickName}"
					      		data-validation="required length"  data-validation-length="2-30" 
					      		data-validation-error-msg-required="昵称为必填项"
	                   			data-validation-error-msg-length="长度为2-30个字符之间" 
	                   			data-validation-error-msg-container="#nError2">
					    </td>
					</tr>
									    
					<tr>
						<td>
					    	<label >真实姓名<small ><code>*</code></small></label>
					    </td>
						<td>
							<input type="text" class="form-control" id="id4" name="realName" placeholder="真实姓名(必填项)" value="${user.realName }"
							data-validation="required length"  data-validation-length="1-10"  
							data-validation-error-msg-required="真实姓名为必填项"
							data-validation-error-msg-length="真实姓名长度在1-10个字符"
							data-validation-error-msg-container="#nError5">
					    </td>
				    </tr>
				    <tr>
				    	<td>
					    	<label >联系电话<small ><code>*</code></small></label>
					    </td>
						<td>
							<input type="text" class="form-control" id="id5" name="mobile" placeholder="联系电话" value="${user.mobile}"
							data-validation="required teleNumber" 
							data-validation-error-msg-required="联系电话为必填项" 
							data-validation-error-msg-container="#nError6">
					    </td>
					</tr>
				    
					<tr>
						<td>
					    	<label >电子邮箱<small ><code>*</code></small></label>
					    </td>
						<td>
							<input type="text" class="form-control" id="email" name="email" placeholder="电子邮箱" value="${user.email }"
							data-validation="required email" 
							data-validation-error-msg-required="电子邮箱为必填项" 
							data-validation-error-msg-email="邮件格式不正确"
							data-validation-error-msg-container="#nError6">
					    </td>
				    </tr>
				    <tr>
				    		<td>
					    		<label >归属机构</label>
					    	</td>
						    <td>
						    	<div class="input-group" onclick="selectOrg()">
					    		 	<input type="hidden" name="orgId"  id="orgId" value="${org.id}"/>
									<input type="text"  class="form-control" name="orgName" id="orgName" readonly="readonly" placeholder="所在机构"
									value="${org.name}"/>
									<span class="input-group-btn">
							       		 <button type="button" id="orgButton" class="btn   btn-primary  "><i class="fa fa-search"></i>
							             </button> 
						       		</span>
					       		</div>
							</td>						     
					    </tr>
					<tr>
						<td>				    
					    	<label >角色</label>
					    </td>
						<td>
			    		 	<select class="select2" id="roles" name="role" multiple="multiple" style="width: 100%;">
								<optgroup label="角色列表" id="rList">
								</optgroup>
							</select>
					    </td>
				    </tr>
					</table>
				</div>
				
				</form>
			</div>
			<div class="v-form-footer">
			  		<button type="button" id="sub" class="btn btn-sm btn-success"><i class="fa fa-floppy-o"></i>保存</button>
			   		<button type="button" class="btn btn-sm" onclick="closeFrame()"><i class="fa fa-remove"></i>关闭</button>
			</div>
		</div>
		<script id="templateData" type="text/html">
				{{#  layui.each(d.list, function(index, item){ }}	
			    	<option value="{{item.id}}" {{item.isOwn!=""?"selected":""}}>{{item.roleName}}</option>
				{{#  }); }}
		</script>
	
	<script src="${sr}js/jquery.min.js" type="text/javascript"></script>
	<script src="${sr}js/config.js" type="text/javascript"></script>
	<script src="${sr}js/jquery.form-validator.min.js" type="text/javascript"></script>
	<script src="${sr}js/jquery-ui.min.js" type="text/javascript"></script>
	<script src="${sr}layui.js" type="text/javascript"></script>
	<script src="${sr}js/baseutil.js" type="text/javascript"></script>
	<script src="${sr}js/select2.js" type="text/javascript"></script>
	
	<script type="text/javascript">
	layui.use(['jquery','layer','laytpl'], function(){
		
		var url="${webroot}user/getUserRoles.do?userId="+${user.id};
		postData(url, null, true, getUserRoleResult);
		
		$(document).ajaxComplete( function(event, jqXHR, options){
			$('.select2').select2({ placeholder: '  选择赋予用户的角色'});
			$("#mainform").show();
		});
		
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
			
			if( !$('#editUserForm').isValid({},conf, true)) {
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
				postFormData("editUserForm",true,editResult);
		    }
			
		})
		
		
		
	});
	
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
				$("#orgName").val(data.name);
				var orgId=data.id;
				/* if(!orgId){
					orgId=0;
				} */
				$("#orgId").val(orgId);
		  }
		});
	}
			
	function clearValid(formId){
		var els = $("#"+formId+" :input");
		els.removeClass("valid");
	}
	
 	//获取角色的回调函数
	function getUserRoleResult(data){
		var laytpl = layui.laytpl;
		var layer = layui.layer;
		if(data && data.success){
			var rolesHtml="";
			var page=data.roleViewsPage;
    		var list = page.list;
    		if(list && list.length > 0){
				var templateHtml = $("#templateData").html();
        		laytpl(templateHtml).render(page, function(result){
        		    $("#rList").html(result);
        		  });
			}
		}
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