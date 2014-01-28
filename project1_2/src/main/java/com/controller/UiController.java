package com.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import com.google.gson.Gson;
import com.model.Subject;
import com.service.CourseService;
import com.service.SemesterService;
import com.service.SubjectService;

@Component
public class UiController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static final String ADMIN_PAGE = "jsp/admin.jsp";
	private static final String PIE_PAGE = "jsp/selectpie.jsp";

	private static final String ADD_STUDENT_PROFILE = "jsp/studentprofile.jsp";
	private String forward = "";
	private String action = "";

	@Autowired
	private CourseService courseService;

	@Autowired
	private SemesterService semesterService;

	@Autowired
	private SubjectService subjectService;

	public void init(ServletConfig config) {
		SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this,
				config.getServletContext());
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		action = request.getParameter("action");
		if (action.equalsIgnoreCase("newstudentprofile")) {

			try {

				setCoursesAndSemesters(request);

			} catch (Exception e) {
				e.printStackTrace();
			}
			forward = ADD_STUDENT_PROFILE;

			dispatchRequest(request, response, forward);

		}

		else if (action.equalsIgnoreCase("piechartselect")) {

			try {

				request = setCoursesAndSemesters(request);

			} catch (Exception e) {
				e.printStackTrace();
			}
			forward = PIE_PAGE;

			dispatchRequest(request, response, forward);

		} else if (action.equalsIgnoreCase("populateSubjects")) {

			int courseId = Integer.parseInt(request.getParameter("course"));
			int semesterId = Integer.parseInt(request.getParameter("semester"));

			List<Subject> subjects;
			try {
				subjects = subjectService.getSubjectsBySemesterAndCourse(
						semesterId, courseId);
				String json = new Gson().toJson(subjects);

				request.setAttribute("subjects", subjects);
				response.setContentType("application/json");
				response.setCharacterEncoding("UTF-8");
				response.getWriter().write(json);
			} catch (SQLException e) {

				e.printStackTrace();
			}

		}

	}

	private void dispatchRequest(HttpServletRequest request,
			HttpServletResponse response, String forward)
			throws ServletException, IOException {
		RequestDispatcher view = request.getRequestDispatcher(forward);
		view.forward(request, response);
	}

	private HttpServletRequest setCoursesAndSemesters(HttpServletRequest request)
			throws SQLException {
		request.setAttribute("courses", courseService.getAllCourses());
		request.setAttribute("semesters", semesterService.getAllSemesters());
		return request;
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		action = request.getParameter("action");

		if (action.equalsIgnoreCase("validateuser")) {
			forward = ADMIN_PAGE;
		}

		dispatchRequest(request, response, forward);
	}
}
