<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
		<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Add Employee</title>
	</head>
		
	<body>
		<h1>Add Employee Form</h1>
		<div>
		<a href="/">Home</a>
		</div>
		
		<form:form action="addUser" method="post" modelAttribute="user">
			<table>
				<tr>
					<td>User Name</td>
					<td><form:input path="userName"/></td>
				</tr>
				<tr>
					<td>Password</td>
					<td><form:input type="password" path="password"/></td>
				</tr>
				<tr>
					<td></td>
					<td><form:button>Submit</form:button></td>
				</tr>
				
			</table>
		</form:form>
	</body>
</html>