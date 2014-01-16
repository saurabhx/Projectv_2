package com.model;

import java.util.List;
import java.util.Map;

public class Student {
	private int studentId;
	private String studentName;
	private int studentCurrentSemester;
	private List<Course> studentCourses;
	private Map<Subject,Double> studentScores;
	
	
	public String getStudentName() {
		return studentName;
	}
	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}
	public int getStudentCurrentSemester() {
		return studentCurrentSemester;
	}
	public void setStudentCurrentSemester(int studentCurrentSemester) {
		this.studentCurrentSemester = studentCurrentSemester;
	}
	public List<Course> getStudentCourses() {
		return studentCourses;
	}
	public void setStudentCourses(List<Course> studentCourses) {
		this.studentCourses = studentCourses;
	}
	public Map<Subject, Double> getStudentScores() {
		return studentScores;
	}
	public void setStudentScores(Map<Subject, Double> studentScores) {
		this.studentScores = studentScores;
	}
	@Override
	public String toString() {
		return "Student [studendId=" + studentId + ", studentName="
				+ studentName + ", studentCurrentSemester="
				+ studentCurrentSemester + ", studentCourses=" + studentCourses
				+ ", studentScores=" + studentScores + "]";
	}
	public int getStudentId() {
		return studentId;
	}
	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}
	
	
	
}
