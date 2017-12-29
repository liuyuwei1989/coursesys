package com.bwf.coursesys.web.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bwf.coursesys.service.ICourseService;
import com.bwf.coursesys.service.impl.CourseService;

import net.sf.json.JSONObject;
/**
 * 告知用户此课程花费，并告知客户目前此课程是否被选满;
 * @author hasee
 *
 */
@WebServlet("/queryCost.do")
public class QueryCostServlet extends HttpServlet {

	private static final long serialVersionUID = 8254260913378109595L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		ICourseService courseService = new CourseService();
		int courseId = Integer.parseInt(request.getParameter("courseId"));
		//如果用户在生成网页之后，显示的某些课程已选满，对用户进行提示
		try {
			String cost = courseService.queryCost(courseId);
			Map<String, String> map = new HashMap<String, String>();
			if(cost==null){
				cost="课程已报满";
			}
			map.put("cost", cost);
			out.print(JSONObject.fromObject(map));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}
