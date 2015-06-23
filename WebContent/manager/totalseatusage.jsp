
<%@page import="com.cineplex.tools.NumberFormatter"%>
<%@page import="java.text.NumberFormat"%>
<div class="center_div">
<%@page import="com.cineplex.controller.SeatController,java.util.*"%>
	<%
		for (int i = 0; i < 6; i++) {
	%>
	<div class="seatarea">
		<%
			for (int j = 0; j < 6 + i; j++) {
				int seatId = i * (i + 11) / 2 + j + 1;
				double rate = SeatController.getTotalUsage(seatId);
		%>
			<a class="myseat_small"><%=NumberFormatter.percentage(rate)%></a>
		<%
			}
		%>
	</div>
	<br />
	<div class="clear"></div>
	<%
		}
	%>
</div>