<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>  
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
	<link href="${sr}css/font-awesome/css/font-awesome.min.css" rel="stylesheet" media="screen">
    <link href="${sr}css/bootstrap.min.css" rel="stylesheet">
    <link href="${sr}css/bootstrap-datetimepicker.min.css" rel="stylesheet">
	<link href="${sr}css/xenon.min.css" rel="stylesheet">
	<link href="${sr}css/base.css" rel="stylesheet">
	<link href="${sr}css/new.css" rel="stylesheet">
	<link href="${sr}css/jquery-ui.min.css" rel="stylesheet">
	<link href="${sr}css/theme-default.min.css" rel="stylesheet">
    <!--[if lt IE 9]>
      <script src="${sr}js/html5shiv.min.js"></script>
      <script src="${sr}js/respond.min.js"></script>
    <![endif]-->
    <style type="text/css">
    	label {
    		display: inline;
    	}
		#u1622_1 {
		    border-width: 0px;
		    position: absolute;
		    left: 15px;
		    top: 2px;
		    width: 87px;
		    height: 40px;
		}
		#u1622_2 {
		    border-width: 0px;
		    position: absolute;
		    left: 101px;
		    top: 2px;
		    width: 87px;
		    height: 40px;
		}
		#u1622_3 {
		    border-width: 0px;
		    position: absolute;
		    left: 193px;
		    top: 2px;
		    width: 87px;
		    height: 40px;
		}
		#u1622_4 {
		    border-width: 0px;
		    position: absolute;
		    left: 286px;
		    top: 2px;
		    width: 87px;
		    height: 40px;
		}
		.u1623 {
		    border-width: 0px;
		    position: absolute;
		    left: 15px;
		    top: 10px;
		    width: 57px;
		    white-space: nowrap;
		}
		#u1620 {
		    border-width: 0px;
		    position: absolute;
		    left: 0px;
		    top: 41px;
		    width: 100%;
		    height: 1px;
		}
		#u1620_img {
		    border-width: 0px;
		    position: absolute;
		    left: 0px;
		    top: 0px;
		    width: 100%;
		    height: 1px;
		}
		
		.ax_default{
		    font-family: 'Helvetica Neue Regular', 'Helvetica Neue';
		    font-weight: 400;
		    font-style: normal;
		    font-size: 14px;
		    color: #428BCA;
		    text-align: center;
		    line-height: 20px;
		}
		
		#imageDiv div{
			border:1px solid #eee;
			float: left;
			margin:3px;
			padding: 2px;
			width:145px;
			height:100px;
			display:inline-block;
		}
		
		#imageDiv div img{
			width:142px;
			height:94px;
		}
		
		#imageDiv div span{
			position:relative;
			top:-96px;
			right:-132px;
		}
    </style>
