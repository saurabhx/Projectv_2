package com.service;

public class SemesterService implements Readable,Writable{
	SemesterDao semesterDao;
	
	public SemesterService(){
		semesterDao=newSemesterDao();
	} 
	
	public void writeToDatabase() {
		
		
	}

	public void readFromDatabase() {
	
		
	}

}
