package com.bwf.coursesys.service.impl;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.List;

import com.bwf.courses.utils.ChooseLock;
import com.bwf.coursesys.dao.ICourseDAO;
import com.bwf.coursesys.dao.impl.CourseDAO;
import com.bwf.coursesys.entity.Course;
import com.bwf.coursesys.entity.Student;
import com.bwf.coursesys.service.ICourseService;

public class CourseService implements ICourseService {
	
	private ICourseDAO courseDAO = new CourseDAO();
	
	@Override
	public List<Course> getTueCourseList(Student stu) throws SQLException{
		Character grade = stu.getClassName().charAt(0); 
		return courseDAO.getCourseList(grade,"周二");
		
	}

	@Override
	public List<Course> getFriCourseList(Student stu) throws SQLException {
		Character grade = stu.getClassName().charAt(0); 
		return courseDAO.getCourseList(grade,"周五");
	}

	@Override
	public String queryCost(int courseId) throws SQLException {
		return courseDAO.queryCost(courseId);
	}

	@Override
	public int updateCourse(Student stu, int tueCourseId, int friCourseId) throws SQLException {
		//利用单例模式，同步锁这段代码，确保课程人数不会超标。
		synchronized (ChooseLock.getUpdateLock()) {
			//判断当前课程是否可选
			if(courseDAO.queryChoosable(friCourseId)==0
					||courseDAO.queryChoosable(friCourseId)==0){
				return 0;
			}
			int count = courseDAO.chooseCourse(stu, tueCourseId, friCourseId);
			//插入之后判断是人数是否已满
			if(courseDAO.queryStuNumChoosed(tueCourseId)>=courseDAO.queryStuNumAllow(tueCourseId)){
				courseDAO.lockCourse(tueCourseId);
			}
			if(courseDAO.queryStuNumChoosed(friCourseId)>=courseDAO.queryStuNumAllow(friCourseId)){
				courseDAO.lockCourse(friCourseId);
			}
			return count;
		}
	}

	@Override
	public void input(InputStream input) throws SQLException, IOException {
		courseDAO.input(input);
	}


}
