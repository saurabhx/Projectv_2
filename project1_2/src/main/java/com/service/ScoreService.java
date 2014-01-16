package com.service;

public class ScoreService implements Readable,Writable{
	ScoreDao scoreDao;

	public void writeToDatabase() {
		scoreDao=new ScoreDao();
		}

	public void readFromDatabase() {
	
		}

}
