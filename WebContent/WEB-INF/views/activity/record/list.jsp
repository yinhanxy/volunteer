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
	<link href="${sr}css/bootstrap-datetimepicker.min.css" rel="stylesheet">
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
		
	    <!--主内容 -->
	    <div class="main-content" style="height: 100%;min-width:1100px;" style="margin: 0px;padding: 0px;background-color: #fff;">
		    <div  class="workspace-page">
		    
		        <div class="page-title list-page">
		            <div class="title-env">
		                <ol class="breadcrumb bc-1" style="margin: 0px;">
		                    <li>
		                        <a href="${webroot}main.html" target="_top"><i class="fa-home"></i>首页</a>
		                    </li>
		                    <li>
		                        <a>活动管理</a>
		                    </li>
		                    <li class="active">
		                        <a  href="${webroot}activity/activityRecord.html"><strong>活动记录</strong></a>
		                    </li>
		                </ol>
		            </div>
		        </div>
		        
		        <div class="table-panel panel">
		        
		            <div class="panel-heading" style="padding-bottom: 0px;">
		           			<div class="btn-toolbar" style="margin-left: 0px;">
			           			<form class="form-inline" id="searchForm" action="${webroot}activity/activityRecord.do" method="POST">
			           				<div class="row">
			           					<span class="col-xs-12">
			           						<label style="margin-left: 23px;">活动名称：</label><input type="text" name="activityName" class="form-control">
			           						<label style="margin-left: 37px;">发布人：</label><input type="text" name="publisher" class="form-control">
			           						<span id="ser">
					           					<label>所属服务队：</label>
				           						<select class="form-control" name="serviceTeam" id="serviceTeam" >
					           					</select>
				           					</span>
				           					<label>状态：</label>
				           					<select class="form-control" name="status" id="status">
					           					<option value="">所有</option>
					           					<option value="05">待进行</option>
					           					<option value="06">进行中</option>
					           					<option value="07">已完成</option>
					           				</select>
			           					</span>
			           				</div>
			           				<div class="row" style="margin-top: 10px;">
			           					<span class="col-xs-12">
			           						<label style="margin-right: 0px;">活动开始时间：</label><input type="text" name="startTime" class="form-control" id="startTime">
			           						<label>活动结束时间：</label><input type="text" name="endTime" class="form-control" id="endTime"> 
			           						<a class="btn btn-success btn-sm" id="searchBtn" style="float: none;margin-bottom: 2px;">
					                        <i class="fa fa-search">查询</i>
						                    </a>
						                    <a class="btn btn-info btn-sm" id="resetBtn" style="float: none;margin-bottom: 2px;">
						                        <i class="fa fa-undo">清除条件</i>
						                    </a>
			           					</span>
			           				</div>
			           				<input type="hidden" name="page" id="currentPageInput" value="1"/>
			           				<input type="hidden" name="rows" id="pageSizeInput" />
			           			</form>
				             </div>
		            </div>
		            
		             <div class="pull-left tableTools-container" style="padding-top: 8px;" id="optional">
		              </div>
		                            
		            <div class="dataTables">
		                <div class="dataTables_wrapper form-inline dt-bootstrap no-footer">
		                    <table aria-describedby="example-2_info" role="grid" id="userList"
		                           class="table table-bordered table-hover table-striped dataTable no-footer">
		                        <thead>
		                        <tr role="row">
		                        	<th style="width: 40px"><input id="allcheck" type="checkbox" class="ace"></th>
		                            <th style="width: 60px">序号</th>
		                            <th>活动名称</th>
		                            <th style="width: 160px">活动开始时间</th>
		                            <th style="width: 90px">活动类型</th>
		                            <th style="width: 130px">服务时长(小时)</th>
		                            <th style="width: 70px">状态</th>
		                            <th style="width: 190px">操作</th>
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
											<input type="checkbox" class="ace" id="UID" value="{{item.id}}">
										</td>
			                            <td>{{(d.pageSize * (d.pageNum-1))+index+1}}</td>
			                            <td>
			                                <div>
												<span>
													<shiro:hasPermission name="/activity/view.html">
														<a href="javascript:void(0)" onclick="view({{item.id}})">
													</shiro:hasPermission>
															{{item.name}}
											   		<shiro:hasPermission name="/activity/view.html">	
														</a>
													</shiro:hasPermission>
												</span>
			                                </div>
			                            </td>
										<td>
			                                <div>
			                                   <span>{{getFormatDateByLong(item.activitySt)}}</span>
			                                </div>
			                            </td>
			                            <td>
			                                <div>
			                                   <span>{{item.type==1?"短期":"长期" }}</span>
			                                </div>
			                            </td>
			                             <td>
			                                <div>
			                                   <span>{{item.hours}}</span>
			                                </div>
			                            </td>
										<td>
			                                <div>
			                                   <span>{{item.statusDesc}}</span>
			                                </div>
			                            </td>
										<td>
			                                <div>
											<span class="btn-group btn-group-sm btn-group-xs">
												<shiro:hasPermission name="/activity/record.html">
											   		<a class="btn btn-info btn-icon icon-left btn-one-last" style="margin-right:5px;" title="活动记录" onclick="record({{item.id}})"><i class="fa fa-pencil-square-o">活动记录</i></a>
												</shiro:hasPermission>
												<shiro:hasPermission name="/activity/hourManagement.html">
											   		<a class="btn btn-info btn-icon icon-left btn-one-last" style="margin-right:5px;" title="时长管理" onclick="hourMangement({{item.id}})"><i class="fa fa-pencil-square-o">时长管理</i></a>
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
	<!-- 主内容结束 -->
</div>

<jsp:include page="../../include/footer.jsp"></jsp:include>		

