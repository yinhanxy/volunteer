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
	<link href="${sr}ztree/metroStyle/style.css" rel="stylesheet">
	<link href="${sr}css/jquery.mCustomScrollbar.min.css" rel="stylesheet">
    <!--[if lt IE 9]>
      <script src="${sr}js/html5shiv.min.js"></script>
      <script src="${sr}js/respond.min.js"></script>
    <![endif]-->
    <style type="text/css">
		ul.ztree {margin-top: 10px;border: 1px solid #ccc;border-radius:4px;background: #f0f6e4;width:220px;height:320px;overflow-x:auto;}
		.ztree span{display:inline-block;}
    </style>
</head>
<body class="v-page-body">
	<div class="page-container">
		<div class="panel">
			<form id="addMenuForm" action="${webroot}menu/addMenu.do" method="post">
				<div class="row">
				<input type="hidden" name="id" value="0">
				<table class="table table-bordered dataTable no-footer">
					<tr>
						<td class="col-xs-2"><label for="inputEmail1">菜单名称<small><code >*</code></small></label></td>
						<td class="col-xs-10">
							 <input type="text" class="form-control" name="menuName" placeholder="菜单名称(2-30位的字母)"  value=""
								data-validation="custom" data-validation-regexp="^[a-zA-Z_]{2,30}$"
		                   		data-validation-error-msg-custom="此项必须为2-30位的字母和下划线"
		                   		data-validation-error-msg-container="#nError"/>
                  		</td>
					</tr>
					<tr>
						<td class="col-xs-2"><label for="inputEmail1">菜单描述<small><code >*</code></small></label></td>
						<td class="col-xs-10">
							<input type="text" class="form-control" name="menuDesc" placeholder="菜单名称(2-50位的汉字与字母)"  value=""
								data-validation="custom" data-validation-regexp="^[a-zA-Z\u4e00-\u9fa5]{2,50}$"
		                   		data-validation-error-msg-custom="此项必须为2-50位的汉字和字母"
		                   		data-validation-error-msg-container="#nError"/>
                  			</td>
					</tr>
					<tr>
						<td class="col-xs-2"><label for="inputEmail1">上级菜单<small><code >*</code></small></label></td>
						<td class="col-xs-10">
							<input type="text" class="form-control" readonly="readonly" name="parentName" id="parentName" style="background:#fff;cursor:pointer;" placeholder="上级菜单"  value="" />
							<input type="hidden" value="${pid }" name="parentId" id="parentId">
								<div id="treeview" style="position: absolute;z-index:20000;display:none;">
									<ul id="menuContent" class="ztree" style="margin-top:0;height:300px;" ></ul>
								</div>
                  		</td>
					</tr>
					<tr>
						<td><label for="inputEmail2">菜单类型<small><code >*</code></small></label></td>
						<td >
						<input type="radio" value="0" checked="checked" name="menuType" >菜单
						<input type="radio" value="1" name="menuType">按钮
                  		</td>
					</tr>
					<tr>
						<td class="col-xs-2"><label for="inputEmail1">菜单图标</label></td>
						<td class="col-xs-10">
							<input type="text" class="form-control" name="icon" placeholder="菜单图标"  value="" />
                  		</td>
					</tr>
					<tr>
						<td class="col-xs-2"><label for="inputEmail1">菜单URL</label></td>
						<td class="col-xs-10">
							<input type="text" class="form-control" name="url" placeholder="菜单URL"  value="" />
                  		</td>
					</tr>
					
					<tr>
						<td class="col-xs-2"><label for="inputEmail1">菜单排序号</label></td>
						<td class="col-xs-10">
							<input type="text" class="form-control" name="orderNo" placeholder="菜单排序号"  value="" />
							
                  		</td>
					</tr>
					<tr>
						<td class="col-xs-2"><label for="inputEmail1">是否可见<small><code >*</code></small></label></td>
						<td class="col-xs-10">
							<input type="radio" value="1" checked="checked" name="isShow" >可见
							<input type="radio" value="0" name="isShow">不可见
                  		</td>
					</tr>
				</table>
				</div>
		</form>
		</div>		
	</div>
	
	<div class="v-form-footer">
	   	<button type="button" id="sub" class="btn btn-sm btn-success"><i class="fa fa-plus"></i>保存</button>
	   	<button type="button" class="btn btn-sm" onclick="closeFrame()"><i class="fa fa-remove"></i>关闭</button>
	</div>
	

	
	<script src="${sr}js/jquery.min.js" type="text/javascript"></script>
	<script src="${sr}js/config.js" type="text/javascript"></script>
	<script src="${sr}js/baseutil.js" type="text/javascript"></script>
	<script src="${sr}js/jquery.form-validator.min.js" type="text/javascript"></script>
	<script src="${sr}js/jquery-ui.min.js" type="text/javascript"></script>
	<script src="${sr}layui.js" type="text/javascript"></script>
	<script src="${sr}ztree/jquery.ztree.core.js" type="text/javascript"></script>
	<script src="${sr}js/jquery.mousewheel-3.0.6.min.js" type="text/javascript"></script>
	<script src="${sr}js/jquery.mCustomScrollbar.min.js" type="text/javascript"></script>
	<script type="text/javascript">
	layui.use(['jquery','layer','laytpl'],null);

	
	$(document).ready(function(){
		postData("./loadMenu.do", {}, true, function (result){
			var success=result.success;
			if(success){
				var data=[];
				buildTree(result.data,data);
				var treeData=[{id:0,name:"根节点",children:data}];
				var zTree = $.fn.zTree.init($("#menuContent"), {
					view: {
						selectedMulti: false,
						dblClickExpand:false
					},callback: {
						onClick: function (event, treeId, treeNode){
							if(treeNode){
								var parentMenuId=treeNode.id;
								var parentPath=getPathText(treeNode);
								$("#parentName").val(treeNode.name);
								$("#parentId").val(parentMenuId);
								hideMenu();
							}
						}
					}
				},treeData);
				zTree.expandAll(true);
				var node =zTree.getNodeByParam("id", "${pid}", null);
				$("#parentName").val(node.name);
				if(node){
					$("#parentName").val(node.name);
					zTree.selectNode(node,false,false);
				}
				$("#menuContent").mCustomScrollbar({
					setHeight:300,
					theme:"minimal-dark"
				});
                 
			}
		},function (XMLHttpRequest, textStatus, errorThrown){

		});
	});
	
	var errors =[],
	conf = {
		onElementValidate : function(valid, $el, $form, errorMess) {
		     if( !valid ) {
		    	errors.push({el: $el, error: errorMess});
				return ;
		     }
			  	
		  } 
		 
	  };
	$('#sub').on('click', function() {
		errors =[];
		if( !$('#addMenuForm').isValid({},conf, true)) {
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
			clearValid("addMenuForm");
			return false;
	   	}else {
	   		clearValid("addMenuForm");
	   		$("#sub").prop("disabled",true);
			postFormData("addMenuForm",true,editResult);
	    } 
	});
	
	
	function clearValid(formId){
		var els = $("#"+formId+" :input");
		els.removeClass("valid");
	}
	/**
	**编辑菜单的回调函数
	*/
	function editResult(data){
		var layer = layui.layer;
		var message;
		if(data && data.success){
			message=data.message;
			layer.alert(message, {icon: 6},function(index){
			    parent.loadData();
			    parent.initMenu();
			    closeFrame();
			  });
		}else{
			errorTips(data);
			clearValid("addMenuForm");
		}
		$("#sub").prop("disabled",false);
	}
	function buildTree(json,data) {
		for (var i = 0;i <json.length;i++) {
			var menu=json[i];
         	if(menu.menuType==0){
	          	var obj = {  
	                  id : menu.id,  
	                  name : (menu.menuDesc != null ? menu.menuDesc :"")
	            };  
	            if (menu.menuViews&&menu.menuViews.length>0) {  
	                obj.children = [];  
	                buildTree(menu.menuViews,obj.children);  
	            }
	            data.push(obj);
         	}
		}
	}
	
	 function getPathText(node){
         var s=node.id;
         while(node=node.getParentNode())s=node.id+'/'+s;
         return s;
      }
	 
	function onBodyDown(event) {
		if (!(event.target.id == "parentName" || event.target.id == "menuContent" || $(event.target).parents("#menuContent").length>0)) {
			hideMenu();
		}
	}
	
	function hideMenu(){
		$("#treeview").fadeOut("fast");
		$("body").unbind("mousedown", onBodyDown);
	}
	 

	$("#parentName").click(function() {
		var parentObj = $("#parentName");
		var parentOffset = $("#parentName").offset();
		$("#treeview").css({left:parentOffset.left + "px", top:parentOffset.top + parentObj.outerHeight() + "px"});
		$("#treeview").slideDown(800);
		$("body").bind("mousedown", onBodyDown);
	});

	function closeFrame(){
		var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
		parent.layer.close(index);
	}
	</script>
</body>
</html>