<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>  
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
						    	<label >招募人数</label>
						    </td>
						    <td class="col-xs-2" >
						    	${activity.recruitNum}
						    </td>
					    </tr>
				    	<tr>
							<td class="col-xs-1">
						    	<label >招募条件</label>
						    </td>
						    <td class="col-xs-5" colspan="3">
								${activity.requirements}
						    </td>
				    	 </tr>
				    	 <tr>
							<td class="col-xs-1">
						    	<label >招募开始时间</label>
						    </td>
						    <td class="col-xs-2">
						    	<fmt:formatDate value="${activity.recruitSt}" pattern="yyyy-MM-dd HH:mm:ss"/>
						    </td>
						    <td class="col-xs-1">
						    	<label >招募结束时间</label>
						    </td>
						    <td class="col-xs-2">
						    	<fmt:formatDate value="${activity.recruitEt}" pattern="yyyy-MM-dd HH:mm:ss"/>
						    </td>
				    	 </tr>
					</table>
				</div>
				
				<div class="row">
					报名列表
					<table class="table table-bordered dataTable no-footer">
						<thead>
							<tr >
								<th style="width: 65px">序号</th>
								<th style="width: 90px">姓名</th>
								<th >所属服务队</th>
								<th style="width: 70px">性别</th>
								<th style="width: 120px">联系方式</th>
								<th >地址</th>
								<th style="width: 200px">操作</th>
							</tr>
						</thead>
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
			                            <td>
			                                <div>
			                                   <span >
													<shiro:hasPermission name="/vols/viewVolunteer.html">
														<a href="javascript:void(0)" onclick="viewVolunteer({{item.id}},'{{item.serviceTeam}}')">
													</shiro:hasPermission>
															{{item.realName}}
											   		<shiro:hasPermission name="/vols/viewVolunteer.html">	
														</a>
													</shiro:hasPermission>
												</span>

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
			                                   <span>{{item.mobile}}</span>
			                                </div>
			                            </td>
										<td>
			                                <div>
			                                   <span>{{item.address}}</span>
			                                </div>
			                            </td>
										<td align="center">
			                                <div>
													{{#  if(item.applyResult!=null && item.applyResult!="" ){ }}
    														{{item.applyResult}}
  													{{#  } }}
													{{#   if(item.applyResult==null || item.applyResult=="" ){ }}
														<span class="btn-group btn-group-sm btn-group-xs">
    														<a class="btn btn-info btn-icon icon-left btn-one-last" style="margin-right:5px;" title="接受" onclick="pass({{item.id}})"><i class="fa fa-pencil-square-o">接受</i></a>
															<a class="btn btn-danger  btn-icon icon-left btn-one-last" title="拒绝" onclick="refuse({{item.id}})" ><i class="fa fa-trash-o">拒绝</i></a>
  														</span>
													{{#  } }}
													
                                            
			                                </div>
			                            </td>
			                        </tr>
								{{#  }); }}                       
					</script>
				</div>
				<form id="activityApplyForm" action="${webroot}activity/activityApply.do" method="post">
					<input type="hidden" name="aId" id="aId" value="${activity.id}">
					<input type="hidden" name="page" id="currentPageInput" value="1"/>
				</form>
		</div>
		<div class="v-form-footer">
		  		<button type="button" id="sub" class="btn btn-sm " onclick="closeFrame()"><i class="fa fa-remove"></i>关闭</button>
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
		loadPage();
		$('#pic').attr('src', '${webroot}file/readPic.do?ataUrl='+encodeURIComponent("${volunteerView.imgUrl}"));
		var layer = layui.layer;
		layer.config({
			  extend: 'myskin/style.css'
			});
	})
	
	function loadPage(){
		$("#currentPageInput").val(1);
		postFormData("activityApplyForm",true,searchResult);
	}
	
	<shiro:hasPermission name="/vols/viewVolunteer.html">
	//查看志愿者
	function viewVolunteer(id,teamName){
		var layer = layui.layer;
		//iframe层-父子操作
		var index=layer.open({
		  type: 2,
		  title:"查看志愿者",
		  skin: 'view-class',
		  area: ['950px','600px'],
		  scrollbar: false,
		  content: ["${webroot}vols/viewVolunteer.html?volunteerId="+id+"&teamName="+teamName],
		  btn: ['<i class="fa fa-remove"></i>关闭'],
			yes : function(index, layero) {
				var iframeWin = top.window[layero.find('iframe')[0]['name']]; 
				//iframeWin.closeFrame();
				layer.close(index);
			}
		});
		layer.full(index);  
		
	}
	</shiro:hasPermission>
	
	//志愿者活动报名的审核通过
	function pass(vId){
		var layer = layui.layer;
		var aId=$("#aId").val();
		layer.confirm('确定通过该志愿者的活动报名申请吗？', {icon: 3, title:'系统提示',
			  btn: ['确定','取消'] //按钮
			}, function(){
				var url="${webroot}activity/volunteerApplyActivity.do";
				params={"vId":vId,"status":1,"aId":aId};
				postData(url, params, true,applyResult);
			  	
			}, function(){
			  
			});
	}
	
	//志愿者活动报名的审核拒绝
	function refuse(vId){
		var layer = layui.layer;
		var aId=$("#aId").val();
		layer.confirm('确定拒绝该志愿者的活动报名申请吗？', {icon: 3, title:'系统提示',
			  btn: ['确定','取消'] //按钮
			}, function(){
				var url="${webroot}activity/volunteerApplyActivity.do";
				params={"vId":vId,"status":2,"aId":aId};
				postData(url, params, true,applyResult);
			}, function(){
			  
		});
	}
	
	function applyResult(data){
		var message=data.message;
		if(data && data.success){
			layer.msg(message, {icon: 1});
			loadPage();
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
        		var pageHtml = createActivityApplyPage(page, "activityApplyForm");
        		
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
	
	function createActivityApplyPage(page,searchFormId){
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