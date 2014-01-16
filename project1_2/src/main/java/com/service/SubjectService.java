package com.service;

import com.dao.SubjectDao;
import com.service.interfaces.DbReadable;
import com.service.interfaces.DbWritable;

public class SubjectService implements DbReadable,DbWritable{
	SubjectDao subjectDao;
	
	public SubjectService(){
		subjectDao=new SubjectDao();
	}

	public void writeToDatabase() {
		subjectDao.addSubject();
		
	}

	public void readFromDatabase() {
		subjectDao.getAllSubjects();
		
	}


}
