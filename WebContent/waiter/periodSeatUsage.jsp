<%@page import="com.cineplex.tools.NumberFormatter"%>
<%@page import="java.util.List"%>
<%@page import="com.cineplex.model.tables.Hall"%>
<%@page import="com.cineplex.model.impl.HallModel"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="waiterheader.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>不同时段上座率统计</title>
<style>
td, tr, th {
	border-bottom: 1px solid #AEDEF2;
	border-left: 1px solid #AEDEF2;
	border-right: 1px solid #AEDEF2;
	border-top: 1px solid #AEDEF2;
}

th {
	width: 17%;
}
</style>
</head>
<body>
	<table class="mytable"  >
		<tr>
			<th>时段</th>
			<th>上座率</th>
		</tr>
		<%
			List<Hall> halls = HallModel.getHalls();
		%>
		<%
			for(Hall h : halls){
		%>
			<tr>
				<td><%=h.getStart()+"-"+h.getEnd() %>  </td>
				<td><% Integer left = Integer.parseInt(h.getLeft_tickets());
					   double rate = left.doubleValue()/51D;
					   rate = 1-rate;
					   out.print(NumberFormatter.percentage(rate));%></td>
			</tr>
		<%
			}
		%>
	</table>
</body>
</html>