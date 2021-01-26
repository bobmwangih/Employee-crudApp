<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Employee</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
<link rel="stylesheet"
	,href="https://cdn.datatables.net/1.10.23/css/jquery.dataTables.min.css">
<script type="text/javascript"
	src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<script type="text/javascript"
	src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.bundle.min.js"></script>
<script type="text/javascript"
	src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.bundle.min.js"></script>
<script type="text/javascript"
	src="https://cdn.datatables.net/1.10.23/js/jquery.dataTables.min.js"></script>
</head>
<body>
	<div class="container">
		<a href="employees"><button type="button" class="btn btn-info">Home</button></a>
		<br> <br>
		<form action="update" method="post">
			<div class="form-group">
				<label for="fname">First Name</label> <input type="text"
					class="form-control" id="fname" name="fname"
					value="${employee.fname}">
			</div>
			<div class="form-group">
				<label for="lname">Last Name</label> <input type="text"
					class="form-control" id="lname" name="lname"
					value="${employee.lname}">
			</div>
			<div class="form-group">
				<label for="email">Email</label> <input type="email"
					class="form-control" id="email" name="email"
					value="${employee.email}">
			</div>
			<div class="form-group">
				<label for="role">Position</label> <input type="text"
					class="form-control" id="role" name="role" value="${employee.role}">
			</div>
			<div class="form-group">

				<input type="hidden" class="form-control" id="id" name="id"
					value="${employee.id}">
			</div>


			<button type="submit" class="btn btn-outline-success">Submit</button>
		</form>

	</div>


</body>
</html>