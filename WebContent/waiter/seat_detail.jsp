<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>座位上座率统计</title>
<style>

</style>
<link href="../css/mystyle.css" rel="stylesheet" type="text/css"
	media="all" />
</head>
<body>
<%@ include file="waiterheader.jsp" %>
<!-- 代码 begin -->
<div id="tab" style="margin-left:1%;margin-top:20px;width:98%">
  <div class="tabList">
	<ul>
		<li id="tab_1" class="cur">整体座位使用率</li>
		<li id="tab_2">分厅座位使用率</li>
		<li id="tab_3">分时段座位使用率</li>
	</ul>
  </div>
  <div class="tabCon">
	<div class="cur" id="div_1"><%@ include file="totalseatusage.jsp" %></div>
	<div id="div_2">红尘客栈、牛仔很忙、给我一首歌的时间、听妈妈的话</div>
	<div id="div_3">被风吹过的夏天、江南、一千年以后</div>
  </div>
</div>

<script>
window.onload = function() {
    var timer = null;
    
    var content_div_1 = document.getElementById("div_1");
    var content_div_2 = document.getElementById("div_2");
    var content_div_3 = document.getElementById("div_3");
    var content_array = new Array(content_div_1,content_div_2,content_div_3);
    
    var tab_1 = document.getElementById("tab_1");
    var tab_2 = document.getElementById("tab_2");
    var tab_3 = document.getElementById("tab_3");
    var tab_array = new Array(tab_1,tab_2,tab_3);
    
    
    for (var i = 0; i < tab_array.length; i++) {
        tab_array[i].index = i; 
        tab_array[i].onmouseover = function() {
            show(this.index);
        };
    }
    function show(a) {
        index = a;
        var alpha = 0;
        for (var j = 0; j < tab_array.length; j++) {
            tab_array[j].className = "";
            content_array[j].className = "";
            content_array[j].style.opacity = 0;
            content_array[j].style.filter = "alpha(opacity=0)";
        }
        tab_array[index].className = "cur";
        clearInterval(timer);
        timer = setInterval(function() {
            alpha += 2;
            alpha > 100 && (alpha = 100);
            content_array[index].style.opacity = alpha / 100;
            content_array[index].style.filter = "alpha(opacity=" + alpha + ")";
            alpha == 100 && clearInterval(timer);
        },
        5)
    }
}
</script>
<!-- 代码 en -->

</body>
</html>