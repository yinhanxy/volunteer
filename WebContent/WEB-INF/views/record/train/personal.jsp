<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
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
   <%--  <link href="${sr}css/xenon.min.css" rel="stylesheet"> --%>
	<link href="${sr}css/base.css" rel="stylesheet">
	<link href="${sr}css/new.css" rel="stylesheet">
	<link href="${sr}css/iCheck/blue.css?v=1.0.2" rel="stylesheet">
    <!--[if lt IE 9]>
      <script src="${sr}js/html5shiv.min.js"></script>
      <script src="${sr}js/respond.min.js"></script>
    <![endif]-->
    <style type="text/css">
    	.table-bordered > thead > tr > th {
    		background-color: #F5F5F6;
    	}
    	body{
		   	font-family: "open sans", "Helvetica Neue", Helvetica, Arial, sans-serif;
		    font-size: 13px;
		    color: rgb(103, 106, 108);
    	}
    	.footer-right{
    		padding-top: 0;
		    height: 40px;
		    position: fixed;
		    line-height: 60px;
		    background: #fff;
		    z-index: 99;
		    bottom: 20px;
		    right: 20px;
		    float: right;
    	}
    	a.layui-layer-btn1 {
		        background-color: #DCDCDC;
    			color: #000040;
		}    
    </style>
    
