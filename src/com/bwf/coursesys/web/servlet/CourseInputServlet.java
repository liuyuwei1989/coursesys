package com.bwf.coursesys.web.servlet;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bwf.coursesys.exception.ExcelEception;
import com.bwf.coursesys.service.ICourseService;
import com.bwf.coursesys.service.IStuService;
import com.bwf.coursesys.service.impl.CourseService;
import com.bwf.coursesys.service.impl.StuService;
import com.jspsmart.upload.SmartUpload;
import com.jspsmart.upload.SmartUploadException;

import net.sf.json.JSONObject;

/**
 * Servlet implementation class CourseInputServlet
 */
@WebServlet("/courseInput.ad")
public class CourseInputServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CourseInputServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		try {
			
			SmartUpload smu = new SmartUpload();
			smu.initialize(this.getServletConfig(), request,response);
			smu.upload();
			com.jspsmart.upload.File file = smu.getFiles().getFile(0);
			String fullPath = "/tmpFile/"+file.getFilePathName();
			file.saveAs(fullPath);
			System.out.println(fullPath);
			InputStream input = new FileInputStream(request.getServletContext().getRealPath("/")+fullPath);
			
			ICourseService courseService = new CourseService();
			
			courseService.input(input);
			
			Map<String, String> map = new HashMap<String, String>();
			map.put("status", "success");
			out.print(JSONObject.fromObject(map));
			
		} catch (SmartUploadException e) {
			out.print("数据格式可能有误，请检查上传表格");
		} catch (SQLException e) {
			out.print("数据格式可能有误，请检查上传表格");
		} catch (IllegalStateException e) {
			out.print("数据可能有误，请检查上传表格");
		} catch (ExcelEception e) {
			out.print(e.getMessage());
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
