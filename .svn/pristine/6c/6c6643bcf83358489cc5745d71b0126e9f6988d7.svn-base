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
</head>
<body class="v-page-body">
	<div class="page-container">
		<div class="panel">	
				<form id="editChannelForm" action="${webroot}channel/saveChannel.do">
					<div>
					<table class="table table-bordered dataTable no-footer">
						<tr class="row">
							<td class="col-xs-2"><label for="inputEmail1">所属站点</label></td>
							<td class="col-xs-10">
									<input type="text"  class="form-control" name="siteName" value="${site.siteName}" disabled="disabled"/>
                   			</td>
						</tr>
						<tr class="row">
							<td class="col-xs-2"><label for="inputEmail1">父栏目</label></td>
							<td class="col-xs-10">
									<input type="text"  class="form-control" name="parentChannel" disabled="disabled" value="${empty parentChannel.chnlName?'无':parentChannel.chnlName}"/>
                   			</td>
						</tr>
						<%-- <td class="col-xs-2"><label for="inputEmail1">栏目名称<small><code >*</code></small></label></td>
							<td class="col-xs-4">
								<div class="input-group" ${empty org.id ? "onclick='selectOrg()'":""}>
									<input type="hidden" name="parentId"  id="parentId" value="${org.id}"/>
									<input type="text"  class="form-control" name="parentName" id="parentName" readonly="readonly" placeholder="上级机构名称" value="${org.name}"
									data-validation="required" data-validation-error-msg-required="此项为必填项" data-validation-error-msg-container="#nError"/>
									<span class="input-group-btn">
							       		 <button type="button" id="orgButton" class="btn   btn-primary  "><i class="fa fa-search"></i>
							             </button> 
						       		</span>
								</div>
                   			</td> --%>
						<tr class="row">
							<td class="col-xs-2"><label for="inputEmail1">唯一标识<small><code >*</code></small></label></td>
							<td class="col-xs-10">
									<input type="hidden" name="channelId" id="channelId" value="${channel.id}">
									<input type="hidden" name="parentId" id="parentId" value="${channel.parentId}">
									<input type="text"  class="form-control" name="channelName" id="channelName" value="${channel.chnlName}"
									data-validation="custom" data-validation-regexp="^[a-zA-Z0-9_\u4e00-\u9fa5]{2,50}$"
			                   		data-validation-error-msg-custom="由2-50个汉字、数字、英文和下划线组合而成"
			                   		data-validation-error-msg-container="#nError"/>
                   			</td>
						</tr>
						<tr class="row">
							<td class="col-xs-2"><label for="inputEmail1">显示名称<small><code >*</code></small></label></td>
							<td class="col-xs-10">
									<input type="text"  class="form-control" name="channelDesc" id="channelDesc" value="${channel.chnlDesc}"   
									data-validation="custom" data-validation-regexp="^[a-zA-Z0-9_\u4e00-\u9fa5]{2,50}$"
			                   		data-validation-error-msg-custom="由2-50个汉字、数字、英文和下划线组合而成"
			                   		data-validation-error-msg-container="#nError"/>
			                   		<input type="hidden" name="order" value="${channel.order}">
                   			</td>
						</tr>
						<tr class="row">
							<td class="col-xs-2"><label for="inputEmail2">前一栏目<small><code >*</code></small></label></td>
							<td class="col-xs-10">
								<select class="form-control" name="previousChannel" >
							    	<option value="-1">最前面</option>
							    	<c:if test="${!empty channels}">
							    		<c:forEach items="${channels}" var="broChannel">
								    		<option value="${broChannel.id}" ${broChannel.order==channel.order-1?"selected":"" }>${broChannel.chnlDesc }</option>
							    		</c:forEach>
							    	</c:if>
	           					</select>
                   			</td>
						</tr>
					</table>
					</div>
			    </form>
		</div>
	</div>
	
	<div class="v-form-footer">
	  			<button type="button" id="sub" class="btn btn-sm btn-success" ><i class="fa fa-plus"></i>保存</button>
	   			<button type="button" class="btn btn-sm" onclick="closeFrame()"><i class="fa fa-remove"></i>关闭</button>
	</div>
	
	<script src="${sr}js/jquery.min.js" type="text/javascript"></script>
	<script src="${sr}js/config.js" type="text/javascript"></script>
	<script src="${sr}js/baseutil.js" type="text/javascript"></script>
	<script src="${sr}js/jquery.form-validator.min.js" type="text/javascript"></script>
	<script src="${sr}js/jquery-ui.min.js" type="text/javascript"></script>
	<script src="${sr}layui.js" type="text/javascript"></script>
	<script type="text/javascript">
	layui.use(['jquery','layer','laytpl'], function(){
		$("#editChannelForm input,select").on("change",function(){
			$(this).validate(function(valid, elem) {
			});
		});
		$.validate({
			validateOnBlur : false, 
			onElementValidate : function(valid, $el, $form, errorMess) {
				 if(!valid){
					 layui.layer.tips(errorMess, $el,{
			    		  tips: [1, '#f57a78']
			    	 });
				 }
			}
		});
		
	    $('#text-area').restrictLength($('#max-len'));
		
		var errors=[];
		var conf = {
				validateOnBlur : false, 
				onElementValidate : function(valid, $el, $form, errorMess) {
			     if( !valid ){
			    	errors.push({el: $el, error: errorMess});
			     }
			  }
		 };
		$('#sub').on('click', function() {
			errors=[];
			var typeEl=$("input[name='type']:checked");
			if( !$('#editChannelForm').isValid({},conf, true)) {
				isForm=false;
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
				return false;
		   	}else {
		   		$("#sub").prop("disabled",true);
				postFormData("editChannelForm",true,editChannel);
		    }
			
		});
});
	
	function clearValid(formId){
		var els = $("#"+formId+" :input");
		els.removeClass("valid");
	}
	
	function closeFrame(){
		var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
		parent.layer.close(index);
	}
	/**
	**添加栏目的回调函数
	*/
	function editChannel(data){
		var layer = layui.layer;
		var message;
		if(data && data.success){
			message=data.message;
			layer.alert(message, {icon: 6},function(index){
			    closeFrame();
			    var channelDesc=$("#channelDesc").val();
			    var channelId=$("#channelId").val();
			    var parentId=$("#parentId").val();
			    console.log("parentId:"+parentId);
			    var node={ id:channelId, pId:parentId, name:channelDesc};
			    var order=data.order;
			    console.log(order);
			    parent.editRefresh(parentId,node,order);
			  });
		}else{
			errorTips(data);
			clearValid("editChannelForm");
		}
		$("#sub").prop("disabled",false);
	}
	</script>
</body>
</html>