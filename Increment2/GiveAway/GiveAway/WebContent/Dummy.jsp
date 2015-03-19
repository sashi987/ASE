<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
   <script src="http://code.jquery.com/jquery-1.8.3.js"></script>
  <script src="http://code.jquery.com/ui/1.9.2/jquery-ui.js"></script>
  <link rel="stylesheet" type="text/css" href="http://code.jquery.com/ui/1.9.2/themes/base/jquery-ui.css">
  <link rel="stylesheet" type="text/css" href="/css/result-light.css">
<link rel="stylesheet" type="text/css" href="css/registerstyle.css" ></link>
 <script>
 $(function() {
	    $( "#datepicker" ).datepicker();
	});
</script>	
<title>Insert title here</title>
</head>
<body>
<html:html>
<div class="main">		    	
<div class="login-head">					    
<h1>Give Away Signup</h1>					
</div>					
<div  class="wrap">						  
<div class="Regisration">						  	
<div class="Regisration-head">						    					
<h2><span></span>Register</h2>						 	
</div>
	<html:form action="/register" method="post">

		First Name
		<html:text property="firstName" size="50" /><br><br>
		Last Name
		<html:text property="lastName" size="50" /><br><br>
		Username
		<html:text property="userName" size="50" /><br><br>
		Email Address
		<html:text property="email" size="30" /><br><br>
		Phone No
		<html:text property="phone" size="15" /><br><br>
		
		Date: <input type="text" id="datepicker" size="15"><br><br>
		Address
		<html:text property="address" size="15" /><br><br>
		State
		<input type=Select property="address" size="15" /><br><br>
		Password
		<html:text property="password" size="30" /><br><br>
		Retype Password
		<html:text property="password" size="30" /><br><br>
		
		<html:submit>Submit</html:submit>
		


	</html:form>
</div></div></div>
</html:html>
</body>
</html>