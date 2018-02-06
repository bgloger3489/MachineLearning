package Main;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JFrame;

import org.knowm.xchart.XChartPanel;
import org.knowm.xchart.XYChart;
import org.knowm.xchart.XYChartBuilder;
import org.knowm.xchart.XYSeries;
import org.knowm.xchart.style.Styler.ChartTheme;
import org.knowm.xchart.style.Styler.LegendPosition;
import org.knowm.xchart.style.markers.SeriesMarkers;

public class BETACharting{
	public static String SERIES_NAME = "Linear Regression Demo";

	public List<Double> yData;
	public List<Double> xData;
	public List<Double> scatteryData;
	//final BETACharting realtimeChart04;
    //final XChartPanel<XYChart> chartPanel;
    
	public static void main(String[] args) {
		
		
	 
	    // Schedule a job for the event-dispatching thread:
	    // creating and showing this application's GUI.
		//final BETACharting realtimeChart04 = new BETACharting();
		final XYChart realtimeChart04 = new XYChartBuilder().width(800).height(600).theme(ChartTheme.Matlab).title("Matlab Theme").xAxisTitle("X").yAxisTitle("Y").build();
		
		final XChartPanel<XYChart> chartPanel = new gettChart();
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			 
		      @Override
		      public void run() {
		 
		        // Create and set up the window.
		        JFrame frame = new JFrame("XChart");
		        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		        frame.add(chartPanel);
		 
		        // Display the window.
		        frame.pack();
		        frame.setVisible(true);
		      }
		    });
		
		//each ttime data is optimized this.updateChart gets called
	}

	
	/*public BETACharting(double[][] xxData, double[][] yyData) {
		xData = new ArrayList<Double>();
		scatteryData = new ArrayList<Double>();
	    for (int i = 0; i < xxData.length; i++) {
	    	xData.add(xxData[i][0]);
	    	scatteryData.add(yyData[i][0]);
	    }
	}*/
	
	
	public void updateData(double[][] xxData,double[][] slope, double[][] yint) {

	    yData = new ArrayList<Double>();
	    for (int i = 0; i < xxData.length; i++) {
	      yData.add(slope[0][0]*xxData[i][0] + yint[0][0]);
	    }

	}
	
	public void updateChart(double[][] xxData,double[][] slope, double[][] yint, double[][] yyData) {
		this.updateData(xxData, slope, yint, yyData);
        //this.updateSeries(SERIES_NAME, null, yData, xData);
		
		
		
	}
	
	
	public XYChart gettChart(double[][] xxData,double[][] slope, double[][] yint, double[][] yyData) {
		 
	    // Create Chart
	    XYChart chart = new XYChartBuilder().width(800).height(600).theme(ChartTheme.Matlab).title("Matlab Theme").xAxisTitle("X").yAxisTitle("Y").build();
	 
	    // Customize Chart
	    chart.getStyler().setPlotGridLinesVisible(false);
	    chart.getStyler().setXAxisTickMarkSpacingHint(100);
	    
	    // Line
	    xData = new ArrayList<Double>();
	    yData = new ArrayList<Double>();
	    for (int i = 0; i < xxData.length; i++) {
	      xData.add(xxData[i][0]);
	      yData.add(slope[0][0]*xxData[i][0] + yint[0][0]);
	    }
	    
	    //!!!!
	    XYSeries series = chart.addSeries("Linee", xData, yData);
	    series.setMarker(SeriesMarkers.NONE);

	    
	    //Scatter
	    chart.getStyler().setChartTitleVisible(false);
	    chart.getStyler().setLegendPosition(LegendPosition.InsideSE);
	    chart.getStyler().setMarkerSize(16);
	    
	    
	    // Series
	    List<Double> y2Data = new ArrayList<Double>();
	    for (int i = 0; i < xxData.length; i++) {
	      y2Data.add(yyData[i][0]);
	    }
	    
	    series = chart.addSeries("scater", xData, y2Data);
	    
	    
	    return chart;
	  }
	 
	  private List<Double> getYAxis(List<Integer> xData, double mean, double std) {
	 
	    List<Double> yData = new ArrayList<Double>(xData.size());
	 
	    for (int i = 0; i < xData.size(); i++) {
	      yData.add((1 / (std * Math.sqrt(2 * Math.PI))) * Math.exp(-(((xData.get(i) - mean) * (xData.get(i) - mean)) / ((2 * std * std)))));
	    }
	    return yData;
	  }
}
