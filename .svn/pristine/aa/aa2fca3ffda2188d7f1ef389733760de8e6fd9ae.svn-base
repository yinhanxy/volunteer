<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
    <!--[if lt IE 9]>
      <script src="${sr}js/html5shiv.min.js"></script>
      <script src="${sr}js/respond.min.js"></script>
    <![endif]-->
</head>
<body class="v-page-body">
	<div class="page-container" >
		<div class="panel" id="mainform" style="margin-bottom: 0px;">
				<div class="row">
					<table class="table table-bordered dataTable no-footer" style="margin-bottom: 10px;">
						<tr>
							<td class="col-xs-3">
						    	<label >姓名</label>
						    </td>
						    <td class="col-xs-9">
								${volunteerView.realName}
						    </td>
					    </tr>
				    	<tr>
							<td>
					    		<label >证件号</label>
					   		</td>
					   		<td>
								${volunteerView.idcard}
		                   	</td>
				    	 </tr>
				    	<tr>
							<td>
						    	<label >所属服务队</label>
						    </td>
						    <td>
								${serName}
		                   	</td>
				    	</tr>
				    	<tr>
				    		<td>
						    	<label >服务时长</label>
						    </td>
							<td>
								${empty volunteerView.serviceHour?0:volunteerView.serviceHour}(小时)
						    </td>
					    </tr>
					    <tr>
				    		<td>
						    	<label >星级</label>
						    </td>
							<td>
								<c:if test="${!empty volunteerView.star}">
									<c:forEach  begin="1" end="${volunteerView.star}">
					               		<img src="${sr}/images/star.png">
					                </c:forEach>
					                <c:forEach  begin="${volunteerView.star+1}" end="5">
					                	<img src="${sr}/images/noStar.png">
					                </c:forEach>
				                </c:if>
				                <c:if test="${empty volunteerView.star}">
				                	暂无
				                </c:if>
						    </td>
					    </tr>
					    <tr>
					    	<td height="150px" style="vertical-align: middle;">
						    	<label >评价理由</label>
						    </td>
							<td>
								<textarea style="width: 100%;height: 130px" readonly="readonly">${volunteerView.evaluateContent}</textarea>
						     </td>
				    	</tr>
					</table>
					<div class="pull-left tableTools-container" style="" id="optional">
					<a class="btn btn-success  btn-sm" onclick="viewEvaluateHistory('${volunteerView.id}','${serName}')" ><i class="fa fa-search">评定记录</i></a>
			   	</div>
				</div>
		</div>
	</div>
	<div class="v-form-footer">
	   			<button type="button" class="btn btn-sm" onclick="closeFrame()"><i class="fa fa-remove"></i>关闭</button>
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
		var layer = layui.layer;
		layer.config({
			  extend: 'myskin/style.css'
			});
	})
	function closeFrame(){
		var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
		parent.layer.close(index);
	}
	
	function viewEvaluateHistory(id,serName){
		var layer = parent.layui.layer;
		var index=layer.open({
		  type: 2,
		  title:"查看星级评定记录",
		  skin: 'view-class',
		  area: ['950px', '670px'],
		  scrollbar: false,
		  content: ["${webroot}star/starEvaluateList.html?vId="+id+"&serName="+serName],
		 /*   btn: ['<i class="fa fa-remove"></i>关闭'],
		  yes : function(index, layero) {
				var iframeWin = top.window[layero.find('iframe')[0]['name']]; 
				iframeWin.closeFrame();
			}, */
		  //关闭层后的回调函数
		  end:function(){
			  
		  }
		});
		layer.full(index);
	}
	</script>
</body>
</html>