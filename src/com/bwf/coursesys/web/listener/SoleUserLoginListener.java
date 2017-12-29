package com.bwf.coursesys.web.listener;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.bwf.coursesys.entity.Student;
import com.bwf.coursesys.web.servlet.LoginServlet;

/**
 *	用于确保用户的唯一登录
 */
@WebListener
public class SoleUserLoginListener implements HttpSessionListener {

	/**
	 * 用户和Session绑定关系
	 */
	public static final Map<Student, HttpSession> USER_SESSION = new HashMap<Student, HttpSession>();

	/**
	 * seeionId和用户的绑定关系
	 */
	public static final Map<String, Student> SESSIONID_USER = new HashMap<String, Student>();

	/**
	 * 日志
	 */
	private static Log log = LogFactory.getLog(LoginServlet.class);

	@Override
	public void sessionCreated(HttpSessionEvent se) {
		String sessionId = se.getSession().getId();
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent se) {
		String sessionId = se.getSession().getId();
		USER_SESSION.remove(SESSIONID_USER.remove(sessionId));
	}
	
}
