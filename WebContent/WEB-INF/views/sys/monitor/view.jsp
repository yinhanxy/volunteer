<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>  
<%@ taglib prefix="tps" uri="/pageTag"%>
<c:set var="webroot" value="${pageContext.request.contextPath}/" />
<%-- 静态资源绝对地址 --%>
<c:set var="sr" value="${pageContext.request.contextPath}/static/"/>
<!DOCTYPE html>
<html lang="zh-CN">
  <head>
    <!-- <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,Chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    
    <title>湖北省文化志愿者管理系统</title> -->
    <jsp:include page="../../include/title.jsp"></jsp:include>
	
	<link href="${sr}css/font-awesome/css/linecons.css" rel="stylesheet">
	<link href="${sr}css/font-awesome/css/font-awesome.min.css" rel="stylesheet">
    <link href="${sr}css/bootstrap.min.css" rel="stylesheet">
	<link href="${sr}css/xenon.min.css" rel="stylesheet">
	<link href="${sr}css/base.css" rel="stylesheet">
	<link href="${sr}css/new.css" rel="stylesheet">
    <!--[if lt IE 9]>
      <script src="${sr}js/html5shiv.min.js"></script>
      <script src="${sr}js/respond.min.js"></script>
    <![endif]-->
    <style type="text/css">
    	.panel-now {
    		margin-top:15px;
   	 		margin-bottom: 20px;
    		background-color: #fff;
			}
		.content-top{
			font-size: 17px;
   			padding-bottom: 15px;
			padding: 10px 15px;
    		color: #ffffff;
		}
		.systopcolor{
			background-color: #23c6c8;
		}
		#sysInfo{
			border-radius: 4px;
			border:1px solid #23c6c8;
		}
		
		.cptopcolor{
			background-color: #30BEA2;
		}
		#cpxx{
			border-radius: 4px;
			border:1px solid #30BEA2;
			
		}
		
		#jkxx{
			border-radius: 4px;
			border:1px solid #ed5565;
		}
		.jkxxcolor{
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
	    <div class="main-content" style="height: 100%;" style="margin: 0px;padding: 0px;background-color: #fff;">
		    <div  class="workspace-page">
		        <div class="page-title list-page" >
		            <div class="title-env">
		                <ol class="breadcrumb bc-1" style="margin: 0px;">
		                    <li>
		                        <a href="${webroot}main.html" target="_top"><i class="fa-home"></i>首页</a>
		                    </li>
		                    <li>
		                        <a>系统管理</a>
		                    </li>
		                    <li class="active">
		                        <a  href="${webroot}monitor/view.html"><strong>系统监控</strong></a>
		                    </li>
		                </ol>
		            </div>
		        </div>
		        
		        <div class="table-panel panel">
		        
		         <div class="col-sm-12">
		         	<div class="panel-now" id="cpxx">
		         	<div class="content-top cptopcolor">
                           <i class="fa fa-bell"></i> 实时信息
                    </div>
                    <div class="panel-body">
		         	<div class="col-sm-4">
                                <div id="disk" style="width:100%;height:300px;"></div>
                	</div>
                
                	<div class="col-sm-4">
                                 <div id="cpu" style="width:100%;height:300px;"></div>   
                	</div>
                
                	<div class="col-sm-4">
                				<div id="memory" style="width:100%;height:300px;"></div>
                	</div>
                </div>
                </div>
                </div>
		        
		        <div class="row">
		        <div class="col-sm-6">
                                <div class="panel-now" id="sysInfo">
                                    <div class="content-top systopcolor">
                                        <i class="fa-list-ul"></i> 服务器信息
                                    </div>
                                    <div class="panel-body">
                                        <table aria-describedby="example-2_info" role="grid" id="roleList"
		                           class="table table-bordered table-hover table-striped dataTable no-footer">
		                        	<c:forEach var="list"   items="${sysInfoList}">
		                        	<tr role="row">
		                        	<td width="40">${list.name}</td>
		                            <td width="60">${list.value}</td>
		                       	 	</tr>
		                        	</c:forEach>
		                    	</table>
                                    </div>

                                </div>
                </div>
		         <div class="col-sm-6">
                                <div class="panel-now" id="jkxx">
                                    <div class="content-top jkxxcolor">
                                        <i class="fa fa-fire"></i> 监控信息
                                    </div>
                                    <div class="panel-body">
                                    	 <div id="monitor" style="width:100%;height:400px;"></div>
                                    </div>

                                </div>
                </div>
		        </div>                    
		        
		        </div>
		        
		    </div>
	</div> 
</div>

<jsp:include page="../../include/footer.jsp"></jsp:include>		

<script src="${sr}js/jquery.min.js" type="text/javascript"></script>
<script src="${sr}js/config.js" type="text/javascript"></script>
<script src="${sr}js/main.js" type="text/javascript"></script>
<script src="${sr}layui.js" type="text/javascript"></script>
<script src="${sr}js/baseutil.js" type="text/javascript"></script>
<script src="${sr}js/echarts-all.js" type="text/javascript"></script>
<script type="text/javascript">
		$(function(){
			load();
		});
		function load(){
		layui.use(['jquery','layer','laytpl'], function(){
			initMenu();
			var laytpl = layui.laytpl;
			var layer = layui.layer;
			});
	
		// 基于准备好的dom，初始化echarts实例
		var cpuChart = echarts.init(document.getElementById('cpu'),'macarons');
		var diskChart = echarts.init(document.getElementById('disk'),'macarons');
		var memoryChart = echarts.init(document.getElementById('memory'),'macarons');
		//折线图
		var monitorChart = echarts.init(document.getElementById('monitor'),'macarons');
		
		cpuChart.showLoading({
            text: "图表数据正在努力加载..."
        });
		diskChart.showLoading({
            text: "图表数据正在努力加载..."
        });
		memoryChart.showLoading({
            text: "图表数据正在努力加载..."
        });
		// 指定图表的配置项和数据
		//cpu
		var cpuOptions = {
			tooltip: {
				formatter:"{b} : {c}%"
			},
			series: [{
				name: 'CPU使用率',
	    		type: 'gauge',
	            detail: {formatter:'{value}%'},
	            data: []
			}]
		};
		
		//disk
		var diskOptions = {
			tooltip: {
				formatter:"{b} : {c}%"
			},
			series: [{
				name: '磁盘使用率',
	    		type: 'gauge',
	            detail: {formatter:'{value}%'},
	            data: []
			}]
		};
		
		//memory
		var memoryOptions = {
			tooltip: {
				formatter:"{b} : {c}%"
			},
			series: [{
				name: '内存使用率',
	    		type: 'gauge',
	            detail: {formatter:'{value}%'},
	            data: []
			}]
		};
		
		//monitor
		var monitorOptions = {
			tooltip: {
		        trigger: 'axis',
		        formatter:"{b}<br />{a0}: {c0}%<br />{a1}: {c1}%<br />{a2}: {c2}%"
		    },	
		    legend: {
		        data:['磁盘使用率','CPU使用率','内存使用率']
		    },
		    grid: {
		        containLabel: true
		    },
		    xAxis: [{
		    	type: 'category',
		        boundaryGap: false,
		    	data: [],
		    	name:'时间',
		    	axisLabel:{
// 		              rotate:45, //刻度旋转45度角
		              textStyle:{
		                 fontSize:5
		            }
		    
		    	}}],
		    yAxis: {
		    	name:'百分比',
		    	
		    },
		    series: [
		        {
		            name:'磁盘使用率',
		            type:'line',
		            data:[]
		        },
		        {
		            name:'CPU使用率',
		            type:'line',
		            data:[]
		        },
		        {
		            name:'内存使用率',
		            type:'line',
		            data:[]
		        }
		        ]
		};
		
		
		//通过Ajax获取数据
		var url="${webroot}monitor/Perc.do";
		setInterval(function(){
		postData(url, null, true,result);
		},1000);
		//获取cpu,磁盘,内存使用率的回调函数
		var name;
		var value;
		function result(data){
			var message=data.message;
			if(data && data.success){
			name=data.cpuPerc.name;
			value=parseInt(data.cpuPerc.value);
			cpuOptions.series[0].data = [{value: value, name:name}];
       	 	cpuChart.hideLoading();
            cpuChart.setOption(cpuOptions);
			
            
            name=data.filePerc.name;
			value=parseInt(data.filePerc.value);
			diskOptions.series[0].data = [{value: value, name:name}];
			diskChart.hideLoading();
			diskChart.setOption(diskOptions);
			
			
			name=data.memoryPerc.name;
			value=parseInt(data.memoryPerc.value);
			memoryOptions.series[0].data = [{value: value, name:name}];
			memoryChart.hideLoading();
			memoryChart.setOption(memoryOptions);
			
			var res=data.monitorList.list;
			var xAxis=[];
			var Series=[];
			var cpuSerie=[];
			var diskSerie=[];
			var memorySerie=[];
			for(i=res.length-1;i>=0;i--){
				xAxis.push(getFormatDateByLong(res[i].crTime,"hh:mm:ss"));
				cpuSerie.push(res[i].cpurate);
				diskSerie.push(res[i].diskrate);
				memorySerie.push(res[i].memoryrate);
			}
			monitorOptions.xAxis[0].data=xAxis;
			monitorOptions.series[0].data=diskSerie;
			monitorOptions.series[1].data=cpuSerie;
			monitorOptions.series[2].data=memorySerie;
			monitorChart.setOption(monitorOptions);
			}else{
				layer.msg(message, {icon: 2,time:1000});
			}
		}
		
		}
	
	
</script>
</body>
</html>