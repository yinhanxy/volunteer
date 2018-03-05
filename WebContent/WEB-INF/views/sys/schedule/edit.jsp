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
	<link href="${sr}ztree/metroStyle/style.css" rel="stylesheet">
	<link href="${sr}css/jquery.mCustomScrollbar.min.css" rel="stylesheet">
    <!--[if lt IE 9]>
      <script src="${sr}js/html5shiv.min.js"></script>
      <script src="${sr}js/respond.min.js"></script>
    <![endif]-->
    <style type="text/css">
		ul.ztree {margin-top: 10px;border: 1px solid #ccc;border-radius:4px;background: #f0f6e4;width:220px;height:320px;overflow-x:auto;}
		.ztree span{display:inline-block;}
    </style>
</head>
<body class="v-page-body">
	<div class="page-container">
		<div class="panel">
			<form id="addScheduleForm" action="${webroot}schedule/edit.do" method="post">
				<div class="row">
				<input type="hidden" name="jobId" value="${schedule.jobId }">
				<table class="table table-bordered dataTable no-footer">
					<tr>
						<td class="col-xs-2"><label for="inputEmail1">任务名称<small><code >*</code></small></label></td>
						<td class="col-xs-10">
							 <input type="text" class="form-control" name="jobName" value="${schedule.jobName }" disabled="disabled"/>
                  		</td>
					</tr>
					<tr>
						<td class="col-xs-2"><label for="inputEmail1">JAVA BEAN<small><code >*</code></small></label></td>
						<td class="col-xs-10">
							<input type="text" class="form-control" name="beanName" placeholder="JAVA BEAN" disabled="disabled" value="${schedule.beanName }"/>
                  			</td>
					</tr>
					
					<tr>
						<td><label for="inputEmail2">参数值<small></small></label></td>
						<td >
						<input type="text" class="form-control"  name="params" id="params" style="background:#fff;cursor:pointer;" placeholder="参数值"  value="${schedule.params }" />
                  		</td>
					</tr>
					<tr>
						<td class="col-xs-2"><label for="inputEmail2">Corn表达式<small><code >*</code></small></label></td>
						<td class="col-xs-10">
							<div class="input-group">
							<input type="text" class="form-control" name="cronExpression" placeholder="Cron表达式"  value="${schedule.cronExpression }" />
							<div class="input-group-addon" id="cronBtn"><a href="javascript:void(0);"  style="text-decoration:none;color:#555;">在线生成</a></div>
							</div>
                  		</td>
					</tr>
					<tr>
						<td class="col-xs-2"><label for="inputEmail2">备注</label></td>
						<td class="col-xs-10">
							<textarea rows="4" style="resize:none;" cols="50" class="form-control"  placeholder="备注"  name="remark">${schedule.remark }</textarea>
                  		</td>
					</tr>
				</table>
				</div>
		</form>
		</div>		
	</div>
	
	<div class="v-form-footer">
	  	<button type="button" id="sub" class="btn btn-sm btn-success"><i class="fa fa-plus"></i>保存</button>
	   	<button type="button" class="btn btn-sm" onclick="closeFrame()"><i class="fa fa-remove"></i>关闭</button>
	</div>
	

	
	<script src="${sr}js/jquery.min.js" type="text/javascript"></script>
	<script src="${sr}js/config.js" type="text/javascript"></script>
	<script src="${sr}js/baseutil.js" type="text/javascript"></script>
	<script src="${sr}js/jquery.form-validator.min.js" type="text/javascript"></script>
	<script src="${sr}js/jquery-ui.min.js" type="text/javascript"></script>
	<script src="${sr}layui.js" type="text/javascript"></script>
	<script src="${sr}ztree/jquery.ztree.core.js" type="text/javascript"></script>
	<script src="${sr}js/jquery.mousewheel-3.0.6.min.js" type="text/javascript"></script>
	<script src="${sr}js/jquery.mCustomScrollbar.min.js" type="text/javascript"></script>
	<script type="text/javascript">
	layui.use(['jquery','layer','laytpl'],function (){
		
	});

	var errors =[],
	conf = {
		onElementValidate : function(valid, $el, $form, errorMess) {
		     if( !valid ) {
		    	errors.push({el: $el, error: errorMess});
				return ;
		     }
			  	
		  } 
		 
	  };
	
	$('#cronBtn').on('click',function (){
		var layer = layui.layer;
		//iframe层-父子操作
		layer.open({
		  type: 2,
		  title:"生成Cron表达式",
		  area: ['680px', '420px'],
		  maxmin: false,
		  scrollbar: true,
		  content: "${webroot}cron/index.html"
		});
	})
	$('#sub').on('click', function() {
		errors =[];
		if( !$('#addScheduleForm').isValid({},conf, true)) {
			if(errors && errors.length>0){
				for (var i = 0; i < errors.length; i++) {
					var el=errors[i].el;
					var errorMess=errors[i].error;
					if(i==0){
						layui.layer.tips(errorMess, el,{
				    		  tips: [1, '#f57a78'] //还可配置颜色
				    	 });
					}else{
						el.attr("style","");
						el.removeClass("error");
					}
				}
			}
			clearValid("addScheduleForm");
			return false;
	   	}else {
	   		clearValid("addScheduleForm");
	   		$("#sub").prop("disabled",true);
			postFormData("addScheduleForm",true,editResult);
	    } 
	});
	
	
	function clearValid(formId){
		var els = $("#"+formId+" :input");
		els.removeClass("valid");
	}
	/**
	**编辑菜单的回调函数
	*/
	function editResult(data){
		var layer = layui.layer;
		var message;
		if(data && data.success){
			message=data.message;
			layer.alert(message, {icon: 6},function(index){
			    parent.loadData();
			    closeFrame();
			  });
		}else{
			errorTips(data);
			clearValid("addScheduleForm");
		}
		$("#sub").prop("disabled",false);
	}



	function closeFrame(){
		var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
		parent.layer.close(index);
	}
	</script>
</body>
</html>