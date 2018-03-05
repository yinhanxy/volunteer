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
		                        <a>培训记录管理</a>
		                    </li>
		                    <li class="active">
		                        <a href="${webroot}record/list.html"><strong>培训记录</strong></a>
		                    </li>
		                </ol>
		            </div>
		        </div>
		        
		        <div class="table-panel panel">
		            <div class="panel-heading" style="padding-bottom: 0px;">
		           			<div class="btn-toolbar" style="margin-left:0px;">
			           			<form class="form-inline" id="searchForm" action="${webroot}record/list.do" method="POST">
			           				<span><label>培训名称：</label><input type="text" name="trName" class="form-control"></span>
			           				<span><label>负责人：</label><input type="text" name="principal" class="form-control"></span>
			           				<span id="showSerTeam" style="height:200px;overflow-y:auto;width:100px;"><label>所属服务队：</label>
									<select class="form-control" id="teamName" name="teamName" style="display: inline-block;">
									</select>
									</span>
			           				<span><label>  状态：</label>
			           				<select class="form-control input-sm" name="status">
			           					<option value="">所有</option>
			           					<option value="1">待进行</option>
			           					<option value="2">进行中</option>
			           					<option value="3">已完成</option>
			           				</select>
			           				</span>
			           				<div class="form-group" style="display: block;margin-top: 10px">
										    <span>
											    <a class="btn btn-success btn-sm" id="searchBtn" style="float: none;margin-bottom: 2px;">
							                        <i class="fa fa-search">查询</i>
							                    </a>
							                    <a class="btn btn-info btn-sm" id="resetBtn" style="float: none;margin-bottom: 2px;">
							                        <i class="fa fa-undo">清除条件</i>
							                    </a>
						                    </span>
										  </div>
			           				<input type="hidden" name="page" id="currentPageInput" />
			           				<input type="hidden" name="rows" id="pageSizeInput" />
			           			</form>
				             </div>
		            </div>
		            
		             <div class="pull-left" style="padding-top: 8px;">
		                    <shiro:hasPermission name="/record/addTrRecord.html">
			                    <a class="btn btn-success btn-sm" onclick="addTrRecord()">
			                        <i class="fa fa-plus">新增</i>
			                    </a>
		                   </shiro:hasPermission>
		                   <shiro:hasPermission name="/record/delTrRecord.do">
			                    <a class="btn btn-danger btn-sm" onclick="delTrRecord()" id="del">
			                    	<i class="fa fa-trash-o">删除</i>
			                    </a>
		                   </shiro:hasPermission>
		              </div>
		                <div class="form-inline">
		                    <table id="trRecordList" class="table table-bordered table-hover table-striped">
		                        <thead>
		                        <tr role="row">
		                        	<th style="width: 40px"><input id="allcheck" type="checkbox" class="ace" ></th>
		                            <th style="width: 60px">序号</th>
		                            <th >培训名称</th>
		                            <th >培训时间</th>
		                            <th style="width:70px">负责人</th>
		                            <th style="width: 65px">状态</th>
		                            <th style="width:160px">操作</th>
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
			                            <td >
											<input type="checkbox" class="ace" {{item.roleType==1 ? 'name="RID"':''}} value="{{item.id}}">
										</td>
			                            <td >{{(d.pageSize * (d.pageNum-1))+index+1}}</td>
			                            <td >
			                                <div>
			                                   <span>
													<shiro:hasPermission name="/record/viewTrRecord.html">
													<a href="javascript:void(0)" onclick="viewTrRecord({{item.id}})">
													</shiro:hasPermission>
													{{item.trName}}
													<shiro:hasPermission name="/record/viewTrRecord.html">
													</a>
													</shiro:hasPermission>
												</span>
			                                </div>
			                            </td>
										<td >
			                                <div>
			                                   <span>{{item.trTime}}</span>
			                                </div>
			                            </td>
			                             <td>
			                                <div>
			                                   <span>{{item.principal}}</span>
			                                </div>
			                            </td>
			                             
			                             <td>
			                                <div>
			                                   <span>{{item.status==1 ? '待进行' :item.status==2 ? '进行中' : '已完成'}}</span>
			                                </div>
			                            </td>
										<td>
											<span class="btn-group btn-group-sm btn-group-xs">
												<shiro:hasPermission name="/record/editTrRecord.html">
											   		<a class="btn btn-info btn-icon"  {{item.roleType==1 ? 'disabled':''}} style="margin-right:5px;" title="编辑"
                                                                     {{item.roleType==1 ? "":"onclick='editTrRecord("+item.id+")'"}} >
														<i class="fa fa-pencil-square-o">编辑</i></a>
												</shiro:hasPermission>
												<shiro:hasPermission name="/record/personal.html">
													<a class="btn btn-danger btn-icon"   title="用户管理" style="margin-right:5px;" onclick="setupUser({{item.id}})">
													<i class="fa fa-cog">人员记录</i></a>
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
		var layer = layui.layer;
		layer.config({
			  extend: 'myskin/style.css'
			});
		$("#trRecordList").on("ifChanged",":checkbox", function(){
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
	  		$("#searchForm [name=trName]").val("");
	  		$("#searchForm [name=principal]").val("");
	  		$("#searchForm [name=teamName]").val("");
	  		$("#searchForm [name=status]").val("");
	  		postFormData("searchForm",true,searchResult);
	  	});
	  	
	  	$(document).ajaxComplete( function(event, jqXHR, options){
			$('input').iCheck({
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
			var html="<option value=''>所有</option>";
			var list =data.serTeamList;
			for (var i = 0; i < list.length; i++) {
				html+="<option value='"+list[i].name+"'>"+list[i].name+"</option>";
			}
			$("#teamName").prepend(html);
		} 
		postFormData("searchForm",true,searchResult);
	}
	
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
	
	<shiro:hasPermission name="/record/addTrRecord.html">
	//新增培训记录
	function addTrRecord(){
		var layer = layui.layer;
		//iframe层-父子操作
		var index=layer.open({
		  type: 2,
		  title:"新增培训记录",
		  skin: 'edit-class',
		  area: ['700px', '570px'],
		  scrollbar: false,
		  content: ["${webroot}record/addTrRecord.html","no"]
		});
		layer.full(index);
	}
	</shiro:hasPermission >
	
	<shiro:hasPermission name="/record/viewTrRecord.html">
	//查看培训记录
	function viewTrRecord(id){
		var layer = layui.layer;
		var index=layer.open({
		  type: 2,
		  title:"查看培训记录",
		  skin: 'view-class',
		  area: ['800px', '580px'],
		  content: "${webroot}record/viewTrRecord.html?id="+id,
		  btn: ['<i class="fa fa-remove"></i>关闭'],
		  closeBtn:1
		});
		layer.full(index);
	}
	</shiro:hasPermission >
	
	<shiro:hasPermission name="/record/editTrRecord.html">
	//编辑培训记录
	function editTrRecord(id){
		var layer = layui.layer;
		var index=layer.open({
		  type: 2,
		  title:"编辑培训记录",
		  area: ['780px', '580px'],
		  scrollbar: false,
		  content: ["${webroot}record/editTrRecord.html?id="+id,"no"],
		  //关闭层后的回调函数
		  end:function(){
			  
		  }
		});
		layer.full(index);
	}
	</shiro:hasPermission >
	
	<shiro:hasPermission name="/record/delTrRecord.do">
	//删除培训记录
	function delTrRecord(){
		var layer = layui.layer;
		var ids="";
		$("#pageData input:checked").each(function(){ 
			ids+=$(this).val()+","; 
		}); 
		ids=ids.substring(0,ids.length-1);
		if(!ids){
			layer.msg('请选择要删除的培训记录！',{time:1000});
			return false;
		}else{
			layer.confirm('确定要删除选中的培训记录吗？', {icon: 3, title:'系统提示',
			  btn: ['确定','取消'] //按钮
			}, function(){
				var url="${webroot}record/delTrRecord.do";
				params={"ids":ids};
				postData(url, params, true, delRoleResult); 
			  	
			});
		}
	}
	</shiro:hasPermission >
	
	<shiro:hasPermission name="/record/personal.html">
	//人员记录
	function setupUser(id){
		var layer = layui.layer;
		var index=layer.open({
		  type: 2,
		  title:"人员记录",
		  skin: 'view-class',
		  area: ['800px', '580px'],
		  scrollbar: false,
		  fix:false,
		  content: "${webroot}record/personal.html?id="+id,
		  btn: ['<i class="fa fa-remove"></i>关闭'],
		  closeBtn:1
		});
		layer.full(index);
	}
	</shiro:hasPermission>
	
	
	<shiro:hasPermission name="/record/delTrRecord.do">
	//删除培训记录的回调函数
	function delRoleResult(data){
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
	</shiro:hasPermission>
	
	function loadPage(){
		postFormData("searchForm",true,searchResult);
	}
</script>
</body>
</html>