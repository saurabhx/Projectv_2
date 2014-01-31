package com.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Table;


@Embeddable
@Table()
public class ChartData {
	private int studentId;
	private String studentName;
	private double marks;
	
	@Override
	public String toString() {
		return "ChartData [studentId=" + studentId + ", studentName="
				+ studentName + ", marks=" + marks + "]";
	}
	@Column(name ="studentid")
	public int getStudentId() {
		return studentId;
	}
	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}
	@Column(name ="studentname")
	public String getStudentName() {
		return studentName;
	}
	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}
	@Column(name ="score")
	public double getMarks() {
		return marks;
	}
	public void setMarks(double marks) {
		this.marks = marks;
	}

}
