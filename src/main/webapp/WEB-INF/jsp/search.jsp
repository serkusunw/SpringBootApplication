<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec"	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <%@include file="/WEB-INF/include/bootstrap.incl" %>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title><s:message code="librarian.manageLoans"/></title>

	<style>
	body {
		background-color: #E7E7E7;
		height: 100vh;
	}
	</style>
</head>
<body>
	<%@include file="/WEB-INF/include/menu.incl" %>

	<div class="container p-5">

		<c:choose>
			<c:when test="${success == 0}">
				<div class="container alert alert-danger mt-2 text-center" role="alert"><b><s:message code="search.failure"/></b></div>
			</c:when>
		</c:choose>


		<c:choose>
			<c:when test="${operation eq 'showFoundBooks'}">

				<c:forEach var="book" items="${books}">
					<div class="row mb-4">
						<div class="col-12">
							<div class="card">
								<div class="card-header"><s:message code="search.book.name" /></div>
								<div class="card-body">
									<div class="row">
										<div class="col-4">
											<img src="/images/<c:url value="${book.image_name }"/>" class="shadow-lg img-fluid rounded text-left mt-4" alt="<c:out value="${book.title}"/>"/>
										</div>
										
										<div class="col-8 mt-4">
												
												<h5><s:message code="book.title"/>:</h5>
												<h1 class="font-weight-light">&ldquor;<c:out value="${book.title }"/>&rdquo;</h1>
												</br>
												
												<h5><s:message code="book.author"/>:</h5>
												<h2 class="font-weight-light"><c:out value="${book.author.surname }"/> <c:out value="${book.author.name }"/></h2>
												</br>
		
												<h5><s:message code="book.publishingHouse"/>:</h5>
												<h3 class="font-weight-light"><c:out value="${book.publishingHouse.name}"/></h3>
												</br>
		
												<h5><s:message code="book.releaseDate"/>:</h5>
												<h4 class="font-weight-light"><c:out value="${book.release_date}"/></h4>
												</br>
												
												<h5><s:message code="book.quantity.available"/></h5>
												<h3 class="font-weight-bold" style="color: #4bcc35;"><c:out value="${book.count}"/></h3>
												</br>
												
											    <a class="btn btn-primary" data-toggle="collapse" href="#<c:out value="${book.id}"/>" role="button" aria-expanded="false" aria-controls="<c:out value="${book.id}"/>"><s:message code="book.description"/></a>
												<sec:authorize access="hasRole('ROLE_USER')">
													<input type="button"
													class="btn btn-success"
													onclick="window.location.href='${pageContext.request.contextPath}/showBooks/${book.id}/reservation'"
													value="<s:message code="button.reservation"/>" />
												</sec:authorize>
										</div>
									</div>
									<div class="row my-3">
										<div class="col-12">
											<div class="collapse" id="<c:out value="${book.id }"/>">
												<div class="jumbotron jumbotron">
													<p class="lead text-justify"><c:out value="${book.description }"/></p>
												</div>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</c:forEach>

			</c:when>
		</c:choose>
		
	</div>
	
</body>
</html>