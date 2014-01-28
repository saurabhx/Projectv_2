package com.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.model.Course;
import com.util.DbUtil;

@Component
public class CourseDao {

	@Autowired
	DbUtil dbUtil;
	
	private PreparedStatement preparedStatement;
	private ResultSet resultSet;

	
	public List<Course> getAllCourse() throws SQLException {

		List<Course> courses = new ArrayList<Course>();
		preparedStatement = dbUtil.getConnection()
				.prepareStatement("select * from course");
		resultSet = preparedStatement.executeQuery();
		while (resultSet.next()) {
			Course course = new Course();
			course.setCourseName(resultSet.getString("coursename"));
			course.setCourseId(resultSet.getInt("courseid"));
			courses.add(course);
			
		}
		return courses;

	}

	public void addCourse(String courseName) throws SQLException {
		
		preparedStatement =  dbUtil.getConnection()
				.prepareStatement("insert into course (coursename) values (?)");
		preparedStatement.setString(1, courseName);
		preparedStatement.executeUpdate();

	}


}
