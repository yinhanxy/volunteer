<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page import="com.topstar.volunteer.common.AlternativeUtil"%>
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
	<link href="${sr}css/layui.css" rel="stylesheet">
	<%-- <link href="${sr}css/xenon-forms.css" rel="stylesheet"> --%>
    <!--[if lt IE 9]>
      <script src="${sr}js/html5shiv.min.js"></script>
      <script src="${sr}js/respond.min.js"></script>
    <![endif]-->
    <style type="text/css">
    	label {
    		display: inline;
    	}
    	.checkbox >label{
    		padding-right: 10px
    	}
    	#photo {
		    border-width: 0px;
		    //position: absolute;
		    left: 0px;
		    top: 0px;
		    padding-bottom:5px;
		    width: 123px;
		    height: 150px;
		}
		table td span, table td a {
	    	display: inline-block;
	    }
    </style>
</head>
<body class="v-page-body">
		<div class="panel" style="margin-bottom: 0;padding-top: 10px;" id="mainform">
			
				<div class="row">
					<table class="table table-bordered dataTable no-footer">
						<tr>
							<td class="col-xs-1">
						    	<label >活动名称</label>
						    </td>
						    <td class="col-xs-2">
						    	${activity.name}
						    </td>
						    <td class="col-xs-1">
						    	<label >活动状态</label>
						    </td>
						    <td class="col-xs-2" >
						    	${activity.statusDesc}
						    </td>
					    </tr>
				    	<tr>
				    		<td class="col-xs-1">
						    	<label >活动简介</label>
						    </td>
							<td class="col-xs-5" colspan="3">
								${activity.summary}
						    </td>
				    	 </tr>
				    	 <tr>
							<td class="col-xs-1">
						    	<label >活动开始时间</label>
						    </td>
						    <td class="col-xs-2">
						    	<fmt:formatDate value="${activity.activitySt}" pattern="yyyy-MM-dd HH:mm:ss"/>
						    </td>
						    <td class="col-xs-1">
						    	<label >活动结束时间</label>
						    </td>
						    <td class="col-xs-2">
						    	<fmt:formatDate value="${activity.activityEt}" pattern="yyyy-MM-dd HH:mm:ss"/>
						    </td>
				    	 </tr>
					</table>
				</div>
				
				<div class="row">
					活动图片
					<table class="table table-bordered dataTable no-footer">
						<tr>
							<td style="text-align:center;" colspan="2">
								活动图片上传
						     </td>
						     <td style="text-align:center;" colspan="6">
						     		<span style="width:60%">
										<input type="file" name="file" class="layui-upload-file" id="">
									</span>
						     </td>
			    	 	</tr>
						<tr >
							<td style="text-align:center;line-height: 200px" colspan="2">
						       	已上传的图片列表
						     </td>
						     <td style="text-align:center;" colspan="6" >
						     		<div id="activityPhotos" class="layer-photos-demo" style="overflow: auto;width:100%;height:240px">
						     		</div>
						     </td>
						</tr>
					</table>
					<div class="row" style="display: none;" id="pageInfo"></div>
				</div>
				<form id="saveRecordForm" action="${webroot}activity/saveActivityRecord.do" method="post">
					<input type="hidden" name="aId"  id="aId" value="${activity.id}">
					<input type="hidden" name="ius" id="ius">
					<input type="hidden" name="ctype" id="ctype">
				</form>
				<input type="hidden" id="eius" value="${activity.activityImg}">
		</div>
	<div style="text-align: right;margin-right: 15px;bottom: 10px;right: 10px;position: fixed;">
		<button type="button" id="save" class="btn btn-sm btn-success" ><i class="fa fa-save"></i>保存</button>
 			<c:if test="${activity.status==6}">
 				<button type="button" id="saveAndEnd" class="btn btn-sm btn-success" ><i class="fa fa-save"></i>保存并结束活动</button>
 			</c:if>
 			<button type="button" id="close" class="btn btn-sm "><i class="fa fa-remove"></i>关闭</button>
	</div>
	<script src="${sr}js/jquery.min.js" type="text/javascript"></script>
	<script src="${sr}js/config.js" type="text/javascript"></script>
	<script src="${sr}js/baseutil.js" type="text/javascript"></script>
	<script src="${sr}js/jquery.form-validator.min.js" type="text/javascript"></script>
	<script src="${sr}js/jquery-ui.min.js" type="text/javascript"></script>
	<script src="${sr}layui.js" type="text/javascript"></script>
	
	<script type="text/javascript">
	var ius;
	layui.use(['jquery','layer','upload','laytpl'], function(){
		ius=new Array();
		layui.upload({
			  url: '${webroot}file/upload.do',
			  ext: 'jpg|png|gif',
			  success: function(data){
			   		var layer = layui.layer;
				 	if(data.success){
				 		var message = data.message;
				 		var iu=data.picPath;
				 		if(iu){
				 			ius.push(iu);
				 		}
				 		var imgUrl=encodeURIComponent(iu);
				 		var imgHtml='<img layer-src=\"${webroot}file/readTempPic.do?ataUrl='+imgUrl+'" src="${webroot}file/readTempPic.do?ataUrl='+imgUrl+'" alt="'+data.picName+'" width="150" height="120" border="0" style="float: left;margin: 3px 3px 3px 3px;" >';
						$("#activityPhotos").append(imgHtml);
				 	}
				 	else{
						var message = data.message;
					}
				 	layer.photos({
					  photos: '#activityPhotos',
					  closeBtn:1
						,anim: 5 //0-6的选择，指定弹出图片动画类型，默认随机（请注意，3.0之前的版本用shift参数）
					});
				 	layer.msg(message,{time:1000});
			  }
			}); 
		showPic();
	})
	
	//显示图片信息	
   	function showPic(){
		var eurls=$("#eius").val();
		if(eurls){
			var urls=eurls.split(",");
			if(urls && urls.length>0){
				for (var i = 0; i < urls.length; i++) {
					var src = encodeURIComponent(urls[i]);
					var imgHtml='<img layer-src=\"${webroot}file/readPic.do?ataUrl='+src+'" src="${webroot}file/readPic.do?ataUrl='+src+'" width="150" height="120" border="0" style="float: left;margin: 3px 3px 3px 3px;" >';
					$("#activityPhotos").append(imgHtml);
				}
			}
		}
		layer.photos({
			  photos: '#activityPhotos',
			  closeBtn:1,
				anim: 5 //0-6的选择，指定弹出图片动画类型，默认随机（请注意，3.0之前的版本用shift参数）
			}); 
   	}
	
	$("#save").on("click",function(){
		$("#ius").val(ius);
		if(!$("#ius").val()){
			layer.alert("请上传活动图片!", {icon: 2});
			return false;
		}
		$("#save").prop("disabled",true);
		postFormData("saveRecordForm",true,saveRecordResult)
	});
	$("#saveAndEnd").on("click",function(){
		$("#ius").val(ius);
		if(!$("#ius").val()){
			layer.alert("请上传活动图片!", {icon: 2});
			return false;
		}
		$("#saveAndEnd").prop("disabled",true);
		$("#ctype").val("se");
		postFormData("saveRecordForm",true,saveRecordResult)
	});
	
	/**
	**保存活动记录图片的回调函数
	*/
	function saveRecordResult(data){
		var layer = layui.layer;
		var message;
		if(data && data.success){
			message=data.message;
			layer.alert(message, {icon: 6},function(index){
			    closeFrame();
			    parent.loadPage();
			  });
		}else{
			message=data.message;
			layer.alert(message, {icon: 2})
		}
	}
	
	
	
	function closeFrame(){
		var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
		parent.layer.close(index);
	}
	
	$('#close').on('click', function() {
		parent.loadPage();
		closeFrame();
	})
	</script>
</body>
</html>;