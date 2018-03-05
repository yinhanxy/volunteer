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
	<link href="${sr}ztree/zTreeStyle/style.css" rel="stylesheet">
	<style type="text/css">
		@media screen and (min-width: 1600px) {
			#channelTree {width: 200px} 
		} 
		@media screen and (max-width: 1500px) { 
			#channelTree {width: 140px}
		}
		.ztree li span.button.pIcon01_ico_open{margin-right:2px; background: url(${sr}css/11.png) no-repeat scroll 0 0 transparent; vertical-align:top; *vertical-align:middle}
		.ztree li span.button.pIcon01_ico_close{margin-right:2px; background: url(${sr}css/11.png) no-repeat scroll 0 0 transparent; vertical-align:top; *vertical-align:middle}
		.ztree li span.button.ico_docu {
		    margin-right: 2px;
		    background-position: -110px -16px;
		    vertical-align: top;
		}
	</style>
</head>
<body class="page-body skin-facebook">
	<div class="page-container">
		<jsp:include page="../../include/top.jsp"></jsp:include>
        <!--  -->
		<jsp:include page="../../include/left.jsp"></jsp:include>
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
		                        <a>信息发布</a>
		                    </li>
		                    <li class="active">
		                        <a  href="${webroot}channel/list.html"><strong>栏目管理</strong></a>
		                    </li>
		                </ol>
		            </div>
		        </div>
		        <div class="table-panel panel">
		        	
					<div class="pull-left" style="padding-top: 8px;">
						
						<a  class="btn btn-info btn-sm" onclick="refresh()">
						    <i class="fa fa-refresh">刷新</i><span></span>
						</a>
						<shiro:hasPermission name="/channel/addChannel.html">
							<a class="btn btn-success btn-sm" onclick="addChannel()">
								    <i class="fa fa-plus">新增</i>
							</a>
						</shiro:hasPermission>
						<shiro:hasPermission name="/channel/recycleList.html">
							<a class="btn btn-success btn-sm" id="recycle" onclick="channelRecycle()" style="display:none">
								    <i class="fa fa-recycle">回收站</i>
							</a>
						</shiro:hasPermission>
					</div>
					<form name="searchChannelForm" class="form-inline" id="searchChannelForm" action="${webroot}channel/channelSelect.do" style="margin-top: 8px;float: right;margin-right: 10px;" method="post" onsubmit="return false">
						<input type="hidden" id="orgId" name="orgId" >
						<span><label>栏目名称或描述：</label><input type="text" name="channelCondition" id="channelCondition" class="form-control"></span>
						<span>
							<a class="btn btn-success btn-sm" id="searchBtn" style="float: none;margin-bottom: 2px;">
						        <i class="fa fa-search">查询</i>
						    </a>
						</span>  
						<input type="hidden" name="page" id="currentPageInput" value="1"/> 
           				<input type="hidden" name="rows" id="pageSizeInput"/>
           				<!-- <input type="hidden" name="channelId" id="channelId"> -->
           				<input type="hidden" name="parentId" id="parentId">
           				<input type="hidden" name="siteId" id="siteId">
					</form> 
					<div style="clear:both;"></div> 
					<div class="row">
		            <div class="col-sm-1" id="channelTree" style="border:1px solid #eee;margin-left:15px;height: 480px;float:left;overflow-x:auto;overflow-y:auto;padding-left: 0px;">
			         	<div id="tree" class="ztree">
							
						</div>
					</div>
		            <div class="dataTables col-sm-10" style="padding: 0px;margin-top: 0px;position: relative;">
		                <div class="dataTables_wrapper form-inline dt-bootstrap no-footer">
		                    <table  role="grid" id="orgList" 
		                           class="table table-bordered table-hover  table-striped" style="margin: 0px;border-left: none; ">
		                        <thead>
		                        <tr role="row">
		                            <th >唯一标识</th>
		                            <th >显示名称</th>
		                            <th >创建用户</th>
		                            <th style="width:160px">创建时间</th>
		                            <th style="width:280px">操作</th>
		                        </tr>
		                        </thead>
		                        <tbody class="middle-align" id="pageData">
		                        	
		                        </tbody>
		                    </table>
		                    <div class="row" style="display: none;padding-left: 10px;padding-top: 20px;" id="pageInfo">
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
			                                <div >
			                                   <span>{{item.chnlName}}</span>
			                                </div>
			                            </td>
			                             <td >
			                                <div>
			                                   <span>{{item.chnlDesc }}</span>
			                                </div>
			                            </td>
			                             <td >
			                                <div>
			                                   <span>{{item.crUser }}</span>
			                                </div>
			                            </td>
			                             <td >
			                                <div>
			                                   <span>{{getFormatDateByLong(item.crTime,"yyyy-MM-dd hh:mm:ss")}}</span>
			                                </div>
			                            </td>
										<td>
											<span class="btn-group btn-group-sm btn-group-xs">
													<shiro:hasPermission name="/channel/addChannel.html">
	                        	                  	<a class="btn btn-success btn-icon" style="margin-right:5px;" title="新增子栏目" onclick="addChannel({{item.id}})"> <i class="fa fa-plus">新增子栏目</i> </a>
													</shiro:hasPermission>
													<shiro:hasPermission name="/channel/editChannel.html">
											   		<a class="btn btn-info btn-icon"  style="margin-right:5px;" title="修改栏目" onclick="editChannel({{item.id}})">
														<i class="fa fa-pencil-square-o">修改</i>
													</a>
													</shiro:hasPermission>
													<shiro:hasPermission name="/channel/channelRecycle.do">
	                        						<a class="btn btn-danger btn-icon" style="margin-right:5px;" title="删除栏目" onclick="moveRecycle({{item.id}})"> <i class="fa fa-trash-o">删除</i> </a>
													</shiro:hasPermission>
													<shiro:hasPermission name="/channel/selectMoveChannel.html">
													<a class="btn btn-danger btn-icon"   title="移动栏目" style="margin-right:5px;" onclick="moveChannel({{item.id}},'{{item.chnlName}}')">
														<i class="fa fa-cog">移动</i>
													</a>
													</shiro:hasPermission>
                                            </span>
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
	<jsp:include page="../../include/footer.jsp"></jsp:include>		

	<script src="${sr}js/jquery.min.js" type="text/javascript"></script>
	<script src="${sr}js/main.js" type="text/javascript"></script>
	<script src="${sr}layui.js" type="text/javascript"></script>
	<script src="${sr}js/config.js" type="text/javascript"></script>
	<script src="${sr}js/baseutil.js" type="text/javascript"></script>
	<script src="${sr}js/jquery.treetable.js" type="text/javascript"></script>
	<script src="${sr}ztree/jquery.ztree.core.js" type="text/javascript"></script>
	<script type="text/javascript">
		layui.use(['jquery','layer','laytpl'], function(){
			initMenu();
			var layer = layui.layer;
			layer.config({
				  extend: 'myskin/style.css'
				});
			getChannels();
			$(".panel-disabled").hide();
			
			//查询按钮
		  	$("#searchBtn").click(function(){
				$("#currentPageInput").val(1);
		  		postFormData("searchChannelForm",true,searchResult);
		  	});
		});
		
		/**
		*翻页
		*/
		function setCurrentPage(currentPage,searchFormId){
			$("#currentPageInput").val(currentPage);
			postFormData(searchFormId,true,searchResult);
		}
		
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
	        		var pageHtml = createPageInfo(page, "searchChannelForm");
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
				layer.msg(message);
			}
		}
		
		function getSubChannels(){
			postFormData("searchChannelForm",true,channelShowResult);
		}
		
		<shiro:hasPermission name="/channel/addChannel.html">
		//添加栏目
		function addChannel(id){
			var layer = layui.layer;
			//iframe层-父子操作
			var index=layer.open({
			  type: 2,
			  title:"新建栏目",
			  area: ['600px', '400px'],
			  scrollbar: false,
			  content: ["${webroot}channel/addChannel.html?channelId="+id,"no"]
			});
			layer.full(index);
		}
		</shiro:hasPermission>
		
		<shiro:hasPermission name="/channel/selectMoveChannel.html">
		//移动栏目
		function moveChannel(id,chnlName){
			var layer = layui.layer;
			layer.open({
			  type: 2,
			  title:"移动栏目("+chnlName+")",
			  skin: 'v-page-class',
			  area: ['300px', '450px'],
			  maxmin: true,
			  scrollbar: true,
			  content: "${webroot}channel/selectMoveChannel.html?channelId="+id,
			  btn: ['<i class="fa fa-remove"></i>确定','<i class="fa fa-remove"></i>关闭'],
				yes : function(index, layero) {
					var iframeWin = top.window[layero.find('iframe')[0]['name']]; 
					var result=iframeWin.commit();
					if(result)
						refresh();
				},
				btn2 : function(index, layero) {
					var iframeWin = top.window[layero.find('iframe')[0]['name']]; 
					iframeWin.closeFrame();
				}
			});	
		}
		</shiro:hasPermission>

		<shiro:hasPermission name="/channel/editChannel.html">
		//修改栏目
		function editChannel(id){
			var layer = layui.layer;
			//iframe层-父子操作
			layer.open({
			  type: 2,
			  title:"修改栏目",
			  area: ['600px', '400px'],
			  maxmin: true,
			  scrollbar: false,
			  content: ["${webroot}channel/editChannel.html?channelId="+id,"no"]
			});	
		}
		</shiro:hasPermission>
		
		<shiro:hasPermission name="/channel/recycleList.html">
		//栏目回收站
		function channelRecycle(){
			var layer = layui.layer;
			var treeObj = $.fn.zTree.getZTreeObj("tree");
			var nodes = treeObj.getSelectedNodes();
			if(!nodes || nodes.length<1){
				layer.msg("正在进行非法操作", {icon: 1,time:1000});
				return;
			}
			var channelId=nodes[0].id;
			if(nodes[0].pId==-1){
				channelId=0;
			}
			//iframe层-父子操作
			var index=layer.open({
			  type: 2,
			  title:"栏目回收站",
			  skin: 'view-class',
			  area: ['800px'],
			  scrollbar: false,
			  content: ["${webroot}channel/recycleList.html?channelId="+channelId,"no"],
			  btn: ['<i class="fa fa-remove"></i>关闭'],
			  closeBtn:1
			});
			layer.full(index);
		}
		</shiro:hasPermission>

		<shiro:hasPermission name="/channel/channelRecycle.do">
		//删除栏目到回收站中
		function moveRecycle(id){
			var params=null;
			if(id){
				var msg="确定要将该栏目及其子栏目放入回收站中吗？";
				layer.confirm(msg, {icon: 3, title:'系统提示',
					  btn: ['确定','取消'] //按钮
					}, function(){
						var url="${webroot}channel/channelRecycle.do";
						params={"channelId":id};
						postData(url, params, true, function(data){
							var laytpl = layui.laytpl;
							var layer = layui.layer;
							var message=data.message;
							if(data && data.success){
								layer.msg(message, {icon: 1,time:1000});
								delRefresh(id);
							}else{
								layer.msg(message, {icon: 2,time:1000});
							}
						}); 
					  	
					});
			}
		}
		</shiro:hasPermission>
		
		function getChannels(){
/* 			var url="${webroot}channel/sites.do"; */
			var url="${webroot}channel/allSites.do";
			postData(url, null, true, channelTreeResult);
		}
		
		/**
		**栏目列表回调函数
		*/
		function channelShowResult(data){
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
	        		var pageHtml = createPageInfo(page, "searchChannelForm");
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
				layer.msg(message);
			}
			
		}
		
		function closeFrame(){
			var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
			parent.layer.close(index);
		}
		
		//站点栏目列表回调函数
		function channelTreeResult(data){
			console.log("====");
 			console.log(data);
 			if(data && data.success){
				var channels=data.channels;
				if(channels&& channels.length>0){
					var nodes=[];
					build(channels,nodes);
					$.fn.zTree.init($("#tree"),{
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
							onClick: clickChannel
						},
						view:{
							selectedMulti:false
						},
					}, nodes);
				}
				getSubChannels();
			}
		}
		
		function build(data,nodes){
			for (var i = 0; i < data.length; i++) {
				var idValue=data[i].id;
				var pIdValue=data[i].pId;
				var isSite=data[i].site;
				var descValue=data[i].desc;
				var openValue=false;
				var parentValue=false;
				var sId=data[i].siteId;
				if(data[i].open){
					openValue=true;
				}
				if(data[i].parent){
					parentValue=true;
				}
				var node;
				
				if(isSite){
					$("#siteId").val(0-idValue);
					$("#parentId").val(idValue);
					sId=idValue;
					node={ id:idValue, pId:pIdValue, siteId:sId,name:descValue, open:openValue,isParent:parentValue,iconSkin:"pIcon01"};
				}else{
					node={ id:idValue, pId:pIdValue,siteId:sId,name:descValue, open:openValue,isParent:parentValue};
				}
				nodes.push(node);
			}
		}
		function clickChannel(event, treeId, treeNode){
			var channelId=treeNode.id;
			var pId=treeNode.pId;
			$("#parentId").val(channelId);
			$("#pageData").html("");
			$("#currentPageInput").val(1);
			getSubChannels();
			
			$("#recycle").show();
			$("#channelCondition").val();
		}
		
		function addRefresh(parentId,newNode,order){
			var treeObj = $.fn.zTree.getZTreeObj("tree");
			if(parentId==0){
				parentId="-"+$("#siteId").val();
			}
			var parentNodes = treeObj.getNodesByParam("id", parentId, null);
			console.log(parentNodes);
			if(parentNodes && parentNodes.length>0){
				/* if(parentNodes[0].open){ */
					treeObj.addNodes(parentNodes[0],order-1,newNode,true);
				/* }else{
					treeObj.expandNode(parentNodes[0], true, false, true);
				} */
			}
			$("#pageData").html("");
			
			getSubChannels();
		}
		
		function delRefresh(channelId){
			$("#pageData").html("");
			getSubChannels();
			var treeObj = $.fn.zTree.getZTreeObj("tree");
			var delNodes = treeObj.getNodesByParam("id", channelId, null);
			if(delNodes && delNodes.length>0){
				treeObj.removeNode(delNodes[0], false);
			}
		}
		
		function editRefresh(parentId,newNode,order){
			if(parentId==0){
				parentId="-"+$("#siteId").val();
			}
			var id=newNode.id;
			var treeObj = $.fn.zTree.getZTreeObj("tree");
			var editNodes = treeObj.getNodesByParam("id", id, null);
			editNodes[0].name=newNode.name;
			console.log(editNodes);
			delRefresh(id);
			var parentNodes = treeObj.getNodesByParam("id", parentId, null);
			if(parentNodes && parentNodes.length>0){
				treeObj.addNodes(parentNodes[0],order-1,editNodes);
			}
			$("#pageData").html("");
			getSubChannels();
		}
		
		
		function refresh(){
			$("#pageData").html("");
			$("#tree").html("");
			getChannels();
			var siteId=$("#siteId").val();
			$("#parentId").val(siteId);
/* 			getSubChannels();
 */			$("#recycle").hide();
		}
	</script>
</body>
</html>