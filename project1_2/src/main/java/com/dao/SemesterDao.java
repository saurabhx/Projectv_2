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
	ResultSet rs;
	Semester semester;

	public SemesterDao() {
		connection = com.util.DbUtil.getConnection();
		semester = new Semester();
	}

	public List<Semester> getAllSemesters() throws SQLException {
		List<Semester> semesters = new ArrayList<Semester>();
		
			Statement statement = connection.createStatement();
		    rs = statement.executeQuery("select * from semester");
			while (rs.next()) {
				semester= new Semester();
				semester.setSemesterId((rs.getInt("semesterId")));
				semester.setSemesterName((rs.getString("semesterName")));
				semesters.add(semester);
			}
		
		return semesters;

	}

	public void addSemester(String semester) {
		try {
			PreparedStatement preparedStatement = connection
					.prepareStatement("insert into semester(semestername) values (?)");

			preparedStatement.setString(1, semester);
			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public boolean semesterDoesNotExist(String semesterName) {
		try {
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery("select * from semester where semestername="+semesterName);
			if(rs!=null)
				return false;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return true;
		
		
	}

}
