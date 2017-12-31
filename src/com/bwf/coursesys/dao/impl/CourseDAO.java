package com.bwf.coursesys.dao.impl;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.List;

import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

import com.bwf.coursesys.dao.BaseDAO;
import com.bwf.coursesys.dao.ICourseDAO;
import com.bwf.coursesys.entity.Course;
import com.bwf.coursesys.entity.Student;
import com.bwf.coursesys.exception.ExcelEception;

public class CourseDAO extends BaseDAO implements ICourseDAO {
	/**
	 * 搜索结果时，屏蔽已选满的课程
	 */
	@Override
	public List<Course> getCourseList(Character grade, String week) throws SQLException {
		String sql = "select * from course where grade like '%"+grade+"%' and week=? and choosable!=0";
		return qRunner.query(sql, new BeanListHandler<Course>(Course.class),week);
	}
	/**
	 * 用于查询课程学费
	 */
	@Override
	public String queryCost(int courseId) throws SQLException {
		String sql = "select * from course where id=? and choosable=1";
		return qRunner.query(sql, new BeanHandler<Course>(Course.class),courseId).getCost();
	}
	/**
	 * 用于查询课程是否可选
	 */
	@Override
	public int queryChoosable(int courseId) throws SQLException{
		String sql = "select choosable from course where id=?";
		return (Integer)qRunner.query(sql, new ScalarHandler("choosable"),courseId);
	}
	/**
	 * 用于查询课程允许选择的人数
	 */
	@Override
	public int queryStuNumAllow(int courseId) throws SQLException{
		String sql = "select stuNum from course where id=?";
		return (Integer)qRunner.query(sql, new ScalarHandler("stuNum"),courseId);
	}
	/**
	 * 用于查询课程已选择的人数
	 */
	@Override
	public int queryStuNumChoosed(int courseId) throws SQLException{
		String sql = "select count(*) from student where tueCourseId=? or friCourseId=?";
		return Integer.parseInt(qRunner.query(sql, new ScalarHandler("count(*)"),courseId,courseId).toString());
	}
	/**
	 * 用于选择课程
	 */
	@Override
	public int chooseCourse(Student stu, int tueCourseId, int friCourseId) throws SQLException {
		String sql = "update student set tueCourseId=?,friCourseId=? where id=?";
		return qRunner.update(sql, tueCourseId,friCourseId,stu.getId());
	}
	/**
	 * 用于锁定课程
	 */
	@Override
	public void lockCourse(int courseId) throws SQLException {
		String sql = "update course set choosable=0 where id=?";
		qRunner.update(sql,courseId);
	}
	/**
	 * @throws SQLException 
	 * @throws IOException 
	 * @throws ExcelEception 
	 * 
	 */
	@Override
	public void input(InputStream input) throws SQLException, IOException, ExcelEception {
		Connection conn = qRunner.getDataSource().getConnection();
		// 开启事务
		conn.setAutoCommit(false);

		String updateSql = "update student set tueCourseId=null,friCourseId=null where 1=1";
		
		String deleteSql = "delete from course where 1=1";

		String insertSql = "INSERT INTO course(`week`,grade,courseName,cost,stuNum) values(?,?,?,?,?);";
		// 重置选课信息
		qRunner.update(updateSql);
		// 清除原表内容
		qRunner.update(conn, deleteSql);

		POIFSFileSystem fs = new POIFSFileSystem(input);
		HSSFWorkbook wb = new HSSFWorkbook(fs);
		HSSFSheet sheet = wb.getSheetAt(0);
		// 用于格式化数字类型
		DecimalFormat df = new DecimalFormat("0");
		// 检查表头是否符合要求
				HSSFRow head = sheet.getRow(0);
				if(!head.getCell(0).toString().equals("序号")||
						!head.getCell(1).toString().equals("上课时间")||
						!head.getCell(2).toString().equals("年纪")||
						!head.getCell(3).toString().equals("课程名称")||
						!head.getCell(4).toString().equals("费用")||
						!head.getCell(5).toString().equals("招生人数")) {
					wb.close();
					throw new ExcelEception("表头不符合要求，请检查是否按以下排序\n序号，姓名，班级，登录账号，登录密码");
				}
		// 逐行提取，跳过第一行
		for (int i = 1; i < sheet.getPhysicalNumberOfRows(); i++) {
			HSSFRow row = sheet.getRow(i);
			if (row.getCell(1) != null && row.getCell(2) != null && row.getCell(3) != null && row.getCell(4) != null) {

				String week = row.getCell(1).toString();

				String grade = row.getCell(2).toString();

				String courseName = row.getCell(3).toString();

				String cost = row.getCell(4).toString();
				
				int stuNum = Integer.parseInt(df.format(row.getCell(5).getNumericCellValue()));

				qRunner.update(conn, insertSql, week, grade, courseName, cost,stuNum);
			}
		}
		wb.close();
		conn.commit();
	}
}
