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
	<link href="${sr}ztree/metroStyle/style.css" rel="stylesheet">
</head>
<body class="v-page-body">
	<div id="tree" class="ztree" style="padding:15px 20px;">
		
	</div>
	<input type="hidden" >
	<script src="${sr}js/jquery.min.js" type="text/javascript"></script>
	<script src="${sr}js/config.js" type="text/javascript"></script>
	<script src="${sr}js/baseutil.js" type="text/javascript"></script>
	<script src="${sr}ztree/jquery.ztree.core.js" type="text/javascript"></script>
	<script type="text/javascript">
	$(document).ready(function(){
		var data;
		getAreas();
	});
	
	function getAreas(){
		var url="${webroot}area/loadArea.do";
		var params={"id":1};
		postData(url, params, true, areaTreeResult);
	}
	
	function closeFrame(){
		var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
		parent.layer.close(index);
	}
	
	function areaTreeResult(data){
		if(data && data.success){
			var areas=data.areaViews;
			if(areas && areas.length>0){
				var nodes=[];
				build(areas,nodes);
				$.fn.zTree.init($("#tree"),{
					data: {
						simpleData: {
							enable: true,
							idKey: "id",
							pIdKey: "pId",
							rootPId: 0
						}
					},
					callback: {
						onClick: clickArea
					},
					view:{
						selectedMulti:false
					},
					async:{
						enable:true,
						autoParam: ["id"],
						contentType:"application/x-www-form-urlencoded",
						dataFilter: ajaxDataFilter,
						dataType: "text",
						url: "${webroot}area/loadArea.do"
					}
				}, nodes);
			}
		}
	}
	
	function ajaxDataFilter(treeId, parentNode, responseData){
		var data=responseData;
		if(data && data.success){
			var areas=data.areaViews;
			areas.shift();
			if(areas && areas.length>0){
				var nodes=[];
				build(areas,nodes);
				return nodes;
			}
		}	
	}
	
	function build(data,nodes){
		for (var i = 0; i < data.length; i++) {
			var idValue=data[i].id;
			var pIdValue=data[i].pId;
			var nameValue=data[i].name;
			if(data[i].open){
				var openValue=data[i].open;
			}
			if(data[i].parent){
				var parentValue=data[i].parent;
			}
			var node={ id:idValue, pId:pIdValue, name:nameValue, open:openValue,isParent:parentValue};
			nodes.push(node);
		}
	}
	function clickArea(event, treeId, treeNode){
		$("input").attr("id",treeNode.id);
		$("input").attr("name",treeNode.name);
	}
	
	function selectArea(){
		var name=$("input").attr("name");
		var id=$("input").attr("id");
		var data={"id":id,"name":name};
		closeFrame();
		return data;
		
	}
	</script>
</body>
</html>