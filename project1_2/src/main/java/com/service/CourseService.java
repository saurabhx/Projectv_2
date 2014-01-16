package com.service;

public class CourseService implements Readable,Writable{
	CourseDao courseDao;
	
	public CourseService(){
		courseDao=new CourseDao();
	}
	
	public void writeToDatabase() {
		
		
	}

	public void readFromDatabase() {
		
		
	}

}
