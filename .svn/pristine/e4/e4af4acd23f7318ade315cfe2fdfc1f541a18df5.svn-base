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
	<link href="${sr}css/layui.css" rel="stylesheet">

    <!--[if lt IE 9]>
      <script src="${sr}js/html5shiv.min.js"></script>
      <script src="${sr}js/respond.min.js"></script>
    <![endif]-->
</head>
<body class="v-page-body">
	<div class="page-container">
		<div class="panel">	
				<form id="addSerTeamForm" action="${webroot}serviceteam/addSerTeam.do">
				<input type="hidden" id="avatarUrl" name="avatarUrl">
					<div class="row">
					<table class="table table-bordered dataTable no-footer">
						<tr>
							<td class="col-xs-2" style="vertical-align: middle"><label>服务队名称<small><code >*</code></small></label></td>
							<td class="col-xs-4" style="vertical-align: middle">
								<input type="text"  class="form-control" name="name" placeholder="服务队名称(2-20位的汉字与字母)"   
									data-validation="required custom" data-validation-regexp="^[a-zA-Z\u4e00-\u9fa5]{2,20}$"
			                   		data-validation-error-msg-required="服务队名称为必填项"
			                   		data-validation-error-msg-custom="此项必须为2-20位的汉字与字母"
			                   		data-validation-error-msg-container="#nError"/>
                   			</td>
                   			<td class="col-xs-2" rowspan="3" style="vertical-align: middle">服务队图标</td>
                   			<td class="col-xs-4" rowspan="3"  style="vertical-align: middle;text-align:center">
								<img id="pic" alt="服务队头像" width="174" height="124" border="0" src="" style="margin-bottom: 5px">
 								<input type="file" name="file" class="layui-upload-file" lay-title="上传图片">
							</td>
						</tr>
						<tr>
                   			<td class="col-xs-2" style="vertical-align: middle"><label>所属机构<small><code >*</code></small></label></td>
							<td class="col-xs-4" style="vertical-align: middle">
								<div class="input-group" onclick="selectOrg()">
					    		 	<input type="hidden" name="orgId"  id="orgId"/>
									<input type="text"  class="form-control" name="orgName" id="orgName" readonly="readonly" placeholder="服务队所属机构"
									/>
									<span class="input-group-btn">
							       		 <button type="button" id="orgButton" class="btn   btn-primary  "><i class="fa fa-search"></i>
							             </button> 
						       		</span>
					       		</div>
                   			</td>
						</tr>
						<tr>
                   			<td class="col-xs-2" style="vertical-align: middle"><label>所属区域<small><code >*</code></small></label></td>
							<td class="col-xs-4" style="vertical-align: middle">
								<div class="input-group" onclick="selectArea()">
									<input type="hidden" name="areaId"  id="parentId"/>
									<input type="text"  class="form-control" name="parentName"  id="parentName" readonly="readonly" placeholder="服务队区域名称"
									data-validation="required" data-validation-error-msg-required="此项为必填项" data-validation-error-msg-container="#nError"/>
									<span class="input-group-btn">
							       		 <button type="button" id="areaButton" class="btn   btn-primary  "><i class="fa fa-search"></i>
							             </button> 
						       		</span>
								</div>
                   			</td>
						</tr>
						<tr>
							<td style="vertical-align: middle"><label>简介<small><code >*</code></small></label></td>
							<td colspan="3">
								<textarea id="text-area" class="form-control" rows="5" placeholder="服务队简介" name="summary"></textarea>
				      			<div>(剩余<label id="max-len">300</label>字符)</div>
                   			</td>
						</tr>
						<tr>
							<td><label>联系人<small><code >*</code></small></label></td>
							<td>
								<input type="text"  class="form-control" name="contact" placeholder="联系人名称"   
									data-validation="required length"  data-validation-length="1-10"  
							      	data-validation-error-msg-required="真实姓名为必填项"
							      	data-validation-error-msg-length="真实姓名长度在1-10个字符" 
			                   		data-validation-error-msg-container="#nError"/>
                   			</td>
                   			<td><label>联系方式<small><code >*</code></small></label></td>
							<td>
								<input type="text"  class="form-control" name="contactTel" placeholder="联系方式"   
									data-validation="required teleNumber"
									data-validation-error-msg-required="联系电话为必填项"
									data-validation-error-msg-container="#nError"/>
                   			</td>
						</tr>
						<tr>
							<td><label>传真</label></td>
							<td>
								<input type="text"  class="form-control" name="fax" placeholder="传真"   
							      	data-validation-error-msg-length="传真长度在1-10个字符" 
			                   		data-validation-error-msg-container="#nError"/>
                   			</td>
                   			<td><label>电子邮箱</label></td>
							<td>
								<input type="text"  class="form-control" name="email" placeholder="电子邮箱"   
								     data-validation-error-msg-email="邮件格式不正确" 
			                   		data-validation-error-msg-container="#nError"/>
                   			</td>
						</tr>
						<tr>
							<td><label>负责人<small><code >*</code></small></label></td>
							<td>
								<input type="text"  class="form-control" name="principal" placeholder="负责人名称"   
									data-validation="required length"  data-validation-length="1-10"  
							      	data-validation-error-msg-required="真实姓名为必填项"
							      	data-validation-error-msg-length="真实姓名长度在1-10个字符"
			                   		data-validation-error-msg-container="#nError"/>
                   			</td>
                   			<td><label>负责人联系方式<small><code >*</code></small></label></td>
							<td>
								<input type="text"  class="form-control" name="principalTel" placeholder="负责人联系电话"   
									data-validation="required teleNumber"
									data-validation-error-msg-required="联系电话为必填项"
									data-validation-error-msg-container="#nError"/>
                   			</td>
						</tr>
						
						<tr>
							
                   			<td class="col-xs-2"><label>服务队地址<small><code >*</code></small></label></td>
							<td class="col-xs-10" colspan="3">
								<input type="text"  class="form-control" name="address" placeholder="服务队地址"   
									data-validation="required length"  data-validation-length="1-30"  
							      	data-validation-error-msg-required="服务队地址为必填项"
							      	data-validation-error-msg-length="服务队地址长度在1-30个字符" 
							      	data-validation-error-msg-container="#nError"/>
                   			</td>
						</tr>
						
 						
