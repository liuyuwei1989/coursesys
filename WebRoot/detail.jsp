<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<base href="<%=basePath%>">

<title>欢迎使用枫叶小学选课系统</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<link rel="stylesheet" type="text/css" href="../css/bootstrap.min.css">
<script type="text/javascript" src="../js/jquery-1.11.3.min.js"></script>
<script type="text/javascript" src="../js/bootstrap.min.js"></script>
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/css/bootstrap.min.css">
<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/jquery-1.11.3.min.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/bootstrap.min.js"></script>
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
</head>

<body>
<!--设置提示用户，使用手机时需横屏使用-->
	<div class="hidden-lg hidden-md"><strong><font color="#F90409">建议您将手机设置横屏模式访问此页面！</font></strong></div>
<!--设置背景颜色-->
	<div class="col-lg-4 col-lg-offset-4 col-xs-12"
		style="position: absolute;">
		<img src="<%=request.getContextPath()%>/image/indexImage.jpg" />
	</div>
	<!--设置form所在div-->
	<div class="col-lg-4 col-lg-offset-4 col-xs-12"
		style="position: absolute;margin-top: 10px">
		<div style="margin-left: 120px">
			您的选课已完成，不能进行更改。
		</div>
	</div>
</body>
</html>
