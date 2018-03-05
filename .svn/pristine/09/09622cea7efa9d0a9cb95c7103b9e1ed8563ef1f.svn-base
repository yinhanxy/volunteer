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
	<input type="hidden" id="menuId"/>
	<input  type="hidden" id="userId" value="${userId}"/>
	<script src="${sr}js/jquery.min.js" type="text/javascript"></script>
	<script src="${sr}js/config.js" type="text/javascript"></script>
	<script src="${sr}js/baseutil.js" type="text/javascript"></script>
	<script src="${sr}ztree/jquery.ztree.core.js" type="text/javascript"></script>
	<script src="${sr}ztree/jquery.ztree.excheck.min.js" type="text/javascript"></script>
	<script src="${sr}layui.js" type="text/javascript"></script>
	<script type="text/javascript">
	//tree对象
	var treeObj = null;
	
	layui.use(['jquery','layer','laytpl'],function(){
		var result=${data};
		if(result){
			menuTreeResult(result);
		}
	});
	
	function closeFrame(){
		var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
		parent.layer.close(index);
	}
	
	function menuTreeResult(result){
		if(result && result.success){
			var menus=result.data;
			var menuIds=result.menuIds;
			if(menuIds){
				$("#menuId").val(menuIds);
			}
			if(menus && menus.length>0){
				var data=[];
				buildTree(menus,data,menuIds);
				var treeData=[{id:0,name:"功能菜单",children:data}];
				treeObj = $.fn.zTree.init($("#tree"),{
					check: {
						enable: true
					},
					callback: {
						onClick: clickMenu,
						onCheck: zTreeOnCheck
					},
					view:{
						selectedMulti:false
					}
				}, treeData);
				treeObj.expandAll(true);
			}
		}else{
			layer = parent.layer;
			layer.alert(result.message, {icon: 5});
		}
	}
	
	function buildTree(json,data,menuIds) {
		for (var i = 0;i <json.length;i++) {
			var menu=json[i];
			var id=menu.id;
			var isChecked=false;
			if(menuIds && menuIds.indexOf(id)>-1){
				isChecked=true;
			}
          	var obj = {  
                  id : id,  
                  name : (menu.menuDesc != null ? menu.menuDesc :""),
                  checked:isChecked 
            };  
            if (menu.menuViews&&menu.menuViews.length>0) {  
                obj.children = [];  
                buildTree(menu.menuViews,obj.children,menuIds);  
            }
            data.push(obj);
		}
	}
	
	function clickMenu(event, treeId, treeNode){
		var flag=treeNode.checked;
		treeObj.checkNode(treeNode, !flag, true);
		var nodes = treeObj.getCheckedNodes(true);
		var menuId=[];
		 for (var i = 0; i < nodes.length; i++) {
			 var nodeId=nodes[i].id;
				if(nodeId>0)
					menuId.push(nodes[i].id);
		} 
		$("#menuId").val(menuId);
	}
	
	/**
	* 复选框点击事件
	*/
	function zTreeOnCheck(event, treeId, treeNode) {
		var nodes = treeObj.getCheckedNodes(true);
		var menuId=[];
		if(nodes.length > 0){
			for (var i = 0; i < nodes.length; i++) {
				var nodeId=nodes[i].id;
				if(nodeId>0){
					menuId.push(nodes[i].id);
				}
			}
		}
		$("#menuId").val(menuId);
	};
	
	var index;
	var submitCount=0;
	function addCheckedMenus(){
		if(++submitCount==1){
			index = layer.load(1);
			var menuIds=$("#menuId").val();
			var userId=$("#userId").val();
			var url="${webroot}user/addUserMenus.do";
			var params={"userId":userId,"menuIds":menuIds};
			postData(url, params, true,operateResult);
		}
	}
	
	/**
	**回调函数
	*/
	function operateResult(data){
		var laytpl = layui.laytpl;
		var layer = layui.layer;
		layer.close(index);
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