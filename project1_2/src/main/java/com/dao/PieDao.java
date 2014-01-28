package com.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
	
	
	public List<ChartData> getPiechartOutputs(int i) {
		List<ChartData> queryResult = new ArrayList<ChartData>();

		try {
			Statement statement = dbUtil.getConnection().createStatement();
			ResultSet rs = statement
					.executeQuery("select studentid,studentname,score from college.mapstudentscore natural join college.student where subjectid="
							+ i);
			while (rs.next()) {
				ChartData out = new ChartData();
				out.setStudentId((rs.getInt("studentid")));
				out.setStudentName(rs.getString("studentname"));
				out.setMarks(Double.parseDouble(rs.getString("score")));

				queryResult.add(out);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return queryResult;
	}

	public List<ChartData> getPiechartOutputsWithCondition(int i, int si) {
		List<ChartData> qr = new ArrayList<ChartData>();
		String query;
	
		if (i == 1) {
			query = "select studentid,studentname,score from college.mapstudentscore natural join college.student where subjectid=" + si
					+ " and score < 50";
		} else if (i == 2) {
			query = "select studentid,studentname,score from college.mapstudentscore natural join college.student where subjectid=" + si
					+ " and score >= 50 and score <=75";
		} else {
			query = "select studentid,studentname,score from college.mapstudentscore natural join college.student where subjectid=" + si
					+ " and score > 75";
		}

		try {
			Statement statement = dbUtil.getConnection().createStatement();
			ResultSet rs = statement.executeQuery(query);
			while (rs.next()) {
				ChartData out = new ChartData();
				out.setStudentId((rs.getInt("studentid")));
				out.setStudentName(rs.getString("studentname"));
				out.setMarks(Double.parseDouble(rs.getString("score")));

				qr.add(out);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return qr;
	}

	public Map<String, Double> getHighestMarksForSubject(int subjectId) {
		Map<String, Double> map = new HashMap<String, Double>();
		try {
			Statement statement = dbUtil.getConnection().createStatement();

			ResultSet rs = statement
					.executeQuery("select studentname,score from mapstudentscore natural join student where score  in (select max(score) from mapstudentscore where subjectId ="+subjectId+")order by studentname limit 1");

			while (rs.next()) {
				map.put(rs.getString("studentname"),
						rs.getDouble("score"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return map;
	}
	public String getSubjectNameById(int subjectId){
		String subjectName="";
		try {
			Statement statement = dbUtil.getConnection().createStatement();
			ResultSet rs = statement.executeQuery("select subjectname from subject where subjectId="+subjectId);
			while (rs.next()) {
				subjectName= rs.getString("subjectName");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		
		return subjectName;
	}
}
