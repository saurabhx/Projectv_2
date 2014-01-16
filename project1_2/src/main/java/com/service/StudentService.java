package com.service;

import java.io.IOException;
import java.nio.CharBuffer;
import com.service.interfaces.Writable;
import com.dao.*;
public class StudentService implements Readable,Writable{
	StudentDao studentDao;
	
	public void writeToDatabase() {
		
	}

	public void readFromDatabase() {
		
		
	}

	public int read(CharBuffer cb) throws IOException {
		// TODO Auto-generated method stub
		return 0;
	}

	

}
