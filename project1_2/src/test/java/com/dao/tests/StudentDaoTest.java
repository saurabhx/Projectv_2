package com.dao.tests;

import static org.junit.Assert.assertTrue;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.internal.util.reflection.Whitebox;

import com.dao.StudentDao;
import com.model.Student;
import com.util.DbUtil;

public class StudentDaoTest {

	StudentDao studentDao;
	DbUtil dbUtil;

	PreparedStatement preparedStatement;
	ResultSet resultSet;
	Connection connection;

	@Before
	public void setup() {

		studentDao = new StudentDao();

		dbUtil = Mockito.mock(DbUtil.class);
		Whitebox.setInternalState(studentDao, "dbUtil", dbUtil);

		connection = Mockito.mock(Connection.class);
		preparedStatement = Mockito.mock(PreparedStatement.class);
		resultSet = Mockito.mock(ResultSet.class);

	}

	private void mocking() throws SQLException {
		Mockito.when(dbUtil.getConnection()).thenReturn(connection);
		Mockito.when(connection.prepareStatement(Mockito.anyString()))
				.thenReturn(preparedStatement);
		Mockito.when(preparedStatement.executeQuery()).thenReturn(resultSet);
	}

	@Test
	public void shouldAddStudentCorrectly() throws SQLException {

		mocking();
		Student student = new Student();
		student.setStudentName("Saurabh");
		studentDao.addStudent(student);

		Mockito.verify(preparedStatement, Mockito.times(1)).setString(
				Mockito.eq(1), Mockito.eq("Saurabh"));
		Mockito.verify(preparedStatement, Mockito.times(1)).executeUpdate();
		Mockito.verify(connection, Mockito.times(1)).prepareStatement(
				Mockito.eq("insert into student(studentname) values (?)"));

	}

	@Test
	public void shouldReturnStudentsCorrectly() throws SQLException {

		mocking();

		List<Student> students = studentDao.getAllStudents();
		assertTrue(0 == students.size());

		Mockito.verify(connection, Mockito.times(1)).prepareStatement(
				Mockito.eq("select * from student"));
	}

	@Test
	public void shouldReturnStudentNameByIdCorrectly() throws SQLException {
		mocking();

		studentDao.getStudentNameById(1);

		Mockito.verify(connection, Mockito.times(1))
				.prepareStatement(
						Mockito.eq("select studentname from student where studentid= " + 1));
	}
}
