<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<html>

<head>

<title>Admin Master Data Entry</title>
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
<h1>Add Masters</h1>
<form method="POST" action="UiController?action=insertmaster">
	<ul>
        <li>
        	<label for="name">Course Name:</label>
            <input name="coursename" type="text" size="40" id="name" />
        </li>
        <li>
        	<label for="semestername">Semester Name:</label>
            <input type="text" size="40" id="name" />
        </li>
        <li>
        	<label for="subjectname">Subject Name:</label>
            <input type="text" size="40" id="name" />
        </li>       
	</ul>
    <p>
        <button type="submit" class="action">Submit</button>
        <button type="reset"  class="right">Reset</button>
    </p>
</form>
</article>
