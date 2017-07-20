<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Submit Reimbursement</title>
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
				<li><a href="viewInfo">View your info</a></li>
				<li class="active"><a href="submitReimbursement">Submit reimbursement</a></li>
				<li><a href="viewReimbursement">View reimbursements</a></li>
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
					<div class="col-md-2"></div>
					<div class="col-md-8">
						<form method="POST" action="submitReimbursement">
							<div class="form-group">
								<input type="text" class="form-control"
									placeholder="Reimbursement Type" name="r_type" required>
							</div>
							<div class="form-group">
								<input type="text" class="form-control" placeholder="Amount"
									name="amount" required>
							</div>
							<div class="form-group">
								<label for="description">Description:</label>
								<textarea class="form-control" rows="5" id="description" style="resize: none;" name="description"></textarea>
							</div>
								 <label for="files" class="btn" style=" border:2px solid black;"><b>Select Receipt</b></label>
 								 <input id="files" style="visibility:hidden;" type="file" name="receipt">
							<div>
							
							<div class="row">
								<div class="col-xs-4"></div>
								<div class="col-xs-4">
									<div class="text-center">
										<button type="submit" class="btn btn-primary">Submit
											Reimbursement</button>
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