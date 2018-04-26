package com.bwf.coursesys.dao;

import org.apache.commons.dbutils.QueryRunner;

import com.bwf.courses.utils.JDBCUtils;
//基础dao层接口
public class BaseDAO {
	
	protected QueryRunner qRunner = JDBCUtils.getQueryRunner();
	
}
