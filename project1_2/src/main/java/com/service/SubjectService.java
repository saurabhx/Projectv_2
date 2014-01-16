package com.service;

import java.sql.SQLException;

import com.dao.SubjectDao;
import com.service.interfaces.DbReadable;
import com.service.interfaces.DbWritable;

public class SubjectService implements DbReadable,DbWritable{
	SubjectDao subjectDao;
	
	public SubjectService(){
		subjectDao=new SubjectDao();
	}

	public void writeToDatabase(String subjectName) {
		try {
			subjectDao.addSubject(subjectName);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public void readFromDatabase() {
		subjectDao.getAllSubjects();
		
	}


}
