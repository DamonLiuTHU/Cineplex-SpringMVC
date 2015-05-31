<%@page import="com.cineplex.model.impl.StatisticsModel"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="mheader.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>逐月统计信息</title>
<style>
td, tr, th {
	border-bottom: 1px solid #AEDEF2;
	border-left: 1px solid #AEDEF2;
	border-right: 1px solid #AEDEF2;
	border-top: 1px solid #AEDEF2;
}
</style>

<script type="text/javascript"
	src="../js/jquery-1.9.0.min.js"></script>
<script type="text/javascript"
	src="../js/highcharts.js"></script>
<script type="text/javascript"
	src="../js/*"></script>
<script>
$(function () {
    $('#container').highcharts({
        chart: {
            plotBackgroundColor: null,
            plotBorderWidth: null,
            plotShadow: false
        },
        title: {
            text: '网上购票与现金购票比例'
        },
        tooltip: {
    	    pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
        },
        plotOptions: {
            pie: {
                allowPointSelect: true,
                cursor: 'pointer',
                dataLabels: {
                    enabled: true,
                    color: '#000000',
                    connectorColor: '#000000',
                    format: '<b>{point.name}</b>: {point.percentage:.1f} %'
                }
            }
        },
        series: [{
            type: 'pie',
            name: 'Browser share',
            data: [
               
                {
                    name: 'online',
                    y: <%=StatisticsModel.getCashandOnline()[0] %>,
                    sliced: true,
                    selected: true
                },
                ['cash', <%=StatisticsModel.getCashandOnline()[1] %>]
                
            ]
        }]
    });
});				
  </script>
</head>
<body>
	
	<form action="checkByMonth" method="POST">
		Month: <input type="month" name="user_date" /> <input type="submit" />
	</form>

	<table class="mytable">
		<tr>
			<th>会员年龄</th>
			<th>会员居住城市</th>
		</tr>
		<c:forEach items="${mystat.userlist}" var="user">
			<tr>
				<td>${user.age }</td>
				<td>${user.city}</td>
			</tr>
		</c:forEach>
	</table>
	<input type="button" onclick="showCashandOnline()" value="查看现金/网上售票比例"/>
	<div id="container" style="min-width:700px;height:400px"></div>


</body>
<script type="text/javascript">
	function showCashandOnline(){
		var con = document.getElementById("container");
		if(con.style.display=='none'){
			con.style.display='block'
			}else{
				con.style.display='none'
				}
	}


</script>
</html>