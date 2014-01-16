
   <%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
   <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<html>

<head>

<title>Pie Chart</title>
<link rel="stylesheet" href="css/style.css" />
<link href='http://fonts.googleapis.com/css?family=Engagement' rel='stylesheet' type='text/css'>
<!--[if IE]>
  <script src="http://html5shiv.googlecode.com/svn/trunk/html5.js"></script>
<![endif]-->
    <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.4.2/jquery.min.js" type="text/javascript" charset="utf-8"></script>
    <script src="js/jquery.uniform.min.js" type="text/javascript" charset="utf-8"></script>
    <script type="text/javascript" charset="utf-8">
      $(function(){
        $("input:checkbox, input:radio, input:file, select").uniform();
      });
    </script>
</head>
<body>
<article>
<h1>Select Choices to Generate Pie-Chart</h1>
<form>
	<ul>
		  <li>
            <label for="car">Choose Course</label>
            <select id="car">
                <option>Computer Science</option>
                <option>Mechanical</option>
                <option>Civil</option>
                <option>Electronics</option>
                <option>IT</option>
            </select>
        </li>
        <li>
            <label for="car">Choose Semester</label>
            <select id="car">
                <option>First</option>
                <option>Second</option>
                <option>Third</option>
                <option>Fourth</option>
                <option>Fifth</option>
				<option>Sixth</option>
				<option>Seventh</option>
				<option>Eighth</option>
            </select>
        </li>
        <li>
            <label for="car">Choose Subject</label>
            <select id="car">
                <option>DAA</option>
                <option>Artificial Intelligence</option>
                <option>OS</option>
                <option>Graph Theory</option>
                <option>DBMS</option>
            </select>
        </li>
       
	</ul>
    <p>
        <button type="submit" class="action">Generate Pie Chart</button>
        <button type="reset" class="right">Reset</button>
    </p>
</form>
</article>
