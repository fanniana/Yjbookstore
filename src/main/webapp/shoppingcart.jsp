<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8" import="java.util.List,entity.Book,entity.ShoppingCart,entity.Order"%>
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

</head>
<body>

<%
	String username="用户名";
	if(session.getAttribute("username")!=null){
		username = (String)session.getAttribute("username");
	}
%>

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
<form method="post" action="placeorder.do" id="listform">
  <div class="panel admin-panel">
    <div class="panel-head"><strong class="icon-reorder"> 购物车</strong> <a href="" style="float:right; display:none;">添加字段</a></div>

    
    <table class="table table-hover text-center">
      <tr>
        <th width="17%" style="text-align:left; padding-left:20px;">书名</th>
        <th width="17%">图片</th>
        <th width="17%">单价/元</th>
        <th>数量/本</th>
        <th width="17%">小计/元</th>
        <th width="17%">操作</th>
      </tr>
      <volist name="list" id="vo">
      
      <tbody id="tbody1">

<%
	List<ShoppingCart> itemslist = (List<ShoppingCart>) request.getAttribute("itemslist");
	for(ShoppingCart items : itemslist){
%>

      <tr>
        <td style="text-align:left; padding-left:20px;"><input class="check1" type="checkbox" name="itemcheck" value="<%= items.getBid() %>"/><%= items.getBname() %></td>
        <td><img src="<%= items.getImage() %>" alt=""  height="50" /></td>
        <td><%= items.getPrice() %></td>
        <td><%= items.getCountbook() %></td>
        <td><%= items.getPrice()*items.getCountbook() %></td>
        <td><div class="button-group"> <a class="button border-red" href="deleteitem.do?bid=<%= items.getBid() %>"><span class="icon-trash-o"></span> 删除</a> </div></td>
      </tr>

<%	
	}
%>
	  </tbody>

      <tr>
        <td style="text-align:left; padding:19px 0;padding-left:20px;"><input class="checkAll1" type="checkbox" id="checkall"/>全选 </td>
        <td></td>
        <td></td>
        <td></td>
        <td>合计：<span id="priceTotal">0</span></td>
        <td><div class="button-group"><input class="button border-blue" type="submit" value="下单"></div></td>     
      </tr>

    </table>
    
  </div>
</form>


<!-- 订单 -->
<form method="post" action="" id="listform">
  <div class="panel admin-panel">
    <div class="panel-head"><strong class="icon-reorder"> 我的订单</strong> <a href="" style="float:right; display:none;">添加字段</a></div>

    
    <table class="table table-hover text-center">
      <tr>
        <th width="20%" style="text-align:left; padding-left:20px;">订单号</th>
        <th width="20%">总价</th>
        <th width="20%">时间</th>
        <th width="20%">状态</th>
        <th width="20%">操作</th>
      </tr>
      <volist name="list" id="vo">
  
<%
	List<Order> orderlist = (List<Order>) request.getAttribute("orderlist");
	for(Order order : orderlist){
%>      
      <tr>
        <td style="text-align:left; padding-left:20px;"><a href="orderdetailadmin1.do?oid=<%= order.getOid() %>"><%= order.getOid() %></a></td>
        <td><%= order.getTotalprice() %></td>
        <td><%= order.getTime() %></td>
        <td><%= order.getState() %></td>
        <td><div class="button-group"> <a class="button border-main" href="paymoney.do?oid=<%= order.getOid() %>" id="paymoney1"><span class="icon-edit"></span> <%= order.getOperate() %></a> <a class="button border-red" href="javascript:void(0)" onclick="return del(1,1,1)"><span class="icon-trash-o"></span> 删除</a> </div></td>
      </tr>
<%	
	}
%>      

    </table>
    
  </div>
</form>
</div>

    <!-- 底部 -->
    <footer class="container-fluid">
        <div class="row dibu">
          <div class="dibu2">Copyright © 2022 LYJ. All rights reserved.</div>
        </div>
    </footer>

<script type="text/javascript">
function del(id,mid){
	if(confirm("您确定要删除吗?")){			
		
	}
}


//合计已选商品

window.onload= function(){
		
	
		var priceTotal=document.getElementById('priceTotal');
		var checkOne = getClass('check1');
		var checkAll1 = getClass('checkAll1')

		for(var i=0;i<checkOne.length;i++){
			checkOne[i].onclick = function(){
				console.log(this.checked);
				getTotal();	
			}
		}
		checkAll1[0].onclick = function(){
			for(var i=0;i<checkOne.length;i++){
				console.log(this.checked);
				checkOne[i].checked=this.checked;
				getTotal();	
			}
		}
		
		var paymoney1=document.getElementById('paymoney1');
		paymoney1.onclick = function(){
			if(confirm("请确认支付！")){			
				return true;
			}else{
				return false;
			}
		}
		

}

function getTotal(){
	var tbody1 = document.getElementById('tbody1');
	var tr = tbody1.getElementsByTagName('tr');
	var checkOne = getClass('check1');
	var pricet1 = 0;
	console.log(tr);
	for(var i=0;i<tr.length;i++){		
		console.log(checkOne[i].checked);
		if(checkOne[i].checked){
			console.log(tr[i].getElementsByTagName('td')[4].innerText);
			pricet1+=parseInt(tr[i].getElementsByTagName('td')[4].innerText);
		}
	}	
	priceTotal.innerText=pricet1+0.00;	
}

function getClass(cName){
	var doms =  document.getElementsByTagName("*");
	var domArr = [];
	for(var i=0;i<doms.length;i++){
		if(doms[i].className==cName){
			domArr.push(doms[i]);
		}		
	}
	return domArr;
}

</script>
		
	<script src="js/jquery-3.6.0.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
	
</body>
</html>