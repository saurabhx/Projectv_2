package com.service.tests;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.internal.util.reflection.Whitebox;

import com.dao.PieDao;
import com.service.PieService;

public class PieServiceTest {

	private PieService pieService;
	private PieDao pieDao;
	
	@Before
    public void setup(){
    	pieService = new PieService();
        pieDao = Mockito.mock(PieDao.class);
        Whitebox.setInternalState(pieService, "pieDao", pieDao);
    }
	
	@Test
	public void shouldCallGetPieChartOutput(){
		pieService.getPiechartOutputs(1);
		Mockito.verify(pieDao,Mockito.times(1)).getPiechartOutputs(Mockito.anyInt());
	}
	@Test
	public void shouldCallGetPieChartOutputWithCondition(){
		pieService.getPiechartOutputsWithCondition(1, 1);
		Mockito.verify(pieDao,Mockito.times(1)).getPiechartOutputsWithCondition(Mockito.anyInt(), Mockito.anyInt());
	}
	@Test
	public void shouldCallGetHighestMarksForSubject(){
		pieService.getHighestMarksForSubject(1);
		Mockito.verify(pieDao,Mockito.times(1)).getHighestMarksForSubject(Mockito.anyInt());
	}
	@Test
	public void shouldCallGetSubjectNameById(){
		pieService.getSubjectNameById(1);
		Mockito.verify(pieDao,Mockito.times(1)).getSubjectNameById(Mockito.anyInt());
	}
}
