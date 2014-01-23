package com.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.model.Student;
import com.util.DbUtil;

public class StudentDao {
	
	private PreparedStatement preparedStatement;
	private Student student = new Student();
	private ResultSet rs;
	
	@Autowired
	DbUtil dbUtil;
	
	
	public int addStudent(Student student) throws SQLException {
		int studentId = 0;
		preparedStatement = dbUtil.getConnection()
				.prepareStatement("insert into student(studentname) values (?)");

		preparedStatement.setString(1, student.getStudentName());
		preparedStatement.executeUpdate();

		preparedStatement = dbUtil.getConnection()
				.prepareStatement("select last_insert_id() from student");
		if (rs.next()) {

			studentId = rs.getInt("last_insert_id()");
		}
		return studentId;

	}

	public List<Student> getAllStudents() throws SQLException {
		List<Student> students = new ArrayList<Student>();

		Statement statement = dbUtil.getConnection().createStatement();
		rs = statement.executeQuery("select * from student");
		while (rs.next()) {

			student.setStudentId((rs.getInt("studentid")));

			student.setStudentName((rs.getString("studentname")));

			students.add(student);
		}

		return students;

	}

	public String getStudentNameById(int studentId) throws SQLException {

		preparedStatement = (PreparedStatement)dbUtil.getConnection()
				.prepareStatement("select studentname from student where studentid= "
						+ studentId);

		rs = preparedStatement.executeQuery();
		if (rs.next()) {

			return rs.getString("studentname");

		}
		return null;

	}

}
