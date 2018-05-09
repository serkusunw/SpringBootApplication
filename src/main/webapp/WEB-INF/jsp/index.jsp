<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sec"	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <%@include file="/WEB-INF/include/bootstrap.incl" %>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title><s:message code="page.title.index"/></title>

	<style> 
	body {
		background-image: url("/images/wallpaper.jpg");
		background-repeat: no-repeat;
		background-color: #25271E;
		background-size: cover;
		height: 100vh;
	}
	</style>
</head>
<body>
	<%@include file="/WEB-INF/include/menu.incl" %>

	<div class="container">
		<div class="jumbotron mt-5 px-5">
			<h1 class="display-4"><s:message code="index.jumbotron.title"/></h1>
			<p class="lead"><s:message code="index.jumbotron.short_description"/></p>
			<hr class="my-4">
			<p><s:message code="index.jumbotron.description"/></p>
			</br>
			<div class="card-deck px-5">
				<c:forEach var="book" items="${books}">
					<div class="card">
						<img class="card-img-top" src="/images/<c:url value="${book.image_name}"/>">
						<div class="card-body">
							<h5 class="card-title"><c:out value="${book.title}"/></h5>
							<p class="card-text"><c:out value="${book.release_date}"/></p>
							<p class="card-text"><c:out value="${book.publishingHouse.name}"/></p>
						</div>
					</div>
				</c:forEach>
			</div>
		</div>
	</div>
</body>
</html>