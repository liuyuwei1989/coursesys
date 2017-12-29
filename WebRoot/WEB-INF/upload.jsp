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
  <form enctype="multipart/form-data">
    <div class="form-group">
         <input id="studentFile" type="file" class="file" name="student" >
    </div>
  </form>
  <form enctype="multipart/form-data">
    <div class="form-group">
         <input id="courseInput" type="file" class="file" name="student" >
    </div>
  </form>
</div>
</body>
<script>
    $("#studentFile").fileinput({
    	language: 'zh',
        uploadUrl: 'studentInput.ad', // you must set a valid URL here else you will get an error
        allowedFileExtensions: ['xls', 'xlsx'],
        overwriteInitial: false,
        maxFileSize: 1000,
        maxFilesNum: 1,
        dropZoneTitle:"拖拽学生信息到这里，支持后缀名.xls及.xlsx",
        fileActionSettings : {
			showZoom: false,
		}
    });
    $("#courseInput").fileinput({
    	language: 'zh',
        uploadUrl: 'studentInput.ad', // you must set a valid URL here else you will get an error
        allowedFileExtensions: ['xls', 'xlsx'],
        overwriteInitial: false,
        maxFileSize: 1000,
        maxFilesNum: 1,
        dropZoneTitle:"拖拽课程信息到这里，支持后缀名.xls及.xlsx",
        fileActionSettings : {
			showZoom: false,
		}
    });
</script>
</html>
