<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>


<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml"
	xmlns:th="http://www.thymeleaf.org">
<head>
	<title>Some different obscure title</title>
	<meta charset="utf-8"/>
	<meta name="viewport" content="width=device-width, initial-scale=1"/>
	<link rel="stylesheet"
		href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"/>
	
	 <link href="https://fonts.googleapis.com/css?family=Raleway:400,300,600,800,900" rel="stylesheet" type="text/css">
	<style>
		body {
			background-color: #000;
			font-family: Raleway;
		}
		
		#password, .btn {
			margin-top: 5px;
		}
		
	</style>
</head>
<body class="container" style="margin-top:50px">
	<div class="row col-sm-6" 
				style="border: 1px ridge #F93; padding:20px; float: none; margin: 0 auto;">
		<h5 class="text-center" style="font-size: 25px; color: #FFF; font-family:Raleway">Sign In</h5>
		<a href="/employeeAddForm">Register</a>
 		<form method="POST" action="${contextPath}/login" class="form-signin">
			
			<div class="form-group ${error != null ? 'has-error' : ''}">
				<p style="color: red">${errorMsg}</p>
				<h1 style="color: blue">${msg}</h1>
				
				<label for="username" style="color: #FFF">User Name: </label>
				<input type="text" class="form-control" id="username" placeholder="Enter UserName" name="username"/>
				
				<label for="password" id="password" style="color: #FFF">Password: </label>
				<input type="password" class="form-control" id="password" placeholder="Enter Password" name="password"/>
				
				<button type="submit" class="btn btn-primary btn-block" style="background-color:#F93">Submit</button>
			</div>
			
		</form>
		
	</div>
</body>
</html>