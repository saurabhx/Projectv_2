package com.model;

public class ChartData {
	private int studentId;
	private String studentName;
	private double marks;
	
	@Override
	public String toString() {
		return "ChartData [studentId=" + studentId + ", studentName="
				+ studentName + ", marks=" + marks + "]";
	}
	public int getStudentId() {
		return studentId;
	}
	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}
	public String getStudentName() {
		return studentName;
	}
	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}
	public double getMarks() {
		return marks;
	}
	public void setMarks(double marks) {
		this.marks = marks;
	}

}
