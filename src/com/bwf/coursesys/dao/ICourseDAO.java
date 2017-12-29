package com.bwf.coursesys.dao;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.List;

import com.bwf.coursesys.entity.Course;
import com.bwf.coursesys.entity.Student;

public interface ICourseDAO {

	List<Course> getCourseList(Character grade, String string) throws SQLException;

	String queryCost(int courseId) throws SQLException;

	int queryChoosable(int courseId) throws SQLException;

	int queryStuNumAllow(int courseId) throws SQLException;

	int queryStuNumChoosed(int courseId) throws SQLException;

	int chooseCourse(Student stu, int tueCourseId, int friCourseId) throws SQLException;

	void lockCourse(int tueCourseId) throws SQLException;

	void input(InputStream input) throws SQLException, IOException;

}
