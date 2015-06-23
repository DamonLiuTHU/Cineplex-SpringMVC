<%@page import="com.cineplex.model.impl.OrderModel"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="mheader.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>影厅详情</title>
<style>
</style>
<script type="text/javascript"
	src="http://cdn.hcharts.cn/jquery/jquery-1.8.3.min.js"></script>
<script type="text/javascript"
	src="http://cdn.hcharts.cn/highcharts/highcharts.js"></script>
<script type="text/javascript"
	src="http://cdn.hcharts.cn/highcharts/exporting.js"></script>
<script type="text/javascript">
$(function () {
	
    $('#container').highcharts({
    	
        chart: {
            type: 'column',
            margin: [ 50, 50, 100, 80]
        },
        title: {
            text: '影厅收入情况'
        },
        xAxis: {
            categories: [
                '1号厅',
                '2号厅',
                '3号厅',
            ],
            labels: {
                rotation: 0,
                align: 'right',
                style: {
                    fontSize: '15px',
                    fontFamily: 'Verdana, sans-serif'
                }
            }
        },
        yAxis: {
            min: 0,
            title: {
                text: '营业额（RMB）'
            }
        },
        legend: {
            enabled: false
        },
        tooltip: {
            pointFormat: '营业额: <b>{point.y:.1f} 元</b>',
        },
        series: [{
            name: 'Population',
            data: ["${requestScope.data[0]}"*1, 
                   "${requestScope.data[1]}"*1, 
                   "${requestScope.data[2]}"*1, 
                   ],
            dataLabels: {
                enabled: true,
                rotation: 0,
                color: '#FFFFFF',
                align: 'center',
                x: 4,
                y: 10,
                style: {
                    fontSize: '13px',
                    fontFamily: 'Verdana, sans-serif',
                    textShadow: '0 0 3px black'
                }
            }
        }]
    });
});

</script>
</head>
<body>

	<div id="container" style="min-width: 700px; height: 400px"></div>

	<%@ include file="../footer.jsp"%>
</body>

</html>