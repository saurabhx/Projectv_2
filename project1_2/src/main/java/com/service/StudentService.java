package com.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dao.StudentDao;
import com.model.Student;

@Component
public class StudentService {
	@Autowired
	StudentDao studentDao;

	public void addStudent(Student student) throws SQLException {
			studentDao.addStudent(student);
	}

 public List<Student> getAllStudents() throws SQLException{
	 return studentDao.getAllStudents();
 }
 
 public String getStudentNameById(int studentId) throws SQLException{
	 return studentDao.getStudentNameById(studentId);
 }
}
