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
	
	<link href="${sr}css/font-awesome/css/linecons.css" rel="stylesheet">
	<link href="${sr}css/font-awesome/css/font-awesome.min.css" rel="stylesheet">
    <link href="${sr}css/bootstrap.min.css" rel="stylesheet">
	<link href="${sr}css/xenon.min.css" rel="stylesheet">
	<link href="${sr}css/base.css" rel="stylesheet">
	<link href="${sr}css/new.css" rel="stylesheet">
	<link href="${sr}css/iCheck/blue.css?v=1.0.2" rel="stylesheet">
    <!--[if lt IE 9]>
      <script src="${sr}js/html5shiv.min.js"></script>
      <script src="${sr}js/respond.min.js"></script>
    <![endif]-->
</head>
<body style="background-color: #fff;">
	    <div  style="height: 100%;" style="margin: 0px;padding: 0px;">
		    <div  class="workspace-page">
		        
		        <div class="table-panel panel">
		            <div class="panel-heading" style="padding-bottom: 0px;">
		           			<div class="btn-toolbar" style="margin-left:0px;">
			           			<form class="form-inline" id="searchForm" action="${webroot}document/list.do">
			           				<span><label>文档名称或发稿人：</label><input type="text" name="title" class="form-control"></span>
			           				<span>
			           					<a class="btn btn-success btn-sm" id="searchBtn" style="float: none;margin-bottom: 2px;">
					                        <i class="fa fa-search">查询</i>
					                    </a>
					                    <a class="btn btn-info btn-sm" id="resetBtn" style="float: none;margin-bottom: 2px;">
					                        <i class="fa fa-undo">清除条件</i>
					                    </a>
			           				</span>
			           				<input type="hidden" name="page" id="currentPageInput" />
			           				<input type="hidden" name="rows" id="pageSizeInput" />
			           				<input type="hidden" name="status" value="-10"/>
			           				<input type="hidden" name="channelId" id="channelId" value="${channelId}"/>
			           				<input type="hidden" name="siteId" id="siteId" value="${siteId}"/>
			           			</form>
				             </div>
		            </div>
		            
		             <div class="pull-left" style="padding-top: 8px;">
		             		<shiro:hasPermission name="/document/restoreDocument.do">
		                    <a class="btn btn-info btn-sm" onclick="restoreDocument()">
		                        <i class="fa fa-reply">还原文档</i>
		                    </a>
		                    </shiro:hasPermission>
		                    
		                    <shiro:hasPermission name="/document/removeDocument.do">
		                    <a class="btn btn-danger btn-sm" onclick="deleteDocument()" id="del">
		                    	<i class="fa fa-trash-o">彻底删除</i>
		                    </a>
		                    </shiro:hasPermission>
		              </div>
		                <div class="form-inline">
		                    <table role="grid" id="documentList"  aria-describedby="example-2_info"
		                           class="table table-bordered table-hover  table-striped" style="margin: 0px;border-left: none; ">
		                        <thead>
		                        <tr role="row">
		                        	<th style="text-align: center;width: 40px;"><input id="allcheck" type="checkbox" class="ace"></th>
		                            <th style="text-align: center;width: 60px">编号</th>
		                            <th style="text-align: center;width: 30%">文档标题</th>
		                            <th style="text-align: center;width: 100px">删除时间</th>
		                            <th style="text-align: center;width: 10%">发稿人</th>
		                            <th style="text-align: center;width: 100px;">所在栏目</th>
		                            <th style="text-align: center;width: 140px;">操作</th>
		                        </tr>
		                        </thead>
		                        <tbody class="middle-align" id="pageData"></tbody>
		                    </table>
		                    <div class="row" style="display: none;padding-left: 10px;padding-top: 20px;" id="pageInfo">
		                    </div>
		                    <div class="panel-disabled">
			                    <div class="loader-1"></div>
			                </div>
		                    <div style="line-height: 50px;border: 1px solid #ddd; border-top: 0;display: none;" id="messageDiv">
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
			                                   <span>{{getFormatDateByLong(item.delTime,"MM-dd hh:mm")}}</span>
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
										<td style='padding-left:5px;padding-right:5px;'>
											<span class="btn-group btn-group-sm btn-group-xs">
												<shiro:hasPermission name="/document/restoreDocument.do">	
											   		<a class="btn btn-info btn-icon"  style="margin-right:5px;" title="还原栏目"
                                                                     onclick="restoreDocument({{item.id}})">
														<i class="fa fa-reply">还原</i></a>
												</shiro:hasPermission>
												<shiro:hasPermission name="/document/restoreDocument.do">	
													<a class="btn btn-danger btn-icon"  title="彻底删除栏目" style="margin-right:5px;" 
																onclick="deleteDocument({{item.id}})">
													<i class="fa fa-trash-o">彻底删除</i></a>
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

