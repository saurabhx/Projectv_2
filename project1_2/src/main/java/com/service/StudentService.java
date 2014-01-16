package com.service;

import java.sql.SQLException;

import com.model.Student;
import com.service.interfaces.DbReadable;
import com.service.interfaces.DbWritable;
import com.dao.*;

public class StudentService implements DbReadable,DbWritable{
	StudentDao studentDao;
	Student student;
	
	public StudentService(){
		studentDao=new StudentDao();
	}
	
	public void writeToDatabase(String arg) {
		try {
			studentDao.addStudent(student);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void readFromDatabase() {
		try {
			studentDao.getAllStudents();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public void writeToDatabase() {
		// TODO Auto-generated method stub
		
	}

	

}
