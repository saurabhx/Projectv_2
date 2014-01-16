package com.service;

import com.service.interfaces.DbReadable;
import com.service.interfaces.DbWritable;
import com.dao.*;

public class StudentService implements DbReadable,DbWritable{
	StudentDao studentDao;
	
	public StudentService(){
		studentDao=new StudentDao();
	}
	
	public void writeToDatabase() {
		studentDao.addStudent();
	}

	public void readFromDatabase() {
		studentDao.getAllStudents();
		
	}

}
