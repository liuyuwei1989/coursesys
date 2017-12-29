package com.bwf.coursesys.dao;

import org.apache.commons.dbutils.QueryRunner;

import com.bwf.courses.utils.JDBCUtils;

public class BaseDAO {
	
	protected QueryRunner qRunner = JDBCUtils.getQueryRunner();
	
}
