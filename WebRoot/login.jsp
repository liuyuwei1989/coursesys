<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<base href="<%=basePath%>">
<title>欢迎使用枫叶小学选课系统</title>
<link rel="stylesheet" href="css/bootstrap.min.css">
<script type="text/javascript" src="js/jquery-1.11.3.min.js"></script>
<script type="text/javascript" src="js/bootstrap.min.js"></script>
</head>
<body>
<a href="admin.jsp">管理员登录</a>
<!--设置提示用户，使用手机时需横屏使用-->
	<div class="hidden-lg hidden-md"><strong><font color="#F90409">建议您将手机设置横屏模式访问此页面！</font></strong></div>
<!--背景-->
<div class="col-lg-4 col-lg-offset-4 col-sm-12 col-xs-12" style="position: absolute;">
	<img src="image/loginImage.jpg"/>
</div>
<!--用于电脑端访问的Form-->
<div class="col-lg-4 col-lg-offset-4 hidden-xs " style="position: absolute;margin-top:120px">
  <form class="form-horizontal" action="login" method="post">
  <div class="form-group" >
    <label for="inputEmail3" class="col-sm-2 control-label">学号</label>
    <div class="col-sm-6">
      <input type="text" class="form-control" id="inputEmail3" name="userName" placeholder="请输入学号" required autofocus>
    </div>
    <div class="col-sm-4"></div>
  </div>
  <div class="form-group">
    <label for="inputPassword3" class="col-sm-2 control-label">密码</label>
    <div class="col-sm-6">
      <input type="password" class="form-control" id="inputPassword3" name="password" placeholder="请输入密码" required>
    </div>
    <div class="col-sm-4"></div>
  </div>
  <div class="form-group">
    <div class="col-sm-offset-2 col-sm-6">
      <button type="submit" class="btn btn-default">登录</button>
    </div>
    <div class="col-sm-4">
      <font color="#F70B0F">${msg }</font>
    </div>
  </div>
</form>
</div>
<!--用于移动端访问的Form-->
<div class="hidden-lg hidden-sm hidden-md col-xs-10 col-xs-offset-2" style="position: absolute;margin-top:100px;">
<form class="form-inline" action="login" method="post">
  <font><strong>学号：</strong><font><input type="text" class="input-lg" id="exampleInputEmail3" name="userName" placeholder="请输入学号"><br/>
   <font><strong>密码：</strong></font><input type="password" class="input-lg" id="exampleInputPassword3" name="password" placeholder="请输入密码"><br/>
  <div class="col-xs-offset-1 col-xs-7">
      <button type="submit" class="btn btn-default btn-lg">登录</button>
    </div>
    <div class="col-xs-4">
      <font color="#F70B0F">${msg }</font>
    </div>
</form>
</div>
</body>
</html>
