<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@include file="/WEB-INF/include/bootstrap.incl"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><s:message code="librarian.showBooks.title" /></title>
</head>
<body style="background-color: #E7E7E7">
	<%@include file="/WEB-INF/include/menu.incl"%>

	<div class="container-fluid px-5 pt-1 ">
		<div class="row m-5">
			<div class="col-2 text-center">
				<div class="list-group mb-4" style="background-color: white; box-shadow: 1px 4px 25px -4px rgba(0, 0, 0, 0.63); border-radius: 5px;">
						<a href="/manageBooks/book/addform" class="list-group-item list-group-item-action"><s:message code="librarian.option.addbook" /></a> 
						<a href="/manageBooks/book/deleteform" class="list-group-item list-group-item-action"><s:message code="librarian.option.deletebook" /></a> 
				</div>
				<div class="list-group mb-4" style="background-color: white; box-shadow: 1px 4px 25px -4px rgba(0, 0, 0, 0.63); border-radius: 5px;">
						<a href="/manageBooks/author/show/0" class="list-group-item list-group-item-action"><s:message code="librarian.option.showauthors" /></a>
						<a href="/manageBooks/author/addform" class="list-group-item list-group-item-action"><s:message code="librarian.option.addauthor" /></a> 
						<a href="/manageBooks/author/deleteform" class="list-group-item list-group-item-action"><s:message code="librarian.option.deleteauthor" /></a> 
				</div>
				
				<div class="list-group mb-4" style="background-color: white; box-shadow: 1px 4px 25px -4px rgba(0, 0, 0, 0.63); border-radius: 5px;">
					<a href="/manageBooks/category/show/0" class="list-group-item list-group-item-action"><s:message code="librarian.option.showcategories" /></a>
					<a href="/manageBooks/category/addform" class="list-group-item list-group-item-action"><s:message code="librarian.option.addcategory" /></a> 
					<a href="/manageBooks/category/deleteform" class="list-group-item list-group-item-action"><s:message code="librarian.option.deletecategory" /></a>				
				</div>
				
				<div class="list-group mb-4" style="background-color: white; box-shadow: 1px 4px 25px -4px rgba(0, 0, 0, 0.63); border-radius: 5px;">
					<a href="/manageBooks/publishinghouse/show/0" class="list-group-item list-group-item-action"><s:message code="librarian.option.publishinghouse.show" /></a>
					<a href="/manageBooks/publishinghouse/addform" class="list-group-item list-group-item-action"><s:message code="librarian.option.publishinghouse.add" /></a> 
					<a href="/manageBooks/publishinghouse/deleteform" class="list-group-item list-group-item-action"><s:message code="librarian.option.publishinghouse.delete" /></a>				
				</div>
			</div>
			<div class="col-9 offset-1">
				<div class="container-fluid pb-5 pt-2 px-5" style="background-color: white; box-shadow: 1px 4px 25px -4px rgba(0, 0, 0, 0.63); border-radius: 5px;">
						<c:choose>
							<c:when test="${not empty successAdd}">
								<div class="container alert alert-success mt-2 text-center px-4" role="alert"><b><s:message code="success.librarian.category.add"/></b></div>
							</c:when>
							<c:when test="${not empty successDelete}">
								<div class="container alert alert-success mt-2 text-center px-4" role="alert"><b><s:message code="success.librarian.category.delete"/></b></div>
							</c:when>
						</c:choose>
				
					<c:choose>
						<c:when test="${operation eq 'bookAdd'}">
							<h1 class="text-center font-weight-light my-4">
								<s:message code='librarian.option.addbook.title' />
							</h1>
							<sf:form id="addBook" action="/manageBooks/book/add" modelAttribute="book" enctype="multipart/form-data" method="POST" class="px-5">
								<sf:hidden path="id"/>
								<sf:hidden path="image"/>
									<div class="form-group">
										<label for="title"><s:message code="book.title" /></label>
										<sf:input path="title" type="text" id="title" class="form-control" />
										<sf:errors path="title" class="text-danger" />
									</div>
									
									<div class="form-group">
										<label for="author"><s:message code="book.author"/></label>
										<sf:select path="authorId" items="${authorMap}" id="author" class="form-control"/>
									</div>
									
									<div class="form-group">
										<label for="publishingHouse"><s:message code="book.publishingHouse"/></label>
										<sf:select path="publishingHouseId" items="${publishingHouseMap}" id="publishingHouse" class="form-control"/>
									</div>
									
									<div class="form-group">
										<label for="releaseDate"><s:message code="book.releaseDate" /></label>
										<sf:input path="date" type="date" id="releaseDate" class="form-control" />
										<sf:errors path="date" class="text-danger" />
									</div>
									
									<div class="form-group">
										<label for="category"><s:message code="book.category"/></label>
										<sf:select path="categoryId" items="${categoryMap}" id="category" class="form-control"/>
									</div>
									
									<div class="form-group">
										<label for="count"><s:message code="book.count" /></label>
										<sf:input path="count" type="number" value="1" id="count" class="form-control" />
										<sf:errors path="count" class="text-danger" />
									</div>
									
									<div class="form-group">
										<label for="description"><s:message code="book.description" /></label>
										<sf:textarea path="description" rows="4" type="text" id="description" class="form-control" style="resize: none;"/>
										<sf:errors path="description" class="text-danger" />
									</div>
									
									<div class="form-group">
										<sf:input path="image" type="file" id="file" class="form-control" />
										<!-- <input type="file" value="file" name="file"/> -->
									</div>
									
									<input type="submit" value="<s:message code="button.save"/> " class="btn btn-success"/>
									<input type="button" value="<s:message code="button.cancel"/>" class="btn btn-secondary" onclick="window.location.href='${pageContext.request.contextPath}/manageBooks'" />
							</sf:form>
						</c:when>
						
						<c:when test="${operation eq 'bookDelete'}">
							<h1 class="text-center font-weight-light my-4">
								<s:message code='librarian.option.deletebook.title' />
							</h1>
							<sf:form id="addBook" action="/manageBooks/book/delete" modelAttribute="book" enctype="multipart/form-data" method="POST" class="px-5">
								<sf:hidden path="id"/>
									<div class="form-group">
										<label for="title"><s:message code="book.title" /></label>
										<sf:input path="title" type="text" id="title" class="form-control" />
										<sf:errors path="title" class="text-danger" />
									</div>
									
									<div class="form-group">
										<label for="author"><s:message code="book.author"/></label>
										<sf:select path="authorId" items="${authorMap}" id="author" class="form-control"/>
									</div>
									
									<input type="submit" value="<s:message code="button.save"/> " class="btn btn-success"/>
									<input type="button" value="<s:message code="button.cancel"/>" class="btn btn-secondary" onclick="window.location.href='${pageContext.request.contextPath}/manageBooks'" />
							</sf:form>
						</c:when>
					
						<c:when test="${operation eq 'authorsShow'}">
							<h1 class="text-center font-weight-light my-4">
								<s:message code='librarian.option.showauthors.title' />
							</h1>
							
							<table class="table table-striped">
								<thead>
									<tr>
										<th scope="col"><s:message code="author.name"/></th>
										<th scope="col"><s:message code="author.surname"/></th>
									</tr>
								</thead>
								<tbody>
									<c:forEach var="author" items="${authors}">
										<tr>
											<td><c:out value="${author.name }"/></td>
											<td><c:out value="${author.surname }"/></td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
							
							<c:if test="${currentPage > 0 }">
								<input type="button"
									class="btn btn-dark"
									onclick="window.location.href='${pageContext.request.contextPath}/manageBooks/author/show/${currentPage-1}'"
									value="<s:message code="button.previous"/>" />&nbsp;&nbsp;
							</c:if>
							<c:if test="${currentPage < totalPages-1 }">
								<input type="button"
									class="btn btn-dark" 
									onclick="window.location.href='${pageContext.request.contextPath}/manageBooks/author/show/${currentPage+1}'"
									value="<s:message code="button.next"/>" />
							</c:if>
						</c:when>
						
						<c:when test="${operation eq 'authorAdd'}">
							<h1 class="text-center font-weight-light my-4">
								<s:message code='librarian.option.addauthor' />
							</h1>
							<sf:form id="addAuthor" action="/manageBooks/author/add" modelAttribute="author" enctype="multipart/form-data" method="POST" class="px-5">
									<sf:hidden path="id"/>
								 	<div class="form-group">
										<label for="name"><s:message code="author.name" /></label>
										<sf:input path="name" type="text" id="name" class="form-control" />
										<sf:errors path="name" class="text-danger" />
									</div>
									<div class="form-group">
										<label for="name"><s:message code="author.surname" /></label>
										<sf:input path="surname" type="text" id="surname" class="form-control" />
										<sf:errors path="surname" class="text-danger" />
									</div>
									
									<input type="submit" value="<s:message code="button.save"/> " class="btn btn-success"/>
									<input type="button" value="<s:message code="button.cancel"/>" class="btn btn-secondary" onclick="window.location.href='${pageContext.request.contextPath}/manageBooks'" />
							</sf:form>
						</c:when>
						
						<c:when test="${operation eq 'authorDelete'}">
							<h1 class="text-center font-weight-light my-4">
								<s:message code='librarian.option.deleteauthor' />
							</h1>
							<sf:form id="deleteAuthor" action="/manageBooks/author/delete" modelAttribute="author" enctype="multipart/form-data" method="POST" class="px-5">
									<sf:hidden path="id"/>
								 	<div class="form-group">
										<label for="name"><s:message code="author.name" /></label>
										<sf:input path="name" type="text" id="name" class="form-control" />
										<sf:errors path="name" class="text-danger" />
									</div>
									<div class="form-group">
										<label for="name"><s:message code="author.surname" /></label>
										<sf:input path="surname" type="text" id="surname" class="form-control" />
										<sf:errors path="surname" class="text-danger" />
									</div>
									
									<input type="submit" value="<s:message code="button.save"/> " class="btn btn-success"/>
									<input type="button" value="<s:message code="button.cancel"/>" class="btn btn-secondary" onclick="window.location.href='${pageContext.request.contextPath}/manageBooks'" />
							</sf:form>
						</c:when>
					
						<c:when test="${operation eq 'categoriesShow'}">
							<h1 class="text-center font-weight-light my-4">
								<s:message code='librarian.option.showcategories.title' />
							</h1>
							
							<table class="table table-striped">
								<thead>
									<tr>
										<th scope="col" class="text-center"><s:message code="category.name"/></th>
									</tr>
								</thead>
								<tbody>
									<c:forEach var="category" items="${categories}">
										<tr>
											<td class="text-center"><c:out value="${category.name }"/></td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
							
							<c:if test="${currentPage > 0 }">
								<input type="button"
									class="btn btn-dark"
									onclick="window.location.href='${pageContext.request.contextPath}/manageBooks/category/show/${currentPage-1}'"
									value="<s:message code="button.previous"/>" />&nbsp;&nbsp;
							</c:if>
							<c:if test="${currentPage < totalPages-1 }">
								<input type="button"
									class="btn btn-dark" 
									onclick="window.location.href='${pageContext.request.contextPath}/manageBooks/category/show/${currentPage+1}'"
									value="<s:message code="button.next"/>" />
							</c:if>
						</c:when>
						
						<c:when test="${operation eq 'categoryAdd'}">
							<h1 class="text-center font-weight-light my-4">
								<s:message code='librarian.option.addcategory' />
							</h1>
							<sf:form id="addCategory" action="/manageBooks/category/add" modelAttribute="category" enctype="multipart/form-data" method="POST" class="px-5">
								<sf:hidden path="id"/>
									<div class="form-group">
										<label for="name"><s:message code="category.name" /></label>
										<sf:input path="name" type="text" id="name" class="form-control" />
										<sf:errors path="name" class="text-danger" />
									</div>
									
									<input type="submit" value="<s:message code="button.save"/> " class="btn btn-success"/>
									<input type="button" value="<s:message code="button.cancel"/>" class="btn btn-secondary" onclick="window.location.href='${pageContext.request.contextPath}/manageBooks'" />
							</sf:form>
						</c:when>
						
						<c:when test="${operation eq 'categoryDelete'}">
							<h1 class="text-center font-weight-light my-4">
								<s:message code='librarian.option.deletecategory' />
							</h1>
							<sf:form id="addCategory" action="/manageBooks/category/delete" modelAttribute="category" enctype="multipart/form-data" method="POST" class="px-5">
								<sf:hidden path="id"/>
									<div class="form-group">
										<label for="name"><s:message code="category.name" /></label>
										<sf:input path="name" type="text" id="name" class="form-control" />
										<sf:errors path="name" class="text-danger" />
									</div>
									
									<input type="submit" value="<s:message code="button.save"/> " class="btn btn-success"/>
									<input type="button" value="<s:message code="button.cancel"/>" class="btn btn-secondary" onclick="window.location.href='${pageContext.request.contextPath}/manageBooks'" />
							</sf:form>
						</c:when>
						
						<c:when test="${operation eq 'publishingHouseAdd'}">
							<h1 class="text-center font-weight-light my-4">
								<s:message code='librarian.option.publishinghouse.add' />
							</h1>
							<sf:form id="addPublishingHouse" action="/manageBooks/publishinghouse/add" modelAttribute="publishingHouse" enctype="multipart/form-data" method="POST" class="px-5">
								<sf:hidden path="id"/>
									<div class="form-group">
										<label for="name"><s:message code="librarian.option.publishinghouse.name" /></label>
										<sf:input path="name" type="text" id="name" class="form-control" />
										<sf:errors path="name" class="text-danger" />
									</div>
									
									<input type="submit" value="<s:message code="button.save"/> " class="btn btn-success"/>
									<input type="button" value="<s:message code="button.cancel"/>" class="btn btn-secondary" onclick="window.location.href='${pageContext.request.contextPath}/manageBooks'" />
							</sf:form>
						</c:when>
						
						<c:when test="${operation eq 'publishingHouseDelete'}">
							<h1 class="text-center font-weight-light my-4">
								<s:message code='librarian.option.publishinghouse.delete' />
							</h1>
							<sf:form id="deletePublishingHouse" action="/manageBooks/publishinghouse/delete" modelAttribute="publishingHouse" enctype="multipart/form-data" method="POST" class="px-5">
								<sf:hidden path="id"/>
									<div class="form-group">
										<label for="name"><s:message code="librarian.option.publishinghouse.name" /></label>
										<sf:input path="name" type="text" id="name" class="form-control" />
										<sf:errors path="name" class="text-danger" />
									</div>
									
									<input type="submit" value="<s:message code="button.save"/> " class="btn btn-success"/>
									<input type="button" value="<s:message code="button.cancel"/>" class="btn btn-secondary" onclick="window.location.href='${pageContext.request.contextPath}/manageBooks'" />
							</sf:form>
						</c:when>
						
						<c:when test="${operation eq 'publishingHousesShow'}">
							<h1 class="text-center font-weight-light my-4">
								<s:message code='librarian.option.publishinghouse.title' />
							</h1>
							
							<table class="table table-striped">
								<thead>
									<tr>
										<th scope="col" class="text-center"><s:message code="category.name"/></th>
									</tr>
								</thead>
								<tbody>
									<c:forEach var="publishingHouse" items="${publishingHouses}">
										<tr>
											<td class="text-center"><c:out value="${publishingHouse.name }"/></td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
							
							<c:if test="${currentPage > 0 }">
								<input type="button"
									class="btn btn-dark"
									onclick="window.location.href='${pageContext.request.contextPath}/manageBooks/publishinghouse/show/${currentPage-1}'"
									value="<s:message code="button.previous"/>" />&nbsp;&nbsp;
							</c:if>
							<c:if test="${currentPage < totalPages-1 }">
								<input type="button"
									class="btn btn-dark" 
									onclick="window.location.href='${pageContext.request.contextPath}/manageBooks/publishinghouse/show/${currentPage+1}'"
									value="<s:message code="button.next"/>" />
							</c:if>
						</c:when>
					</c:choose>
				</div>
			</div>
		</div>
	</div>
</body>
</html>