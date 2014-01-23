package com.controller;

import java.awt.Color;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;

import com.dao.PieDao;
import com.model.ChartData;

@Configurable
public class PieChartGenerator extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static String PIE_CHART_PAGE="jsp/viewpie.jsp";
	private int subjectId;
	
	@Autowired
	private PieDao pieDao;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		subjectId=(Integer.parseInt(request.getParameter("subject")));
			
		List<ChartData>qr=pieDao.getPiechartOutputs(subjectId);
		List<ChartData>qr50=pieDao.getPiechartOutputsWithCondition(1,subjectId);
		List<ChartData>qr5075=pieDao.getPiechartOutputsWithCondition(2,subjectId);
		List<ChartData>qr75=pieDao.getPiechartOutputsWithCondition(3,subjectId);
					
		int n=qr.size();
		int x1=qr50.size();
		int x3=qr75.size();
		int x2=qr5075.size();
		
		float r[]= {x1,x2,x3,n};
		
		drawPieChart(request,response,r);
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
		
		 ServletContext sc = getServletContext();
		 String filename = sc.getRealPath("pie.png");
		 File file = new File(filename);
		
		 
		
		 ChartUtilities.saveChartAsPNG(file,chart,400,300);
		
		request.setAttribute("filepath",filename);
		request.setAttribute("subjectName",pieDao.getSubjectNameById(subjectId));
		
		Map<String, Double> m= pieDao.getHighestMarksForSubject(subjectId);
		request.setAttribute("toppers",m);
		
		forward=PIE_CHART_PAGE;
        RequestDispatcher view = request.getRequestDispatcher(forward);
        view.forward(request, response);
    
	} catch (Exception e) {
		System.out.println(e.toString());
	}
}
}


