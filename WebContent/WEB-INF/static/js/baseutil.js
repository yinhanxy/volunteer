
/**
 * 验证手机号码 <br/>
 * @param mobile
 * @returns {Boolean}
 */
function validateMobile(mobile){
	if(!mobile || mobile == ""){
		return false;
	}
	var validate = false;
	 var reg =/^((\+?86)|(\(\+86\)))?(13[0123456789][0-9]{8}|15[012356789][0-9]{8}|170[0125789][0-9]{7}|17[678][0-9]{8}|18[012356789][0-9]{8}|147[0-9]{8}|1349[0-9]{7})$/;
	 if (reg.test(mobile)) {
		 validate = true;
	 }else{
		 validate = false;
	 };
	 return validate;
}

/**
 * 验证电话号码 <br/>
 * @param telphone
 * @returns {Boolean}
 */
function validateTel(telphone){
	if(!telphone || telphone == ""){
		return false;
	}
	var validate = false;
	 var reg =/^(0[0-9]{2,3})?(-)?[0-9]{7,8}$/;
	 if (reg.test(telphone)) {
		 validate = true;
	 }else{
		 validate = false;
	 };
	 return validate;
}

/**
 * 验证邮箱地址
 * @param email
 * @returns {Boolean}
 */
function validateEmail(email){
	if(!email || email == ""){
		return false;
	}
	var pattern = /^([\.a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+(\.[a-zA-Z0-9_-])+/; 
	if (!pattern.test(email)) {  
        return false;  
    }else{
    	return true;
    }
}
/**
 * 验证15位或18位身份证号码
 * @param idCard
 * @returns {Boolean}
 */
function validateIdCard(idCard) {
	//15位和18位身份证号码的正则表达式
	var regIdCard = /^(^[1-9]\d{7}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])\d{3}$)|(^[1-9]\d{5}[1-9]\d{3}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])((\d{4})|\d{3}[Xx])$)$/;
	//如果通过该验证，说明身份证格式正确，但准确性还需计算
	if (regIdCard.test(idCard)) {
		if (idCard.length == 18) {
			var idCardWi = new Array(7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9,10, 5, 8, 4, 2); //将前17位加权因子保存在数组里
			var idCardY = new Array(1, 0, 10, 9, 8, 7, 6, 5, 4, 3, 2); //这是除以11后，可能产生的11位余数、验证码，也保存成数组
			var idCardWiSum = 0; //用来保存前17位各自乖以加权因子后的总和
			for (var i = 0; i < 17; i++) {
				idCardWiSum += idCard.substring(i, i + 1) * idCardWi[i];
			}
			var idCardMod = idCardWiSum % 11;//计算出校验码所在数组的位置
			var idCardLast = idCard.substring(17);//得到最后一位身份证号码
			//如果等于2，则说明校验码是10，身份证号码最后一位应该是X
			if (idCardMod == 2) {
				if (!(idCardLast == "X" || idCardLast == "x")) {
					return false;
				}
			} else {
				//用计算出的验证码与最后一位身份证号码匹配，如果一致，说明通过，否则是无效的身份证号码
				if (!(idCardLast == idCardY[idCardMod])) {
					return false;
				}
			}
		}
	} else {
		return false;
	}
	return true;
}


//扩展Date的format方法 
//对Date的扩展，将 Date 转化为指定格式的String 
//月(M)、日(d)、小时(h)、分(m)、秒(s)、季度(q) 可以用 1-2 个占位符， 
//年(y)可以用 1-4 个占位符，毫秒(S)只能用 1 个占位符(是 1-3 位的数字) 
//例子： 
//(new Date()).Format("yyyy-MM-dd HH:mm:ss.S") ==> 2006-07-02 08:09:04.423 
//(new Date()).Format("yyyy-M-d H:m:s.S")      ==> 2006-7-2 8:9:4.18 
Date.prototype.format = function (format) {
  var o = {
      "M+": this.getMonth() + 1,
      "d+": this.getDate(),
      "h+": this.getHours(),
      "m+": this.getMinutes(),
      "s+": this.getSeconds(),
      "q+": Math.floor((this.getMonth() + 3) / 3),
      "S": this.getMilliseconds()
  }
  if (/(y+)/.test(format)) {
      format = format.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
  }
  for (var k in o) {
      if (new RegExp("(" + k + ")").test(format)) {
          format = format.replace(RegExp.$1, RegExp.$1.length == 1 ? o[k] : ("00" + o[k]).substr(("" + o[k]).length));
      }
  }
  return format;
}

