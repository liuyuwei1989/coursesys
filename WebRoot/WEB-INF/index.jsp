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
			<form id="chooseCourse" class="form-horizontal" action="chooseCourse.do" method="post">
				<p>
					<strong>姓名：</strong>${loginStu.name }</p>
				<p>
					<strong>班级：</strong>${loginStu.className }</p>
				<p>
					<strong>学号：</strong>${loginStu.loginName }</p>
					<p>
					<strong>请选择课程：</strong></p>
				<div class="form-group">
					<div class="col-lg-12 col-sm-12 col-xs-12 col-md-12">
						周二：<select name="tueCourseId" class="input-sm" style="width:30%">
							<option value=-1>请选择</option>
							<c:forEach var="tueCourse" items="${tueCourses }">
								<option value='${tueCourse.id }'>${tueCourse.courseName }</option>
							</c:forEach>
						</select><input class="input-sm" style="border:0px;width:40%;color:red;" readonly="readonly" />
					</div>
				</div>
				<div class="form-group">
					<div class="col-lg-12 col-sm-12 col-xs-12 col-md-12">
						周五：<select name="friCourseId" class="input-sm" style="width:30%;">
							<option value=-1>请选择</option>
							<c:forEach var="friCourse" items="${friCourses }">
								<option value='${friCourse.id }'>${friCourse.courseName }</option>
							</c:forEach>
						</select><input class="input-sm" style="border:0px;width:40%;color: red;" readonly="readonly" />
					</div>
				</div>	
			</form>
			<button class="btn-info" id="submitBtn">提交选课</button><font color="#F70B0F">${msg }</font>
		</div>
	</div>
<script type="text/javascript">
	/*用于返回可选课程菜单*/
	/* $(document).ready(function(){
		$.ajax({
			url : 'tueCourse.do',
			dataType : 'json',
			success : function(data){
				for(var index in data){
					var course = data[index];
					var id = course['id'];
					var courseName = course['courseName'];
					$("select[name='tueCourseId']").append("<option value="+id+">"+courseName+"</option>");
				}
			}
		})
		$.ajax({
			url : 'friCourse.do',
			dataType : 'json',
			success : function(data){
				for(var index in data){
					var course = data[index];
					var id = course['id'];
					var courseName = course['courseName'];
					$("select[name='friCourseId']").append("<option value="+id+">"+courseName+"</option>");
				}
			}
		})
	})   */
	/*用于显示课程花费*/
	$("select").change(function(){
		var id = $(this).val();
		var input = $(this).next('input');
		$.ajax({
			url : 'queryCost.do' ,
			data : "courseId=" + id ,
			type : "post" ,
			dataType : 'json',
			success: function(data){
				input.val(data['cost']);
			}
		})
	})
	/*用于强制用户必须选定课程*/
	$("#submitBtn").click(function(){
		if($("select[name='tueCourseId']").val()==-1){
			alert("请选择周二的课程！");
			return;
		}
		if($("select[name='friCourseId']").val()==-1){
			alert("请选择周五的课程！");
			return;
		}
		var msg = "周二:" + $("select[name='tueCourseId']>option[value='"+$("select[name='tueCourseId']").val()+"']").text()
		+"\n周五:" + $("select[name='friCourseId']>option[value='"+$("select[name='friCourseId']").val()+"']").text();
		var r = confirm("确认选课？\n"+msg);
		if(r){
			$("#chooseCourse").submit();
		}else{
			return;
		}
	})
	
		
	
</script>
</body>
</html>
