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
#tabList>thead>tr>td {
	padding-left: 6px;
	padding-right: 6px;
}

.panel-now {
	margin-top: 15px;
	margin-bottom: 20px;
	background-color: #fff;
}

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

.cptopcolor {
	background-color: #30BEA2;
}

#cpxx {
	border-radius: 4px;
	border: 1px solid #30BEA2;
}

#jkxx {
	border-radius: 4px;
	border: 1px solid #ed5565;
}

.jkxxcolor {
	background-color: #ed5565;
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
							<li class="active"><a href="${webroot}config/list.html"><strong>志愿者统计</strong></a>
							</li>
						</ol>
					</div>
				</div>

				<div class="table-panel panel">
					<div class="col-sm-12" id="volList">
						<div id="main" style="width: 100%; height: 400px;"></div>
					</div>

					<div class="col-sm-12" id="starList" style="margin-bottom: 40px">
						<div id="star" style="width: 100%; height: 400px;"></div>
					</div>

					<div class="col-sm-12">
						<div class="panel-now" id="sysInfo">
							<div class="content-top systopcolor">
								<i class="fa-list-ul"></i> 志愿者服务时长及活动次数统计列表
							</div>
							<div class="dataTables_wrapper form-inline dt-bootstrap no-footer" style="margin-top: 20px;margin-bottom: 10px;">
								<form class="form-inline" id="searchForm"
									action="${webroot}GetVolStatis/list.do" method="POST">
									<input type="hidden" name="page" id="currentPageInput"
										value="1" /> <input type="hidden" name="rows"
										id="pageSizeInput" />
								</form>
								<table aria-describedby="example-2_info" role="grid"
									id="volsList"
									class="table table-bordered table-hover table-striped dataTable no-footer">
									<thead>
										<tr role="row">
											<th style="width: 50px">序号</th>
											<th style="width: 90px">姓名</th>
											<th style="width: 55px">服务时长(h)</th>
											<th style="width: 55px">参加活动次数(次)</th>
											<th style="width: 160px">注册时间</th>
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
			                                <div>
												<span>
													{{item.volName}}
												</span>
			                                </div>
			                            </td>
										<td>
			                                <div>
			                                   <span>{{item.serTeamHour }}</span>
			                                </div>
			                            </td>
			                             <td>
			                                <div>
			                                   <span>{{item.activityNum }}</span>
			                                </div>
			                            </td>
			                             <td>
			                                <div>
			                                   <span>{{getFormatDateByLong(item.regTime) }}</span>
			                                </div>
			                            </td>
			                        </tr>
									{{#  }); }}
									</script>
							</div>
						</div>
					</div>

					<div class="col-sm-12" style="margin-bottom: 40px">
						<div class="panel-now" id="sysInfo">
							<div class="content-top systopcolor">
								<i class="fa-list-ul"></i> 文化志愿者统计信息(人数)
							</div>
							<div class="panel-body">
								<table aria-describedby="example-2_info"
									style="text-align: center;" id="tabList"
									class="table table-bordered table-hover table-striped dataTable no-footer">
									<thead>
										<tr role="row">
											<td rowspan="2" style="width: 135px; vertical-align: middle;">服务队名称</td>
											<td colspan="2">性别</td>
											<td colspan="10">学历</td>
											<td colspan="6">政治面貌</td>
											<td colspan="6">年龄</td>
										</tr>

										<tr role="row">
											<td>男生数量</td>
											<td>女生数量</td>
											<td>博士</td>
											<td>硕士</td>
											<td>本科</td>
											<td>大专</td>
											<td>中专</td>
											<td>职业高中</td>
											<td>普通高中</td>
											<td>初中</td>
											<td>小学</td>
											<td>其他</td>
											<td>中共党员</td>
											<td>中共预备党员</td>
											<td>共青团员</td>
											<td>民主党派</td>
											<td>无党派民主人士</td>
											<td>群众</td>
											<td>20以下</td>
											<td>20<br>&ndash;<br>30
											</td>
											<td>31<br>&ndash;<br>40
											</td>
											<td>41<br>&ndash;<br>50
											</td>
											<td>51<br>&ndash;<br>60
											</td>
											<td>60以上</td>
										</tr>
									</thead>
									<c:if test="${empty statisticsList}">
										<p align="center">没有相关数据</p>
									</c:if>
									<c:if test="${not empty statisticsList}">
										<c:forEach items="${statisticsList}" var="item">
											<tbody id="tbody">
												<tr role="row">
													<td>${item.serTeamName }</td>
													<td>${item.volBoysNum }</td>
													<td>${item.volGirlsNum }</td>
													<td>${item.volGraduateNum }</td>
													<td>${item.volPostGraduateNum }</td>
													<td>${item.volUndergraduateNum }</td>
													<td>${item.volCollegeNum }</td>
													<td>${item.volSecondaryNum }</td>
													<td>${item.volVocationalNum }</td>
													<td>${item.volMiddleNum }</td>
													<td>${item.volJuniorNum }</td>
													<td>${item.volPrimaryNum }</td>
													<td>${item.volOtherEducation }</td>
													<td>${item.volCpcNum }</td>
													<td>${item.volPrepareNum }</td>
													<td>${item.volCYLNum }</td>
													<td>${item.volDemocraticNum }</td>
													<td>${item.volNonpartisanNum }</td>
													<td>${item.volCitizen }</td>
													<td>${item.volTeen }</td>
													<td>${item.volTwenty }</td>
													<td>${item.volThirty }</td>
													<td>${item.volForty }</td>
													<td>${item.volFiFty }</td>
													<td>${item.volSixty }</td>
												</tr>
											</tbody>
										</c:forEach>
									</c:if>
									<tbody id="totalRow"></tbody>

								</table>
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
		$(function() {
			load();
		});

		function load() {
			layui.use([ 'jquery', 'layer', 'laytpl' ], function() {
				initMenu();
				var laytpl = layui.laytpl;
				var layer = layui.layer;
				postFormData("searchForm", true, searchResult);
			});

			// 基于准备好的dom，初始化echarts实例
			var myChart = echarts.init(document.getElementById('main'));
			var starChart = echarts.init(document.getElementById('star'));

			myChart.showLoading({
				text : "图表数据正在努力加载..."
			});

			starChart.showLoading({
				text : "图表数据正在努力加载..."
			});
			option = {
				title : {
					text : '志愿者人数统计',
					subtext : '区域统计',
					x : 'center'
				},
				tooltip : {
					trigger : 'item',
					formatter : "{b}(人数: {c})<br />占比 :  {d}%"
				},
				toolbox : {
					feature : {
						dataView : {
							show : true,
							readOnly : false
						},
						saveAsImage : {
							show : true,
							title : '下载'
						}
					}
				},
				legend : {
					orient : 'vertical',
					left : 'left',
					data : []
				},
				series : [ {
					name : '志愿者人数统计',
					type : 'pie',
					radius : '55%',
					center : [ '50%', '60%' ],
					data : [],
					itemStyle : {
						emphasis : {
							shadowBlur : 10,
							shadowOffsetX : 0,
							shadowColor : 'rgba(0, 0, 0, 0.5)'
						}
					}
				} ]
			};

			staroption = {
				title : {
					text : '星级统计',
					subtext : '人数统计',
					x : 'center'
				},
				tooltip : {
					trigger : 'item',
					formatter : "{b}(人数: {c})<br />占比 :  {d}%"
				},
				toolbox : {
					feature : {
						dataView : {
							show : true,
							readOnly : false
						},
						saveAsImage : {
							show : true,
							title : '下载'
						}
					}
				},
				legend : {
					orient : 'vertical',
					left : 'left',
					data : [ '无', '1星', '2星', '3星', '4星', '5星' ]
				},
				series : [ {
					name : '星级统计',
					type : 'pie',
					radius : '55%',
					center : [ '50%', '60%' ],
					data : [ {
						value : 335,
						name : '无'
					}, {
						value : 310,
						name : '1星'
					}, {
						value : 234,
						name : '2星'
					}, {
						value : 135,
						name : '3星'
					}, {
						value : 135,
						name : '4星'
					}, {
						value : 135,
						name : '5星'
					} ],
					itemStyle : {
						emphasis : {
							shadowBlur : 10,
							shadowOffsetX : 0,
							shadowColor : 'rgba(0, 0, 0, 0.5)'
						}
					}
				} ]
			};

			//通过Ajax获取数据
			var url = "${webroot}statisticsVol/list.do";
			postData(url, null, true, statisticsVolResult);

			var name;
			var value;
			//获取统计的回调函数
			function statisticsVolResult(data) {
				console.log(data);
				if (data && data.success) {
					var volList = data.volList;
					var starList = data.starList;
					if (!volList || volList.length == 0) {
						console.log(1);
						myChart.hideLoading();
						$("#volList").hide();
					}
					if (!starList || starList.length == 0) {
						console.log(2);
						starChart.hideLoading();
						$("#starList").hide();
					}

					var data = [];
					var city = [];
					var starName = [];
					var star = [];
					if (volList && volList.length > 0) {
						var istreatAgency = volList[0].istreatAgency;
						if (istreatAgency) {
							var orgId = volList[0].curOrgId;
							totreatAgency(orgId);
						}
						for (var i = 0; i < volList.length; i++) {
							var ob = new Object();
							ob.value = volList[i].volNum;
							ob.name = volList[i].areaName;
							city.push(ob.name);
							data.push(ob);
						}
						option.legend.data = city;
						option.series[0].data = data;
						myChart.hideLoading();
						myChart.setOption(option);
					}
					if (starList && starList.length > 0) {
						for (var i = 0; i < starList.length; i++) {
							var ob = new Object();
							ob.value = starList[i].volNum;
							ob.name = starList[i].star + "星";
							var starNam = ob.name;
							starName.push(ob.name);
							star.push(ob);
						}
						staroption.legend.data = starName;
						staroption.series[0].data = star;
						starChart.hideLoading();
						starChart.setOption(staroption);
					}
				} else {
					$("#volList").hide();
					$("#starList").hide();
				}
			}

		}

		/**
		 **回调函数
		 */
		function searchResult(data) {
			$(".panel-disabled").hide();
			$("#allcheck").prop("checked", false);
			var laytpl = layui.laytpl;
			var layer = layui.layer;
			if (data.success) {
				var page = data.page;
				var list = page.list;
				if (list && list.length > 0) {
					var templateHtml = $("#templateData").html();
					laytpl(templateHtml).render(page, function(result) {
						$("#pageData").html(result);
					});
					var pageHtml = createPageInfo(page, "searchForm");
					$("#pageInfo").html(pageHtml);
					$("#pageInfo").show();
					$("#messageDiv").hide();
				} else {
					$("#pageData").html("");
					$("#pageInfo").html("");
					$("#pageInfo").hide();
					$("#messageDiv").show();
				}
			} else {
				var message = data.message;
				$("#pageData").html("");
				$("#pageInfo").html("");
				$("#pageInfo").hide();
				layer.msg(message);
			}
		}

		/**
		 *切换每页显示的条数
		 */
		function changePage(obj, searchFormId) {
			var pageSize = $(obj).val();
			if (console.log) {
				console.log("pageSize:" + pageSize);
			}
			$("#pageSizeInput").val(pageSize);
			$("#currentPageInput").val(1);
			postFormData(searchFormId, true, searchResult);
		}

		/**
		 *翻页
		 */
		function setCurrentPage(currentPage, searchFormId) {
			$("#currentPageInput").val(currentPage);
			postFormData(searchFormId, true, searchResult);
		}

		/**
		 *跳转到设置的页面
		 */
		function goPage(maxPage, searchFormId) {
			var j_goPageText = $("#goPageText");
			if (j_goPageText) {
				try {
					var currentPage = parseInt(j_goPageText.val());
					if (currentPage > 0 && currentPage <= maxPage) {
						$("#currentPageInput").val(currentPage);
						postFormData(searchFormId, true, searchResult);
					} else {
						layer.msg("页码输入错误", {
							time : 1000
						});
					}
				} catch (e) {
					layer.msg("页码输入错误", {
						time : 1000
					});
				}
			}
		}

		function totreatAgency(orgId) {
			location.href = "${webroot}TreatAgency/list.html?orgId=" + orgId;
		}
	</script>

	<script type="text/javascript">
		$(document).ready(function() {

			var totalRow = [];
			var length = $("#tabList").find("tr").find("td").length - 1; //列数 
			console.log(length);
			var html = '<tr role="row"><td>合计</td>';

			for (var i = 1; i <= 24; i++) {
				var sum = 0;
				$('#tbody tr').each(function() {
					$(this).find('td:eq(' + i + ')').each(function() {
						sum += parseInt($(this).text());
						totalRow[i] = sum;
					});
				});

				html += '<td>' + totalRow[i] + '</td>';

			}

			$('#totalRow').append(html + '</tr>');
		});
	</script>
</body>
</html>