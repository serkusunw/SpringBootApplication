<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec"	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title><s:message code="page.title.login"/></title>
	<%@include file="/WEB-INF/include/bootstrap.incl" %>
</head>
<body>
	<%@include file="/WEB-INF/include/menu.incl" %>
	
		<div class="container mt-5 p-4" style="width: 22%; background-color: #F2F2F2; box-shadow: 1px 4px 81px -4px rgba(0,0,0,0.63); border-radius: 5px;">
	
		<h2 class="text-center font-weight-light mb-4"><s:message code="login.title"/></h2>
		
		<form id="loginForm" action="/login" method="POST">
	
				  <div class="form-group">
				    <label for="email"><s:message code="login.email"/></label>
				    <input type="text" name="email" id="email" class="form-control"/>
				  </div>
				  
  				  <div class="form-group">
				    <label for="password"><s:message code="login.password"/></label>
				    <input type="password" name="password" id="password" class="form-control"/>
				  </div>
				  
 				  <input type="submit" value="<s:message code="login.login"/>" class="btn btn-block btn-primary"/>
				  
		</form>
	</div>
	
</body>
</html>