<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1">
<link href="../css/style.css" rel="stylesheet" type="text/css"
	media="all" />
<link href="../css/slider.css" rel="stylesheet" type="text/css"
	media="all" />
<link href="../css/mystyle.css" rel="stylesheet" type="text/css" media="all" />
<script type="text/javascript" src="./js/jquery-1.9.0.min.js"></script>
<script type="text/javascript" src="./js/move-top.js"></script>
<script type="text/javascript" src="./js/easing.js"></script>
<script type="text/javascript" src="./js/jquery.nivo.slider.js"></script>
<script type="text/javascript">
    $(window).load(function() {
        $('#slider').nivoSlider();
    });
    </script>
</head>
<body>
	<div class="header">
		<div class="headertop_desc">
			<div class="wrap">
				<div class="nav_list">
					<ul>
						<li><a href="waiterindex.jsp">主页</a></li>
						<li><a href="contact.html">Sitemap</a></li>
						<li><a href="contact.html">Contact</a></li>
						<% if(session.getAttribute("notice")!=null){ %>
						<li><a href="activate"><% out.println(session.getAttribute("notice")); %></a>  </li>
						<% } %>
						
					</ul>
				</div>
				<div class="account_desc">
					<ul>
					
						<%! String phone; %>

						<% 
							
							if(request != null){
							
								 
								  
								  if(session!=null){
									  out.println("<li><a >"+session.getAttribute("waiterId")+"</a></li>");
									  out.println("<li><a href=\"logout\"> log out </a></li>");
								  }else{
									  out.println("<li><a href=\"register.jsp\">Register</a></li><li><a href=\"login.jsp\">Login</a></li>");
								  }
							}
							
							%>


						<li><a href="preview.html">Delivery</a></li>
<!-- 						<li><a href="#">Checkout</a></li> -->
						<li><a href="myaccount.jsp">My Account</a></li>
					</ul>
				</div>
				<div class="clear"></div>
			</div>
		</div>
		<div class="wrap"></div>
	</div>
	<!------------End Header ------------>
</body>
</html>