<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<c:set var="webroot" value="${pageContext.request.contextPath}/" />

<nav class="navbar horizontal-menu navbar-fixed-top">
	<div class="navbar-inner">
		<div class="navbar-brand">
			<a href="${webroot}main.html" class="logo"> <span class="hidden-xs">湖北省文化志愿者管理系统 </span></a>
		</div>

		<ul class="nav nav-userinfo navbar-right">
			<li class="user-profile"><span>欢迎登录，您好：</span> <span><a
					class="inline" onclick="editUserInfo()" style="cursor: pointer" title="修改个人信息">
						<shiro:principal property="userName" />
				</a> </span></li>
			<li class="exit lt-btn hover-line"><a
				href="${webroot}logout.html" class="js_exit" title="关闭"> <i
					class="fa-power-off"></i>
			</a></li>
		</ul>
	</div>
</nav>


<script type="text/javascript">
	function editUserInfo() {
		var layer = layui.layer;
		layer.config({
			extend : 'myskin/style.css'
		});
		layer.open({
			type : 2,
			title : "查看用户信息",
			area : [ '600px', '400px' ],
			maxmin : true,
			skin: 'editInfo-class',
			scrollbar : false,
			content : [ "${webroot}userInfo/userInfo.html", "no" ],
			btn : [ '<i class="fa fa-remove"></i>关闭',
					'<i class="fa fa-pencil-square-o"></i>修改信息',
					'<i class="fa fa-pencil-square-o"></i>修改密码' ],
			closeBtn: 1,
			btn2 : function() {
				//按钮【按钮二】的回调
				//编辑用户
				layer.open({
					type : 2,
					title : "编辑用户",
					area : [ '600px', '360px' ],
					maxmin : true,
					scrollbar : false,
					content : [ "${webroot}userInfo/editUserInfo.html", "no" ],
					//关闭层后的回调函数
					end : function() {

					}
				});

			},
			btn3 : function() {
				//按钮【按钮三】的回调
				//修改密码
				layer.open({
					type : 2,
					title : "修改密码",
					area : [ '450px', '270px' ],
					maxmin : true,
					scrollbar : false,
					content : [ "${webroot}userInfo/editUserPwd.html", "no" ],
					//关闭层后的回调函数
					end : function() {

					}
				});

			}
		});
	}
	
	
	
	
	
</script>