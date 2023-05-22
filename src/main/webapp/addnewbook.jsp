<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8" import="java.util.List,entity.User,entity.User,dao.UserDao,dao.impl.UserDaoImpl"%>
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

<div class="panel admin-panel">
  <div class="panel-head" id="add"><strong><span class="icon-pencil-square-o"></span>添加图书</strong></div>
  <div class="body-content">
    <form method="post" class="form-x" action="addnewbook.do">  
      <div class="form-group">
        <div class="label">
          <label>书名：</label>
        </div>
        <div class="field">
          <input type="text" class="input w50" value="" name="bname" data-validate="required:请输入书名" />
          <div class="tips"></div>
        </div>
      </div>
      <div class="form-group">
        <div class="label">
          <label>作者：</label>
        </div>
        <div class="field">
          <input type="text" class="input w50" value="" name="author"/>
          <div class="tips"></div>
        </div>
      </div>     
      <div class="form-group">
        <div class="label">
          <label>出版社：</label>
        </div>
        <div class="field">
          <input type="text" class="input w50" value="" name="publisher"/>
          <div class="tips"></div>
        </div>
      </div>
      <if condition="$iscid eq 1">
        <div class="form-group">
          <div class="label">
            <label>书籍类型：</label>
          </div>
          <div class="field">
            <select name="types" class="input w50">
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
          <textarea class="input" name="introduction" style=" height:90px;"></textarea>
          <div class="tips"></div>
        </div>
      </div>
      
      <div class="form-group">
        <div class="label">
          <label>图片：</label>
        </div>
        <div class="field">
          <input type="text" id="url1" name="image" class="input tips" style="width:25%; float:left;"  value=""  data-toggle="hover" data-place="right" data-image="" />
          <input type="button" class="button bg-blue margin-left" id="image1" value="+ 浏览上传"  style="float:left;">
          <div class="tipss">图片尺寸：156*208</div>
        </div>
      </div>  
     
      <div class="form-group">
        <div class="label">
          <label>价格：</label>
        </div>
        <div class="field">
          <input type="text" class="input w50" value="" name="price"/>
          <div class="tips"></div>
        </div>
      </div>
      
      <div class="form-group">
        <div class="label">
          <label>库存：</label>
        </div>
        <div class="field">
          <input type="text" class="input w50" value="" name="count"/>
          <div class="tips"></div>
        </div>
      </div>
	  <div class="form-group">
          <div class="label">
            <label>其他属性：</label>
          </div>
          <div class="field" style="padding-top:8px;"> 
            是否推荐 <input id="isvouch"  type="checkbox" name="recommend"/>     
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