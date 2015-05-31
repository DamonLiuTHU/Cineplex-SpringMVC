<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="./waiterheader.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>发布问题</title>
</head>
<body>
	<form action="postQuestionnaire" method="POST">
		
		<label>请选择发布问题对应的电影</label>
		<select name="movieId">
			<c:forEach items="${movielist}" var="movie">
				<option value="${movie.id}" > ${movie.name}  </option>
			</c:forEach>
		</select>
		<br/>
		<input name="description" placeholder="问题描述"/>
		<br/>
		<input name="selection1" placeholder="选项1"/>
		<br/>
		<input name="selection2" placeholder="选项2"/>
		<br/>
		<input name="selection3" placeholder="选项3"/>
		<br/>
		<label>请选择截止日期</label><input type="date" name="duedate">
		<br/>
		<label>请选择积分：</label> <input type="number" name="credit" min="1" max="10" value="5"/>
		<br/>
		<input type="submit" />
	</form>
</body>
<script type="text/javascript">
	function getTime(){
		var time = new Date();
		return time;
	}

</script>
</html>