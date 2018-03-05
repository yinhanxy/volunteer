<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="tps" uri="/pageTag"%>
<c:set var="webroot" value="${pageContext.request.contextPath}/" />
<%-- 静态资源绝对地址 --%>
<c:set var="sr" value="${pageContext.request.contextPath}/static/"/>
<!DOCTYPE html>
<html lang="zh-CN">
  <head>
    <meta charset="utf-8" >
    <meta http-equiv="X-UA-Compatible" content="IE=edge,Chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    
    <title>湖北省文化志愿者管理系统</title>
	
	<link href="${sr}css/font-awesome/css/linecons.css" rel="stylesheet">
	<link href="${sr}css/font-awesome/css/font-awesome.min.css" rel="stylesheet">
    <link href="${sr}css/bootstrap.min.css" rel="stylesheet">
   <%--  <link href="${sr}css/xenon.min.css" rel="stylesheet"> --%>
	<link href="${sr}css/base.css" rel="stylesheet">
	<link href="${sr}css/new.css" rel="stylesheet">
	<link href="${sr}css/iCheck/blue.css?v=1.0.2" rel="stylesheet">
    <!--[if lt IE 9]>
      <script src="${sr}js/html5shiv.min.js"></script>
      <script src="${sr}js/respond.min.js"></script>
    <![endif]-->
    <style type="text/css">
    	.table-bordered > thead > tr > th {
    		background-color: #F5F5F6;
    	}
    	body{
		   	font-family: "open sans", "Helvetica Neue", Helvetica, Arial, sans-serif;
		    font-size: 13px;
		    color: rgb(103, 106, 108);
    	}
    	.footer-right{
    		padding-top: 0;
		    height: 40px;
		    position: fixed;
		    line-height: 60px;
		    background: #fff;
		    z-index: 99;
		    bottom: 20px;
		    right: 20px;
		    float: right;
    	}
    	a.layui-layer-btn1 {
		        background-color: #DCDCDC;
    			color: #000040;
		}    
    </style>
    
