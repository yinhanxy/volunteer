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
    <!-- <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,Chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    
    <title>湖北省文化志愿者管理系统</title> -->
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
		                        <a  href="${webroot}lockuser/list.html"><strong>锁定用户管理</strong></a>
		                    </li>
		                </ol>
		            </div>
		        </div>
		        
		        <div class="table-panel panel">
		        
		            <div class="panel-heading" style="padding-bottom: 0px;">
		           			<div class="btn-toolbar" style="margin-left:0px;">
			           			<form class="form-inline" id="searchForm" action="${webroot}lockuser/list.do">
			           				<span><label>用户名或昵称：</label><input type="text" name="userName" class="form-control"></span>
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
			           			</form>
				             </div>
		            </div>
		            
		            <div class="pull-left" style="padding-top: 8px;">
		                   <shiro:hasPermission name="/lockuser/unlock.do">
			                    <a class="btn btn-info btn-sm" onclick="unlockUser()">
			                    	<i class="fa-unlock-alt">批量解锁</i>
			                    </a>
		                    </shiro:hasPermission>
		              </div>
		                            
		            <div class="dataTables">
		                <div class="dataTables_wrapper form-inline dt-bootstrap no-footer">
		                    <table aria-describedby="example-2_info" role="grid" id="lockUserList"
		                           class="table table-bordered table-hover table-striped dataTable no-footer">
		                        <thead>
		                        <tr role="row">
		                        	<th style="width: 40px;"><input id="allcheck" type="checkbox" class="ace" ></th>
		                            <th style="width: 60px;">序号</th>
		                            <th>用户名</th>
		                            <th style="width: 180px;">最后锁定时间</th>
		                            <th style="width: 180px;">登录次数</th>
		                            <th style="width: 100px;">操作</th>
		                        </tr>
		                        </thead>
		                        <tbody class="middle-align" id="pageData"></tbody>
		                    </table>
		                    <div class="row" style="display: none;" id="pageInfo">
		                    </div>
		                    <div class="panel-disabled">
			                    <div class="loader-1"></div>
			                </div>
		                    <div style="margin-top: -20px; line-height: 50px;border: 1px solid #ddd; border-top: 0;display: none;" id="messageDiv">
		                        <p class="text-center">暂无数据</p>
		                    </div>
		                    <script id="templateData" type="text/html">
									{{#  layui.each(d.list, function(index, item){ }}	
			                        <tr role="row"
			                            data-toggle="collapse">
			                            <td width="40">
											<input type="checkbox" class="ace" value="{{item.userName}}">
										</td>
			                            <td>{{(d.pageSize * (d.pageNum-1))+index+1}}</td>
			                            <td>
			                                <div>
			                                   <span>
													{{item.userName}}
												</span>
			                                </div>
			                            </td>
			                             <td>
			                                <div>
			                                   <span>{{getFormatDateByLong(item.lockDate,"MM-dd hh:mm")}}</span>
			                                </div>
			                            </td>
			                             <td>
			                                <div>
			                                   <span>{{item.retryCount}}</span>
			                                </div>
			                            </td>
										<td>
											<span class="btn-group btn-group-sm btn-group-xs">
												<shiro:hasPermission name="/lockuser/unlock.do">
											   		<a class="btn btn-info btn-icon "  style="margin-right:5px;" title="解锁用户" onclick="unlockUser('{{item.userName}}')">
														<i class="fa-unlock-alt">解锁用户</i></a>
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

<jsp:include page="../../include/footer.jsp"></jsp:include>		

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
		postFormData("searchForm",true,searchResult);
		var layer = layui.layer;
		layer.config({
			  extend: 'myskin/style.css'
			});
	  	//查询按钮
	  	$("#searchBtn").click(function(){
			$("#currentPageInput").val(1);
	  		postFormData("searchForm",true,searchResult);
	  	});
	  	
	  	//清空条件按钮
	  	$("#resetBtn").click(function(){
	  		$("#currentPageInput").val(1);
	  		$("#searchForm [name=userName]").val("");
	  		postFormData("searchForm",true,searchResult);
	  	});
	  	
	  	$("#allcheck").click(function () {   
	  		var flag=this.checked;
	       $("#lockUserList  input:checkbox").each(function(){
	        	 $(this).prop('checked', flag);  
	       });
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
		if(data.success){
    		var page = data.page;
    		if( page && page.list && page.list.length > 0){
    			var list = page.list;
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
    		
    		$('#lockUserList input').iCheck({
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
	
	
	<shiro:hasPermission name="/lockuser/unlock.do">
	//解锁用户
	function unlockUser(userName){
		var layer = layui.layer;
		var userNames="";
		if(userName == null){
			$("#pageData input:checked").each(function(){
				userNames+=$(this).val()+","; 
			}); 
			userNames=userNames.substring(0,userNames.length-1);
		}else{
			userNames = userName;
		}
		if(!userNames){
			layer.msg('请选择要解锁的用户！',{time:1000});
			return false;
		}else{
			var message = "确定要解锁当前的用户吗？";
			if(userName == null){
				message = "确定要解锁选中的用户吗？";
			}
			layer.confirm(message, {icon: 3, title:'系统提示',
			  btn: ['确定','取消'] //按钮
			}, function(){
				var url="${webroot}lockuser/unlock.do";
				params={"userNames":userNames};
				postData(url, params, true, unlockResult); 
			  	
			}, function(){
			  
			});
		}
	}
	</shiro:hasPermission>
	
	/**
	**回调函数
	*/
	function unlockResult(data){
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