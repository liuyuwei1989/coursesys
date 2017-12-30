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
<base href="<%=basePath%>">
<title>欢迎使用枫叶小学选课系统</title>
<link href="<%=request.getContextPath()%>/css/fileinput.min.css" media="all" rel="stylesheet" type="text/css"/>
<link href="<%=request.getContextPath()%>/themes/explorer/theme.min.css" media="all" rel="stylesheet" type="text/css"/>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/bootstrap.min.css">
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.11.3.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/bootstrap.min.js"></script>
<script src="<%=request.getContextPath()%>/js/plugins/sortable.min.js" type="text/javascript"></script>
<script src="<%=request.getContextPath()%>/js/fileinput.min.js" type="text/javascript"></script>
<script src="<%=request.getContextPath()%>/js/locales/zh.js" type="text/javascript"></script>
<script src="<%=request.getContextPath()%>/themes/explorer/theme.min.js" type="text/javascript"></script>

<link href="../css/fileinput.min.css" media="all" rel="stylesheet" type="text/css"/>
<link href="../themes/explorer/theme.min.css" media="all" rel="stylesheet" type="text/css"/>
<link rel="stylesheet" type="text/css" href="../css/bootstrap.min.css">
<script type="text/javascript" src="../js/jquery-1.11.3.min.js"></script>
<script type="text/javascript" src="../js/bootstrap.min.js"></script>
<script src="../js/plugins/sortable.min.js" type="text/javascript"></script>
<script src="../js/fileinput.min.js" type="text/javascript"></script>
<script src="../js/locales/zh.js" type="text/javascript"></script>
<script src="../themes/explorer/theme.min.js" type="text/javascript"></script>
</head>

<body>

<div class="container kv-main">
	<h1>下载操作</h1>
	<h4>学生选课详情下载</h4>
	<form action="stuCourseOut.ad">
	<button id="outPut" type="submit" class="btn btn-primary btn-lg btn-block" onclick="StuCourseOutServlet.ad">点击此处进行下载</button>
	</form>
</div>
<div class="container kv-main">
	<h1>上传操作</h1>
	<h4>学生信息上传</h4>
	<h5><font color="red">上传后会清空全部学生信息，请确认您导入的是全部学生信息</font></h5>
	<h5><font color="red">请确认您要导入的表位于Excel的第一张工作表，并且包含表头，列按以下顺序进行排序</font></h5>
	<h5><font color="red">序号，姓名，班级，登录账号，登录密码</font></h5>
  <form enctype="multipart/form-data">
    <div class="form-group">
         <input id="studentFile" type="file" class="file" name="student" >
    </div>
  </form>
  	<h4>课程信息上传</h4>
  	<h5><font color="red">上传后会重置全部选课信息，并清空存在的课程，请确认后上传课程信息</font></h5>
  	<h5><font color="red">请确认您要导入的表位于Excel的第一张工作表，并且包含表头，列按以下顺序进行排序</font></h5>
	<h5><font color="red">序号，上课时间，年级，课程名称，费用，招生人数</font></h5>
  <form enctype="multipart/form-data">
    <div class="form-group">
         <input id="courseInput" type="file" class="file" name="course" >
    </div>
  </form>
</div>

</body>
<script>
    $("#studentFile").fileinput({
    	language: 'zh',
        uploadUrl: 'studentInput.ad', // you must set a valid URL here else you will get an error
        allowedFileExtensions: ['xls'],
        overwriteInitial: false,
        maxFileSize: 1000,
        maxFilesNum: 1,
        dropZoneTitle:"拖拽学生信息到这里，仅支持后缀名.xls",
        fileActionSettings : {
			showZoom: false,
		}
    });
    $("#courseInput").fileinput({
    	language: 'zh',
        uploadUrl: 'courseInput.ad', // you must set a valid URL here else you will get an error
        allowedFileExtensions: ['xls'],
        overwriteInitial: false,
        maxFileSize: 1000,
        maxFilesNum: 1,
        dropZoneTitle:"拖拽课程信息到这里，仅支持后缀名.xls",
        fileActionSettings : {
			showZoom: false,
		}
    });
</script>
</html>
