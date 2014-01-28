package com.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.model.Semester;
import com.util.DbUtil;

@Component
public class SemesterDao {
	
	
	@Autowired
	DbUtil dbUtil;
	private ResultSet rs;
	private Semester semester;

	public SemesterDao() {
			semester = new Semester();
	}

	public List<Semester> getAllSemesters() throws SQLException {
		List<Semester> semesters = new ArrayList<Semester>();
		
			Statement statement = dbUtil.getConnection().createStatement();
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
			PreparedStatement preparedStatement = dbUtil.getConnection()
					.prepareStatement("insert into semester(semestername) values (?)");

			preparedStatement.setString(1, semester);
			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
		

}
