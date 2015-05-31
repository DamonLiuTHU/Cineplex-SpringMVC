<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@ include file="waiterheader.jsp"%>
  <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>结算问题</title>
<style type="text/css">
	td, tr, th {
	border-bottom: 1px solid #AEDEF2;
	border-left: 1px solid #AEDEF2;
	border-right: 1px solid #AEDEF2;
	border-top: 1px solid #AEDEF2;
}
	
</style>
</head>
<body>
	<table class="mytable">
		<c:forEach items="${myqlist}" var="q">
			<tr>
				<td>${q.description}  </td>
				<td><a href="closequestion?id=${q.id}">关闭问题</a></td>
			
			</tr>
		</c:forEach>
	</table>
</body>
</html>