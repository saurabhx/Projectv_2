package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.model.Subject;
import com.util.DbUtil;

public class SubjectDao {
	private Connection connection;
	DbUtil DbUtil;
	Subject subject = new Subject();

	public SubjectDao() {
		connection = com.util.DbUtil.getConnection();
	}

	public List<Subject> getAllSubjects() {
		List<Subject> subjects = new ArrayList<Subject>();
		try {
			Statement statement = connection.createStatement();

			ResultSet rs = statement.executeQuery("select * from subject");

			while (rs.next()) {

				subject.setSubjectId(rs.getInt("subjectid"));
				subject.setSubjectName(rs.getString("subjectname"));
				subjects.add(subject);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return subjects;

	}

	public void addSubject(Subject subject) {
		try {
			PreparedStatement preparedStatement = connection
					.prepareStatement("insert into subject(subjectname) values ( ?)");

			preparedStatement.setString(1, subject.getSubjectName());
			preparedStatement.executeQuery();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
