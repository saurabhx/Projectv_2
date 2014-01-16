package com.service;


import java.sql.SQLException;

import com.dao.*;
import com.service.interfaces.DbReadable;
import com.service.interfaces.DbWritable;


public class CourseService implements DbReadable,DbWritable{
	CourseDao courseDao;
	
	public CourseService(){
		courseDao=new CourseDao();
	}
	
	public void writeToDatabase(String courseName) {
		try {
			if(courseDoesNotExist(courseName))
			courseDao.addCourse(courseName);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	private boolean courseDoesNotExist(String courseName) {
		if(courseDao.courseDoesNotExist(courseName))
			{
			return true;
			}else return false;
		
	}

	public void readFromDatabase() {
		try {
			courseDao.getAllCourse();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public void writeToDatabase() {
		// TODO Auto-generated method stub
		
	}

	

	

}
