<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="com.cineplex.controller.*"%>
<%@ page import="com.cineplex.model.forms.*"%>
<%@ include file="mheader.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="../css/style.css" rel="stylesheet" type="text/css"
	media="all" />
<link href="../css/slider.css" rel="stylesheet" type="text/css"
	media="all" />
<link href="../css/mystyle.css" rel="stylesheet" type="text/css"
	media="all" />
<title>检查计划</title>
<style>
th, td, tr {
	width: 20%;
	border-bottom: 1px solid #AEDEF2;
	border-left: 1px solid #AEDEF2;
	border-right: 1px solid #AEDEF2;
	border-top: 1px solid #AEDEF2;
}
</style>
</head>
<body>

	<form action="checkPlans" method="POST">
		<select name="hallId">
			<option value="1" selected>一号厅</option>
			<option value="2">二号厅</option>
			<option value="3">三号厅</option>
		</select>


		<select name="waiterId">
			<c:forEach items="${waiters}" var="waiter">
				<option value="${waiter}">${waiter}</option>
			</c:forEach>
		</select> <input type="submit">
	</form>

	<label> 服务员：<strong>${waiterId} </strong> 为 放映厅：<strong>${hallId}</strong>
		上传的放映计划为：
	</label>

	<%
		PlanForm pf = (PlanForm) request.getAttribute("planform");
		int length = 0;
		if (pf != null) {
			length = pf.getStart().length;
		}
	%>


	<br />
	
	<% if(length!=0){ %>
	
	<label>电影名称：</label>${planform.moviename}
	
	

	<form>

		<table class="mytable">
			<tr>
				<th>开始时间</th>
				<th>结束时间</th>
			</tr>
			<%
				for (int i = 0; i < length; i++) {
			%>

			<tr>
				<td><%=pf.getStart()[i]%></td>
				<td><%=pf.getEnd()[i]%></td>
			</tr>

			<%
				}
			%>
			
			<tr>
				<td><a href="accept?waiterId=${waiterId}&hallId=${hallId}">Accept</a></td>
				<td><a href="refuse?waiterId=${waiterId}&hallId=${hallId}">Refuse</a></td>
			</tr>


		</table>
		
		
	</form>
	
	<% } %>


</body>
</html>