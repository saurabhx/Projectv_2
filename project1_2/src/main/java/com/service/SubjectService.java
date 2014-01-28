package com.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dao.SubjectDao;
import com.model.Subject;

@Component
public class SubjectService {
	@Autowired
	SubjectDao subjectDao;
	
	public List<Subject> getAllSubjects() throws SQLException{
		return subjectDao.getAllSubjects();
	}
	public List<Subject> getSubjectsBySemesterAndCourse(int semesterId, int courseId){
		return subjectDao.getSubjectsBySemesterAndCourse(semesterId, courseId);
	}
	public void addSubject(String subjectName) throws SQLException{
		subjectDao.addSubject(subjectName);
	}
}
