package com.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.model.Student;
import com.util.DbUtil;

@Component
public class StudentDao {
	
	private PreparedStatement preparedStatement;
	private Student student = new Student();
	
	
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
		ResultSet resultSet= preparedStatement.executeQuery();
		
		if (resultSet.next()) {

			studentId = resultSet.getInt("last_insert_id()");
		}
		return studentId;

	}

	public List<Student> getAllStudents() throws SQLException {
		List<Student> students = new ArrayList<Student>();
		preparedStatement = dbUtil.getConnection()
				.prepareStatement("select * from student");
		ResultSet resultSet = preparedStatement.executeQuery();
		while (resultSet.next()) {

			student.setStudentId((resultSet.getInt("studentid")));

			student.setStudentName((resultSet.getString("studentname")));

			students.add(student);
		}

		return students;

	}

	public String getStudentNameById(int studentId) throws SQLException {

		preparedStatement = dbUtil.getConnection()
				.prepareStatement("select studentname from student where studentid= "
						+ studentId);

		ResultSet resultSet= preparedStatement.executeQuery();
		if (resultSet.next()) {

			return resultSet.getString("studentname");

		}
		return null;

	}

}
