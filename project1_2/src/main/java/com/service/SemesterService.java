package com.service;

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
		semesterDao.getAllSemesters();
		
	}

}
