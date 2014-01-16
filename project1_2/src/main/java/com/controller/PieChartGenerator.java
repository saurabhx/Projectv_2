package com.controller;
import java.awt.Color;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;




import com.model.Score;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;

import com.service.PieService;


/**
 * Servlet implementation class PieChartGenerator
 */
public class PieChartGenerator extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private PieService service;
	private Score score;

	public PieChartGenerator(){
    	super();
    	service = new PieService();
    	
	}
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			doTestPieChart(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			score = new Score();
			score.setSubId(Integer.parseInt(request.getParameter("outPie")));
			List<Score>qr=service.getPiechartOutputs(score.getSubjectId());
			List<Score>qr50=service.getPiechartOutputsWithCondition(1,score.getSubjectId());
			List<Score>qr5075=service.getPiechartOutputsWithCondition(2,score.getSubjectId());
			List<Score>qr75=service.getPiechartOutputsWithCondition(3,score.getSubjectId());
			int n=qr.size();
			int x1=qr50.size();
			int x3=qr75.size();
			int x2=qr5075.size();
			
			float r[]= {x1,x2,x3,n};
			//doTestPieChart(request, response);
			drawPieChart(request,response,r);
	}

	protected void drawPieChart(HttpServletRequest request,
			HttpServletResponse response, float[] r) throws IOException, ServletException {
		OutputStream out = response.getOutputStream();

		try {
			DefaultPieDataset dataset = new DefaultPieDataset();

			dataset.setValue("Marks <50", (r[0]/r[3])*100);
			dataset.setValue("Marks b/w 50-75",(r[1]/r[3])*100);
			dataset.setValue("Marks >75", (r[2]/r[3])*100);
				
			
			JFreeChart chart = ChartFactory.createPieChart("Pie",dataset, true, false, false);
			chart.setBackgroundPaint(Color.white);

			response.setContentType("image/png");
			ChartUtilities.writeChartAsPNG(out, chart, 400, 300);
			/*
			 * ServletContext sc = getServletContext(); String filename =
			 * sc.getRealPath("pie.png"); File file = new File(filename);
			 * ChartUtilities.saveChartAsPNG(file,chart,400,300);
			 */

		} catch (Exception e) {
			System.out.println(e.toString());
		} finally {
			out.close();
		}

	}

	protected void doTestPieChart(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {
		OutputStream out = response.getOutputStream();

		try {
			DefaultPieDataset dataset = new DefaultPieDataset();

			dataset.setValue("<50", 192);
			dataset.setValue(">75", 125);
			dataset.setValue("50-75", 236);
				
			
			JFreeChart chart = ChartFactory.createPieChart("Pie",dataset, true, false, false);
			chart.setBackgroundPaint(Color.white);

			response.setContentType("image/png");
			ChartUtilities.writeChartAsPNG(out, chart, 400, 300);
			/*
			 * ServletContext sc = getServletContext(); String filename =
			 * sc.getRealPath("pie.png"); File file = new File(filename);
			 * ChartUtilities.saveChartAsPNG(file,chart,400,300);
			 */

		} catch (Exception e) {
			System.out.println(e.toString());
		} finally {
			out.close();
		}

	}

}
