<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
  <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Choose Pie details</title>
</head>

<body>
<form method="post" action="PieChartGenerator">
<table>
	<tr>
		<td align="left">Course:</td>
		<td align="left">
			<select id="course" name="course">
				<option value="-1"></option>
				<c:forEach items="${courses}" var="course">
				<option value="${course.courseId}">${course.courseName}</option>
			</c:forEach>
			</select><br>
		</td>
	</tr>
	<tr>
		<td align="left">	Choose Semester:	</td>
		<td align="left">
			<select  id="semester" name="semester">
				<option value="-1"></option>
				<c:forEach items="${semesters}" var="semester">
				<option  value="${semester.semesterId}">${semester.semesterName}</option>
			</c:forEach>
			</select><br>
		</td>
	</tr>
<tr>
	<td align="left">Choose Subject:</td>
	<td align="left">
		<select  id="subject" name="subject" >
				<option value="-1"></option>
			
		</select>
	</td>
</tr>
</table>
<input type=submit value ="Generate Chart" >
<input type=reset  value="Reset"> 
</form>
  <br>
  <br>
 
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
                   $('<option>').val(this.subjectId).text(this.subjectName).appendTo($select);      
            });
		});
	});
});
</script>

</html>

