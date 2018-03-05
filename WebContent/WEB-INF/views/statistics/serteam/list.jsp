<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<%@ taglib prefix="tps" uri="/pageTag"%>
<c:set var="webroot" value="${pageContext.request.contextPath}/" />
<%-- 静态资源绝对地址 --%>
<c:set var="sr" value="${pageContext.request.contextPath}/static/" />
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<!-- <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,Chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    
    <title>湖北省文化志愿者管理系统</title> -->
<jsp:include page="../../include/title.jsp"></jsp:include>

<link href="${sr}css/font-awesome/css/linecons.css" rel="stylesheet">
<link href="${sr}css/font-awesome/css/font-awesome.min.css"
	rel="stylesheet">
<link href="${sr}css/bootstrap.min.css" rel="stylesheet">
<link href="${sr}css/xenon.min.css" rel="stylesheet">
<link href="${sr}css/base.css" rel="stylesheet">
<link href="${sr}css/new.css" rel="stylesheet">
<link href="${sr}css/iCheck/blue.css?v=1.0.2" rel="stylesheet">
<!--[if lt IE 9]>
      <script src="${sr}js/html5shiv.min.js"></script>
      <script src="${sr}js/respond.min.js"></script>
    <![endif]-->
<style type="text/css">
.content-top {
	font-size: 17px;
	padding-bottom: 15px;
	padding: 10px 15px;
	color: #ffffff;
}

.systopcolor {
	background-color: #cc3f44;
}

