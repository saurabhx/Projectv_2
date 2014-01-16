package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.model.Student;
import com.util.DbUtil;

public class StudentDao {
	private Connection connection;
	DbUtil DbUtil;
	Student student = new Student();

	public StudentDao() {
		connection = com.util.DbUtil.getConnection();
	}

	public void addStudent(Student student) {
		try {
			PreparedStatement preparedStatement = connection
					.prepareStatement("insert into student(studentname) values ( ?)");

			preparedStatement.setString(1, student.getStudentName());
			preparedStatement.executeQuery();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public List<Student> getAllStudents() {
		List<Student> students = new ArrayList<Student>();
		try {
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery("select * from student");
			while (rs.next()) {
				
				student.setStudentId((rs.getInt("studentid")));

				student.setStudentName((rs.getString("studentname")));

				students.add(student);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return students;

	}

}
