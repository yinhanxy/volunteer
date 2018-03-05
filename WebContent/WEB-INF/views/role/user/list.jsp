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
		                        <a>权限管理</a>
		                    </li>
		                    <li class="active">
		                        <a  href="${webroot}user/list.html"><strong>用户管理</strong></a>
		                    </li>
		                </ol>
		            </div>
		        </div>
		        
		        <div class="table-panel panel">
		        
		            <div class="panel-heading" style="padding-bottom: 0px;">
		           			<div class="btn-toolbar" style="margin-left: 0px;">
			           			<form class="form-inline" id="searchForm" action="${webroot}user/userList.do" method="POST">
			           				<span><label>用户名：</label><input type="text" name="userName" class="form-control"></span>
			           				<span><label>真实姓名：</label><input type="text" name="realName" class="form-control"></span>
			           				<span><label>状态：</label><select class="form-control" name="status" id="userStatus">
			           					<option value="">所有</option>
			           					<option value="02">未开通</option>
			           					<option value="01">已开通</option>
			           					<option value="03">已停用</option>
			           					<option value="04">已删除</option>
			           				</select></span>
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
							
		              </div>
		                            
		            <div class="dataTables">
		                <div class="dataTables_wrapper form-inline dt-bootstrap no-footer">
		                    <table aria-describedby="example-2_info" role="grid" id="userList"
		                           class="table table-bordered table-hover table-striped dataTable no-footer">
		                        <thead>
		                        <tr role="row">
		                        	<th style="width:40px;"><input id="allcheck" type="checkbox" class="ace"></th>
		                            <th style="width:60px;">序号</th>
		                            <th>用户名</th>
		                            <th style="width: 85px">当前状态</th>
		                            <th style="width: 95px">真实姓名</th>
		                            <th style="width: 160px">注册时间</th>
		                            <th style="width: 160px">开通时间</th>
		                            <th style="width: 320px">操作</th>
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
			                            <td>
											<input type="checkbox" class="ace" id="UID" value="{{item.id}}">
										</td>
			                            <td>{{(d.pageSize * (d.pageNum-1))+index+1}}</td>
			                            <td>
			                                <div>
			                                   <span>
													<shiro:hasPermission name="/user/viewUser.html">
														<a href="javascript:void(0)" onclick="viewUser({{item.id}})">
													</shiro:hasPermission>
															{{item.userName}}
											   		<shiro:hasPermission name="/user/viewUser.html">	
														</a>
													</shiro:hasPermission>
												</span>
			                                </div>
			                            </td>
										<td>
			                                <div>
			                                   <span>{{item.userStatus}}</span>
			                                </div>
			                            </td>
			                             <td>
			                                <div>
			                                   <span>{{item.realName }}</span>
			                                </div>
			                            </td>
			                             <td>
			                                <div>
			                                   <span>{{getFormatDateByLong(item.regTime) }}</span>
			                                </div>
			                            </td>
										<td>
			                                <div>
			                                   <span>{{getFormatDateByLong(item.useTime) }}</span>
			                                </div>
			                            </td>
										<td>
			                                <div>
											<span class="btn-group btn-group-sm btn-group-xs">
												<shiro:hasPermission name="/user/viewAccess.html">
			                                   		<a class="btn btn-secondary btn-icon icon-left" style="margin-right:5px;" title="查看权限" onclick="viewAccess({{item.id}})"><i class="fa-search">查看权限</i></a>
											   	</shiro:hasPermission>
												<shiro:hasPermission name="/user/editUser.html">
											   		<a class="btn btn-info btn-icon icon-left btn-one-last" style="margin-right:5px;" title="编辑" onclick="editUser({{item.id}})"><i class="fa fa-pencil-square-o">编辑</i></a>
												</shiro:hasPermission>
												<a class="btn btn-danger btn-icon"  title="访问控制" style="margin-right:5px;" onclick='setChannel({{item.id}})'><i class="fa fa-cog">访问控制</i></a>
												<shiro:hasPermission name="/user/userAccess.html">
													<a class="btn btn-danger btn-icon"  title="权限管理" style="margin-right:5px;" onclick='setupAccess({{item.id}})'><i class="fa fa-cog">权限配置</i></a>
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
		var layer = layui.layer;
		layer.config({
			  extend: 'myskin/style.css'
			});
		postFormData("searchForm",true,searchResult);
	  	getButton();
	  	//查询按钮
	  	$("#searchBtn").click(function(){
	  		getButton();
			$("#currentPageInput").val(1);
	  		postFormData("searchForm",true,searchResult);
	  	});
	  	
	  	//清空条件按钮
	  	$("#resetBtn").click(function(){
	  		$("#currentPageInput").val(1);
	  		$("#searchForm [name=userName]").val("");
	  		$("#searchForm [name=realName]").val("");
	  		$("#userStatus").val("");
	  		getButton();
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
	  	
	  	/* $("#btnExport").click(function(){
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
		}); */
	});
	
	<shiro:hasPermission name="/user/userAccess.html">
	//给用户设置访问权限
	function setupAccess(id){
		var layer = layui.layer;
		layer.open({
			type: 2,
			title:"权限设置",
			skin: 'edit-class',
			area: ['300px', '450px'],
			maxmin: true,
			scrollbar: true,
			content: "${webroot}user/userAccess.html?uId="+id,
			btn: ['<i class="fa fa-floppy-o"></i>保存','<i class="fa fa-remove"></i>关闭'],
			yes : function(index, layero) {
				var iframeWin = top.window[layero.find('iframe')[0]['name']]; 
				iframeWin.addCheckedMenus();
			},
			btn2 : function(index, layero) {
				var iframeWin = top.window[layero.find('iframe')[0]['name']]; 
				iframeWin.closeFrame();
			}
		});
	}
	</shiro:hasPermission>
	
	<shiro:hasPermission name="/user/viewAccess.html">
	//查看用户所有访问权限
	function viewAccess(id){
		var layer = layui.layer;
		layer.open({
			type: 2,
			title:"查看权限",
			skin: 'view-class',
			area: ['300px', '450px'],
			maxmin: true,
			scrollbar: true,
			content: "${webroot}user/viewAccess.html?uId="+id,
			btn: ['<i class="fa fa-remove"></i>关闭'],
			yes : function(index, layero) {
				var iframeWin = top.window[layero.find('iframe')[0]['name']];
				iframeWin.closeFrame();
			}
		});
	}
	</shiro:hasPermission>
	
	function getButton(){
  		var status=$("#userStatus").val();
  		var addBtn='<shiro:hasPermission name="/user/addUser.html"><a class="btn btn-success  btn-sm" onclick="addUser()" ><i class="fa fa-plus">新增</i></a></shiro:hasPermission>';	                   
		var passBtn='<shiro:hasPermission name="/user/changeUser.do"><a class="btn btn-success btn-sm" onclick="operateUser(1)" ><i class="fa fa-check-circle">开通</i></a></shiro:hasPermission>';
		var stopBtn='<shiro:hasPermission name="/user/changeUser.do"><a class="btn btn-danger btn-sm" onclick="operateUser(3)" ><i class="fa fa-ban">停用</i></a></shiro:hasPermission>';
		var restoreBtn='<shiro:hasPermission name="/user/changeUser.do"><a class="btn btn-success btn-sm" onclick="operateUser(2)" ><i class="fa fa-undo">恢复</i></a></shiro:hasPermission>';
		var delBtn='<shiro:hasPermission name="/user/delUser.do"><a class="btn btn-danger btn-sm" onclick="delUser()" ><i class="fa fa-trash-o">删除</i></a></shiro:hasPermission>';
		var resetBtn='<shiro:hasPermission name="/user/resetPassword.do"><a class="btn btn-info btn-sm" onclick="resetPass()" ><i class="fa fa-key">重置密码</i></a></shiro:hasPermission>';
		/* var exportBtn='<a class="btn btn-warning btn-sm" id="btnExport"><i class="fa fa-download"></i><span>导出</span></a>'; */
  		var button;
  		if(!status){
  			$("#optional").html("");
  			$("#optional").append(addBtn);
  			$("#optional").append(delBtn);
  			$("#optional").append(resetBtn);
  			/* $("#optional").append(exportBtn); */
  		}else if(status=="01"){
  			$("#optional").html("");
  			$("#optional").append(stopBtn);
  			$("#optional").append(delBtn);
  			$("#optional").append(resetBtn);
  			/* $("#optional").append(exportBtn); */
  		}else if(status=="02"){
  			$("#optional").html("");
  			$("#optional").append(passBtn);
  			$("#optional").append(stopBtn);
  			$("#optional").append(delBtn);
  			$("#optional").append(resetBtn);
  			/* $("#optional").append(exportBtn); */
  		}else if(status=="03"){
  			$("#optional").html("");
  			$("#optional").append(passBtn);
  			$("#optional").append(delBtn);
  			$("#optional").append(resetBtn);
  			/* $("#optional").append(exportBtn); */
  		}else if(status=="04"){
  			$("#optional").html("");
  			$("#optional").append(restoreBtn);
  			$("#optional").append(delBtn);
  			$("#optional").append(resetBtn);
  			/* $("#optional").append(exportBtn); */
  		}
  	};
	
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
	
	<shiro:hasPermission name="/user/addUser.html">
	//添加用户
	function addUser(){
		var layer = layui.layer;
		var index=layer.open({
		  type: 2,
		  title:"添加用户",
		  area: ['700px'],
		  scrollbar: false,
		  content: ["${webroot}user/addUser.html","no"],
		  //关闭层后的回调函数
		  end:function(){
			  
		  }
		});
		layer.full(index);
	}
	</shiro:hasPermission>
	
	//查看用户
	function viewUser(id){
		var layer = layui.layer;
		//iframe层-父子操作
		var index=layer.open({
		  type: 2,
		  title:"查看用户",
		  //skin: 'v-page-class',
		  area: ['600px','450px'],
		  maxmin:true,
		  scrollbar: false,
		  content: ["${webroot}user/viewUser.html?vid="+id]
		 /*  btn: ['<i class="fa fa-remove"></i>关闭'],
		  closeBtn:1 */
		});
	}
	
	<shiro:hasPermission name="/user/editUser.html">
	//编辑用户
	function editUser(id){
		var layer = layui.layer;
		var index=layer.open({
		  type: 2,
		  title:"编辑用户",
		  area: ['600px','450px'],
		  maxmin:true,
		  scrollbar: false,
		  content: ["${webroot}user/editUser.html?vid="+id,"no"],
		  //关闭层后的回调函数
		  end:function(){
			  
		  }
		});
	}
	</shiro:hasPermission>
	
	<shiro:hasPermission name="/user/delUser.do">
	//删除用户
	function delUser(){
		var layer = layui.layer;
		var ids="";
		$("#pageData input:checked").each(function(){ 
			ids+=$(this).val()+","; 
		}); 
		ids=ids.substring(0,ids.length-1);
		if(!ids){
			layer.msg('请选择要删除的用户！',{time:1000});
			return;
		}else{
			layer.confirm('确定要删除选中的用户吗？', {icon: 3, title:'系统提示',
			  btn: ['确定','取消'] //按钮
			}, function(){
				var url="${webroot}user/delUser.do";
				params={"ids":ids};
				postData(url, params, true,userResult); 
			  	
			}, function(){
			  
			});
		}
	}
	</shiro:hasPermission>
	
	
	/**
	* 访问控制
	*/
	function setChannel(id){
		var layer = layui.layer;
		layer.open({
			type: 2,
			title:"访问控制",
			skin: 'edit-class', 
			area: ['300px', '450px'],
			maxmin: true,
			scrollbar: true,
			content: "${webroot}user/selectChannel.html?rId="+id,
			btn: ['<i class="fa fa-floppy-o"></i>保存','<i class="fa fa-remove"></i>关闭'],
			yes : function(index, layero) {
				var iframeWin = top.window[layero.find('iframe')[0]['name']]; 
				iframeWin.addCheckedChannels();
			},
			btn2 : function(index, layero) {
				var iframeWin = top.window[layero.find('iframe')[0]['name']]; 
				iframeWin.closeFrame();
			}
		});
	}
	
	
	<shiro:hasPermission name="/user/changeUser.do">
	//操作账户
	function operateUser(type){
		var layer = layui.layer;
		var ids="";
		$("#pageData input:checked").each(function(){ 
			ids+=$(this).val()+","; 
		}); 
		ids=ids.substring(0,ids.length-1);
		if(!ids){
			layer.msg('请选择要操作的账户！',{time:1000});
			return false;
		}else{
			layer.confirm('确定要操作该账户吗？', {
			  btn: ['确定','取消'] //按钮
			}, function(){
				var url="${webroot}user/changeUser.do?type="+type;
				params={"ids":ids};
				postData(url, params, true,userResult); 
			  	
			}, function(){
			  
			});
		}
	}
	</shiro:hasPermission>
	
	<shiro:hasPermission name="/user/resetPassword.do">
	//重置密码
	function resetPass(){
		var layer = layui.layer;
		var ids="";
		$("#pageData input:checked").each(function(){ 
			ids+=$(this).val()+","; 
		}); 
		ids=ids.substring(0,ids.length-1);
		if(!ids){
			layer.msg('请选择要重置密码的用户！',{time:1000});
			return;
		}else{
			layer.confirm('确定要重置指定用户密码吗？', {
			  btn: ['确定','取消'] //按钮
			}, function(){
				var url="${webroot}user/resetPassword.do";
				params={"ids":ids};
				postData(url, params, true,userResult); 
			  	
			}, function(){
			  
			});
		}
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