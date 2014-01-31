package com.model;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "semester", catalog = "college")
public class Semester implements java.io.Serializable {
	private int semesterId;
	private String semesterName;
	
	public Semester(){}
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "semesterid", unique = true, nullable = false)
	public int getSemesterId() {
		return semesterId;
	}
	public void setSemesterId(int semesterId) {
		this.semesterId = semesterId;
	}
	@Column(name = "semestername", unique = true, nullable = false, length =45)
	public String getSemesterName() {
		return semesterName;
	}
	public void setSemesterName(String semesterName) {
		this.semesterName = semesterName;
	}
	@Override
	public String toString() {
		return "Semester [semesterId=" + semesterId + ", semesterName="
				+ semesterName + "]";
	}

}
