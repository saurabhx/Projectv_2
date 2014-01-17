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
		//if(semesterNameDoesNotExist(semesterName))
		semesterDao.addSemester(semesterName);
		
	}

	private boolean semesterNameDoesNotExist(String semesterName) {
		if(semesterDao.semesterDoesNotExist(semesterName))
			return true; 
		
		return false;
	}

	public void readFromDatabase() {
		try {
			semesterDao.getAllSemesters();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public void writeToDatabase() {
		// TODO Auto-generated method stub
		
	}

}
