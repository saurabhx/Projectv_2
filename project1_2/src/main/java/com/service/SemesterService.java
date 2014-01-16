package com.service;

import java.sql.SQLException;

import com.dao.SemesterDao;
import com.service.interfaces.DbReadable;
import com.service.interfaces.DbWritable;

public class SemesterService implements DbReadable,DbWritable{
	SemesterDao semesterDao;
	
	public SemesterService(){
		semesterDao=new SemesterDao();
	} 
	
	public void writeToDatabase(String semesterName) {
		semesterDao.addSemester(semesterName);
		
	}

	public void readFromDatabase() {
		try {
			semesterDao.getAllSemesters();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
