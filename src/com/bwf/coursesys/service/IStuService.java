package com.bwf.coursesys.service;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.bwf.coursesys.entity.Student;
import com.bwf.coursesys.exception.ExcelEception;

public interface IStuService {

	Student login(String userName, String userPasswd) throws SQLException;

	void input(InputStream input) throws SQLException, IOException, ExcelEception;

	void outPut(HSSFWorkbook workbook) throws SQLException;


}
