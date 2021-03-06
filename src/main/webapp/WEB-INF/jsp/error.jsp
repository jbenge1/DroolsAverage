<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- Used for logout logic -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "~//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org.TR.html4/loose.dtd">


<html>
<head>
<title>Error</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" />

<link
	href="https://fonts.googleapis.com/css?family=Raleway:400,300,600,800,900"
	rel="stylesheet" type="text/css">
<style>
body {
	/* 	background-color: #000; */
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
	
	<div>
		<h1>
			${error}
		</h1>
	</div>

</body>
</html>
