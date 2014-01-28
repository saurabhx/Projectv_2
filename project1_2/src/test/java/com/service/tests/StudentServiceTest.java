package com.service.tests;

import java.sql.SQLException;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentMatcher;
import org.mockito.Mockito;
import org.mockito.internal.util.reflection.Whitebox;

import com.dao.StudentDao;
import com.model.Student;
import com.service.StudentService;

public class StudentServiceTest {
	private StudentService studentService;
	private StudentDao studentDao;
	
	@Before
    public void setup(){
    	studentService = new StudentService();
        studentDao = Mockito.mock(StudentDao.class);
        Whitebox.setInternalState(studentService, "studentDao", studentDao);
    }
	
	@Test
	public void shouldAddStudentCorrectly() throws SQLException {
	        
		ArgumentMatcher<Student> argumentMatcher = new ArgumentMatcher<Student>() {

				@Override
				public boolean matches(Object argument) {
					//Student student = (Student) argument;
	                return true;
				}
		};
			studentService.addStudent(new Student());
	        Mockito.verify(studentDao, Mockito.times(1)).addStudent(Mockito.argThat(argumentMatcher));
	}
	@Test
	public void shouldCallGetAllStudentsCorrectly() throws SQLException{
		 	studentService.getAllStudents();
	        Mockito.verify(studentDao, Mockito.times(1)).getAllStudents();
	}
	
	@Test
	public void shouldCallGetStudentByNameAndId() throws SQLException{
		studentService.getStudentNameById(1);
		Mockito.verify(studentDao,Mockito.times(1)).getStudentNameById(Mockito.anyInt());
	}
}
