package com.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.model.Course;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.ResultSet;
import com.util.DbUtil;

public class CourseDao {

	private Connection connection;
	private PreparedStatement preparedStatement;
	private java.sql.ResultSet rs;

	public CourseDao() {
		connection = DbUtil.getConnection();
	}

	public List<Course> getAllCourse() throws SQLException {

		List<Course> courses = new ArrayList<Course>();
		preparedStatement = (PreparedStatement) connection
				.prepareStatement("select * from course");
		rs = preparedStatement.executeQuery();
		while (rs.next()) {
			Course course = new Course();
			course.setCourseName(rs.getString("coursename"));
			course.setCourseId(rs.getInt("courseid"));
			courses.add(course);
		}
		return courses;

	}

	public void addCourse(String courseName) throws SQLException {

		preparedStatement = (PreparedStatement) connection
				.prepareStatement("insert into course (coursename) values (?)");
		preparedStatement.setString(1, courseName);
		preparedStatement.executeUpdate();

	}

	public boolean courseDoesNotExist(String courseName) throws SQLException {
		
		try {
			preparedStatement = (PreparedStatement) connection
					.prepareStatement("select * from course where coursename="+courseName);
			rs=preparedStatement.executeQuery();
			
				
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(rs.next())
		{
		return false;
		}else return true;
		
	}

}
