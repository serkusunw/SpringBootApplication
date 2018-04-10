<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec"	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title><s:message code="admin.title"/></title>
	<%@include file="/WEB-INF/include/bootstrap.incl" %>
</head>
<body style="background-color: #E7E7E7">
	<%@include file="/WEB-INF/include/adminmenu.incl" %>
	
	<div class="container mt-5 p-4" style="width: 80%; background-color: #F2F2F2; box-shadow: 1px 4px 81px -4px rgba(0,0,0,0.63); border-radius: 5px;">
		<h1 class="text-center font-weight-light"><s:message code='admin.welcome.message'/></h1>
	</div>
</body>
</html>