package com.service;
import com.dao.ScoreDao;
import com.service.interfaces.DbReadable;
import com.service.interfaces.DbWritable;



public class ScoreService implements DbReadable,DbWritable{
	ScoreDao scoreDao;
	
	public ScoreService(){
		scoreDao=new ScoreDao();
	}

	public void writeToDatabase() {
		scoreDao.addScore();
		}

	public void readFromDatabase() {
		scoreDao.getScore();
		}

	

}
