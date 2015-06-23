<%@page import="com.cineplex.model.impl.OrderModel"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ include file="mheader.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>财务统计</title>
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

<table class="mytable"> 
	<tr> <th>月份</th>  <th> 营业额</th>  <th>电影类型分布</th></tr>
	<% for(int i = 1 ; i<=12 ; i++){ %>
		<tr> <td><%=i %> </td>  <td><%=OrderModel.getFinancialForMonth(i) %> </td> <td><a href="getTypeDetailForMonth?month=<%=i%>">go</a></td> </tr>
	<% } %>
</table>


<%@ include file="../footer.jsp" %>
</body>
</html>