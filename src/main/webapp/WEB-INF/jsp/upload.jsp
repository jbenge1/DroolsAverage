<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<head>
<title>Upload</title>
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
	<nav class="navbar navbar-inverse">
	<div class="container-fluid">
		<div class="navbar-header">
			<a class="navbar-brand" href="#">Temp...</a>
		</div>
		<ul class="nav navbar-nav">
			<li><a href="/">Home</a></li>
			<li><a href="/ViewEmployees">Employees</a></li>
			<li><a href="/load">Upload</a></li>
		</ul>

		<div class="container">
			<ul class="nav navbar-nav navbar-right">
				<li><a href="logout"><span
						class="glyphicon glyphicon-log-out"></span> Logout</a></li>
			</ul>
		</div>
	</nav>
<div class="row col-sm-6"
		style="border: 1px ridge #F93; padding:20px; float: none; margin: 0 auto;">
	<h1 class="text-center" style="font-size:25px; font-family:Raleway">Spring Boot file upload example</h1>

	<form method="POST" action="/upload" enctype="multipart/form-data">
		<p>CSV file</p>
		<input type="file" name="file1" class="btn btn-primary"/><br /> <br />
		
		<p>Rules file</p>
		<input type="file" name="file2" class="btn btn-primary"/><br /> <br />
		
		<p>Month</p>
		<input type="text" name="month" class="form-control"/><br /> <br />
		
		<p>Year</p>
		<input type="text" name="year" class="form-control"/><br /> <br /> <input type="submit"
			value="Submit" class="btn btn-primary btn-block"/>
	</form>
</div>
</body>
</html>
