package com.bwf.coursesys.web.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bwf.coursesys.entity.Student;
import com.bwf.coursesys.service.ICourseService;
import com.bwf.coursesys.service.IStuService;
import com.bwf.coursesys.service.impl.CourseService;
import com.bwf.coursesys.service.impl.StuService;
/**
 * 用于选课的Servlet
 * @author hasee
 *
 */
@WebServlet("/chooseCourse.do")
public class ChooseCourseServlet extends HttpServlet {

	private static final long serialVersionUID = 7123358006872514287L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		ICourseService courseService = new CourseService();
		IStuService stuService = new StuService();
		Student stu = null;
		try {
			// 重新查询，如果选课完成则禁止重新选课操作。
			stu = stuService.login(((Student)session.getAttribute("loginStu")).getLoginName(),((Student)session.getAttribute("loginStu")).getPassword());
			if(stu.getFriCourseId()>=0&&stu.getTueCourseId()>=0){
				request.setAttribute("msg", "您的选课已完成，不能进行更改。");
				request.getRequestDispatcher("/WEB-INF/detail.jsp").forward(request, response);
				return;
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		int tueCourseId = Integer.parseInt(request.getParameter("tueCourseId"));
		int friCourseId = Integer.parseInt(request.getParameter("friCourseId"));
		try {
			int count = courseService.updateCourse(stu,tueCourseId,friCourseId);
			// 判断是否选课成功，如果成功则进入提示页面
			if(count==0){
				request.setAttribute("msg", "某些课程可能人数已满，请重新选择。");
				request.getRequestDispatcher("/WEB-INF/index.jsp").forward(request, response);
			}else{
				request.setAttribute("msg", "选课已完成！");
				request.getRequestDispatcher("/WEB-INF/detail.jsp").forward(request, response);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
