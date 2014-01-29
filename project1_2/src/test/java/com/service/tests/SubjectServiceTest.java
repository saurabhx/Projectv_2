package com.service.tests;

import java.sql.SQLException;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.internal.util.reflection.Whitebox;

import com.dao.SubjectDao;
import com.service.SubjectService;

public class SubjectServiceTest {
	private SubjectService subjectService;
	private SubjectDao subjectDao;
	
	@Before
    public void setup(){
    	subjectService = new SubjectService();
        subjectDao = Mockito.mock(SubjectDao.class);
        Whitebox.setInternalState(subjectService, "subjectDao", subjectDao);
    }
	
	@Test
	public void shouldAddSubjectCorrectly() throws SQLException {
	        subjectService.addSubject("Flying");
	        Mockito.verify(subjectDao, Mockito.times(1)).addSubject(Mockito.anyString());
	}
	@Test
	public void shouldCallGetAllSubjectsCorrectly() throws SQLException{
		 	subjectService.getAllSubjects();
	        Mockito.verify(subjectDao, Mockito.times(1)).getAllSubjects();
	}
	
	@Test
	public void shouldCallGetSubjectBySemesterAndCourse() throws SQLException{
		subjectService.getSubjectsBySemesterAndCourse(1, 1);
		Mockito.verify(subjectDao,Mockito.times(1)).getSubjectsBySemesterAndCourse(Mockito.anyInt(), Mockito.anyInt());
	}
}
