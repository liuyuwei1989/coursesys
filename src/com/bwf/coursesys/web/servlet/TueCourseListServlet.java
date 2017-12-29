package com.bwf.coursesys.web.servlet;
/**
 * 用于Ajax的方法生成星期二的选课清单
 */
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
@WebServlet("/tueCourse.do")
public class TueCourseListServlet extends HttpServlet {

	private static final long serialVersionUID = 7941121214386372128L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		PrintWriter out = response.getWriter();
		Student stu = (Student)session.getAttribute("loginStu");
		ICourseService courseService = new CourseService();
		try {
			List<Course> list = courseService.getTueCourseList(stu);
			out.print(JSONArray.fromObject(list));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
