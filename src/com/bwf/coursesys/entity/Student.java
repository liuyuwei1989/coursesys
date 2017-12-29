package com.bwf.coursesys.entity;

public class Student {
	//学生ID
	private int id;
	//学生姓名
	private String name;
	//学生所在班级
	private String className;
	//学生登录用户名
	private String loginName;
	//学生登录密码
	private String password;
	//学生所选周二的课程ID
	private int tueCourseId;
	//学生所选周五的课程ID
	private int friCourseId;

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	public String getLoginName() {
		return loginName;
	}
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getTueCourseId() {
		return tueCourseId;
	}
	public void setTueCourseId(int tueCourseId) {
		this.tueCourseId = tueCourseId;
	}
	public int getFriCourseId() {
		return friCourseId;
	}
	public void setFriCourseId(int friCourseId) {
		this.friCourseId = friCourseId;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Student other = (Student) obj;
		if (id != other.id)
			return false;
		return true;
	}

	
}