<!--  						<tr>  -->
<%--  							<td class="col-xs-2"><label>上传头像<small><code >*</code></small></label></td>  --%>
<!-- 							<td class="col-xs-10" colspan="3" style="text-align:center">  -->
<!--  								<img id="pic" alt="服务队头像" width="164" height="124" border="0" src="" > -->
<!--  								<input type="file" name="file" class="layui-upload-file" lay-title="请上传一张图片吧亲"> -->
<!--                     		</td> -->
								
<!--  						</tr>  -->
					</table>
					</div>
					
			    </form>
<%-- 			    <form id="uploadForm" action="${webroot}file/upload.do" enctype="multipart/form-data" method="post" > --%>
<!-- 			    	<input type="file" name="file" id="imgFile"> <a class="btn btn-success btn-sm" onclick="uploadPic()">上传</a> -->
<!-- 			    </form> -->
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
	<script src="${sr}js/jquery.form.js" type="text/javascript"></script>
	<script src="${sr}js/jquery-ui.min.js" type="text/javascript"></script>
	<script src="${sr}layui.js" type="text/javascript"></script>
	<script type="text/javascript">
	layui.use(['jquery','layer','upload','laytpl'], function(){
	    $('#text-area').restrictLength($('#max-len'));
		
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
	    
		var errors=[];
		var conf = {
				validateOnBlur : false, 
				onElementValidate : function(valid, $el, $form, errorMess) {
			     if( !valid ){
			    	errors.push({el: $el, error: errorMess});
			     }
			  }
		 };
		 
		 layui.upload({
			   url: '${webroot}file/upload.do'
			  ,ext: 'jpg|png|gif'
			  ,success: function(data){
			   		var layer = layui.layer;
				 	if(data.success){
				 		var message = data.message;
				 		$("#avatarUrl").val(data.picPath);
				 		showPic(data.picPath);
						layer.msg(message,{time:1000});
				 	}
				 	else{
						var message = data.message;
						layer.msg(message,{time:1000});
					}
			  }
			}); 
		 
		$('#sub').on('click', function() {
			errors=[];
			if( !$('#addSerTeamForm').isValid({},conf, true)) {
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
				postFormData("addSerTeamForm",true,addResult);
		     } 
			
		});
		
});
	
   function addPic(){
   	var layer = layui.layer;
   		 layui.upload({
			   url: '${webroot}file/upload.do'
			  ,ext: 'jpg|png|gif'
			  ,success: function(data){
			   		var layer = layui.layer;
				 	if(data.success){
				 		var message = data.message;
				 		alert(data.picPath)
				 		$("#avatarUrl").val(data.picPath);
				 		showPic(data.picPath);
						layer.msg(message,{time:1000});
				 	}
				 	else{
						var message = data.message;
						layer.msg(message,{time:1000});
					}
			  }
			}); 
   }	
	
   //上传后展示图片	
   function showPic(picPath){
   		$('#pic').attr('src', '${webroot}file/readTempPic.do?ataUrl='+picPath); 
   }
	
   function	uploadAvatar(){
   		var layer = layui.layer;
		//iframe层-父子操作
		layer.open({
		  type: 2,
		  title:"上传头像",
		  area: ['600px', '300px'],
		  maxmin: true,
		  scrollbar: false,
		  content: ["${webroot}file/uploadAvatar.html","no"]
		});
   }
	
	function uploadPic(){
		if(checkData()){
			$("#uploadForm").ajaxSubmit({
				 type:"post",
				 enctype:"multipart/form-data",
				 dataType:"json",
				 success:function(data){
				 	var layer = layui.layer;
				 	if(data.success){
				 		var message = data.message;
				 		alert(data.picPath)
				 		$("#avatarUrl").val(data.picPath);
						layer.msg(message,{time:1000});
				 	}
				 	else{
						var message = data.message;
						layer.msg(message,{time:1000});
					}
				 },
				 error:function(msg){
					 
				 }
			});
		}
	}
	
	//JS校验form表单信息    
	function checkData(){   
	   var layer = layui.layer;
	   var fileDir = $("#imgFile").val();    
	   var suffix = fileDir.substr(fileDir.lastIndexOf("."));    
	   if("" == fileDir){ 
		   layer.msg('选择需要上传的图片！',{time:1000});
	       return false;    
	   }    
	   if(".jpg" != suffix && ".jpeg" != suffix && ".png" != suffix && ".gif" != suffix && ".bmp" != suffix){ 
		   layer.msg('选择正确的图片格式！',{time:1000});
	       return false;    
	   }    
	   return true;    
	}  
	
	//选择机构
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
	
	//选择区域
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
	
	function clearValid(formId){
		var els = $("#"+formId+" :input");
		els.removeClass("valid");
	}
	
	function closeFrame(){
		var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
		parent.layer.close(index);
	}
	
	/**
	**添加服务队的回调函数
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
			clearValid("addSerTeamForm");
		}
		$("#sub").prop("disabled",false);
	}
	</script>
</body>
</html>