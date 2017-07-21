<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>View Reimbursements</title>
</head>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.0/jquery.min.js"></script>
<link
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
	rel="stylesheet">
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script src="resources/js/viewReimbursement.js" type="text/javascript"></script>
<link rel="stylesheet" href="resources/css/viewReimbursement.css"/>
<body background="resources/img/bg.png">

	<nav class="navbar navbar-inverse">
		<div class="container-fluid">
			<div class="navbar-header">
				<a class="navbar-brand" href="homepage">Employee Homepage</a>
			</div>
			<ul class="nav navbar-nav">
				<li><a href="homepage">Home</a></li>
				<li><a href="viewInfo">View your info</a></li>
				<li><a href="submitReimbursement">Submit reimbursement</a></li>
				<li class="active"><a href="viewReimbursement">View
						reimbursements</a></li>
			</ul>
			<ul class="nav navbar-nav navbar-right">
				<li><a href="logout"><span
						class="glyphicon glyphicon-log-in"></span>Logout</a></li>
			</ul>
		</div>
	</nav>

	<div class="container">
		<div class="row">
			<div class="col-md-12">
				<div class="row">
					<div class="col-md-1"></div>
					<div class="col-md-10">
						<div class="container">
							<div class="row">
								<div class="col-md-12">
									<div class="col-md-2">
									</div>
									<div class="col-md-3">
									<button id="pending" type="button" class="btn btn-primary">View pending reimbursements</button>
									</div>
									<div class="col-md-3">
									<button id="resolved" type="button" class="btn btn-primary">View resolved reimbursements</button>
									</div>
									<div class="col-md-3">
									</div>
								</div>
							</div>
						</div>
					</div>
					<div class="col-md-1"></div>
				</div>
			</div>
		</div>
	</div>
<br> <br>
	<div id="view">
		<div class="container">
			<table id="r_table">
			</table>
		</div>
	</div>

</body>
</html>