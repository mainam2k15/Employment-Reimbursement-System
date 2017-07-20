<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="com.revature.pojo.User"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Personal Information</title>
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
				<li><a href="homepage">Home</a></li>
				<li class="active"><a href="viewInfo">View your info</a></li>
				<li><a href="submitReimbursement">Submit reimbursement</a></li>
				<li><a href="viewReimbursement">View reimbursements</a></li>
			</ul>
			<ul class="nav navbar-nav navbar-right">
				<li><a href="logout"><span
						class="glyphicon glyphicon-log-in"></span>Logout</a></li>
			</ul>
		</div>
	</nav>

	<%
		if (session.getAttribute("updateErr") != null) {
			out.print("<div class=\"alert alert-danger\" role=\"alert\">");
			out.print("<strong> <span class=\"glyphicon glyphicon-alert\"> ERROR: </strong>  "
					+ session.getAttribute("updateErr"));
			out.print("</div");
		}
	%>

	<div class="container">
		<div class="row">
			<div class="col-md-12">
				<div class="row">
					<div class="col-md-4"></div>
					<div class="col-md-4"
						style="border: 2px black solid; border-radius: 25px; text-align: center;">
						<%
							if ((User) session.getAttribute("user") != null) {
						%>
						<h1>
							<%=((User) session.getAttribute("user")).getF_name().toUpperCase()%>
							<%=((User) session.getAttribute("user")).getL_name().toUpperCase()%>
						</h1>
						<p>
							<b>EMAIL: </b>
							<%=((User) session.getAttribute("user")).getEmail()%>
						</p>
						<p>
							<b>EMPLOYEE ID: </b>
							<%=((User) session.getAttribute("user")).getId()%>
						</p>
						<%
							}
						%>
					</div>
					<div class="col-md-4"></div>
				</div>
			</div>
		</div>
	</div>

	<br>
	<br>

	<div class="container">
		<div class="row">
			<div class="col-md-12">
				<div class="row">
					<div class="col-md-2"></div>
					<div class="col-md-8">
						<form method="POST" action="viewInfo">
							<div class="form-group">
								<input type="text" class="form-control"
									aria-describedby="emailHelp" placeholder="Email" name="email">
							</div>
							<div class="form-group">
								<input type="text" class="form-control" placeholder="First Name"
									name="fname">
							</div>
							<div class="form-group">
								<input type="text" class="form-control" placeholder="Last Name"
									name="lname">
							</div>
							<div class="form-group">
								<input type="password" class="form-control"
									placeholder="Password" name="password">
							</div>
							<div class="row">
								<div class="col-xs-4"></div>
								<div class="col-xs-4">
									<div class="text-center">
										<button type="submit" class="btn btn-primary">Update
											Information</button>
									</div>
								</div>
								<div class="col-xs-4"></div>
							</div>
						</form>

					</div>
					<div class="col-md-2"></div>
				</div>
			</div>
		</div>
	</div>

</body>
</html>