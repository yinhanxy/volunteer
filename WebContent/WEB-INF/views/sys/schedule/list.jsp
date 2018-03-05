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
	<style type="text/css">
		select,option,optgroup {font-size:12px;}
		select:focus,option:checked{
		 	cursor: pointer;
		}
		
	</style>
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
		                        <a  href="${webroot}schedule/list.html"><strong>定时任务</strong></a>
		                    </li>
		                </ol>
		            </div>
		        </div>
		        
		        <div class="table-panel panel">
		        
		            <div class="panel-heading" style="padding-bottom: 0px;">
			           			<form class="form-inline" id="searchForm" action="${webroot}schedule/list.do" onkeydown="if(event.keyCode==13){return false;}">
		           			<div class="btn-toolbar" style="margin-left:0px;">
			           				<span><label>任务名称：</label><input type="text" style="min-width:160px;" name="jobName" id="jobName" class="form-control"></span>
			           				<span>
			           					<a class="btn btn-success btn-sm" id="searchBtn" style="float: none;margin-bottom: 2px;">
					                        <i class="fa fa-search"></i>查询
					                    </a>
					                    <a class="btn btn-info btn-sm" id="resetBtn" style="float: none;margin-bottom: 2px;">
					                        <i class="fa fa-undo"></i>清除条件
					                    </a>
			           				</span>
			           				<input type="hidden" name="pageNo" id="pageNo" />
			           				<input type="hidden" name="pageSize" id="pageSize" />
				             </div>
			           	</form>
		            </div>
		            
		           	  <div class="pull-left tableTools-container" style="padding-top: 8px;" id="optional">
							<shiro:hasPermission name="/schedule/add.html"><a class="btn btn-success  btn-sm" onclick="addJob()" ><i class="fa fa-plus"></i>新增</a></shiro:hasPermission>
							<shiro:hasPermission name="/schedule/delete.do"><a class="btn btn-danger  btn-sm" onclick="deleteJobs()" ><i class="fa fa-trash"></i>删除</a></shiro:hasPermission>	
		             	 	<shiro:hasPermission name="/schedule/start.do"><a class="btn btn-success  btn-sm" onclick="startJobs()"><i class="fa fa-play"></i>立即执行</a></shiro:hasPermission>
		              </div>
		                            
		          <div class="dataTables">
		                <div class="dataTables_wrapper form-inline dt-bootstrap no-footer">
		                    <table aria-describedby="example-2_info" role="grid" id="roleList"
		                           class="table table-bordered table-hover table-striped dataTable no-footer">
		                        <thead>
		                        <tr role="row">
		                        	<th style="width:40px;text-align:center;"><input id="allcheck" type="checkbox" class="ace" ></th>
		                            <th style="width:60px;text-align:center;">序号</th>
		                            <th style="text-align:center;">任务名称</th>
		                            <th style="width:60px;text-align:center;">任务状态</th>
		                            <th style="width:160px;text-align:center;">下次运行时间</th>
		                            <th style="width:380px;text-align:center;">操作</th>
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
			                            <td width="30">
											<input type="checkbox" class="ace" value="{{item.jobId}}">
										</td>
			                            <td style="text-align:center;">{{(d.pageSize * (d.pageNum-1))+index+1}}</td>
			                            <td>
											<span>{{item.jobName}}</span>
										</td>
			                            <td>
			                                <div>
			                                    <span>{{item.statusName}}</span>
			                                </div>
			                            </td>
			                             <td>
			                                <div>
			                                  <span>{{item.nextFireTime}}</span>
			                                </div>
			                            </td>
			                            
										<td>
											<div class="btn-group btn-group-sm btn-group-xs">
												<shiro:hasPermission name="/schedule/detail.html">
			                                  		<a class="btn btn-secondary btn-icon " style="margin-right:4px;" title="查看" onclick="viewJob({{item.jobId}})" >
														<i class="fa-search"></i>查看</a>
											   	</shiro:hasPermission>
												<shiro:hasPermission name="/schedule/edit.html">
											   		<a class="btn btn-info btn-icon "  style="margin-right:4px;" title="编辑" onclick="editJob({{item.jobId}})">
														<i class="fa fa-pencil-square-o"></i>编辑</a>
												</shiro:hasPermission>
												<shiro:hasPermission name="/schedule/delete.do">
											   		<a class="btn btn-danger btn-icon "  style="margin-right:4px;" title="删除" onclick="deleteJob({{item.jobId}})">
														<i class="fa fa-trash-o"></i>删除</a>
												</shiro:hasPermission>
                                                 <shiro:hasPermission name="/schedule/start.do">
											   		<a class="btn btn-success btn-icon "  style="margin-right:4px;" title="立即执行"  onclick="startJob({{item.jobId}})">
														<i class="fa fa-play"></i>立即执行</a>
												</shiro:hasPermission>

                                                <shiro:hasPermission name="/schedule/pause.do">
											   		{{#  if(item.status === 0||item.status === 2){ }}
														<a class="btn btn-success btn-icon "  style="margin-right:4px;" title="暂停任务" onclick="pauseJob({{item.jobId}})">
														<i class="fa fa-pause"></i>暂停</a>
													{{#  } }} 
												</shiro:hasPermission>
												<shiro:hasPermission name="/schedule/resume.do">
{{#  if(item.status === 1){ }}
											   		<a class="btn btn-success btn-icon "  style="margin-right:4px;" title="恢复" onclick="resumeJob({{item.jobId}})">
														<i class="fa fa-unlock"></i>恢复</a>
	{{#  } }} 
												</shiro:hasPermission>
												<shiro:hasPermission name="/schedule/logs.do">
											   		<a class="btn btn-success btn-icon "  style="margin-right:4px;" title="运行日志" onclick="jobLogs({{item.jobId}})">
														<i class="fa fa-history"></i>日志</a>
												</shiro:hasPermission>
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

<jsp:include page="../../include/footer.jsp"></jsp:include>		

<script src="${sr}js/jquery.min.js" type="text/javascript"></script>
<script src="${sr}js/main.js" type="text/javascript"></script>
<script src="${sr}layui.js" type="text/javascript"></script>
<script src="${sr}js/config.js" type="text/javascript"></script>
<script src="${sr}js/baseutil.js" type="text/javascript"></script>
<script src="${sr}js/iCheck/icheck.min.js?v=1.0.2" type="text/javascript"></script>
<script src="${sr}js/iCheck/custom.min.js?v=1.0.2" type="text/javascript"></script>


<script type="text/javascript">
	var layIndex;
	layui.use(['jquery','layer','laytpl','laydate'], function(){
		var layer = layui.layer;
		layIndex=layer.load(0, {shade: false});
		initMenu();
		postFormData("searchForm",true,searchResult,searchResultError);
		layer.config({
			  extend: 'myskin/style.css'
		});
	  	//查询按钮
	  	$("#searchBtn").click(function(){
	  		$(".panel-disabled").show();
			$("#pageNo").val(1);
			layIndex=layer.load(0, {shade: false});
	  		postFormData("searchForm",true,searchResult,searchResultError);
	  	});
	  	//清空条件按钮
	  	$("#resetBtn").click(function(){
	  		$("#jobName").val('');
	  		$("#pageNo").val(1);
	  		$(".panel-disabled").show();
	  		layIndex=layer.load(0, {shade: false});
	  		postFormData("searchForm",true,searchResult,searchResultError);
	  	});
	  	
	});
	
	function addJob(){
		var layer = layui.layer;
		layer.open({
		  type: 2,
		  title:"新建任务",
		  area: ['770px', '480px'],
		  maxmin: true,
		  scrollbar: false,
		  content: "${webroot}schedule/add.html"
		});
	}
	
	function editJob(jobId){
		var layer = layui.layer;
		layer.open({
		  type: 2,
		  title:"编辑任务",
		  area: ['770px', '480px'],
		  maxmin: true,
		  scrollbar: false,
		  content: "${webroot}schedule/edit.html?jobId="+jobId
		});
	}
	


	//查看详情
	function viewJob(id){
		var layer = layui.layer;
		//iframe层-父子操作
		layer.open({
		  type: 2,
		  title:"查看任务详情",
		  area: ['770px', '550px'],
		  maxmin: false,
		  scrollbar: false,
		  content: "${webroot}schedule/view.html?jobId="+id
		});
	}
	
	//启动任务
	function startJob(ids){
		var layer = layui.layer;
		if(!ids||ids==""){
			layer.msg('请选择要启动的任务！',{time:2000});
			return;
		}
		<shiro:hasPermission name="/schedule/start.do">
		var layIndex=layer.load(0, {shade: false});
		postData("./start.do", {"jobIds":ids}, true, function (result){
			$('#pageData').html('');
			$(".panel-disabled").hide();
			var success=result.success;
			layer.close(layIndex);
			if(success){
				$(".panel-disabled").hide();
				layer.msg("启动任务成功", {time: 3000});
				$('input').iCheck('uncheck');
				loadData();
			}else{
				$(".panel-disabled").hide();
				layer.msg(result.message, {time: 3000});
			}
		},function (XMLHttpRequest, textStatus, errorThrown){
			layer.close(layIndex);
			$(".panel-disabled").hide();
			layer.msg("启动任务失败", {time: 3000});
		});
		</shiro:hasPermission>
	}
	
	//批量启动任务
	function startJobs(){
		var ids="";
		$("#pageData input:checked").each(function(){ 
			ids+=$(this).val()+","; 
		}); 
		ids=ids.substring(0,ids.length-1);
		if(!ids){
			layer.msg('请选择要启动的任务！',{time:2000});
			return;
		}
		startJob(ids);
	}
	
	//暂停任务
	function pauseJob(ids){
		var layer = layui.layer;
		if(!ids||ids==""){
			layer.msg('请选择要暂停的任务！',{time:2000});
			return;
		}
		<shiro:hasPermission name="/schedule/pause.do">
		var layIndex=layer.load(0, {shade: false});
		postData("./pause.do", {"jobIds":ids}, true, function (result){
			$('#pageData').html('');
			$(".panel-disabled").hide();
			var success=result.success;
			layer.close(layIndex);
			if(success){
				$(".panel-disabled").hide();
				layer.msg("暂停任务成功", {time: 3000});
				$('input').iCheck('uncheck');
				loadData();
			}else{
				$(".panel-disabled").hide();
				layer.msg(result.message, {time: 3000});
			}
		},function (XMLHttpRequest, textStatus, errorThrown){
			layer.close(layIndex);
			$(".panel-disabled").hide();
			layer.msg("暂停任务失败", {time: 3000});
		});
		</shiro:hasPermission>
	}
	
	//恢复任务
	function resumeJob(ids){
		var layer = layui.layer;
		if(!ids||ids==""){
			layer.msg('请选择要恢复的任务！',{time:2000});
			return;
		}
		<shiro:hasPermission name="/schedule/resume.do">
		var layIndex=layer.load(0, {shade: false});
		postData("./resume.do", {"jobIds":ids}, true, function (result){
			$('#pageData').html('');
			$(".panel-disabled").hide();
			var success=result.success;
			layer.close(layIndex);
			if(success){
				$(".panel-disabled").hide();
				layer.msg("恢复任务成功", {time: 3000});
				$('input').iCheck('uncheck');
				loadData();
			}else{
				$(".panel-disabled").hide();
				layer.msg(result.message, {time: 3000});
			}
		},function (XMLHttpRequest, textStatus, errorThrown){
			layer.close(layIndex);
			$(".panel-disabled").hide();
			layer.msg("恢复任务失败", {time: 3000});
		});
		</shiro:hasPermission>
	}
	
	//查看任务日志
	function jobLogs(id){
		var layer = layui.layer;
		//iframe层-父子操作
		layer.open({
		  type: 2,
		  title:"查看任务日志",
		  area: ['900px', '600px'],
		  maxmin: true,
		  scrollbar: false,
		  content: "${webroot}schedule/logs.html?jobId="+id
		});
	}
	
	
	/**
	*切换每页显示的条数
	*/
	function changePage(obj,searchFormId){
		$(".panel-disabled").show();
		var pageSize = $(obj).val();
		$("#pageSize").val(pageSize);
		$("#pageNo").val(1);
		layIndex=layer.load(0, {shade: false});
		postFormData(searchFormId,true,searchResult,searchResultError);
	}
	
	/**
	*翻页
	*/
	function setCurrentPage(currentPage,searchFormId){
		$(".panel-disabled").show();
		$("#pageNo").val(currentPage);
		layIndex=layer.load(0, {shade: false});
		postFormData(searchFormId,true,searchResult,searchResultError);
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
					$("#pageNo").val(currentPage);
					$(".panel-disabled").show();
					layIndex=layer.load(0, {shade: false});
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
		layer.close(layIndex);
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
        		$('input').iCheck({
      			  checkboxClass: 'icheckbox_square-blue',
      			  radioClass: 'iradio_square-blue',
      			  increaseArea: '20%' 
      			})
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
			errorTips(data);
		}
	}
	
	function deleteJobs(){
		var ids="";
		$("#pageData input:checked").each(function(){ 
			ids+=$(this).val()+","; 
		}); 
		ids=ids.substring(0,ids.length-1);
		if(!ids){
			layer.msg('请选择要删除的任务！',{time:2000});
			return;
		}
		deleteJob(ids);
	}
	
	function deleteJob(ids){
		var layer = layui.layer;
		if(!ids||ids==""){
			layer.msg('请选择要删除的任务！',{time:2000});
			return;
		}
		<shiro:hasPermission name="/schedule/delete.do">
		var layIndex=layer.load(0, {shade: false});
		postData("./delete.do", {"jobIds":ids}, true, function (result){
			$('#pageData').html('');
			$(".panel-disabled").hide();
			var success=result.success;
			layer.close(layIndex);
			if(success){
				$(".panel-disabled").hide();
				layer.msg("删除任务成功", {time: 3000});
				$('input').iCheck('uncheck');
				loadData();
			}else{
				$(".panel-disabled").hide();
				layer.msg(result.message, {time: 3000});
			}
		},function (XMLHttpRequest, textStatus, errorThrown){
			layer.close(layIndex);
			$(".panel-disabled").hide();
			layer.msg("删除任务失败", {time: 3000});
		});
		</shiro:hasPermission>
	}
	
	function searchResultError (XMLHttpRequest, textStatus, errorThrown){
		layer.close(layIndex);
		$("#messageDiv").html("获取任务列表失败")
		$("#messageDiv").show();
		$(".panel-disabled").hide();
	}
	

	function loadData(){
		$(".panel-disabled").show();
		layIndex=layer.load(0, {shade: false});
		postFormData("searchForm",true,searchResult,searchResultError);
	}
</script>
</body>
</html>