package com.model;

public class Course {
	private int courseId;
	private String courseName;
	private int semestersInCourse;
	
	public int getCourseId() {
		return courseId;
	}
	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}
	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	public int getSemestersInCourse() {
		return semestersInCourse;
	}
	public void setSemestersInCourse(int semestersInCourse) {
		this.semestersInCourse = semestersInCourse;
	}
	@Override
	public String toString() {
		return "Course [courseId=" + courseId + ", courseName=" + courseName
				+ ", semestersInCourse=" + semestersInCourse + "]";
	}
}
