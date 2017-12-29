<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
<meta name="description" content="">
<meta name="author" content="">
<link rel="icon" href="icon/favicon.ico">

<!-- Bootstrap core CSS -->
<title>欢迎使用枫叶小学选课系统</title>
<link rel="stylesheet" href="css/bootstrap.min.css">
<script type="text/javascript" src="js/jquery-1.11.3.min.js"></script>
<script type="text/javascript" src="js/bootstrap.min.js"></script>

<!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
<link href="../../assets/css/ie10-viewport-bug-workaround.css" rel="stylesheet">

<!-- Custom styles for this template -->
<link href="css/signin.css" rel="stylesheet">

<!-- Just for debugging purposes. Don't actually copy these 2 lines! -->
<!--[if lt IE 9]><script src="../../assets/js/ie8-responsive-file-warning.js"></script><![endif]-->
<script src="../../assets/js/ie-emulation-modes-warning.js"></script>

<!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
<!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>
<body>
<div class="container">
  <form class="form-signin" action="admin" method="post">
    <h2 class="form-signin-heading">请您登录管理员账户！</h2>
    <label for="inputEmail" class="sr-only">Email address</label>
    <input
				name="userName" id="inputEmail" class="form-control"
				placeholder="请输入管理员账户" required autofocus>
    <label
				for="inputPassword" class="sr-only">密码</label>
    <input
				name="password" type="password" id="inputPassword" class="form-control"
				placeholder="请您输入密码" required>
    <button class="btn btn-lg btn-primary btn-block" type="submit">登入</button>
    <div class="form-group">
      <div class="col-sm-12">
        <p class="form-control-static"><font color="#F5070B">${msg}</font></p>
      </div>
    </div>
  </form>
</div>
<!-- /container -->
</body>
</html>
