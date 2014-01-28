package com.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.model.ChartData;
import com.util.DbUtil;

@Component
public class PieDao {

	@Autowired
	DbUtil dbUtil;
	private PreparedStatement preparedStatement;
	private ResultSet resultSet;

	public List<ChartData> getPiechartOutputs(int subjectId)
			throws SQLException {
		List<ChartData> queryResult = new ArrayList<ChartData>();
		preparedStatement = dbUtil
				.getConnection()
				.prepareStatement(
						"select studentid,studentname,score from college.mapstudentscore natural join college.student where subjectid="
								+ subjectId);
		resultSet = preparedStatement.executeQuery();

		while (resultSet.next()) {
			ChartData chartData = returnChartData(resultSet);

			queryResult.add(chartData);
		}

		return queryResult;
	}

	public List<ChartData> getPiechartOutputsWithCondition(int range,
			int subjectId) throws SQLException {
		List<ChartData> qr = new ArrayList<ChartData>();
		String query;

		if (range == 1) {
			query = "select studentid,studentname,score from college.mapstudentscore natural join college.student where subjectid="
					+ subjectId + " and score < 50";
		} else if (range == 2) {
			query = "select studentid,studentname,score from college.mapstudentscore natural join college.student where subjectid="
					+ subjectId + " and score >= 50 and score <=75";
		} else {
			query = "select studentid,studentname,score from college.mapstudentscore natural join college.student where subjectid="
					+ subjectId + " and score > 75";
		}

		preparedStatement = dbUtil.getConnection().prepareStatement(query);
		resultSet = preparedStatement.executeQuery();
		while (resultSet.next()) {
			ChartData chartData = returnChartData(resultSet);

			qr.add(chartData);
		}

		return qr;
	}

	private ChartData returnChartData(ResultSet resultSet) throws SQLException {
		ChartData chartData = new ChartData();
		chartData.setStudentId((resultSet.getInt("studentid")));
		chartData.setStudentName(resultSet.getString("studentname"));
		chartData.setMarks(Double.parseDouble(resultSet.getString("score")));
		return chartData;
	}

	public Map<String, Double> getHighestMarksForSubject(int subjectId)
			throws SQLException {
		Map<String, Double> map = new HashMap<String, Double>();

		preparedStatement = dbUtil
				.getConnection()
				.prepareStatement(
						"select studentname,score from mapstudentscore natural join student where score  in (select max(score) from mapstudentscore where subjectId ="
								+ subjectId + ")order by studentname limit 1");
		resultSet = preparedStatement.executeQuery();

		while (resultSet.next()) {
			map.put(resultSet.getString("studentname"),
					resultSet.getDouble("score"));
		}

		return map;
	}

	public String getSubjectNameById(int subjectId) throws SQLException {
		String subjectName = "";

		preparedStatement = dbUtil.getConnection().prepareStatement(
				"select subjectname from subject where subjectId=" + subjectId);
		resultSet = preparedStatement.executeQuery();

		while (resultSet.next()) {
			subjectName = resultSet.getString("subjectName");
		}

		return subjectName;
	}

}
