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
    <jsp:include page="../../../include/title.jsp"></jsp:include>
	
	<link href="${sr}css/font-awesome/css/linecons.css" rel="stylesheet">
	<link href="${sr}css/font-awesome/css/font-awesome.min.css" rel="stylesheet">
    <link href="${sr}css/bootstrap.min.css" rel="stylesheet">
	<link href="${sr}css/xenon.min.css" rel="stylesheet">
	<link href="${sr}css/base.css" rel="stylesheet">
	<link href="${sr}css/new.css" rel="stylesheet">
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
	    <div class="main-content" style="height: 100%;margin: 0px;padding: 0px;background-color: #fff;">
		        <div class="table-panel panel">
		            <div class="panel-heading" style="padding-bottom: 0px;">
			           			<form class="form-inline" id="searchForm" action="${webroot}schedule/logs.do">
			           				
						             <div class="btn-toolbar" style="margin-left:0px;">
						             		<span><label>是否成功：</label><select  id="success" name="success" style="min-width:30px;" class="form-control" style="font-size:10px;">
					           					<option value="-1" selected="selected">全部</option>
					           					<option value="0">是</option>
					           					<option value="1">否</option>
					           				</select></span>
						             		<span><label>操作时间：</label><select  id="timeItem" name="timeItem" style="min-width:50px;" class="form-control" style="font-size:10px;">
					           					<option value="0" selected="selected">不限</option>
					           					<option value="1">今天</option>
					           					<option value="2">昨天</option>
					           					<option value="3">本周</option>
					           					<option value="4">本月</option>
					           					<option value="5">本季</option>
					           					<option value="6">本年</option>
					           					<option value="-1">自定义</option>
					           				</select></span>
					           				<span style="display:none;" id="startTimeLable"><label>开始时间：</label><input type="text" id="startTime"  readonly="readonly" style="width:160px;background-color:#fff;" name="StartTime" class="form-control"></span>
					           				<span style="display:none;" id="endTimeLable"><label>结束时间：</label><input type="text" id="endTime" readonly="readonly" style="width:160px;background-color:#fff;" name="EndTime" class="form-control"></span>
						             		<span>
				           					<a class="btn btn-success btn-sm" id="searchBtn" style="float: none;margin-bottom: 2px;">
						                        <i class="fa fa-search"></i>查询
						                    </a>
						                    </span>
						                <input type="hidden" name="jobId" id="jobId" value="${jobId}"/>
				           				<input type="hidden" name="pageNo" id="pageNo" />
				           				<input type="hidden" name="pageSize" id="pageSize" />
					             </div>
			           			</form>
		            </div>
		            
		                            
		            <div class="dataTables">
		                <div class="dataTables_wrapper form-inline dt-bootstrap no-footer">
		                    <table aria-describedby="example-2_info" role="grid" id="roleList"
		                           class="table table-bordered table-hover table-striped dataTable no-footer">
		                        <thead>
		                        <tr role="row">
		                            <th style="width:60px;text-align:center;">序号</th>
		                            <th style="width:200px;text-align:center;">任务名称</th>
		                            <th style="width:150px;text-align:center;">执行时间</th>
		                            <th style="width:100px;text-align:center;">耗时(毫秒)</th>
		                            <th style="width:100px;text-align:center;">执行结果</th>
		                            <th style="width:80px;text-align:center;">操作</th>
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
			                      
			                            <td style="text-align:center;">{{(d.pageSize * (d.pageNum-1))+index+1}}</td>
			                            <td style="text-align:center;">
			                                <div>
			                                   <span>{{item.jobName}}</span>
			                                </div>
			                            </td>
			                             <td>
			                                <div>
			                                   <span>{{item.beginTime}}</span>
			                                </div>
			                            </td>
			                             <td>
			                                <div>
			                                   <span>{{item.useTime }}</span>
			                                </div>
			                            </td>
			                             <td>
			                                <div>
			                                   <span>{{item.statusName}}</span>
			                                </div>
			                            </td>
										<td>
			                                <div>
			                                   <span><a href="javascript:viewLogger({{item.logId}})">查看详情</a></span>
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
<script src="${sr}js/jquery.min.js" type="text/javascript"></script>
<script src="${sr}js/main.js" type="text/javascript"></script>
<script src="${sr}layui.js" type="text/javascript"></script>
<script src="${sr}js/config.js" type="text/javascript"></script>
<script src="${sr}js/baseutil.js" type="text/javascript"></script>

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
	  		$("#pageNo").val(1);
	  		$(".panel-disabled").show();
	  		layIndex=layer.load(0, {shade: false});
	  		postFormData("searchForm",true,searchResult,searchResultError);
	  	});
	  	 
	  	 $("select[name='timeItem']").change(function() {  
	  		var value = $(this).val();
	  		$("#startTime").val('');
	  		$("#endTime").val('');
	  		if(value==-1){
	  			$("#startTimeLable").show();
	  			$("#endTimeLable").show();
	  		}else{
	  			$("#startTimeLable").hide();
	  			$("#endTimeLable").hide();
	  		}
	  	 });
	  	 
	  	var laydate = layui.laydate;
	  	var start = {
	  		max: laydate.now(),
	  		format: 'YYYY-MM-DD hh:mm:ss',
	  		istoday: true,
	  		istime: true,
 		    choose: function(datas){
 		      end.min = datas; //开始日选好后，重置结束日的最小日期
 		      end.start = datas //将结束日的初始值设定为开始日
 		    }
 		  };
 		  
 		  var end = {
 		    max: laydate.now(),
 		    istoday: true,
 		    istime: true,
 		    format: 'YYYY-MM-DD hh:mm:ss',
 		    choose: function(datas){
 		      start.max = datas; //结束日选好后，重置开始日的最大日期
 		    }
 		  };
 		  
 		  document.getElementById('startTime').onclick = function(){
 		    start.elem = this;
 		    laydate(start);
 		  }
 		  document.getElementById('endTime').onclick = function(){
 		    end.elem = this
 		    laydate(end);
 		  }
	  	
	});
	
	//查看日志详情
	function viewLogger(id){
		window.location.href="./log.html?logId="+id;
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
	
	function searchResultError (XMLHttpRequest, textStatus, errorThrown){
		layer.close(layIndex);
		$("#messageDiv").html("获取日志列表失败")
		$("#messageDiv").show();
		$(".panel-disabled").hide();
	}
	

	function loadPage(){
		$(".panel-disabled").show();
		layIndex=layer.load(0, {shade: false});
		postFormData("searchForm",true,searchResult,searchResultError);
	}
</script>
</body>
</html>