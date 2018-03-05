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
	<link href="${sr}ztree/metroStyle/style.css" rel="stylesheet">
	<!-- <style type="text/css">
		@media screen and (min-width: 1600px) {
			#areaTree {width: 200px} 
		} 
		@media screen and (max-width: 1500px) { 
			#areaTree {width: 140px}
		}
	</style> -->
</head>
<body class="page-body skin-facebook">
	<div class="page-container">
		<jsp:include page="../include/top.jsp"></jsp:include>
        <!--  -->
		<jsp:include page="../include/left.jsp"></jsp:include>
        <!--  -->
        <div class="main-content" style="height: 100%;min-width:1100px;">
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
		                        <a  href="${webroot}org/orgList.html"><strong>机构管理</strong></a>
		                    </li>
		                </ol>
		            </div>
		        </div>
		        <div class="table-panel panel">
		        	
					<div class="pull-left" style="padding-top: 8px;">
						<shiro:hasPermission name="/org/addOrg.html">
							<a class="btn btn-success btn-sm" onclick="addOrg()">
							    <i class="fa fa-plus">新增</i>
							</a>
						</shiro:hasPermission>
						<a  class="btn btn-info btn-sm" onclick="refresh()">
						    <i class="fa fa-refresh">刷新</i><span></span>
						</a>
					</div>
					<form name="searchOrgForm" class="form-inline" id="searchOrgForm" action="${webroot}org/orgSelect.do" style="margin-top: 8px;float: right;" method="post" onsubmit="return false">
						<input type="hidden" id="orgId" name="orgId" >
						<span><input type="text" name="selectName" id="selectName" class="form-control"></span>
						<span>
							<select class="form-control" name="selectType" id="selectType">
								<option value="1">机构名称</option>
								<option value="2">备注</option>
							</select>
						</span>
						<span>
							<a class="btn btn-success btn-sm" id="searchBtn" style="float: none;margin-bottom: 2px;">
						        <i class="fa fa-search">查询</i>
						    </a>
						</span>  
						<input type="hidden" name="page" id="currentPageInput" value="1"/> 
						<input type="hidden" name="rows" id="pageSizeInput" />
					</form> 
					<div style="clear:both;"></div> 
					<div class="row">
		            <div class="col-sm-1" id="areaTree" style="border:1px solid #eee;margin-left:15px;min-height: 480px;min-width: 150px;float:left;overflow-x:auto;overflow-y:auto;padding-left: 0px;">
			         	<div id="tree" class="ztree">
							
						</div>
					</div>
		            <div class="dataTables col-sm-10" style="padding: 0px;margin-top: 0px;position: relative;">
		                <div class="dataTables_wrapper form-inline dt-bootstrap no-footer">
		                    <table  role="grid" id="orgList" 
		                           class="table table-bordered table-hover  dataTable no-footer " style="margin: 0px;border-left: none; ">
		                        <thead>
		                        <tr role="row">
		                            <th>机构名称</th>
		                            <th style="width: 90px">机构类型</th>
		                            <th style="width: 370px">操作</th>
		                        </tr>
		                        </thead>
		                        <tbody class="middle-align" id="pageData">
		                        	
		                        </tbody>
		                    </table>
		                   	 <div class="row" style="display: none;margin-top: 5px;" id="pageInfo">
		                    	 
		                    </div>
		                    <div class="panel-disabled">
			                    <div class="loader-1"></div>
			                </div>
		                    <div style="line-height: 50px;border: 1px solid #eee; border-top: 0;display: none;" id="messageDiv">
		                        <p class="text-center">暂无数据</p>
		                    </div>
		                    <script id="templateData" type="text/html">
									{{#  layui.each(d.list, function(index, item){ }}	
			                        <tr role="row"
			                            data-toggle="collapse">
			                            <td>
			                                <div>
			                                   <span style="padding: 0px;">
													<shiro:hasPermission name="/org/viewOrg.html">
														<a href="javascript:void(0)" onclick="viewOrg({{item.id}})">
													</shiro:hasPermission>
															{{item.name}}
											   		<shiro:hasPermission name="/org/viewOrg.html">	
														</a>
													</shiro:hasPermission>
												</span>
			                                </div>
			                            </td>
			                             <td>
			                                <div>
			                                   <span style="padding: 0px;">{{item.typeName }}</span>
			                                </div>
			                            </td>
										<td>
			                                <div>
											<span class="btn-group btn-group-sm btn-group-xs">
	                                            <shiro:hasPermission name="/org/editOrg.html">
	                                               <a class="btn btn-info btn-icon" style="margin-right:5px;" title="编辑" onclick="editOrg({{item.id}})"> <i class="fa fa-pencil-square-o">编辑</i> </a>
	                                            </shiro:hasPermission>
	                        	                <shiro:hasPermission name="/org/delOrg.do">
	                        						<a class="btn btn-danger btn-icon" style="margin-right:5px;" title="删除" onclick="delOrg({{item.id}})"> <i class="fa fa-trash-o">删除</i> </a>
	                        					</shiro:hasPermission>
	                        	                <shiro:hasPermission name="/org/addOrg.html">
	                        	                    <a class="btn btn-success btn-icon" style="margin-right:5px;" title="新增子机构" onclick="addOrg({{item.id}})"> <i class="fa fa-plus">新增子机构</i> </a>
	                        	                </shiro:hasPermission>
	                        	                <shiro:hasPermission name="/org/orgUser.html">
	                        	                    <a class="btn btn-success btn-icon"   title="用户管理" style="margin-right:5px;" onclick="setupUser({{item.id}})"><i class="fa fa-cog">用户配置</i></a>
	                        	                </shiro:hasPermission>
                                            </span>
			                                </div>
			                            </td>
			                        </tr>
									{{#  }); }}
									</script>
		                </div>
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
	<script src="${sr}ztree/jquery.ztree.core.js" type="text/javascript"></script>
	<script type="text/javascript">
		layui.use(['jquery','layer','laytpl'], function(){
			var layer = layui.layer;
			layer.config({
				  extend: 'myskin/style.css'
				});
			initMenu();
			getAreas();
			getOrgs();
			$(".panel-disabled").hide();
			//查询按钮
		  	$("#searchBtn").click(function(){
		  		if ($("#selectName").val() != null && $("#selectName").val() !="") {
		  			$("#currentPageInput").val(1);
			  		postFormData("searchOrgForm",true,searchResult);	
				}else{
					layer.msg("请输入要查询的内容！", {time: 1000});
				}
		  	});
		});
		
		/**
		*切换每页显示的条数
		*/
		function changePage(obj,searchFormId){
			var pageSize = $(obj).val();
			if(console.log){
				console.log("pageSize:"+pageSize);
			}
			$("#pageSizeInput").val(pageSize);
			$("#currentPageInput").val(1);
			postFormData(searchFormId,true,searchResult);
		}
		
		/**
		*翻页
		*/
		function setCurrentPage(currentPage,searchFormId){
			$("#currentPageInput").val(currentPage);
			postFormData(searchFormId,true,searchResult);
		}
		
		/**
		*跳转到设置的页面
		*/
		function goPage(maxPage,searchFormId){
			var j_goPageText = $("#goPageText");
			if(j_goPageText){
				try{
					var currentPage = parseInt(j_goPageText.val());
					if(currentPage > 0  && currentPage <= maxPage){
						$("#currentPageInput").val(currentPage);
						postFormData(searchFormId,true,searchResult);
					}else{
						layer.msg("页码输入错误", {time: 1000});
					}
				}catch(e){
					layer.msg("页码输入错误", {time: 1000});
				}
			}
		}
		

		/**
		**回调函数
		*/
		function searchResult(data){
			$(".panel-disabled").hide();
			$("#pageData").html("");
			var laytpl = layui.laytpl;
			var layer = layui.layer;
			if(data.success){
	    		var page = data.page;
	    		var list = page.list;
	    		if(list && list.length > 0){
	    			var templateHtml = $("#templateData").html();
	        		laytpl(templateHtml).render(page, function(result){
	        		    $("#pageData").html(result);
	        		  });
	        		var pageHtml = createPageInfo(page, "searchOrgForm");
	        		$("#pageInfo").html(pageHtml);
	        		$("#pageInfo").show();
	        		$("#messageDiv").hide();
	    		}else{
	    			$("#pageData").html("");
	    			$("#pageInfo").html("");
	    			$("#pageInfo").hide();
	    			$("#messageDiv").show();
	    		}
			}else{
				var message = data.message;
				$("#pageData").html("");
				$("#pageInfo").html("");
				$("#pageInfo").hide();
				getOrgs()
				layer.msg(message);
			}
		}
		
		var index =null;
		function getOrgs(areaId){
			$("#pageInfo").hide();
			index = layer.load();
			var params=null;
			if(!areaId){
				areaId=1;
			}
			params={"id":areaId};
			var url="${webroot}org/orgList.do";
			postData(url,params, true, orgShowResult);	
		}
		
		<shiro:hasPermission name="/org/addOrg.html">
		//添加机构
		function addOrg(id){
			var layer = layui.layer;
			//iframe层-父子操作
			var index=layer.open({
			  type: 2,
			  title:"新增机构",
			  area: ['700px'],
			  scrollbar: false,
			  content: ["${webroot}org/addOrg.html?id="+id,"no"]
			});
			layer.full(index);
		}
		</shiro:hasPermission>
		
		//查看机构
		function viewOrg(id){
			var layer = layui.layer;
			var index=layer.open({
			  type: 2,
			  title:"机构信息",
			  skin: 'view-class',
			  area: ['650px'],
			  scrollbar: false,
			  content: "${webroot}org/viewOrg.html?id="+id,
			  btn: ['<i class="fa fa-remove"></i>关闭'],
			  closeBtn:1
			});	
			layer.full(index);
		}

		<shiro:hasPermission name="/org/editOrg.html">
		//修改机构
		function editOrg(id){
			var layer = layui.layer;
			//iframe层-父子操作
			var index=layer.open({
			  type: 2,
			  title:"修改机构",
			  area: ['650px', '570px'],
			  scrollbar: false,
			  content: ["${webroot}org/editOrg.html?id="+id,"no"]
			});
			layer.full(index);
		}
		</shiro:hasPermission>

		<shiro:hasPermission name="/org/delOrg.do">
		//删除机构
		function delOrg(id,isParent){
			var params=null;
			if(id){
				var msg="确定要删除该机构吗？";
				if(isParent){
					msg="确定要删除该机构及其下级机构吗？";
				}
				layer.confirm(msg, {icon: 3, title:'系统提示',
					  btn: ['确定','取消'] //按钮
					}, function(){
						var url="${webroot}org/delOrg.do";
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
		
		function getAreas(){
			var url="${webroot}area/loadArea.do";
			var params={"id":1};
			postData(url, params, true, areaTreeResult);
		}
		
		/**
		**机构列表回调函数
		*/
		function orgShowResult(data){
			var laytpl = layui.laytpl;
			var layer = layui.layer;
			if(data.success){
				var list=data.list;
				if(list && list.length > 0){
					$("#orgList").treetable({
		    			expandable: true,
					    nodeIdAttr:"id",
					    parentIdAttr:"parentId",
					    clickableNodeNames:true
					},true);
					var rootId=list[0].id;
		    		for (var i = 0; i < list.length; i++) {
			    		var node = list[i];
			    		var parentNodeID=node.pId;
			    		if(node) {
			    			var parentNode = $("#orgList").treetable("node",parentNodeID);
	                       	var row ='<tr data-id="' + 
	                            node.id + 
	                            '" data-parent-id="' +
	                            parentNodeID + '" ';
	                        if(node.isParent) {
	                            row += ' data-tt-branch="true" ';
	                        }
	                        row += ' >';
	                        row += "<td><span controller='true'>" + node.name+ "</span></td>"+"<td>" + node.typeName + "</td>";
	                        row += '<td> ';
	                        row += '<div class="btn-group btn-group-sm btn-group-xs">'; 
	                        row += '  <shiro:hasPermission name="/org/viewOrg.html">';
	                        row += '	<a class="btn btn-secondary btn-icon" style="margin-right:5px;" title="查看" onclick="viewOrg('+node.id +')"> <i class="fa-search">查看</i> </a>'; 
	                       	row += '  </shiro:hasPermission>';
	                        row += '  <shiro:hasPermission name="/org/editOrg.html">';
	                        row += '    <a class="btn btn-info btn-icon" style="margin-right:5px;" title="编辑" onclick="editOrg('+node.id +')"> <i class="fa fa-pencil-square-o">编辑</i> </a> ';
	                        row += '  </shiro:hasPermission>';
	                        if(node.id==1){
	                        	row += '<shiro:hasPermission name="/org/delOrg.do">';
	                        	row += '	<a class="btn btn-danger btn-icon " style="margin-right:5px;" disabled> <i class="fa fa-trash-o">删除</i> </a>';
	                        	row += '</shiro:hasPermission>';
	                        }else{
	                        	row += '<shiro:hasPermission name="/org/delOrg.do">';
	                        	row += '  <a class="btn btn-danger btn-icon" style="margin-right:5px;" title="删除" onclick="delOrg('+node.id +','+node.parent+')"> <i class="fa fa-trash-o">删除</i> </a>'; 
	                        	row += '</shiro:hasPermission>';
	                        }
	                        	row += '<shiro:hasPermission name="/org/addOrg.html">';
	                        	row += '	<a class="btn btn-success btn-icon" style="margin-right:5px;" title="新增下级区域" onclick="addOrg('+node.id +')"> <i class="fa fa-plus">新增下级机构</i> </a> ';
	                        	row += '</shiro:hasPermission>';
	                        	row += '<shiro:hasPermission name="/org/orgUser.html">';
	                        	row += '<a class="btn btn-success btn-icon"   title="用户管理" style="margin-right:5px;" onclick="setupUser('+node.id +')"><i class="fa fa-cog">用户配置</i></a>';
	                        	row += '</shiro:hasPermission>';
	                        row += '</div>'; 
	                        row += '</td>';
	                   		row +='</tr>';
	                   		$("#orgList").treetable("loadBranch", parentNode, row);
		                }
			        }				
		    		$("#messageDiv").hide();
		    		$("#orgList").treetable("collapseAll");
				}else{
					$("#pageData").html("");
					$("#messageDiv").show();
				}
			}else{
				var message = data.message;
				$("#pageData").html("");
				layer.msg(message);
			}
			layer.close(index);  
		}
		
		function closeFrame(){
			var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
			parent.layer.close(index);
		}
		
		<shiro:hasPermission name="/org/orgUser.html">
		//用户配置
		function setupUser(id){
			var layer = layui.layer;
			var index=layer.open({
			  type: 2,
			  title:"分配用户",
			  skin: 'view-class',
			  area: ['900px', '710px'],
			  scrollbar: true,
			  content: "${webroot}org/orgUsers.html?oId="+id,
			  btn: ['<i class="fa fa-remove"></i>关闭'],
			  closeBtn:1
			});
			layer.full(index);
		}
		</shiro:hasPermission>
		
		//区域信息列表回调函数
		function areaTreeResult(data){
			console.log(data);
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
			$("#selectName").val("");
			$("#selectType").val("1");
			var areaId=treeNode.id;			
			if(areaId){
				$("#pageData").html("");
				getOrgs(areaId);
			}
		}
		
		
		function refresh(){
			$("#selectName").val("");
			$("#selectType").val("1");
			$("#pageData").html("");
			$("#tree").html("");
			getOrgs();
			getAreas();
		}
		
	</script>
</body>
</html>