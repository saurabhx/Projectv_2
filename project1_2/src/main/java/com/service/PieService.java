package com.service;
import java.sql.Connection;
import com.util.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import com.model.Score;


public class PieService {
	private Connection connection;
	DbUtil DbUtil;


	public PieService() {

		connection = DbUtil.getConnection();
	}

	public List<Score> getPiechartOutputs(int i) {
		List <Score>qr = new ArrayList<Score>();
		
		try {
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery("SELECT * FROM mapstudentscore where subjectid ="+i);
			while (rs.next()) {
				Score out = new Score();
				out.setStudentId((rs.getInt("studentid")));
				out.setMarks(Double.parseDouble(rs.getString("score")));
				
				qr.add(out);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return qr;
	}
	public List<Score> getPiechartOutputsWithCondition(int i, int si) {
		List <Score>qr = new ArrayList<Score>();
		String query;
		//String subjectid="1";
		if(i==1){
			query="SELECT * FROM mapstudentscore where subjectid ="+si+" and score < 50";
		}
		else if(i==2){
			query="SELECT * FROM mapstudentscore where subjectid ="+si+" and score >= 50 and score <=75";
		}
		else{
			query="SELECT * FROM mapstudentscore where subjectid ="+si+" and score > 75";
		}
			
		try {
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery(query);
			while (rs.next()) {
				Score out = new Score();
				out.setStudentId((rs.getInt("studentid")));
				out.setMarks(Double.parseDouble(rs.getString("score")));
				
				qr.add(out);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return qr;
	}
	public void insertScore(int studentId, int subId, int score) {
		// TODO Auto-generated method stub
		
		try {
			Statement statement = connection.createStatement();
			String query="Insert into score(studentId,subjectId,marks) values("+studentId+","+subId+","+score+")";
			statement.executeUpdate(query);
			
			}
		 catch (SQLException e) {
			 System.out.print("CUSTOM");
			e.printStackTrace();
		}
		
	}
	public void insertStudent(String sName, int cId, int sId) {
		
		
		// TODO Auto-generated method stub
		
		try {
			Statement statement = connection.createStatement();
			String query2="Insert into student(studentName,courseId,semesterId) values('"+sName+"','"+cId+"','"+sId+"')";
			statement.executeUpdate(query2);
			
			}
		 catch (SQLException e) {
			 System.out.print("CUSTOM");
			e.printStackTrace();
		}
		
	}
	



}
