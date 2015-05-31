<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="mheader.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>发布电影</title>

<style type="text/css">
.mylabel {
	width: 200px;
	height: 50px;
}
</style>
</head>
<body>
<img name="showimg" id="showimg" src="" style="display:none;" alt="预览图片" />  
	<form action="publishMovie" enctype="multipart/form-data" method="post">
		<input type="hidden" name="id" value=""/>
		<label class="myfile">请选择海报图片</label>
		<input name="poster" type="file" id="imgfile" size="40" onchange="showimg1()"/> <br /> 
		<label class="mylabel">电影名称：</label>
		<input name="name" type="text" id="text" /><br /> 
		<label class="mylabel">国家：</label>
		<input name="country" type="text" id="country" /> <br /> 
		<label class="mylabel">公司：</label>
		<input name="company" type="text" id="price" /> <br /> 
		<label class="mylabel">价格：</label>
		<input name="price" type="text" id="price" /> <br /> 
		<label class="mylabel">简短剧情介绍：</label>
		<input name="short_description" type="text" id="long_description" /> <br /> 
		<label class="mylabel">剧情介绍：</label>
		<input name="long_description" type="text" id="long_description" /> <br /> 
		<label class="mylabel">导演：</label><input name="director" type="text" id="director " /> <br /> 
		<label class="mylabel">主要明星：</label><input name="main_star" type="text" id="main_star" /> <br /> 
		<label class="mylabel">电影类别：</label>
		<input name="type" type="text" id="type" /> <br /> 
		<input type="submit" />
	</form>
</body>
<script>
	function viewmypic(mypic, imgfile) {
		if (imgfile.value) {
			mypic.src = imgfile.value;
			mypic.style.display = "";
			mypic.border = 1;
		}
	}
	 function showimg1() {
         var img = document.getElementById("imgfile").value;
         document.getElementById("showimg").src =img;
     }
</script>
</html>