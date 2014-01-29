package com.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dao.PieDao;
import com.model.ChartData;

@Component
public class PieService {
	@Autowired
	PieDao pieDao;
	
	public List<ChartData> getPiechartOutputs(int subjectId) throws SQLException {
		return pieDao.getPiechartOutputs(subjectId);
	}
	public List<ChartData> getPiechartOutputsWithCondition(int range, int subjectId) throws SQLException{
		return pieDao.getPiechartOutputsWithCondition(range, subjectId);
	}
	public Map<String, Double> getHighestMarksForSubject(int subjectId) throws SQLException{
		return pieDao.getHighestMarksForSubject(subjectId);
	}
	public String getSubjectNameById(int subjectId) throws SQLException{
		return pieDao.getSubjectNameById(subjectId);
	}
}
