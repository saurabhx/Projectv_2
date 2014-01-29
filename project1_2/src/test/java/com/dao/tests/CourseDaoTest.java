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

import com.dao.CourseDao;
import com.model.Course;
import com.util.DbUtil;

public class CourseDaoTest {

	CourseDao courseDao;
	DbUtil dbUtil;

	PreparedStatement preparedStatement;
	ResultSet resultSet;
	Connection connection;

	@Before
	public void setup() {

		courseDao = new CourseDao();

		dbUtil = Mockito.mock(DbUtil.class);
		Whitebox.setInternalState(courseDao, "dbUtil", dbUtil);

		connection = Mockito.mock(Connection.class);
		preparedStatement = Mockito.mock(PreparedStatement.class);
		resultSet = Mockito.mock(ResultSet.class);

	}

	@Test
	public void shouldAddCourseCorrectly() throws SQLException {

		mocking();

		courseDao.addCourse("Maths");

		Mockito.verify(preparedStatement, Mockito.times(1)).setString(
				Mockito.eq(1), Mockito.eq("Maths"));
		Mockito.verify(preparedStatement, Mockito.times(1)).executeUpdate();
		Mockito.verify(connection, Mockito.times(1)).prepareStatement(
				Mockito.eq("insert into course (coursename) values (?)"));
	}

	@Test
	public void shouldReturnCoursesCorrectly() throws SQLException {

		mocking();

		List<Course> courses = courseDao.getAllCourse();
		assertTrue(0 == courses.size());

		Mockito.verify(connection, Mockito.times(1)).prepareStatement(
				Mockito.eq("select * from course"));

	}

	private void mocking() throws SQLException {
		Mockito.when(dbUtil.getConnection()).thenReturn(connection);
		Mockito.when(connection.prepareStatement(Mockito.anyString()))
				.thenReturn(preparedStatement);
		Mockito.when(preparedStatement.executeQuery()).thenReturn(resultSet);
	}
}
