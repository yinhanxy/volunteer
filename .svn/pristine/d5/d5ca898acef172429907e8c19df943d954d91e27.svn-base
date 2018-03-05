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
    	.v-form-footer {
		    padding-top: 0;
		    height: 50px;
		    position: absolute;
		    line-height: 60px;
		    background: #fff;
		    right: 20px;
		    bottom: 0px;
		}
    </style>
</head>
<body class="v-page-body">
	<div class="page-container">
		<div class="panel">	
				<form id="addChannelForm" action="${webroot}channel/addChannel.do">
					<div>
					<table class="table table-bordered dataTable no-footer">
						<tr class="row">
							<td class="col-xs-2"><label for="inputEmail1">所属站点</label></td>
							<td class="col-xs-10">
									<input type="hidden" name="siteId" value="${site.id}"/>
									<input type="text"  class="form-control" name="siteName" value="${site.siteName}" disabled="disabled"/>
                   			</td>
						</tr>
						<tr class="row">
							<td class="col-xs-2"><label for="inputEmail1">父栏目</label></td>
							<td class="col-xs-10">
									<input type="hidden" name="parentChannelId" id="parentId" value="${empty channel.id?0:channel.id}"/>
									<c:if test="${empty channel.id}">
										<div class="input-group" onclick='selectChannel()'>
									</c:if>
											<input type="text"  class="form-control" name="parentChannel" id="parentName" value="${channel.chnlName}" readonly="readonly" placeholder="上级栏目名称"/>
						       		<c:if test="${empty channel.id}">
						       			<span class="input-group-btn">
								       		 <button type="button" id="channelButton" class="btn btn-primary  "><i class="fa fa-search"></i>
								             </button> 
							       		</span>
										</div>
									</c:if>
                   			</td>
						</tr>
						
						<tr class="row">
							<td class="col-xs-2"><label for="inputEmail1">唯一标识<small><code >*</code></small></label></td>
							<td class="col-xs-10">
								<input type="text"  class="form-control" name="channelName" id="channelName" placeholder="栏目名称"   
									data-validation="custom" data-validation-regexp="^[a-zA-Z0-9_\u4e00-\u9fa5]{2,50}$"
			                   		data-validation-error-msg-custom="由2-50个汉字、数字、英文和下划线组合而成"
			                   		data-validation-error-msg-container="#nError"/>
                   			</td>
						</tr>
						<tr class="row">
							<td class="col-xs-2"><label for="inputEmail1">显示名称<small><code >*</code></small></label></td>
							<td class="col-xs-10">
									<input type="text"  class="form-control" name="channelDesc" id="channelDesc" placeholder="显示名称"   
									data-validation="custom" data-validation-regexp="^[a-zA-Z0-9_\u4e00-\u9fa5]{2,50}$"
			                   		data-validation-error-msg-custom="由2-50个汉字、数字、英文和下划线组合而成"
			                   		data-validation-error-msg-container="#nError"/>
                   			</td>
						</tr>
						<tr class="row">
							<td class="col-xs-2"><label for="inputEmail2">前一栏目<small><code >*</code></small></label></td>
							<td class="col-xs-10">
								<select class="form-control" name="previousChannel" id="previousChannel">
							    	<option value="-1">最前面</option>
							    	<c:if test="${!empty channels}">
							    		<c:forEach items="${channels}" var="subChannel" varStatus="status">
							    			<c:if test="${status.last}">
							    				<option value="${subChannel.id}" selected>${subChannel.desc }</option>
							    			</c:if>
							    			<c:if test="${!status.last}">
								    			<option value="${subChannel.id}">${subChannel.desc }</option>
								    		</c:if>
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
	  			<button type="button" id="sub" class="btn btn-sm btn-success" ><i class="fa fa-plus"></i>新建</button>
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
		$("#addChannelForm input,select").on("change",function(){
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
			if( !$('#addChannelForm').isValid({},conf, true)) {
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
				postFormData("addChannelForm",true,addChannel);
		    }
			
		});
});
	
	function clearValid(formId){
		var els = $("#"+formId+" :input");
		els.removeClass("valid");
	}
	
	//选择上级栏目
	function selectChannel(){
		var layer = parent.layer;
		layer.open({
		  type: 2,
		  title:"选择父栏目",
		  area: ['300px', '450px'],
		  maxmin: true,
		  scrollbar: true,
		  content: "${webroot}channel/selectChannel.html",
		  btn : [ '确定', '取消' ], 
		  yes : function(index, layero) {
				var iframeWin = top.window[layero.find('iframe')[0]['name']]; 
				var data=iframeWin.selectChannel();
				$("#parentName").val(data.desc);
				var parentId=data.id;
				if(!parentId){
					parentId=0;
				}
				$("#parentId").val(parentId);
				parentChannel();
		  }
		});
	}
	
	/* //选择栏目站点
	function selectChannel(){
		var layer = parent.layer;
		layer.open({
		  type: 2,
		  title:"选择站点",
		  area: ['300px', '450px'],
		  maxmin: true,
		  scrollbar: false,
		  content: ["${webroot}channel/selectSite.html","no"],
		  btn : [ '确定', '取消' ], 
		  yes : function(index, layero) {
				var iframeWin = top.window[layero.find('iframe')[0]['name']]; 
				var data=iframeWin.selectOrg();
				$("#parentName").val(data.name);
				var parentId=data.id;
				if(!parentId){
					parentId=0;
				}
				$("#parentId").val(parentId);
		  }
		});
	} */
	
	function closeFrame(){
		var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
		parent.layer.close(index);
	}
	
	/**
	**添加栏目的回调函数
	*/
	function addChannel(data){
		var layer = layui.layer;
		var message;
		if(data && data.success){
			message=data.message;
			layer.alert(message, {icon: 6},function(index){
			    closeFrame();
			    var parentId=$("#parentId").val();
			    var channelDesc=$("#channelDesc").val();
			    var id=data.addChannelId;
			    var order=data.order;
			    var node={ id:id, pId:parentId, name:channelDesc};
			    parent.addRefresh(parentId,node,order);
			  });
		}else{
			errorTips(data);
			clearValid("addChannelForm");
		}
		$("#sub").prop("disabled",false);
	}
	
	function parentChannel(){
		var parentId=$("#parentId").val();
		if(parentId){
			var url="${webroot}channel/getSubChannel.do";
			var params={"id":parentId};
			postData(url, params, true, channelResult);
		}
	}
	
	function channelResult(data){
		if(data.success){
			$("#previousChannel").html("");
			$("#previousChannel").html("<option value=\"-1\">最前面</option>");
			var channels=data.channels;
			var option="";
			if(channels){
				for (var i = 0; i < channels.length; i++) {
					if(i<channels.length-1){
						option+="<option value='"+channels[i].id+"'>"+channels[i].desc+"</option>";
					}else{
						option+="<option value='"+channels[i].id+"' selected>"+channels[i].desc+"</option>";
					}
				}
				$("#previousChannel").append(option);	
			}
		}	
	}
	</script>
</body>
</html>