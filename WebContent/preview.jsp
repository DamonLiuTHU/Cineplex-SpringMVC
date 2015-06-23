<%@page import="com.cineplex.controller.UserController"%>
<%@page import="com.cineplex.model.impl.CommentModel"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ page import="com.cineplex.model.tables.Movie,com.cineplex.model.impl.*,java.util.*,com.cineplex.model.tables.*"
 %>

<%! String movieId ;
	Movie movie;
	List<Comment> commentlist;
%>
<% movieId = (String)request.getParameter("movieId");  
   movie = MovieModel.getMovie(movieId);
   commentlist = CommentModel.getComments(movieId);
%>

<!DOCTYPE HTML>
<head>
	<title>Film Detail</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
	<link href="./css/style.css" rel="stylesheet" type="text/css" media="all"/>
	<link rel="stylesheet" href="./css/comment.css" media="all"/> 
	<script type="text/javascript" src="./js/jquery-1.9.0.min.js"></script> 
	<script type="text/javascript" src="./js/move-top.js"></script>
	<script type="text/javascript" src="./js/easing.js">  </script>

</head>
<body>
	<!-- <div class="header">
		 <div class="headertop_desc">
			<div class="wrap">
				<div class="nav_list">
					<ul>
						<li><a href="index.html">Home</a></li>
						<li><a href="contact.html">Sitemap</a></li>
						<li><a href="contact.html">Contact</a></li>
					</ul>
				</div>
					<div class="account_desc">
						<ul>
							<li><a href="contact.html">Register</a></li>
							<li><a href="contact.html">Login</a></li>
							<li><a href="preview.html">Delivery</a></li>
							<li><a href="#">Checkout</a></li>
							<li><a href="#">My Account</a></li>
						</ul>
					</div>
				<div class="clear"></div>
			</div>
	  	</div>
  	  		<div class="wrap">
				<div class="header_top">
					<div class="logo">
						<a href="index.html"><img src="./images/logo.png" alt="" /></a>
					</div>
						<div class="header_top_right">
						  <div class="cart">
						  	   <p><span>Cart</span><div id="dd" class="wrapper-dropdown-2"> (empty)
						  	   	<ul class="dropdown">
										<li>you have no items in your Shopping cart</li>
								</ul></div></p>
						  </div>
							  <div class="search_box">
					     		<form>
					     			<input type="text" value="Search" onfocus="this.value = '';" onblur="if (this.value == '') {this.value = 'Search';}"><input type="submit" value="">
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
					
										obj.dd.on('click', function(event){
											$(this).toggleClass('active');
											event.stopPropagation();
										});	
									}
								}
					
								$(function() {
					
									var dd = new DropDown( $('#dd') );
					
									$(document).click(function() {
										// all dropdowns
										$('.wrapper-dropdown-2').removeClass('active');
									});
					
								});
					    </script>
			   <div class="clear"></div>
  		    </div>     				
   		</div>
   </div> -->
   
   <%@ include file="header.jsp" %>
   
   <div class="main">
   	 <div class="wrap">
   	 	<div class="content_top">
    		<div class="back-links">
    		<p><a href="index.html">Home</a> &gt;&gt;&gt;&gt; <a href="#" class="active">English</a></p>
    	    </div>
    		<div class="clear"></div>
    	</div>
   	 	<div class="section group">
				<div class="cont-desc span_1_of_2">
				  <div class="product-details">				
					<div class="grid images_3_of_2">
						<img src=<%=movie.getPoster()%> alt="movie picture here" />
				  </div>
				<div class="desc span_3_of_2">
					<h2><%=movie.getName() %></h2>
					<p><%=movie.getShort_description() %></p>					
					<div class="price">
						<p>Price: <span>$<%= movie.getPrice() %></span></p>
					</div>
					<div class="available">
						<ul>
						  <li><span>Model:</span> &nbsp;<%=movie.getType() %></li>
						  <li><span>Shipping Weight:</span>&nbsp; 5lbs</li>
						  <li><span>Units in Stock:</span>&nbsp;<%=MovieModel.getLeftTicketsForMovie(movieId)%></li>
					    </ul>
					</div>
				<div class="share-desc">
					<div class="share">
						<!-- <p>Number of units :</p><input class="text_box" type="text"> -->				
					</div>
					<div class="button"><span><a href="order?movieId=<%= movie.getId()%>">But Ticket</a></span></div>					
					<div class="clear"></div>
				</div>
				 <div class="wish-list">
				 	<ul>
				 		<li class="wish"><a href="#">Add to Wishlist</a></li>
				 	    <li class="compare"><a href="#">Add to Compare</a></li>
				 	</ul>
				 </div>
			</div>
			<div class="clear"></div>
		  </div>
		<div class="product_desc">	
			 <h2>Details :</h2>
			   <p class="movie_detail_description"><%=movie.getLong_description() %></p>
	    </div>
	    
	    <!-- average rank and rank count div  -->
	    <div class="" data-target=""  style="display: block	">
		    <div class="avg_score_tab">
		        <div class="avg_score_tab_inner">
		            <span>平均得分：</span>
		            <strong><%=MovieModel.getAvgScore(movieId) %></strong>
		            <span>分</span>
		        </div>
		        <div class="total_rank_times">共<strong><%=MovieModel.getRankCount(movieId) %></strong>次打分</div>
		    	<div class="clear"></div>
		    </div>
		</div>
		
		<!-- the latest comments div -->
		<div class="commentlist">
			<h3>最新评论</h3>
			
			<% for(Comment commentItem : commentlist){%>
				<div class="product_desc comment">
					<span>评分：<%=commentItem.getScore() %></span>	<span class="comment_userId"> 用户：<%=commentItem.getUserId()%></span>
					<p class="comment_inner"> <%=commentItem.getComment() %> </p>
				</div>
			<% } %>
		</div>
   </div>
				<div class="rightsidebar span_3_of_1 sidebar">
					<h2>Specials</h2>
					 	<div class="special_movies">
					 	   <div class="movie_poster">
					 		  <a href="preview.html"><img src="./images/end-game.jpg" alt="" /></a>
					 		 </div>
					 		   <div class="movie_desc">
							    <h3><a href="preview.html">End Game</a></h3>
								   <p><span>$620.87</span> &nbsp; $500.35</p>
								     <span><a href="#">Add to Cart</a></span>
								 </div>
								<div class="clear"></div>
					 		</div>	
					 		<div class="special_movies">
					 	   <div class="movie_poster">
					 		  <a href="preview.html"><img src="./images/Coraline.jpg" alt="" /></a>
					 		 </div>
					 		   <div class="movie_desc">
							    <h3><a href="preview.html">Coraline</a></h3>
								   <p><span>$620.87</span> &nbsp; $500.35</p>
								     <span><a href="#">Add to Cart</a></span>
								 </div>
								<div class="clear"></div>
					 		</div>	
					 		<div class="special_movies">
					 	   <div class="movie_poster">
					 		  <a href="preview.html"><img src="./images/Eclipse.jpg" alt="" /></a>
					 		 </div>
					 		   <div class="movie_desc">
							    <h3><a href="preview.html">Eclipse</a></h3>
								   <p><span>$620.87</span> &nbsp; $500.35</p>
								     <span><a href="#">Add to Cart</a></span>
								 </div>
								<div class="clear"></div>
					 		</div>	
					 		<div class="special_movies">
					 	   <div class="movie_poster">
					 		  <a href="preview.html"><img src="./images/Priest.jpg" alt="" /></a>
					 		 </div>
					 		   <div class="movie_desc">
							    <h3><a href="preview.html">Priest 3D</a></h3>
								   <p><span>$620.87</span> &nbsp; $500.35</p>
								     <span><a href="#">Add to Cart</a></span>
								 </div>
								<div class="clear"></div>
					 		</div>	
					 		<div class="special_movies">
					 	    <div class="movie_poster">
					 		  <a href="preview.html"><img src="./images/Sorority_Wars.jpg" alt="" /></a>
					 		 </div>
					 		   <div class="movie_desc">
							    <h3><a href="preview.html">Sorority Wars</a></h3>
								   <p><span>$620.87</span> &nbsp; $500.35</p>
								     <span><a href="#">Add to Cart</a></span>
								 </div>
								<div class="clear"></div>
					 		</div>			 
 					   </div>
 		 		 </div>
   	 		</div>
        </div>
        
<%@ include file="footer.jsp" %>
</body>
</html>

