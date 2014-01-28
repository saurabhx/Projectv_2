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

import com.dao.SemesterDao;
import com.model.Semester;
import com.util.DbUtil;

public class SemesterDaoTest {

	SemesterDao semesterDao;
	DbUtil dbUtil;

	PreparedStatement preparedStatement;
	ResultSet resultSet;
	Connection connection;

	@Before
	public void setup() {

		semesterDao = new SemesterDao();

		dbUtil = Mockito.mock(DbUtil.class);
		Whitebox.setInternalState(semesterDao, "dbUtil", dbUtil);

		connection = Mockito.mock(Connection.class);
		preparedStatement = Mockito.mock(PreparedStatement.class);
		resultSet = Mockito.mock(ResultSet.class);

	}

	@Test
	public void shouldAddSemesterCorrectly() throws SQLException {

		mocking();

		semesterDao.addSemester("first");

		Mockito.verify(preparedStatement, Mockito.times(1)).setString(
				Mockito.eq(1), Mockito.eq("first"));
		Mockito.verify(preparedStatement, Mockito.times(1)).executeUpdate();
		Mockito.verify(connection, Mockito.times(1)).prepareStatement(
				Mockito.eq("insert into semester(semestername) values (?)"));

	}

	@Test
	public void shouldReturnSemestersCorrectly() throws SQLException {
		mocking();

		List<Semester> semesters = semesterDao.getAllSemesters();
		assertTrue(0 == semesters.size());

		Mockito.verify(connection, Mockito.times(1)).prepareStatement(
				Mockito.eq("select * from semester"));

	}

	private void mocking() throws SQLException {
		Mockito.when(dbUtil.getConnection()).thenReturn(connection);
		Mockito.when(connection.prepareStatement(Mockito.anyString()))
				.thenReturn(preparedStatement);
		Mockito.when(preparedStatement.executeQuery()).thenReturn(resultSet);
	}

}
