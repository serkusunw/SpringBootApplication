<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sec"	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<%@include file="/WEB-INF/include/bootstrap.incl" %>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title><s:message code="page.title.register"/></title>
</head>
<body style="background-color: #E7E7E7">
	<%@include file="/WEB-INF/include/menu.incl" %>
	
	<div class="container mt-5 p-4" style="width: 22%; background-color: #F2F2F2; box-shadow: 1px 4px 81px -4px rgba(0,0,0,0.63); border-radius: 5px;">
	
		<h2 class="text-center font-weight-light mb-4"><s:message code="register.title"/></h2>
		
		<sf:form id="usersForm" action="adduser" modelAttribute="user" enctype="multipart/form-data" method="POST">
	
				  <div class="form-group">
				    <label for="email"><s:message code="register.email"/></label>
				    <sf:input path="email" type="email" id="email" class="form-control"/>
				    <sf:errors path="email" class="text-danger"/>
				  </div>
				  
  				  <div class="form-group">
				    <label for="password"><s:message code="register.password"/></label>
				    <sf:input path="password" type="password" id="password" class="form-control"/>
				    <sf:errors path="password" class="text-danger"/>
				  </div>
				  
   				  <div class="form-group">
				    <label for="passwordCheck"><s:message code="register.passwordCheck"/></label>
				    <sf:input path="passwordCheck" type="password" id="passwordCheck" class="form-control"/>
				    <sf:errors path="passwordCheck" class="text-danger"/>
				  </div>
				  
  				  <div class="form-group">
				    <label for="name"><s:message code="register.name"/></label>
				    <sf:input path="name" type="text" id="name" class="form-control"/>
				    <sf:errors path="name" class="text-danger"/>
				  </div>
				  
   				  <div class="form-group">
				    <label for="surname"><s:message code="register.surname"/></label>
				    <sf:input path="surname" type="text" id="surname" class="form-control"/>
				    <sf:errors path="surname" class="text-danger"/>
				  </div>
				  
   				  <div class="form-group">
				    <label for="age"><s:message code="register.age"/></label>
				    <sf:input path="age" type="number" id="age" class="form-control"/>
				    <sf:errors path="age" class="text-danger"/>
				  </div>
				  
 				  <input type="submit" value="<s:message code="register.button.register"/>" class="btn btn-block btn-primary"/>
				  
		</sf:form>
	</div>

</body>
</html>