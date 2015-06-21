<!DOCTYPE HTML>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="header.jsp"%>
<%@ page import="com.cineplex.model.tables.Movie"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="com.cineplex.controller.*"%>
<head>
<title>Cineplex</title>
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
		<div class="header_top">
			<div class="logo">
				<a href="index.html"><img src="./images/logo.png" alt="" /></a>
			</div>
			<div class="header_top_right">
				<div class="cart">
					<p>
						<span>Cart</span>
					<div id="dd" class="wrapper-dropdown-2">
						(empty)
						<ul class="dropdown">
							<li>you have no items in your Shopping cart</li>
						</ul>
					</div>
					</p>
				</div>
				<div class="search_box">
					<form>
						<input type="text" value="Search" onfocus="this.value = '';"
							onblur="if (this.value == '') {this.value = 'Search';}"><input
							type="submit" value="">
					</form>
				</div>
				<div class="clear"></div>
			</div>
			<script type="text/javascript">
				function DropDown(el) {
					this.dd = el;
					this.initEvents();
				}
				DropDown.prototype = {
					initEvents : function() {
						var obj = this;

						obj.dd.on('click', function(event) {
							$(this).toggleClass('active');
							event.stopPropagation();
						});
					}
				}

				$(function() {

					var dd = new DropDown($('#dd'));

					$(document).click(function() {
						// all dropdowns
						$('.wrapper-dropdown-2').removeClass('active');
					});

				});
			</script>
			<div class="clear"></div>
		</div>
		<div class="header_bottom">
			<div class="header_bottom_left">
				<div class="categories">
					<ul>
						<h3>Categories</h3>
						<li><a href="#">All</a></li>
						<li><a href="#">Hindi</a></li>
						<li><a href="#">Telugu</a></li>
						<li><a href="#">English</a></li>
						<li><a href="#">Tamil</a></li>
						<li><a href="#">Malayalam</a></li>
						<li><a href="#">Kannada</a></li>
						<li><a href="#">Bengali</a></li>
						<li><a href="#">Assami</a></li>
						<li><a href="#">Kids</a></li>
						<li><a href="#">Animation</a></li>
						<li><a href="#">Games</a></li>
					</ul>
				</div>
			</div>
			<div class="header_bottom_right">
				<!------ Slider ------------>
				<div class="slider">
					<div class="slider-wrapper theme-default">
						<div id="slider" class="nivoSlider">
							<img src="./images/1.jpg" data-thumb="./images/1.jpg" alt="" />
							<img src="./images/2.jpg" data-thumb="./images/2.jpg" alt="" />
							<img src="./images/3.jpg" data-thumb="./images/3.jpg" alt="" />
							<img src="./images/4.jpg" data-thumb="./images/4.jpg" alt="" />
							<img src="./images/5.jpg" data-thumb="./images/5.jpg" alt="" />
						</div>
					</div>
				</div>
				<!------End Slider ------------>
			</div>
			<div class="clear"></div>
		</div>
	</div>
	</div>
	<!------------End Header ------------>
	<div class="main">
		<div class="wrap">
			<div class="content">
				<div class="content_top">
					<div class="heading">
						<h3>New Products</h3>
					</div>
				</div>

				<%! ArrayList<Movie> list = IndexController.getMovies(); %>

				<div class="section group">

					<%
  						 for(Movie movie : list){
  					%>


					<div class="grid_1_of_5 images_1_of_5">
						<a href="preview.jsp?movieId=<%= movie.getId() %>"><img src=<%=movie.getPoster() %> alt="" /></a>
						<h2>
							<a href="preview.jsp"><%= movie.getName() %></a>
						</h2>
						<div class="price-details">
							<div class="price-number">
								<p>
									<span class="rupees"> <label>￥ </label> <%= movie.getPrice() %></span>
								</p>
							</div>
							<div class="add-cart">
								<h4>
									<a href="order?movieId=<%= movie.getId()  %>">But Ticket</a>
								</h4>
							</div>
							<div class="clear"></div>
						</div>
					</div>

					<%
  						 }
					%>

				</div>
				<div class="content_bottom">
					<div class="heading">
						<h3>Feature Products</h3>
					</div>
				</div>
				<div class="section group">
					<div class="grid_1_of_5 images_1_of_5">
						<a href="preview.html"><img
							src="./images/beauty_and_the_beast.jpg" alt="" /></a>
						<h2>
							<a href="preview.html">Beauty and the beast</a>
						</h2>
						<div class="price-details">
							<div class="price-number">
								<p>
									<span class="rupees">$620.87</span>
								</p>
							</div>
							<div class="add-cart">
								<h4>
									<a href="preview.html">Add to Cart</a>
								</h4>
							</div>
							<div class="clear"></div>
						</div>

					</div>
					<div class="grid_1_of_5 images_1_of_5">
						<a href="preview.html"><img src="./images/Eclipse.jpg" alt="" /></a>
						<h2>
							<a href="preview.html">Eclipse</a>
						</h2>
						<div class="price-details">
							<div class="price-number">
								<p>
									<span class="rupees">$620.87</span>
								</p>
							</div>
							<div class="add-cart">
								<h4>
									<a href="preview.html">Add to Cart</a>
								</h4>
							</div>
							<div class="clear"></div>
						</div>

					</div>
					<div class="grid_1_of_5 images_1_of_5">
						<a href="preview.html"><img src="./images/Coraline.jpg" alt="" /></a>
						<h2>
							<a href="preview.html">Coraline</a>
						</h2>
						<div class="price-details">
							<div class="price-number">
								<p>
									<span class="rupees">$899.75</span>
								</p>
							</div>
							<div class="add-cart">
								<h4>
									<a href="preview.html">Add to Cart</a>
								</h4>
							</div>
							<div class="clear"></div>
						</div>

					</div>
					<div class="grid_1_of_5 images_1_of_5">
						<a href="preview.html"><img src="./images/Unstoppable.jpg"
							alt="" /></a>
						<h2>
							<a href="preview.html">Unstoppable</a>
						</h2>
						<div class="price-details">
							<div class="price-number">
								<p>
									<span class="rupees">$599.00</span>
								</p>
							</div>
							<div class="add-cart">
								<h4>
									<a href="preview.html">Add to Cart</a>
								</h4>
							</div>
							<div class="clear"></div>
						</div>
					</div>
					<div class="grid_1_of_5 images_1_of_5">
						<a href="preview.html"><img src="./images/Priest.jpg" alt="" /></a>
						<h2>
							<a href="preview.html">Priest 3D</a>
						</h2>
						<div class="price-details">
							<div class="price-number">
								<p>
									<span class="rupees">$679.87</span>
								</p>
							</div>
							<div class="add-cart">
								<h4>
									<a href="preview.html">Add to Cart</a>
								</h4>
							</div>
							<div class="clear"></div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="footer">
		<div class="wrap">
			<div class="section group">
				<div class="col_1_of_4 span_1_of_4">
					<h4>Information</h4>
					<ul>
						<li><a href="#">About Us</a></li>
						<li><a href="#">Customer Service</a></li>
						<li><a href="#">Advanced Search</a></li>
						<li><a href="#">Orders and Returns</a></li>
						<li><a href="contact.html">Contact Us</a></li>
					</ul>
				</div>
				<div class="col_1_of_4 span_1_of_4">
					<h4>Why buy from us</h4>
					<ul>
						<li><a href="#">About Us</a></li>
						<li><a href="#">Customer Service</a></li>
						<li><a href="#">Privacy Policy</a></li>
						<li><a href="contact.html">Site Map</a></li>
						<li><a href="#">Search Terms</a></li>
					</ul>
				</div>
				<div class="col_1_of_4 span_1_of_4">
					<h4>My account</h4>
					<ul>
						<li><a href="contact.html">Sign In</a></li>
						<li><a href="index.html">View Cart</a></li>
						<li><a href="#">My Wishlist</a></li>
						<li><a href="#">Track My Order</a></li>
						<li><a href="contact.html">Help</a></li>
					</ul>
				</div>
				<div class="col_1_of_4 span_1_of_4">
					<h4>Contact</h4>
					<ul>
						<li><span>+91-123-456789</span></li>
						<li><span>+00-123-000000</span></li>
					</ul>
					<div class="social-icons">
						<h4>Follow Us</h4>
						<ul>
							<li><a href="#" target="_blank"><img
									src="./images/facebook.png" alt="" /></a></li>
							<li><a href="#" target="_blank"><img
									src="./images/twitter.png" alt="" /></a></li>
							<li><a href="#" target="_blank"><img
									src="./images/skype.png" alt="" /> </a></li>
							<li><a href="#" target="_blank"> <img
									src="./images/linkedin.png" alt="" /></a></li>
							<div class="clear"></div>
						</ul>
					</div>
				</div>
			</div>
			<div class="copy_right">
				<p>
					Company Name © All rights Reseverd | Design by <a
						href="http://w3layouts.com">W3Layouts</a>
				</p>
			</div>
		</div>
	</div>
	<script type="text/javascript">
		$(document).ready(function() {
			$().UItoTop({
				easingType : 'easeOutQuart'
			});

		});
	</script>
	<a href="#" id="toTop"><span id="toTopHover"> </span></a>
</body>
</html>

