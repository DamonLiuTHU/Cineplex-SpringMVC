  <%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="com.cineplex.model.tables.Movie"%>
<%@ page import="com.cineplex.controller.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>制定计划</title>
</head>
<body>

	<% ArrayList<Movie> movies= IndexController.getMovies(); 

	
	%>

	<form method="post" action="upload" id="planform">
		<select name="hallId">
			<option value='1' selected>1号厅</option>
			<option value='2'>2号厅</option>
			<option value='3'>3号厅</option>
		</select> <br /> <br /> <select name="movieId">

			<c:forEach items="<%=movies %>" var="movie">
				<option value="${movie.id}" selected>${movie.name}</option>
			</c:forEach>

		</select> 
		
		<input type="submit" >
		
		<br /> <br /> 
		
		
		<label>开始时间：</label><input type="time" name="start[0]">
		<label>结束时间：</label><input type="time" name="end[0]">
		


	</form>
	<br />
	<input type="button" class="mybutton" onclick="addPeriod()" value="addperiod" >
</body>
<script type="text/javascript">
	var counter=1;
	function addPeriod(){
		var form = document.getElementById("planform");
		var label1 = document.createElement("label");
		var input1 = document.createElement("input");
		var label2 = document.createElement("label");
		var input2 = document.createElement("input");
		label1.innerHTML="开始时间：";
		label2.innerHTML="结束时间：";
		input1.setAttribute("type", "time");
		input1.setAttribute("name", "start["+counter+"]");
		input2.setAttribute("type", "time");
		input2.setAttribute("name", "end["+counter+"]");
		counter = counter + 1;
		var newline = document.createElement("br");
		form.appendChild(newline);
		form.appendChild(label1);
		form.appendChild(input1);
		form.appendChild(label2);
		form.appendChild(input2);
			
		
	}


</script>
</html>