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
			         	<span >个人信息</span>
			        </div>
		      </div>
		      <div id="u1622_2" class="ax_default sx_cur5" style="cursor: pointer;">
			        <img class="img" src="${sr}images/u1622.png" tabindex="0">
			        <div class="u1623" >
			         <span >活动信息</span>
			        </div>
		      </div>
		      <div id="u1622_3" class="ax_default sx_cur5" style="cursor: pointer;">
			        <img class="img" src="${sr}images/u1622.png" tabindex="0">
			        <div class="u1623" >
			          <span >培训信息</span>
			        </div>
		      </div>
		      <div id="u1622_4" class="ax_default sx_cur5" style="cursor: pointer;">
			        <img class="img" src="${sr}images/u1622.png" tabindex="0">
			        <div class="u1623" >
			          <span >证书信息</span>
			        </div>
		      </div>
		<div class="panel" style="margin-bottom: 0;padding-top: 10px;width: 100%;position: absolute;top: 45px" id="mainform">
			
				<div class="row mn5">
					<table class="table table-bordered dataTable no-footer">
						<tr>
							<td class="col-xs-1">
						    	<label >姓名</label>
						    </td>
						    <td class="col-xs-2">
						    	${volunteerView.realName}
						    </td>
						    <td class="col-xs-1">
						    	<label >性别</label>
						    </td>
						    <td class="col-xs-2">
						    	${volunteerView.sex}
						    </td>
						    <td class="col-xs-1" rowspan="4" style="vertical-align:middle">
						    	<label >照片</label>
						    </td>
						    <td class="col-xs-2" rowspan="4" style="" align="center">
						    	<span style='${!empty volunteerView.imgUrl?"display:none":""}'><img width="164" height="150" border="0" src="${sr}images/photo.png" style="margin-bottom: 5px ;"></span>
								<img name="pic" alt="志愿者头像" width="164" height="150" border="0" src="" style="margin-bottom: 5px ;${empty volunteerView.imgUrl?'display:none':''}" >
						    </td>
					    </tr>
				    	<tr>
							<td class="col-xs-1">
						    	<label >身份证号</label>
						    </td>
						    <td class="col-xs-2">
						    	${volunteerView.idcard}
						    </td>
						    <td class="col-xs-1">
						    	<label >手机号</label>
						    </td>
						    <td class="col-xs-2">
						    	${volunteerView.mobile}
						    </td>
				    	 </tr>
				    	 <tr>
							<td class="col-xs-1">
						    	<label >民族</label>
						    </td>
						    <td class="col-xs-2">
						    	${volunteerView.nationName}
						    </td>
						    <td class="col-xs-1">
						    	<label >政治面貌</label>
						    </td>
						    <td class="col-xs-2">
						    	${volunteerView.polstatusName}
						    </td>
				    	 </tr>
				    	 <tr>
							<td class="col-xs-1">
						    	<label >籍贯</label>
						    </td>
						    <td class="col-xs-2">
						    	${volunteerView.naplace}
						    </td>
						    <td class="col-xs-1">
						    	<label >出生日期</label>
						    </td>
						    <td class="col-xs-2">
						    	<fmt:formatDate value="${volunteerView.birthday}" pattern="yyyy-MM-dd"/>
						    </td>
				    	 </tr>
				    	 <tr>
							<td class="col-xs-1">
						    	<label >学历</label>
						    </td>
						    <td class="col-xs-2">
						    	${volunteerView.educationName}
						    </td>
						    <td class="col-xs-1">
						    	<label >毕业学校</label>
						    </td>
						    <td class="col-xs-2">
						    	${volunteerView.grasch}
						    </td>
						    <td class="col-xs-1">
						    	<label >所学专业</label>
						    </td>
						    <td class="col-xs-2">
						    	${volunteerView.profession}
						    </td>
				    	 </tr>
				    	 <tr>
							<td class="col-xs-1">
						    	<label >居住地址</label>
						    </td>
						    <td class="col-xs-2" colspan="3">
						    	${volunteerView.address}
						    </td>
						    <td class="col-xs-1">
						    	<label >邮政编码</label>
						    </td>
						    <td class="col-xs-2">
						    	${volunteerView.postcode}
						    </td>
				    	 </tr>
				    	 <tr>
							<td class="col-xs-1">
						    	<label >工作单位</label>
						    </td>
						    <td class="col-xs-2">
						    	${volunteerView.work}
						    </td>
						    <td class="col-xs-1">
						    	<label >工作地址</label>
						    </td>
						    <td class="col-xs-2">
						    	${volunteerView.jobAddress}
						    </td>
						    <td class="col-xs-1">
						    	<label >电子邮箱</label>
						    </td>
						    <td class="col-xs-2">
						    	${volunteerView.email}
						    </td>
				    	 </tr>
				    	 <tr>
							<td class="col-xs-1">
						    	<label >职称</label>
						    </td>
						    <td class="col-xs-2">
						    	${volunteerView.jobTitleName}
						    </td>
						    <td class="col-xs-1">
						    	<label >职务</label>
						    </td>
						    <td class="col-xs-2">
						   		${volunteerView.duties}
						    </td>
						    <td class="col-xs-1">
						    	<label >QQ号码</label>
						    </td>
						    <td class="col-xs-2">
						    	${volunteerView.qq}
						    </td>
				    	 </tr>
				    	 <tr>
							<td class="col-xs-1" style="vertical-align: middle">
						    	<label >擅长技能<small ><code>*</code></small></label>
						    </td>
						    <td class="col-xs-2" colspan="3">
						    	${volunteerView.specility}
						    </td>
						    <td class="col-xs-1" style="vertical-align: middle">
						    	<label >分类</label>
						    </td>
						    <td class="col-xs-2">
						    	${volunteerView.serviceClass}
						    </td>
				    	 </tr>
				    	 <tr >
							<td class="col-xs-1" style="vertical-align:middle">
						    	<label >志愿服务经历</label>
						    </td>
						    <td class="col-xs-2" colspan="5" style="padding-bottom: 0">
									<textarea  style="width: 100%;height: 100px" readonly="readonly" >${volunteerView.serviceExperiment}</textarea>
						    </td>
				    	 </tr>
				    	 <tr >
							<td class="col-xs-1" style="vertical-align:middle">
						    	<label >志愿服务队</label>
						    </td>
						    <td class="col-xs-2" colspan="5" style="padding-bottom: 0">
								${teamName}
						    </td>
				    	 </tr>
				    	 <tr >
							<td class="col-xs-1" style="vertical-align:middle">
						    	<label >意向服务时间</label>
						    </td>
						    <td class="col-xs-2" colspan="5">
      					         <div class="checkbox">
								    <label>
								      <input type="checkbox" value="oneA" style="margin-top: 8px;" name="serviceTime" disabled="disabled"
								     	${fn:contains(volunteerView.serviceTime,"oneA")? "checked":""}>周一上午
								    </label>
								    
								    <label>
								      <input type="checkbox" value="twoA" style="margin-top: 8px;" name="serviceTime" disabled="disabled"
								       ${fn:contains(volunteerView.serviceTime,"twoA")? "checked":""}>周二上午
								    </label>
								    <label>
								      <input type="checkbox" value="threeA" style="margin-top: 8px;" name="serviceTime" disabled="disabled"
								      ${fn:contains(volunteerView.serviceTime,"threeA")? "checked":""}>周三上午
							    	</label>
							    	 <label>
								      <input type="checkbox" value="fourA" style="margin-top: 8px;" name="serviceTime" disabled="disabled"
								      ${fn:contains(volunteerView.serviceTime,"fourA")? "checked":""}> 周四上午
								    </label>
								    <label>
								      <input type="checkbox" value="fiveA" style="margin-top: 8px;" name="serviceTime" disabled="disabled"
								      ${fn:contains(volunteerView.serviceTime,"fiveA")? "checked":""}> 周五上午
								    </label>
								    <label>
								      <input type="checkbox" value="sixA" style="margin-top: 8px;" name="serviceTime" disabled="disabled"
								      ${fn:contains(volunteerView.serviceTime,"sixA")? "checked":""}> 周六上午
								    </label>
								    <label>
								      <input type="checkbox" value="sevenA" style="margin-top: 8px;" name="serviceTime" disabled="disabled"
								      ${fn:contains(volunteerView.serviceTime,"sevenA")? "checked":""}> 周日上午
								    </label>
								</div>
								 <div class="checkbox">
								    <label>
								      <input type="checkbox" value="oneP" style="margin-top: 8px;" name="serviceTime" disabled="disabled"
								      ${fn:contains(volunteerView.serviceTime,"oneP")? "checked":""}> 周一下午
								    </label>
								    <label>
								      <input type="checkbox" value="twoP" style="margin-top: 8px;" name="serviceTime" disabled="disabled"
								      ${fn:contains(volunteerView.serviceTime,"twoP")? "checked":""}> 周二下午
								    </label>
								    <label>
								      <input type="checkbox" value="threeP" style="margin-top: 8px;" name="serviceTime" disabled="disabled"
								      ${fn:contains(volunteerView.serviceTime,"threeP")? "checked":""}> 周三下午
								    </label>
								    <label>
								      <input type="checkbox" value="fourP" style="margin-top: 8px;" name="serviceTime" disabled="disabled"
								      ${fn:contains(volunteerView.serviceTime,"fourP")? "checked":""}> 周四下午
								    </label>
								    <label>
								      <input type="checkbox" value="fiveP" style="margin-top: 8px;" name="serviceTime" disabled="disabled"
								      ${fn:contains(volunteerView.serviceTime,"fiveP")? "checked":""}> 周五下午
								    </label>
								    <label>
								      <input type="checkbox" value="sixP" style="margin-top: 8px;" name="serviceTime" disabled="disabled"
								      ${fn:contains(volunteerView.serviceTime,"sixP")? "checked":""}> 周六下午
								    </label>
								    <label>
								      <input type="checkbox" value="sevenP" style="margin-top: 8px;" name="serviceTime" disabled="disabled"
								      ${fn:contains(volunteerView.serviceTime,"sevenP")? "checked":""}> 周日下午
								    </label>
								</div>
						    </td>
				    	 </tr>
				    	 <tr id="child">
							<td class="col-xs-1">
								<input type="hidden" id="input_child" value="${volunteer.child}">
						    	<label >是否经过监护人或<br>单位领导人同意<small ><code>*</code></small></label>
						    </td>
						    <td class="col-xs-2" colspan="5" style=" vertical-align: middle;" >
						    	${volunteer.child  ==1?"是":"不是"}
						    </td>
				    	 </tr>
				    	 <tr >
							<td class="col-xs-1" style="vertical-align:middle">
						    	<label >备注</label>
						    </td>
						    <td class="col-xs-2" colspan="5">
								<textarea style="width: 100%;height: 100px" readonly="readonly">${volunteerView.remark}</textarea>
						    </td>
				    	 </tr>
					</table>
				</div>
				<div id="u1628_state1" class="mn5 row" data-label="活动信息" style="visibility: inherit;; display: none;">
						参加活动 <span id="activityNum"></span>次，总服务时长${volunteerView.serviceHour}小时
		            	<table class="table table-bordered dataTable no-footer">
		            		<thead>
								<tr>
									<th style="width: 60px">序号</th>
									<th style="">活动名称</th>
									<th style="">活动服务队</th>
									<th style="width: 310px">活动开始-结束时间</th>
									<th style="width: 100px">服务时长</th>
									<th style="width: 160px">评价</th>
									<th style="width: 70px">状态</th>
								</tr>
							</thead>
							<tbody class="middle-align" id="pageData1">
			                        	
	                        </tbody>
						</table>
			          	<div class="row" style="display: none;" id="pageInfo1"></div>
						<div style="margin-top: -20px; line-height: 50px;border: 1px solid #ddd; border-top: 0;display: none;" id="messageDiv1">
			                        <p class="text-center">暂无数据</p>
			            </div>
				      	<script id="templateData1" type="text/html">
							{{#  layui.each(d.list, function(index, item){ }}	
			 							<tr role="row"
			                            data-toggle="collapse">
			                            <td style="text-align:center">{{(d.pageSize * (d.pageNum-1))+index+1}}</td>
			                            <td>
			                                <div>
			                                   <span >{{item.activityName}}</span>
			                                </div>
			                            </td>
			                             <td>
			                                <div>
			                                   <span >{{item.activityServiceTeam}}</span>
			                                </div>
			                            </td>
										<td title="{{getFormatDateByLong(item.activitySt)}}至{{getFormatDateByLong(item.activityEt)}}">
			                                <div>
			                                   <span>{{getFormatDateByLong(item.activitySt)}}至{{getFormatDateByLong(item.activityEt)}}</span>
			                                </div>
			                            </td>
										<td>
			                                <div>
			                                   <span>{{item.activityServiceHours}}小时</span>
			                                </div>
			                            </td>
										<td style="padding-bottom: 1px;">
			                               <input name="star" type="text" title="" value="{{item.activityServiceStar}}"/>
			                            </td>
										<td>
			                               {{item.activityStatusDesc}}
			                            </td>
			                        </tr>
								{{#  }); }}                       
						</script>
			        	<form id="joinedActivityForm" action="${webroot}vols/joinedActivityList.do" method="post" >
								<input type="hidden" name="volunteerId" id="volunteerId" value="${volunteerView.id}">
								<input type="hidden" name="page" id="currentPageInput" value="1"/>
						</form>
        		</div>
        		<div id="u1628_state1" class="mn5" data-label="培训信息" style="visibility: inherit;; display: none;">
			          <table class="table table-bordered dataTable no-footer">
						<thead>
							<tr >
								<th style="text-align: center;">序号</th>
								<th style="text-align: center;">培训名称</th>
								<th style="text-align: center;">培训时间</th>
								<th style="text-align: center;">组织培训服务队</th>
								<th style="text-align: center;">状态</th>
							</tr>
						</thead>
						<tbody class="middle-align" id="pageData2">
		                        	
                        </tbody>
					</table>
			          <div class="row" style="display: none;" id="pageInfo2"></div>
					<div style="margin-top: -20px; line-height: 50px;border: 1px solid #ddd; border-top: 0;display: none;" id="messageDiv2">
		                        <p class="text-center">暂无数据</p>
		            </div>
		            <script id="templateData2" type="text/html">
							{{#  layui.each(d.list, function(index, item){ }}	
			 							<tr role="row"
			                            data-toggle="collapse">
			                            <td style="text-align:center">{{(d.pageSize * (d.pageNum-1))+index+1}}</td>
			                            <td align="center">
			                                <div>
			                                   <span >{{item.trName}}</span>
			                                </div>
			                            </td>
			                             <td align="center">
			                                <div>
			                                   <span >{{item.trTime}}</span>
			                                </div>
			                            </td>
			                             <td align="center">
			                                <div>
			                                   <span >{{item.teamName }}</span>
			                                </div>
			                            </td>
										<td align="center">
			                                <div>
			                                   <span>{{item.status==1 ? '待进行' :item.status==2 ? '进行中' : '已完成'}}</span>
			                                </div>
			                            </td>
			                        </tr>
								{{#  }); }}                       
					</script>
		        	<form id="trRecordForm" action="${webroot}vols/volTrRecordList.do" method="post" >
							<input type="hidden" name="volunteerId" id="volunteerId" value="${volunteerView.id}">
							<input type="hidden" name="page" id="currentPageInput" value="1"/>
					</form>
        		</div>
        		<div id="certInfo" class="mn5" data-label="证书信息" style="visibility: inherit;; display: none;">
			          <div class="row ">
						<table class="table table-bordered dataTable no-footer">
							<tr>
								<td class="col-xs-1">
							    	<label >姓名</label>
							    </td>
							    <td class="col-xs-2">
							    	${volunteerView.realName}
							    </td>
							    <td class="col-xs-1">
							    	<label >证件号</label>
							    </td>
							    <td class="col-xs-2">
							    	${volunteerView.idcard}
							    </td>
							    <td class="col-xs-2" rowspan="4" style="" align="center">
							    	 <span style='${!empty volunteerView.imgUrl?"display:none":""}'><img width="164" height="150" border="0" src="${sr}images/photo.png" style="margin-bottom: 5px ;"></span>
									<img name="pic" alt="志愿者头像" width="164" height="150" border="0" src="" style="margin-bottom: 5px ;${empty volunteerView.imgUrl?'display:none':''}" >
							    </td>
						    </tr>
					    	<tr>
								<td class="col-xs-1">
							    	<label >性别</label>
							    </td>
							    <td class="col-xs-2">
							    	${volunteerView.sex}
							    </td>
							    <td class="col-xs-1">
							    	<label >出生年月</label>
							    </td>
							    <td class="col-xs-2">
							    	<fmt:formatDate value="${volunteerView.birthday}" pattern="yyyy-MM-dd"/>
							    </td>
					    	 </tr>
					    	 <tr>
								<td class="col-xs-1">
							    	<label >所属服务队</label>
							    </td>
							    <td class="col-xs-2">
							    	${teamName}
							    </td>
							    <td class="col-xs-1">
							    	<label >注册时间</label>
							    </td>
							    <td class="col-xs-2">
							    	<fmt:formatDate value="${volunteerView.regTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
							    </td>
					    	 </tr>
					    	 <tr>
								<td class="col-xs-1" >
							    	<label >服务时长</label>
							    </td>
							    <td class="col-xs-2">
							    	${empty volunteerView.serviceHour?0:volunteerView.serviceHour}小时
							    </td>
							    <td class="col-xs-1">
							    	<label >发证时间</label>
							    </td>
							    <td class="col-xs-2">
							    	<fmt:formatDate value="${volunteerView.certDate}" pattern="yyyy-MM-dd"/>
							    </td>
					    	 </tr>
						</table>
					</div>
					<div class="row">
					<table class="table table-bordered dataTable no-footer">
						<tr>
							<td style="text-align:center;background-color:rgba(248, 248, 248, 1)" colspan="5">
						       	年度注册登记信息
						     </td>
			    	 	</tr>
						<tr >
							<th style="text-align: center;">序号</th>
							<th style="text-align: center;">审核年份</th>
							<th style="text-align: center;">审核时间</th>
							<th style="text-align: center;">审核人</th>
							<th style="text-align: center;">审核状态</th>
						</tr>
						<tbody class="middle-align" id="pageData3">
		                        	
                        </tbody>
					</table>
					<div class="row" style="display: none;" id="pageInfo3"></div>
					<div style="margin-top: -20px; line-height: 50px;border: 1px solid #ddd; border-top: 0;display: none;" id="messageDiv3">
		                        <p class="text-center">暂无数据</p>
		            </div>
		            <script id="templateData3" type="text/html">
							{{#  layui.each(d.list, function(index, item){ }}	
			 							<tr role="row"
			                            data-toggle="collapse">
			                            <td style="text-align:center">{{(d.pageSize * (d.pageNum-1))+index+1}}</td>
			                            <td align="center">
			                                <div>
			                                   <span >{{item.checkYear}}</span>
			                                </div>
			                            </td>
			                             <td align="center">
			                                <div>
			                                   <span >{{item.opertTime==null?"":getFormatDateByLong(item.opertTime,"yyyy-MM-dd")}}</span>
			                                </div>
			                            </td>
			                             <td align="center">
			                                <div>
			                                   <span >{{item.opertUser }}</span>
			                                </div>
			                            </td>
										<td align="center">
			                                <div>
			                                   <span>{{item.certCheckState}}</span>
			                                </div>
			                            </td>
			                        </tr>
								{{#  }); }}                       
					</script>
				</div>
				<form id="certCheckForm" action="${webroot}cert/certChecklist.do" method="post" >
					<input type="hidden" name="certId" id="certId" value="${volunteerView.certId}">
					<input type="hidden" name="page" id="currentPageInput" value="1"/>
				</form>
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
		 var val= $("#input_child").val();
			if (val==2 || val==null ||val=="") {
				$("#child").hide();
			}
			
		$("img[name='pic']").attr('src', '${webroot}file/readPic.do?ataUrl='+encodeURIComponent("${volunteerView.imgUrl}"));
		$(document).ajaxComplete( function(event, jqXHR, options){
			var $star = $("input[name='star']");
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

		});
	})
	
	
	function closeFrame(){
		var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
		parent.layer.close(index);
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
	}
	
	function createCertCheckPage(page,searchFormId){
		var html = "";
		if(page && page.total){
			var total = page.total;
			if(total && total > 0){
				$("#activityNum").html(total);
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
	    		template=$("#templateData1");
	    		pageData=$("#pageData1");
	    		formName="joinedActivityForm";
	    		pageInfo=$("#pageInfo1");
	    		messageDiv=$("#messageDiv1");
	    		postFormData("joinedActivityForm",true,searchResult);
	    	}
	    	if(index==2){
	    		template=$("#templateData2");
	    		pageData=$("#pageData2");
	    		formName="trRecordForm";
	    		pageInfo=$("#pageInfo2");
	    		messageDiv=$("#messageDiv2");
	    		postFormData("trRecordForm",true,searchResult);
	    	}
	    	if(index==3){
	    		var id=$("#certId").val();
	    		if(id && !isNaN(id)){
		    		template=$("#templateData3");
		    		pageData=$("#pageData3");
		    		formName="certCheckForm";
		    		pageInfo=$("#pageInfo3");
		    		messageDiv=$("#messageDiv3");
		    		postFormData("certCheckForm",true,searchResult);
	    		}else{
	    			$("#certInfo").html("<p style='margin-left: 45%;'>暂无证书信息</p>");
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