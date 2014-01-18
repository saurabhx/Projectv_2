<%@page import="org.omg.PortableInterceptor.ForwardRequest"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login</title>
<link rel="stylesheet" type="text/css" href="css/login.css">
 
<script language="javascript">

function validateuser(form){ 
 	if(form.username.value == "admin" && form.password.value == "admin"){
		window.open("UiController.jsp?action=validateuser");
	 }
	 else { alert("Incorrect Password"); }
 }
</script>

</head>
<body>
<form name="login">
<div class="container">
<center><div style="font-size:xx-large"><u>Login Page</u></div></center>
<div class="wrapper">
<div class="mainleftdiv">
<div class="leftdiv">
UserName<div class="clear"></div>
Password<div class="clear"></div>
</div>

</div>



<div class="mainrightdiv">
<div class="rightdiv">
<input type="text" name="username"><div class="clear"></div>
<input type="password" name="password"><div class="clear"></div>
</div>
</div>

<div class="submitbuttons">
<div style="float:left; width:40%; text-align:right">
<input type="submit" name="submit" value="SUBMIT" onclick="validateuser(this.form)">
</div>
<div style="float:right; width:40%; text-align:left">
<input type="button" name="cancel" value="CANCEL">
</div>



</div>

</div>
</div>
</form>
</body>
</html>