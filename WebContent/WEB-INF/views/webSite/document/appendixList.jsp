<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>  
<%@ taglib prefix="tps" uri="/pageTag"%>
<%@page import="com.topstar.volunteer.util.PropertiesUtil"%>

<%
	long preChannelId2 = PropertiesUtil.getInstance().getPropertyLong("preChannelId2", 8);
%>
<c:set var="preChannelId2" value="<%=preChannelId2%>" />
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
	<link href="${sr}css/jquery-ui.min.css" rel="stylesheet">
	<link href="${sr}css/theme-default.min.css" rel="stylesheet">
	<link href="${sr}css/layui.css" rel="stylesheet">
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
			width:160px;
			height:100px;
			display:inline-block;
		}
		
		#imageDiv div img{
			width:154px;
			height:94px;
		}
		
		#imageDiv div span{
			position:relative;
			top:-96px;
			right:-132px;
		}
		
		/* 上传控件的样式 */
		.layui-upload-button{
			height: 30px;line-height: 30px;margin-bottom: 8px;
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
			         	<span>图片附件</span>
			        </div>
		      </div>
		      <div id="u1622_2" class="ax_default sx_cur5" style="cursor: pointer;">
			        <img class="img" src="${sr}images/u1622.png" tabindex="0">
			        <div class="u1623" >
			         <span>文件附件</span>
			        </div>
		      </div>
		   <c:if test="${channelId == preChannelId2}">
		      <div id="u1622_3" class="ax_default sx_cur5" style="cursor: pointer;">
			        <img class="img" src="${sr}images/u1622.png" tabindex="0">
			        <div class="u1623" >
			         <span>视频附件</span>
			        </div>
		      </div>
		   </c:if>
		<div class="panel" style="margin-bottom: 0;padding-top: 10px;width: 100%;position: absolute;top: 45px" id="mainform">
				
				<div class="row mn5">
					<input type="file" name="file" class="layui-upload-file" id="imageAppendix" lay-title="上传图片"/>
					<div id="imageDiv" style="width:99%;overflow:auto;height: 230px;">
						
					</div>
				</div>
				
				<div id="u1628_state1" class="row mn5" data-label="活动信息" style="visibility: inherit; display: none;">
					<input type="file" name="file" class="layui-upload-file" id="fileAppendix" lay-title="上传文件"/>
					 <div style="width:99%;overflow:auto;height: 230px;">
			         <table class="table table-bordered dataTable no-footer">
			         	<thead>
						<tr>
							<th style="text-align:center;width:60px;padding:5px;">序号</th>
							<th style="text-align:center;width:240px;padding:5px;">文件名</th>
							<th style="text-align:center;padding:5px;">显示名称</th>
							<th style="text-align:center;width:50px;padding:5px;">删除</th>
						</tr>
						</thead>
						<tbody class="middle-align" id="fileTbody">
		                        	
                        </tbody>
					</table>
					</div>
        		</div>
			<c:if test="${channelId == 8 }">
				<div class="row mn5" style="visibility: inherit; display: none;">
						<div>
							<input type="file" name="file" class="layui-upload-file" id="videoAppendix" lay-title="上传视频文件"/>
							<button class="layui-btn layui-btn layui-btn-primary" onclick="addLink();" 
							style="height:30px; line-height:30px;margin-bottom: 8px;">
  								<i class="layui-icon">&#xe608;</i> 添加视频链接
							</button>
							<input type="hidden" name="file" id="alink">
						</div>
						
						
						<div id="addaLink" style="display:none;width:390px;">
							<br><input type="text" name="link" id="linkAppendix" required  
							lay-verify="required" placeholder="请输入链接" class="layui-input" style="width:90%;margin:0 auto;">
						</div>	
							
							
					 <div style="width:99%;overflow:auto;height: 230px;">
			         <table class="table table-bordered dataTable no-footer">
			         	<thead>
						<tr>
							<th style="text-align:center;width:60px;padding:5px;">序号</th>
							<th style="text-align:center;width:240px;padding:5px;">视频名称</th>
							<th style="text-align:center;padding:5px;">视频描述</th>
							<th style="text-align:center;width:50px;padding:5px;">删除</th>
						</tr>
						</thead>
						<tbody class="middle-align" id="videoTbody">
		                        	
                        </tbody>
					</table>
					</div>
        		</div>		
			</c:if>
		</div>
	<script src="${sr}js/jquery.min.js" type="text/javascript"></script>
	<script src="${sr}js/config.js" type="text/javascript"></script>
	<script src="${sr}js/baseutil.js" type="text/javascript"></script>
	<script src="${sr}js/jquery.form-validator.min.js" type="text/javascript"></script>
	<script src="${sr}js/jquery-ui.min.js" type="text/javascript"></script>
	<script src="${sr}layui.js" type="text/javascript"></script>
	<script src="${sr}js/select2.js" type="text/javascript" ></script>
	
	<script type="text/javascript">
	// 文件数组
	var  fileArray = new Array();
	//被删除的文件ID数组
	var deleteIdArray = new Array();
	
	//图片附件div
	var j_imageDivObj = null;
	//文件附件表格body
	var j_fileTobody = null;
	//文件序号
	var fileIndex = 1;
	
	layui.use(['jquery','layer','upload','laytpl'], function(){
		//绑定上传图片附件控件
		layui.upload({
			   elem:'#imageAppendix',
			   url: '${webroot}document/appendixImage.do'
			  ,ext: 'jpg|png|gif'
			  ,success: function(data){
				 	if(data.success){
				 		var fileName = data.fileName;
				 		var fileDesc = data.fileDesc;
				 		var fileExt = data.fileExt;
				 		var fileType = data.fileType;
				 		var srcHtml = "<img  src=\"${webroot}file/showTempPic.do?name="+fileName+
				 				"\" layer-src=\"${webroot}file/showTempPic.do?name="+fileName+"\" id=\""+
				 		fileName+"\" title=\""+encodeURIComponent(fileDesc)+"\" alt=\""+encodeURIComponent(fileDesc)+"\"/>";
				 		
				 		var divHtml = "<div>"+srcHtml+"<span><a title='删除' href='javascript:void(0)' onclick='deleteAppendixImage(this,\""+fileName+"\")' class=\"btn-sm\"  style=\"color:red;\">"+
                        "<i class=\"fa fa-close\"></i></a></span></div>";
                        j_imageDivObj = $("#imageDiv");
                        j_imageDivObj.append(divHtml);
				 		
				 		var imageObj = new Object();
				 		imageObj.fileName = fileName;
				 		imageObj.fileDesc = fileDesc;
				 		imageObj.fileExt = fileExt;
				 		imageObj.fileType = fileType;
		
				 		fileArray[fileArray.length] = imageObj;
				 	}else{
						var message = data.message;
						var layer = layui.layer;
						layer.msg(message,{time:1000});
					}
			  }
			});
		
		
		//绑定上传文件附件控件
		layui.upload({
			   elem:'#fileAppendix',
			   url: '${webroot}document/appendixFile.do'
			   ,ext: 'doc|docx|xls|xlsx|pdf|txt'
			  ,success: function(data){
				 	if(data.success){
				 		var fileName = data.fileName;
				 		var fileDesc = data.fileDesc;
				 		var fileExt = data.fileExt;
				 		var fileType = data.fileType;
				 		
				 		var downLoadSrc= "${webroot}file/tempDownload.html?fileName=" + fileName +"&sourceName="+fileDesc;
                        
                        var trHtml = "<tr role=\"row\" data-toggle=\"collapse\">"+
                        			"<td style='text-align:center;width:60px;padding:5px;'>"+fileIndex+"</td>"+
                        			"<td style='width:240px;padding:5px;'><a href='"+downLoadSrc+"'>"+fileName+"</a></td>"+
                        			"<td style='padding:5px;'>"+fileDesc+"</td>"+
                        			"<td style='text-align:center;width:50px;padding:5px;'><a title='删除' href='javascript:void(0)' onclick='deleteAppendixFile(this,\""+fileName+"\")' class=\"btn-sm\"  style=\"color:red;\">"+
                                    "<i class=\"fa fa-close\"></i></a></td>"+
                        			"</tr>";
                        
                        j_fileTobody = $("#fileTbody");
                        j_fileTobody.append(trHtml);
				 		
				 		var fileObj = new Object();
				 		fileObj.fileName = fileName;
				 		fileObj.fileDesc = fileDesc;
				 		fileObj.fileExt = fileExt;
				 		fileObj.fileType = fileType;
		
				 		fileArray[fileArray.length] = fileObj;
				 		fileIndex ++;
				 	}else{
						var message = data.message;
						var layer = layui.layer;
						layer.msg(message,{time:1000});
					}
			  }
			});
		
		//绑定上传视频附件控件
		layui.upload({
			   elem:'#videoAppendix',
			   url: '${webroot}document/appendixVideo.do'
			  ,ext: 'mp4|flv',
			  success: function(data){
				 	if(data.success){
				 		var fileName = data.fileName;
				 		var fileDesc = data.fileDesc;
				 		var fileExt = data.fileExt;
				 		var fileType = data.fileType;
				 		var downLoadSrc= "${webroot}file/tempDownload.html?fileName=" + fileName +"&sourceName="+fileDesc;
                        
                        var trHtml = "<tr role=\"row\" data-toggle=\"collapse\">"+
                        			"<td style='text-align:center;width:60px;padding:5px;'>"+fileIndex+"</td>"+
                        			"<td style='width:240px;padding:5px;'><a href='"+downLoadSrc+"'>"+fileName+"</a></td>"+
                        			"<td style='padding:5px;'>"+fileDesc+"</td>"+
                        			"<td style='text-align:center;width:50px;padding:5px;'><a title='删除' href='javascript:void(0)' onclick='deleteAppendixFile(this,\""+fileName+"\")' class=\"btn-sm\"  style=\"color:red;\">"+
                                    "<i class=\"fa fa-close\"></i></a></td>"+
                        			"</tr>";
                        
                        j_fileTobody = $("#videoTbody");
                        j_fileTobody.append(trHtml);
				 		
				 		var fileObj = new Object();
				 		fileObj.fileName = fileName;
				 		fileObj.fileDesc = fileDesc;
				 		fileObj.fileExt = fileExt;
				 		fileObj.fileType = fileType;
		
				 		fileArray[fileArray.length] = fileObj;
				 		fileIndex ++;
				 	}else{
						var message = data.message;
						var layer = layui.layer;
						layer.msg(message,{time:1000});
					}
			  }
			});
		
		//页面加载时从文档编辑页面获取已上传的附件信息
		var fileInfoInput = window.parent.document.getElementById("fileInfo");
		if(fileInfoInput != null && fileInfoInput.value != ""){
			var xmlObj = $.parseXML(fileInfoInput.value);
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
			 		
			 		var divHtml = "<div>"+srcHtml+"<span><a title='删除' href='javascript:void(0)' onclick='deleteAppendixImage(this,\""+fileName+"\")' class=\"btn-sm\"  style=\"color:red;\">"+
		            "<i class=\"fa fa-close\"></i></a></span></div>";
		            j_imageDivObj = $("#imageDiv");
		            j_imageDivObj.append(divHtml);
			 		
			 		var imageObj = new Object();
			 		imageObj.fileName = fileName;
			 		imageObj.fileDesc = fileDesc;
			 		imageObj.fileExt = fileExt;
			 		imageObj.fileType = fileType;
			 		imageObj.id = id;
		
			 		fileArray[fileArray.length] = imageObj;
		 			
		 		}else if(fileType == "1"){
		 			var downLoadSrc = "${webroot}file/tempDownload.html?fileName=" + fileName +"&sourceName="+fileDesc;
		 			if(id != null && id != ""){
		 				downLoadSrc = "${webroot}file/download.html?fileName=" + fileName +"&sourceName="+fileDesc;
		 			}
		 			// 文件附件
		 			var trHtml = "<tr role=\"row\" data-toggle=\"collapse\">"+
			        			"<td style='text-align:center;width:60px;padding:5px;'>"+fileIndex+"</td>"+
			        			"<td style='width:240px;padding:5px;'><a href='"+downLoadSrc+"'>"+fileName+"</a></td>"+
			        			"<td style='padding:5px;'>"+fileDesc+"</td>"+
			        			"<td style='text-align:center;width:50px;padding:5px;'><a title='删除' href='javascript:void(0)' onclick='deleteAppendixFile(this,\""+fileName+"\")' class=\"btn-sm\"  style=\"color:red;\">"+
			                    "<i class=\"fa fa-close\"></i></a></td>"+
			        			"</tr>";
			        j_fileTobody = $("#fileTbody");
			        j_fileTobody.append(trHtml);
			 		var fileObj = new Object();
			 		fileObj.fileName = fileName;
			 		fileObj.fileDesc = fileDesc;
			 		fileObj.fileExt = fileExt;
			 		fileObj.fileType = fileType;
			 		fileObj.id = id;
			 		fileArray[fileArray.length] = fileObj;
			 		fileIndex ++;
		 		}else if (fileType == "3") {
		 			var downLoadSrc = "${webroot}file/tempDownload.html?fileName=" + fileName +"&sourceName="+fileDesc;
		 			if(id != null && id != ""){
		 				downLoadSrc = "${webroot}file/download.html?fileName=" + fileName +"&sourceName="+fileDesc;
		 			}
		 			// 视频附件
		 			var trHtml = "<tr role=\"row\" data-toggle=\"collapse\">"+
			        			"<td style='text-align:center;width:60px;padding:5px;'>"+fileIndex+"</td>"+
			        			"<td style='width:240px;padding:5px;'><a href='"+downLoadSrc+"'>"+fileName+"</a></td>"+
			        			"<td style='padding:5px;'>"+fileDesc+"</td>"+
			        			"<td style='text-align:center;width:50px;padding:5px;'><a title='删除' href='javascript:void(0)' onclick='deleteAppendixFile(this,\""+fileName+"\")' class=\"btn-sm\"  style=\"color:red;\">"+
			                    "<i class=\"fa fa-close\"></i></a></td>"+
			        			"</tr>";
			        j_fileTobody = $("#videoTbody");
			        j_fileTobody.append(trHtml);
			 		var fileObj = new Object();
			 		fileObj.fileName = fileName;
			 		fileObj.fileDesc = fileDesc;
			 		fileObj.fileExt = fileExt;
			 		fileObj.fileType = fileType;
			 		fileObj.id = id;
			 		fileArray[fileArray.length] = fileObj;
			 		fileIndex ++;
				}else if (fileType == "4") {
					// 链接附件
		 			var trHtml = "<tr role=\"row\" data-toggle=\"collapse\">"+
			        			"<td style='text-align:center;width:60px;padding:5px;'>"+fileIndex+"</td>"+
			        			"<td style='width:240px;padding:5px;'><a href='"+fileName+"'>"+fileName+"</a></td>"+
								"<td style='padding:5px;'>"+fileDesc+"</td>"+
			        			"<td style='text-align:center;width:50px;padding:5px;'><a title='删除' href='javascript:void(0)' onclick='deleteAppendixFile(this,\""+fileName+"\")' class=\"btn-sm\"  style=\"color:red;\">"+
			                    "<i class=\"fa fa-close\"></i></a></td>"+
			        			"</tr>";
			        j_fileTobody = $("#videoTbody");
			        j_fileTobody.append(trHtml);
			 		var fileObj = new Object();
			 		fileObj.fileName = fileName;
			 		fileObj.fileDesc = fileDesc;
			 		fileObj.fileExt = fileExt;
			 		fileObj.fileType = fileType;
			 		fileObj.id = id;
			 		fileArray[fileArray.length] = fileObj;
			 		fileIndex ++;
				}
	        });
		}
		
		//页面加载时从文档编辑页面获取已删除的附件ID信息
		var deleteFileIdsInput = window.parent.document.getElementById("deleteFileIds");
		
		if(deleteFileIdsInput != null && deleteFileIdsInput.value != ""){
			var deleteFileIds = deleteFileIdsInput.value;
			var arrStr = deleteFileIds.split(",");
			if(arrStr != null && arrStr.length > 0){
				for(var i = 0; i< arrStr.length;i++){
					var id = arrStr[i];
					if(id != null && id != ""){
						deleteIdArray[deleteIdArray.length] = id;
					}
				}
			}
		}
		
		
	});
	
	/**
	* 删除图片附件
	*/
	function deleteAppendixImage(obj,fileName){
		if(fileArray.length > 0){
			for(var i = fileArray.length-1 ;i > -1;i--){
				var imageObj = fileArray[i];
				if(imageObj != null){
					var tempFileName = imageObj.fileName;
					if(tempFileName == fileName){
						var id = imageObj.id;
						if(id != null && id != ""){
							deleteIdArray[deleteIdArray.length] = id;
						}
						fileArray.splice(i,1);
					}
				}
			}
		}
		var p = $(obj).parent("span").parent("div");
		p.remove();
	}
	
	/**
	* 删除文件附件
	*/
	function deleteAppendixFile(obj,fileName){
		if(fileArray.length > 0){
			for(var i = fileArray.length-1 ;i > -1;i--){
				var imageObj = fileArray[i];
				if(imageObj != null){
					var tempFileName = imageObj.fileName;
					if(tempFileName == fileName){
						var id = imageObj.id;
						if(id != null && id != ""){
							deleteIdArray[deleteIdArray.length] = id;
						}
						fileArray.splice(i,1);
					}
				}
			}
		}
		var p = $(obj).parent().parent();
		p.remove();
	}
	
	/**
	* 提供方法供文档编辑页面调用，获取附件信息
	*
	*/
	function getFileArray(){
		var fileArrayInfo = "<files>";
		if(fileArray.length > 0){
			for(var i = 0 ;i < fileArray.length;i++){
				var fileObj = fileArray[i];
		 		var fileName =  fileObj.fileName;
		 		var fileDesc = fileObj.fileDesc;
		 		var fileExt = fileObj.fileExt;
		 		var fileType = fileObj.fileType;
				var id = fileObj.id;
				if(id == null){
					id = "";
				}
				var fileXml = "<file><name><![CDATA["+fileName+"]]></name>"+
									"<desc><![CDATA["+fileDesc+"]]></desc>"+
									"<ext>"+fileExt+"</ext>"+
									"<type>"+fileType+"</type>"+
									"<id>"+id+"</id></file>";
		 		fileArrayInfo += fileXml;
			}
		}
		fileArrayInfo += "</files>";
		return fileArrayInfo;
	}
	
	/**
	* 提供方法供文档编辑页面使用，返回被删除的附件ID，以逗号分割
	*/
	function getDeleteFileIds(){
		return deleteIdArray.join(",");
	}
	
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
	
	//添加视频链接
	function addLink(){
		//弹出一个添加视频链接的层
		layui.use(['form','layer'], function() {
		var form = layui.form,
		    layer = layui.layer;
		var str=$("#addaLink");
		layer.open({
			type: 1,
			title:"添加视频链接",
			area: ['400px', '160px'],
			maxmin: true,
			shadeClose: true, //点击遮罩关闭
			content:str,
			btn:['确定', '取消' ],
			yes: function(index, layero){
				var link=$("#linkAppendix").val();
				var layer = layui.layer;
				if (link != "" && link != null) {
					var reg="^((https|http|ftp|rtsp|mms)?://)"
				        + "?(([0-9a-z_!~*'().&=+$%-]+: )?[0-9a-z_!~*'().&=+$%-]+@)?" //ftp的user@
				        + "(([0-9]{1,3}\.){3}[0-9]{1,3}" // IP形式的URL- 199.194.52.184
				        + "|" // 允许IP和DOMAIN（域名）
				        + "([0-9a-z_!~*'()-]+\.)*" // 域名- www.
				        + "([0-9a-z][0-9a-z-]{0,61})?[0-9a-z]\." // 二级域名
				        + "[a-z]{2,6})" // first level domain- .com or .museum
				        + "(:[0-9]{1,4})?" // 端口- :80
				        + "((/?)|" // a slash isn't required if there is no file name
				        + "(/[0-9a-z_!~*'().;?:@&=+$,%#-]+)+/?)$";  
					 var re=new RegExp(reg);
				    if(!re.test(link)){  
				    	layer.msg("请输入正确的链接！",{time:1000});  
				    }else{  
					$.ajax({
						url:'${webroot}document/appendixLink.do',
						type:'post',
						data:{'link':link},
						dataType:'json',
						async:true,
				 		success:function(data){
				 			if (data.success == false) {
				 				var message = data.message;
								var layer1 = layui.layer;
								layer1.msg(message,{time:1000});
								return;
							}
							var fileName = data.fileName;
					 		var fileDesc = data.fileDesc;
					 		var fileExt = data.fileExt;
					 		var fileType = data.fileType;
				            var trHtml = "<tr role=\"row\" data-toggle=\"collapse\">"+
				            			"<td style='text-align:center;width:60px;padding:5px;'>"+fileIndex+"</td>"+
				            			"<td style='width:240px;padding:5px;'><a href='"+fileName+"'>"+fileName+"</a></td>"+
				            			"<td style='padding:5px;'>"+fileDesc+"</td>"+
				            			"<td style='text-align:center;width:50px;padding:5px;'><a title='删除' href='javascript:void(0)' onclick='deleteAppendixFile(this,\""+fileName+"\")' class=\"btn-sm\"  style=\"color:red;\">"+
				                        "<i class=\"fa fa-close\"></i></a></td>"+
				            			"</tr>";
				            var alink=data.alink;
				            $("#alink").val(alink);
				            j_fileTobody = $("#videoTbody");
				            j_fileTobody.append(trHtml);
					 		var fileObj = new Object();
					 		fileObj.fileName = fileName;
					 		fileObj.fileDesc = fileDesc;
					 		fileObj.fileExt = fileExt;
					 		fileObj.fileType = fileType;
					 		fileArray[fileArray.length] = fileObj;
					 		fileIndex ++;
				 		},error:function(){
							layer.msg("添加视频链接出错，请稍后再试",{time:1000});
				 		}
					})
				    }
				}else{
					layer.msg("视频链接不能为空！",{time:1000});
				}
				$("#addaLink").hide();
				layer.close(index);
			  }
		});
	});
		
	}
	
	function closeFrame(){
		var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
		parent.layer.close(index);
	}
	
	</script>
</body>
</html>