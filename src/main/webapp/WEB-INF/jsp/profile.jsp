<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sec"	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<%@include file="/WEB-INF/include/bootstrap.incl" %>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title><s:message code="user.profile.title"/></title>
</head>
<body style="background-color: #E7E7E7">
	<%@include file="/WEB-INF/include/menu.incl" %>
	
		<div class="container mt-5 p-4" style="width: 22%; background-color: #F2F2F2; box-shadow: 1px 4px 81px -4px rgba(0,0,0,0.63); border-radius: 5px;">
	
			<h2 class="text-center font-weight-light mb-5"><s:message code="user.profile.yourprofile"/></h2>

			<div class="row mb-3">
				<div class="col font-weight-bold"><s:message code='admin.users.column.name' /></div>
				<div class="col"><c:out value="${user.name }"/></div>
			</div>
			<div class="row mb-3">
				<div class="col font-weight-bold"><s:message code='admin.users.column.surname' /></div>
				<div class="col"><c:out value="${user.surname }"/></div>
			</div>
			<div class="row mb-3">
				<div class="col font-weight-bold"><s:message code='admin.users.column.age' /></div>
				<div class="col"><c:out value="${user.age }"/></div>
			</div>
			<div class="row mb-3">
				<div class="col font-weight-bold"><s:message code='admin.users.column.email' /></div>
				<div class="col"><c:out value="${user.email }"/></div>
			</div>
			<div class="row">
				<div class="col font-weight-bold"><s:message code='admin.users.column.date' /></div>
				<div class="col"><c:out value="${user.registerDate }"/></div>
			</div>

	</div>
</body>
</html>