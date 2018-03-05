/**
 * 
 */
var intervalFunObj = null; 
	
function initMenu(){
	
	var href = window.location.href;
	var rootIndex = href.indexOf(Config.root);
	var hashIndex = href.indexOf("#");
	var placeholderIndex = href.indexOf("?");
	var end = href.length;
	if(placeholderIndex > -1){
		end = placeholderIndex;
	}
	if(hashIndex > -1 && hashIndex < placeholderIndex){
		end = hashIndex;
	}
	
	try{
		if(!document.mCustomScrollbar){
			 $('head').append('<link  href="'+Config.root+'/static/css/jquery.mCustomScrollbar.min.css" rel="stylesheet" type="text/css" />');
			 $('head').append('<script  src="'+Config.root+'/static/js/jquery.mousewheel-3.0.6.min.js"  type="text/javascript" />');
			 $('head').append('<script  src="'+Config.root+'/static/js/jquery.mCustomScrollbar.min.js"  type="text/javascript" />');			
		}
	}catch(e){
		 $('head').append('<link  href="'+Config.root+'/static/css/jquery.mCustomScrollbar.min.css" rel="stylesheet" type="text/css" />');
		 $('head').append('<script  src="'+Config.root+'/static/js/jquery.mousewheel-3.0.6.min.js"  type="text/javascript" />');
		 $('head').append('<script  src="'+Config.root+'/static/js/jquery.mCustomScrollbar.min.js"  type="text/javascript" />');
	}
	$(".sidebar-menu").mCustomScrollbar({
		setHeight:false,
		theme:"minimal-dark"
	});
	
	var time = 10 * 1000;
	intervalFunObj = window.setInterval(doCenter,time);
	
	var paramUrl = href.substring(rootIndex+Config.root.length,end);
	getData(Config.root+"/menus.do", null, null, function(result){
		var menus = result;
		var html = "";
		if(menus && menus.length > 0){
				for(var i = 0 ; i < menus.length;i++ ){
					var menu = menus[i];
					var desc = menu.menuDesc;
					var url = menu.url;
					var icon = menu.icon;
					var childMenus = menu.menuViews;
					html += "<li><a ";
					if(url != null && url != ""){
						html += "  href=\""+Config.root+url+"\" ";
					}
					html +=	" title=\""+desc+"\"><i class=\""+icon+"\"></i><span>"+desc+"</span>";
					var hasHref = false;
					if(!childMenus || childMenus.length == 0){
						html += "</a>";
					}
					if(childMenus && childMenus.length > 0){
						if(paramUrl != "/"){
							for(var j = 0 ; j < childMenus.length;j++){
								var childMenu = childMenus[j];
								var childUrl = childMenu.url;
								if(childUrl.indexOf(paramUrl) > -1){
									hasHref = true;
									break;
								}
							}
						}
						
						if(hasHref){
							html += "<i class=\"pull-right fa fa-angle-up expand\"></i>";
						}else{
							html += "<i class=\"pull-right fa fa-angle-down\"></i>";
						}
						html += "</a>";
						html += "<ul>";
						for(var j = 0 ; j < childMenus.length;j++){
							var childMenu = childMenus[j];
							var childName = childMenu.menuName;
							var childDesc = childMenu.menuDesc;
							var childUrl = childMenu.url;
							var childIcon = childMenu.icon;
							if(hasHref){
								html += "<li class=\"is-shown\">";
							}else{
								html += "<li>";
							}
							html += "<a href=\""+Config.root+childUrl+"\" title=\""+childDesc+"\" id=\""+childName+"\">" +
										"<i></i>" +
										"<span class=\"title\">"+childDesc+"</span>" +
										"</a>" +
									"</li>";
						}
						html += "</ul>";
					}
					html += "</li>";
				}
				$("#main-menu").html(html);
				menuOnClick();
		}
	});
}


function doCenter(){
	getData(Config.root+"/center.do", null, null
		,function(result){
			if(!result.success){
				if(intervalFunObj){
					window.clearInterval(intervalFunObj);
					intervalFunObj = null;
				}
				var msg = "您已退出登录或已被系统管理员强制下线，请重新登录！";
				if(layui){
					var layer = layui.layer;
					if(layer){
						layer.alert(msg, {icon:2});
					}else{
						alert(msg);
					}
				}else{
					alert(msg);
				}
			}
		},function(XMLHttpRequest, textStatus, errorThrown){
			if(intervalFunObj){
				window.clearInterval(intervalFunObj);
				intervalFunObj = null;
			}
			var message = "系统已关闭！";
			if(layui){
				var layer = layui.layer;
				if(layer){
					layer.alert(message, {icon:2});
				}else{
					alert(message);
				}
			}else{
				alert(message);
			}
		}
	);
}


/**
 * 为导航增加点击事件
 */
function menuOnClick(){
	$("#main-menu a").click(function (e){
		e.stopPropagation();
		setTimeout(function(){
			if(!$(e.currentTarget).siblings().children().hasClass("is-shown")){
				$(e.currentTarget).siblings().children().addClass("is-shown");
				$(e.currentTarget).children(".pull-right").removeClass("fa-angle-down").addClass("fa-angle-up expand");
			}else{
				$(e.currentTarget).children(".pull-right").removeClass("fa-angle-up expand").addClass("fa-angle-down");
				$(e.currentTarget).siblings().children().removeClass("is-shown");
			}
		}, 1);
	});
}



