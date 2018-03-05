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
    </style>
</head>
<body class="v-page-body">
		<div class="panel" style="margin-bottom: 0;padding-top: 10px;" id="mainform" >
			
				<div class="row mn5">
					<table class="table table-bordered dataTable no-footer">
						<tr>
							<td class="col-xs-1">
						    	<label >姓名</label>
						    </td>
						    <td class="col-xs-2">
						    	${volunteerView.realName}
						    </td>
						    <td class="col-xs-1">
						    	<label >证件号</label>
						    </td>
						    <td class="col-xs-2">
						    	${volunteerView.idcard}
						    </td>
						    <td class="col-xs-2" rowspan="4" style="" align="center">
						    	 <span id="content" style='${!empty volunteerView.imgUrl?"display:none":""}'><img width="164" height="150" border="0" src="${sr}images/photo.png" style="margin-bottom: 5px ;"></span>
								<img id="pic" alt="志愿者头像" width="164" height="150" border="0" src="" style="margin-bottom: 5px ;${empty volunteerView.imgUrl?'display:none':''}" >
						    </td>
					    </tr>
				    	<tr>
							<td class="col-xs-1">
						    	<label >性别</label>
						    </td>
						    <td class="col-xs-2">
						    	${volunteerView.sex}
						    </td>
						    <td class="col-xs-1">
						    	<label >出生年月</label>
						    </td>
						    <td class="col-xs-2">
						    	<fmt:formatDate value="${volunteerView.birthday}" pattern="yyyy-MM-dd"/>
						    </td>
				    	 </tr>
				    	 <tr>
							<td class="col-xs-1">
						    	<label >所属服务队</label>
						    </td>
						    <td class="col-xs-2">
						    	${teamName}
						    </td>
						    <td class="col-xs-1">
						    	<label >注册时间</label>
						    </td>
						    <td class="col-xs-2">
						    	<fmt:formatDate value="${volunteerView.regTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
						    </td>
				    	 </tr>
				    	 <tr>
							<td class="col-xs-1" >
						    	<label >服务时长</label>
						    </td>
						    <td class="col-xs-2">
						    	${empty volunteerView.serviceHour?0:volunteerView.serviceHour}小时
						    </td>
						    <td class="col-xs-1">
						    	<label >发证时间</label>
						    </td>
						    <td class="col-xs-2">
						    	<fmt:formatDate value="${volunteerView.certDate}" pattern="yyyy-MM-dd"/>
						    </td>
				    	 </tr>
					</table>
				</div>
				<div class="row">
					<table class="table table-bordered dataTable no-footer">
						<tr>
							<td style="text-align:center;background-color:rgba(248, 248, 248, 1)" colspan="5">
						       	年度注册登记信息
						     </td>
			    	 	</tr>
						<tr >
							<th style="text-align: center;">序号</th>
							<th style="text-align: center;">审核年份</th>
							<th style="text-align: center;">审核时间</th>
							<th style="text-align: center;">审核人</th>
							<th style="text-align: center;">审核状态</th>
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
			 							<tr role="row"
			                            data-toggle="collapse">
			                            <td style="text-align:center">{{(d.pageSize * (d.pageNum-1))+index+1}}</td>
			                            <td align="center">
			                                <div>
			                                   <span >{{item.checkYear}}</span>
			                                </div>
			                            </td>
			                             <td align="center">
			                                <div>
			                                   <span >{{item.opertTime==null?"":getFormatDateByLong(item.opertTime,"yyyy-MM-dd")}}</span>
			                                </div>
			                            </td>
			                             <td align="center">
			                                <div>
			                                   <span >{{item.opertUser }}</span>
			                                </div>
			                            </td>
										<td align="center">
			                                <div>
			                                   <span>{{item.certCheckState}}</span>
			                                </div>
			                            </td>
			                        </tr>
								{{#  }); }}                       
					</script>
				</div>
				<form id="certCheckForm" action="${webroot}cert/certChecklist.do" method="post" >
					<input type="hidden" name="certId" value="${volunteerView.certId}">
					<input type="hidden" name="page" id="currentPageInput" value="1"/>
				</form>
		</div>
	<script src="${sr}js/jquery.min.js" type="text/javascript"></script>
	<script src="${sr}js/config.js" type="text/javascript"></script>
	<script src="${sr}js/baseutil.js" type="text/javascript"></script>
	<script src="${sr}js/jquery.form-validator.min.js" type="text/javascript"></script>
	<script src="${sr}js/jquery-ui.min.js" type="text/javascript"></script>
	<script src="${sr}layui.js" type="text/javascript"></script>
	<script src="${sr}js/select2.js" type="text/javascript"></script>
	
	<script type="text/javascript">
	layui.use(['jquery','layer','laytpl'], function(){
		$('#pic').attr('src', '${webroot}file/readPic.do?ataUrl='+encodeURIComponent("${volunteerView.imgUrl}"));
		postFormData("certCheckForm",true,searchResult);
	})
	
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
        		var pageHtml = createCertCheckPage(page, "certCheckForm");
        		
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
	
	function createCertCheckPage(page,searchFormId){
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
	
	</script>
</body>
</html>