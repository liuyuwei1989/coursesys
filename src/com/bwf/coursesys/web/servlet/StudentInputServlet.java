package com.bwf.coursesys.web.servlet;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.RandomAccessFile;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import com.bwf.coursesys.exception.ExcelEception;
import com.bwf.coursesys.service.IStuService;
import com.bwf.coursesys.service.impl.StuService;
import com.jspsmart.upload.SmartUpload;
import com.jspsmart.upload.SmartUploadException;

import net.sf.json.JSONObject;
@WebServlet("/studentInput.ad")
public class StudentInputServlet extends HttpServlet {

	private static final long serialVersionUID = 1108678719346962486L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
			
			IStuService stuService = new StuService();
			
			stuService.input(input);
			
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

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
