<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Insert title here</title>

<link rel="stylesheet" href="css/regist.css">

</head>
<body>

<%
	//接受转发过来的数据
	String username = "支持字母数字、_、的组合";
	String password1 = "建议使用8-16位的数字加字母的组合";
	String password2 = "两次密码必须一致";
	
	if(request.getAttribute("useremptyError")!=null){
		username =(String)request.getAttribute("useremptyError");
	}
	
	if(request.getAttribute("usernameError")!=null){
		username =(String)request.getAttribute("usernameError");
	}

	if(request.getAttribute("passwordemptyError")!=null){
		password1=(String)request.getAttribute("passwordemptyError");
	}

	if(request.getAttribute("passwordError")!=null){
		password2=(String)request.getAttribute("passwordError");
	}
%>

    <div id="header">
        <div id="line1">
            <img src="image/regist.png"/>
            <span class="line2"></span>
            <span class="welcome">欢迎注册</span>
        </div>
        <div id="line2">
            <span id="line2">已有账号？</span>
            <a href="login.jsp">请登录</a>
        </div>
    </div>

    <div id="middle">
        <form action="regist.do" method="post">
            <table>
                <tr>
                    <td>
                        <input type="text" name="username" placeholder="用户名">
                    </td>
                </tr>
                <tr>
                    <td class="msg" id="name">
						<%= username %>
                    </td>
                </tr>
                <tr>
                    <td>
                        <input type="password" name="password" placeholder="密码" >
                    </td>
                </tr>
                <tr>
                    <td class="msg" id="password1">
                        <%= password1 %>
                    </td>
                </tr>
                <tr>
                    <td>
                        <input type="password" name="repassword" placeholder="请再次确认密码" >
                    </td>
                </tr>
                <tr>
                    <td class="msg" id="password2">
                       	<%= password2 %>
                    </td>
                </tr>
                <tr>
                    <td>
                        <input type="text" name="tel" placeholder="手机号码">
                    </td>
                </tr>
                <tr>
                    <td>
                        <input type="text" name="adress" placeholder="收货地址">
                    </td>
                </tr>
                <tr>
                    <td>
                        <input type="submit" value="立即注册">
                    </td>
                </tr>
            </table>
        </form>
    </div>
    
    <script>
    	var name1 = document.getElementById("name");
    	var name2 = name1.innerText;
    	
    	if(name2 != "支持字母数字、_、的组合"){
    		name1.style.color = "red";
    	}
    	
    	var password1 = document.getElementById("password1");
    	if(password1.innerText != "建议使用8-16位的数字加字母的组合"){
    		password1.style.color = "red";
    	}
    	
    	var password2 = document.getElementById("password2");   	
    	if(password2.innerText != "两次密码必须一致"){
    		password2.style.color = "red";
    	}
    	
    </script>
    
</body>
</html>