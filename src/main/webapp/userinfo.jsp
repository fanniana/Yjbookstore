<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8" import="java.util.List,entity.Book,entity.User"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Bootstrap 101 Template</title>

<link href="css/bootstrap.min.css" rel="stylesheet">
<link href="css/index.css" rel="stylesheet">
<link rel="stylesheet" href="css/pintuer.css">
<link rel="stylesheet" href="css/admin.css">
<script src="js/jquery.js"></script>
<script src="js/pintuer.js"></script>
<link rel="stylesheet" href="css/regist.css">


</head>
<body>

<%
	//接受转发过来的数据
	User user=new User();
	if(request.getAttribute("user")!=null){
		user = (User) request.getAttribute("user");
	}
%>

	<div id="mask">
		<div id="popup">
			<div class="tanchuang1">
				<div class="closebutton1" id="btnclose"></div>
			</div>
			<div class="tanchuang3">请输入新的身份信息
			</div>		
			<div class="tanchuang2">
				    <form action="updateuserinfo.do" method="post">
			            <table>
			                <tr>
			                    <td>
			                        <input id="yhm1" type="text" name="username1" value=<%= user.getUsername() %> placeholder="用户名">
			                    </td>
			                </tr>
			                <tr>
			                    <td>
			                        <input id="shouji1" type="text" name="tel1" value=<%= user.getTel() %> placeholder="手机号码">
			                    </td>
			                </tr>
			                <tr>
			                    <td>
			                        <input type="text" name="address1" value=<%= user.getAddress() %> placeholder="收货地址">
			                    </td>
			                </tr>
			                <tr>
			                    <td>
			                        <input id="tijiao1" type="submit" value="修改">
			                    </td>
			                </tr>
			            </table>
			        </form>
			</div>
		</div>	
	</div>
	


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
              <a href="userinfoenter.do" class="weizhi1">
                <img src="image/user.png" class="img-rersponsive size2">
                <span class="zi2"><%= user.getUsername()%></span>
              </a>
            </div>
            <!-- 购物车 -->
            <div class="col-md-2">   
              <a href="shoppingcart.do" class="weizhi2">
                <img src="image/shoppingcart.png" class="img-rersponsive size3">
                <span class="zi2">购物车</span>
              </a>
            </div>
        </div>
        <!-- 导航栏 -->
        <div class="row">
          <nav class="navbar navbar-default">
            <div class="container-fluid">
              <!-- Brand and toggle get grouped for better mobile display -->
              <div class="navbar-header">
                <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
                  <span class="sr-only">Toggle navigation</span>
                  <span class="icon-bar"></span>
                  <span class="icon-bar"></span>
                  <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="#">全部图书</a>
              </div>
          
              <!-- Collect the nav links, forms, and other content for toggling -->
              <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                <ul class="nav navbar-nav">
                  <li><a href="homeload.do">  精选  </a></li>
                  <li><a href="type.do?type=文学">  文学	  </a></li>
                  <li><a href="type.do?type=科技">  科技	  </a></li>
                  <li><a href="type.do?type=休闲">  休闲    </a></li>
                  <li><a href="type.do?type=少儿">  少儿    </a></li>            
                </ul>
              </div><!-- /.navbar-collapse -->
            </div><!-- /.container-fluid -->
          </nav>
        </div>


    </header>
    
    <!-- 主体 -->
    
    
	<div class="container">
<form method="post" action="" id="listform">
  <div class="panel admin-panel">
    <div class="panel-head"><strong class="icon-reorder"> 用户信息</strong> <a href="login.jsp" style="float:right;">退出登陆</a></div>

    
    <table class="table table-hover text-center">

      <tr>
        <td>用户编号</td>
        <td><%= user.getUid()%></td>
	  </tr>
	  <tr>
        <td>用户名</td>
        <td><%= user.getUsername()%></td>
	  </tr>
      <tr>
        <td>电话号码</td>
        <td><%= user.getTel()%></td>
	  </tr>
	  <tr>
        <td>收货地址</td>
        <td><%= user.getAddress()%></td>
	  </tr>
      <tr>
        <td>操作</td>
        <td><div class="button-group button border-main" id="btn"> <span class="icon-edit"></span> 修改 </div></td>
        <!-- <td><div class="button-group"> <a class="button border-main" href="#"><span class="icon-edit"></span> 修改</a> </div></td> -->
      </tr>

    </table>
    
  </div>
</form>
</div>

		
    <!-- 底部 -->
    <footer class="container-fluid" style="height: 100px;width: 100%;position: fixed;bottom:100px">
        <div class="row dibu">
          <div class="dibu2">Copyright © 2022 LYJ. All rights reserved.</div>
        </div>
    </footer>

<script>
	window.onload=function(){
		var btn=document.getElementById('btn');
		var mask=document.getElementById('mask');
		var popup=document.getElementById('popup');
		var btnclose=document.getElementById('btnclose');
		var yhm1=document.getElementById('yhm1');
		var shouji1=document.getElementById('shouji1');
		var tijiao1=document.getElementById('tijiao1');
		var pheight=348;
		
		var winWidth = 0;
		var winHeight = 0;

		//获取窗口宽度
		if (window.innerWidth)
		winWidth = window.innerWidth;
		else if ((document.body) && (document.body.clientWidth))
		winWidth = document.body.clientWidth;
		//获取窗口高度
		if (window.innerHeight)
		winHeight = window.innerHeight;
		else if ((document.body) && (document.body.clientHeight))
		winHeight = document.body.clientHeight;
		//通过深入Document内部对body进行检测，获取窗口大小
		if (document.documentElement  && document.documentElement.clientHeight && document.documentElement.clientWidth)
		{
		winHeight = document.documentElement.clientHeight;
		winWidth = document.documentElement.clientWidth;
		}
		
		btn.onclick=function(){
			mask.style.width=winWidth+"px";
			mask.style.height=winHeight+"px";
			mask.style.display="block";
			mask.style.zIndex=2147483647;
			popup.style.marginTop=(winHeight-pheight)/2+"px";
		}
		btnclose.onclick=function(){
			mask.style.display="none";
		}
		var regexp=/^(13[0-9]|14[01456879]|15[0-35-9]|16[2567]|17[0-8]|18[0-9]|19[0-35-9])\d{8}$/
		var str = shouji1.value;
		tijiao1.onclick=function(){
			var str = shouji1.value;
			if(regexp.test(str)){
				if(confirm("确认要修改个人信息吗？")){			
					return true;
				}else{
					return false;
				}
			}else{
				alert("请输入正确的手机号码！");
				return false;
			}
		}
		
	}
</script>
		
	<script src="js/jquery-3.6.0.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
	
</body>
</html>