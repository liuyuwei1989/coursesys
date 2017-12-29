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
		try {
			PrintWriter out = response.getWriter();
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
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
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
