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
	
	<div style="padding: 10px;" class="form-inline">
		<div class="panel panel-default">
		  <!-- <div class="panel-heading">角色信息</div> -->
		  <!-- <div class="panel-body"> -->
			  <form method="post"  enctype="multipart/form-data" action="${webroot}record/upload.do"  id="searchForm"  >
			  <input type="hidden" id="trainId" name="trainId" value="${trainId}">
			  <table class="table" style="margin:0">
			  		<tbody>
					<tr style="background-color: #f9f9f9;">
						<td width=50% style="text-align: center;">
							
							<input id="upfile" type="file" name="filename" style="width:290px">
							
						</td>
						<td width=50% style="text-align: center;">
							<a id="sub" class="btn btn-success btn-sm"><i class="fa fa-upload" aria-hidden="true"></i>导入</a>
						</td>
					</tr>
					</tbody>
				</table>
			</form>	
		  <!-- </div> -->
		
		</div>
		
		<!-- <div style="border-bottom: 2px solid #f5f5f5;"></div> -->
		<div  style="padding-top: 5px;">
         </div>
		
		<table id="trRecordList" class="table table-bordered table-hover table-striped">
		                        <thead>
		                        <tr role="row">
		                            <th width="10%" style="text-align:center">姓名</th>
		                            <th width="10%" style="text-align:center">性别</th>
		                            <th width="15%" style="text-align:center">联系方式</th>
		                            <th width="35%" style="text-align:center">身份证号</th>
		                        </tr>
		                        </thead>
		                        <tbody class="middle-align" id="pageData"></tbody>
		                    </table>
		                    <div class="row" style="display: none;" id="pageInfo">
		                    </div>
		                    <div class="panel-disabled">
			                    <div class="loader-1"></div>
			                </div>
		                    <div style="margin-top: -20px; line-height: 50px;border: 1px solid #ddd; border-top: 0;" id="messageDiv">
		                        <p class="text-center">暂无数据</p>
		                    </div>
		                    <script id="templateData" type="text/html">
									{{#  layui.each(d.volList, function(index, item){ }}	
			                        <tr role="row"
			                            data-toggle="collapse">
			                            <td>
			                                <div style="text-align:center">
			                                   <span>{{item.userName}}</span>
			                                </div>
			                            </td>
										<td>
			                                <div style="text-align:center">
			                                   <span>{{item.sex}}</span>
			                                </div>
			                            </td>
			                             <td style="text-align:center">
			                                <div>
			                                   <span>{{item.mobile }}</span>
			                                </div>
			                            </td>
			                             
			                             <td align="center">
			                                <div>
			                                   <span>{{item.idcard }}</span>
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
	<script src="${sr}js/jquery.form.js" type="text/javascript"></script>
	<script src="${sr}layui.js" type="text/javascript"></script>
	<script src="${sr}js/iCheck/icheck.min.js?v=1.0.2" type="text/javascript"></script>
	<script src="${sr}js/iCheck/custom.min.js?v=1.0.2" type="text/javascript"></script>
	
<script type="text/javascript">
	layui.use(['jquery','layer','laytpl'], function(){
		
		
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
		
		$("#sub").click(function(){
			if(checkData()){
				 $('#searchForm').ajaxSubmit({
					 type:"post",
					 enctype:"multipart/form-data",
					 dataType:"json",
					 success:function(data){
						 
						 var laytpl = layui.laytpl;
							var layer = layui.layer;
							if(data.success){
								var list = data.volList;
								if(list && list.length > 0){
									$("#messageDiv").hide();
									var templateHtml = $("#templateData").html();
					        		laytpl(templateHtml).render(data, function(result){
					        		    $("#pageData").html(result);
					        		  });
								}else{
									$("#pageData").html("");
									$("#messageDiv").show();
								}
							}else{
								var message = data.message;
								layer.msg(message);
							}
					 },
					 error:function(msg){
						 
					 }
				 });
			}
		});
	  	
	});
	
	function closeFrame(){
		 var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
		parent.layer.close(index);
	}
	
	//JS校验form表单信息    
	function checkData(){   
	   var layer = layui.layer;
	   var fileDir = $("#upfile").val();    
	   var suffix = fileDir.substr(fileDir.lastIndexOf("."));    
	   if("" == fileDir){ 
		   layer.msg('选择需要导入的Excel文件！',{time:1000});
	       return false;    
	   }    
	   if(".xls" != suffix && ".xlsx" != suffix ){ 
		   layer.msg('选择Excel格式的文件导入！',{time:1000});
	       return false;    
	   }    
	   return true;    
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
	
	function addVols(){
		var trainId = $("#trainId").val();
		var url="${webroot}record/upLoadVols.do";
		params={"trainId":trainId};
		postData(url, params, true,upLoadVolsResult);
// 		var layer = layui.layer;
// 		if(!ids){
// 			layer.msg('请选择要分配的志愿者！',{time:1000});
// 			return false;
// 		}else{
// 				var url="${webroot}record/upLoadVols.do";
// 				params={};
// 				postData(url, params, true,volTrainResult); 
			  	
// 		}
	}
	
	/**
	**回调函数
	*/
	function upLoadVolsResult(data){
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
	**回调函数
	*/
	function searchResult(data){
		$(".panel-disabled").hide();
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
	
	function loadPage(){
		$("#currentPageInput").val(1);
		postFormData("searchForm",true,searchResult);
	}
	
	function createPageInfo(page,searchFormId){
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
	
</script>
</body>
</html>