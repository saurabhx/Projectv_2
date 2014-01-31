package com.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.model.ChartData;
import com.util.DbUtil;
import com.util.HibernateUtil;

@Component
public class PieDao {

	@Autowired
	DbUtil dbUtil;
	private PreparedStatement preparedStatement;
	private ResultSet resultSet;

	public List<ChartData> getPiechartOutputs(int subjectId)
			throws SQLException {
		List<ChartData> queryResult = new ArrayList<ChartData>();
		Session session= HibernateUtil.getSessionFactory().openSession();
		Query query = session.createQuery("select studentid,studentname,score from mapstudentscore natural join student where subjectid="+ subjectId);
		queryResult=query.list();
		return queryResult;
	}

	public List<ChartData> getPiechartOutputsWithCondition(int range,
			int subjectId) throws SQLException {
		List<ChartData> qr = new ArrayList<ChartData>();
		String query;

		if (range == 1) {
			query = "select studentid,studentname,score from college.mapstudentscore natural join college.student where subjectid="
					+ subjectId + " and score < 50";
		} else if (range == 2) {
			query = "select studentid,studentname,score from college.mapstudentscore natural join college.student where subjectid="
					+ subjectId + " and score >= 50 and score <=75";
		} else {
			query = "select studentid,studentname,score from college.mapstudentscore natural join college.student where subjectid="
					+ subjectId + " and score > 75";
		}
		
		Session session= HibernateUtil.getSessionFactory().openSession();
		Query queryHibernate = session.createSQLQuery(query);
		qr=queryHibernate.list();
	
		return qr;
	}

	

	public Map<String, Double> getHighestMarksForSubject(int subjectId)
			throws SQLException {
		Map<String, Double> map = new HashMap<String, Double>();
		Session session= HibernateUtil.getSessionFactory().openSession();
		Query query	= session.createSQLQuery("select studentname,score from mapstudentscore natural join student where score  in (select max(score) from mapstudentscore where subjectId ="
								+ subjectId + ")order by studentname limit 1");
		map= (Map<String, Double>) query.list();
		
		return map;
	}

	public String getSubjectNameById(int subjectId) throws SQLException {
		String subjectName = "";
		Session session= HibernateUtil.getSessionFactory().openSession();
		Query query = session.createSQLQuery("select subjectname from subject where subjectId=" + subjectId);
		subjectName = query.list().toString();		
		
		return subjectName;
	}

}
