<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ include file="header.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>回答问题</title>
</head>
<body>
	<c:forEach items="${list}" var="form">
		<div>
			<form action="postAnswer">
				<input type="hidden" name="qId" value="${form.id} " />
				<input type="hidden" name="phone" value=<%= session.getAttribute("phone") %>  />
				<label>问题：${form.description} </label> 
				<br/>
				A:${form.selection1}<input type="radio" name="selection" value="${form.selection1}" checked="checked"> 
				<br /> 
				B:${form.selection2}<input type="radio" name="selection" value="${form.selection2}"> 
				<br /> 
				C:${form.selection3}<input type="radio" name="selection" value="${form.selection3}">
				<br /> 
				<input type="submit" />
			</form>

		</div>
	</c:forEach>
</body>
</html>