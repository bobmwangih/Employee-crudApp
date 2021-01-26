<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>
<html>
<head>
<title>Employee</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">

</head>
<body>
	<div class="container">
		
		<div class="container">
			<div class="row">
				<div class="col-md-2">
					<a href="create"><button type="button" class="btn btn-primary">Create
							Employee</button></a>
				</div>
				<div class="col-md-2">
					<a href="employees"><button type="button"
							class="btn btn-primary">View Employees</button></a>
				</div>
				<div class="col-md-2">
					<a href="download-pdf"><button type="button"
							class="btn btn-primary">Download Pdf</button></a>
				</div>
				
				<div class="col-md-2">
					<a href="download-excel"><button type="button"
							class="btn btn-primary">Download Excel</button></a>
				</div>
				<div class="col-md-4">
					<a href="download-doc"><button type="button"
							class="btn btn-primary">Download Document</button></a>
				</div>
				
				


			</div>
		</div>
		<br>
		<br>

		<table class="table table-striped table-bordered">
			<caption>List of employees</caption>
			<thead>
				<tr>
					<th scope="col">First Name</th>
					<th scope="col">Last Name</th>
					<th scope="col">Email</th>
					<th scope="col">Position</th>
					<th scope="col">Delete</th>
					<th scope="col">Update</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${employees}" var="employee">
					<tr>
						<td><c:out value="${employee.fname}" /></td>
						<td><c:out value="${employee.lname}" /></td>
						<td><c:out value="${employee.email}" /></td>
						<td><c:out value="${employee.role}" /></td>
						<td><a href="delete-employee?id=${employee.id}"><button
									class="btn btn-outline-danger" type="button">Delete</button></a></td>
						<td><a href="update-employee?id=${employee.id}"><button
									class="btn btn-outline-primary" type="button">Update</button></a></td>
					</tr>
				</c:forEach>

			</tbody>
		</table>


	</div>

</body>
</html>