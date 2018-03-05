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
    <style type="text/css">
    	.v-page-class .layui-layer-btn .layui-layer-btn0{
			padding: 5px 10px;
		    font-size: 12px;
		    line-height: 1.5;
		    border-radius: 3px;outline: 0;
		    border: 1px solid transparent;
		    margin-bottom: 10px;background-color:rgba(151, 152, 152, 0.81);
		}
    </style>
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
		                        <a>志愿者管理</a>
		                    </li>
		                    <li class="active">
		                        <a  href="${webroot}cert/list.html"><strong>证书管理</strong></a>
		                    </li>
		                </ol>
		            </div>
		        </div>
		        
		        <div class="table-panel panel">
		        
		            <div class="panel-heading" style="padding-bottom: 0px;">
		           			<div class="btn-toolbar" style="margin-left: 0px;">
			           			<form class="form-inline" id="searchForm" action="${webroot}cert/list.do" method="POST">
				           				<span><label >姓名：</label><input type="text" name="realName" class="form-control" id="volsName" style="width:15%;"></span>
				           				<span><label>证件号：</label><input type="text" name="idCard" class="form-control" style="width:15%;"></span>
				           				<span><label>手机号：</label><input type="text" name="tele" class="form-control" style="width:15%;"></span>
				           				<span id="showSerTeam">
				           					<label>所属服务队：</label>
				           					<select class="form-control" name="serviceTeam" id="serviceTeam">
				           					</select>
			           					</span>
			           					<br />
			           					<br />
			           					<span>
			           						<label>状态：</label>
			           						<select class="form-control" name="status" id="status">
					           					<option value="-1">所有</option>
					           					<option value="">待审核</option>
					           					<option value="01">已通过</option>
					           					<option value="02">已否</option>
			           						</select>
			           					</span>
			           					<span><a class="btn btn-success btn-sm" id="searchBtn" style="float: none;margin-bottom: 2px;">
					                        <i class="fa fa-search">查询</i>
					                    </a>
					                    <a class="btn btn-info btn-sm" id="resetBtn" style="float: none;margin-bottom: 2px;">
					                        <i class="fa fa-undo">清除条件</i>
					                    </a>
					                </span>
			           				
			           				<input type="hidden" name="page" id="currentPageInput" value="1"/>
			           				<input type="hidden" name="rows" id="pageSizeInput" />
			           			</form>
				             </div>
		            </div>
		            
		             <div class="pull-left tableTools-container" style="padding-top: 8px;" id="optional">
		             		<shiro:hasPermission name="/cert/addCert.html">
								<a class="btn btn-success  btn-sm" onclick="addCert()" ><i class="fa fa-plus">新增证书</i></a>
							</shiro:hasPermission>
							<shiro:hasPermission name="/vols/delVolunteerCerts.do">
								<a class="btn btn-danger btn-sm" onclick="delCert()" ><i class="fa fa-trash-o">删除</i></a>
							</shiro:hasPermission>
		              </div>
		                            
		            <div class="dataTables">
		                <div class="dataTables_wrapper form-inline dt-bootstrap no-footer">
		                    <table aria-describedby="example-2_info" role="grid" id="volsList"
		                           class="table table-bordered table-hover table-striped dataTable no-footer">
		                        <thead>
		                        <tr role="row">
		                        	<th style="width: 40px"><input id="allcheck" type="checkbox" class="ace"></th>
		                            <th style="width: 60px">序号</th>
		                            <th style="width: 80px">姓名</th>
		                            <th style="width: 170px">证件号</th>
		                            <th >颁发时间</th>
		                            <th style="width: 90px">审核年份</th>
		                            <th >审核时间</th>
		                            <th >审核人</th>
		                            <th style="width: 90px">审核状态</th>
		                            <th style="width: 100px">操作</th>
		                        </tr>
		                        </thead>
		                        <tbody class="middle-align" id="pageData">
		                        	
		                        </tbody>
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
			                            <td>
											<input type="checkbox" class="ace" id="UID" value="{{item.id}}">
										</td>
			                            <td >{{(d.pageSize * (d.pageNum-1))+index+1}}</td>
			                            <td>
			                                <div>
			                                   	<span>
													<shiro:hasPermission name="/cert/viewVolunteerCert.html">
														<a href="javascript:void(0)" onclick="viewVolunteerCert({{item.id}},'{{item.serviceTeam }}')">
													</shiro:hasPermission>
															{{item.realName}}
											   		<shiro:hasPermission name="/cert/viewVolunteerCert.html">	
														</a>
													</shiro:hasPermission>	
												</span>
			                                </div>
			                            </td>
										<td >
			                                <div>
			                                   <span>{{item.idcard }}</span>
			                                </div>
			                            </td>
			                             <td>
			                                <div>
			                                   <span>{{item.certDate==null?"":getFormatDateByLong(item.certDate,"yyyy-MM-dd")}}</span>
			                                </div>
			                            </td>
			                             <td>
			                                <div>
			                                   <span>{{getFormatDate(null,"yyyy") }}</span>
			                                </div>
			                            </td>
										<td>
			                                <div>
			                                   <span>{{item.opertTime==null?"":getFormatDateByLong(item.opertTime,"yyyy-MM-dd")}}</span>
			                                </div>
			                            </td>
										<td>
			                                <div>
			                                   <span>{{item.opertUser}}</span>
			                                </div>
			                            </td>
										<td>
			                                <div>
			                                   <span>{{item.checkState}}</span>
			                                </div>
			                            </td>
										<td>
			                                <div>
											<span class="btn-group btn-group-sm btn-group-xs">
													<shiro:hasPermission name="/cert/setVolunteerCert.html">
											   			<a class="btn btn-info btn-icon icon-left btn-one-last" style="margin-right:5px;" title="年检管理" onclick="setVolunteerCert({{item.id}},'{{item.serviceTeam }}')"><i class="fa fa-pencil-square-o">年检管理</i></a>
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
<script type="text/javascript">
	layui.use(['jquery','layer','laytpl'], function(){
		initMenu();
		$(".panel-disabled").hide();
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
	  		$("#searchForm [name=realName]").val("");
	  		$("#searchForm [name=idCard]").val("");
	  		$("#searchForm [name=tele]").val("");
	  		$("#searchForm [name=serviceTeam] ").val("");
	  		$("#searchForm [name=status]").val("-1");
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
			        $("#searchForm").attr("action","${webroot}/vols/exportvols.html");
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
		$("#showSerTeam").hide();
		var url="${webroot}record/serTeamDisplay.do";
		params={};
		postData(url, params, true, showSerTeamResult); 
	}
	
	function showSerTeamResult(data){
		if (data.success) {
			$("#showSerTeam").show();
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
	
	<shiro:hasPermission name="/cert/addCert.html">
	//添加证书
	function addCert(){
		var layer = layui.layer;
		layer.open({
		  type: 2,
		  title:"新增证书",
		  area: ['600px', '510px'],
		  scrollbar: false,
		  content: ["${webroot}cert/addCert.html"],
		  //关闭层后的回调函数
		  end:function(){
			  
		  }
		});
	}
	</shiro:hasPermission>
	
	<shiro:hasPermission name="/cert/viewVolunteerCert.html">
	//查看证书
	function viewVolunteerCert(id,teamName){
		var layer = layui.layer;
		//iframe层-父子操作
		var index=layer.open({
		  type: 2,
		  title:"查看证书",
		  skin: 'view-class',
		  area: ['950px', '670px'],
		  scrollbar: false,
		  content: ["${webroot}cert/viewVolunteerCert.html?volunteerId="+id+"&teamName="+teamName],
		  btn: ['<i class="fa fa-remove"></i>关闭'],
			yes : function(index, layero) {
				var iframeWin = top.window[layero.find('iframe')[0]['name']]; 
				iframeWin.closeFrame();
			}
		});
		layer.full(index);
	}
	</shiro:hasPermission>
	
	<shiro:hasPermission name="/cert/setVolunteerCert.html">
	//年检管理
	function setVolunteerCert(id,teamName){
		var layer = layui.layer;
		var index=layer.open({
		  type: 2,
		  title:"年检管理",
		  area: ['950px', '670px'],
		  scrollbar: false,
		  content: ["${webroot}cert/setVolunteerCert.html?volunteerId="+id+"&teamName="+teamName],
		  //关闭层后的回调函数
		  end:function(){
			  
		  }
		});
		layer.full(index);
	}
	</shiro:hasPermission>
	
	<shiro:hasPermission name="/vols/delVolunteerCerts.do">
	//删除证书
	function delCert(){
		var layer = layui.layer;
		var ids="";
		$("#pageData input:checked").each(function(){ 
			ids+=$(this).val()+","; 
		}); 
		ids=ids.substring(0,ids.length-1);
		if(!ids){
			layer.msg('请选择要删除指定志愿者的证书信息?',{time:1000});
			return;
		}else{
			layer.confirm('确定要删除指定志愿者的证书信息吗？', {
			  btn: ['确定','取消'] //按钮
			}, function(){
				var url="${webroot}vols/delVolunteerCerts.do";
				params={"ids":ids};
				postData(url, params, true,volsResult); 
			  	
			}, function(){
			  
			});
		}
	}
	</shiro:hasPermission>
	
	/**
	**回调函数
	*/
	function volsResult(data){
		var laytpl = layui.laytpl;
		var layer = layui.layer;
		var message=data.message;
		if(data && data.success){
			layer.msg(message, {icon: 1,time:1000});
			loadPage();
		}else{
			layer.alert(message, {icon: 2});
		}
	}
	
	function loadPage(){
		$("#currentPageInput").val(1);
		postFormData("searchForm",true,searchResult);
	}
</script>
</body>
</html>