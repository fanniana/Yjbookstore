<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8" import="java.util.List,entity.Book,entity.Book,dao.BookDao,dao.impl.BookDaoImpl,entity.User,dao.UserDao,dao.impl.UserDaoImpl"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Bootstrap 101 Template</title>

<link href="css/bootstrap.min.css" rel="stylesheet">
<link href="css/index.css" rel="stylesheet">


</head>
<body>

<% 		
	String username="用户名";
	if(session.getAttribute("username")!=null){
		username = (String)session.getAttribute("username");
	}
	UserDao dao1 = new UserDaoImpl();
	User user = dao1.getSingleUser(username);
	int uid = user.getUid();
	
	BookDao dao = new BookDaoImpl();
	List<Book> booklist1 =dao.getBook1();
	List<Book> booklist2 =dao.getBook2(); 
	List<Book> booklist3 =dao.getBook3(uid); 
%>

<div id="suinfo" style="display:none;"><%= request.getAttribute("suinfo") %></div>

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
                <span class="zi2"><%= username%></span>
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
                  <li class="active"><a href="#">精选<span class="sr-only">(current)</span></a></li>
                  <li><a href="type.do?type=文学">  文学  </a></li>
                  <li><a href="type.do?type=科技">  科技  </a></li>
                  <li><a href="type.do?type=休闲">  休闲  </a></li>
                  <li><a href="type.do?type=少儿">  少儿  </a></li>               
                </ul>
              </div><!-- /.navbar-collapse -->
            </div><!-- /.container-fluid -->
          </nav>
        </div>
        <!-- 轮播图 -->
        <div class="container">
          <div id="carousel-example-generic" class="carousel slide abc" data-ride="carousel">
            <!-- Indicators -->
            <ol class="carousel-indicators">
              <li data-target="#carousel-example-generic" data-slide-to="0" class="active"></li>
              <li data-target="#carousel-example-generic" data-slide-to="1"></li>
              <li data-target="#carousel-example-generic" data-slide-to="2"></li>
            </ol>
          
            <!-- Wrapper for slides -->
            <div class="carousel-inner" role="listbox">
              <div class="item active">
                <img src="image/lunhuantu1.jpg" alt="..." style="width:100%;height: 300px;">
              </div>
              <div class="item">
                <img src="image/lunhuantu2.jpg" alt="..." style="width:100%;height: 300px;">
              </div>
              <div class="item">
                <img src="image/lunhuantu3.jpg" alt="..." style="width:100%;height: 300px;">
              </div>
              <div class="item">
                <img src="image/lunhuantu4.jpg" alt="..." style="width:100%;height: 300px;">
              </div>
              ...
            </div>
          
            <!-- Controls -->
            <a class="left carousel-control" href="#carousel-example-generic" role="button" data-slide="prev">
              <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
              <span class="sr-only">Previous</span>
            </a>
            <a class="right carousel-control" href="#carousel-example-generic" role="button" data-slide="next">
              <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
              <span class="sr-only">Next</span>
            </a>
          </div>
        </div>
    </header>
    <!-- 主体 -->
    <div class="container">
        <!-- 精品图书 -->
        <div class="row jx">
            <img src="image/star.png" alt="">
            <span>精品图书</span>
        </div>
        <div class="row padding-top">
        
<%
	for(Book book : booklist1){
%>
        
          <div class="col-md-3 jianju">
              <div class="thumbnail">
                <a href="singlebook.do?bname=<%= book.getBname() %>">
                  <img src="<%= book.getImage() %>" alt="" onerror="this.src='image/errorpic.png;this.onerror=null'">
                  <p class="text-center shu line1s"><%= book.getBname() %></p>
                  <p class="text-center shu"><%= book.getAuthor() %></p>
                </a>
                  <p class="text-center"><font color="red" class="shu">￥<%= book.getPrice() %></font>&nbsp&nbsp&nbsp&nbsp<button id="additem" type="button" onclick="javascript:window.location.href='addshoppingcart.do?bid=<%= book.getBid() %>&page=0';" class="btn btn-success"> 添加购物车</button></p>       
                
              </div>
          </div>
         
<%	
	}
%>
          
          </div>
        <!-- 排行榜 -->
        <div class="row jx">
          <img src="image/leaderboard.png" alt="">
          <span>排行榜</span>
        </div>
        <div class="row padding-top">
        
<%
	for(Book book2 : booklist2){
%>
        
          <div class="col-md-3 jianju">
              <div class="thumbnail">
                <a href="singlebook.do?bname=<%= book2.getBname() %>">
                  <img src="<%= book2.getImage() %>" alt="" onerror="this.src='image/errorpic.png;this.onerror=null'">
                  <p class="text-center shu line1s"><%= book2.getBname() %></p>
                  <p class="text-center shu"><%= book2.getAuthor() %></p>
                  <p class="text-center shu">销量<%= book2.getSellcount() %></p>
                </a>
                  <p class="text-center"><font color="red" class="shu">￥<%= book2.getPrice() %></font>&nbsp&nbsp&nbsp&nbsp <button id="additem" type="button" onclick="javascript:window.location.href='addshoppingcart.do?bid=<%= book2.getBid() %>&page=0';" class="btn btn-success"> 添加购物车</button></p>       
              </div>
          </div>
         
<%	
	}
%>
          
          </div> 
        <!-- 为您推荐 -->
        <div class="row jx">
          <img src="image/recommend.png" alt="">
          <span>为您推荐</span>
        </div>
        <div class="row padding-top">
        <!-- 用一下精选的数据  -->
<%
	for(Book book3 : booklist3){
%>
        
          <div class="col-md-3 jianju">
              <div class="thumbnail">
                <a href="singlebook.do?bname=<%= book3.getBname() %>">
                  <img src="<%= book3.getImage() %>" alt="" onerror="this.src='image/errorpic.png;this.onerror=null'">
                  <p class="text-center shu line1s"><%= book3.getBname() %></p>
                  <p class="text-center shu"><%= book3.getAuthor() %></p>
                </a>
                  <p class="text-center"><font color="red" class="shu">￥<%= book3.getPrice() %></font>&nbsp&nbsp&nbsp&nbsp<button id="additem" type="button" onclick="javascript:window.location.href='addshoppingcart.do?bid=<%= book3.getBid() %>&page=0';" class="btn btn-success"> 添加购物车</button></p>       
              </div>
          </div>
         
<%	
	}
%>
        </div>  
    </div>

    <!-- 底部 -->
    <footer class="container-fluid">
        <div class="row dibu">
          <div class="dibu2">Copyright © 2022 LYJ. All rights reserved.</div>
        </div>
    </footer>
	
	<script>
	window.onload =function(){
    	var suinfo1 = document.getElementById("suinfo");
    	var suinfo2 = suinfo.innerText;
    	if(suinfo2 == "添加成功！"){
    		alert("添加成功!");
    	}
	}
	</script>
	<script src="js/jquery-3.6.0.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
	
</body>
</html>