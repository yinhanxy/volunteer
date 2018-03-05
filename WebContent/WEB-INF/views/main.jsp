<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %> 
<c:set var="webroot" value="${pageContext.request.contextPath}/" />
<%-- 静态资源绝对地址 --%>
<c:set var="sr" value="${pageContext.request.contextPath}/static/" />
<!DOCTYPE html>
<html lang="zh-CN">
<head>

<jsp:include page="include/title.jsp"></jsp:include>

<link href="${sr}css/font-awesome/css/linecons.css" rel="stylesheet">
<link href="${sr}css/font-awesome/css/font-awesome.min.css"
	rel="stylesheet">
<link href="${sr}css/bootstrap.min.css" rel="stylesheet">
<link href="${sr}css/xenon.min.css" rel="stylesheet">
<link href="${sr}css/base.css" rel="stylesheet">
<link href="${sr}css/new.css" rel="stylesheet">

<!--[if lt IE 9]>
      <script src="${sr}js/html5shiv.min.js"></script>
      <script src="${sr}js/respond.min.js"></script>
    <![endif]-->

<style type="text/css">
a {
	text-decoration: none;
}

a:hover {
	color: #FFF;
}

a:visited{
	color: #FFF;
}
.blue {
	background-color: #3598dc;
}

.red {
	background-color: #e7505a;
}

.green {
	background-color: #32c5d2;
}

.purple {
	background-color: #8E44AD;
}

.boder-start {
	display: block;
	margin-top: 10px;
	margin-bottom: 25px;
	overflow: hidden;
	border-radius: 4px;
}

.detials {
	padding-right: 15px;
	float: right;
}

.number {
	color: #FFF;
	padding-top: 25px;
	text-align: right;
	font-size: 34px;
	line-height: 36px;
	letter-spacing: -1px;
	margin-bottom: 0;
	font-weight: 300;
}

.desc {
	color: #FFF;
	opacity: 1;
	filter: alpha(opacity = 100);
	text-align: right;
	font-size: 16px;
	letter-spacing: 0;
	font-weight: 300;
	font-family: "微软雅黑";
}

.more {
	color: #FFF;
	clear: both;
	display: block;
	padding-top: 10px;
	padding-bottom: 6px;
	padding-left: 6px;
	position: relative;
	text-transform: uppercase;
	font-weight: 300;
	font-size: 11px;
	opacity: .7;
}

#roleList {
	font-family: "微软雅黑";
}

.domore {
	margin-top: 10px;
}

.span {
	margin-top: 15px;
	width: 100px;
	line-height: 100px;
	color: #FFF;
	border-radius: 50%;
}