/**  
*转换日期对象为日期字符串  
* @param date 日期对象  
* @param isFull 是否为完整的日期数据,  
*               为true时, 格式如"2000-03-05 01:05:04"  
*               为false时, 格式如 "2000-03-05"  
* @return 符合要求的日期字符串  
*/  
function getSmpFormatDate(date, isFull) {
  var pattern = "";
  if (isFull == true || isFull == undefined) {
      pattern = "yyyy-MM-dd hh:mm:ss";
  } else {
      pattern = "yyyy-MM-dd";
  }
  return getFormatDate(date, pattern);
}

/**  
*转换当前日期对象为日期字符串  
* @param date 日期对象  
* @param isFull 是否为完整的日期数据,  
*               为true时, 格式如"2000-03-05 01:05:04"  
*               为false时, 格式如 "2000-03-05"  
* @return 符合要求的日期字符串  
*/  

function getSmpFormatNowDate(isFull) {
  return getSmpFormatDate(new Date(), isFull);
}

/**  
*转换long值为日期字符串  
* @param l long值  
* @param isFull 是否为完整的日期数据,  
*               为true时, 格式如"2000-03-05 01:05:04"  
*               为false时, 格式如 "2000-03-05"  
* @return 符合要求的日期字符串  
*/  

function getSmpFormatDateByLong(l, isFull) {
  return getSmpFormatDate(new Date(l), isFull);
}

/**  
*转换long值为日期字符串  
* @param l long值  
* @param pattern 格式字符串,例如：yyyy-MM-dd hh:mm:ss  
* @return 符合要求的日期字符串  
*/  

function getFormatDateByLong(l, pattern) {
  return getFormatDate(new Date(l), pattern);
}

/**  
*转换日期对象为日期字符串  
* @param l long值  
* @param pattern 格式字符串,例如：yyyy-MM-dd hh:mm:ss  
* @return 符合要求的日期字符串  
*/  
function getFormatDate(date, pattern) {
  if (date == undefined) {
      date = new Date();
  }
  if (pattern == undefined) {
      pattern = "yyyy-MM-dd hh:mm:ss";
  }
  return date.format(pattern);
}

/**
 * 将字符串转换为日期 <br/>
 * 字符串格式给：yyyy-MM-dd
 * @param dateStr 字符串
 * @returns
 */
function pasre(dateStr){
	try{
		var dtArr = dateStr.split("-");
		var dt = new Date(dtArr[0], dtArr[1], dtArr[2]);
		return dt;
	}catch(e){
		return null;
	}
}

/**
 * 将字符串转换为年月日
 * 字符串格式给：yyyy-MM-dd
 * @param dateStr yyyy年MM月dd日
 * @returns
 */
function getDateStr(dateStr){
	try{
		var dtArr = dateStr.split("-");
		var dt = dtArr[0]+"年"+dtArr[1]+"月"+ dtArr[2]+"日";
		return dt;
	}catch(e){
		return null;
	}
}

function getCurDate(){
	var d = new Date();
	var week;
	switch (d.getDay()){
		case 1: 
			week="星期一"; 
			break;
		case 2: 
			week="星期二"; 
			break;
		case 3: 
			week="星期三"; 
			break;
		case 4: 
			week="星期四"; 
			break;
		case 5: 
			week="星期五"; 
			break;
		case 6: 
			week="星期六"; 
			break;
		default: 
			week="星期天";
	}
	var years = d.getFullYear();
	var month = add_zero(d.getMonth()+1);
	var days = add_zero(d.getDate());
	var hours = add_zero(d.getHours());
	var minutes = add_zero(d.getMinutes());
	var seconds=add_zero(d.getSeconds());
	var dateStr = years+"年"+month+"月"+days+"日&nbsp;&nbsp"+week;
	return dateStr;
}

function add_zero(temp){
	if(temp < 10){
		return "0"+temp;
	}else{
		return temp;
	}
}


/***
 * ajax请求封装
 * @param url 请求地址
 * @param params 请求参数
 * @param async 是否异步,默认值为：true. true：异步，false：同步
 * @param callback 执行成功后的回调方法
 */
function getData(url,params,async,callback,errorMethod){
	if(async == null){
		async = true;
	}
	$.ajax({
		url : url,
		data : params,
		type : "GET",
		dataType : 'json',
		cache : false,
		traditional: true,
		async : async,
		success : function(result) {
			if (typeof callback == 'function') {
				callback(result);
			}
		},
		complete : function(xhr, ts) {
			
		},
		error:function(XMLHttpRequest, textStatus, errorThrown){
			if(window.console){
				console.log("status:"+XMLHttpRequest.status);
				console.log("readyState:"+XMLHttpRequest.readyState);
				console.log("textStatus:"+textStatus);
			}
			if(errorMethod && typeof errorMethod == "function"){
				errorMethod(XMLHttpRequest, textStatus, errorThrown);
			}
		}
	})
}

