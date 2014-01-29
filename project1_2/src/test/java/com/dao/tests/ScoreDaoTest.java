package com.dao.tests;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.internal.util.reflection.Whitebox;

import com.dao.ScoreDao;
import com.util.DbUtil;

public class ScoreDaoTest {

	ScoreDao scoreDao;
	DbUtil dbUtil;

	PreparedStatement preparedStatement;
	ResultSet resultSet;
	Connection connection;

	@Before
	public void setup() {

		scoreDao = new ScoreDao();

		dbUtil = Mockito.mock(DbUtil.class);
		Whitebox.setInternalState(scoreDao, "dbUtil", dbUtil);

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
	public void shouldAddScoreCorrectly() throws SQLException {

		mocking();

		scoreDao.addScore(1, 1, 88.0);

		Mockito.verify(preparedStatement, Mockito.times(1)).setInt(
				Mockito.eq(1), Mockito.eq(1));
		Mockito.verify(preparedStatement, Mockito.times(1)).setInt(
				Mockito.eq(2), Mockito.eq(1));
		Mockito.verify(preparedStatement, Mockito.times(1)).setDouble(
				Mockito.eq(3), Mockito.eq(88.0));

		Mockito.verify(preparedStatement, Mockito.times(1)).executeUpdate();
		Mockito.verify(connection, Mockito.times(1))
				.prepareStatement(
						Mockito.eq("insert into mapstudentscore(studentid,subjectid,score) values (?,?,?)"));
	}

	@Test
	public void shouldReturnScoreCorrectly() throws SQLException {

		mocking();

		scoreDao.getScore(1);

		Mockito.verify(connection, Mockito.times(1))
				.prepareStatement(
						Mockito.eq("select studentid,score from mapstudentscore where subjectid=" + 1));

	}

}
