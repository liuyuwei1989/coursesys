package com.bwf.coursesys.web.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.bwf.coursesys.entity.Student;

/**
 * 用于过滤未登录非法请求
 */
@WebFilter(filterName="LoginFilter",urlPatterns="*.do")
public class LoginFilter implements Filter {

	/**
	 * 如果判断用户未登录，曾进行屏蔽
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpSession session = ((HttpServletRequest)request).getSession();
		Student stu = (Student)session.getAttribute("loginStu");
		if(stu==null||"admin".equals(stu.getName())){
			((HttpServletRequest)request).setAttribute("msg", "请先登录。");
			request.getRequestDispatcher("login.jsp").forward(request, response);
		}else{
			chain.doFilter(request, response);
		}
	}

	@Override
	public void destroy() {
		
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		
	}
	public LoginFilter(){
	}

}
