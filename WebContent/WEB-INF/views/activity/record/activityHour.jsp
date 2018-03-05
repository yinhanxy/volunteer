<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page import="com.topstar.volunteer.common.AlternativeUtil"%>
<%@ taglib prefix="tps" uri="/pageTag"%>
<c:set var="webroot" value="${pageContext.request.contextPath}/" />
<%-- 静态资源绝对地址 --%>
<c:set var="sr" value="${pageContext.request.contextPath}/static/"/>
<!DOCTYPE html>
<html lang="zh-CN">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,Chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    
    <title>湖北省文化志愿者管理系统</title>
	
	<link href="${sr}css/font-awesome/css/linecons.css" rel="stylesheet">
	<link href="${sr}css/font-awesome/css/font-awesome.min.css" rel="stylesheet">
    <link href="${sr}css/bootstrap.min.css" rel="stylesheet">
	<link href="${sr}css/xenon.min.css" rel="stylesheet">
	<link href="${sr}css/base.css" rel="stylesheet">
	<link href="${sr}css/new.css" rel="stylesheet">
	<link href="${sr}css/theme-default.min.css" rel="stylesheet">
	<link href="${sr}css/star-rating.min.css" rel="stylesheet">
	<%-- <link href="${sr}css/xenon-forms.css" rel="stylesheet"> --%>
    <!--[if lt IE 9]>
      <script src="${sr}js/html5shiv.min.js"></script>
      <script src="${sr}js/respond.min.js"></script>
    <![endif]-->
    <style type="text/css">
    	label {
    		display: inline;
    	}
    	.checkbox >label{
    		padding-right: 10px
    	}
    	#photo {
		    border-width: 0px;
		    //position: absolute;
		    left: 0px;
		    top: 0px;
		    padding-bottom:5px;
		    width: 123px;
		    height: 150px;
		}
		.glyphicon-star-empty:before {
   			 content: "\e005";
		}
		.glyphicon-star:before {
    		content: "\e005";
		}
		.rating-container .empty-stars {
		    color: #e4e4e4;
		}
		.rating-container .filled-stars {
		    position: absolute;
		    left: 0;
		    top: 0;
		    margin: auto;
		    color: #fd888d;
		    white-space: nowrap;
		    overflow: hidden;
		    -webkit-text-stroke: 1px #777;
		    text-shadow: 1px 1px rgba(238, 238, 238, 0);
		}
    </style>
