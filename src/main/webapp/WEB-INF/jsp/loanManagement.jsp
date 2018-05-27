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

	
	<div class="container-fluid px-5 pt-1 ">
		<div class="row m-5">
			<div class="col-2">
				<div class="list-group mb-4" style="background-color: white; box-shadow: 1px 4px 25px -4px rgba(0, 0, 0, 0.63); border-radius: 5px;">
					<a href="/libraryManage/user/reservations/show" class="list-group-item list-group-item-action"><s:message code="librarian.manageLoans.option.reservations.show"/></a>
					<a href="/libraryManage/user/borrows/show" class="list-group-item list-group-item-action"><s:message code="librarian.manageLoans.option.borrows.show"/></a>
				</div>
			</div>
			<div class="col-9 offset-1">
				<div class="container-fluid p-3" style="background-color: white; box-shadow: 1px 4px 25px -4px rgba(0, 0, 0, 0.63); border-radius: 5px;">
					
					<c:choose>
						<c:when test="${operation eq 'getUserReservations'}">
							<h1 class="text-center font-weight-light my-4">
								<s:message code='librarian.manageLoans.option.reservations.show' />
							</h1>
						
							<sf:form id="findUser" action="/libraryManage/user/getUserReservations" modelAttribute="user" enctype="multipart/form-data" method="POST">
								<sf:hidden path="id" />
								<sf:hidden path="active" />
								<sf:hidden path="email" />
								
								<div class="form-group">
									<label for="name"><s:message code="register.name"/></label>
									<sf:input path="name" type="text" id="name" class="form-control" />
								</div>
								
								<div class="form-group">
									<label for="surname"><s:message code="register.surname" /></label>
									<sf:input path="surname" type="text" id="surname" class="form-control" />
								</div>
								
								<c:choose>
									<c:when test="${success == 0}">
										<div class="alert alert-danger text-center" role="alert"><b><s:message code="statement.failure.user.found"/></b></div>
									</c:when>
								</c:choose>
								
								<input type="submit" value="<s:message code="button.user.find"/>" class="btn btn-block btn-primary my-3"/>
							
							</sf:form>
						</c:when>
						
						<c:when test="${operation eq 'getUserBorrows'}">
							
							<h1 class="text-center font-weight-light my-4">
								<s:message code='librarian.manageLoans.option.borrows.show' />
							</h1>
						
							<sf:form id="findUser" action="/libraryManage/user/getUserBorrows" modelAttribute="user" enctype="multipart/form-data" method="POST">
								<sf:hidden path="id" />
								<sf:hidden path="active" />
								<sf:hidden path="email" />
								
								<div class="form-group">
									<label for="name"><s:message code="register.name"/></label>
									<sf:input path="name" type="text" id="name" class="form-control" />
								</div>
								
								<div class="form-group">
									<label for="surname"><s:message code="register.surname" /></label>
									<sf:input path="surname" type="text" id="surname" class="form-control" />
								</div>
								
								<c:choose>
									<c:when test="${success == 0}">
										<div class="alert alert-danger text-center" role="alert"><b><s:message code="statement.failure.user.found"/></b></div>
									</c:when>
								</c:choose>
								
								<input type="submit" value="<s:message code="button.user.find"/>" class="btn btn-block btn-primary my-3"/>
							
							</sf:form>
						</c:when>
						
						<c:when test="${operation eq 'showUsers'}">
							
							<table class="table table-hover">
								<thead>
									<tr>
										<th scope="col"><s:message code="register.email"/></th>
										<th scope="col"><s:message code="register.name"/></th>
										<th scope="col"><s:message code="register.surname"/></th>
										<th scope="col"><s:message code="register.age"/></th>
										<th scope="col"><s:message code="register.city"/></th>
										<th scope="col"><s:message code="register.street"/></th>
										<th scope="col"><s:message code="register.streetnumber"/></th>
										<th scope="col"><s:message code="admin.users.column.operations"/></th>
									</tr>
								</thead>
								<tbody>	
									<c:forEach var="user" items="${users}">
										<tr>
											<td><c:out value="${user.email}"/></td>
											<td><c:out value="${user.name}"/></td>
											<td><c:out value="${user.surname}"/></td>
											<td><c:out value="${user.age}"/></td>
											<td><c:out value="${user.address.city}"/></td>
											<td><c:out value="${user.address.street}"/></td>
											<td><c:out value="${user.address.number}"/></td>
											<td>			
											<c:choose>
												<c:when test="${NextOperation eq 'getReservations'}">
													<input type="button" class="btn btn-sm btn-secondary btn-block" onclick="window.location.href='${pageContext.request.contextPath}/libraryManage/user/reservations/show/${user.id}'" value="<s:message code="button.user.reservations"/>" />
												</c:when>
												<c:when test="${NextOperation eq 'getBorrows'}">
													<input type="button" class="btn btn-sm btn-secondary btn-block" onclick="window.location.href='${pageContext.request.contextPath}/libraryManage/user/borrows/show/${user.id}'" value="<s:message code="librarian.manageLoans.option.borrows.show"/>" />
												</c:when>
											</c:choose>
											</td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
							
						</c:when>
						
						<c:when test="${operation eq 'showUserReservations'}">
							
							<h1 class="text-center font-weight-light my-5">
								 <s:message code='librarian.manageLoans.option.borrows.title' /> <c:out value="${user.name}"/>
							</h1>
							
							<table class="table table-hover">
								<thead>
									<tr>
										<th scope="col"><s:message code="book.cover"/></th>
										<th scope="col" style="width: 40%"><s:message code="book.title"/></th>
										<th scope="col"><s:message code="book.author"/></th>
										<th scope="col"><s:message code="book.publishingHouse"/></th>
										<th scope="col"><s:message code="admin.users.column.operations"/></th>
									</tr>
								</thead>
								<tbody>
									<c:forEach var="book" items="${books}">
										<tr>
											<td><img style="width:100px; height:155px;" src="/images/<c:url value="${book.image_name}"/>"></td>
											<td><b>&ldquor;<c:out value="${book.title}"/>&rdquo;</b></td>
											<td><c:out value="${book.author.name}"/> <c:out value="${book.author.surname}"/></td>
											<td><c:out value="${book.publishingHouse.name}"/></td>
											<td>			
												<input type="button" class="btn btn-sm btn-primary btn-block" onclick="window.location.href='${pageContext.request.contextPath}/libraryManage/user/borrow/${userID}/${book.id}'" value="<s:message code="button.user.borrow"/>" />
											</td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
							
						</c:when>
						
						<c:when test="${operation eq 'showUserBorrows'}">
							
							<h1 class="text-center font-weight-light my-5">
								<s:message code='librarian.manageLoans.option.reservations.title' /><c:out value="${user.name}"/>
							</h1>
							
							<table class="table table-hover">
								<thead>
									<tr>
										<th scope="col"><s:message code="book.cover"/></th>
										<th scope="col" style="width: 40%"><s:message code="book.title"/></th>
										<th scope="col"><s:message code="book.author"/></th>
										<th scope="col"><s:message code="book.publishingHouse"/></th>
										<th scope="col"><s:message code="admin.users.column.operations"/></th>
									</tr>
								</thead>
								<tbody>
									<c:forEach var="book" items="${books}">
										<tr>
											<td><img style="width:100px; height:155px;" src="/images/<c:url value="${book.image_name}"/>"></td>
											<td><b>&ldquor;<c:out value="${book.title}"/>&rdquo;</b></td>
											<td><c:out value="${book.author.name}"/> <c:out value="${book.author.surname}"/></td>
											<td><c:out value="${book.publishingHouse.name}"/></td>
											<td>			
												<input type="button" class="btn btn-sm btn-primary btn-block" onclick="window.location.href='${pageContext.request.contextPath}/libraryManage/user/return/${userID}/${book.id}'" value="<s:message code="librarian.manageLoans.option.borrows.delete"/>" />
											</td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
							
						</c:when>
						

					</c:choose>
				</div>
			</div>
		</div>
	</div>

</body>
</html>