.divicon {
	width: 100px;
	height: 100px;
	text-align: center;
	font-size: xx-large;
	margin-right: 36px;
}
</style>
</head>
<body class="page-body skin-facebook">
	<div class="page-container">
		<jsp:include page="include/top.jsp"></jsp:include>
		<!--  -->
		<jsp:include page="include/left.jsp"></jsp:include>
		<!--  -->
		<div class="main-content my-index" style="height: 100%;">

			<div class="workspace-page">
				<div class="page-title list-page">
					<div class="title-env">
						<ol class="breadcrumb bc-1" style="margin: 0px;">
							<li><a href="${webroot}main.html" target="_top"><i
									class="fa-home"></i>首页</a></li>
						</ol>
					</div>
				</div>

				<div class="table-panel panel">
					
					<c:if test="${checkVolNum ne 0}">
					<shiro:hasPermission name="/vols/PendingVols.html">
					<div class="col-sm-3 ">
						<div class="boder-start blue">
							<div class="detials">
								<div class="number">${checkVolNum }</div>
								<div class="desc">待审核志愿者人数</div>
							</div>
							<div class="domore">
									<a class="more" href="${webroot}vols/PendingVols.html">查看更多 <i
										class="fa fa-arrow-right detials" aria-hidden="true"></i>
									</a>
							</div>
						</div>
					</div>
					</shiro:hasPermission>
					</c:if>
					<c:if test="${subActNum ne 0}">
					<shiro:hasPermission name="/activity/SubmitActs.html">
					<div class="col-sm-3 ">
						<div class="boder-start red">
							<div class="detials">
								<div class="number">${subActNum }</div>
								<div class="desc">待提交的活动数</div>
							</div>
							<div class="domore">
									<a class="more" href="${webroot}activity/SubmitActs.html?selected=submited">查看更多 <i
										class="fa fa-arrow-right detials" aria-hidden="true"></i>
									</a>
							</div>
						</div>
					</div>
					</shiro:hasPermission>
					</c:if>
					<c:if test="${actNum ne 0}">
					<shiro:hasPermission name="/activity/SubmitActs.html">
					<div class="col-sm-3 ">
						<div class="boder-start green">
							<div class="detials">
								<div class="number">${actNum }</div>
								<div class="desc">待发布的活动数</div>
							</div>
							<div class="domore">
									<a class="more" href="${webroot}activity/SubmitActs.html?selected=released">查看更多 <i
										class="fa fa-arrow-right detials" aria-hidden="true"></i>
									</a>
							</div>
						</div>
					</div>
					</shiro:hasPermission>
					</c:if>
					<c:if test="${douNum ne 0}">
					<shiro:hasPermission name="/document/pubDocument.do">
					<div class="col-sm-3 ">
						<div class="boder-start purple">
							<div class="detials">
								<div class="number">${douNum }</div>
								<div class="desc">待发布的文档数</div>
							</div>
							<div class="domore">
									<a class="more" href="${webroot}document/Released.html">查看更多 <i
										class="fa fa-arrow-right detials" aria-hidden="true"></i>
									</a>
							</div>
						</div>
					</div>
					</shiro:hasPermission>
					</c:if>
					<div id="main" class="col-sm-12"
						style="width: 100%; height: 150px; text-align: left;">
						
						<shiro:hasPermission name="/activity/regist.html">
							<a href="${webroot}activity/regist.html" title="活动管理">
								<div class="span divicon green" style="display: inline-block;">
									<i class="fa fa-book fa-3x" aria-hidden="true"></i>
								</div>
							</a>
						</shiro:hasPermission>
						<shiro:hasPermission name="/record/list.html"> 
							<a href="${webroot}record/list.html" title="培训信息">
								<div class="span divicon purple" style="display: inline-block;">
									<i class="fa fa-newspaper-o fa-3x" aria-hidden="true"></i>
								</div>
							</a> 
						</shiro:hasPermission>
						<shiro:hasPermission name="/vols/list.html">
							<a href="${webroot}vols/list.html" title="志愿者管理">
								<div class="span divicon blue" style="display: inline-block;">
									<i class="fa fa-user fa-3x" aria-hidden="true"></i>
								</div>
							</a>
						</shiro:hasPermission>
						<shiro:hasPermission name="/document/list.html">
							<a href="${webroot}document/list.html" title="文档发布">
								<div class="span divicon green" style="display: inline-block;">
									<i class="fa fa-file-text-o fa-3x" aria-hidden="true"></i>
								</div>
							</a> 
						</shiro:hasPermission>
						<shiro:hasPermission name="/activity/registrationManage.html">
							<a href="${webroot}activity/registrationManage.html" title="活动报名">
								<div class="span divicon green" style="display: inline-block;">
									<i class="fa fa-user-plus fa-3x" aria-hidden="true"></i>
								</div>
							</a>
						</shiro:hasPermission>
					</div>

					<div class="row">

						<div class="col-sm-12">
							<form class="form-inline" id="searchForm" action="${webroot}document/list.do"  method="post" onsubmit="return false">
								<input type="hidden" name="page" id="currentPageInput" value="1"/> 
		           				<input type="hidden" name="rows" id="pageSizeInput" value="10"/>
		           				<input type="hidden" name="channelId" id="channelId" value="6">
		           				<input type="hidden" name="siteId" id="siteId" value="1"> 
							</form>

							<div class="dataTables">
								<div
									class="dataTables_wrapper form-inline dt-bootstrap no-footer">
									<c:if test="${not empty docNoticeList}">
									<table aria-describedby="example-2_info" role="grid"
										id="roleList"
										class="table table-bordered table-hover table-striped dataTable no-footer">
										<tr role="row">
				                            <th style="text-align: center;width: 60px">编号</th>
				                            <th style="text-align: center;width: 30%">文档标题</th>
				                            <th style="text-align: center;width: 100px">创建时间</th>
				                            <th style="text-align: center;width: 10%">发稿人</th>
		                       		    </tr>
										<thead>
											<tr role="row">
												<td colspan="4">
												<font style="color: #e61400; font-size: 25px; height: 34">
													<i class="fa fa-star-o" aria-hidden="true"></i> 内部通知公告
												</font> 
												</td>
											</tr>
						
										</thead>
										<tbody class="middle-align" id="pageData">
											
												<c:forEach items="${docNoticeList}" var="item" varStatus="status">
													<tr>
														<td width="40">${ status.index + 1}</td>
							                            <td>
							                                <div>
							                                   <span><a title='{{item.id}}-{{item.title}}' href='javascript:void(0)' onclick="viewDocument(${item.id})">${item.title}</a></span>
							                                </div>
							                            </td>
							                            <td>
							                                <div>
							                                   <span>
							                                   	<fmt:formatDate value="${item.crTime }" pattern="yyyy-MM-dd hh:mm"/>
							                                   </span>
							                                </div>
							                            </td>
							                            <td>
							                                <div>
							                                   <span>${item.crUser}</span>
							                                </div>
							                            </td>
													</tr>
												</c:forEach>
											
										</tbody>
									</table>
									</c:if>
									<c:if test="${empty docNoticeList}">
										<p align="center">没有相关数据</p>
									</c:if>
								</div>
							</div>
						</div>

					</div>


				</div>

			</div>

		</div>
	</div>

	<jsp:include page="include/footer.jsp"></jsp:include>

	<script src="${sr}js/jquery.min.js" type="text/javascript"></script>
	<script src="${sr}js/config.js" type="text/javascript"></script>
	<script src="${sr}js/baseutil.js" type="text/javascript"></script>
	<script src="${sr}js/main.js" type="text/javascript"></script>
	<script src="${sr}layui.js" type="text/javascript"></script>
	<script>
		layui.use([ 'jquery', 'layer', 'laytpl' ], function() {
			initMenu();
			
		});
		
		//查看文档
		function viewDocument(id){
			var layer = layui.layer;
			var index = layer.open({
				  type: 2,
				  title:"查看文档",
				  area: ['1000px', '580px'],
				  skin: 'view-class',
				  maxmin: true,
				  scrollbar: false,
				  content: "${webroot}index/viewDocument.html?docId="+id,
				});	
		}
		
	</script>

</body>
</html>