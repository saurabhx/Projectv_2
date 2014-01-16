package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.model.Semester;

import com.util.DbUtil;

public class SemesterDao {
	private Connection connection;
	DbUtil DbUtil;
	Semester semester = new Semester();

	public SemesterDao() {
		connection = com.util.DbUtil.getConnection();
	}

	public List<Semester> getAllSemesters() throws SQLException {
		List<Semester> semesters = new ArrayList<Semester>();
		
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery("select * from Semester");
			while (rs.next()) {

				semester.setSemesterId((rs.getInt("studentid")));

				semester.setSemesterName((rs.getString("studentname")));

				semesters.add(semester);
			}
		
		return semesters;

	}

	public void addSemester() {
		try {
			PreparedStatement preparedStatement = connection
					.prepareStatement("insert into semester(semestername) values (?)");

			preparedStatement.setString(1, semester.getSemesterName());
			preparedStatement.executeQuery();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
