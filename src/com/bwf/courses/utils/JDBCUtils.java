package com.bwf.courses.utils;

import javax.sql.DataSource;

import org.apache.commons.dbutils.QueryRunner;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class JDBCUtils {
	
	private static DataSource ds = new ComboPooledDataSource();
	
	static{
		System.out.println("数据库连接池的代码执行了");
	}
	
	private JDBCUtils() {
	
	}
	
	public static QueryRunner getQueryRunner(){
		return new QueryRunner(ds);
	}
}
