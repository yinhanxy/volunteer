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
    <meta charset="utf-8" >
    <meta http-equiv="X-UA-Compatible" content="IE=edge,Chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    
    <title>湖北省文化志愿者管理系统</title>
	
	<link href="${sr}css/font-awesome/css/linecons.css" rel="stylesheet">
	<link href="${sr}css/font-awesome/css/font-awesome.min.css" rel="stylesheet">
    <link href="${sr}css/bootstrap.min.css" rel="stylesheet">
   <%--  <link href="${sr}css/xenon.min.css" rel="stylesheet"> --%>
	<link href="${sr}css/base.css" rel="stylesheet">
	<link href="${sr}css/new.css" rel="stylesheet">
	<link href="${sr}css/iCheck/blue.css?v=1.0.2" rel="stylesheet">
    <!--[if lt IE 9]>
      <script src="${sr}js/html5shiv.min.js"></script>
      <script src="${sr}js/respond.min.js"></script>
    <![endif]-->
    <style type="text/css">
    	.table-bordered > thead > tr > th {
    		background-color: #F5F5F6;
    	}
    	body{
		   	font-family: "open sans", "Helvetica Neue", Helvetica, Arial, sans-serif;
		    font-size: 13px;
		    color: rgb(103, 106, 108);
    	}
    	.footer-right{
    		padding-top: 0;
		    height: 40px;
		    position: fixed;
		    line-height: 60px;
		    background: #fff;
		    z-index: 99;
		    bottom: 20px;
		    right: 20px;
		    float: right;
    	}
    	a.layui-layer-btn1 {
		        background-color: #DCDCDC;
    			color: #000040;
		}    
    </style>
    
</head>
<body >
	
	<div style="padding: 10px;" class="form-inline">
		<div class="panel panel-default">
		  <!-- <div class="panel-heading">角色信息</div> -->
		  <!-- <div class="panel-body"> -->
			  <form method="post"  enctype="multipart/form-data" action="${webroot}file/upload.do"  id="uploadForm" >
			  <input type="hidden" id="avatarUrl" name="trainId" >
			  <table class="table" style="margin:0">
			  		<tbody>
					<tr style="background-color: #f9f9f9;">
						<td width=50% style="text-align: center;">
							<input id="imgFile" type="file" name="file" style="width:290px">
						</td>
						<td width=50% style="text-align: center;">
							<a class="btn btn-success btn-sm" onclick="uploadPic()">上传</a>
<!-- 							<input type="button" value="导入" >  -->
						</td>
					</tr>
					</tbody>
				</table>
			</form>	
		  <!-- </div> -->
		
		</div>
		
		<!-- <div style="border-bottom: 2px solid #f5f5f5;"></div> -->
		<div  style="padding-top: 5px;">
         </div>
		
		
		                  
		                  
		                  					
	</div>
	<script src="${sr}js/jquery.min.js" type="text/javascript"></script>
	<script src="${sr}js/config.js" type="text/javascript"></script>
	<script src="${sr}js/baseutil.js" type="text/javascript"></script>
	<script src="${sr}js/jquery-ui.min.js" type="text/javascript"></script>
	<script src="${sr}js/jquery.form.js" type="text/javascript"></script>
	<script src="${sr}layui.js" type="text/javascript"></script>
	<script src="${sr}js/iCheck/icheck.min.js?v=1.0.2" type="text/javascript"></script>
	<script src="${sr}js/iCheck/custom.min.js?v=1.0.2" type="text/javascript"></script>
	
<script type="text/javascript">
	layui.use(['jquery','layer','laytpl'], function(){
		
	});
	
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
				 		parent.$('#avatarUrl').val(data.picPath);
						layer.msg(message,{time:1000});
						closeFrame();
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
	
	function closeFrame(){
		 var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
		parent.layer.close(index);
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
	
	
</script>
</body>
</html>