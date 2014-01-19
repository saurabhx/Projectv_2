package com.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.model.ChartData;
import com.util.DbUtil;

public class PieDao {
	private Connection connection;

	
	public PieDao(){
		connection=DbUtil.getConnection();
	}
	public List<ChartData> getPiechartOutputs(int i) {
		List<ChartData> qr = new ArrayList<ChartData>();

		try {
			Statement statement = connection.createStatement();
			ResultSet rs = statement
					.executeQuery("select studentid,studentname,score from college.mapstudentscore natural join college.student where subjectid="
							+ i);
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

	public List<ChartData> getPiechartOutputsWithCondition(int i, int si) {
		List<ChartData> qr = new ArrayList<ChartData>();
		String query;
	
		if (i == 1) {
			query = "select studentid,studentname,score from college.mapstudentscore natural join college.student where subjectid=" + si
					+ " and score < 50";
		} else if (i == 2) {
			query = "select studentid,studentname,score from college.mapstudentscore natural join college.student where subjectid=" + si
					+ " and marks >= 50 and score <=75";
		} else {
			query = "select studentid,studentname,score from college.mapstudentscore natural join college.student where subjectid=" + si
					+ " and score > 75";
		}

		try {
			Statement statement = connection.createStatement();
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
			Statement statement = connection.createStatement();

			ResultSet rs = statement
					.executeQuery("select studentname,score from mapstudentscore natural join student where score  in (select max(score) from mapstudentscore where subjectId ="+subjectId+")");

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
			Statement statement = connection.createStatement();
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
