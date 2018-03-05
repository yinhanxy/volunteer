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
  	<jsp:include page="../../include/title.jsp"></jsp:include>
  	
    <!-- <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,Chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    
    <title>湖北省文化志愿者管理系统</title> -->
	
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
		<jsp:include page="../../include/top.jsp"></jsp:include>
	    <!--  -->
		<jsp:include page="../../include/left.jsp"></jsp:include>
	    <!--  -->
	    <div class="main-content" style="height: 100%;min-width:1100px;" style="margin: 0px;padding: 0px;background-color: #fff;">
		    <div  class="workspace-page">
		        <div class="page-title list-page" >
		            <div class="title-env">
		                <ol class="breadcrumb bc-1" style="margin: 0px;">
		                    <li>
		                        <a href="${webroot}main.html" target="_top"><i class="fa-home"></i>首页</a>
		                    </li>
		                    <li>
		                        <a>系统管理</a>
		                    </li>
		                    <li class="active">
		                        <a  href="${webroot}area/list.html"><strong>区域管理</strong></a>
		                    </li>
		                </ol>
		            </div>
		        </div>
		        
		        <div class="table-panel panel">
		        
		             <div class="pull-left" style="padding-top: 8px;">
		                    <shiro:hasPermission name="/area/addArea.html">
			                    <a class="btn btn-success btn-sm" onclick="addArea()">
			                        <i class="fa fa-plus">新增</i>
			                    </a>
		                   </shiro:hasPermission>
		                    <a  class="btn btn-info btn-sm" onclick="refresh()">
		                        <i class="fa fa-refresh">刷新</i><span></span>
		                    </a>
		              </div>
		                            
		            <div class="dataTables">
		                <div class="dataTables_wrapper form-inline dt-bootstrap no-footer">
		                    <table  role="grid" id="areaList"
		                           class="table table-bordered table-hover  dataTable no-footer treetable">
		                        <thead>
		                        <tr role="row">
		                            <th>区域名称</th>
		                            <th style="width:90px">区域代码</th>
		                            <th style="width:90px">区域类型</th>
		                            <th style="width:290px">操作</th>
		                        </tr>
		                        </thead>
		                        <tbody class="middle-align" id="pageData">
		                        	
		                        </tbody>
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
</div>

<jsp:include page="../../include/footer.jsp"></jsp:include>		

<script src="${sr}js/jquery.min.js" type="text/javascript"></script>
<script src="${sr}js/main.js" type="text/javascript"></script>
<script src="${sr}layui.js" type="text/javascript"></script>
<script src="${sr}js/config.js" type="text/javascript"></script>
<script src="${sr}js/baseutil.js" type="text/javascript"></script>
<script src="${sr}js/jquery.treetable.js" type="text/javascript"></script>
<script type="text/javascript">
layui.use(['jquery','layer','laytpl'], function(){
	initMenu();
	var layer = layui.layer;
	layer.config({
		  extend: 'myskin/style.css'
		});
	$("#areaList").treetable({
	    expandable: true,
	    nodeIdAttr:"id",
	    parentIdAttr:"parentId",
	    clickableNodeNames:true
	});
	getAreas();
	
	$(".panel-disabled").hide();
});

function getAreas(){
	var url="${webroot}area/list.do";
	postData(url, null, true, areaShowResult);
}

