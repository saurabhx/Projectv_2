package com.service;

import java.io.IOException;
import java.nio.CharBuffer;
import com.dao.ScoreDao;

import com.service.interfaces.Writable;

public class ScoreService implements Readable,Writable{
	ScoreDao scoreDao;

	public void writeToDatabase() {
		scoreDao=new ScoreDao();
		}

	public void readFromDatabase() {
	
		}

	public int read(CharBuffer cb) throws IOException {
		// TODO Auto-generated method stub
		return 0;
	}

}
