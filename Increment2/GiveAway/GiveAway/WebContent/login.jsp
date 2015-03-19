<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
   
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="css/loginstyle.css" ></link>	
	<title>Give Away Login</title>
	<script></script>
</head>
<body>

<div class="main">
	<div class="login-head">
		<h1>Login to Give Away</h1>
	</div>
	<div  class="wrap">
		<div class="Login">
			<div class="Login-head"> 	
				<h3>LOGIN</h3>
			</div>
			
<form action="login" method="post">
     <div class="ticker">
			Username
			<input type="text" >
		         <span> </span>
				<div class="clear"> </div>		  		
		</div>
		<div>
			Password
			<input type="text"  >
			<span> </span>
				<div class="clear"> </div>
		</div>
        <div class="submit-button">
			<input type="submit" value="Login">
			<html:submit >NOT A USER</html:submit>
		</div>
		<div class="clear"> </div>
		


</form>			
</div>
</div>			
</div>

</body>
</html>

