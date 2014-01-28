package com.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.util.DbUtil;

@Component
public class ScoreDao {

	
	private PreparedStatement preparedStatement;
	

	@Autowired
	DbUtil dbUtil;
	

	public Map<Integer, Double> getScore(int subjectId) throws SQLException {

		Map<Integer, Double> map = new HashMap<Integer, Double>();

		preparedStatement = dbUtil.getConnection()
				.prepareStatement("select studentid,score from mapstudentscore where subjectid="+subjectId);
		
		ResultSet resultSet = preparedStatement.executeQuery();
		while (resultSet.next()) {
			map.put(resultSet.getInt("studentid"), resultSet.getDouble("score"));

		}
		return map;

	}

	public void addScore(int studentId, int subjectId, double score)
			throws SQLException {

		preparedStatement =  dbUtil.getConnection()
				.prepareStatement("insert into mapstudentscore(studentid,subjectid,score) values (?,?,?)");
		preparedStatement.setInt(1, studentId);
		preparedStatement.setInt(2, subjectId);
		preparedStatement.setDouble(3, score);
		preparedStatement.executeUpdate();

	}
}
