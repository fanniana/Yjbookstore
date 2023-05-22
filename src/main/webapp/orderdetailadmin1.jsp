<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8" import="java.util.List,entity.Book,dao.BookDao,dao.impl.BookDaoImpl,entity.Order,entity.User,entity.OrderDetail"%>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
<meta name="renderer" content="webkit">
<title></title>
<link rel="stylesheet" href="css/pintuer.css">
<link rel="stylesheet" href="css/admin.css">
<script src="js/jquery.js"></script>
<script src="js/pintuer.js"></script>
</head>
<body>

<form method="post" action="" id="listform">
  <div class="panel admin-panel">
    <div class="panel-head"><strong class="icon-reorder"> 订单详情</strong> <a href="" style="float:right; display:none;">添加字段</a></div>

    
<%
	//接受转发过来的数据
	Order orderinfo = new Order();
	if(request.getAttribute("orderinfo")!=null){
		orderinfo = (Order) request.getAttribute("orderinfo");
	}
	User user = new User();
	if(request.getAttribute("user")!=null){
		user = (User) request.getAttribute("user");
	}
	List<OrderDetail> orderdetaillist = (List<OrderDetail>) request.getAttribute("orderdetaillist");
%>
    
    <table class="table table-hover text-center">
      
      <tr>
        <td>订单编号</td>
        <td><%= orderinfo.getOid() %></td>
	  </tr>
	  <tr>
        <td>时间</td>
        <td><%= orderinfo.getTime() %></td>
	  </tr>
      <tr>
        <td>订单状态</td>
        <td><%= orderinfo.getState() %></td>
	  </tr>
	  
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
        <th width="20%" style="text-align:left; padding-left:20px;">BID</th>
        <th width="20%">书名</th>
        <th width="20%">价格</th>
        <th width="20%">数量</th>
        <th width="20%">小计</th>
      </tr>
      <volist name="list" id="vo">
      
<%
	for(OrderDetail orderdetail : orderdetaillist){
%>
        <tr>
          <td style="text-align:left; padding-left:20px;"><input type="checkbox" name="id[]" value="" /><%= orderdetail.getBid() %></td>
          <td><%= orderdetail.getBname() %></td>
          <td><%= orderdetail.getPrice() %></td>
          <td><%= orderdetail.getCount() %></td>
          <td><%= orderdetail.getPrice()*orderdetail.getCount() %></td>
        </tr>
        
<%	
	}
%> 
      
    </table>
    
  </div>
</form>


<script type="text/javascript">

//搜索
function changesearch(){	
		
}

//单个删除
function del(id,mid,iscid){
	if(confirm("您确定要删除吗?")){
		
	}
}

//全选
$("#checkall").click(function(){ 
  $("input[name='id[]']").each(function(){
	  if (this.checked) {
		  this.checked = false;
	  }
	  else {
		  this.checked = true;
	  }
  });
})

//批量删除
function DelSelect(){
	var Checkbox=false;
	 $("input[name='id[]']").each(function(){
	  if (this.checked==true) {		
		Checkbox=true;	
	  }
	});
	if (Checkbox){
		var t=confirm("您确认要删除选中的内容吗？");
		if (t==false) return false;		
		$("#listform").submit();		
	}
	else{
		alert("请选择您要删除的内容!");
		return false;
	}
}

//批量排序
function sorts(){
	var Checkbox=false;
	 $("input[name='id[]']").each(function(){
	  if (this.checked==true) {		
		Checkbox=true;	
	  }
	});
	if (Checkbox){	
		
		$("#listform").submit();		
	}
	else{
		alert("请选择要操作的内容!");
		return false;
	}
}


//批量首页显示
function changeishome(o){
	var Checkbox=false;
	 $("input[name='id[]']").each(function(){
	  if (this.checked==true) {		
		Checkbox=true;	
	  }
	});
	if (Checkbox){
		
		$("#listform").submit();	
	}
	else{
		alert("请选择要操作的内容!");		
	
		return false;
	}
}

//批量推荐
function changeisvouch(o){
	var Checkbox=false;
	 $("input[name='id[]']").each(function(){
	  if (this.checked==true) {		
		Checkbox=true;	
	  }
	});
	if (Checkbox){
		
		
		$("#listform").submit();	
	}
	else{
		alert("请选择要操作的内容!");	
		
		return false;
	}
}

//批量置顶
function changeistop(o){
	var Checkbox=false;
	 $("input[name='id[]']").each(function(){
	  if (this.checked==true) {		
		Checkbox=true;	
	  }
	});
	if (Checkbox){		
		
		$("#listform").submit();	
	}
	else{
		alert("请选择要操作的内容!");		
	
		return false;
	}
}


//批量移动
function changecate(o){
	var Checkbox=false;
	 $("input[name='id[]']").each(function(){
	  if (this.checked==true) {		
		Checkbox=true;	
	  }
	});
	if (Checkbox){		
		
		$("#listform").submit();		
	}
	else{
		alert("请选择要操作的内容!");
		
		return false;
	}
}

//批量复制
function changecopy(o){
	var Checkbox=false;
	 $("input[name='id[]']").each(function(){
	  if (this.checked==true) {		
		Checkbox=true;	
	  }
	});
	if (Checkbox){	
		var i = 0;
	    $("input[name='id[]']").each(function(){
	  		if (this.checked==true) {
				i++;
			}		
	    });
		if(i>1){ 
	    	alert("只能选择一条信息!");
			$(o).find("option:first").prop("selected","selected");
		}else{
		
			$("#listform").submit();		
		}	
	}
	else{
		alert("请选择要复制的内容!");
		$(o).find("option:first").prop("selected","selected");
		return false;
	}
}

</script>

</body>
</html>