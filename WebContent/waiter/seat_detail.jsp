<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>座位上座率统计</title>
<style>
*{margin:0;padding:0;}
body{font-size:14px;font-family:"Microsoft YaHei";}
ul,li{list-style:none;}

#tab{position:relative;}
#tab .tabList ul li{
	float:left;
	background:#fefefe;
	background:-moz-linear-gradient(top, #fefefe, #ededed);	
	background:-o-linear-gradient(left top,left bottom, from(#fefefe), to(#ededed));
	background:-webkit-gradient(linear,left top,left bottom, from(#fefefe), to(#ededed));
	border:1px solid #ccc;
	padding:5px 0;
	width:25%;
	text-align:center;
	position:relative;
	cursor:pointer;
}
#tab .tabCon{
	position:absolute;
	left:-1px;
	top:32px;
	border:1px solid #ccc;
 	border-top:none; 
	width:75.5%;
	height:450px;
}
#tab .tabCon div{
	padding:10px;
	position:absolute;
	opacity:0;
	filter:alpha(opacity=0);
}
#tab .tabList li.cur{
	border-bottom:none;
	background:#fff;
}
#tab .tabCon div.cur{
	opacity:1;
	filter:alpha(opacity=100);
}
</style>
</head>
<body>
<%@ include file="waiterheader.jsp" %>
<!-- 代码 begin -->
<div id="tab" style="margin-left:1%;margin-top:20px;width:98%">
  <div class="tabList">
	<ul>
		<li class="cur">整体座位使用率</li>
		<li>分厅座位使用率</li>
		<li>分时段座位使用率</li>
	</ul>
  </div>
  <div class="tabCon">
	<div class="cur">断桥残雪、千百度、幻听、想象之中</div>
	<div>红尘客栈、牛仔很忙、给我一首歌的时间、听妈妈的话</div>
	<div>被风吹过的夏天、江南、一千年以后</div>
  </div>
</div>

<script>
window.onload = function() {
    var oDiv = document.getElementById("tab");
    var oLi = oDiv.getElementsByTagName("div")[0].getElementsByTagName("li");
    var aCon = oDiv.getElementsByTagName("div")[1].getElementsByTagName("div");
    var timer = null;
    for (var i = 0; i < oLi.length; i++) {
        oLi[i].index = i;
        oLi[i].onmouseover = function() {
            show(this.index);
        }
    }
    function show(a) {
        index = a;
        var alpha = 0;
        for (var j = 0; j < oLi.length; j++) {
            oLi[j].className = "";
            aCon[j].className = "";
            aCon[j].style.opacity = 0;
            aCon[j].style.filter = "alpha(opacity=0)";
        }
        oLi[index].className = "cur";
        clearInterval(timer);
        timer = setInterval(function() {
            alpha += 2;
            alpha > 100 && (alpha = 100);
            aCon[index].style.opacity = alpha / 100;
            aCon[index].style.filter = "alpha(opacity=" + alpha + ")";
            alpha == 100 && clearInterval(timer);
        },
        5)
    }
}
</script>
<!-- 代码 en -->

</body>
</html>