<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.0/jquery.min.js"></script>
  <link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login</title>
</head>
<body background="resources/img/bg.png">
<br>
<br>
<br>
<br>
<br>
<br>
<br>
<br>
<br>
<br>
<form action="login" method="POST" role="form">
	<div class="container">
	    <div class="row vertical-offset-100">
	    	<div class="col-md-4 col-md-offset-4">
	    		<div class="panel panel-default">
				  	<div class="panel-heading">
				    	<h1 class="panel-title" style="text-align: center;">Welcome, please log in to use application!</h1>
				 	</div>
				  	<div class="panel-body">
	                    <fieldset>
				    	  	<div class="form-group">
				    		    <input class="form-control" placeholder="Username" name="username" type="text">
				    		</div>
				    		<div class="form-group">
				    			<input class="form-control" placeholder="Password" name="password" type="password" value="">
				    		</div>
				    		<input class="btn btn-lg btn-success btn-block" type="submit" value="Login">
				    	</fieldset>
				    </div>
				    <% if(session.getAttribute("err") != null){ 
				    out.print("<h4 style=\"color:red; border: 2px solid; text-align: center;\">");
				    out.print(session.getAttribute("err"));
				    out.print("</h4");}%>
				</div>
			</div>
		</div>
	</div>
</form>
</body>
</html>