</head>
<body >
	
	<div style="padding: 10px;" class="form-inline">
		<div class="panel panel-default">
		  <!-- <div class="panel-heading">角色信息</div> -->
		  <!-- <div class="panel-body"> -->
			  <table class="table" style="margin:0">
			  		<tbody>
					<tr style="background-color: #f9f9f9;">
						<td width=15% style="text-align: center;">培训名称：</td>
						<td width=35%  id="name" >${trRecord.trName}</td>
						<td width=15% style="text-align: center;">培训时间：</td>
						<td width=35% >${trRecord.trTime}</td>
					</tr>
					<tr style="background-color: #f9f9f9;">
						<td style="text-align: center;">培训内容：</td>
						<td colspan="3">${trRecord.trContent}</td>
					</tr>
					</tbody>
				</table>
		  <!-- </div> -->
		
		</div>
		
		<!-- <div style="border-bottom: 2px solid #f5f5f5;"></div> -->
		<div  style="padding-top: 5px;">
          	<font style="font-weight: bold;font-size: 20px">参与志愿者列表</font>
          	
	        <a class="btn btn-info  btn-sm" onclick="upload(${trRecord.id})" id="upload" style="margin-bottom: 10px; float: right;margin-left: 10px">
            	<i class="fa fa-upload">上传人员记录</i>
            </a>
            
	        <a class="btn btn-danger btn-sm" onclick="delTrainVol()" id="del" style="margin-bottom: 10px;float: right;margin-left: 10px">
            	<i class="fa fa-trash-o">移除志愿者</i>
            </a>
	           
	        <a class="btn btn-success btn-sm" onclick="addVol(${trRecord.id})" style="margin-bottom: 10px; float: right">
                <i class="fa fa-plus">新增志愿者</i>
            </a>   
	                    
		    <form class="form-inline" name="searchForm" id="searchForm" action="${webroot}record/viewTrRecord.do">
						<input type="hidden" name="id" value="${trRecord.id }" />
						<input type="hidden" name="page" id="currentPageInput" />
			           	<input type="hidden" name="rows" id="pageSizeInput" />
			</form>           
         </div>
		
		<table id="trRecordList" class="table table-bordered table-hover table-striped">
		                        <thead>
		                        <tr role="row">
		                        	<th width="40" style="text-align:center">
										<input type="checkbox" style="position: relative;" class="ace" id="allcheck" value="">
									</th>
		                            <th width="60" style="text-align:center">序号</th>
		                            <th style="text-align:center;width:150px">姓名</th>
		                            <th style="text-align:center;width:150px">性别</th>
		                            <th style="text-align:center">联系方式</th>
		                            <th style="text-align:center">证件号</th>
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
										<td width="40" style="text-align:center">
											<input type="checkbox" class="ace" value="{{item.id}}">
										</td>
			                            <td align="center">{{(d.pageSize * (d.pageNum-1))+index+1}}</td>
			                            <td>
			                                <div>
			                                   <span>{{item.realName}}</span>
			                                </div>
			                            </td>
										<td>
			                                <div>
			                                   <span>{{item.sex}}</span>
			                                </div>
			                            </td>
			                             <td style="text-align:center">
			                                <div>
			                                   <span>{{item.mobile }}</span>
			                                </div>
			                            </td>
			                             
			                             <td align="center">
			                                <div>
			                                   <span>{{item.idcard }}</span>
			                                </div>
			                            </td>
			                        </tr>
									{{#  }); }}
									</script>						
	</div>
	<script src="${sr}js/jquery.min.js" type="text/javascript"></script>
	<script src="${sr}js/config.js" type="text/javascript"></script>
	<script src="${sr}js/baseutil.js" type="text/javascript"></script>
	<script src="${sr}js/jquery-ui.min.js" type="text/javascript"></script>
	<script src="${sr}layui.js" type="text/javascript"></script>
	<script src="${sr}js/iCheck/icheck.min.js?v=1.0.2" type="text/javascript"></script>
	<script src="${sr}js/iCheck/custom.min.js?v=1.0.2" type="text/javascript"></script>
	
<script type="text/javascript">
	layui.use(['jquery','layer','laytpl'], function(){
		
		postFormData("searchForm",true,searchResult);

		$(document).ajaxComplete( function(event, jqXHR, options){
			$('input').iCheck({
				  checkboxClass: 'icheckbox_square-blue',
				  radioClass: 'iradio_square-blue',
				  increaseArea: '20%' // optional
			});
			
			var i=0;
			$("#allcheck").on("ifClicked",function () {
				var flag=(i++)%2==0?"check":"uncheck";
				$("#pageData  input").each(function(){
				 	 $(this).iCheck(flag);  
				}); 
		    });
		});


	  	
	});
	
	function closeFrame(){
		 var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
		parent.layer.close(index);
	}
	
	//跳转到上传页面
	function upload(id){
		var layer = parent.layer;
		var index=layer.open({
		  type: 2,
		  title:"上传人员记录",
		  skin: 'edit-class',
		  area: ['780px'],
		  scrollbar: true,
		  content: "${webroot}record/toUpLoad.html?tId="+id,
		  btn : [ '<i class="fa fa-floppy-o"></i>确定','<i class="fa fa-remove"></i>取消'], 
		  yes :function(index, layero) {
				var iframeWin = top.window[layero.find('iframe')[0]['name']]; 
				var result=iframeWin.addVols();
				iframeWin.closeFrame();
				return result;
	  		},
		  btn2 :function(index, layero) {
			  	var iframeWin = top.window[layero.find('iframe')[0]['name']]; 
				iframeWin.closeFrame();
	  		},
		  //关闭层后的回调函数
		  end:function(){
			  loadPage();
		  }
		});
		layer.full(index);
	}
	
	
	//向培训记录添加志愿者
	function addVol(id){
		var layer = parent.layer;
		//iframe层-父子操作
		var index=layer.open({
		  type: 2,
		  title:"选择志愿者",
		  skin: 'edit-class',
		  //area: ['900px', '620px'],
		  area: ['800px', '640px'],
		  //maxmin: true,
		  scrollbar: true,
		 // fix:false,
		  content: "${webroot}record/addVol.html?tId="+id,
		  btn : [ '<i class="fa fa-floppy-o"></i>确定','<i class="fa fa-remove"></i>取消'], 
		  yes :function(index, layero) {
				var iframeWin = top.window[layero.find('iframe')[0]['name']]; 
				var result=iframeWin.addVolsUnderTrain();
				iframeWin.closeFrame();
				return result;
	 	 }, 
		  btn2 : function(index, layero) {
			  	var iframeWin = top.window[layero.find('iframe')[0]['name']]; 
				iframeWin.closeFrame();
	 	 },
		  end: function(){
			  loadPage();
		  }
		});
		layer.full(index);
	}
	
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
	
	
	//删除培训记录下的志愿者
	function delTrainVol(){
		
		var layer = layui.layer;
		var ids="";
		$("#pageData input:checked").each(function(){ 
			ids+=$(this).val()+","; 
		}); 
		ids=ids.substring(0,ids.length-1);
		if(!ids){
			layer.msg('请选择要移除的志愿者！',{time:1000});
			return false;
		}else{
			layer.confirm('确定要从该培训记录中移除选中的志愿者吗？', {icon: 3, title:'系统提示',
			  btn: ['确定','取消']
			}, function(){
				var url="${webroot}record/delTrainVol.do";
				params={"ids":ids};
				postData(url, params, true, delTrainVolResult); 
			});
		}
	}
	
	/**
	**回调函数
	*/
	function delTrainVolResult(data){
		var laytpl = layui.laytpl;
		var layer = layui.layer;
		var message=data.message;
		if(data && data.success){
			layer.msg(message, {icon: 1,time:1000});
			loadPage();
		}else{
			layer.msg(message, {icon: 2,time:1000});
		}
	}
	
	function loadPage(){
		$("#currentPageInput").val(1);
		$("selectType").val(0);
		postFormData("searchForm",true,searchResult);
	}
	
</script>
</body>
</html>