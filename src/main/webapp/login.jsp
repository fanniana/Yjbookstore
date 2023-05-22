<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>用户登录</title>

<link rel="stylesheet" href="css/login.css">

</head>
<body>

<%
	//接受转发过来的数据
	String username = "用户名";
	String password = "密码";
	
	if(request.getAttribute("useremptyError")!=null){
		username =(String)request.getAttribute("useremptyError");
	}

	if(request.getAttribute("passwordemptyError")!=null){
		password=(String)request.getAttribute("passwordemptyError");
	}

	if(request.getAttribute("usernameError")!=null){
		username=(String)request.getAttribute("usernameError");
	}

	if(request.getAttribute("passwordError")!=null){
		password=(String)request.getAttribute("passwordError");
	}
%>


<div class="box">    
    <div class="ys0">
        <h4>网上书店</h4>
        <form action="login.do" method="post">
            <input class="ys1" type="text" name="username1"  placeholder=<%= username %>>
            <input class="ys1" type="password" name="password1" placeholder=<%= password %>>
            <input class="login" type="submit" value="Login">
        </form>
        <div class="fn">
            <a href="regist.jsp">注册账号</a>
            <a href="#">管理员登陆</a>
        </div>
    </div>
</div>

</body>
</html>