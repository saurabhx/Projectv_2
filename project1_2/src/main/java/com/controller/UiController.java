package com.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.service.CourseService;
import com.service.SemesterService;
import com.service.SubjectService;


public class UiController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static String HOME_PAGE="/homepage.jsp";
	String forward="";
	String action;
	
    public UiController() {
        super();
        
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		

	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		action = request.getParameter("action");
        
        CourseService courseService=new CourseService();
        SemesterService semesterService=new SemesterService();
        SubjectService subjectService=new SubjectService();

        if (action.equalsIgnoreCase("insertMaster")){
            String courseName = request.getParameter("coursename");
            String subjectName = request.getParameter("subjectname");
            String semesterName = request.getParameter("semestername");
            
            
            courseService.writeToDatabase(courseName);
            subjectService.writeToDatabase(subjectName);
            semesterService.writeToDatabase(semesterName);
            forward = HOME_PAGE;
	}
	}

}
