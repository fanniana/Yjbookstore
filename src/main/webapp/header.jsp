<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Insert title here</title>

<link href="css/bootstrap.min.css" rel="stylesheet">
<link href="css/index.css" rel="stylesheet">

</head>
<body>
	    <!-- 页眉 -->
    <header class="container-fluid">
        <!-- 顶部 -->
        <div class="row padding-top">
            <div class="col-md-4"> 
                <img src="image/regist.png" class="img-rersponsive size1">
                <span class="zi1">网上书店</span>
            </div>
            <div class="col-md-4">
	            <form action="searchbook.do" method="post">
	                <input class="search-input" name="str" placeholder="书籍">
	                <input class="search-btn" type="submit" value="搜索">
	            </form>
            </div>
            <!-- 用户信息超链接 -->
            <div class="col-md-2">
              <a href="#" class="weizhi1">
                <img src="image/user.png" class="img-rersponsive size2">
                <span class="zi2">用户名</span>
              </a>
            </div>
            <!-- 购物车 -->
            <div class="col-md-2">   
              <a href="#" class="weizhi2">
                <img src="image/shoppingcart.png" class="img-rersponsive size3">
                <span class="zi2">购物车</span>
              </a>
            </div>
        </div>
    </header>
  
  	<script src="js/jquery-3.6.0.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
	<script type="text/javascript">
			function getInputVal(){
			const inputBox = document.querySelectorAll('.input-box')[0],
				  content = document.querySelectorAll('.content')[0];
			// 获取input输入框中的值
			let inputVal = inputBox.value;
			if(inputVal){
				content.innerHTML = inputVal;
			}else{
				alert('请在input输入框中填点东西');
			}
		}
	</script>
 	
</body>
</html>