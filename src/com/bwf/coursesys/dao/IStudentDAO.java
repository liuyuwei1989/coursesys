package com.bwf.coursesys.dao;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.List;

import com.bwf.courses.utils.StudentCourseInfo;
import com.bwf.coursesys.entity.Student;
import com.bwf.coursesys.exception.ExcelEception;

public interface IStudentDAO {

	Student login(String userName, String userPasswd) throws SQLException;

	void input(InputStream input) throws SQLException, IOException, ExcelEception;

	List<StudentCourseInfo> queryAll() throws SQLException;


}
