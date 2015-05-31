<%@page import="com.cineplex.controller.OrderController"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="waiterheader.jsp"%>
<%@ page import="java.util.ArrayList"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>选择座位</title>
<style>
</style>
</head>
<body>
	<%
		String Id = request.getParameter("Id");
		String phone = request.getParameter("phone");
		ArrayList<String> unavailableseats = OrderController.getUnavailableSeats(Id);
	%>


	<label>您的场次： <%=Id%>
	</label>
	
	<br />
	<div id="seats">已经选择的座位：</div>
	<br />
	<br />


	

	<form action="buyTicket" id="orderform">
		<input type="hidden" name="waiterId" value="<%=session.getAttribute("waiterId") %>"> 
		<input type="hidden" name="Id" value="<%=Id %>"> 
		<input type="hidden" name="seatlist" id="seatlistinfrom" value=""> 
		<input type="submit" value="购买" class="mybuttonleft">
	</form> 

	<%-- 	<a href="buyTicket?phone=<%=phone%>&Id=<%=Id%>" class="myseat"> 购买 </a> --%>

	<div class="seatarea">屏幕</div>
	<br />
	<br />
	<br />

	<%
		for (int i = 0; i < 6; i++) {
	%>

	<div class="seatarea">
		<%
			for (int j = 0; j < 6 + i; j++) {
				int seatId = i * (i + 11) / 2 + j + 1;
				if(unavailableseats.contains(seatId+"")){
		%>
					<a class="myseat" style="background-color: #FF0000" ><%=seatId%></a>
		<% 
				}else{
		%>

					<a onclick="addSeat(<%=seatId%>)" class="myseat" id="<%=seatId%>"><%=seatId%></a>

		<%
					
				}
				
			}
		%>

	</div>
	<br />
	<br />
	<br />
	<div class="clear"></div>

	<%
		}
	%>

</body>
<script>

	var seatlist = new Array(); 

	function removeSeat(seat){
		var seats = document.getElementById("seats");
		var selectseat = document.getElementById("selected"+seat);
		seats.removeChild(selectseat)
		
		var oldseat = document.getElementById(seat);
		//oldseat.onclick = true;
		oldseat.setAttribute("onclick","addSeat("+seat+")") 
		oldseat.removeAttribute("style");
		//seatlist.replace(seat,"");

		for(var i = 0;i<seatlist.length;i++){
			if(seatlist[i]==seat){
				seatlist.splice(i,1);
			}

		}
		//alert(seatlist);
		
	}

	function addSeat(seat){
		var seats = document.getElementById("seats");
		/* alert(seat); */
		var new_seat = document.createElement("a");
		
		var clickedseat = document.getElementById(seat);
		clickedseat.removeAttribute("onclick")
		clickedseat.style.background="#000000";
		new_seat.setAttribute("id","selected"+seat)
		new_seat.setAttribute("value", seat);
		new_seat.innerHTML = seat;
		new_seat.setAttribute("class","myseat");
		new_seat.setAttribute("onclick","removeSeat("+seat+")");
		seats.appendChild(new_seat);
		/* seatlist.push(seat); */
		
		seatlist.push(seat);
		var jsonvalue = eval(seatlist);
		var seatlistinform = document.getElementById("seatlistinform");
		seatlistinfrom.setAttribute("value",jsonvalue);
		
	}
	function submit(phone,Id){
		
		    
		   	var json = eval(seatlist);
		   	url="/Cineplex-SpringMVC/buyTicket?seatlist="+json+"&phone="+phone+"&Id="+Id;
		    data={};

		    
			initRequest()
			send(url, data);

	}

	function initRequest()
	{
	    var request = false;
	    if(window.XMLHttpRequest) {         //FireFox
	    request = new XMLHttpRequest();
	    if (request.overrideMimeType) {
	      request.overrideMimeType('text/xml');
	    }
	  }
	  else if (window.ActiveXObject) {    //IE
	    try {
	      request = new ActiveXObject("Msxml2.XMLHTTP");
	    } catch (e) {
	      try {
	        request = new ActiveXObject("Microsoft.XMLHTTP");
	      } catch (e) {}
	    }
	  }
	  if (!request) {
	    window.alert("Create request error!");
	    return false;
	  }
	  return request;
	}

	var http_request;
	function send(sendUrl,sendData)
	{
	  http_request = initRequest();
	 // sendUrl += "&random="+Math.random();   //在URL后添加这个，可以保证不被缓存
	  http_request.onreadystatechange = ajax_call_back;//指定请求返回时的回调函数
	    
	  //get
	  //http_request.open("GET", sendUrl,true);
	 // http_request.send(sendData);
	  
	  //post
	  http_request.open("POST", sendUrl, true);
	  http_request.setRequestHeader("Content-Type","application/json");
	  http_request.send(sendData);
	}

	function ajax_call_back()
	{
	    //readyState共有5中状态，0未初始化，1已初始化，2发送请求，3开始接收结果，4接收结果完毕。
	    //status服务器响应状态码
	  if (http_request.readyState == 4) {
	    if (http_request.status == 200) {
	      var str = http_request.responseText;
	    }
	    else if(http_request.status == 404){
	      //alert("请求资源不存在！");
	    }
	    else {
	      //alert("Ajax请求失败，错误状态为："+http_request.status);
	    }
	  }
	}


</script>

</html>