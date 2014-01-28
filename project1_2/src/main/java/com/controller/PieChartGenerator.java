package com.controller;

import java.awt.Color;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import com.model.ChartData;
import com.service.PieService;

@Component
public class PieChartGenerator extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static String PIE_CHART_PAGE="jsp/viewpie.jsp";
	private int subjectId;
	
	@Autowired
	private PieService pieService;
	
	 public void init(ServletConfig config) {
	        SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this,
	          config.getServletContext());
	      }
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		subjectId=(Integer.parseInt(request.getParameter("subject")));
			
		List<ChartData>queryResult=pieService.getPiechartOutputs(subjectId);
		List<ChartData>queryResult50=pieService.getPiechartOutputsWithCondition(1,subjectId);
		List<ChartData>queryResult5075=pieService.getPiechartOutputsWithCondition(2,subjectId);
		List<ChartData>queryResult75=pieService.getPiechartOutputsWithCondition(3,subjectId);
					
		int n=queryResult.size();
		int x1=queryResult50.size();
		int x3=queryResult75.size();
		int x2=queryResult5075.size();
		
		float pieChartRatios[]= {x1,x2,x3,n};
		
		drawPieChart(request,response,pieChartRatios);
}

protected void drawPieChart(HttpServletRequest request,
		HttpServletResponse response, float[] r) throws IOException, ServletException {
	
	String forward="";

	try {
		DefaultPieDataset dataset = new DefaultPieDataset();

		dataset.setValue("Marks <50", (r[0]/r[3])*100);
		dataset.setValue("Marks b/w 50-75",(r[1]/r[3])*100);
		dataset.setValue("Marks >75", (r[2]/r[3])*100);
			
		
		JFreeChart chart = ChartFactory.createPieChart("Pie",dataset, true, false, false);
		chart.setBackgroundPaint(Color.white);
		
		 String filename = "C:\\Users\\xebia - pc\\Desktop\\New folder\\pie.png";
		 File file = new File(filename);
		
		 
		
		 ChartUtilities.saveChartAsPNG(file,chart,400,300);
		
		request.setAttribute("filepath",filename);
		request.setAttribute("subjectName",pieService.getSubjectNameById(subjectId));
		
		Map<String, Double> m= pieService.getHighestMarksForSubject(subjectId);
		request.setAttribute("toppers",m);
		
		forward=PIE_CHART_PAGE;
        RequestDispatcher view = request.getRequestDispatcher(forward);
        view.forward(request, response);
    
	} catch (Exception e) {
		System.out.println(e.toString());
	}
}
}


