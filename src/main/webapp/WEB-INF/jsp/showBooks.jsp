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
		background-color: #E7E7E7;
		height: 100vh;
	}
	</style>
</head>
<body>
	<%@include file="/WEB-INF/include/menu.incl" %>

	
	<div class="container-fluid px-5 pt-1 ">
		<div class="row m-5">
			<div class="col-2">
				<div class="list-group mb-4" style="background-color: white; box-shadow: 1px 4px 25px -4px rgba(0, 0, 0, 0.63); border-radius: 5px;">

					<c:forEach var="category" items="${categories}">
						<a href="/showBooks/<c:url value="${category.id}"/>/0" class="list-group-item list-group-item-action"><c:out value="${category.name }"/></a> 
					</c:forEach>
				</div>
			</div>
			<div class="col-9 offset-1">
				<div class="container-fluid pb-5 pt-2 px-5" style="background-color: white; box-shadow: 1px 4px 25px -4px rgba(0, 0, 0, 0.63); border-radius: 5px;">
					<c:choose>
						<c:when test="${reservationStatus == 1 }">
							<div class="container alert alert-success mt-2 text-center" role="alert"><b><s:message code="success.librarian.book.reservation"/></b></div>
						</c:when>
						<c:when test="${reservationStatus == 0}">
							<div class="container alert alert-danger mt-2 text-center" role="alert"><b><s:message code="fault.librarian.book.reservation"/></b></div>
						</c:when>
						<c:when test="${reservationStatus == -1}">
							<div class="container alert alert-danger mt-2 text-center" role="alert"><b><s:message code="fault.librarian.book.quantity"/></b></div>
						</c:when>
					</c:choose>
				
					<c:choose>
						<c:when test="${operation eq 'showBooks'}">
										<div class="row row-eq-height pt-4">
												<c:forEach var="book" items="${books}">
													<div class="col-3 mb-4">
														<div class="card h-100 shadow">
															<img class="card-img-top" src="/images/<c:url value="${book.image_name}"/>">
															<div class="card-body d-flex flex-column text-center" style="height:100%;">
																<h5 class="card-title "><c:out value="${book.title}"/></h5>
																
																<div class="mt-auto"><small class="card-title"><c:out value="${book.release_date}"/></small>
																<div class="mt-auto">
																	<small class="text-muted mb-2"><c:out value="${book.publishingHouse.name}"/></small>
																	
																	<c:choose>
																		<c:when test="${book.count > 0 }">
																			<h6 class="card-title"><s:message code="book.quantity.available"/><c:out value="${book.count}"/></h6>
																		</c:when>
																		<c:when test="${book.count == 0}">
																			<h6 class="card-title" style="color: #d10202"><s:message code="fault.librarian.book.quantity"/></h6>
																		</c:when>
																	</c:choose>
																	
																	<input type="button"
																	class="btn btn-dark btn-sm btn-block"
																	onclick="window.location.href='${pageContext.request.contextPath}/showBooks/${currentcategory}/${currentPage}/${book.id}'"
																	value="<s:message code="button.show.description"/>" />
																	
																	<sec:authorize access="hasRole('ROLE_USER')">
																		<input type="button"
																		class="btn btn-success btn-sm btn-block"
																		onclick="window.location.href='${pageContext.request.contextPath}/showBooks/${currentcategory}/${currentPage}/${book.id}/reservation'"
																		value="<s:message code="button.reservation"/>" />
																	</sec:authorize>
																</div>
																</div>
															</div>
														</div>
													</div>
												</c:forEach>
										</div>

										<div class="text-center>">
											<div class="row">
												<div class="col-6">
												<c:if test="${currentPage > 0 }">
														<input type="button"
															class="btn btn-dark btn-block"
															onclick="window.location.href='${pageContext.request.contextPath}/showBooks/${currentcategory}/${currentPage-1}'"
															value="<s:message code="button.previous"/>" />
												</c:if>
												</div>
												<c:if test="${currentPage < totalPages-1 }">
													<div class="col-6">
														<input type="button"
															class="btn btn-dark btn-block" 
															onclick="window.location.href='${pageContext.request.contextPath}/showBooks/${currentcategory}/${currentPage+1}'"
															value="<s:message code="button.next"/>" />
													</div>
												</c:if>
											</div>
										</div>

							</c:when>
							<c:when test="${operation eq 'showBook'}">
								<div class="row m-5">

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

										<input type="button"
										class="btn btn-dark shadow" 
										onclick="window.location.href='${pageContext.request.contextPath}/showBooks/${currentcategory}/${currentPage}'"
										value="<s:message code="button.back.category"/>" />
									</div>
								</div>
								<div class="row mx-5">
									<div class="col-12">
										<div class="jumbotron jumbotron">
											<h3 class="text-center mb-4"><s:message code="book.description"/>:</h3>
											<p class="lead text-justify"><c:out value="${book.description }"/></p>
										</div>
									</div>
								</div>
									
							</c:when>
						</c:choose>
					</div>
			</div>
	</div>
	</div>
</body>

</html>