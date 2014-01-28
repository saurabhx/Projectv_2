package com.service.tests;

import java.sql.SQLException;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.internal.util.reflection.Whitebox;

import com.dao.ScoreDao;
import com.service.ScoreService;

public class ScoreServiceTest {
	
	private ScoreService scoreService;
	private ScoreDao scoreDao;
	
	@Before
    public void setup(){
    	scoreService = new ScoreService();
        scoreDao = Mockito.mock(ScoreDao.class);
        Whitebox.setInternalState(scoreService, "scoreDao", scoreDao);
    }
	 @Test
	    public void shouldCallAddScoreCorrectly() throws SQLException {
	        
	        scoreService.addScore(1, 1, 100);
	        Mockito.verify(scoreDao, Mockito.times(1)).addScore(Mockito.anyInt(),Mockito.anyInt(),Mockito.anyInt());
	        
	        
	    }
	 @Test
	 public void shouldCallGetScoreCorrectly() throws SQLException{
		 	scoreService.getScore(1);
	        Mockito.verify(scoreDao, Mockito.times(1)).getScore(Mockito.anyInt());
	 }
}

