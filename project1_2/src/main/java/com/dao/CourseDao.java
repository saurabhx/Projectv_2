package com.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.model.Course;
import com.util.DbUtil;
import com.util.HibernateUtil;

@Component
public class CourseDao {

	@Autowired
	DbUtil dbUtil;
	
	private PreparedStatement preparedStatement;
	private ResultSet resultSet;

	
	public List<Course> getAllCourse() throws SQLException {
		Session session= HibernateUtil.getSessionFactory().openSession();
		List<Course> courses = new ArrayList<Course>();
		courses=session.createCriteria(Course.class).list();
		return courses;

	}

	public void addCourse(String courseName) throws SQLException {
		
		preparedStatement =  dbUtil.getConnection()
				.prepareStatement("insert into course (coursename) values (?)");
		preparedStatement.setString(1, courseName);
		preparedStatement.executeUpdate();
		

	}


}
