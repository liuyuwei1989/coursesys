package com.bwf.coursesys.dao.impl;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import com.bwf.courses.utils.StudentCourseInfo;
import com.bwf.coursesys.dao.BaseDAO;
import com.bwf.coursesys.dao.IStudentDAO;
import com.bwf.coursesys.entity.Student;

public class StudentDAO extends BaseDAO implements IStudentDAO {

	@Override
	public Student login(String userName, String userPasswd) throws SQLException {
		String sql = "select * from student where loginName=? and password=?";
		return qRunner.query(sql, new BeanHandler<Student>(Student.class), userName, userPasswd);
	}
/**
 * 用于导入学生信息
 */
	@Override
	public void input(InputStream input) throws SQLException, IOException {

		Connection conn = qRunner.getDataSource().getConnection();
		// 开启事务
		conn.setAutoCommit(false);

		String deleteSql = "delete from student where 1=1";

		String insertSql = "insert into student(name,className,loginName,password) values(?,?,?,?)";

		// 清除原表内容
		qRunner.update(conn, deleteSql);

		// 配置管理员用户信息
		qRunner.update(conn, insertSql, "admin", "admin", "admin", "admin");

		POIFSFileSystem fs = new POIFSFileSystem(input);
		HSSFWorkbook wb = new HSSFWorkbook(fs);
		HSSFSheet sheet = wb.getSheetAt(0);
		// 用于格式化数字类型
		DecimalFormat df = new DecimalFormat("0");
		// 逐行提取，跳过第一行
		for (int i = 1; i < sheet.getPhysicalNumberOfRows(); i++) {
			HSSFRow row = sheet.getRow(i);
			if (row.getCell(1) != null && row.getCell(2) != null && row.getCell(3) != null && row.getCell(4) != null) {

				String name = row.getCell(1).toString();

				String className = row.getCell(2).toString();

				String loginName = df.format(row.getCell(3).getNumericCellValue());

				String password = df.format(row.getCell(4).getNumericCellValue());

				qRunner.update(conn, insertSql, name, className, loginName, password);
			}
		}
		wb.close();
		conn.commit();
	}
/**
 * 用于导出学生信息
 * @throws SQLException 
 */
	@Override
	public List<StudentCourseInfo> queryAll() throws SQLException {
		
		String sql = "SELECT\n" +
				"	A.`name`,\n" +
				"	A.className,\n" +
				"	A.loginName,\n" +
				"	A.tueCourseName,\n" +
				"	course.courseName friCourseName\n" +
				"FROM\n" +
				"	(\n" +
				"		SELECT\n" +
				"			student.`name` `name`,\n" +
				"			student.className `className`,\n" +
				"			student.loginName `loginName`,\n" +
				"			student.friCourseId `fId`,\n" +
				"			course.courseName `tueCourseName`\n" +
				"		FROM\n" +
				"			student\n" +
				"		LEFT JOIN course ON student.tueCourseId = course.id\n" +
				"		WHERE `name` != 'admin'\n" +
				"	) A\n" +
				"LEFT JOIN course ON A.fId = course.id;";
		
		return qRunner.query(sql, new BeanListHandler<StudentCourseInfo>(StudentCourseInfo.class));
	}

}
