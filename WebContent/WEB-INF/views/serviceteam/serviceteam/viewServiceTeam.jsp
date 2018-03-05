<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
	<link href="${sr}css/xenon.min.css" rel="stylesheet">
	<link href="${sr}css/base.css" rel="stylesheet">
	<link href="${sr}css/new.css" rel="stylesheet">
	<link href="${sr}css/jquery-ui.min.css" rel="stylesheet">
	<link href="${sr}css/theme-default.min.css" rel="stylesheet">
	
    <!--[if lt IE 9]>
      <script src="${sr}js/html5shiv.min.js"></script>
      <script src="${sr}js/respond.min.js"></script>
    <![endif]-->
     <style type="text/css">
    	#u1620 {
		    border-width: 0px;
		    position: absolute;
		    left: 0px;
		    top: 51px;
		    width: 100%;
		    height: 1px;
		}
		#u1620_img {
		    border-width: 0px;
		    position: absolute;
		    left: 0px;
		    top: 0px;
		    width: 100%;
		    height: 1px;
		}
 		.serTab{ 
 			margin-top: 50px; 
		} 
		.ax_default{
		    font-family: 'Helvetica Neue Regular', 'Helvetica Neue';
		    font-weight: 400;
		    font-style: normal;
		    font-size: 14px;
		    color: #428BCA;
		    text-align: center;
		    line-height: 20px;
		} 
		#u1622_1 {
		    border-width: 0px;
		    position: absolute;
		    left: 15px;
		    top: 12px;
		    width: 120px;
		    height: 40px;
		}
		#u1622_2 {
		    border-width: 0px;
		    position: absolute;
		    left: 134px;
		    top: 12px;
		    width: 120px;
		    height: 40px;
		}
		.u1623 {
		    border-width: 0px;
		    position: absolute;
		    top: 10px;
		    left:17px;
		    width: 57px;
		    white-space: nowrap;
		}
		
		.img {
		    border-width: 0px;
		    position: absolute;
		    left: 0px;
		    top: 0px;
		    width: 120px;
		    height: 41px;
		}
    </style>
</head>
<body class="v-page-body">
	<div class="page-container">
		<div class="panel">	
			<div id="u1620"  tabindex="0">
		      	<img  id="u1620_img" src="${sr}images/u1620.png" tabindex="0">
		    </div>
		    <div id="u1622_1" class="ax_default" style="cursor: pointer;" >
			    <img class="img" src="${sr}images/u13552_selected.png" >
			    <div class="u1623"  style="top:10px;color:black;">
			       <span>服务队基本信息</span>
			    </div>
		     </div>
		     <div id="u1622_2" class="ax_default" style="cursor: pointer;">
	       		 <img class="img" src="${sr}images/u1622.png" tabindex="0">
	        	<div class="u1623" >
	         	<span>服务队用户信息</span>
	        	</div>
      		</div>
					<div class="row mn5">
					<table class="table table-bordered dataTable no-footer serTab">
