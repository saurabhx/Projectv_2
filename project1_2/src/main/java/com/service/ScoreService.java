package com.service;

import java.sql.SQLException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dao.ScoreDao;

@Component
public class ScoreService {
	@Autowired
	ScoreDao scoreDao;

	public void addScore(int studentId, int subjectId, double score)
			throws SQLException {
		scoreDao.addScore(subjectId, subjectId, score);
	}

	public Map<Integer, Double> getScore(int subjectId) throws SQLException {
		return scoreDao.getScore(subjectId);
	}

}
