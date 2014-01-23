package com.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.model.Course;
import com.mysql.jdbc.PreparedStatement;
import com.util.DbUtil;

@Component
public class CourseDao {

	@Autowired
	DbUtil dbUtil;
	
	
	private PreparedStatement preparedStatement;
	private java.sql.ResultSet rs;

	
	public List<Course> getAllCourse() throws SQLException {

		List<Course> courses = new ArrayList<Course>();
		preparedStatement = (PreparedStatement) dbUtil.getConnection()
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
		
		preparedStatement = (PreparedStatement) dbUtil.getConnection()
				.prepareStatement("insert into course (coursename) values (?)");
		preparedStatement.setString(1, courseName);
		preparedStatement.executeUpdate();

	}

	public boolean courseDoesNotExist(String courseName) throws SQLException {
		
		try {
			preparedStatement = (PreparedStatement) dbUtil.getConnection()
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
