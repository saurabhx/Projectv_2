package com.service.tests;

import java.sql.SQLException;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.internal.util.reflection.Whitebox;

import com.dao.CourseDao;
import com.service.CourseService;

public class CourseServiceTest {

	
    
    private CourseService courseService;
	private CourseDao courseDao;
	
	@Before
    public void setup(){
    	courseService = new CourseService();
        courseDao = Mockito.mock(CourseDao.class);
        Whitebox.setInternalState(courseService, "courseDao", courseDao);
    }
	 @Test
	    public void shouldCallCreateCourseCorrectly() throws SQLException {
	        
	        courseService.createCourse("B.Tech IT");
	        Mockito.verify(courseDao, Mockito.times(1)).addCourse(Mockito.anyString());
	        
	        
	    }
	 @Test
	 public void shouldCallGetAllCoursesCorrectly() throws SQLException{
		 	courseService.getAllCourses();
	        Mockito.verify(courseDao, Mockito.times(1)).getAllCourse();
	 }
}
