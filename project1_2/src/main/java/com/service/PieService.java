package com.service;

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
	
	public List<ChartData> getPiechartOutputs(int i) {
		return pieDao.getPiechartOutputs(i);
	}
	public List<ChartData> getPiechartOutputsWithCondition(int i, int si){
		return pieDao.getPiechartOutputsWithCondition(i, si);
	}
	public Map<String, Double> getHighestMarksForSubject(int subjectId){
		return pieDao.getHighestMarksForSubject(subjectId);
	}
	public String getSubjectNameById(int subjectId){
		return pieDao.getSubjectNameById(subjectId);
	}
}
