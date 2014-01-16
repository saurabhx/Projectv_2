package com.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import com.mysql.jdbc.PreparedStatement;
import com.util.DbUtil;

public class ScoreDao {

	private Connection connection;
	private PreparedStatement preparedStatement;
	private java.sql.ResultSet rs;

	public ScoreDao() {
		connection = DbUtil.getConnection();
	}

	public Map<Integer, Double> getScore(int subjectId) throws SQLException {

		Map<Integer, Double> map = new HashMap<Integer, Double>();

		preparedStatement = (PreparedStatement) connection
				.prepareStatement("select studentid,score from mapstudentscore where subjectid=?");
		preparedStatement.setInt(1, subjectId);

		rs = preparedStatement.executeQuery();
		while (rs.next()) {

			map.put(rs.getInt("studentid"), rs.getDouble("score"));

		}
		return map;

	}

	public void addScore(int studentId, int subjectId, double score)
			throws SQLException {

		preparedStatement = (PreparedStatement) connection
				.prepareStatement("insert into mapstudentscore(studentid,subjectid,score) values (?,?,?)");
		preparedStatement.setInt(1, studentId);
		preparedStatement.setInt(2, subjectId);
		preparedStatement.setDouble(1, score);

		preparedStatement.executeUpdate();

	}
}