<script src="${sr}js/jquery.min.js" type="text/javascript"></script>
<script src="${sr}js/main.js" type="text/javascript"></script>
<script src="${sr}layui.js" type="text/javascript"></script>
<script src="${sr}js/config.js" type="text/javascript"></script>
<script src="${sr}js/baseutil.js" type="text/javascript"></script>
<script src="${sr}js/iCheck/icheck.min.js?v=1.0.2" type="text/javascript"></script>
<script src="${sr}js/iCheck/custom.min.js?v=1.0.2" type="text/javascript"></script>
<script type="text/javascript">
	layui.use(['jquery','layer','laytpl'], function(){
		initMenu();
		var layer = layui.layer;
		layer.config({
			  extend: 'myskin/style.css'
			});
		$("#documentList").on("ifChanged",":checkbox", function(){
			var n=$(":checked[name='RID']");
			if(n.length){
				$("#del").addClass("disabled");
			}else{
				$("#del").removeClass("disabled");
			}
		});
		
		postFormData("searchForm",true,searchResult);
	  	//查询按钮
	  	$("#searchBtn").click(function(){
			$("#currentPageInput").val(1);
	  		postFormData("searchForm",true,searchResult);
	  	});
	  	
	  	//清空条件按钮
	  	$("#resetBtn").click(function(){
	  		$("#currentPageInput").val(1);
	  		$("#searchForm [name=channelName]").val("");
	  		postFormData("searchForm",true,searchResult);
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
		$("#allcheck").prop("checked",false);
		var laytpl = layui.laytpl;
		var layer = layui.layer;
		console.log(data);
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
	
	
	//彻底删除文档
	function deleteDocument(id){
		var layer = layui.layer;
		var ids="";
		if(!id){
			$("#pageData input:checked").each(function(){ 
				ids+=$(this).val()+","; 
			}); 
			ids=ids.substring(0,ids.length-1);
			if(!ids){
				layer.msg('请选择要彻底删除的文档！',{time:1000});
				return false;
			}
		}else{
			ids=id;
		}
		layer.confirm('确定要彻底删除选中的文档吗？删除后将不可恢复', {icon: 3, title:'系统提示',
		  btn: ['确定','取消'] //按钮
		}, function(){
			var url="${webroot}document/removeDocument.do";
			ids = new String(ids);
			params={"id":ids.split(",")};
			postData(url, params, true, delDocumentResult); 
		  	
		});
	}
	
	//还原文档
	function restoreDocument(id){
		var layer = layui.layer;
		var ids="";
		if(!id){
			$("#pageData input:checked").each(function(){ 
				ids+=$(this).val()+","; 
			}); 
			ids=ids.substring(0,ids.length-1);
			if(!ids){
				layer.msg('请选择要还原的文档！',{time:1000});
				return false;
			}
		}else{
			ids=id;
		}
		layer.confirm('确定要还原选中的文档吗？', {icon: 3, title:'系统提示',
		  btn: ['确定','取消'] //按钮
		}, function(){
			var url="${webroot}document/restoreDocument.do";
			ids = new String(ids);
			params={"id":ids.split(",")};
			postData(url, params, true, restoreDocumentResult); 
		  	
		});
	}
	
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
			  closeBtn:1
			});	
	}
	
	function restoreDocumentResult(data){
		var laytpl = layui.laytpl;
		var layer = layui.layer;
		var message=data.message;
		if(data && data.success){
			layer.msg(message, {icon: 1,time:1000});
			loadPage();
			parent.getSubDocs();
		}else{
			layer.msg(message, {icon: 2,time:1000});
		}
	}
	
	function delDocumentResult(data){
		var laytpl = layui.laytpl;
		var layer = layui.layer;
		var message=data.message;
		if(data && data.success){
			layer.msg(message, {icon: 1,time:1000});
			loadPage();
		}else{
			layer.msg(message, {icon: 2,time:1000});
		}
	}
	
	function loadPage(){
		postFormData("searchForm",true,searchResult);
	}
</script>
</body>
</html>