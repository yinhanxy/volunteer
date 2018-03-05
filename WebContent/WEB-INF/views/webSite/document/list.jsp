<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>  
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
	<link href="${sr}css/iCheck/blue.css?v=1.0.2" rel="stylesheet">
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
        <div class="main-content" style="height: 100%;">
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
		                        <a  href="${webroot}document/list.html"><strong>文档管理</strong></a>
		                    </li>
		                </ol>
		            </div>
		        </div>
		        <div class="table-panel panel">
		        	
					<div class="pull-left" style="padding-top: 8px;">
						
						<a  class="btn btn-info btn-sm" onclick="refresh()">
						    <i class="fa fa-refresh">刷新</i><span></span>
						</a>
						<shiro:hasPermission name="/document/addDocument.html">
						<a class="btn btn-success btn-sm" onclick="addDocument()">
							<i class="fa fa-plus">新增</i>
						</a>
						</shiro:hasPermission>
						
						<shiro:hasPermission name="/document/commitDocument.do">
						<a class="btn btn-info btn-sm" onclick="commitCheckDocument()">
							<i class="fa  fa-check">提交</i>
						</a>
						</shiro:hasPermission>
						
						<shiro:hasPermission name="/document/pubDocument.do">
						<a class="btn btn-info btn-sm" onclick="pubCheckDocument()">
							<i class="fa fa-upload">发布</i>
						</a>
						</shiro:hasPermission>
						
						<shiro:hasPermission name="/document/deleteDocument.do">
						<a class="btn btn-danger btn-sm" onclick="deleteCheckDocument()">
							<i class="fa fa-trash-o">删除</i>
						</a>
						</shiro:hasPermission>
						<shiro:hasPermission name="/document/recycleList.html">
						<a class="btn  btn-danger btn-sm" id="recycle" onclick="documentRecycle()" style="display:none;">
							    <i class="fa fa-recycle">回收站</i>
						</a>
						</shiro:hasPermission>
					</div>
					<form class="form-inline" id="searchForm" action="${webroot}document/list.do" style="margin-top: 8px;float: right;" method="post" onsubmit="return false">
						<input type="hidden" id="orgId" name="orgId" >
						<span><label>文档标题或发稿人：</label><input type="text" name="title" id="title" class="form-control">
						<label>文档状态：</label>
							<select name="status" id="status" class="form-control">
								<option value="">所有</option>
								<option value="1">新稿</option>
								<option ${selected==true?"selected":""} value="2">已提交</option>
								<option value="10">已发</option>
								<option value="3">已否</option>
							</select>
							<a class="btn btn-success btn-sm" id="searchBtn" style="float: none;margin-bottom: 2px;">
						        <i class="fa fa-search">查询</i>
						    </a>
						</span>  
						<input type="hidden" name="page" id="currentPageInput" value="1"/> 
           				<input type="hidden" name="rows" id="pageSizeInput" value="10"/>
           				<input type="hidden" name="channelId" id="channelId">
           				<input type="hidden" name="siteId" id="siteId">
					</form> 
					<div style="clear:both;"></div> 
					<div class="row">
		            <div class="col-sm-1" id="channelTree" style="border:1px solid #eee;margin-left:10px;min-height: 480px;
		            float:left;overflow-x:auto;overflow-y:auto;padding-left: 0px;padding-right:0px;">
			         	<div id="tree" class="ztree">
						</div>
					</div>
		            <div class="dataTables col-sm-10" style="padding: 0px;margin-top: 0px;position: relative;">
		                <div class="dataTables_wrapper form-inline dt-bootstrap no-footer">
		                    <table role="grid" id="documentList"  aria-describedby="example-2_info"
		                           class="table table-bordered table-hover  table-striped" style="margin: 0px;border-left: none; ">
		                        <thead>
		                        <tr role="row">
		                        	<th style="text-align: center;width: 40px;"><input id="allcheck" type="checkbox" class="ace"></th>
		                            <th style="text-align: center;width: 60px">编号</th>
		                            <th style="text-align: center;width: 30%">文档标题</th>
		                            <th style="text-align: center;width: 100px">创建时间</th>
		                            <th style="text-align: center;width: 10%">发稿人</th>
		                            <th style="text-align: center;width: 100px;">所在栏目</th>
		                            <th style="text-align: center;width: 60px;">状态</th>
		                            <th style="text-align: center;width: 240px;">操作</th>
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
										<td width="40">
											<input type="checkbox" class="ace" id="UID" value="{{item.id}}">
										</td>
			                            <td>{{(d.pageSize * (d.pageNum-1))+index+1}}</td>
			                            <td>
			                                <div>
			                                   <span><a title='{{item.id}}-{{item.title}}' href='javascript:void(0)' onclick="viewDocument({{item.id}})">{{item.title}}</a></span>
			                                </div>
			                            </td>
			                             <td>
			                                <div>
			                                   <span>{{getFormatDateByLong(item.crTime,"MM-dd hh:mm")}}</span>
			                                </div>
			                            </td>
			                             <td>
			                                <div>
			                                   <span>{{item.crUser}}</span>
			                                </div>
			                            </td>
			                             <td>
			                                <div>
			                                   <span>{{item.channelName}}</span>
			                                </div>
			                            </td>
 										<td>
			                                <div>
			                                   <span>{{item.statusDesc}}</span>
			                                </div>
			                            </td>
										<td style='padding-left:5px;padding-right:5px;'>
											<span class="btn-group btn-group-sm btn-group-xs">
												<shiro:hasPermission name="/document/editDocument.html">
											   		<a class="btn btn-info btn-icon" style="margin-right:5px;" title="修改文档" onclick="editDocument({{item.id}})">
														<i class="fa fa-pencil-square-o">修改</i></a>
												</shiro:hasPermission>
												<shiro:hasPermission name="/document/commitDocument.do">
													<a class="btn btn-info btn-icon" style="margin-right:5px;" title="提交文档" onclick="commitDocument({{item.id}})">
														<i class="fa fa-check">提交</i></a>
                                                </shiro:hasPermission>
												<shiro:hasPermission name="/document/pubDocument.do">							
													<a class="btn btn-info btn-icon" style="margin-right:5px;" title="发布文档" onclick="pubDocument({{item.id}})">
														<i class="fa fa-upload">发布</i></a>
                                                </shiro:hasPermission>
												<shiro:hasPermission name="/document/deleteDocument.do">					
	                        						<a class="btn btn-danger btn-icon" style="margin-right:5px;" title="删除文档" onclick="deleteDocument({{item.id}})">
														<i class="fa fa-trash-o">删除</i> </a>
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
	<script src="${sr}js/iCheck/icheck.min.js?v=1.0.2" type="text/javascript"></script>
	<script src="${sr}js/iCheck/custom.min.js?v=1.0.2" type="text/javascript"></script>
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
		  		postFormData("searchForm",true,searchResult);
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
			$("#allcheck").prop("checked",false);
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
	        		var pageHtml = createPageInfo(page, "searchForm");
	        		$("#pageInfo").html(pageHtml);
	        		$("#pageInfo").show();
	        		$("#messageDiv").hide();
	    		}else{
	    			$("#pageData").html("");
	    			$("#pageInfo").html("");
	    			$("#pageInfo").hide();
	    			$("#messageDiv").show();
	    		}
	    		
	    		$('#documentList input').iCheck({
					  checkboxClass: 'icheckbox_square-blue',
					  radioClass: 'iradio_square-blue',
					  increaseArea: '20%'
				});
				
				var i=0;
				$("#allcheck").on("ifClicked",function () {
					var flag=(i++)%2==0?"check":"uncheck";
					$("#pageData  input").each(function(){
					 	 $(this).iCheck(flag);  
					}); 
			    });
			}else{
				var message = data.message;
				$("#pageData").html("");
				$("#pageInfo").html("");
				$("#pageInfo").hide();
				layer.msg(message);
			}
		}
		
		
		function getSubDocs(){
			postFormData("searchForm",true,searchResult);
		}
		
		<shiro:hasPermission name="/document/addDocument.html">
		//添加文档
		function addDocument(){
			var layer = layui.layer;
			var channelId = $("#channelId").val();
			if(channelId != ""){
				alertAddIFrame(layer,channelId);
			}else{
				layer.open({
					  type: 2,
					  title:"选择栏目",
					  area: ['300px', '450px'],
					  skin: 'edit-class',
					  maxmin: true,
					  scrollbar: true,
					  content: "${webroot}document/selectChannel.html",
					  btn : ['<i class="fa fa-floppy-o"></i>确定', '<i class="fa fa-remove"></i>关闭'], 
					  yes : function(index, layero) {
							var iframeWin = top.window[layero.find('iframe')[0]['name']]; 
							var data=iframeWin.selectChannel();
							var channelId=data.id;
							if(!channelId){
								channelId=0;
							}
							$("#channelId").val(channelId);
							alertAddIFrame(layer,channelId);
					  }
					});
			}
		}
		

		/**
		* 弹出添加文档的窗口,自动全屏
		*/
		function alertAddIFrame(layer,channelId){
			var index = layer.open({
				  type: 2,
				  title:"新建文档",
				  area: ['1000px', '580px'],
				  maxmin: true,
				  scrollbar: false,
				  content: ["${webroot}document/addDocument.html?channelId="+channelId,"no"]
				});
				//layer.full(index);
		}
		</shiro:hasPermission>
		
		<shiro:hasPermission name="/document/viewDocument.html">
		//查看文档
		function viewDocument(id){
			var layer = layui.layer;
			var index = layer.open({
				  type: 2,
				  title:"查看文档",
				  area: ['1000px', '580px'],
				  skin: 'view-class',
				  maxmin: true,
				  scrollbar: false,
				  content: "${webroot}document/viewDocument.html?docId="+id,
				});	
		}
		</shiro:hasPermission>
		
		<shiro:hasPermission name="/document/editDocument.html">
		//编辑文档
		function editDocument(id){
			var layer = layui.layer;
			if(id == null && id == ""){
				layer.msg('请选择要编辑的文档！',{time:1000});
				return;
			}
			var index = layer.open({
				  type: 2,
				  title:"编辑文档",
				  area: ['1000px', '580px'],
				  maxmin: true,
				  scrollbar: false,
				  content: ["${webroot}document/editDocument.html?docId="+id,"no"]
				});	
			
		}
		</shiro:hasPermission>
		
		
		<shiro:hasPermission name="/document/commitDocument.do">
		/**
		* 提交文档
		*/
		function commitDocument(id){
			var layer = layui.layer;
			if(id != null && id != ""){
				layer.confirm('确定要提交选中的文档吗？', {icon: 3, title:'系统提示', btn: ['确定','取消']},
					function(index,layero){
						var url="${webroot}document/commitDocument.do";
						params={"id":id,'status':2};
						postData(url, params, true,commitOrPubResult); 
						layer.close(index);
					}, function(){
						  
					});
			}else{
				layer.msg('请选择要提交的文档！',{time:1000});
				return;
			}
		}
		
		
		/**
		* 批量提交选中的文档
		*/
		function commitCheckDocument(){
			var layer = layui.layer;
			var ids="";
			$("#pageData input:checked").each(function(){ 
				ids+=$(this).val()+","; 
			});
			ids=ids.substring(0,ids.length-1);
			if(!ids){
				layer.msg('请选择要提交的文档！',{time:2000});
				return;
			}
			var idArray = ids.split(",");
			if(ids != null && ids != ""){
				layer.confirm('确定要提交选中的文档吗？', {icon: 3, title:'系统提示',btn: ['确定','取消']}
				, function(index){
						var url="${webroot}document/commitDocument.do";
						params={"id":idArray,'status':2};
						postData(url, params, true,commitOrPubResult); 
						layer.close(index);
					}, function(){
						  
					});
			}
		}
		</shiro:hasPermission>
		
		<shiro:hasPermission name="/document/commitDocument.do">
		/**
		* 发布文档
		*/
		function pubDocument(id){
			var layer = layui.layer;
			if(id != null && id != ""){
				layer.confirm('确定要发布选中的文档吗？', {icon: 3, title:'系统提示', btn: ['确定','取消']},
					function(index,layero){
						var url="${webroot}document/pubDocument.do";
						params={"id":id};
						postData(url, params, true,commitOrPubResult); 
						layer.close(index);
					}, function(){
						  
					});
			}else{
				layer.msg('请选择需要发布的文档！',{time:1000});
				return;
			}
		}
		
		/**
		* 批量发布选中的文档
		*/
		function pubCheckDocument(){
			var layer = layui.layer;
			var ids="";
			$("#pageData input:checked").each(function(){ 
				ids+=$(this).val()+","; 
			});
			ids=ids.substring(0,ids.length-1);
			if(!ids){
				layer.msg('请选择要发布的文档！',{time:2000});
				return;
			}
			var idArray = ids.split(",");
			if(ids != null && ids != ""){
				layer.confirm('确定要发布选中的文档吗？', {icon: 3, title:'系统提示',btn: ['确定','取消']}
				, function(index){
						var url="${webroot}document/pubDocument.do";
						params={"id":idArray};
						postData(url, params, true,commitOrPubResult); 
						layer.close(index);
					}, function(){
						  
					});
			}
		}
		</shiro:hasPermission>
		
		/**
		* 删除文档
		*/
		function deleteDocument(id){
			var layer = layui.layer;
			if(id != null && id != ""){
				layer.confirm('确定要删除选中的文档吗？', {icon: 3, title:'系统提示', btn: ['确定','取消']},
					function(index,layero){
						var url="${webroot}document/deleteDocument.do";
						params={"id":id};
						postData(url, params, true,commitOrPubResult); 
						layer.close(index);
					}, function(){
						  
					});
			}else{
				layer.msg('请选择需要删除的文档！',{time:1000});
				return;
			}
		}
		
		/**
		* 批量删除选中的文档
		*/
		function deleteCheckDocument(){
			var layer = layui.layer;
			var ids="";
			$("#pageData input:checked").each(function(){ 
				ids+=$(this).val()+","; 
			});
			ids=ids.substring(0,ids.length-1);
			if(!ids){
				layer.msg('请选择要删除的文档！',{time:2000});
				return;
			}
			var idArray = ids.split(",");
			if(ids != null && ids != ""){
				layer.confirm('确定要删除选中的文档吗？', {icon: 3, title:'系统提示',btn: ['确定','取消']}
				, function(index){
						var url="${webroot}document/deleteDocument.do";
						params={"id":idArray};
						postData(url, params, true,commitOrPubResult); 
						layer.close(index);
					}, function(){
						  
					});
			}
		}
		
		/**
		* 提交后发布后的回调页面
		*/
		function commitOrPubResult(data){
			var layer = layui.layer;
			var message=data.message;
			if(data && data.success){
				layer.alert(message, {icon: 6},function(index){
					getSubDocs();
					layer.close(index);
				  });
			}else{
				layer.msg(message, {icon: 2,time:1000});
			}
		}
		
		
		
		//文档回收站
		function documentRecycle(){
			var layer = layui.layer;
			var treeObj = $.fn.zTree.getZTreeObj("tree");
			var nodes = treeObj.getSelectedNodes();
			if(!nodes || nodes.length<1){
				layer.msg("正在进行非法操作", {icon: 1,time:1000});
				return;
			}
			var channelId=nodes[0].id;
			var siteId = $("#siteId").val();
			console.log("channelId:"+channelId);
			//iframe层-父子操作
			layer.open({
			  type: 2,
			  title:"文档回收站",
			  area: ['1000px', '600px'],
			  maxmin: true,
			  scrollbar: false,
			  content: ["${webroot}/document/recycleList.html?channelId="+channelId+"&siteId="+siteId,"no"]
			});	
		}
		
		function getChannels(){
			var url="${webroot}document/allSites.do";
			postData(url, null, true, channelTreeResult);
		}
		
		/**
		**文档列表回调函数
		*/
		function channelShowResult(data){
			$(".panel-disabled").hide();
			$("#pageData").html("");
			var laytpl = layui.laytpl;
			var layer = layui.layer;
			if(data.success){
	    		var page = data.page;
	    		if(page.list && page.list.length > 0){
	    			var list = page.list;
	    			var templateHtml = $("#templateData").html();
	        		laytpl(templateHtml).render(page, function(result){
	        		    $("#pageData").html(result);
	        		  });
	        		var pageHtml = createPageInfo(page, "searchForm");
	        		$("#pageInfo").html(pageHtml);
	        		
	        		$("#pageInfo").show();
	        		$("#messageDiv").hide();
	        		
	        		$('input').iCheck({
						  checkboxClass: 'icheckbox_square-blue',
						  radioClass: 'iradio_square-blue',
						  increaseArea: '20%' // optional
					});
					
					var i=0;
					$("#allcheck").on("ifClicked",function () {
						var flag=(i++)%2==0?"check":"uncheck";
						$("#pageData  input").each(function(){
						 	 $(this).iCheck(flag);  
						}); 
				    });
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
								rootPId: -1
							}
						},
						callback: {
							onClick: clickChannel
						},
						view:{
							selectedMulti:false
						}
					}, nodes);
				}
				getSubDocs();
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
				if(data[i].open){
					openValue=true;
				}
				if(data[i].parent){
					parentValue=true;
				}
				var node;
				if(isSite){
					$("#siteId").val(0-idValue);
					node={ id:idValue, pId:pIdValue, name:descValue, open:openValue,isParent:parentValue,iconSkin:"pIcon01"};
				}else{
					node={ id:idValue, pId:pIdValue, name:descValue, open:openValue,isParent:parentValue};
				}
				nodes.push(node);
			}
		}
		
		function clickChannel(event, treeId, treeNode){
			var channelId=treeNode.id;
			$("#channelId").val(channelId);
			if(channelId){
				$("#pageData").html("");
				$("#currentPageInput").val(1);
				getSubDocs();
			}
			$("#recycle").show();
			$("#title").val();
		}
		
		function refresh(){
			$("#pageData").html("");
			$("#tree").html("");
			getChannels();
			var siteId=$("#siteId").val();
			getSubDocs();
			$("#recycle").hide();
		}
	</script>
</body>
</html>