</head>
<body class="v-page-body">
	<div id="u1620"  tabindex="0">
      	<img id="u1620_img" src="${sr}images/u1620.png" tabindex="0">
     </div>
	 <div id="u1622_1" class="ax_default sx_cur5" style="cursor: pointer;" >
	       <img class="img" src="${sr}images/u1622_selected.png" >
	        <div class="u1623"  style="top:10px;color:black;">
	         	<span>文档信息</span>
	        </div>
     </div>
     <div id="u1622_2" class="ax_default sx_cur5" style="cursor: pointer;">
	        <img class="img" src="${sr}images/u1622.png" tabindex="0">
	        <div class="u1623" >
	         <span>附件信息</span>
	        </div>
     </div>
     <div id="u1622_3" class="ax_default sx_cur5" style="cursor: pointer;">
	        <img class="img" src="${sr}images/u1622.png" tabindex="0">
	        <div class="u1623" >
	         <span>操作信息</span>
	        </div>
     </div>
	 <div  class="panel" style="margin-bottom:30px;padding-top:10px;width:100%;position: absolute;top: 45px" id="mainform">
	 	<input type="hidden" id="fileInfo"  value="${fileInfo}"/>
		<div class="row mn5">
			<div class="col-xs-8">
				<div class="row form-horizontal form-group">
					<label class="col-xs-12" style="text-align: center;">${doc.title}</label>
				</div>
				<div class="row">
					<div style="height: 100%;width: 100%;" class="col-xs-12">
						${doc.htmlcon}
					</div>
               	</div>
			</div>
			<div class="col-xs-4">
				<table class="table table-bordered dataTable no-footer">
				<tr class="row">
           			<td class="col-xs-4"><label>栏目</label></td>
           			<td class="col-xs-8">${channel.chnlDesc}</td>
				</tr>
				<tr class="row">
					<td>状态</td><td>${doc.statusDesc}</td>
				</tr>
				<tr class="row">
					<td>关键词</td><td>${doc.keyword}</td>
				</tr>
				<tr class="row">
					<td>来源</td><td> ${doc.source}</td>
				</tr>
				<tr class="row">
					<td>时间</td><td><fmt:formatDate value='${doc.relTime}' pattern=' yyyy-MM-dd HH:mm:ss'/></td>
				</tr>
				<tr class="row">
					<td>作者</td><td>${doc.author}</td>
				</tr>
				<tr class="row">
					<td>摘要</td><td style="height:80px;">${doc.summary}</td>
				</tr>
			</table>
			</div>
		</div>	
		
		<div class="row mn5" style="display: none;">
			<div id="imageDiv" style="width:99%;overflow:auto;height:230px;">
						
			</div>
			
			<div style="width:99%;overflow:auto;display: none;" id="fileDiv">
			    <table class="table table-bordered dataTable no-footer">
			    <thead>
					<tr>
						<th style="text-align:center;width:60px;padding:5px;">序号</th>
						<th style="text-align:center;width:240px;padding:5px;">文件名</th>
						<th style="text-align:center;padding:5px;">显示名称</th>
					</tr>
				</thead>
				<tbody class="middle-align" id="fileTbody">
                        	
                </tbody>
			</table>
			</div>
		</div>
		
		<div class="row mn5" style="display: none;">
			<table class="table table-bordered dataTable no-footer">
				<tr>
					<td style="width:150px;">创建人</td>
					<td>${doc.crUser}</td>
				</tr>
				<tr>
					<td>创建时间</td>
					<td><fmt:formatDate value="${doc.crTime }" pattern=' yyyy-MM-dd HH:mm:ss'/></td>
				</tr>
				<c:if test="${!empty orgName }">
				<tr>
					<td>创建人所在机构</td>
					<td>${orgName}</td>
				</tr>
				</c:if>
				<tr>
					<td>最后修改人</td>
					<td>${doc.lastUpdateUser}</td>
				</tr>
				<tr>
					<td>最后修改时间</td>
					<td><fmt:formatDate value="${doc.lastUpdateTime }" pattern=' yyyy-MM-dd HH:mm:ss'/></td>
				</tr>
				<tr>
					<td>发布人</td>
					<td>${doc.subUser}</td>
				</tr>
				<tr>
					<td>发布时间</td>
					<td><fmt:formatDate value="${doc.subTime }" pattern=' yyyy-MM-dd HH:mm:ss'/></td>
				</tr>
			</table>
		</div>
		
	 </div>
		 
	 <div class="v-form-footer" style="margin-bottom: 10px;">
	 		<shiro:hasPermission name="/document/commitDocument.do">
	 		<c:if test="${isCommit}">
	 			<c:if test="${doc.status == 1 || doc.status == -1 }">
	 				<button type="button" class="btn btn-info btn-sm" onclick="commitDocument(${doc.id},2)" style="margin-bottom: 0px;"><i class="fa fa-check"></i>提交文档</button>
	 			</c:if>
	 			<c:if test="${doc.status == 2 }">
	 				<button type="button" class="btn btn-danger btn-sm" onclick="commitDocument(${doc.id},0)" style="margin-bottom: 0px;"><i class="fa fa-check"></i>退回文档</button>
	 			</c:if>
	 		</c:if>
	 		</shiro:hasPermission>
	 		
	 		<shiro:hasPermission name="/document/pubDocument.do">
	 		<c:if test="${isPub}">
	 			<c:if test="${doc.status == 2 }">
	 				<button type="button" class="btn btn-info btn-sm" onclick="pubDocument(${doc.id})" style="margin-bottom: 0px;"><i class="fa fa-upload"></i>发布文档</button>
	 			</c:if>
	 			<c:if test="${doc.status == 10 }">
	 				<button type="button" class="btn btn-danger btn-sm" onclick="cancelDocument(${doc.id})" style="margin-bottom: 0px;"><i class="fa fa-upload"></i>撤销发布</button>
	 			</c:if>
	 		</c:if>
	 		</shiro:hasPermission>
	 
	   		<button type="button" class="btn btn-sm" onclick="closeFrame()" style="margin-bottom: 0px;"><i class="fa fa-remove"></i>关闭</button>
	</div>
	 
	
	<script src="${sr}js/config.js" charset="utf-8" type="text/javascript"></script>
	<script src="${sr}js/jquery.min.js" charset="utf-8" type="text/javascript"></script>
	<script src="${sr}js/baseutil.js" charset="utf-8" type="text/javascript"></script>
	<script src="${sr}layui.js" charset="utf-8" type="text/javascript"></script>
	<script type="text/javascript" src="${sr}js/bootstrap.min.js"></script>
	<script type="text/javascript">
	var fileIndex = 1;
	
	layui.use(['jquery','layer'], function(){
		var fileInfoInput = $("#fileInfo");
		if(fileInfoInput != null && fileInfoInput.val() != ""){
			var xmlObj = $.parseXML(fileInfoInput.val());
			$(xmlObj).find('file').each(function () {
				var fileObj = $(this);
	            var fileName =  fileObj.children('name').text();
		 		var fileDesc = fileObj.children('desc').text();
		 		var fileExt = fileObj.children('ext').text();
		 		var fileType = fileObj.children('type').text();
		 		var id = fileObj.children('id').text();
		 		if(id == null){
		 			id = "";
		 		}
		 		//图片附件
		 		if(fileType == "2"){
		 			var showSrc = "${webroot}file/showTempPic.do";
		 			if(id != null && id != ""){
		 				showSrc = "${webroot}file/showPic.do";
		 			}
		 			
		 			var srcHtml = "<img  src=\""+showSrc+"?name="+fileName+
	 				"\" layer-src=\""+showSrc+"?name="+fileName+"\" id=\""+
			 		fileName+"\" title=\""+encodeURIComponent(fileDesc)+"\" alt=\""+encodeURIComponent(fileDesc)+"\"/>";
			 		
			 		var divHtml = "<div>"+srcHtml+"</div>";
		            j_imageDivObj = $("#imageDiv");
		            j_imageDivObj.append(divHtml);
			 		
		 			
		 		}else if(fileType == "1" || fileType == "3"){
		 			var downLoadSrc = "${webroot}file/tempDownload.html?fileName=" + fileName +"&sourceName="+fileDesc;
		 			if(id != null && id != ""){
		 				downLoadSrc = "${webroot}file/download.html?fileName=" + fileName +"&sourceName="+fileDesc;
		 			}
		 			// 文件附件和视频附件
		 			var trHtml = "<tr role=\"row\">"+
			        			"<td style='text-align:center;width:60px;padding:5px;'>"+fileIndex+"</td>"+
			        			"<td style='width:240px;padding:5px;'><a href='"+downLoadSrc+"'>"+fileName+"</a></td>"+
			        			"<td style='padding:5px;'>"+fileDesc+"</td>"+
			        			"</tr>";
        
			        j_fileTobody = $("#fileTbody");
			        j_fileTobody.append(trHtml);
			 		fileIndex ++;
		 		}else if (fileType == "4") {
					//视频链接
		 			var trHtml = "<tr role=\"row\" data-toggle=\"collapse\">"+
        			"<td style='text-align:center;width:60px;padding:5px;'>"+fileIndex+"</td>"+
        			"<td style='width:240px;padding:5px;'>"+fileName+"</td>"+
        			"<td style='padding:5px;'>"+fileDesc+"</td>"+
        			"</tr>";
		
			        j_fileTobody = $("#fileTbody");
			        j_fileTobody.append(trHtml);
			 		fileIndex ++;
				}
		 		
	        });
			
			if(fileIndex > 1){
				$("#fileDiv").show();
			}
		}
	});
	
	/**
	* 选项卡切换
	*/
	$(".ax_default").each(function(index, element) {
	    $(element).click(function(){
	    	$(".ax_default").find(".img").attr({"src":"${sr}/images/u1622.png"});
	    	$(".ax_default").find(".u1623").css("color","");
		    $(this).find(".img").attr({"src":"${sr}images/u1622_selected.png"});
		    $(this).find(".u1623").css("color","black");
			   $(".mn5").each(function(i, e) {
						if(index==i){
							$(e).show();
						}else{
							$(e).hide();
						}   
				 });

			})
	  });
	
	
	/**
	* 提交文档
	*/
	function commitDocument(id,status){
		var layer = layui.layer;
		if(id != null && id != "" && status != null){
			var message = "";
			if(status == 2){
				message = "确定要提交当前文档吗？";
			}else if(status == 0){
				message = "确定要退回当前文档吗？";
			}else{
				layer.msg('参数不正确，不能提交或退回文档！',{time:1000});
				return;
			}
			
			layer.confirm(message, {icon: 3, title:'系统提示', btn: ['确定','取消']},
				function(index,layero){
					var url="${webroot}document/commitDocument.do";
					params={"id":id,'status':status};
					postData(url, params, true,commitOrPubResult); 
					layer.close(index);
				}, function(){
					  
				});
		}else{
			layer.msg('参数不正确，不能提交或退回文档！',{time:1000});
			return;
		}
	}
	
	
	/**
	* 发布文档
	*/
	function pubDocument(id){
		var layer = layui.layer;
		if(id != null && id != ""){
			layer.confirm('确定要发布当前文档吗？', {icon: 3, title:'系统提示', btn: ['确定','取消']},
				function(index,layero){
					var url="${webroot}document/pubDocument.do";
					params={"id":id};
					postData(url, params, true,commitOrPubResult); 
					layer.close(index);
				}, function(){
					  
				});
		}else{
			layer.msg('参数不正确，不能发布文档！',{time:1000});
			return;
		}
	}
	
	/**
	* 撤销发布文档
	*/
	function cancelDocument(id){
		var layer = layui.layer;
		if(id != null && id != ""){
			layer.confirm('确定要撤销发布当前的文档吗？', {icon: 3, title:'系统提示', btn: ['确定','取消']},
				function(index,layero){
					var url="${webroot}document/cancelDocument.do";
					params={"id":id};
					postData(url, params, true,commitOrPubResult); 
					layer.close(index);
				}, function(){
					  
				});
		}else{
			layer.msg('参数不正确，不能撤销发布文档！',{time:1000});
			return;
		}
	}
	
	
	/**
	* 提交或发布后的回调页面
	*/
	function commitOrPubResult(data){
		var layer = layui.layer;
		var message=data.message;
		if(data && data.success){
			layer.alert(message, {icon: 6},function(index){
				parent.getSubDocs();
				closeFrame();
			  });
		}else{
			layer.msg(message, {icon: 2,time:1000});
		}
	}
	
	
	
	
	function closeFrame(){
		var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
		parent.layer.close(index);
	}
	
	
	</script>
</body>
</html>