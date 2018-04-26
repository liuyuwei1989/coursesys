package testpackage;

import java.sql.SQLException;
import org.junit.Test;

import com.bwf.coursesys.dao.impl.CourseDAO;

public class Test01 {
	@Test
	public void test() throws SQLException{
		CourseDAO courseDAO = new CourseDAO();
/*		List<Course> list = courseDAO.getCourseList('一', "周二");
		for(int i = 0 ;i <list.size();i++){
			System.out.println(list.get(i).getCourseName());
		}*/
		System.out.println(courseDAO.queryStuNumChoosed(2));
	}
	
}
