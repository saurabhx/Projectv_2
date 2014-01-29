package com.dao.tests;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.internal.util.reflection.Whitebox;

import com.dao.PieDao;
import com.util.DbUtil;

public class PieDaoTest {

	PieDao pieDao;
	DbUtil dbUtil;

	PreparedStatement preparedStatement;
	ResultSet resultSet;
	Connection connection;

	@Before
	public void setup() {

		pieDao = new PieDao();

		dbUtil = Mockito.mock(DbUtil.class);
		Whitebox.setInternalState(pieDao, "dbUtil", dbUtil);

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
	public void shouldReturnPieChartOutputCorrectly() throws SQLException {

		mocking();

		pieDao.getPiechartOutputs(1);
		Mockito.verify(connection, Mockito.times(1))
				.prepareStatement(
						Mockito.eq("select studentid,studentname,score from college.mapstudentscore natural join college.student where subjectid=" + 1));

	}

	@Test
	public void shouldReturnPieChartOutputForLessThan50() throws SQLException {
		mocking();

		pieDao.getPiechartOutputsWithCondition(1, 2);
		Mockito.verify(connection, Mockito.times(1))
				.prepareStatement(
						Mockito.eq("select studentid,studentname,score from college.mapstudentscore natural join college.student where subjectid="
								+ 2 + " and score < 50"));

	}

	@Test
	public void shouldReturnPieChartOutputForGreaterThan75()
			throws SQLException {

		mocking();

		pieDao.getPiechartOutputsWithCondition(3, 2);
		Mockito.verify(connection, Mockito.times(1))
				.prepareStatement(
						Mockito.eq("select studentid,studentname,score from college.mapstudentscore natural join college.student where subjectid="
								+ 2 + " and score > 75"));
	}

	@Test
	public void shouldReturnPieChartOutputFor50And75() throws SQLException {
		mocking();

		pieDao.getPiechartOutputsWithCondition(2, 2);
		Mockito.verify(connection, Mockito.times(1))
				.prepareStatement(
						Mockito.eq("select studentid,studentname,score from college.mapstudentscore natural join college.student where subjectid="
								+ 2 + " and score >= 50 and score <=75"));

	}

	@Test
	public void shouldReturnHighestMarksCorrectly() throws SQLException {

		mocking();

		pieDao.getHighestMarksForSubject(1);
		Mockito.verify(connection, Mockito.times(1))
				.prepareStatement(
						Mockito.eq("select studentname,score from mapstudentscore natural join student where score  in (select max(score) from mapstudentscore where subjectId ="
								+ 1 + ")order by studentname limit 1"));

	}

	@Test
	public void shouldReturnSubjectCorrectlyCorrespondingToSubjectId()
			throws SQLException {

		mocking();

		pieDao.getSubjectNameById(3);
		Mockito.verify(connection, Mockito.times(1))
				.prepareStatement(
						Mockito.eq("select subjectname from subject where subjectId=" + 3));

	}
}
