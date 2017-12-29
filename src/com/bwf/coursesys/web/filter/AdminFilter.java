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
 * 当执行管理员相关操作时，确认目前登录用户是否是管理员。
 */
@WebFilter(filterName="AdminFilter",urlPatterns="*.ad")
public class AdminFilter implements Filter {

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpSession session = ((HttpServletRequest)request).getSession();
		Student admin = (Student)session.getAttribute("admin");
		if(admin==null||!"admin".equals(admin.getName())){
			request.setAttribute("msg", "管理员账户未登录");
			request.getRequestDispatcher("admin.jsp").forward(request, response);
		}else{
			chain.doFilter(request, response);
		}
	}

	@Override
	public void destroy() {
		
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		
	}


}
