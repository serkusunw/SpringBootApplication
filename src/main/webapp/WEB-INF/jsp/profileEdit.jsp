<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><s:message code="user.profile.title.edit" /></title>
<%@include file="/WEB-INF/include/bootstrap.incl"%>
</head>
<body style="background-color: #E7E7E7">
	<%@include file="/WEB-INF/include/menu.incl"%>

	<div class="container my-5 p-4"
		style="width: 30%; background-color: #F2F2F2; box-shadow: 1px 4px 81px -4px rgba(0, 0, 0, 0.63); border-radius: 5px;">
		<h1 class="text-center font-weight-light mb-4">
			<s:message code='user.profile.title.edit' />
		</h1>

		<sf:form id="usersForm" action="/user/update" modelAttribute="user"
			enctype="multipart/form-data" method="POST">
			<sf:hidden path="id" />
			<sf:hidden path="active" />
			<sf:hidden path="registerDate" />
			<sf:hidden path="email" />

			<div class="form-group">
				<label for="email"><s:message code="register.name" /></label>
				<sf:input path="name" type="text" id="name" class="form-control" />
			</div>

			<div class="form-group">
				<label for="surname"><s:message code="register.surname" /></label>
				<sf:input path="surname" type="text" id="surname"
					class="form-control" />
			</div>

			<div class="form-group">
				<label for="age"><s:message code="register.age" /></label>
				<sf:input path="age" type="date" id="age" class="form-control" />
			</div>

			<div class="form-group">
				<label for="city"><s:message code="register.city" /></label>
				<sf:input path="address.city" type="text" id="city"
					class="form-control" />
			</div>

			<div class="form-group">
				<label for="postalcode"><s:message
						code="register.postalcode" /></label>
				<sf:input path="address.postal_code" type="text" id="postalcode"
					class="form-control" />
			</div>

			<div class="form-group">
				<label for="street"><s:message code="register.street" /></label>
				<sf:input path="address.street" type="text" id="street"
					class="form-control" />
			</div>

			<div class="form-group">
				<label for="number"><s:message code="register.streetnumber" /></label>
				<sf:input path="address.number" type="text" id="number"
					class="form-control" />
			</div>
			
			<div class="form-group">
				<label for="password"><s:message code="register.password" /></label>
				<sf:input path="password" type="password" id="password"
					class="form-control" />
				<sf:errors path="password" class="text-danger" />
			</div>

			<div class="form-group">
				<label for="passwordCheck"><s:message
						code="register.passwordCheck" /></label>
				<sf:input path="passwordCheck" type="password" id="passwordCheck"
					class="form-control" />
				<sf:errors path="passwordCheck" class="text-danger" />
			</div>

			<input type="submit" value="<s:message code="button.save"/> "
				class="btn btn-success" />
			<input type="button" value="<s:message code="button.cancel"/>"
				class="btn btn-secondary"
				onclick="window.location.href='${pageContext.request.contextPath}/profile'" />

		</sf:form>


	</div>
</body>
</html>