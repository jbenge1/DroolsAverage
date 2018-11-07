<%@ page language="java" contentType="text/html; charset=US-ASCII"
	pageEncoding="US-ASCII"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="US-ASCII">
<title>Insert title here</title>
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
<body class="container" style="margin-top:50px">

	<div class="row col-sm-6"
		style="border: 1px ridge #F93; padding: 20px; float: none; margin: 0 auto;">
		<h5 class="text-center" style="font-size: 25px; font-family:Raleway">Register</h5>
		<form method="POST" action="/employeeAdd">
			<p>Username</p>
			<input type="text" name="username" class = "form-control"/><br /> <br />
			<p>Password</p>
			<input type="password" name="password" class="form-control" style="color: #FFF" /><br />
			<br /> <input type="submit" value="Register"
				class="btn btn-primary btn-block" style="background-color: #F93" />
		</form>
	</div>
</body>
</html>