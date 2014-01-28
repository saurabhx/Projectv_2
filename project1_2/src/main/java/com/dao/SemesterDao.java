package com.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
	private ResultSet resultSet;
	private Semester semester;
	private PreparedStatement preparedStatement;

	public SemesterDao() {
		semester = new Semester();
	}

	public List<Semester> getAllSemesters() throws SQLException {
		List<Semester> semesters = new ArrayList<Semester>();

		preparedStatement = dbUtil.getConnection().prepareStatement(
				"select * from semester");

		resultSet = preparedStatement.executeQuery();
		while (resultSet.next()) {
			semester = new Semester();
			semester.setSemesterId((resultSet.getInt("semesterId")));
			semester.setSemesterName((resultSet.getString("semesterName")));
			semesters.add(semester);
		}

		return semesters;

	}

	public void addSemester(String semester) throws SQLException {
		
			preparedStatement = dbUtil.getConnection().prepareStatement(
					"insert into semester(semestername) values (?)");

			preparedStatement.setString(1, semester);
			preparedStatement.executeUpdate();

		
	}

}
