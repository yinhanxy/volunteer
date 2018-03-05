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
	<link href="${sr}css/select2-bootstrap.min.css" rel="stylesheet">
	<link href="${sr}css/select2.min.css" rel="stylesheet"> 
	<link href="${sr}css/star-rating.min.css" rel="stylesheet">
    <!--[if lt IE 9]>
      <script src="${sr}js/html5shiv.min.js"></script>
      <script src="${sr}js/respond.min.js"></script>
    <![endif]-->
    <style type="text/css">
    	select,select2-container input[type="text"],.uneditable-input{display:inline-block;height:20px;padding:4px 4px;margin-bottom:8px;font-size:14px;line-height:20px;color:#555555;-webkit-border-radius:4px;-moz-border-radius:4px;border-radius:4px;vertical-align:middle;}
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
		#u1622_1 {
		    border-width: 0px;
		    position: absolute;
		    left: 15px;
		    top: 2px;
		    width: 87px;
		    height: 40px;
		}
		#u1622_2 {
		    border-width: 0px;
		    position: absolute;
		    left: 101px;
		    top: 2px;
		    width: 87px;
		    height: 40px;
		}
		#u1622_3 {
		    border-width: 0px;
		    position: absolute;
		    left: 193px;
		    top: 2px;
		    width: 87px;
		    height: 40px;
		}
		#u1622_4 {
		    border-width: 0px;
		    position: absolute;
		    left: 286px;
		    top: 2px;
		    width: 87px;
		    height: 40px;
		}
		.u1623 {
		    border-width: 0px;
		    position: absolute;
		    left: 15px;
		    top: 10px;
		    width: 57px;
		    white-space: nowrap;
		}
		#u1620 {
		    border-width: 0px;
		    position: absolute;
		    left: 0px;
		    top: 41px;
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
		
		.ax_default{
		    font-family: 'Helvetica Neue Regular', 'Helvetica Neue';
		    font-weight: 400;
		    font-style: normal;
		    font-size: 14px;
		    color: #428BCA;
		    text-align: center;
		    line-height: 20px;
		} 
		.img {
		    border-width: 0px;
		    position: absolute;
		    left: 0px;
		    top: 0px;
		    width: 88px;
		    height: 41px;
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
			 <div id="u1620"  tabindex="0">
		      	<img id="u1620_img" src="${sr}images/u1620.png" tabindex="0">
		      </div>
			  <div id="u1622_1" class="ax_default sx_cur5" style="cursor: pointer;" >
			       <img class="img" src="${sr}images/u1622_selected.png" >
			        <div class="u1623"  style="top:10px;color:black;">
			         	<span >基本信息</span>
			        </div>
		      </div>
		      <div id="u1622_2" class="ax_default sx_cur5" style="cursor: pointer;">
			        <img class="img" src="${sr}images/u1622.png" tabindex="0">
			        <div class="u1623" style="left: 10px;">
			         <span>志愿者信息</span>
			        </div>
		      </div>
		      <div id="u1622_3" class="ax_default sx_cur5" style="cursor: pointer;">
			        <img class="img" src="${sr}images/u1622.png" tabindex="0">
			        <div class="u1623" style="">
			          <span >活动图片</span>
			        </div>
		      </div>
		<div class="panel" style="margin-bottom: 0;padding-top: 10px;width: 100%;position: absolute;top: 45px" id="mainform">
			
				<div class="row mn5">
					<table class="table table-bordered dataTable no-footer">
						<tr class="row">
							<td class="col-xs-2"><label for="inputEmail1">活动名称<small><code >*</code></small></label></td>
							<td class="col-xs-4">
								${activity.name}
                   			</td>
                   			<td class="col-xs-2"><label for="inputEmail2">活动类型<small><code >*</code></small></label></td>
							<td class="col-xs-4">
								${activity.type==1?"短期":"长期"}
                   			</td>
						</tr>
						<tr class="row">
							<td style="vertical-align: middle;"><label for="inputEmail3">招募条件<small><code >*</code></small></label></td>
							<td colspan="3">
								${activity.requirements}
                   			</td>
						</tr>
						<tr class="row">
                   			<td style="vertical-align: middle;"><label for="inputEmail3">活动类别<small><code >*</code></small></label></td>
							<td>
								<select class="form-control" name="activityClass" id="activityClass" disabled="disabled" >
	           					</select>
                   			</td>
                   			<td class="col-xs-2"><label for="inputEmail1">招募人数<small><code >*</code></small></label></td>
							<td class="col-xs-4">
								${activity.recruitNum}
                   			</td>
						</tr>
						<tr class="row">
							<td class="col-xs-2"><label for="inputEmail1">联系人<small><code >*</code></small></label></td>
							<td class="col-xs-4">
								${activity.contactPerson}
                   			</td>
                   			<td class="col-xs-2"><label for="inputEmail1">联系方式<small><code >*</code></small></label></td>
							<td class="col-xs-4">
								${activity.contact}
                   			</td>
						</tr>
						<tr class="row">
							<td class="col-xs-2"><label for="inputEmail1">地点<small><code >*</code></small></label></td>
							<td class="col-xs-4">
								${activity.address}
                   			</td>
                   			<td class="col-xs-2"><label for="inputEmail1">服务时长<small><code >*</code></small></label></td>
							<td class="col-xs-4">
								${activity.hours}
                   			</td>
						</tr>
						<tr class="row">
							<td class="col-xs-2"><label for="inputEmail1">招募开始时间<small><code >*</code></small></label></td>
							<td class="col-xs-4">
									<fmt:formatDate value="${activity.recruitSt}" pattern="yyyy-MM-dd HH:mm:ss"/>
                   			</td>
                   			<td class="col-xs-2"><label for="inputEmail1">招募结束时间<small><code >*</code></small></label></td>
							<td class="col-xs-4">
								<fmt:formatDate value="${activity.recruitEt}" pattern="yyyy-MM-dd HH:mm:ss"/>
                   			</td>
						</tr>
						<tr class="row">
							<td class="col-xs-2"><label for="inputEmail1">活动开始时间<small><code >*</code></small></label></td>
							<td class="col-xs-4">
								<fmt:formatDate value="${activity.activitySt}" pattern="yyyy-MM-dd HH:mm:ss"/>
                   			</td>
                   			<td class="col-xs-2"><label for="inputEmail1">活动结束时间<small><code >*</code></small></label></td>
							<td class="col-xs-4">
								<fmt:formatDate value="${activity.activityEt}" pattern="yyyy-MM-dd HH:mm:ss"/>
                   			</td>
						</tr>
						<tr class="row">
							<td style="vertical-align: middle;"><label for="inputEmail3">招募范围<small><code >*</code></small></label></td>
							<td>
		           				${activity.recruitRangeDesc}
                   			</td>
						</tr>
						<tr class="row">
							<td style="vertical-align: middle;"><label for="inputEmail3">活动简介<small><code >*</code></small></label></td>
							<td colspan="3">
								${activity.summary}
                   			</td>
						</tr>
					</table>
				</div>
        		<div id="u1628_state1" class="mn5 row" data-label="志愿者信息" style="visibility: inherit;; display: none;">
			          <table class="table table-bordered dataTable no-footer">
						<tr >
							<th style="width: 65px">序号</th>
							<th style="width: 90px">姓名</th>
							<th style="">所属服务队</th>
							<th style="width: 65px">性别</th>
							<th style="width: 120px">联系方式</th>
							<th style="width: 200px">报名时间</th>
							<th style="width: 160px">服务评价</th>
							<th style="width: 95px">服务时长</th>
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
			                            <td>{{(d.pageSize * (d.pageNum-1))+index+1}}</td>
			                            <td>
			                                <div>
			                                   <span >{{item.realName}}</span>
			                                </div>
			                            </td>
			                             <td>
			                                <div>
			                                   <span >{{item.serviceTeam}}</span>
			                                </div>
			                            </td>
			                             <td>
			                                <div>
			                                   <span >{{item.sex }}</span>
			                                </div>
			                            </td>
										<td>
			                                <div>
			                                   <span >{{item.mobile }}</span>
			                                </div>
			                            </td>
										<td title="{{getFormatDateByLong(item.applyTime)}}">
			                                <div>
			                                   <span>{{getFormatDateByLong(item.applyTime).split(" ")[0]}}</span>
			                                </div>
			                            </td>
										<td>
			                                <div>
			                                   <span ><input name="serviceStar" type="text" title="" value="{{item.activityServiceStar}}"/></span>
			                                </div>
			                            </td>
										<td>
			                                <div>
			                                   <span >{{item.activityServiceHours}}小时</span>
			                                </div>
			                            </td>
			                        </tr>
								{{#  }); }}                       
					</script>
		        	<form id="volunteerInfoForm" action="${webroot}activity/activityVolunteerInfo.do" method="post" >
							<input type="hidden" name="aId" id="aId" value="${activity.id}">
							<input type="hidden" name="page" id="currentPageInput" value="1"/>
					</form>
        		</div>
        		<div id="recordPicInfo" class="mn5" data-label="活动图片" style="visibility: inherit;; display: none;">
					<div class="row" style="border:2px solid black;">
						<div id="activityPhotos" class="layer-photos-demo" style="overflow: auto;">
			     			
			     		</div>
					</div>
					<input type="hidden" id="eius" value="${activity.activityImg}">
        		</div> 
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
		$("img[name='pic']").attr('src', '${webroot}file/readPic.do?ataUrl='+encodeURIComponent("${volunteerView.imgUrl}"));
		showActivityClass();
		showPic();
	})
	
	//显示图片信息	
   	function showPic(){
		var eurls=$("#eius").val();
		if(eurls){
			var urls=eurls.split(",");
			if(urls && urls.length>0){
				for (var i = 0; i < urls.length; i++) {
					var src = encodeURIComponent(urls[i]);
					var imgHtml='<img layer-src=\"${webroot}file/readPic.do?ataUrl='+src+'" src="${webroot}file/readPic.do?ataUrl='+src+'" width="150" height="120" border="0" style="float: left;margin: 12px 12px 12px 12px;" >';
					$("#activityPhotos").append(imgHtml);
				}
			}
		}
		layer.photos({
			  photos: '#activityPhotos',
			  closeBtn:1,
				anim: 5 //0-6的选择，指定弹出图片动画类型，默认随机（请注意，3.0之前的版本用shift参数）
			}); 
   	}
	
	function closeFrame(){
		var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
		parent.layer.close(index);
	}
	
	//显示活动类别
	function showActivityClass(){
		var url="${webroot}activity/activityClass.do";
		postData(url, null, true, showActivityClassResult); 
	}
	
	function showActivityClassResult(data){
		if (data.success) {
			var html="";
			var list =data.activityClassList;
			for (var i = 0; i < list.length; i++) {
				html+="<option value='"+list[i].id+"'>"+list[i].name+"</option>";
			}
			$("#activityClass").prepend(html);
		} 
		if(${activity.activityClass}){
			$("#activityClass").val(${activity.activityClass});
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
	var template,pageData,formName,pageInfo,messageDiv;
	/**
	**回调函数
	*/
	function searchResult(data){
		$(".panel-disabled").hide();
		var laytpl = layui.laytpl;
		var layer = layui.layer;
		if(data.success){
    		var page = data.page;
    		if(page){
	    		var list = page.list;
	    		if(list && list.length > 0){
	    			var templateHtml = template.html();
	        		laytpl(templateHtml).render(page, function(result){
	        			pageData.html(result);
	        		  });
	        		var pageHtml = createCertCheckPage(page, formName);
	        		
	        		pageInfo.html(pageHtml);
	        		pageInfo.show();
	        		messageDiv.hide();
	    		}else{
	    			pageData.html("");
	    			pageInfo.html("");
	    			pageInfo.hide();
	    			messageDiv.show();
	    		}
    		}else{
    			pageData.html("");
    			pageInfo.html("");
    			pageInfo.hide();
    			messageDiv.show();
    		}
		}else{
			var message = data.message;
			pageData.html("");
			pageInfo.html("");
			pageInfo.hide();
			layer.msg(message);
		}
		var $star = $("input[name='serviceStar']");
		 $star.rating({
          min: 0,
          max: 5,
          step: 1,
          size: 'xs',
          showClear: false
      	});
		 $star.rating('refresh', {
	            showClear: false,
	            disabled: !$star.attr('disabled')
	        });
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
	
	$(".ax_default").each(function(index, element) {
	    $(element).click(function(){
	    	if(index==1){
	    		template=$("#templateData");
	    		pageData=$("#pageData");
	    		formName="volunteerInfoForm";
	    		pageInfo=$("#pageInfo");
	    		messageDiv=$("#messageDiv");
	    		postFormData("volunteerInfoForm",true,searchResult);
	    	}
	    	if(index==2){
	    		var eius=$("#eius").val();
	    		if(!eius){
	    			$("#recordPicInfo").html("<p style='margin-left: 45%;'>暂无图片记录</p>");
	    		}
	    	}
	    	$(".ax_default").find(".img").attr({"src":"${sr}/images/u1622.png"});
	    	$(".ax_default").find(".u1623").css("color","");
		    $(this).find(".img").attr({"src":"${sr}images/u1622_selected.png"});
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
	</script>
</body>
</html>