<script src="${sr}js/jquery.min.js" type="text/javascript"></script>
<script src="${sr}js/config.js" type="text/javascript"></script>
<script src="${sr}js/baseutil.js" type="text/javascript"></script>
<script src="${sr}js/main.js" type="text/javascript"></script>
<script src="${sr}layui.js" type="text/javascript"></script>
<script src="${sr}js/iCheck/icheck.min.js?v=1.0.2" type="text/javascript"></script>
<script src="${sr}js/iCheck/custom.min.js?v=1.0.2" type="text/javascript"></script>
<script type="text/javascript" src="${sr}js/bootstrap-datetimepicker.min.js" charset="UTF-8"></script>
<script type="text/javascript" src="${sr}js/locales/bootstrap-datetimepicker.zh-CN.js" charset="UTF-8"></script>
<script type="text/javascript">
	layui.use(['jquery','layer','laytpl'], function(){
		initMenu();
		$('#startTime').datetimepicker({
			language:  'zh-CN',
	        weekStart: 1,
			autoclose: 1,
			todayHighlight: 1,
			startView: 2,
			minView: 2,
			forceParse: true,
			format:"yyyy-mm-dd",
			pickerPosition:"bottom-left",
		}).on('click', function(ev){
	        $("#startTime").datetimepicker("setEndDate",$("#endTime").val());
	    });
		$('#endTime').datetimepicker({
			language:  'zh-CN',
	        weekStart: 1,
			autoclose: 1,
			todayHighlight: 1,
			startView: 2,
			minView: 2,
			forceParse: true,
			format:"yyyy-mm-dd",
			pickerPosition:"bottom-left",
		}).on('click', function(ev){
	        $("#endTime").datetimepicker("setStartDate",$("#startTime").val());
    	});
		var layer = layui.layer;
		layer.config({
			  extend: 'myskin/style.css'
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
	  		$("#searchForm [name='activityName']").val("");
	  		$("#searchForm [name='publisher']").val("");
	  		$("#searchForm [name='serviceTeam']").val("");
	  		$("#searchForm [name='status']").val("");
	  		$("#searchForm [name='startTime']").val("");
	  		$("#searchForm [name='endTime']").val("");
	  		postFormData("searchForm",true,searchResult);
	  	});
	  	

	  	$(document).ajaxComplete( function(event, jqXHR, options){
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
		});
	  	
	  	$("#btnExport").click(function(){
			top.layer.confirm('确认要导出Excel吗?', {icon: 3, title:'系统提示'}, function(index){
			    	//导出之前备份
			    	var url =  $("#searchForm").attr("action");
			    	var pageNo =  $("#currentPageInput").val();
			    	var pageSize = $("#pageSizeInput").val();
			        $("#searchForm").attr("action","${webroot}/user/exportUser.html");
					$("#searchForm").submit();

					$("#searchForm").attr("action",url);
				    $("#currentPageInput").val(pageNo);
					$("#pageSizeInput").val(pageSize);
			    	top.layer.close(index);
			});
		});
	  	showSerTeam();
	});
	
	//显示服务队
	function showSerTeam(){
		$("#ser").hide();
		var url="${webroot}record/serTeamDisplay.do";
		params={};
		postData(url, params, true, showSerTeamResult); 
	}
	
	function showSerTeamResult(data){
		if (data.success) {
			$("#ser").show();
			var html="";
			var list =data.serTeamList;
			html+="<option value=''>所有</option>";
			for (var i = 0; i < list.length; i++) {
				html+="<option value='"+list[i].id+"'>"+list[i].name+"</option>";
			}
			$("#serviceTeam").prepend(html);
		} 
		postFormData("searchForm",true,searchResult);
	}
	
	<shiro:hasPermission name="/activity/view.html">
	//查看活动
	function view(id){
		var layer = layui.layer;
		//iframe层-父子操作
		var index=layer.open({
		  type: 2,
		  title:"查看活动",
		  skin: 'view-class',
		  area: ['750px'],
		  scrollbar: false,
		  content: ["${webroot}activity/view.html?aId="+id],
		  btn: ['<i class="fa fa-remove"></i>关闭']
		});
		layer.full(index);
	}
	</shiro:hasPermission>
	
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
			layer.msg(message);
		}
	}
	
	<shiro:hasPermission name="/activity/record.html">
	//活动记录
	function record(aId){
		var layer = layui.layer;
		var index=layer.open({
		  type: 2,
		  title:"活动记录",
		  area: ['740px'],
		  scrollbar: false,
		  content: ["${webroot}activity/record.html?aId="+aId],
		  //关闭层后的回调函数
		  end:function(){
			  
		  }
		});
		layer.full(index);
	}
	</shiro:hasPermission>
	
	<shiro:hasPermission name="/activity/hourManagement.html">
	//时长管理
	function hourMangement(aId){
		var layer = layui.layer;
		var index=layer.open({
		  type: 2,
		  title:"时长管理",
		  area: ['900px'],
		  scrollbar: false,
		  content: ["${webroot}activity/hourManagement.html?aId="+aId,"no"],
		  //关闭层后的回调函数
		  end:function(){
			  
		  }
		});
		layer.full(index);
	}
	</shiro:hasPermission>
	
	/**
	**回调函数
	*/
	function userResult(data){
		var laytpl = layui.laytpl;
		var layer = layui.layer;
		var message=data.message;
		if(data && data.success){
			layer.msg(message, {icon: 1,time:1000});
			loadPage();
		}else{
			//layer.msg(message, {icon: 2,time:1000});
			layer.alert(message, {icon: 2});
		}
	}
	
	function loadPage(){
		postFormData("searchForm",true,searchResult);
	}
</script>
</body>
</html>