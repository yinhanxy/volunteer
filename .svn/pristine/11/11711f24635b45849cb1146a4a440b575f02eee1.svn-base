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
	<link href="${sr}ztree/metroStyle/style.css" rel="stylesheet">
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
    </style>
    
</head>
<body >

	<div style="padding: 20px;width: 100%;float:center;" class="form-inline">
		
		<div  style="padding-top: 10px;float:right;">
		                    
		    <form class="form-inline" id="searchUsersForm"  action="${webroot}serTeam/showUsers.do" style="margin-bottom: 10px;" onkeydown="if(event.keyCode==13){return false;}">
				<input type="hidden" id="orgId" name="orgId" >
				<input type="hidden" id="teamId" name="teamId" value="${teamId}">
				<span><input type="text" name="selectName" id="selectName" class="form-control"></span>
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
				<input type="hidden" name="rows" id="pageSizeInput"/>
			</form>                
         </div>
		
		<table id="roleUsers" class="table table-striped table-bordered table-hover table-condensed ">
			<thead>
				<tr >
					<th width="40" style="text-align:center">
						<input type="checkbox" class="ace" id="allcheck" value="">
					</th>
					<th width="50" >序号</th>
					<th >用户名</th>
					<th >真实姓名</th>
					<th >注册时间</th>
				</tr>
			</thead>
			
			<tbody id="pageData">
				
			</tbody>
		</table>
		<div class="row" style="display: none;" id="pageInfo"></div>
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
											<input type="checkbox" class="ace" value="{{item.id}}" {{item.teamIdList==""?"":"name='isOwn'"}}>
										</td>
			                            <td style="text-align:center">{{(d.pageSize * (d.pageNum-1))+index+1}}</td>
			                            <td>
			                                <div>
			                                   <span>{{item.userName}}</span>
			                                </div>
			                            </td>
			                             <td>
			                                <div>
			                                   <span>{{item.realName }}</span>
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
	
	  			
	<script src="${sr}js/jquery.min.js" type="text/javascript"></script>
	<script src="${sr}js/config.js" type="text/javascript"></script>
	<script src="${sr}js/baseutil.js" type="text/javascript"></script>
	<script src="${sr}js/jquery-ui.min.js" type="text/javascript"></script>
	<script src="${sr}layui.js" type="text/javascript"></script>
	<script src="${sr}js/iCheck/icheck.min.js?v=1.0.2" type="text/javascript"></script>
	<script src="${sr}js/iCheck/custom.min.js?v=1.0.2" type="text/javascript"></script>
	<script src="${sr}ztree/jquery.ztree.core.js" type="text/javascript"></script>
<script type="text/javascript">
	layui.use(['jquery','layer','laytpl'], function(){
		getOrgs();
		postFormData("searchUsersForm",true,searchResult);
	  	//查询按钮
	  	$("#searchBtn").click(function(){
			$("#currentPageInput").val(1);
	  		postFormData("searchUsersForm",true,searchResult);
	  	});
	  	
	  	$(document).ajaxComplete( function(event, jqXHR, options){
			$('input').iCheck({
				  checkboxClass: 'icheckbox_square-blue',
				  radioClass: 'iradio_square-blue',
				  increaseArea: '20%' // optional
			});
			$("input[name='isOwn']").iCheck("check");
			$("input[name='isOwn']").iCheck("disable");
			
			var i=0;
			$("#allcheck").on("ifClicked",function () {
				var flag=(i++)%2==0?"check":"uncheck";
				$("#pageData  input[name!='isOwn']").each(function(){
				 	 $(this).iCheck(flag);  
				}); 
		    });
		});
	  	
	});
	
	function getOrgs(){
		var url="${webroot}org/loadOrg.do";
		var params={"id":1};
		postData(url, params, true, orgTreeResult);
	}
	function orgTreeResult(data){
		if(data && data.success){
			var orgs=data.orgViews;
			console.log(data);
			if(orgs && orgs.length>0){
				var nodes=[];
				build(orgs,nodes);
				$.fn.zTree.init($("#tree"),{
					data: {
						simpleData: {
							enable: true,
							idKey: "id",
							pIdKey: "pId",
							rootPId: 0
						}
					},
					callback: {
						onClick: clickOrg
					},
					view:{
						selectedMulti:false
					},
					async:{
						enable:true,
						autoParam: ["id"],
						contentType:"application/x-www-form-urlencoded",
						dataFilter: ajaxDataFilter,
						dataType: "text",
						url: "${webroot}/org/loadOrg.do"
					}
				}, nodes);
			}
		}
	}
	
	function ajaxDataFilter(treeId, parentNode, responseData){
		var data=responseData;
		if(data && data.success){
			var orgs=data.orgViews;
			orgs.shift();
			if(orgs && orgs.length>0){
				var nodes=[];
				build(orgs,nodes);
				return nodes;
			}
		}	
	}
	
	function build(data,nodes){
		for (var i = 0; i < data.length; i++) {
			var idValue=data[i].id;
			var pIdValue=data[i].pId;
			var nameValue=data[i].name;
			if(data[i].open){
				var openValue=data[i].open;
			}
			if(data[i].parent){
				var parentValue=data[i].parent;
			}
			var node={ id:idValue, pId:pIdValue, name:nameValue, open:openValue,isParent:parentValue};
			nodes.push(node);
		}
	}
	function clickOrg(event, treeId, treeNode){
		$("#orgId").val(treeNode.id);
		$("#selectName").val("");
		$("#currentPageInput").val(1);
		$("#selectType").val(1);
  		postFormData("searchUsersForm",true,searchResult);
		
	}
	
	function closeFrame(){
		 var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
		parent.layer.close(index);
	}
	
	function addUsersUnderTeam(){
		var layer = layui.layer;
		var teamId=$("#teamId").val();
		var ids="";
		$("#pageData input:checked[name!='isOwn']").each(function(){ 
			ids+=$(this).val()+","; 
		}); 
		ids=ids.substring(0,ids.length-1);
		if(!ids){
			layer.msg('请选择要分配的用户！',{time:1000});
			return false;
		}else{
				var url="${webroot}serviceteam/addTeamUser.do";
				params={"ids":ids,"teamId":teamId};
				postData(url, params, true,userTeamResult); 
			  	
		}
	}
	
	/**
	**回调函数
	*/
	function userTeamResult(data){
		/* var laytpl = layui.laytpl;
		var layer = layui.layer;
		 var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
		 */
		var message=data.message;
		if(data && data.success){
			//parent.layer.close(index);
		}else{
			layer.alert(message, {icon: 2});
		}
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
        		var pageHtml = createRoleUserPage(page, "searchUsersForm");
        		
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
				    }else if(currentPage >=showPageNum/2 &&currentPage <end-showPageNum/2){
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
	
	function loadPage(){
		$("#selectName").val("");
		$("#currentPageInput").val(1);
		$("#selectType").val(1);
		$("#orgId").val(0);
		postFormData("searchUsersForm",true,searchResult);
	}
</script>
</body>
</html>