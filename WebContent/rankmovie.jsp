<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ include file="header.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>给这部电影一个评分</title>

<style>
.wrapper{width:300px; margin:10px auto; font:14px/1.5 arial;}
/*tab*/
#star{overflow:hidden;}
#star li{float:left; width:20px; height:20px; margin:2px; display:inline; color:#999; font:bold 18px arial; cursor:pointer}
#star .act{color:#c00}
#star_word{width:80px; height:30px; line-height:30px; border:1px solid #ccc; margin:10px; text-align:center; display:none}

</style>

<script>
window.onload = function(){

  var star = document.getElementById("star");
    var star_li = star.getElementsByTagName("li");
    var star_word = document.getElementById("star_word");
    var result = document.getElementById("result");
    var i=0;
    var j=0;
    var len = star_li.length;
    var word = ['很差','差','一般',"好","很好"]
    
    for(i=0; i<len; i++){
      star_li[i].index = i;
        
        star_li[i].onmouseover = function(){
              star_word.style.display = "block";
                star_word.innerHTML = word[this.index];
                for(i=0; i<=this.index; i++){
                  star_li[i].className = "act";
                    
                }
        }

        star_li[i].onmouseout = function(){
              star_word.style.display = "none";
                for(i=0; i<len; i++){
                 star_li[i].className = "";
                }
        }
        
        star_li[i].onclick = function(){
          result.innerHTML = (this.index+1)+"分";
        }
        

    }

}
</script>

</head>
<body>

<div class="wrapper">
打分结果 
<span id="result"></span>
<ul id="star">
<li>★</li>
<li>★</li>
<li>★</li>
<li>★</li>
<li>★</li>
</ul>
<div id="star_word">一般</div>

</div>  

</body>
</html>