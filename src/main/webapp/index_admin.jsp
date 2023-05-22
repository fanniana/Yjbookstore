<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
    <meta name="renderer" content="webkit">
    <title>后台管理中心</title>  
    <link rel="stylesheet" href="css/pintuer.css">
    <link rel="stylesheet" href="css/admin.css">
    <script src="js/jquery.js"></script>   
</head>

<body style="background-color:#f2f9fd;">

<div class="header bg-main">
  <div class="logo margin-big-left fadein-top">
    <h1><img src="image/regist.png" class="radius-circle rotate-hover" height="50" alt="" />网上书店后台管理中心</h1>
  </div>
  <div class="head-l"><a class="button button-little bg-green" href="index.jsp" target="_blank"><span class="icon-home"></span> 前台首页</a>  &nbsp;&nbsp;<a class="button button-little bg-red" href="login.html"><span class="icon-power-off"></span> 退出登录</a> </div>
</div>


<div class="leftnav">
  <div class="leftnav-title"><strong><span class="icon-list"></span>菜单列表</strong></div>
  <h2><span class="icon-user"></span>用户管理</h2>
  <ul style="display:block">
    <li><a href="usermanager.jsp" target="right"><span class="icon-caret-right"></span>用户查询</a></li>
  </ul>   
  <h2><span class="icon-pencil-square-o"></span>订单管理</h2>
  <ul>
    <li><a href="orderprocess.do" target="right"><span class="icon-caret-right"></span>待处理订单</a></li>
    <li><a href="ordermanager.do" target="right"><span class="icon-caret-right"></span>查询订单</a></li>
  </ul>  
    <h2><span class="icon-pencil-square-o"></span>图书管理</h2>
  <ul>
    <li><a href="bookmanager.jsp" target="right"><span class="icon-caret-right"></span>查询图书</a></li> 
  </ul>  
</div>


<script type="text/javascript">
$(function(){
  $(".leftnav h2").click(function(){
	  $(this).next().slideToggle(200);	
	  $(this).toggleClass("on"); 
  })
  $(".leftnav ul li a").click(function(){
	    $("#a_leader_txt").text($(this).text());
  		$(".leftnav ul li a").removeClass("on");
		$(this).addClass("on");
  })
});
</script>

<ul class="bread">
  <li><a href="adminfirst.jsp" target="right" class="icon-home"> 首页</a></li>
  <li><a href="##" id="a_leader_txt">网站信息</a></li>
  <li><b>当前语言：</b><span style="color:red;">中文</php></span>
</ul>

<!-- 我的内容 -->
<div class="admin">
  <iframe scrolling="auto" rameborder="0" src="adminfirst.jsp" name="right" width="100%" height="100%"></iframe>
</div>



</body>
</html>
 
