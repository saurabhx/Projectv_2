package com.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dao.SemesterDao;
import com.model.Semester;
@Component
public class SemesterService {
	@Autowired
	SemesterDao semesterDao;
	
	
	public void addSemester(String semesterName) throws SQLException {
		
		 semesterDao.addSemester(semesterName);
		
	}

	public List<Semester> getAllSemesters() throws SQLException{
		return semesterDao.getAllSemesters();
	}

}
