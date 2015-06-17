<%@page import="com.cineplex.controller.UserController"%>
<%@page import="com.cineplex.controller.OrderController"%>
<%@page import="com.cineplex.model.tables.Order"%>
<%@page import="java.util.LinkedList"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="header.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>个人账户</title>

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



	<label> 您的会员识别码 ：<%=UserController.getID(phone) %></label>
 
	<br />


	<label> 账户余额 ：${user.balance}￥</label>
	
	<label>   积分：${user.credit}</label>
	<br/>
	
	<a href="recharge">充值</a>

	<br />
	<!-- 	<input type="button" value="Activate" onclick="activate"/> -->
	<% 	
		if(!UserController.isVIP(phone)){
			out.println("<a href=\"activate\" class=  > 激活会员资格 </a>");
		}else{
			out.println("您已激活会员资格  <img src=./images/vip.png />");
			out.println("<br/>");
			out.println("会员资格到期时间为："+UserController.getExpirationDate(phone));
		}
		
		
		
		%>
		
		
		<br/>
		<br/>
		
		<label> 我购买过的票 ： </label>
		<br/>
	
	
	<table class="mytable">
		<tr>
			<th>购票日期</th>
			<th>开始时间</th>
			<th>结束时间</th>
			<th>放映大厅</th>
			<th>电影名称</th>
			<th>座位号</th>
			<th></th>
		</tr>
		<%
		
			LinkedList<Order> myorders = OrderController.getOrders(phone);
				
		%>

		<c:forEach items="<%=myorders %>" var="order">
			<tr>
				<td>${order.date }  </td>
				<td>${order.starttime}</td>
				<td>${order.endtime}</td>
				<td><c:out value="${order.hallId}" /></td>
				<td><c:out value="${order.mname}" /></td>
				<td><c:out value="${order.seatId}" /></td>
			</tr>
		</c:forEach>



	</table>
	
	
	<label>查看我看过的电影的活动：</label>
	
	<br/>
	
	
	<table class="mytable">
		<tr>
			<th>电影名称</th>
			
			<th> </th>
			
		</tr>
		

		<c:forEach items="${movielist}" var="movie">
			<tr>
			
				<td>${movie.name}</td>
				<td><a href="getQuestion?movieId=${movie.id}" >查看活动</a></td>
				
				
			</tr>
		</c:forEach>



	</table>
	
	<label>评论看过的电影：</label>
	
	<br/>
	
	
	<table class="mytable">
		<tr>
			<th>电影名称</th>
			
			<th>去评论</th>
			
		</tr>
		

		<c:forEach items="${movielist}" var="mm">
			<tr>
				<td>${mm.name}</td>
				<td><a href="goToRank?movieId=${mm.id}" >评论</a></td>
			</tr>
		</c:forEach>



	</table>
	
	


</body>
</html>