</head>
<body >
	
	<div style="padding: 20px;" class="form-inline">
		<div class="panel panel-default">
		  <!-- <div class="panel-heading">角色信息</div> -->
		  <!-- <div class="panel-body"> -->
			  <table class="table" style="margin:0">
			  		<tbody>
					<tr style="background-color: #f9f9f9;">
						<td  width=10%>角色名称：</td>
						<td width=40%  id="roleName"></td>
						<td width=10%>角色类型：</td>
						<td width=40% id="roleType"></td>
					</tr>
					<tr style="background-color: #f9f9f9;">
						<td>角色描述：</td>
						<td id="roleDesc"></td>
						<td></td>
						<td></td>
					</tr>
					</tbody>
				</table>
		  <!-- </div> -->
		
		</div>
		
		<!-- <div style="border-bottom: 2px solid #f5f5f5;"></div> -->
		<div  style="padding-top: 5px;">
		                    
            <a class="btn btn-success btn-sm" onclick="addRoleUser(${roleId})" style="margin-bottom: 10px;">
                <i class="fa fa-plus">添加用户</i>
            </a>
          
            <a class="btn btn-danger btn-sm" onclick="delRoleUser()" id="del" style="margin-bottom: 10px;">
            	<i class="fa fa-trash-o">移除用户</i>
            </a>
	                    
		    <form name="searchRoleUserForm" class="form-inline" id="searchRoleUserForm" action="${webroot}role/roleUsers.do" style="margin-bottom: 10px;float: right;" method="post" onsubmit="return false">
				<input type="hidden" id="roleId" name="roleId" value="${roleId}">
				<span><input type="text" name="selectName" class="form-control"></span>
				<span>
					<select class="form-control" name="selectType" id="selectType">
						<option value="1">用户名</option>
						<option value="2">真实姓名</option>
					</select>
				</span>
				<span>
					<a class="btn btn-success btn-sm" id="searchBtn" style="float: none;margin-bottom: 2px;">
				        <i class="fa fa-search">查询</i>
				    </a>
				</span>   
				<input type="hidden" name="page" id="currentPageInput" value="1"/>
			</form>                
         </div>
		
		<table id="roleUsers" class="table table-striped table-bordered table-hover table-condensed ">
			<thead>
				<tr >
					<th width="40" style="text-align:center">
						<input type="checkbox" style="position: relative;" class="ace" id="allcheck" value="">
					</th>
					<th width="50" >序号</th>
					<th >用户名</th>
					<th width="80" >当前状态</th>
					<th >真实姓名</th>
					<th >电子邮箱</th>
					<th >注册时间</th>
				</tr>
			</thead>
			
			<tbody id="pageData">
				
			</tbody>
		</table>
		<div class="row" style="display: none;" id="pageInfo"></div>
		<!-- <div class="footer-right">
			<button type="button" id="sub" class="btn btn-sm"  onclick="closeFrame()"><i class="fa fa-remove"></i>关闭</button>
		</div>  -->          
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
			                            <td width="40" style="text-align:center">
											<input type="checkbox" class="ace" value="{{item.id}}">
										</td>
			                            <td style="text-align:center">{{(d.pageSize * (d.pageNum-1))+index+1}}</td>
			                            <td>
			                                <div>
			                                   <span>{{item.userName}}</span>
			                                </div>
			                            </td>
			                             <td >
			                                <div>
			                                   <span>{{item.userStatus }}</span>
			                                </div>
			                            </td>
			                             <td>
			                                <div>
			                                   <span>{{item.realName }}</span>
			                                </div>
			                            </td>
 										<td >
			                                <div>
			                                   <span>{{item.email }}</span>
			                                </div>
			                            </td>
			                             <td >
			                                <div>
			                                   <span>{{getFormatDateByLong(item.regTime,"yyyy-MM-dd hh:mm:ss")}}</span>
			                                </div>
			                            </td>
			                        </tr>
					
									{{#  }); }}  
				          
		</script>	
	</div>
	<div class="v-form-footer">
	   			<button type="button" class="btn btn-sm" onclick="closeFrame()"><i class="fa fa-remove"></i>关闭</button>
		</div>	
	<script src="${sr}js/jquery.min.js" type="text/javascript"></script>
	<script src="${sr}js/config.js" type="text/javascript"></script>
	<script src="${sr}js/baseutil.js" type="text/javascript"></script>
	<script src="${sr}js/jquery-ui.min.js" type="text/javascript"></script>
	<script src="${sr}layui.js" type="text/javascript"></script>
	<script src="${sr}js/iCheck/icheck.min.js?v=1.0.2" type="text/javascript"></script>
	<script src="${sr}js/iCheck/custom.min.js?v=1.0.2" type="text/javascript"></script>
	
<script type="text/javascript">
	layui.use(['jquery','layer','laytpl'], function(){
		var layer = layui.layer;
		layer.config({
			  extend: 'myskin/style.css'
			});
		
		postFormData("searchRoleUserForm",true,searchResult);
		
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

	  	//查询按钮
	  	$("#searchBtn").click(function(){
			$("#currentPageInput").val(1);
	  		postFormData("searchRoleUserForm",true,searchResult);
	  	});
	  	
	});
	
	function closeFrame(){
		 var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
		parent.layer.close(index);
	}
	
	function roleShowResult(role){
		if(role){
			var roleName=role.roleName;
			if(roleName.length>20){
				roleName=roleName.substring(0,20)+"...";
			}
			var roleDesc=role.roleDesc;
			if(roleDesc.length>20){
				roleDesc=role.roleDesc.substring(0,20)+"...";
			}
			$("#roleName").html(roleName);
			$("#roleName").attr("title",role.roleName);
			$("#roleType").html(role.roleTypeName);
			$("#roleDesc").html(roleDesc);
			$("#roleDesc").attr("title",role.roleDesc);
			$("#roleCreUser").html(role.crUser);
		}
	}
	
	//给用户添加角色
	function addRoleUser(id){
		var layer = parent.layer;
		//iframe层-父子操作
		var index=layer.open({
		  type: 2,
		  title:"选择用户",
		  skin: 'edit-class',
		  area: ['950px'],
		  scrollbar: false,
		  content: ["${webroot}role/selectRoleUser.html?rId="+id,"no"],
		  btn : ['<i class="fa fa-floppy-o">  保存</i>','<i class="fa fa-remove">  取消</i>'], 
		  yes : function(index, layero) {
					var iframeWin = top.window[layero.find('iframe')[0]['name']]; 
					iframeWin.addUsersUnderRole();
		  },
		  btn2 : function(index, layero) {
			  	var iframeWin = top.window[layero.find('iframe')[0]['name']]; 
				iframeWin.closeFrame();
	  	  },
		  end: function(){
			  loadPage();
		  }
		});
		layer.full(index);
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
    		var role=data.role;
    		roleShowResult(role);
    		var list = page.list;
    		if(list && list.length > 0){
    			var templateHtml = $("#templateData").html();
        		laytpl(templateHtml).render(page, function(result){
        		    $("#pageData").html(result);
        		  });
        		var pageHtml = createRoleUserPage(page, "searchRoleUserForm");
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
	
	function createRoleUserPage(page,searchFormId){

		var html = "";
		if(page && page.total){
			var total = page.total;
			if(total && total > 0){
				//当前页
				var currentPage = page.pageNum;
				//每页显示的条数
				var pageSize = page.pageSize;
				//总页数
				var pageCount = page.pages;
				
				$("#currentPageInput").val(currentPage);
		
		        html +="<div class=\"col-sm-8 col-sm-offset-4 \">";
		        html +="<nav aria-label=\"Page navigation\">";
		    	html +=" <ul class=\"pagination\" style=\"margin-top: 0px;margin-bottom: 0px;\">";
		    	//第一页时，禁用第一页翻页按钮
				if(currentPage == 1){
					html +="<li class=\"disabled\">"; 
					html +="<a href=\"javascript:void(0)\" aria-label=\"Previous\">";    
					html +="<span aria-hidden=\"true\">&laquo;</span>";      
					html +="</a>";
					html +="</li>";
				}else{
					html +="<li>"; 
					html +="<a href=\"javascript:void(0)\" onclick=\"setCurrentPage("+1+",'"+searchFormId+"')\" aria-label=\"Previous\">";    
					html +="<span aria-hidden=\"true\">&laquo;</span>";      
					html +="</a>";
					html +="</li>";
				}
				
				//最多显示6个页码,此参数必须为偶数
				var showPageNum = 6;
				
				var index = 1;
			    var end = pageCount;
			    if(end > showPageNum){
				    if(currentPage < showPageNum){
				        end = showPageNum;
				    }else if(currentPage >=showPageNum/2 && currentPage < end-showPageNum/2){
				    	index = currentPage - (showPageNum/2+1);
				        end = currentPage + showPageNum/2;
				    }else{
				    	index = end- showPageNum+1;
				    }
				}
				
			    for(var i = index; i <=end;i++){
			    	if( i == currentPage){
			    		html +="<li class=\"active\"><a  href=\"javascript:void(0)\">"+i+" <span class=\"sr-only\">(current)</span></a></li>";
			    	}else{
			    		html +="<li><a href=\"javascript:void(0)\" onclick=\"setCurrentPage("+i+",'"+searchFormId+"')\">"+i+"</a></li>";
			    	}
			    }
				
			    //最后一页时，禁用最后一页翻页按钮
				if(currentPage == pageCount){
					html +="<li class=\"disabled\">";
					html +="<a href=\"javascript:void(0)\" aria-label=\"Next\">";
						html +="<span aria-hidden=\"true\">&raquo;</span>";
					html +="</a>";
					html +="</li>";
				}else{
					html +="<li>";
					html +="<a href=\"javascript:void(0)\" onclick=\"setCurrentPage("+pageCount+",'"+searchFormId+"')\" aria-label=\"Next\">";
						html +="<span aria-hidden=\"true\">&raquo;</span>";
					html +="</a>";
					html +="</li>";
				}
				
				html +="<li><span style=\"color: #2C2E2F;border-top: 0;border-bottom: 0;border-right: 0;\">"+currentPage+"/"+pageCount+"</span></li>";
				html +="<li><input type=\"text\" id=\"goPageText\" style=\"width: 34px;height: 34px;margin-right: 10px;padding: 0px;text-align: center;\" class=\"form-control\" /></li>";
				html +="<li><span style=\"float: right;\"  onclick=\"goPage("+pageCount+",'"+searchFormId+"')\"><a href=\"javascript:void(0)\">GO</a></span></li>";
				html +="</ul>";
				html +="</nav>";
					}
				}
				return html;
	}
	
	//删除对应角色下的用户
	function delRoleUser(){
		
		var layer = layui.layer;
		var ids="";
		var roleId=$("#roleId").val();
		$("#pageData input:checked").each(function(){ 
			ids+=$(this).val()+","; 
		}); 
		ids=ids.substring(0,ids.length-1);
		if(!ids){
			layer.msg('请选择要移除的用户！',{time:1000});
			return false;
		}else{
			layer.confirm('确定要从该角色中移除选中的用户吗？', {icon: 3, title:'系统提示',
			  btn: ['确定','取消']
			}, function(){
				var url="${webroot}role/delRoleUsers.do";
				params={"ids":ids,"roleId":roleId};
				postData(url, params, true, delRoleUserResult); 
			  	
			});
		}
	}
	
	/**
	**回调函数
	*/
	function delRoleUserResult(data){
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
		$("#currentPageInput").val(1);
		$("selectType").val(0);
		postFormData("searchRoleUserForm",true,searchResult);
	}
	
</script>
</body>
</html>