<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>ICBC 工商银行网银付款</title>
</head>
<body>
	<label>账户号码： <%=session.getAttribute("account")%>
	</label>
	<form method="post" action="pay">


		<input type="hidden" name="phone"
			value=<% Cookie cookies[] = request.getCookies();
		for(Cookie c : cookies){
			if(c.getName().compareTo("phone")==0){
				out.println((c.getValue()));
				break;
			}
		} %>>
		<input type="hidden" name="account"
			value=<%=session.getAttribute("account")%>> 
		
		<label>请输入密码</label>	
		<input
			type="password" placeholder="input password" name="password">
			
		<input type="text" name="money" placeholder="how much money do you want to pay?">
		<input type="submit">

	</form>
</body>
</html>