/***
 * ajax请求封装
 * @param url 请求地址
 * @param params 请求参数
 * @param async 是否异步,默认值为：true.<br/>  true：异步，false：同步
 * @param callback 执行成功后的回调方法
 */
function postData(url, params, async, callback,errorMethod) {
	if(async == null){
		async = true;
	}
	$.ajax({
		url : url,
		data : params,
		type : "POST",
		dataType : 'json',
		traditional: true,
		cache : false,
		async : async,
		success : function(result) {
			if (typeof callback == 'function') {
				callback(result);
			}
		},
		complete : function(xhr, ts) {
			
		},
		error:function(XMLHttpRequest, textStatus, errorThrown){
			if(window.console){
				console.log("status:"+XMLHttpRequest.status);
				console.log("readyState:"+XMLHttpRequest.readyState);
				console.log("textStatus:"+textStatus);
			}
			if(errorMethod && typeof errorMethod == "function"){
				errorMethod(XMLHttpRequest, textStatus, errorThrown);
			}
		}
	})
}


/***
 * ajax请求封装
 * @param formId 表单的ID
 * @param async 是否异步,默认值为：true.<br/>  true：异步，false：同步
 * @param callback 执行成功后的回调方法
 * @param errorMethod 出错时的回调方法
 */
function postFormData(formId,async, callback,errorMethod) {
	var j_searchForm = $("#"+formId);
	var url = j_searchForm.attr("action");
    var params = j_searchForm.serialize();
	if(params == null || params == ""){
		params = {"t":new Date().getTime()};
	}
	if(async == null){
		async = true;
	}
	$.ajax({
		url : url,
		data : params,
		type : "POST",
		dataType : 'json',
		traditional: true,
		cache : false,
		async : async,
		success : function(result) {
			if (typeof callback == 'function') {
				var isPrompt = errorMessage(result);
				if(!isPrompt){
					callback(result);
				}
			}
		},
		complete : function(xhr, ts) {
			
		},
		error:function(XMLHttpRequest, textStatus, errorThrown){
			if(window.console){
				console.log("status:"+XMLHttpRequest.status);
				console.log("readyState:"+XMLHttpRequest.readyState);
				console.log("textStatus:"+textStatus);
			}
			if(errorMethod && typeof errorMethod == "function"){
				errorMethod(XMLHttpRequest, textStatus, errorThrown);
			}
		}
	})
}


/**
 * 分页插件
 */
function createPageInfo(page,searchFormId){
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
			
			$("#currentPageInput").val(currentPage);
			$("#pageSizeInput").val(pageSize);
			
			html +="<div class=\"col-sm-4\"><label>每次加载<select class=\"form-control input-sm\" onchange=\"changePage(this,'"+searchFormId+"')\">";
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
			html +="<li><input type=\"text\" id=\"goPageText\" style=\"width: 34px;height: 34px;margin-right: 10px;padding: 0px;text-align: center;\" class=\"form-control\" /></li>";
			html +="<li><span style=\"float: right;\"  onclick=\"goPage("+pageCount+",'"+searchFormId+"')\"><a href=\"javascript:void(0)\">GO</a></span></li>";
			html +="</ul>";
			html +="</nav>";
		}
	}
	return html;
}

/**
 * 全局错误提示，提示未登录和未存在权限的AJAX请求
 * @param result
 */
function errorMessage(result){
	var isPrompt = false;
	if(!result.success){
		var status = result.status;
		//如果用户未登录或者没有权限发出AJAX请求
		if(status == 401 || status == 403){
			if(window.layui){
				var layer = window.layui.layer;
				if(layer){
					$(".panel-disabled").hide();
					var message = result.message;
					layer.open({
						  type: 1,
						  title:'提示信息',
						  skin: 'layui-layer-rim', //加上边框
						  area: ['420px', '240px'], //宽高
						  content: message
						});
					isPrompt = true;
				}
			}
		}
	}
	return isPrompt;
}
/***
 * 表单后端验证提示信息
 * @param errorMessage 
 * @returns
 */
function errorTips(result){ 
	var errors=result.message;
	if(errors instanceof Array){
		if(errors && errors.length>0){
				var fileName=errors[0].propertyName;
				var el=$("input[name='"+fileName+"'][type='text']");
				var errorMess=errors[0].message;
				if(el&&el.length>0){
					layui.layer.tips(errorMess, el,{
			    		  tips: [1, '#f57a78'] //还可配置颜色
			    	 });
				}else{
					layer.alert(errorMess, {icon: 5});
				}
		}
	}else{
		layer.alert(errors, {icon: 5});
	}
}

