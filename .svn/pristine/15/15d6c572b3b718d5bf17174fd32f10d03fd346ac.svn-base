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
	</style>
</head>
<body class="v-page-body">
	<div id="tree" class="ztree" style="padding:15px 20px;">
		
	</div>
	<input type="hidden" id="${channelId}">
	<script src="${sr}js/jquery.min.js" type="text/javascript"></script>
	<script src="${sr}js/config.js" type="text/javascript"></script>
	<script src="${sr}js/baseutil.js" type="text/javascript"></script>
	<script src="${sr}ztree/jquery.ztree.core.js" type="text/javascript"></script>
	<script src="${sr}ztree/jquery.ztree.excheck.min.js" type="text/javascript"></script>
	<script type="text/javascript">
	$(document).ready(function(){
		getSites();
	});
	
	function getSites(){
		var url="${webroot}channel/siteList.do";
		postData(url, null, true, siteTreeResult);
	}
	
	function closeFrame(){
		var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
		parent.layer.close(index);
	}
	
	function channelTreeResult(data){
		console.log(data);
		if(data && data.success){
			var allChannel=data.channels;
			if(allChannel && allChannel.length>0){
				var nodes=[];
				build(allChannel,nodes);
				$.fn.zTree.init($("#tree"),{
					data: {
						simpleData: {
							enable: true,
							idKey: "id",
							pIdKey: "pId",
							rootPId: -1
						}
					},
					callback: {
						/* onClick: clickOrg, */
						beforeCheck: zTreeBeforeCheck
					},
					view:{
						selectedMulti:false
					},
					check: {
						enable: true,
						chkStyle: "radio",
						radioType: "all"
					}
				}, nodes);
			}
		}
	}
	
	function zTreeBeforeCheck(treeId, treeNode) {
		if(treeNode.checked){
	    	return false;
		}
		return true;
	};
	
	function build(data,nodes){
		console.log(data);
		for (var i = 0; i < data.length; i++) {
			var idValue=data[i].id;
			var pIdValue=data[i].pId;
			var nameValue=data[i].name;
			var openValue=false;
			var parentValue=false;
			var chkDisabledValue=false;
			if(data[i].open){
				openValue=true;
			}
			if(data[i].parent){
				parentValue=true;
			}
			if(data[i].chkDisabled){
				chkDisabledValue=true;
			}
			if(pIdValue==-1){
				node={ id:idValue, pId:pIdValue, name:nameValue, open:openValue,isParent:parentValue,iconSkin:"pIcon01",chkDisabled:chkDisabledValue};
			}else{
				node={ id:idValue, pId:pIdValue, name:nameValue, open:openValue,isParent:parentValue,chkDisabled:chkDisabledValue};
			}
			nodes.push(node);
		}
		console.log(nodes);
	}
	function clickOrg(event, treeId, treeNode){
		$("input").attr("id",treeNode.id);
		$("input").attr("name",treeNode.name);
	}
	
	function selectOrg(){
		var name=$("input").attr("name");
		var id=$("input").attr("id");
		var data={"id":id,"name":name};
		closeFrame();
		return data;
		
	}
	</script>
</body>
</html>