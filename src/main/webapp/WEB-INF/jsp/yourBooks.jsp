<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sec"	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <%@include file="/WEB-INF/include/bootstrap.incl" %>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title><s:message code="page.title.yourBooks"/></title>

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
					<a href="/yourBooks/reserved/0" class="list-group-item list-group-item-action"><s:message code="user.books.reserved.title"/></a>
					<a href="/yourBooks/borrowed/0" class="list-group-item list-group-item-action"><s:message code="user.books.borrowed.title"/></a>
				</div>
			</div>
			<div class="col-9 offset-1">
				<div class="container-fluid pt-3" style="background-color: white; box-shadow: 1px 4px 25px -4px rgba(0, 0, 0, 0.63); border-radius: 5px;">
					<c:choose>
						<c:when test="${operation eq 'showReservedBooks'}">

							<table class="table table-hover">
								<thead class="thead-dark">
									<tr>
										<th scope="col"><s:message code="book.cover"/></th>
										<th scope="col"><s:message code="book.title"/></th>
										<th scope="col"><s:message code="book.author"/></th>
										<th scope="col"><s:message code="book.publishingHouse"/></th>
										<th scope="col"><s:message code="book.date.expiration"/></th>

									</tr>
								</thead>
								<tbody>
									<c:forEach var="book" items="${books}">
										<tr>
											<td><img style="width:100px; height:155px;" src="/images/<c:url value="${book.image_name}"/>"></td>
											<td><b>&ldquor;<c:out value="${book.title}"/>&rdquo;</b></td>
											<td><c:out value="${book.author.name}"/> <c:out value="${book.author.surname}"/></td>
											<td><c:out value="${book.publishingHouse.name}"/></td>
											<td><b><font color="#2bbc29"><c:out value="${book.reservation_date}"/></font></b></td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
							
							<div class="text-center pb-4">
									<c:if test="${currentPage > 0 }">
											<input type="button"
												class="btn btn-dark"
												onclick="window.location.href='${pageContext.request.contextPath}/yourBooks/reserved/${currentPage-1}'"
												value="<s:message code="button.previous"/>" />
									</c:if>
									<c:if test="${currentPage < totalPages-1 }">
											<input type="button"
												class="btn btn-dark" 
												onclick="window.location.href='${pageContext.request.contextPath}/yourBooks/reserved/${currentPage+1}'"
												value="<s:message code="button.next"/>" />
									</c:if>
							</div>
							
						</c:when>
						
						<c:when test="${operation eq 'showBorrowedBooks'}">
						
							<table class="table table-hover">
								<thead class="thead-dark">
									<tr>
										<th scope="col"><s:message code="book.cover"/></th>
										<th scope="col"><s:message code="book.title"/></th>
										<th scope="col"><s:message code="book.author"/></th>
										<th scope="col"><s:message code="book.publishingHouse"/></th>
										<th scope="col"><s:message code="book.date.rental"/></th>
										<th scope="col"><s:message code="book.date.return"/></th>

									</tr>
								</thead>
								<tbody>
									<c:forEach var="book" items="${books}">
										<tr>
											<td><img style="width:100px; height:155px;" src="/images/<c:url value="${book.image_name}"/>"></td>
											<td><b>&ldquor;<c:out value="${book.title}"/>&rdquo;</b></td>
											<td><c:out value="${book.author.name}"/> <c:out value="${book.author.surname}"/></td>
											<td><c:out value="${book.publishingHouse.name}"/></td>
											<td><b><font color="#29bcb4"><c:out value="${book.rental_date}"/></font></b></td>
											<td><b><font color="#2bbc29"><c:out value="${book.return_date}"/></font></b></td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
							
							<div class="text-center pb-4">
									<c:if test="${currentPage > 0 }">
											<input type="button"
												class="btn btn-dark"
												onclick="window.location.href='${pageContext.request.contextPath}/yourBooks/borrowed/${currentPage-1}'"
												value="<s:message code="button.previous"/>" />
									</c:if>
									<c:if test="${currentPage < totalPages-1 }">
											<input type="button"
												class="btn btn-dark" 
												onclick="window.location.href='${pageContext.request.contextPath}/yourBooks/borrowed/${currentPage+1}'"
												value="<s:message code="button.next"/>" />
									</c:if>
							</div>
						
						</c:when>

					</c:choose>
				</div>
			</div>
	</div>
	</div>
</body>

</html>