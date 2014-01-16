package com.service;
import java.sql.SQLException;

import com.dao.ScoreDao;
import com.service.interfaces.DbReadable;
import com.service.interfaces.DbWritable;



public class ScoreService implements DbReadable,DbWritable{
	ScoreDao scoreDao;
	
	public ScoreService(){
		scoreDao=new ScoreDao();
	}

	public void writeToDatabase() {
		try {
			scoreDao.addScore(0, 0, 0);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}

	public void readFromDatabase() {
		try {
			scoreDao.getScore(0);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}

	public void setSubId(int parseInt) {
		// TODO Auto-generated method stub
		
	}

	

}
