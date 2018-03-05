<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>  
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
	<link href="${sr}css/jquery.treetable.css" rel="stylesheet">
	<link href="${sr}css/jquery.treetable.theme.default.css" rel="stylesheet">
    <!--[if lt IE 9]>
      <script src="${sr}js/html5shiv.min.js"></script>
      <script src="${sr}js/respond.min.js"></script>
    <![endif]-->

</head>
<body class="page-body skin-facebook">

	<div class="page-container">
		<jsp:include page="../include/top.jsp"></jsp:include>
	    <!--  -->
		<jsp:include page="../include/left.jsp"></jsp:include>
		
		 <div class="main-content" style="height: 100%;min-width:1100px;" style="margin: 0px;padding: 0px;background-color: #fff;">
		    <div  class="workspace-page">
		        <div class="page-title list-page" >
		            <div class="title-env">
		                <ol class="breadcrumb bc-1" style="margin: 0px;">
		                    <li>
		                        <a href="${webroot}main.html" target="_top"><i class="fa-home"></i>首页</a>
		                    </li>
		                    <li>
		                        <a>权限管理</a>
		                    </li>
		                    <li class="active">
		                        <a  href="${webroot}menu/list.html"><strong>菜单管理</strong></a>
		                    </li>
		                </ol>
		            </div>
		        </div>
		        
		        <div class="table-panel panel">
		            <div class="panel-heading" style="padding-bottom: 0px;">
		           			<div class="btn-toolbar" style="margin-left:0px;">
			           			<form class="form-inline" id="searchForm" action="${webroot}menu/loadMenu.do"></form>
				             </div>
		            </div>
		             <div class="pull-left" style="padding-top: 8px;">
		                    <shiro:hasPermission name="/menu/addMenu.html">
			                    <a class="btn btn-success btn-sm" onclick="addMenu(0)">
			                        <i class="fa fa-plus"></i>新建菜单
			                    </a>
		                   </shiro:hasPermission>
		              </div>
		              <div class="form-inline">
		                    <table id="menuList" class="table table-bordered table-hover">
		                        <thead>
		                        <tr role="row" class="table-striped">
		                            <th style="width:30%">菜单名称</th>
		                            <th>菜单URL</th>
		                            <th style="width:80px;">菜单图标</th>
		                            <th style="width:280px;">操作</th>
		                        </tr>
		                        </thead>
		                        <tbody class="middle-align" id="pageData"></tbody>
		                    </table>
		                    <div class="panel-disabled">
			                    <div class="loader-1"></div>
			                </div>
		                    <div style="margin-top: -20px; line-height: 50px;border: 1px solid #ddd; border-top: 0;display: none;" id="messageDiv">
		                        <p class="text-center">暂无数据</p>
		                    </div>
		                </div>
		        </div>
		    </div>
	</div> 
	</div>		
		
<jsp:include page="../include/footer.jsp"></jsp:include>		

<script src="${sr}js/jquery.min.js" type="text/javascript"></script>
<script src="${sr}js/main.js" type="text/javascript"></script>
<script src="${sr}layui.js" type="text/javascript"></script>
<script src="${sr}js/config.js" type="text/javascript"></script>
<script src="${sr}js/baseutil.js" type="text/javascript"></script>
<script src="${sr}js/jquery.treetable.js" type="text/javascript"></script>

<script type="text/javascript">

$("#menuList").treetable({
    expandable: true,
    nodeIdAttr:"id",
    parentIdAttr:"parentId",
}); 
layui.use(['layer','jquery','laytpl'],function (){
	initMenu();
	loadData();
});
function loadData(){
	<shiro:hasPermission name="/menu/loadMenu.do">
	var layer = layui.layer;
	var layIndex=layer.load(0, {shade: false});
	postData("./loadMenu.do", {}, true, function (result){
		$('#pageData').html('');
		$(".panel-disabled").hide();
		var success=result.success;
		layer.close(layIndex);
		if(success){
			progressData(result.data);
			$("#menuList").treetable("collapseAll","");
		}else{
			$("#messageDiv").html(result.messsage);
			$("#messageDiv").show();
			$(".panel-disabled").hide();
		}
	},function (XMLHttpRequest, textStatus, errorThrown){
		layer.close(layIndex);
		$("#messageDiv").html("获取菜单列表失败")
		$("#messageDiv").show();
		$(".panel-disabled").hide();
	});
	</shiro:hasPermission>
}

