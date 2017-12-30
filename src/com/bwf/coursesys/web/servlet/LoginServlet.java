package com.bwf.coursesys.web.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bwf.courses.utils.SessionAddLock;
import com.bwf.coursesys.entity.Course;
import com.bwf.coursesys.entity.Student;
import com.bwf.coursesys.service.ICourseService;
import com.bwf.coursesys.service.IStuService;
import com.bwf.coursesys.service.impl.CourseService;
import com.bwf.coursesys.service.impl.StuService;
import com.bwf.coursesys.web.listener.SoleUserLoginListener;

import net.sf.json.JSONArray;

/**
 * 学生登录所需要的Servlet
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {

	private static final long serialVersionUID = 8555457195222477709L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Student stu = new Student();
		HttpSession session = request.getSession();

		// 获取登录的用户名和密码
		String userName = request.getParameter("userName");
		String userPasswd = request.getParameter("password");

		// 判断是否登录成功
		IStuService stuService = new StuService();
		try {
			stu = stuService.login(userName, userPasswd);

			if (stu != null) {
				// 管理员禁止使用此页面进行登录
				if ("admin".equals(stu.getName())) {
					request.setAttribute("msg", "登录失败，请核对账户密码");
					request.getRequestDispatcher("login.jsp").forward(request, response);
					return;
				}

				// 同步锁账号多处登录检查
				/*synchronized (SessionAddLock.getSessionAddLock()) {
					// session 判断此用户是否登录过
					if (SoleUserLoginListener.USER_SESSION.containsKey(stu)) {
						request.setAttribute("msg", "此用户已登录");
						request.getRequestDispatcher("login.jsp").forward(request, response);
						SessionAddLock.getSessionAddLock().notify();
						return;
					} else {
						// session 如果登录成功 则添加到对应的Map集合中。
						SoleUserLoginListener.USER_SESSION.put(stu, session);
						SoleUserLoginListener.SESSIONID_USER.put(session.getId(), stu);
						session.setAttribute("loginStu", stu);
					}
				}*/
				session.setAttribute("loginStu", stu);
				// 判断数据库中是否已完成选课，如果完成则进入提示选课完成的界面
				if (stu.getFriCourseId() <= 0 && stu.getTueCourseId() <= 0) {
					queryCourseList(request,stu);
					request.getRequestDispatcher("/WEB-INF/index.jsp").forward(request, response);
				} else {
					request.setAttribute("msg", "您的选课已完成");
					request.getRequestDispatcher("/WEB-INF/detail.jsp").forward(request, response);
				}
			} else {
				request.setAttribute("msg", "登录失败，请核对账户密码");
				request.getRequestDispatcher("login.jsp").forward(request, response);
			}
		} catch (SQLException e) {
			request.setAttribute("msg", "请求超时，请重新登录");
			request.getRequestDispatcher("login.jsp").forward(request, response);
			e.printStackTrace();
		}

	}

	private void queryCourseList(HttpServletRequest request, Student stu) {
		ICourseService courseService = new CourseService();
		try {
			List<Course> tueCourses = courseService.getTueCourseList(stu);
			List<Course> friCourses = courseService.getFriCourseList(stu);
			request.setAttribute("tueCourses", tueCourses);
			request.setAttribute("friCourses", friCourses);

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}


	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
