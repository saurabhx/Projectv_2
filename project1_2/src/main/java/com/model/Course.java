package com.model;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;


@Entity
@Table(name="course", catalog="college")
public class Course implements java.io.Serializable{
	private int courseId;
	private String courseName;
	
	@Transient
	private int semestersInCourse;
	
	public Course(){}
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "courseid", unique = true, nullable = false)
	public int getCourseId() {
		return courseId;
	}
	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}
	
	@Column(name = "coursename", unique = true, nullable = false, length= 45)
	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	@Transient
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
