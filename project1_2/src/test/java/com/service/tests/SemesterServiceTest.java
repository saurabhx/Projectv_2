package com.service.tests;

import java.sql.SQLException;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.internal.util.reflection.Whitebox;

import com.dao.SemesterDao;
import com.service.SemesterService;

public class SemesterServiceTest {
	private SemesterService semesterService;
	private SemesterDao semesterDao;
	
	@Before
    public void setup(){
    	semesterService = new SemesterService();
        semesterDao = Mockito.mock(SemesterDao.class);
        Whitebox.setInternalState(semesterService, "semesterDao", semesterDao);
    }
	@Test
	public void shouldCallAddSemesterCorrectly() throws SQLException {
	        
	        semesterService.addSemester("8");
	        Mockito.verify(semesterDao, Mockito.times(1)).addSemester(Mockito.anyString());
	        
	        
	}
	@Test
	public void shouldCallGetAllSemestersCorrectly() throws SQLException{
		 	semesterService.getAllSemesters();
	        Mockito.verify(semesterDao, Mockito.times(1)).getAllSemesters();
	}
}
