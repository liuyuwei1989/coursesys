package com.bwf.coursesys.entity;

public class Course {
	//课程ID
	private int id;
	//上课时间
	private String week;
	//适用年级
	private String grade;
	//科目名称
	private String courseName;
	//科目花费
	private String cost;
	//科目定员
	private int stuNum;
	//科目状态，1代表可选，0代表不可选
	private int choosable;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	public int getStuNum() {
		return stuNum;
	}
	public void setStuNum(int stuNum) {
		this.stuNum = stuNum;
	}
	public String getWeek() {
		return week;
	}
	public void setWeek(String week) {
		this.week = week;
	}
	public String getCost() {
		return cost;
	}
	public void setCost(String cost) {
		this.cost = cost;
	}
	public int getChoosable() {
		return choosable;
	}
	public void setChoosable(int choosable) {
		this.choosable = choosable;
	}
}
