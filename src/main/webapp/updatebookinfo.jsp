<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8" import="java.util.List,entity.User,entity.User,dao.UserDao,dao.impl.UserDaoImpl,dao.BookDao,dao.impl.BookDaoImpl,entity.Book"%>
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

<%
	String bname = request.getParameter("bname");
	BookDao dao = new BookDaoImpl();
	Book book = dao.findBookByName(bname);
	int bid = book.getBid();
%>

<div class="panel admin-panel">
  <div class="panel-head" id="add"><strong><span class="icon-pencil-square-o"></span>添加图书</strong></div>
  <div class="body-content">
    <form method="post" class="form-x" action="updatebook.do">  
    <input type="hidden" value="<%= bid %>" name="bid" id="data" />
      <div class="form-group">
        <div class="label">
          <label>书名：</label>
        </div>
        <div class="field">
          <input type="text" class="input w50" value="<%= book.getBname() %>" name="bname" data-validate="required:请输入书名" />
          <div class="tips"></div>
        </div>
      </div>
      <div class="form-group">
        <div class="label">
          <label>作者：</label>
        </div>
        <div class="field">
          <input type="text" class="input w50" value="<%= book.getAuthor() %>" name="author"/>
          <div class="tips"></div>
        </div>
      </div>     
      <div class="form-group">
        <div class="label">
          <label>出版社：</label>
        </div>
        <div class="field">
          <input type="text" class="input w50" value="<%= book.getPublisher() %>" name="publisher"/>
          <div class="tips"></div>
        </div>
      </div>
      <if condition="$iscid eq 1">
        <div class="form-group">
          <div class="label">
            <label>书籍类型：</label>
          </div>
          <div class="field">
            <select name="types" class="input w50" value="<%= book.getType() %>">
              <option value="">请选择分类</option>
              <option value="文学">文学</option>
              <option value="科技">科技</option>
              <option value="休闲">休闲</option>
              <option value="少儿">少儿</option>
            </select>
            <div class="tips"></div>
          </div>
        </div>
      </if>
      <div class="form-group">
        <div class="label">
          <label>书籍简介：</label>
        </div>
        <div class="field">
          <textarea class="input" name="introduction" style=" height:90px;"><%= book.getIntroduction() %></textarea>
          <div class="tips"></div>
        </div>
      </div>
      
      <div class="form-group">
        <div class="label">
          <label>图片：</label>
        </div>
        <div class="field">
          <input type="text" id="url1" name="image" class="input tips" style="width:25%; float:left;"  value="<%= book.getImage() %>"  data-toggle="hover" data-place="right" data-image="" />
          <input type="button" class="button bg-blue margin-left" id="image1" value="+ 浏览上传"  style="float:left;">
          <div class="tipss">图片尺寸：156*208</div>
        </div>
      </div>  
     
      <div class="form-group">
        <div class="label">
          <label>价格：</label>
        </div>
        <div class="field">
          <input type="text" class="input w50" value="<%= book.getPrice() %>" name="price"/>
          <div class="tips"></div>
        </div>
      </div>
      
      <div class="form-group">
        <div class="label">
          <label>库存：</label>
        </div>
        <div class="field">
          <input type="text" class="input w50" value="<%= book.getCount() %>" name="count"/>
          <div class="tips"></div>
        </div>
      </div>
      <div class="form-group">
        <div class="label">
          <label>是否推荐：</label>
        </div>
        <div class="field">
          <input type="text" class="input w50" value="<%= book.getRecommend() %>" name="recommend"/>
          <div class="tipss">1代表推荐，0代表不推荐</div>
        </div>
      </div>

      <div class="form-group">
        <div class="label">
          <label></label>
        </div>
        <div class="field">
          <button class="button bg-main icon-check-square-o" type="submit"> 提交</button>
        </div>
      </div>
    </form>
  </div>
</div>        
</body>
</html>