package com.bwf.coursesys.service;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.List;

import com.bwf.coursesys.entity.Course;
import com.bwf.coursesys.entity.Student;
import com.bwf.coursesys.exception.ExcelEception;

public interface ICourseService {

	List<Course> getTueCourseList(Student stu) throws SQLException;

	List<Course> getFriCourseList(Student stu) throws SQLException;

	String queryCost(int courseId) throws SQLException;

	int updateCourse(Student stu, int tueCourseId, int friCourseId) throws SQLException;

	void input(InputStream input) throws SQLException, IOException, ExcelEception;

}
