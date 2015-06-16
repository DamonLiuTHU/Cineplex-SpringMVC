<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="header.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Activate</title>
</head>
<body>

	<form method="post" action="activate">
		
		<%-- <input type="hidden" name="phone"value=<%=phone%>> --%>
	
		<input type="hidden" name="phone" value=<%=phone %>>
		
		<select name="bank">
			<option value="ccb">建设银行</option>
			<option value="icbc" selected="selected">工商银行</option>
			<option value="abc">农业银行</option>
			<option value="psbc">邮政银行</option>
		</select> <input type="text" name="account" placeholder="Account number">


		<input type="submit" class="mybutton">




	</form>

</body>
</html>