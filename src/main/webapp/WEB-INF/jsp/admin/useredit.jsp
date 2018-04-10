<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sec"	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title><s:message code="admin.user.edit.title"/></title>
	<%@include file="/WEB-INF/include/bootstrap.incl" %>
</head>
<body>
	<%@include file="/WEB-INF/include/adminmenu.incl" %>

	<div class="container mt-5 p-4" style="width: 80%; background-color: #F2F2F2; box-shadow: 1px 4px 81px -4px rgba(0, 0, 0, 0.63); border-radius: 5px;">
	
		<h1 class="text-center font-weight-light mb-4">
			<s:message code='admin.user.edit.title' />
		</h1>
	
		<sf:form id="usersForm" action="/admin/update" modelAttribute="user" enctype="multipart/form-data" method="POST">
			<sf:hidden path="id"/>
			<sf:hidden path="password"/>
			<sf:hidden path="active"/>
			<sf:hidden path="registerDate"/>

			<div class="form-group">
				<label for="email"><s:message code="register.name" /></label>
				<sf:input path="name" type="text" id="name" class="form-control" />
			</div>

			<div class="form-group">
				<label for="surname"><s:message code="register.surname" /></label>
				<sf:input path="surname" type="text" id="surname" class="form-control" />
			</div>
			
			<div class="form-group">
				<label for="age"><s:message code="register.age" /></label>
				<sf:input path="age" type="text" id="age" class="form-control" />
			</div>
			
			<div class="form-group">
				<label for="email"><s:message code="register.email" /></label>
				<sf:input path="email" type="email" id="email" class="form-control" />
			</div>
			
			<div class="form-group">
				<label for="role"><s:message code="admin.users.column.role" /></label>
				<sf:select path="roleId" items="${roleMap}" id="role" class="form-control"/>
			</div>

			<input type="submit" value="<s:message code="button.save"/> " class="btn btn-success"/>
			<input type="button" value="<s:message code="button.cancel"/>" class="btn btn-secondary" onclick="window.location.href='${pageContext.request.contextPath}/admin/users'" />

		</sf:form>
		
	</div>
</body>
</html>