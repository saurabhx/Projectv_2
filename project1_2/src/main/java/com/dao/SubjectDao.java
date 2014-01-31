package com.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.model.Subject;
import com.util.DbUtil;
import com.util.HibernateUtil;

@Component
public class SubjectDao {

	private Subject subject = new Subject();

	@Autowired
	DbUtil dbUtil;

	public List<Subject> getAllSubjects() throws SQLException {
		List<Subject> subjects = new ArrayList<Subject>();
		Session session = HibernateUtil.getSessionFactory().openSession();
		subjects = session.createCriteria(Subject.class).list();
		return subjects;

	}

	public List<Subject> getSubjectsBySemesterAndCourse(int semesterId,
			int courseId) throws SQLException {
		
		List<Subject> subjects = new ArrayList<Subject>();
		Session session = HibernateUtil.getSessionFactory().openSession();
		
		Query query = session.createSQLQuery(
				"select subjectid,subjectname from subject"
				+" natural join mapsemestersubject where semesterid ="
				+ semesterId + " and courseid =" + courseId);

//		Criteria c= session.createCriteria(Subject.class);
//		c.setFetchMode("mapsemesterstudent", FetchMode.JOIN);
//		c.add(Restrictions.eq("semesterid", semesterId));
//		c.add(Restrictions.eq("courseid", courseId));
		subjects = query.list();
		return subjects;

	}

	public void addSubject(String subject) throws SQLException {

		PreparedStatement preparedStatement = dbUtil.getConnection()
				.prepareStatement(
						"insert into subject(subjectname) values ( ?)");

		preparedStatement.setString(1, subject);
		preparedStatement.executeUpdate();

	}

}
