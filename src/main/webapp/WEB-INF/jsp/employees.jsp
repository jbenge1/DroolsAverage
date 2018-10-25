<%@ page language="java" contentType="text/html; charset=US-ASCII"
	pageEncoding="US-ASCII"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="US-ASCII">
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
		</tr>
		<c:forEach items="${employee}" var="s">
			<tr>
					<td>${s.code }</td>
					<td>${s.performance1 }</td>
					<td>${s.performance2 }</td>
					<td>${s.performance3 }</td>
					<td>${s.performance4 }</td>
					<td>${s.performanceTotal }</td>
			</tr>
		</c:forEach>
	</table>

</body>
</html>