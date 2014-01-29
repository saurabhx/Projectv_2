package com.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.model.Subject;
import com.util.DbUtil;

@Component
public class SubjectDao {

	private Subject subject = new Subject();

	@Autowired
	DbUtil dbUtil;

	public List<Subject> getAllSubjects() throws SQLException {
		List<Subject> subjects = new ArrayList<Subject>();

		PreparedStatement preparedStatement = dbUtil.getConnection()
				.prepareStatement("select * from subject");
		ResultSet resultSet = preparedStatement.executeQuery();

		while (resultSet.next()) {
			subject = new Subject();
			subject.setSubjectId(resultSet.getInt("subjectid"));
			subject.setSubjectName(resultSet.getString("subjectname"));
			subjects.add(subject);
		}

		return subjects;

	}

	public List<Subject> getSubjectsBySemesterAndCourse(int semesterId,
			int courseId) throws SQLException {
		List<Subject> subjects = new ArrayList<Subject>();

		PreparedStatement preparedStatement = dbUtil.getConnection()
				.prepareStatement(
						"select * from mapsemestersubject natural join subject where semesterid ="
								+ semesterId + " and courseid =" + courseId);
		ResultSet resultSet = preparedStatement.executeQuery();

		while (resultSet.next()) {
			subject = new Subject();
			subject.setSubjectId(resultSet.getInt("subjectid"));
			subject.setSubjectName(resultSet.getString("subjectname"));
			subjects.add(subject);
		}

		return subjects;

	}

	public void addSubject(String subject) throws SQLException {

		PreparedStatement preparedStatement = dbUtil.getConnection()
				.prepareStatement(
						"insert into subject(subjectname) values ( ?)");

		preparedStatement.setString(1, subject);
		preparedStatement.executeUpdate();

	}

}
