package com.bwf.coursesys.web.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bwf.coursesys.entity.Course;
import com.bwf.coursesys.entity.Student;
import com.bwf.coursesys.service.ICourseService;
import com.bwf.coursesys.service.impl.CourseService;

import net.sf.json.JSONArray;
/**
 * 用于显示星期五的可选的课程列表
 */
@WebServlet("/friCourse.do")
public class FriCourseListServlet extends HttpServlet {

	private static final long serialVersionUID = -8232878745604654556L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		Student stu = (Student)session.getAttribute("loginStu");
		ICourseService courseService = new CourseService();
		try {
			List<Course> list = courseService.getFriCourseList(stu);
			out.print(JSONArray.fromObject(list));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
