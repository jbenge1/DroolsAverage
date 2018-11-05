<%@ page language="java" contentType="text/html; charset=US-ASCII"
	pageEncoding="US-ASCII"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%-- <%@include file="../../../resources/static/stylesheets/bulma.css"%> --%>


<!DOCTYPE html>
<html>
<head>
	<meta charset="US-ASCII">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>Some Title</title>
</head>
<body>
	<table>
		<tr>
			<td colspan="5"><h1>Employee Information</h1></td>
		</tr>
		<tr>
			<td>Code</td>
			<td>kpi1</td>
			<td>kpi2</td>
			<td>kpi3</td>
			<td>kpi4</td>
			<td>kpiTotal</td>
			<td>Name</td>
		</tr>
		<c:forEach items="${employee}" var="s">
			<tr>
				<td>${s.get(0) }</td>
				<td>${s.get(1) }</td>
				<td>${s.get(2) }</td>
				<td>${s.get(3) }</td>
				<td>${s.get(4) }</td>
				<td>${s.get(5) }</td>
				<td>${s.get(6) }</td>
			</tr>
		</c:forEach>
	</table>

</body>
</html>