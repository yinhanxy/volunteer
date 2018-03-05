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
    </style>
    
</head>
<body >
	<div style="padding: 20px;" class="form-inline" >
		
		<div  style="padding-top: 10px;float:right;">
		                    
		    <form class="form-inline" id="recruitActivityListForm" action="${webroot}activity/recruitActivityList.do" style="margin-bottom: 10px;" onsubmit="return false">
				<input type="hidden" id="orgId" name="orgId" value="${orgId}">
				<span>
					<label >活动名称</label>
					<!-- <select class="form-control" name="selectType" id="selectType">
						<option value="1">姓名</option>
						<option value="2">证件号</option>
					</select> -->
				</span>
				<span><input type="text" id="activityName" name="activityName" class="form-control"></span>
				<!-- <span>
					<label >所属服务队：</label>
					<input type="text" name="serviceTeam" class="form-control" id="serviceTeam" style="width:30%;">
				</span> -->
				<span>
					<a class="btn btn-success btn-sm" id="searchBtn" style="float: none;margin-bottom: 2px;">
				        <i class="fa fa-search">查询</i>
				    </a>
				</span>   
				<input type="hidden" name="page" id="currentPageInput" value="1"/>
			</form>                
         </div>
		
		<table id="orgUsers" class="table table-striped table-bordered table-hover table-condensed ">
			<thead>
				<tr >
					<th width="50"></th>
					<th width="50" >序号</th>
					<th >活动名称</th>
					<th >发布人</th>
					<th >联系人</th>
					<th >联系方式</th>
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
											<input type="radio" class="ace" value="{{item.id}}" name="radio">
										</td>
			                            <td style="text-align:center">{{(d.pageSize * (d.pageNum-1))+index+1}}</td>
			                            <td>
			                                <div>
			                                   <span name="name">{{item.name}}</span>
			                                </div>
			                            </td>
			                             <td>
			                                <div>
			                                   <span name="publisher">{{item.publisher}}</span>
			                                </div>
			                            </td>
			                             <td >
			                                <div>
			                                   <span name="contactPerson">{{item.contactPerson }}</span>
			                                </div>
			                            </td>
										<td >
			                                <div>
			                                   <span name="contact">{{item.contact }}</span>
			                                </div>
			                            </td>
										<input type="hidden" name="activityClass" value="{{item.activityClass}}"/>
										<input type="hidden" name="recruitNum" value="{{item.recruitNum}}"/>
										<input type="hidden" name="address" value="{{item.address}}"/>
										<input type="hidden" name="hours" value="{{item.hours}}"/>
										<input type="hidden" name="summary" value="{{item.summary}}"/>
										<input type="hidden" name="recruitRange" value="{{item.recruitRange}}"/>
										<input type="hidden" name="requirements" value="{{item.requirements}}"/>
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
<script type="text/javascript">
	layui.use(['jquery','layer','laytpl'], function(){
		
		postFormData("recruitActivityListForm",true,searchResult);
	  	//查询按钮
	  	$("#searchBtn").click(function(){
			$("#currentPageInput").val(1);
	  		postFormData("recruitActivityListForm",true,searchResult);
	  	});
	  	
	  	$(document).ajaxComplete( function(event, jqXHR, options){
			$('input').iCheck({
				  checkboxClass: 'icheckbox_square-blue',
				  radioClass: 'iradio_square-blue',
				  increaseArea: '20%' 
			});
			
		});
	  	
	});
	
	
	function closeFrame(){
		var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
		parent.layer.close(index);
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
		console.log(data);
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
        		var pageHtml = createrecruitActivityPage(page, "recruitActivityListForm");
        		
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
	
	function createrecruitActivityPage(page,searchFormId){
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
	
	function selectActivity(){
		var layer = layui.layer;
		var activityId="";
		var name;
		var contact;
		var contactPerson;
		var summary;
		var activityClass;
		var recruitNum;
		var address;
		var hours;
		var recruitRange;
		var requirements;
		var serId;
		var selected=$("#pageData input[name='radio']:checked");
		if(selected && selected.length>0){
			activityId=selected.val(); 
			var parentTr=selected.parents("tr");
			name=parentTr.find("span[name='name']").html();
			contact=parentTr.find("span[name='contact']").html();
			contactPerson=parentTr.find("span[name='contactPerson']").html();
			activityClass=parentTr.find("input[name='activityClass']").val();
			recruitNum=parentTr.find("input[name='recruitNum']").val();
			address=parentTr.find("input[name='address']").val();
			hours=parentTr.find("input[name='hours']").val();
			recruitRange=parentTr.find("input[name='recruitRange']").val();
			summary=parentTr.find("input[name='summary']").val();
			requirements=parentTr.find("input[name='requirements']").val();
		}
		if(!activityId){
			layer.msg('请选择要招募的活动！',{time:1000});
			return false;
		}else{
			var data={"activityId":activityId,"name":name,"contact":contact,"contactPerson":contactPerson,"activityClass":activityClass,"recruitNum":recruitNum,"address":address,"hours":hours,"recruitRange":recruitRange,"summary":summary,"requirements":requirements};
			return data;
		}
	}
	
</script>
</body>
</html>
