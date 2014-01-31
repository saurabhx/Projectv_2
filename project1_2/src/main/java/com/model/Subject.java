package com.model;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name ="subject")
public class Subject implements java.io.Serializable {
	private int subjectId;
	private String subjectName;
	
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "subjectid", unique = true, nullable = false)
	public int getSubjectId() {
		return subjectId;
	}
	public void setSubjectId(int subjectId) {
		this.subjectId = subjectId;
	}
	@Column(name = "subjectname", unique = true, nullable = false)
	public String getSubjectName() {
		return subjectName;
	}
	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}
	@Override
	public String toString() {
		return "Subject [subjectId=" + subjectId + ", subjectName="
				+ subjectName + "]";
	}

}