/**
**回调函数
*/
function areaShowResult(data){
	$(".panel-disabled").hide();
	var laytpl = layui.laytpl;
	var layer = layui.layer;
	if(data.success){
		var list=data.list;
		if(window.console)
			console.log(list);
		if(list && list.length > 0){
    		for (var i = 0; i < list.length; i++) {
	    		var node = list[i];
	    		var parentNodeID=node.pId;
	    		if(node) {
	                var parentNode = $("#areaList").treetable("node", parentNodeID);
                        var row ='<tr data-id="' + 
                            node.id + 
                            '" data-parent-id="' +
                            parentNodeID + '" ';
                        if(node.isParent) {
                            row += ' data-tt-branch="true" ';
                        }
                        row += ' >';
                        row += '<td ><span controller="true">' + node.name+ '</span></td><td>' + node.code + '</td><td>' + node.areaType + '</td>';
                        row += '<td> ';
                        row += '<div class="btn-group btn-group-sm btn-group-xs">'; 
                        row += '  <shiro:hasPermission name="/area/viewArea.html">';
                        row += '	<a class="btn btn-secondary btn-icon" style="margin-right:5px;" title="查看" onclick="viewArea('+node.id +')"> <i class="fa-search">查看</i> </a>'; 
                       	row += '  </shiro:hasPermission>';
                        row += '  <shiro:hasPermission name="/area/editArea.html">';
                        row += '    <a class="btn btn-info btn-icon" style="margin-right:5px;" title="编辑" onclick="editArea('+node.id +')"> <i class="fa fa-pencil-square-o">编辑</i> </a> ';
                        row += '  </shiro:hasPermission>';
                        if(node.id==1){
                        	row += '<shiro:hasPermission name="/area/delArea.do">';
                        	row += '	<a class="btn btn-danger btn-icon " style="margin-right:5px;" disabled> <i class="fa fa-trash-o">删除</i> </a>';
                        	row += '</shiro:hasPermission>';
                        }else{
                        	row += '<shiro:hasPermission name="/area/delArea.do">';
                        	row += '  <a class="btn btn-danger btn-icon" style="margin-right:5px;" title="删除" onclick="delArea('+node.id +','+node.parent+')"> <i class="fa fa-trash-o">删除</i> </a>'; 
                        	row += '</shiro:hasPermission>';
                        }
                        if(node.type==2){
                        	row += '<shiro:hasPermission name="/area/addSubArea.html">';
                        	row += '  <a class="btn btn-success btn-icon" style="margin-right:5px;" disabled> <i class="fa fa-plus">新增下级区域</i> </a> ';
                        	row += '</shiro:hasPermission>';
                        }else{
                        	row += '<shiro:hasPermission name="/area/addSubArea.html">';
                        	row += '	<a class="btn btn-success btn-icon" style="margin-right:5px;" title="新增下级区域" onclick="addSubArea('+node.id +')"> <i class="fa fa-plus">新增下级区域</i> </a> ';
                        	row += '</shiro:hasPermission>';
                        }
                        row += '</div>'; 
                        row += '</td>';
                   		row +='</tr>';
                        
                        $("#areaList").treetable("loadBranch", parentNode, row);
	                }
	            }				
    		
    		$("#messageDiv").hide();
		}else{
			$("#pageData").html("");
			$("#messageDiv").show();
		}
	}else{
		var message = data.message;
		$("#pageData").html("");
		layer.msg(message);
	}
	$("#areaList").treetable("collapseAll","");
	$("#areaList").treetable("expandNode","1");
}

<shiro:hasPermission name="/area/addArea.html">
//添加区域
function addArea(){
	var layer = layui.layer;
	//iframe层-父子操作
	layer.open({
	  type: 2,
	  title:"新增区域",
	  area: ['450px', '480px'],
	  maxmin: true,
	  scrollbar: false,
	  content: ["${webroot}area/addArea.html","no"]
	});
}
</shiro:hasPermission>

<shiro:hasPermission name="/area/addSubArea.html">
//添加下级区域
function addSubArea(id){
	var layer = layui.layer;
	//iframe层-父子操作
	layer.open({
	  type: 2,
	  title:"添加下级区域",
	  area: ['450px', '480px'],
	  maxmin: true,
	  scrollbar: false,
	  content: ["${webroot}area/addSubArea.html?id="+id,"no"]
	});
}
</shiro:hasPermission>

<shiro:hasPermission name="/area/viewArea.html">
//查看区域
function viewArea(id){
	var layer = layui.layer;
	//iframe层-父子操作
	layer.open({
	  type: 2,
	  title:"区域信息",
	  skin: 'view-class',
	  area: ['450px', '430px'],
	  maxmin: true,
	  scrollbar: false,
	  content: ["${webroot}area/viewArea.html?id="+id,"no"],
	  btn: ['<i class="fa fa-remove"></i>关闭'],
	  closeBtn:1
	});	
}
</shiro:hasPermission>

<shiro:hasPermission name="/area/editArea.html">
//修改区域
function editArea(id){
	var layer = layui.layer;
	//iframe层-父子操作
	layer.open({
	  type: 2,
	  title:"修改区域信息",
	  area: ['450px', '480px'],
	  maxmin: true,
	  scrollbar: false,
	  content: ["${webroot}area/editArea.html?id="+id,"no"]
	});	
}
</shiro:hasPermission>

<shiro:hasPermission name="/area/delArea.do">
//删除区域
function delArea(id,isParent){
	var params=null;
	if(id){
		var msg="确定要删除该区域吗？";
		if(isParent){
			msg="确定要删除该区域及其下辖区域吗？";
		}
		layer.confirm(msg, {icon: 3, title:'系统提示',
			  btn: ['确定','取消'] //按钮
			}, function(){
				var url="${webroot}area/delArea.do";
				params={"id":id};
				postData(url, params, true, function(data){
					var laytpl = layui.laytpl;
					var layer = layui.layer;
					var message=data.message;
					if(data && data.success){
						layer.msg(message, {icon: 1,time:1000});
						refresh();
					}else{
						layer.msg(message, {icon: 2,time:1000});
					}
				}); 
			  	
			});
	}
}
</shiro:hasPermission>

function refresh(){
	$("#pageData").html("");
	getAreas();
}

</script>
</body>
</html>