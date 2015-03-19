<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
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
 <script language="Javascript">

 
 function ValidateAlpha(){

 	var keyCode = jswindow.event.keyCode;

 	if ((keyCode < 65 || keyCode > 90) && (keyCode < 97 || keyCode > 123) && keyCode != 32 && keyCode != 8))
 	{

 		window.event.returnValue = false;

 		alert("Only alphabets or space..please");
 	}
 }


 function ValidateAlphaNum(){

 	var keyCode = window.event.keyCode;

 	if ((keyCode < 47 || keyCode > 58) && (keyCode < 65 || keyCode > 90) && (keyCode < 97 || keyCode > 123) && keyCode != 8))){

 		window.event.returnValue = false;

 		alert("Only alphabets or number..please");
 	}
 }


 function onlyNumbers()
 {
 	var keyCode = window.event.keyCode;

 	if (keyCode < 47 || keyCode > 58){

 		window.event.returnValue = false;

 		alert("Only numbers..please");
 	}
 }

 function chkfrm()
 {
 	

         var x=document.add.mobile.value;
 	 
         if (x.length != 10){
                 alert("enter 10 numbers"); return false;
            }
         
 }

 function validateEmail(email) {
     var re = /^(([^<>()[\]\\.,;:\s@"]+(\.[^<>()[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
     alert("enter valid email");
 }

 function validate()
 {
 	var x=document.add.firstName.value;
 	var y=document.add.lastName.value;
 	var z=document.add.address.value;
 	var p=document.add.mobile.value;
 	var q=document.add.userName.value;
 	var r=document.add.email.value;
 	var s=document.add.password.value;
 	var t=document.add.password1.value;

 	if(x.length==0 || y.length==0 || z.length==0 || p.length==0 || q.length==0 || r.length==0 || s.length==0 || t.length==0)
 	{
 		alert("Dont leave any field empty....");
 		return false;
 	    }
 	    else
 	    {
 		return true;
 	    }
 		
 }


 function openPage() {
 	mywin=open("ad.html","_top");
 }

 $(function() {
	    $( "#datepicker" ).datepicker();
	});
 
 
</script>	
<title>Insert title here</title>
</head>
<body>	
	
		
<!--start-login-form-->
				
<div class="main">
			    	
<div class="login-head">
					    
<h1>Give Away Signup</h1>
					
</div>
					
<div  class="wrap">
						  
<div class="Regisration">
						  	

			<div class="Regisration-head">
						    	
				
				<h2><span></span>Register</h2>
						 	
 
			</div>
						  	

			<form  action="register" name ="add" onsubmit="validate()">
						  		

				<input type="text" name="firstName"  placeholder="firstName" onKeyPress=ValidateAlpha() >
						  		
				<input type="text" name="lastName"  onfocus="this.value = '';" onblur="if (this.value == '') {this.value = 'Last Name'; }"  onKeyPress=ValidateAlpha() >
						  		
				<input type="text" name="userName"  onfocus="this.value = '';" onblur="if (this.value == '') {this.value = 'User Name';}" onKeyPress=ValidateAlphaNum() >
	
				<input type="text" name="email"  onfocus="this.value = '';" onblur="if (this.value == '') {this.value = 'Email Address';}" onChange="return validateEmail()">

				<input type="text" name="address"  onfocus="this.value = '';" onblur="if (this.value == '') {this.value = 'Address';}" >
						  		
				<input type="text" name="mobile"  length="10" onfocus="this.value = '';" onblur="if (this.value == '') {this.value = 'Phone No'; }"  onChange="return chkfrm()" onKeyPress=onlyNumbers()>
							
				<select name="State">
	<option value="AL">Alabama</option>
	<option value="AK">Alaska</option>
	<option value="AZ">Arizona</option>
	<option value="AR">Arkansas</option>
	<option value="CA">California</option>
	<option value="CO">Colorado</option>
	<option value="CT">Connecticut</option>
	<option value="DE">Delaware</option>
	<option value="DC">District Of Columbia</option>
	<option value="FL">Florida</option>
	<option value="GA">Georgia</option>
	<option value="HI">Hawaii</option>
	<option value="ID">Idaho</option>
	<option value="IL">Illinois</option>
	<option value="IN">Indiana</option>
	<option value="IA">Iowa</option>
	<option value="KS">Kansas</option>
	<option value="KY">Kentucky</option>
	<option value="LA">Louisiana</option>
	<option value="ME">Maine</option>
	<option value="MD">Maryland</option>
	<option value="MA">Massachusetts</option>
	<option value="MI">Michigan</option>
	<option value="MN">Minnesota</option>
	<option value="MS">Mississippi</option>
	<option value="MO">Missouri</option>
	<option value="MT">Montana</option>
	<option value="NE">Nebraska</option>
	<option value="NV">Nevada</option>
	<option value="NH">New Hampshire</option>
	<option value="NJ">New Jersey</option>
	<option value="NM">New Mexico</option>
	<option value="NY">New York</option>
	<option value="NC">North Carolina</option>
	<option value="ND">North Dakota</option>
	<option value="OH">Ohio</option>
	<option value="OK">Oklahoma</option>
	<option value="OR">Oregon</option>
	<option value="PA">Pennsylvania</option>
	<option value="RI">Rhode Island</option>
	<option value="SC">South Carolina</option>
	<option value="SD">South Dakota</option>
	<option value="TN">Tennessee</option>
	<option value="TX">Texas</option>
	<option value="UT">Utah</option>
	<option value="VT">Vermont</option>
	<option value="VA">Virginia</option>
	<option value="WA">Washington</option>
	<option value="WV">West Virginia</option>
	<option value="WI">Wisconsin</option>
	<option value="WY">Wyoming</option>
</select>
				<input type="password" name="password" value="Password" onfocus="this.value = '';" onblur="if (this.value == '') {this.value = 'Password';}" >
								
				<input type="password" name="password1" value="Password" onfocus="this.value = '';" onblur="if (this.value == '') {this.value = ' Confirm Password';}" >
								 
					
											
			
					<div class="p-container">
								
					
						<label class="checkbox"><input type="checkbox" name="checkbox" checked><i></i>I agree to the Terms and Conditions</label>
								

						<div class ="clear"></div>
							

					</div>
					<div class="submit">

					<input type="submit" value="Sign Me Up" >
								

					</div>					
	</form>

		</div>
		
		
</div>
				
						
<div class ="copy-right">

</div>
			  
</div>
	
</body>

</html>