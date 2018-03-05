<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
    	.v-form-footer {
		    padding-top: 0;
		    height: 50px;
		    position: absolute;
		    line-height: 60px;
		    background: #fff;
		    bottom: 0px;
		}
		
		table td span, table td a{
			display: inline-block;
		}
		
		#ueditor {
			line-height: normal;
		}
    </style>
</head>
<body class="v-page-body">
	<div class="page-container">
		<div class="panel" style="padding: 10px 20px;">
				<form id="addDocForm"  onsubmit="return false;">
					<input type="hidden" name="id"  id="id" value="${doc.id}"/>
					<input type="hidden" name="status" id="status"/>
					<input type="hidden" name="content"  id="content" />
					<input type="hidden" name="htmlcon"  id="htmlcon" />
					<input type="hidden" name="fileInfo" id="fileInfo" value="${fileInfo}"/>
					<input type="hidden" name="deleteFileIds" id="deleteFileIds"/>
					
					<div class="row">
						<div class="col-xs-8">
							<div class="row form-horizontal form-group">
								<label class="col-xs-2 control-label">标题<small><code>*</code></small></label>
								<div class="col-xs-10">
								<input type="text" class="form-control" name="title" id="title" value="${doc.title}"/>
								</div>
							</div>
							<div class="row">
								<div id="ueditor" style="height: 100%;width: 100%;" class="col-xs-12">
									<script id="editor" type="text/plain" style="width: 100%;"></script>
								</div>
                   			</div>
						</div>
						
						<div class="col-xs-4">
							<table class="table table-bordered dataTable no-footer">
							<tr class="row">
	                   			<td class="col-xs-4"></td>
	                   			<td class="col-xs-8">
	                   				<shiro:hasPermission name="/document/editDocument.html">
	                   				<button type="button" class="btn btn-sm btn-success" onclick="appendixManager()">
	                   					<i class="fa fa-file"></i>附件管理</button>
	                   				</shiro:hasPermission>	
	                   			</td>
							</tr>
							<tr class="row">
	                   			<td class="col-xs-4"><label>栏目<small><code >*</code></small></label></td>
	                   			<td class="col-xs-8">
	                   				<input type="hidden" name="channelId" id="channelId" value="${channel.id}"/>
										<div class="input-group" onclick="selectChannel()">
										<input type="text"  class="form-control" name="channelName" readonly="readonly" id="channelName" value="${channel.chnlName}" placeholder="选择栏目"/>
										<span class="input-group-btn">
								       		 <button type="button" class="btn btn-primary"><i class="fa fa-search"></i>
								             </button> 
							       		</span>
									</div>
	                   			</td>
							</tr>
							<tr class="row">
								<td>关键词</td>
	                   			<td><input type="text"  class="form-control" name="keyword" id="keyword" value="${doc.keyword}"/></td>
							</tr>
							<tr class="row">
								<td>来源</td>
	                   			<td><input type="text"  class="form-control" name="source" id="source" value="${doc.source}"/></td>
							</tr>
							<tr class="row">
								<td><label>时间<small><code >*</code></small></label></td>
	                   			<td>
	                   				<div class="date form_datetime" id="datetimepicker" data-date-format="yyyy-mm-dd hh:ii:ss">
									    <input type="text" class="form-control" name="reltime" id="reltime" 
									    	value="<fmt:formatDate value='${doc.relTime }' pattern=' yyyy-MM-dd HH:mm:ss'/>">
									    <span class="add-on input-group"><i class="icon-th"></i></span>
									</div>
	                   			</td>
							</tr>
							<tr class="row">
								<td>作者</td>
	                   			<td><input type="text"  class="form-control" name="author" id="author" value="${doc.author}"/></td>
							</tr>
							<tr class="row">
								<td>摘要</td>
	                   			<td><textarea  class="form-control" id="summary" name="summary" rows="8" value="${doc.summary}" ></textarea></td>
							</tr>
						</table>
						</div>
					</div>
					
					<div class="row" style="text-align: center;">
						<shiro:hasPermission name="/document/editDocument.html">
						    <button type="button" id="addBtn1" onclick="saveDoc()" class="btn btn-sm btn-success" ><i class="fa fa-plus"></i>保存</button>
  							
  							<shiro:hasPermission name="/document/commitDocument.do">
  								<button type="button" id="addBtn2" onclick="saveDoc(2)" class="btn btn-sm btn-success" ><i class="fa fa-check"></i>保存并提交</button>
  							</shiro:hasPermission>
  							
  							<shiro:hasPermission name="/document/pubDocument.do">
  								<button type="button" id="addBtn3" onclick="saveDoc(10)" class="btn btn-sm btn-success" ><i class="fa fa-upload"></i>保存并发布</button>
  							</shiro:hasPermission>
  						</shiro:hasPermission>	
  							<button type="button" class="btn btn-sm" onclick="closeFrame()"><i class="fa fa-remove"></i>关闭</button>
					</div>
			    </form>
		</div>
	</div>
	<textarea id="ueContent" style="display: none;">${doc.htmlcon}</textarea>
	
	<script src="${sr}js/config.js" charset="utf-8" type="text/javascript"></script>
	<script src="${sr}js/jquery.min.js" charset="utf-8" type="text/javascript"></script>
	<script type="text/javascript" charset="utf-8" src="${sr}ue/ueditor.config.js"></script>
    <script type="text/javascript" charset="utf-8" src="${sr}ue/ueditor.all.min.js"> </script>
    <script type="text/javascript" charset="utf-8" src="${sr}ue/lang/zh-cn/zh-cn.js"></script>
	<script src="${sr}js/baseutil.js" charset="utf-8" type="text/javascript"></script>
	<script src="${sr}js/validdate.js" charset="utf-8" type="text/javascript"></script>
	<script src="${sr}layui.js" charset="utf-8" type="text/javascript"></script>
	<script type="text/javascript" src="${sr}js/bootstrap.min.js"></script>
	<script type="text/javascript" src="${sr}js/bootstrap-datetimepicker.min.js" charset="UTF-8"></script>
	<script type="text/javascript" src="${sr}js/locales/bootstrap-datetimepicker.zh-CN.js" charset="UTF-8"></script>
	<script type="text/javascript">
	var ue = null;
	layui.use(['jquery','layer','laytpl'], function(){
		var bodyHeight = $(document.body).height();
		var ueHeight = bodyHeight - 240;
		ue = UE.getEditor('editor',{initialFrameHeight:ueHeight});
		
		UE.Editor.prototype._bkGetActionUrl = UE.Editor.prototype.getActionUrl;  
	    UE.Editor.prototype.getActionUrl = function(action){
	        if(action == '/document/uploadImages.do'){
	            return Config.root+'/document/uploadImages.do';
	        }else{
	            return this._bkGetActionUrl.call(this, action);  
	        }
	    }
	    ue.ready(function() {//编辑器初始化完成再赋值
			ue.setContent($("#ueContent").val());  //赋值给UEditor
		});
	    
		$('.form_datetime').datetimepicker({
	        language:  'zh-CN',
	        weekStart: 1,
	        todayBtn:  1,
			autoclose: 1,
			todayHighlight: 1,
			startView: 2,
			minView: 2,
			forceParse: 0
	    });
	});
	
	/**
	* 选择栏目
	*/
	function selectChannel(){
		var layer = parent.layer;
		layer.open({
		  type: 2,
		  title:"选择父栏目",
		  area: ['300px', '450px'],
		  maxmin: true,
		  scrollbar: true,
		  content: "${webroot}document/selectChannel.html",
		  btn : [ '确定', '取消' ], 
		  yes : function(index, layero) {
				var iframeWin = top.window[layero.find('iframe')[0]['name']]; 
				var data=iframeWin.selectChannel();
				$("#channelName").val(data.desc);
				var parentId=data.id;
				if(!parentId){
					parentId=0;
				}
				$("#channelId").val(parentId);
		  }
		});
	}
	
	/**
	* 保存文档
	*/
	function saveDoc(status){
		var j_title = $("#title");
		var j_channelId = $("#channelId");
		var j_keyword = $("#keyword");
		var j_source = $("#source");
		var j_summary = $("#summary");
		var j_reltime = $("#reltime");
		var j_autor = $("#author");
		
		var j_status = $("#status");
		
		var layer = layui.layer;
		
		if(j_title.val().trim() == ""){
			layer.tips("请输入标题！", "#title",{tips: [1, "#f57a78"]});
			return;
		}
		
		if(j_title.val().length > 200){
			layer.tips("标题不能超过200个字符！", "#title",{tips: [1, "#f57a78"]});
			return;
		}
		
		if(j_channelId.val().trim() == ""){
			layer.tips("请选择栏目！", "#channelName",{tips: [1, "#f57a78"],time: 4000});
			return;
		}
		
		if(j_keyword.val().length > 200){
			layer.tips("关键字不能超过200个字符！", "#keyword",{tips: [1, "#f57a78"]});
			return;
		}
		
		if(j_reltime.val().trim() == ""){
			layer.tips("请输入时间！", "#crtime",{tips: [1, "#f57a78"]});
			return;
		}
		
		var valid = validateYYYYMMDDHHmm(j_reltime.val());
		if(!valid){
			layer.tips("输入的时间格式不正确！", "#crtime",{tips: [1, "#f57a78"]});
			return;
		}
		if(j_source.val().length > 40){
			layer.tips("来源不能超过50个字符！", "#source",{tips: [1, "#f57a78"]});
			return;
		}
		
		if(j_autor.val().length > 40){
			layer.tips("作者不能超过40个字符！", "#author",{tips: [1, "#f57a78"]});
			return;
		}
		
		if(j_summary.val().length > 1000){
			layer.tips("摘要不能超过1000个字符！", "#summary",{tips: [1, "#f57a78"]});
			return;
		}
		
		var hasContents = UE.getEditor('editor').hasContents();
		if(!hasContents){
			layer.alert("请输入正文内容", {icon:2});
			return;
		}
		if(status != null && status != ""){
			j_status.val(status);
		}
		$("#addBtn1").prop("disabled",true);
		$("#addBtn2").prop("disabled",true);
		$("#addBtn3").prop("disabled",true);
		try{
			var content = UE.getEditor('editor').getContentTxt();
			var htmlcon = UE.getEditor('editor').getContent();
			$("#content").val(content);
			$("#htmlcon").val(htmlcon);

			var j_Form = $("#addDocForm");
			var url = "${webroot}document/editDocument.do";
	    	var params = j_Form.serialize();
	    	postData(url,params,true,saveDocResult,saveDocError);
		}catch(e){
			$("#addBtn1").prop("disabled",false);
			$("#addBtn2").prop("disabled",false);
			$("#addBtn3").prop("disabled",false);
		}
	}
	
	/**
	* 添加文档的回调方法
	*/
	function saveDocResult(data){
		var layer = layui.layer;
		var message;
		if(data && data.success){
			message=data.message;
			layer.alert(message, {icon: 6},function(index){
				parent.getSubDocs();
			    closeFrame();
			  });
		}else{
			errorTips(data);
		}
		$("#addBtn1").prop("disabled",false);
		$("#addBtn2").prop("disabled",false);
		$("#addBtn3").prop("disabled",false);
	}
	
	/**
	* 添加文档出错时的调用方法
	*/
	function saveDocError(XMLHttpRequest, textStatus, errorThrown){
		$("#addBtn1").prop("disabled",false);
		$("#addBtn2").prop("disabled",false);
		$("#addBtn3").prop("disabled",false);
	}
	
	
	function closeFrame(){
		var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
		parent.layer.close(index);
	}
	
	/**
	* 附件管理
	*/
	function appendixManager(){
		var channelId=$("#channelId").val();
		var layer = layui.layer;
		layer.open({
		  type: 2,
		  title:"附件管理",
		  area: ['720px', '450px'],
		  maxmin: true,
		  scrollbar: true,
		  content: "${webroot}document/appendixList.html?channelId="+channelId,
		  btn : [ '确定', '取消' ], 
		  yes : function(index, layero) {
				var iframeWin = window[layero.find('iframe')[0]['name']]; 
				var fileInfo=iframeWin.getFileArray();
				$("#fileInfo").val(fileInfo);
				var deleteFileIds = iframeWin.getDeleteFileIds();
				$("#deleteFileIds").val(deleteFileIds);
				iframeWin.closeFrame();
		  }
		});
	}
	
	</script>
</body>
</html>