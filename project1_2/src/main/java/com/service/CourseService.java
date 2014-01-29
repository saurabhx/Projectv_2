package com.service;


import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dao.CourseDao;
import com.model.Course;

@Component
public class CourseService {
	@Autowired
	CourseDao courseDao;
		
	public List<Course> getAllCourses() throws SQLException {
		
		return courseDao.getAllCourse();
			
	}

	public void createCourse(String courseName) throws SQLException {
		courseDao.addCourse(courseName);
	}

}
