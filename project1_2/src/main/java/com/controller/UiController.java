package com.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.CourseDao;
import com.dao.SemesterDao;
import com.dao.SubjectDao;
import com.google.gson.Gson;
import com.model.Subject;


public class UiController extends HttpServlet {
	private static final long serialVersionUID = 1L;
		
	private static final String ADMIN_PAGE = "jsp/admin.jsp";
	private static final String PIE_PAGE= "jsp/selectpie.jsp";

	private static final String ADD_STUDENT_PROFILE = "jsp/studentprofile.jsp";	
	private String forward="";
	private String action="";
	
    public UiController() {
        super();
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		action= request.getParameter("action");

		if(action.equalsIgnoreCase("newstudentprofile")){
        	
        	CourseDao courseDao= new CourseDao();
        	SemesterDao semesterDao= new SemesterDao();
        	try{
        		request.setAttribute("courses", courseDao.getAllCourse());
        		request.setAttribute("semesters", semesterDao.getAllSemesters());
        		
        	}catch(Exception e){ e.printStackTrace();}
        	forward =ADD_STUDENT_PROFILE;

    		RequestDispatcher view = request.getRequestDispatcher(forward);
            view.forward(request, response);

		}

		else if(action.equalsIgnoreCase("piechartselect")){
        	
        	CourseDao courseDao= new CourseDao();
        	SemesterDao semesterDao= new SemesterDao();
        	try{
        		request.setAttribute("courses", courseDao.getAllCourse());
        		request.setAttribute("semesters", semesterDao.getAllSemesters());
        		
        	}catch(Exception e){ e.printStackTrace();}
        	forward =PIE_PAGE;

    		RequestDispatcher view = request.getRequestDispatcher(forward);
            view.forward(request, response);

		}
		else if (action.equalsIgnoreCase("populateSubjects")){
        	
        	int cid=Integer.parseInt(request.getParameter("course"));
        	int sid=Integer.parseInt(request.getParameter("semester"));
         
        	SubjectDao subjectDao = new SubjectDao();
        	List<Subject> subjects = subjectDao.getSubjectList(sid, cid);
            
        	String json= new Gson().toJson(subjects);
            
        	request.setAttribute("subjects", subjects);
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(json);
        }

	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		action = request.getParameter("action");

        if(action.equalsIgnoreCase("validateuser")){
        	forward =ADMIN_PAGE;
        }
              
        RequestDispatcher view = request.getRequestDispatcher(forward);
        view.forward(request, response);
	}
}
