<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"  %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags"  %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>luv2code Company Home Page</title>
</head>
<body>
	<h2>luv2code Company Home Page</h2>
	<hr>
	<p>Welcome to me, 
	
	<!-- display username and role -->
	<label>User: </label><span><security:authentication property="principal.username" /></span>
	
	<label>Role(s): </label><span><security:authentication property="principal.authorities" /></span>
	</p>
	
	<hr>
	
		
	
		<a href="${pageContext.request.contextPath}/leaders">Leadership Meeting</a>
		<span>(Only for Manager peeps)</span>
	<hr>

	<!--  Add a logout button -->
	<form:form action="${pageContext.request.contextPath}/logout"
	method="POST">
		<input type="submit" value="Logout" class="btn btn-sm" />
	</form:form>
	
	
</body>
</html>