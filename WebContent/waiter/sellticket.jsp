<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ include file="./waiterheader.jsp" %>
<%@ page import="com.cineplex.model.tables.Movie"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="com.cineplex.controller.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>服务员售票</title>
</head>
<body>
<%! ArrayList<Movie> list = IndexController.getMovies(); %>

				<div class="section group">

					<%
  						 for(Movie movie : list){
  					%>


					<div class="grid_1_of_5 images_1_of_5">
						<a href="preview.jsp"><img src=<%=movie.getPoster() %>  /></a>
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

</body>
</html>