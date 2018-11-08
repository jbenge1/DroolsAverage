<%@ page language="java" contentType="text/html; charset=US-ASCII"
	pageEncoding="US-ASCII"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%-- <%@include file="../../../resources/static/stylesheets/bulma.css"%> --%>


<!DOCTYPE html>
<html>
<head>
<meta charset="US-ASCII">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Employee KPI's</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" />

<link
	href="https://fonts.googleapis.com/css?family=Raleway:400,300,600,800,900"
	rel="stylesheet" type="text/css">
<style>
body {
	font-family: Raleway;
}
</style>
</head>
<body>
	<nav class="navbar navbar-inverse">
	<div class="container-fluid">
		<div class="navbar-header">
			<a class="navbar-brand" href="#">Temp...</a>
		</div>
		<ul class="nav navbar-nav">
			<li><a href="/">Home</a></li>
			<li><a href="/ViewEmployees">Employees</a></li>
			<li><a href="/load">Upload</a></li>
			<li><a href="/searchForRankings">Search</a></li>
		</ul>

		<div class="container">
			<ul class="nav navbar-nav navbar-right">
				<li><a href="logout"><span
						class="glyphicon glyphicon-log-out"></span> Logout</a></li>
			</ul>
		</div>
		</div>
	</nav>
	<table class="table table-dark table-striped">
		<thead>
			<tr>
				<td colspan="5"><h1>Employee
						Information</h1></td>
				<td><a href="/"></a></td>
			</tr>

			<tr>
				<td scope="col">Code</td>
				<td scope="col">kpi1</td>
				<td scope="col">kpi2</td>
				<td scope="col">kpi3</td>
				<td scope="col">kpi4</td>
				<td scope="col">kpiTotal</td>
				<td scope="col">Name</td>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${employee}" var="s">
				<tr >
					<td>${s.get(0) }</td>
					<td>${s.get(1) }</td>
					<td>${s.get(2) }</td>
					<td>${s.get(3) }</td>
					<td>${s.get(4) }</td>
					<td>${s.get(5) }</td>
					<td>${s.get(6) }</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>

</body>
</html>