#sysInfo {
	border-radius: 4px;
	border: 1px solid #a40f37;
}
</style>
</head>
<body class="page-body skin-facebook">

	<div class="page-container">
		<jsp:include page="../../include/top.jsp"></jsp:include>
		<!--  -->
		<jsp:include page="../../include/left.jsp"></jsp:include>
		<!--  -->
		<div class="main-content" style="height: 100%;"
			style="margin: 0px;padding: 0px;background-color: #fff;">
			<div class="workspace-page">
				<div class="page-title list-page">
					<div class="title-env">
						<ol class="breadcrumb bc-1" style="margin: 0px;">
							<li><a href="${webroot}main.html" target="_top"><i
									class="fa-home"></i>首页</a></li>
							<li><a>统计分析</a></li>
							<li class="active"><a
								href="${webroot}statisticsService/list.html"><strong>服务队统计</strong></a>
							</li>
						</ol>
					</div>
				</div>

				<div class="table-panel panel">
					<div class="col-sm-12">
						<div id="servicetop" style="width: 100%; height: 400px;"></div>
					</div>

					<div class="col-sm-12">
						<div id="servicemid" style="width: 100%; height: 600px;"></div>
					</div>

					<div class="col-sm-12" style="width: 100%;">
						<div class="panel-now" id="sysInfo">
							<div class="content-top systopcolor">
								<i class="fa-list-ul"></i> 参加培训人数统计
							</div>
							<div
								class="dataTables_wrapper form-inline dt-bootstrap no-footer"
								style="margin-top: 20px; margin-bottom: 10px;">
								<form class="form-inline" id="searchForm"
									action="${webroot}GetTrRecordStatis/list.do" method="POST">
									<input type="hidden" name="page" id="currentPageInput"
										value="1" /> <input type="hidden" name="rows"
										id="pageSizeInput" />
								</form>
								<table aria-describedby="example-2_info" role="grid"
									id="volsList"
									class="table table-bordered table-hover table-striped dataTable no-footer">
									<thead>
										<tr role="row">
											<th style="width: 60px">序号</th>
											<th>培训名称</th>
											<th>参加培训人数</th>
											<th>培训时间</th>
										</tr>
									</thead>
									<tbody class="middle-align" id="pageData">

									</tbody>
								</table>
								<div class="row" style="display: none;" id="pageInfo"></div>
								<div class="panel-disabled">
									<div class="loader-1"></div>
								</div>
								<div
									style="margin-top: -20px; line-height: 50px; border: 1px solid #ddd; border-top: 0; display: none;"
									id="messageDiv">
									<p class="text-center">暂无数据</p>
								</div>
								<script id="templateData" type="text/html">
									{{#  layui.each(d.list, function(index, item){ }}	
			                        <tr role="row"
			                            data-toggle="collapse" >
										<td>{{(d.pageSize * (d.pageNum-1))+index+1}}</td>
			                            <td>
			                                <div align="center">
												<span>
													{{item.trName}}
												</span>
			                                </div>
			                            </td>
										<td align="center">
			                                <div>
			                                   <span>{{item.trainVolNum }}</span>
			                                </div>
			                            </td>
										<td align="center">
			                                <div>
			                                   <span>{{item.trTime }}</span>
			                                </div>
			                            </td>
			                        </tr>
									{{#  }); }}
									</script>
							</div>
						</div>
					</div>

					<div class="col-sm-12" style="width: 100%; margin-top: 20px; margin-bottom: 70px;">
					<div class="panel-now" id="sysInfo">
							<div class="content-top systopcolor">
								<i class="fa-list-ul"></i> 培训数量统计(按年份)
							</div>
						<div class="dataTables_wrapper form-inline dt-bootstrap no-footer"
							style="width: 100%; margin-top: 20px; margin-bottom: 10px;">
							<form class="form-inline" id="searchForm1"
								action="${webroot}GetTrRecordByYear/list.do" method="POST">
								<input type="hidden" name="page" id="currentPageInput1"
									value="1" /> <input type="hidden" name="rows"
									id="pageSizeInput1" />
							</form>
							<table aria-describedby="example-2_info" role="grid"
								class="table table-bordered table-hover table-striped dataTable no-footer">
								<thead>
									<tr role="row">
										<th>年份</th>
										<th>培训次数</th>
									</tr>
								</thead>
								<tbody class="middle-align" id="pageData1">

								</tbody>
							</table>
							<div class="row" style="display: none;" id="pageInfo1"></div>
							<div class="panel-disabled">
								<div class="loader-1"></div>
							</div>
							<div
								style="margin-top: -20px; line-height: 50px; border: 1px solid #ddd; border-top: 0; display: none;"
								id="messageDiv1">
								<p class="text-center">暂无数据</p>
							</div>
							<script id="templateData1" type="text/html">
									{{#  layui.each(d.list, function(index, item){ }}	
			                        <tr role="row"
			                            data-toggle="collapse" >
			                            <td>
			                                <div align="center">
												<span>
													{{item.year}}
												</span>
			                                </div>
			                            </td>
										<td align="center">
			                                <div>
			                                   <span>{{item.recordNum }}</span>
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
	</div>

	<jsp:include page="../../include/footer.jsp"></jsp:include>

	<script src="${sr}js/jquery.min.js" type="text/javascript"></script>
	<script src="${sr}js/main.js" type="text/javascript"></script>
	<script src="${sr}layui.js" type="text/javascript"></script>
	<script src="${sr}js/config.js" type="text/javascript"></script>
	<script src="${sr}js/baseutil.js" type="text/javascript"></script>
	<script src="${sr}js/iCheck/icheck.min.js?v=1.0.2"
		type="text/javascript"></script>
	<script src="${sr}js/iCheck/custom.min.js?v=1.0.2"
		type="text/javascript"></script>
	<script src="${sr}js/echarts.min.js" type="text/javascript"></script>
	<script type="text/javascript">
	$(function(){
		load();
	});
	
	function load(){
	layui.use(['jquery','layer','laytpl'], function(){
		initMenu();
		var layer = layui.layer;
		postFormData("searchForm",true,searchResult);
		postFormData("searchForm1",true,searchResult1);
		layer.config({
			  extend: 'myskin/style.css'
			});
	});
	
	// 基于准备好的dom，初始化echarts实例
	var servicetopChart = echarts.init(document.getElementById('servicetop'));
	var servicemidChart = echarts.init(document.getElementById('servicemid'));
	
	servicetopChart.showLoading({
        text: "图表数据正在努力加载..."
    });
	
	servicemidChart.showLoading({
        text: "图表数据正在努力加载..."
    });
	servicetopoption = {
			title: {
				text: '服务队统计',
				subtext: '区域统计',
				x: 'center'
			},
			tooltip: {
				trigger: 'item',
				formatter: "{b}(数量: {c})<br />占比 :  {d}%"
			},
			toolbox: {
				feature: {
					dataView: {
						show: true,
						readOnly: false
					},
					saveAsImage: {
						show: true,
						title : '下载'
					}
				}
			},
			legend: {
				orient: 'vertical',
				left: 'left',
				data: ['武汉市', '襄阳市', '荆州市', '宜昌市','咸宁市','黄冈市', '黄石市']
			},
			series: [{
				name: '服务队统计',
				type: 'pie',
				radius: '55%',
				center: ['50%', '60%'],
				data: [{
						value: 355,
						name: '武汉市'
					},
					{
						value: 310,
						name: '襄阳市'
					},
					{
						value: 234,
						name: '荆州市'
					},
					{
						value: 135,
						name: '宜昌市'
					},
					{
						value: 135,
						name: '咸宁市'
					},
					{
						value: 135,
						name: '黄冈市'
					},
					{
						value: 335,
						name: '黄石市'
					}
				],
				itemStyle: {
					emphasis: {
						shadowBlur: 10,
						shadowOffsetX: 0,
						shadowColor: 'rgba(0, 0, 0, 0.5)'
					}
				}
			}]
		};
	
	
	servicemidoption = {
			title: {
				text: '服务队时长统计',
				subtext: '志愿者服务时长统计',
				x: 'center'
			},
			tooltip: {
				trigger: 'axis',
				axisPointer: {
					type: 'cross',
					crossStyle: {
						color: '#999'
					}
				}
			},
			toolbox: {
				feature: {
					dataView: {
						show: true,
						readOnly: false
					},
					magicType: {
						show: true,
						type: ['line', 'bar']
					},
					restore: {
						show: true
					},
					saveAsImage: {
						show: true
					}
				}
			},
			legend: {
				left: 'left',
				data: ['志愿服务队时长']
			},
			xAxis: [{
				type: 'category',
				name: '服务队名称',
				data: ['湖北省图书馆总队', '武汉美术馆愿者服务大队', '襄阳曲艺馆服务大队', '湖北省美术馆服务总队', '恩施图书馆大队', '荆门美术馆大队', '荆州曲艺馆大队', '仙桃图书馆大队', '天门美术馆大队', '黄石曲艺馆大队', '黄冈图书馆大队', '武昌美术馆服务队','汉口图书馆服务队', '襄州图书馆服务队', '十堰图书馆服务大队', '云梦县图书馆志愿服务队', '湖北省曲艺馆服务总队', '谷城县美术馆服务队', '兴山县图书馆服务队', '五峰美术馆服务队'],
				axisLabel:{
		              rotate:75, //刻度旋转45度角
		              interval:0,
				},
				axisPointer: {
					  type: 'shadow'
				}
			}],
			grid: { // 控制图的大小，调整下面这些值就可以，
	            x: 60,
	            x2: 100,
	            y2: 250,// y2可以控制 X轴跟Zoom控件之间的间隔，避免以为倾斜后造成 label重叠到zoom上
	        },
			yAxis: [{
					type: 'value',
					name: '时长',
					min: 0,
					axisLabel: {
						formatter: '{value} 小时'
					}
				}
			],
			series: [{
					name: '志愿服务队时长',
					type: 'bar',
					data: [300, 286, 256 , 250, 220, 198, 188, 167, 154, 132, 120, 115,109, 102, 92 , 83, 73, 65, 54, 21]
				},
				
			]
		};
	
	//通过Ajax获取数据
	var url="${webroot}StatisticsService/list.do";
	postData(url, null, true,statisticsSerResult);
	
	var name;
	var value;
	//获取统计的回调函数
	function statisticsSerResult(data){
		if(data && data.success){
			var serTeamList= data.serTeamList;
			var serNameHourList= data.serNameHourList;
			var data=[];
			var city=[];
			var serTeam=[];
			var stHour=[];
			if (serTeamList && serTeamList.length>0 &&serNameHourList && serNameHourList.length>0) {
				var istreatAgency=serTeamList[0].istreatAgency;
				if (istreatAgency) {
					$("#servicetop").hide();
					$("#servicemid").hide();
				} 
				for(var i=0;i<serTeamList.length; i++){
					var ob=new Object();
					ob.value=serTeamList[i].serTeamNum;
					ob.name=serTeamList[i].areaName;
					city.push(ob.name);
					data.push(ob);
				}
				servicetopoption.legend.data=city;
				servicetopoption.series[0].data =data;
				servicetopChart.hideLoading();
				servicetopChart.setOption(servicetopoption);
				
				
				for(var i=0;i<serNameHourList.length; i++){
					var ob=new Object();
					ob.value=serNameHourList[i].serHours;
					ob.name=serNameHourList[i].serTeamName;
					serTeam.push(ob.name);
					stHour.push(ob.value);
				}
				servicemidoption.xAxis[0].data= serTeam;
				servicemidoption.series[0].data =stHour;
				servicemidChart.hideLoading();
				servicemidChart.setOption(servicemidoption);
				
			}
		}
	}
	
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
	*切换每页显示的条数
	*/
	function changePage1(obj,searchFormId){
		var pageSize = $(obj).val();
		if(console.log){
			console.log("pageSize:"+pageSize);
		}
		$("#pageSizeInput1").val(pageSize);
		$("#currentPageInput1").val(1);
		postFormData(searchFormId,true,searchResult1);
	}
	
	/**
	*翻页
	*/
	function setCurrentPage1(currentPage,searchFormId){
		$("#currentPageInput1").val(currentPage);
		postFormData(searchFormId,true,searchResult1);
	}
	
	/**
	*跳转到设置的页面
	*/
	function goPage1(maxPage,searchFormId){
		var j_goPageText = $("#goPageText");
		if(j_goPageText){
			try{
				var currentPage = parseInt(j_goPageText.val());
				if(currentPage > 0  && currentPage <= maxPage){
					$("#currentPageInput1").val(currentPage);
					postFormData(searchFormId,true,searchResult1);
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
	
	/**
	**回调函数
	*/
	function searchResult1(data){
		$(".panel-disabled").hide();
		var laytpl = layui.laytpl;
		var layer = layui.layer;
		if(data.success){
    		var page = data.page;
    		var list = page.list;
    		if(list && list.length > 0){
    			var templateHtml = $("#templateData1").html();
        		laytpl(templateHtml).render(page, function(result){
        		    $("#pageData1").html(result);
        		  });
        		var pageHtml = createPageInfo1(page, "searchForm1");
        		$("#pageInfo1").html(pageHtml);
        		$("#pageInfo1").show();
        		$("#messageDiv1").hide();
    		}else{
    			$("#pageData1").html("");
    			$("#pageInfo1").html("");
    			$("#pageInfo1").hide();
    			$("#messageDiv1").show();
    		}
		}else{
			var message = data.message;
			$("#pageData1").html("");
			$("#pageInfo1").html("");
			$("#pageInfo1").hide();
			layer.msg(message);
		}
	}
	
	function loadPage(){
		postFormData("searchForm",true,searchResult);
	}
	
	/**
	 * 分页插件
	 */
	function createPageInfo1(page,searchFormId){
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
				
				$("#currentPageInput1").val(currentPage);
				$("#pageSizeInput1").val(pageSize);
				
				html +="<div class=\"col-sm-4\"><label>每次加载<select class=\"form-control input-sm\" onchange=\"changePage1(this,'"+searchFormId+"')\">";
				if(pageSize == 10){
					html +="<option value=\"10\" selected>10</option>";
				}else{
					html +="<option value=\"10\">10</option>";
				}
				if(pageSize == 20){
					html +="<option value=\"20\" selected>20</option>";
				}else{
					html +="<option value=\"20\">20</option>";
				}
				if(pageSize == 50){
					html +="<option value=\"50\" selected>50</option>";
				}else{
					html +="<option value=\"50\">50</option>";
				}
				if(pageSize == 100){
					html +="<option value=\"100\" selected>100</option>";
				}else{
					html +="<option value=\"100\">100</option>";
				}
	            html +="</select>条，共"+total+"条数据";                        	
	            html +="</label>";
	            html +="</div>";
	                                    
	            
	            html +="<div class=\"col-sm-8\">";
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
					html +="<a href=\"javascript:void(0)\" onclick=\"setCurrentPage1("+1+",'"+searchFormId+"')\" aria-label=\"Previous\">";    
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
			    		html +="<li><a href=\"javascript:void(0)\" onclick=\"setCurrentPage1("+i+",'"+searchFormId+"')\">"+i+"</a></li>";
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
					html +="<a href=\"javascript:void(0)\" onclick=\"setCurrentPage1("+pageCount+",'"+searchFormId+"')\" aria-label=\"Next\">";
						html +="<span aria-hidden=\"true\">&raquo;</span>";
					html +="</a>";
					html +="</li>";
				}
				
				html +="<li><span style=\"color: #2C2E2F;border-top: 0;border-bottom: 0;border-right: 0;\">"+currentPage+"/"+pageCount+"</span></li>";
				html +="<li><input type=\"text\" id=\"goPageText\" style=\"width: 34px;height: 34px;margin-right: 10px;padding: 0px;text-align: center;\" class=\"form-control\" /></li>";
				html +="<li><span style=\"float: right;\"  onclick=\"goPage1("+pageCount+",'"+searchFormId+"')\"><a href=\"javascript:void(0)\">GO</a></span></li>";
				html +="</ul>";
				html +="</nav>";
			}
		}
		return html;
	}
</script>
</body>
</html>