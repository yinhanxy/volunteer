<%@page import="com.topstar.volunteer.util.ConfigUtils"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="webroot" value="${pageContext.request.contextPath}" />
	<meta charset="utf-8">
   	<meta http-equiv="X-UA-Compatible" content="IE=edge,Chrome=1">
   	<meta name="viewport" content="width=device-width, initial-scale=1">
   
   	<link rel="shortcut icon" href="${webroot}/static/images/icon.png" type="image/x-icon" />
   
   	<title><%= ConfigUtils.getConfigContentByName("SOFTWARE_NAME")%></title>