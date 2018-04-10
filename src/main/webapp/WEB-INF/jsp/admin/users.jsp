<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title><s:message code="admin.users" /></title>
	<%@include file="/WEB-INF/include/bootstrap.incl"%>
	
	<script type="text/javascript">
	    $("[data-toggle=\"tooltip\"]").tooltip();
	</script>
</head>
<body style="background-color: #E7E7E7">
	<%@include file="/WEB-INF/include/adminmenu.incl"%>

	<div class="container mt-5 p-4"
		style="width: 90%; background-color: #F2F2F2; box-shadow: 1px 4px 81px -4px rgba(0, 0, 0, 0.63); border-radius: 5px;">
		<h1 class="text-center font-weight-light mb-4">
			<s:message code='admin.users.list' />
		</h1>

		<table class="table table-striped">
			<thead>
				<tr>
					<th scope="col"><s:message code="admin.users.column.name"/></th>
					<th scope="col"><s:message code="admin.users.column.surname"/></th>
					<th scope="col"><s:message code="admin.users.column.age"/></th>
					<th scope="col"><s:message code="admin.users.column.email"/></th>
					<th scope="col"><s:message code="admin.users.column.date"/></th>
					<th scope="col"><s:message code="admin.users.column.active"/></th>
					<th scope="col"><s:message code="admin.users.column.role"/></th>
					<th scope="col"><s:message code="admin.users.column.operations"/></th>
				</tr>
			</thead>
			
			<tbody>
				<c:forEach var="user" items="${userList}">
					<tr>
						<td><c:out value="${user.name }"/></td>
						<td><c:out value="${user.surname }"/></td>
						<td><c:out value="${user.age }"/></td>
						<td><c:out value="${user.email }"/></td>
						<td><c:out value="${user.registerDate }"/></td>
						<td><c:choose>
							<c:when test="${user.active == true }">
								<font color="green"><s:message code="admin.users.column.active.yes"/></font><br/>
							</c:when>
							<c:otherwise>
								<font color="red"><s:message code="admin.users.column.active.no"/></font><br/>
							</c:otherwise>
						</c:choose></td>
						<td><c:choose>
							<c:when test="${user.roleId == 1}">
								<b> <s:message code="admin.users.column.role.user" /></b>
							</c:when>
							<c:when test="${user.roleId == 2}">
								<font color="#ea3500"><b><s:message code="admin.users.column.role.admin" /></b></font>
							</c:when>
						</c:choose></td>
						<td><a href="/admin/edit/${user.id}" style="color: black" rel="tooltip" title="<s:message code="admin.user.edit.title"/>"><svg id="i-compose" viewBox="0 0 32 32" width="20" height="20" fill="none" stroke="currentcolor" stroke-linecap="round" stroke-linejoin="round" stroke-width="2">
						    <path d="M27 15 L27 30 2 30 2 5 17 5 M30 6 L26 2 9 19 7 25 13 23 Z M22 6 L26 10 Z M9 19 L13 23 Z" />
						</svg></a>
						
						<script>
							$("* [rel='tooltip']").tooltip({
							   html: true, 
							   placement: 'bottom'
							});
						</script> 
						
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</body>
</html>