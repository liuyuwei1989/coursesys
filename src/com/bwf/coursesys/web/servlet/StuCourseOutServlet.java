package com.bwf.coursesys.web.servlet;

import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.bwf.coursesys.service.IStuService;
import com.bwf.coursesys.service.impl.StuService;

/**
 * Servlet implementation class StuCourseOutServlet
 */
@WebServlet("/stuCourseOut.ad")
public class StuCourseOutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public StuCourseOutServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			OutputStream out = response.getOutputStream();
			response.setHeader("Content-disposition", "attachment;filename=" + URLEncoder.encode("info.xls", "UTF-8"));
			response.setContentType("application/msexcel;charset=UTF-8");
			HSSFWorkbook workbook = new HSSFWorkbook();
			IStuService stuService = new StuService();

			stuService.outPut(workbook);

			workbook.write(out);
			workbook.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
