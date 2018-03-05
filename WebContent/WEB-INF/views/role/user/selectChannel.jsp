<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tps" uri="/pageTag"%>
<c:set var="webroot" value="${pageContext.request.contextPath}/" />
<%-- 静态资源绝对地址 --%>
<c:set var="sr" value="${pageContext.request.contextPath}/static/"/>
<!DOCTYPE html>
<html lang="zh-CN" style="overflow-x:auto;overflow-y:auto;">
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
	<link href="${sr}ztree/zTreeStyle/style.css" rel="stylesheet">
	<style type="text/css">
		.ztree li span.button.pIcon01_ico_open{margin-right:2px; background: url(${sr}css/11.png) no-repeat scroll 0 0 transparent; vertical-align:top; *vertical-align:middle}
		.ztree li span.button.pIcon01_ico_close{margin-right:2px; background: url(${sr}css/11.png) no-repeat scroll 0 0 transparent; vertical-align:top; *vertical-align:middle}
		.ztree li span.button.ico_docu {
		    margin-right: 2px;
		    background-position: -110px -16px;
		    vertical-align: top;
		}
	</style>
</head>
<body class="v-page-body">
	<div id="tree" class="ztree" style="padding:15px 20px;">
		
	</div>
	<input type="hidden" id="channelId"/>
	<input  type="hidden" id="userId" value="${userId}"/>
	<script src="${sr}js/jquery.min.js" type="text/javascript"></script>
	<script src="${sr}js/config.js" type="text/javascript"></script>
	<script src="${sr}js/baseutil.js" type="text/javascript"></script>
	<script src="${sr}ztree/jquery.ztree.core.js" type="text/javascript"></script>
	<script src="${sr}ztree/jquery.ztree.excheck.min.js" type="text/javascript"></script>
	<script src="${sr}layui.js" type="text/javascript"></script>
	<script type="text/javascript">
	var treeObj = null;
	
	layui.use(['jquery','layer'],function(){
		var result=${data};
		if(result){
			channelTreeResult(result);
		}
	});
	
	
	function closeFrame(){
		var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
		parent.layer.close(index);
	}
	
	function channelTreeResult(data){
		if(data && data.success){
			var allChannel=data.channels;
			var channelIds=data.channelIds;
			if(channelIds){
				$("#channelId").val(channelIds);
			}
			if(allChannel && allChannel.length>0){
				var nodes=[];
				build(allChannel,nodes,channelIds);
				treeObj = $.fn.zTree.init($("#tree"),{
					data: {
						simpleData: {
							enable: true,
							idKey: "id",
							pIdKey: "pId",
							rootPId: -1,
							siteId:"siteId"
						}
					},
					callback: {
						onClick: clickChannel,
						onCheck: zTreeOnCheck
					},
					view:{
						selectedMulti:false
					},
					check: {
						enable: true,
						chkboxType:{ "Y":"","N":""}
					}
				}, nodes);
			}
		}else{
			layer = parent.layer;
			layer.alert(data.message, {icon: 5});
		}
	}
	
	
	function zTreeBeforeCheck(treeId, treeNode) {
		if(treeNode.checked){
	    	return false;
		}
		return true;
	};
	
	function build(data,nodes,channelIds){
		for (var i = 0; i < data.length; i++) {
			var idValue=data[i].id;
			var pIdValue=data[i].pId;
			var isSite=data[i].site;
			var descValue=data[i].desc;
			var openValue=false;
			var parentValue=false;
			var sId=data[i].siteId;
			var isChecked = false;
			if(channelIds && $.inArray(idValue,channelIds)>-1){
				isChecked=true;
			}
			if(data[i].open){
				openValue=true;
			}
			if(data[i].parent){
				parentValue=true;
			}
			if(isSite){
				node={ id:idValue, pId:pIdValue, name:descValue, open:openValue,isParent:parentValue,iconSkin:"pIcon01","nocheck":true};
			}else{
				node={ id:idValue, pId:pIdValue, name:descValue, open:openValue,isParent:parentValue,checked:isChecked};
			}
			nodes.push(node);
		}
	}
	
	function clickChannel(event, treeId, treeNode){
		var flag=treeNode.checked;
		treeObj.checkNode(treeNode,!flag, true);
		var nodes = treeObj.getCheckedNodes(true);
		var channelId=[];
		if(nodes.length > 0){
			for (var i = 0; i < nodes.length; i++) {
				var nodeId=nodes[i].id;
				if(nodeId>0){
					channelId.push(nodes[i].id);
				}
			}
		}
		$("#channelId").val(channelId);
	}
	
	/**
	* 复选框点击事件
	*/
	function zTreeOnCheck(event, treeId, treeNode) {
		var nodes = treeObj.getCheckedNodes(true);
		var channelId=[];
		if(nodes.length > 0){
			for (var i = 0; i < nodes.length; i++) {
				var nodeId=nodes[i].id;
				if(nodeId>0){
					channelId.push(nodes[i].id);
				}
			}
		}
		$("#channelId").val(channelId);
	};
	
	function addCheckedChannels(){
		var channelIds=$("#channelId").val();
		var userId=$("#userId").val();
		var url="${webroot}user/addUserChannels.do";
		var params={"userId":userId,"channelIds":channelIds};
		postData(url, params, true,operateResult); 
	}
	
	/**
	**回调函数
	*/
	function operateResult(data){
		var layer = layui.layer;
		var message = data.message;
		if(data.success){
			layer.alert(message, {
					icon: 1
				}, function(){
					closeFrame();
			});
		}else{
			layer.alert(message, {
				icon: 2
			});
		}
	}
	</script>
</body>
</html>