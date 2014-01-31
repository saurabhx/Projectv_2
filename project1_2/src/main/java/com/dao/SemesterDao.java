package com.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.cfg.beanvalidation.HibernateTraversableResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.model.Semester;
import com.util.DbUtil;
import com.util.HibernateUtil;

@Component
public class SemesterDao {

	@Autowired
	DbUtil dbUtil;
	private ResultSet resultSet;
	private Semester semester;
	private PreparedStatement preparedStatement;

	public SemesterDao() {
		semester = new Semester();
	}

	public List<Semester> getAllSemesters() throws SQLException {
		Session session = HibernateUtil.getSessionFactory().openSession();
		List<Semester> semesters = session.createCriteria(Semester.class).list();
		return semesters;

	}

	public void addSemester(String semester) throws SQLException {
		
			preparedStatement = dbUtil.getConnection().prepareStatement(
					"insert into semester(semestername) values (?)");

			preparedStatement.setString(1, semester);
			preparedStatement.executeUpdate();

		
	}

}
