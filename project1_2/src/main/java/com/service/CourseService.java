package com.service;


import com.dao.*;
import com.service.interfaces.DbReadable;
import com.service.interfaces.DbWritable;
import com.service.interfaces.Writable;

public class CourseService implements DbReadable,DbWritable{
	CourseDao courseDao;
	
	public CourseService(){
		courseDao=new CourseDao();
	}
	
	public void writeToDatabase(String courseName) {
		courseDao.addCourse(courseName);
		
	}

	public void readFromDatabase() {
		courseDao.getAllCourse();
		
	}

	

	

}
