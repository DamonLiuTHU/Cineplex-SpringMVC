<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="header.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Register</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1">
<link href="./css/style.css" rel="stylesheet" type="text/css"
	media="all" />
<link href="./css/slider.css" rel="stylesheet" type="text/css"
	media="all" />
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

	<div class="wrap">

		<form class="contact-form" method="POST" action="register">
			<div class="price-details">
				Your Phone Number : <input type="text" class="username" name="phone">
			</div>
			<div class="price-details">
				<p>Password :</p>
				<p>
					<input type="password" class="username" name="password">
				</p>
			</div>
			<div class="price-details">
				<p>Again Your Password</p>
				<p>
					<input type="password" class="username">
				</p>
			</div>
			<input type="submit" class="mybutton">
		</form>



	</div>

</body>
</html>