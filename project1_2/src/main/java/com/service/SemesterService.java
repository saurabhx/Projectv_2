package com.service;

import java.io.IOException;
import java.nio.CharBuffer;

import com.dao.SemesterDao;
import com.service.interfaces.Writable;

public class SemesterService implements Readable,Writable{
	SemesterDao semesterDao;
	
	public SemesterService(){
		semesterDao=new SemesterDao();
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
