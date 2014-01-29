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

import com.dao.SubjectDao;
import com.model.Subject;
import com.util.DbUtil;

public class SubjectDaoTest {

	SubjectDao subjectDao;
	DbUtil dbUtil;

	PreparedStatement preparedStatement;
	ResultSet resultSet;
	Connection connection;

	@Before
	public void setup() {

		subjectDao = new SubjectDao();

		dbUtil = Mockito.mock(DbUtil.class);
		Whitebox.setInternalState(subjectDao, "dbUtil", dbUtil);

		connection = Mockito.mock(Connection.class);
		preparedStatement = Mockito.mock(PreparedStatement.class);
		resultSet = Mockito.mock(ResultSet.class);

	}

	@Test
	public void shouldReturnSubjectsCorrectly() throws SQLException {

		mocking();

		List<Subject> subjects = subjectDao.getAllSubjects();
		assertTrue(0 == subjects.size());

		Mockito.verify(connection, Mockito.times(1)).prepareStatement(
				Mockito.eq("select * from subject"));

	}

	private void mocking() throws SQLException {
		Mockito.when(dbUtil.getConnection()).thenReturn(connection);
		Mockito.when(connection.prepareStatement(Mockito.anyString()))
				.thenReturn(preparedStatement);
		Mockito.when(preparedStatement.executeQuery()).thenReturn(resultSet);
	}

	@Test
	public void shouldAddSubjectCorrectly() throws SQLException {
		mocking();
		subjectDao.addSubject("Maths");

		Mockito.verify(preparedStatement, Mockito.times(1)).setString(
				Mockito.eq(1), Mockito.eq("Maths"));
		Mockito.verify(preparedStatement, Mockito.times(1)).executeUpdate();
		Mockito.verify(connection, Mockito.times(1)).prepareStatement(
				Mockito.eq("insert into subject(subjectname) values ( ?)"));

	}

	@Test
	public void shouldReturnSunjectBasedOnSemesterAndCourseCorrectly()
			throws SQLException {

		mocking();

		subjectDao.getSubjectsBySemesterAndCourse(1, 1);
		Mockito.verify(connection, Mockito.times(1))
				.prepareStatement(
						Mockito.eq("select * from mapsemestersubject natural join subject where semesterid ="
								+ 1 + " and courseid =" + 1));

	}

}
