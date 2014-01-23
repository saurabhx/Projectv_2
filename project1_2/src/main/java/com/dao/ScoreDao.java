package com.dao;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mysql.jdbc.PreparedStatement;
import com.util.DbUtil;

@Component
public class ScoreDao {

	
	private PreparedStatement preparedStatement;
	private java.sql.ResultSet rs;

	@Autowired
	DbUtil dbUtil;
	

	public Map<Integer, Double> getScore(int subjectId) throws SQLException {

		Map<Integer, Double> map = new HashMap<Integer, Double>();

		preparedStatement = (PreparedStatement) dbUtil.getConnection()
				.prepareStatement("select studentid,score from mapstudentscore where subjectid="+subjectId);
		
		rs = preparedStatement.executeQuery();
		while (rs.next()) {
			map.put(rs.getInt("studentid"), rs.getDouble("score"));

		}
		return map;

	}

	public void addScore(int studentId, int subjectId, double score)
			throws SQLException {

		preparedStatement = (PreparedStatement) dbUtil.getConnection()
				.prepareStatement("insert into mapstudentscore(studentid,subjectid,score) values (?,?,?)");
		preparedStatement.setInt(1, studentId);
		preparedStatement.setInt(2, subjectId);
		preparedStatement.setDouble(1, score);
		preparedStatement.executeUpdate();

	}
}
