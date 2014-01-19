<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<link type="text/css"
	href="css/ui-lightness/jquery-ui-1.8.18.custom.css" rel="stylesheet" />
<script type="text/javascript" src="js/jquery-1.7.1.min.js"></script>
<script type="text/javascript" src="js/jquery-ui-1.8.18.custom.min.js"></script>

<title>Add new student data</title>

</head>
<body>

<form method="POST" action="">
	<table id="form">
		<tr>
			<td>Student Id:</td>
			<td><input type="text" readonly="readonly" name="userid"
								value="${student.studentId}" /></td>
		</tr>
		<tr>
			<td>Name:</td>
			<td><input type="text"	name="name" value="${student.name}" /> </td>
		</tr>
		<tr>
			<td>Course:</td>
			<td>
				<select id="course" name="courseName">
					<option value="-1"></option>
					<c:forEach items="${courses}" var="course">
						<option value="${course.courseId}">${course.courseName}</option>
					</c:forEach>
				</select>
			</td>
		</tr>
		<tr>
			<td>Semester:</td>
			<td>				
				<select id="semester" name="semesterName">
					<option value="-1"></option>
					<c:forEach items="${semesters}" var="semester">
						<option value="${semester.semesterId}">${semester.semesterName}</option>
					</c:forEach>
				</select>
			</td>
		</tr>
		<tr>
			<td></td>
			<td>Subject:</td>
			<td>Score:</td>			
		</tr>
		
		
		
	</table>
	<input type="submit" value="Add Student" /> <input type= "reset" value="Reset" />
</form>

</body>

<script type="text/javascript"
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.7.2/jquery.min.js"></script>
<script>
$(document).ready( function() {
	$('#semester').change(function(){
		var semId = $('#semester').val();
		var courseId = $('#course').val();
		var url= "UiController?action=populateSubjects&semester="+semId +"&course="+courseId;
		$.get(url,function(responseJson) {   
            var $select = $('#subject');                           
            $select.find('option').remove();                          
            $.each(responseJson, function(key, value) { 
            	$('#form tr:last').after("<tr> <td></td> <td>"+this.subjectName+'</td><td><input type="numeric" name="marks" /></td></tr>');    
            });
		});
	});
});
</script>
</html>