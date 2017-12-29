package com.bwf.coursesys.web.servlet;
/**
 * 用于管理员登录
 */
import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bwf.courses.utils.SessionAddLock;
import com.bwf.coursesys.entity.Student;
import com.bwf.coursesys.service.IStuService;
import com.bwf.coursesys.service.impl.StuService;
import com.bwf.coursesys.web.listener.SoleUserLoginListener;

@WebServlet("/admin")
public class AdminServlet extends HttpServlet {
	
	private static final long serialVersionUID = -767130381948413203L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		IStuService stuService = new StuService();
		HttpSession session = request.getSession();
		String userName = request.getParameter("userName");
		String userPasswd = request.getParameter("password");
		try {
			Student admin = stuService.login(userName, userPasswd);
			// 如果登录为空，或者尝试登录非管理员账户，则直接返回账号密码错误
			if(admin==null||!"admin".equals(admin.getName())){
				request.setAttribute("msg", "账号密码错误");
				request.getRequestDispatcher("admin.jsp").forward(request, response);
			}else{
				synchronized (SessionAddLock.getSessionAddLock()) {
					// session 判断此用户是否登录过
					if (SoleUserLoginListener.USER_SESSION.containsKey(admin)) {
						request.setAttribute("msg", "此用户已登录");
						request.getRequestDispatcher("admin.jsp").forward(request, response);
						SessionAddLock.getSessionAddLock().notify();
						return;
					} else {
						// session 如果登录成功 则添加到对应的Map集合中。
						SoleUserLoginListener.USER_SESSION.put(admin, session);
						SoleUserLoginListener.SESSIONID_USER.put(session.getId(), admin);
					}
					session.setAttribute("admin", admin);
				}
				session.setAttribute("admin", admin);
				request.getRequestDispatcher("/WEB-INF/adminIndex.jsp").forward(request, response);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