function progressData(json){
	for (var i = 0;i <json.length;i++) {
		var showContent = "";//添加内容变量
		var menu = json[i];
		var parentNode = $("#menuList").treetable("node", menu.parentId);
		if(menu.menuViews&&menu.menuViews.length>0){
			showContent += "<tr data-id='" + menu.id + "' data-parent-id='" + menu.parentId + "' data-tt-branch='true'>";
		}else{
			showContent += "<tr data-id='" + menu.id + "' data-parent-id='" + menu.parentId + "'>";
		}
		showContent += "<td><span controller='true'>" + menu.menuDesc + "</span></td>";
		showContent += "<td>" + menu.url + "</td>";
		showContent += "<td style='text-align:center;'><i class='fa " +menu.icon+ "'></td>";
		showContent += "<td>";
		showContent +="<shiro:hasPermission name='/menu/viewMenu.html'><a class=\"btn btn-xs btn-secondary btn-icon\" onclick=\"viewMenu("+menu.id+")\"   style=\"margin-left:0px;\" title=\"查看\"><i class=\"fa-search\"></i>查看</a>	</shiro:hasPermission>";
		showContent +="<shiro:hasPermission name='/menu/editMenu.html'><a class=\"btn btn-xs btn-info btn-icon\" onclick=\"editMenu("+menu.id+")\"    style=\"margin-left:0px;\"     title=\"编辑\"><i class=\"fa fa-pencil-square-o\"></i>编辑</a></shiro:hasPermission>";
		showContent +="<shiro:hasPermission name='/menu/delMenu.do'><a class=\"btn btn-xs btn-danger btn-icon\"  onclick=\"delMenu("+menu.id+")\" style=\"margin-left:5px;\"     title=\"删除\" ><i class=\"fa fa-pencil-square-o\"></i>删除</a></shiro:hasPermission>";
		if(menu.menuType==0){
			showContent +="<shiro:hasPermission name='/menu/addMenu.html'><a class=\"btn btn-xs btn-success btn-icon\" onclick=\"addMenu("+menu.id+")\"  style=\"margin-left:5px;\"     title=\"新建子菜单\" ><i class=\"fa fa-pencil-square-o\"></i>新建子菜单</a></shiro:hasPermission>";
		}else{
			showContent +="<shiro:hasPermission name='/menu/addMenu.html'><a class=\"btn btn-xs btn-success btn-icon\"    style=\"margin-left:5px;\"   disabled=\"disabled\"   title=\"新建子菜单\" ><i class=\"fa fa-pencil-square-o\"></i>新建子菜单</a></shiro:hasPermission>";
		}	
		showContent +="</td>";
		showContent += "</tr>";
		$("#menuList").treetable("loadBranch", parentNode, showContent);
		if(menu.menuViews&&menu.menuViews.length>0){
			progressData(menu.menuViews);				
		}
	}
}


//查看菜单
function viewMenu(id){
	var layer = layui.layer;
	//iframe层-父子操作
	layer.open({
	  type: 2,
	  title:"查看菜单",
	  area: ['650px', '320px'],
	  maxmin: true,
	  scrollbar: false,
	  content: "${webroot}menu/viewMenu.html?mid="+id
	});
}

//新建菜单
function addMenu(id){
	var layer = layui.layer;
	//iframe层-父子操作
	layer.open({
	  type: 2,
	  title:"新建菜单",
	  area: ['650px', '520px'],
	  maxmin: true,
	  scrollbar: false,
	  content: "${webroot}menu/addMenu.html?pid="+id,
	  //关闭层后的回调函数
	  end:function(){
		  
	  }
	});
}

//编辑菜单
function editMenu(id){
	var layer = layui.layer;
	//iframe层-父子操作
	layer.open({
	  type: 2,
	  title:"编辑菜单",
	  area: ['650px', '520px'],
	  maxmin: true,
	  scrollbar: false,
	  content: "${webroot}menu/editMenu.html?mid="+id,
	  //关闭层后的回调函数
	  end:function(){
		  
	  }
	});
}

function delMenu(id){
	layer.confirm('删除菜单后不可恢复,确定要删除这个菜单吗？', {icon: 3, title:'系统提示',
		  btn: ['确定','取消'] 
		}, function(){
			var url="${webroot}menu/delMenu.do";
			params={"id":id};
			postData(url, params, true, function (result){
				if(result&&result.success){
					layer.msg(result.message, {icon: 1,time:1000});
					loadData();
					initMenu();
				}else{
					layer.msg(result.message, {icon: 2,time:1000});
				}
			}); 
		  	
		}, function(){
		  
		});
}
</script> 
</body>
</html>