<!-- 						<tr> -->
<!-- 							<td class="col-xs-2" style="vertical-align: middle"><label>服务队头像</label></td> -->
<!-- 							<td class="col-xs-10" colspan="3" align="center"> -->
<%-- 								<img alt="" width="164" height="124" border="0" src="${webroot}file/readPic.do?ataUrl=${serTeam.avatarUrl}"> --%>
<!--                    			</td> -->
<!-- 						</tr> -->
						<tr>
							<td class="col-xs-2"><label>服务队名称</label></td>
							<td class="col-xs-4">
								${serTeam.name}
                   			</td>
                   			<td class="col-xs-2" rowspan="3" style="vertical-align: middle">服务队图标</td>
                   			<td class="col-xs-4" rowspan="3"  style="vertical-align: middle;text-align:center">
								<img alt="" width="175" height="124" border="0" <c:if test="${not empty serTeam.avatarUrl}">src="${webroot}file/readPic.do?ataUrl=${serTeam.avatarUrl}"</c:if>>
							</td>
						</tr>
						<tr>
                   			<td class="col-xs-2"><label>所属机构</label></td>
							<td class="col-xs-4">
								${orgName}
                   			</td>
						</tr>
						<tr>
                   			<td class="col-xs-2"><label>所属区域</label></td>
							<td class="col-xs-4">
								${areaName}
                   			</td>
						</tr>
						<tr>
							<td style="vertical-align: middle"><label>简介</label></td>
							<td colspan="3">
								<textarea id="text-area" class="form-control " readonly="readonly" rows="6"   name="summary">${serTeam.summary}</textarea>
								
                   			</td>
						</tr>
						<tr>
							<td><label>联系人</label></td>
							<td>
								${serTeam.contact}
                   			</td>
                   			<td><label>联系方式</label></td>
							<td>
								${serTeam.contactTel}
                   			</td>
						</tr>
						<tr>
							<td><label>传真</label></td>
							<td>
								${serTeam.fax}
                   			</td>
                   			<td><label>电子邮箱</label></td>
							<td>
								${serTeam.email}
                   			</td>
						</tr>
						<tr>
							<td style="vertical-align: middle"><label>负责人</label></td>
							<td style="vertical-align: middle">
								${serTeam.principal}
                   			</td>
                   			<td style="vertical-align: middle"><label>负责人电话</label></td>
							<td style="vertical-align: middle">
								${serTeam.principalTel}
                   			</td>
						</tr>
						<tr>
                   			<td class="col-xs-2"><label>服务队地址</label></td>
							<td class="col-xs-10" colspan="3">
								${serTeam.address}
                   			</td>
						</tr>
					</table>
					</div>
					<form class="form-inline" id="searchForm" action="${webroot}serviceteam/viewSerTeam.do">
						<input type="hidden" name="id" value="${serTeam.id}" />
						<input type="hidden" name="page" id="currentPageInput" />
			           	<input type="hidden" name="rows" id="pageSizeInput" />
					</form>
					<div id="u1628_state1" class="serTab mn5" data-label="参加人员信息" style="visibility: inherit;; display: none;">
			          <div id="u1628_state1_content" class="panel_state_content">
			            <div id="u2204" class=" box_1">
			            	<div class="form-inline">
		                    <table id="trRecordList" class="table table-bordered table-hover table-striped">
		                        <thead>
		                        <tr role="row">
		                            <th width="60" style="text-align:center">序号</th>
		                            <th style="text-align:center;width:150px">用户名</th>
		                            <th style="text-align:center;width:150px">姓名</th>
		                            <th style="text-align:center">联系方式</th>
		                            <th style="text-align:center">邮箱</th>
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
			                            <td align="center">{{(d.pageSize * (d.pageNum-1))+index+1}}</td>
			                            <td>
			                                <div>
			                                   <span>{{item.userName}}</span>
			                                </div>
			                            </td>
										<td>
			                                <div>
			                                   <span>{{item.realName}}</span>
			                                </div>
			                            </td>
			                             <td style="text-align:center">
			                                <div>
			                                   <span>{{item.mobile }}</span>
			                                </div>
			                            </td>
			                             
			                             <td align="center">
			                                <div>
			                                   <span>{{item.email }}</span>
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
	</div>
	
<!-- 	<div class="v-form-footer"> -->
<!-- 	   			<button type="button" class="btn btn-sm" onclick="closeFrame()"><i class="fa fa-remove"></i>关闭</button> -->
<!-- 	</div> -->
	<script src="${sr}layui.js" type="text/javascript"></script>
	<script src="${sr}js/baseutil.js" type="text/javascript"></script>
	<script src="${sr}js/jquery.min.js" type="text/javascript"></script>
	<script type="text/javascript">
layui.use(['jquery','layer','laytpl'], function(){
		
		$(".ax_default").each(function(index, element) {
		    $(element).click(function(){
		    	$(".ax_default").find(".img").attr({"src":"${sr}/images/u1622.png"});
		    	$(".ax_default").find(".u1623").css("color","");
			    $(this).find(".img").attr({"src":"${sr}images/u13552_selected.png"});
			    $(this).find(".u1623").css("color","black");
				   $(".mn5").each(function(i, e) {
							if(index==i){
								$(e).show();	
							}else{
								$(e).hide();
							}   
					 });

				})
		  });
		
		postFormData("searchForm",true,searchResult);
		
	});
	
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
	
	
	function closeFrame(){
			var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
			parent.layer.close(index);
		}
		
	</script>
</body>
</html>