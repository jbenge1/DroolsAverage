<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<body>

<h1>Spring Boot file upload example</h1>

<form method="POST" action="/upload" enctype="multipart/form-data">
    <p>CSV file</p>
    <input type="file" name="file1" /><br/><br/>
    <p>Rules file</p>
    <input type="file" name="file2" /><br/><br/>
    <p>Month</p>
    <input type="text" name="month"/><br/><br/>
    <p>Year</p>
    <input type="text" name="year"/><br/><br/>
    <input type="submit" value="Submit" />
</form>

</body>
</html>
