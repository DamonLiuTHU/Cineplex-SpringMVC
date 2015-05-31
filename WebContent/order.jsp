<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="com.cineplex.controller.*"%>
<%@ page import="com.cineplex.model.tables.Hall" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="header.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<style>
        td,tr,th{
        
        	border-bottom:1px solid #AEDEF2;
    		border-left:1px solid #AEDEF2;
        	border-right: 1px solid #AEDEF2;
        	border-top:1px solid #AEDEF2;
        }
        
        th{
        	width:20%;
        }
</style>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>购票</title>

</head>


<body>


	<br />

	<div>
		<table class="mytable">
				<tr>
					<th>开始时间</th>
					<th>结束时间</th>
					<th>放映大厅</th>
					<th>剩余票数</th>
					<th>  </th>
				</tr>
			<c:forEach items="${list}" var="Hall">
				<tr>
					<td>${Hall.start}</td>
					<td>${Hall.end} </td>
					<td><c:out value="${Hall.hallId}" /></td>
					<td><c:out value="${Hall.left_tickets}" /></td>
					<td><a href="./chooseSeat.jsp?phone=<%=phone%>&Id=${Hall.ordernumber}">  Buy  </a> </td>
				</tr>
			</c:forEach>
		</table>
	</div>
</body>
</html>