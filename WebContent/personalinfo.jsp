<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="header.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>个人信息</title>
<style>
td, tr, th {
	border-bottom: 1px solid #AEDEF2;
	border-left: 1px solid #AEDEF2;
	border-right: 1px solid #AEDEF2;
	border-top: 1px solid #AEDEF2;
}
</style>
</head>
<body>
	<%-- 	<label> 性别 :${user.gender} </label>   --%>
	<!-- 	</br> -->
	<%-- 	<label> 年龄:${user.age}</label> --%>
	<!-- 	</br> -->
	<%-- 	<label> 居住城市:${user.city}</label> --%>

	<label>个人信息：</label>
	</br>

	<form action="updatepersonalinfo">
		<input type="hidden" name="phone"
			value="<%=session.getAttribute("phone")%>" />
		<table width="150px" class="mytable">
			<tr>
				<td width="40%">性别</td>
				<td width="60%"><select name="gender">
						<option value="${user.gender}" selected>${user.gender}</option>
						<option value="male">male</option>
						<option value="female">female</option>
				</select></td>
			</tr>
			<tr>
				<td>年龄</td>
				<td><input name='age' value='${user.age}' /></td>
			</tr>

			<tr>
				<td>城市</td>
				<td><input name='city' value='${user.city}' /></td>
			</tr>
			<tr>
				<td><input type="submit" /></td>
				<td><input type="reset" value="放弃修改" /></td>
			</tr>
		</table>


	</form>

	<h4>个人消费记录：</h4>
	<table class="mytable">

		<tr>
			<th>消费时间</th>
			<th>消费金额</th>
		</tr>


		<c:forEach items="${orderlist }" var="order">
			<tr>
				<td>${order.date}</td>
				<td>${order.cost}</td>

			</tr>
		</c:forEach>

	</table>
	<h4>充值记录：</h4>
	<table class="mytable">
		<tr> <th> 充值时间 </th>  <th> 充值金额 </th></tr>
		<c:forEach items="${rechargerecord }" var="recharge">
			<tr>
				<td>${recharge.date}</td>
				<td>${recharge.cost}</td>

			</tr>
		</c:forEach>
		
		
	</table>



</body>
</html>