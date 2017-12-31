package com.bwf.coursesys.service.impl;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;

import com.bwf.courses.utils.StudentCourseInfo;
import com.bwf.coursesys.dao.IStudentDAO;
import com.bwf.coursesys.dao.impl.StudentDAO;
import com.bwf.coursesys.entity.Student;
import com.bwf.coursesys.exception.ExcelEception;
import com.bwf.coursesys.service.IStuService;

public class StuService implements IStuService {
	
	@Override
	public Student login(String userName, String userPasswd) throws SQLException {
		return studentDAO.login(userName,userPasswd);
	}

	private IStudentDAO studentDAO = new StudentDAO();

	@Override
	public void input(InputStream input) throws SQLException, IOException, ExcelEception {
		studentDAO.input(input);
	}

	@Override
	public void outPut(HSSFWorkbook workbook) throws SQLException {
		List<StudentCourseInfo> stuList = studentDAO.queryAll();
		HSSFSheet sheet = workbook.createSheet("学生选课基本详情表");
		// 创建标题行
		HSSFRow headRow = sheet.createRow(0);
		HSSFCell headCell0 = headRow.createCell(0, CellType.STRING);
		headCell0.setCellValue("序号");
		HSSFCell headCell1 = headRow.createCell(1, CellType.STRING);
		headCell1.setCellValue("姓名");
		HSSFCell headCell2 = headRow.createCell(2, CellType.STRING);
		headCell2.setCellValue("班级");
		HSSFCell headCell3 = headRow.createCell(3, CellType.STRING);
		headCell3.setCellValue("学号");
		HSSFCell headCell4 = headRow.createCell(4, CellType.STRING);
		headCell4.setCellValue("周二选课情况");
		HSSFCell headCell5 = headRow.createCell(5, CellType.STRING);
		headCell5.setCellValue("周三选课情况");
		
		//插入查询内容
		for(int i = 0 ;i<stuList.size();i++){
			HSSFRow row = sheet.createRow(i+1);
			StudentCourseInfo info = stuList.get(i);
			HSSFCell cell0 = row.createCell(0, CellType.STRING);
			cell0.setCellValue(i+1+"");
			HSSFCell cell1 = row.createCell(1, CellType.STRING);
			cell1.setCellValue(info.getName());
			HSSFCell cell2 = row.createCell(2, CellType.STRING);
			cell2.setCellValue(info.getClassName());
			HSSFCell cell3 = row.createCell(3, CellType.STRING);
			cell3.setCellValue(info.getLoginName());
			HSSFCell cell4 = row.createCell(4, CellType.STRING);
			cell4.setCellValue(info.getTueCourseName());
			HSSFCell cell5 = row.createCell(5, CellType.STRING);
			cell5.setCellValue(info.getFriCourseName());
		}
	}
}
