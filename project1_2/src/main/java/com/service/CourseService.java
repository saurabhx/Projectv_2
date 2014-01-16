package com.service;

import java.io.IOException;
import java.nio.CharBuffer;
import com.dao.*;

import com.service.interfaces.Writable;

public class CourseService implements Readable,Writable{
	CourseDao courseDao;
	
	public CourseService(){
		courseDao=new CourseDao();
	}
	
	public void writeToDatabase() {
		
		
	}

	public void readFromDatabase() {
		
		
	}

	public int read(CharBuffer cb) throws IOException {
		// TODO Auto-generated method stub
		return 0;
	}

}