</head>
<body class="v-page-body">
		<div class="panel" style="margin-bottom: 0;padding-top: 10px;" id="mainform">
			
				<div class="row">
					<table class="table table-bordered dataTable no-footer">
						<tr>
							<td class="col-xs-1">
						    	<label >活动名称</label>
						    </td>
						    <td class="col-xs-2">
						    	${activity.name}
						    </td>
						    <td class="col-xs-1">
						    	<label >服务时长</label>
						    </td>
						    <td class="col-xs-2" >
						    	${activity.hours}小时
						    </td>
					    </tr>
				    	<tr>
							<td class="col-xs-1">
						    	<label >活动简介</label>
						    </td>
						    <td class="col-xs-5" colspan="3">
								${activity.summary}
						    </td>
				    	 </tr>
				    	 <tr>
							<td class="col-xs-1">
						    	<label >活动开始时间</label>
						    </td>
						    <td class="col-xs-2">
						    	<fmt:formatDate value="${activity.activitySt}" pattern="yyyy-MM-dd HH:mm:ss"/>
						    </td>
						    <td class="col-xs-1">
						    	<label >活动结束时间</label>
						    </td>
						    <td class="col-xs-2">
						    	<fmt:formatDate value="${activity.activityEt}" pattern="yyyy-MM-dd HH:mm:ss"/>
						    </td>
				    	 </tr>
					</table>
				</div>
				
				<div class="row">
					志愿者列表
					<table class="table table-bordered dataTable no-footer">
						<tr >
							<td style="text-align: center;">序号</td>
							<td style="text-align: center;">姓名</td>
							<td style="text-align: center;width:7%">性别</td>
							<td style="text-align: center;">联系方式</td>
							<td style="text-align: center;width:12%;">签到状态</td>
							<td style="text-align: center;width:22%">服务评价</td>
							<td style="text-align: center;">服务时长</td>
						</tr>
						<tbody class="middle-align" id="pageData">
		                        	
                        </tbody>
					</table>
					<div class="row" style="display: none;" id="pageInfo"></div>
					<div style="margin-top: -20px; line-height: 50px;border: 1px solid #ddd; border-top: 0;display: none;" id="messageDiv">
		                        <p class="text-center">暂无数据</p>
		            </div>
		            <script id="templateData" type="text/html">
							{{#  layui.each(d.list, function(index, item){ }}
										<input type="hidden" name="avId" value="{{item.activityVolunteerId}}">
										<input type="hidden" name="vId" value="{{item.id}}">	
			 							<tr 
			                            data-toggle="collapse">
			                            <td style="text-align:center">{{(d.pageSize * (d.pageNum-1))+index+1}}</td>
			                            <td align="center">
			                                <div>
			                                   <span >{{item.realName}}</span>
			                                </div>
			                            </td>
			                             <td align="center"> 
			                                <div>
			                                   <span >{{item.sex}}</span>
			                                </div>
			                            </td>
			                             <td align="center">
			                                <div>
			                                   <span >{{item.mobile }}</span>
			                                </div>
			                            </td>
										<td align="center">
			                                <div>
													<select class="form-control" name="checkStatus">
														<option value="1" {{item.checkStatus==1?"selected":"" }}>已签到</option>
														<option value="2" {{item.checkStatus!=1?"selected":"" }}>未签到</option>
	           										</select>
			                                </div>
			                            </td>
										<td align="center" style="padding-bottom: 1px;">
			                               <input name="serviceStar" type="text" title="" value="{{item.activityServiceStar}}" />
			                            </td>
										<td align="center">
			                                <div>
												<input type="text"  class="form-control" name="serHours" value="{{item.activityServiceHours }}"/>
			                                </div>
			                            </td>
			                        </tr>
								{{#  }); }}                       
					</script>
				</div>
				<form id="activityHoursForm" action="${webroot}activity/activityHours.do" method="post">
					<input type="hidden" name="aId" id="aId" value="${activity.id}">
					<input type="hidden" name="page" id="currentPageInput" value="1"/>
				</form>
		</div>
		<div class="v-form-footer">
		  			<button type="button" id="save" class="btn btn-sm btn-success"><i class="fa fa-save"></i>保存</button>
		  			<button type="button" id="cancel" class="btn btn-sm" onclick="closeFrame();"><i class="fa fa-remove"></i>取消</button>
		</div>
	<script src="${sr}js/jquery.min.js" type="text/javascript"></script>
	<script src="${sr}js/config.js" type="text/javascript"></script>
	<script src="${sr}js/baseutil.js" type="text/javascript"></script>
	<script src="${sr}js/jquery.form-validator.min.js" type="text/javascript"></script>
	<script src="${sr}js/jquery-ui.min.js" type="text/javascript"></script>
	<script src="${sr}layui.js" type="text/javascript"></script>
	<script src="${sr}js/select2.js" type="text/javascript"></script>
	<script src="${sr}js/star-rating.min.js" type="text/javascript"></script>
	<script type="text/javascript">
	layui.use(['jquery','layer','laytpl'], function(){
		loadPage();
		$(document).ajaxComplete( function(event, jqXHR, options){
			var $star = $("input[name='serviceStar']");
			 $star.rating({
	            min: 0,
	            max: 5,
	            step: 1,
	            size: 'xs',
	            showClear: true,
	        });
			 $star.on('rating.change', function () {
	        });
			 
		});
	})
	
	function loadPage(){
		$("#currentPageInput").val(1);
		postFormData("activityHoursForm",true,searchResult);
	}
	function getVolunteerActivityHours(){
		var ahs=new Array();
		var avIds=$("input[name='avId']");
		var vIds=$("input[name='vId']");
		var checkStatus=$("select[name='checkStatus']");
		var stars=$("input[name='serviceStar']");
		var hours=$("input[name='serHours']");
		if(vIds && checkStatus && stars && hours){
			if(vIds.length){
				for (var i = 0; i < vIds.length; i++) {
					var ah=new Object();
					ah.id=$(avIds[i]).val();
					ah.activityId=$("#aId").val();
					ah.volunteerId=$(vIds[i]).val();
					ah.checkStatus=$(checkStatus[i]).val();
					ah.serviceStar=$(stars[i]).val()?$(stars[i]).val():0;
					ah.serHours=$(hours[i]).val();
					ahs.push(ah);
				}
			}
		}
		return ahs;
	}
	
	function saveResult(data){
		var layer = layui.layer;
		var message;
		if(data && data.success){
			message=data.message;
			layer.alert(message, {icon: 6},function(index){
			    closeFrame();
			  });
		}else{
			message=data.message;
			layer.alert(message, {icon: 2})
		}
	}
	
	function saveVolunteerHours(callBack){
		var activityHoursData=getVolunteerActivityHours();
		var url="${webroot}activity/saveActivityHours.do";
		params={"activityHoursData":JSON.stringify(activityHoursData)};
		postData(url, params, true,callBack);
	}
	
	/**
	*翻页
	*/
	function setCurrentPage(currentPage,searchFormId){
		layer.confirm('是否要保存当前页的志愿者服务信息？', {
			  btn: ['保存','不保存'] //按钮
			}, function(index){
				saveVolunteerHours();
				$("#currentPageInput").val(currentPage);
				postFormData(searchFormId,true,searchResult);
				layer.close(index);
			},function(index){
				$("#currentPageInput").val(currentPage);
				postFormData(searchFormId,true,searchResult);
				layer.close(index);
			});
		
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
					layer.confirm('是否要保存当前页的志愿者服务信息？', {
						  btn: ['保存','不保存'] //按钮
						}, function(index){
							saveVolunteerHours();
							$("#currentPageInput").val(currentPage);
							postFormData(searchFormId,true,searchResult);
							layer.close(index);
						},function(index){
							$("#currentPageInput").val(currentPage);
							postFormData(searchFormId,true,searchResult);
							layer.close(index);
						});
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
        		var pageHtml = createActivityHoursPage(page, "activityHoursForm");
        		
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
		var $star = $('#rating-input');

		 $star.rating({
            min: 0,
            max: 5,
            step: 1,
            size: 'xs',
            showClear: true
        });
		 $star.on('rating.change', function () {
            alert($('#rating-input').val());
        });
	}
	
	function createActivityHoursPage(page,searchFormId){
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
		
				html +="<div class=\"col-sm-4\" style=\"height: 100%;\"><label style=\"line-height: 100%;text-align:center;\">";
	            html +="共"+total+"条数据";                        	
	            html +="</label>";
	            html +="</div>";
				
		        html +="<div class=\"col-sm-8 \">";
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
				html +="<li><input type=\"text\" id=\"goPageText\" style=\"width: 34px;height: 34px;margin-right: 10px;padding: 0px;text-align: center;display: inline-block;\" class=\"form-control\" /></li>";
				html +="<li><span style=\"float: right;\"  onclick=\"goPage("+pageCount+",'"+searchFormId+"')\"><a href=\"javascript:void(0)\">GO</a></span></li>";
				html +="</ul>";
				html +="</nav>";
					}
				}
				return html;
	}
	
	function closeFrame(){
		var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
		parent.layer.close(index);
	}
	
	$('#save').on('click', function() {
		saveVolunteerHours(saveResult);
	})
	</script>
</body>
</html>