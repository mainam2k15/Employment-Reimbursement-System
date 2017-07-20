<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Employee Homepage</title>
</head>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.0/jquery.min.js"></script>
<link
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
	rel="stylesheet">
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<body background="resources/img/bg.png">

	<nav class="navbar navbar-inverse">
		<div class="container-fluid">
			<div class="navbar-header">
				<a class="navbar-brand" href="homepage">Employee Homepage</a>
			</div>
			<ul class="nav navbar-nav">
				<li class="active"><a href="homepage">Home</a></li>
				<li><a href="viewInfo">View your info</a></li>
				<li><a href="submitReimbursement">Submit reimbursement</a></li>
				<li><a href="viewReimbursement">View reimbursements</a></li>
			</ul>
			<ul class="nav navbar-nav navbar-right">
				<li><a href="logout"><span
						class="glyphicon glyphicon-log-in"></span>Logout</a></li>
			</ul>
		</div>
	</nav>

	Welcome to employee homepage!

</body>
</html>