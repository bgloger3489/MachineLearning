package Main;

import java.util.ArrayList;
import java.util.List;

import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.XYChart;
import org.knowm.xchart.XYChartBuilder;
import org.knowm.xchart.XYSeries;
import org.knowm.xchart.XYSeries.XYSeriesRenderStyle;
import org.knowm.xchart.demo.charts.ExampleChart;
import org.knowm.xchart.demo.charts.theme.ThemeChart03;
import org.knowm.xchart.style.Styler.ChartTheme;
import org.knowm.xchart.style.Styler.LegendPosition;
import org.knowm.xchart.style.markers.SeriesMarkers;

public class ChartingTest implements ExampleChart<XYChart>{
	public static void main(String[] args) {
		 
	    //ExampleChart<XYChart> exampleChart = new ThemeChart03();
	    //XYChart chart = exampleChart.getChart();
	    //new SwingWrapper<XYChart>(chart).displayChart();
		
		ExampleChart<XYChart> f = new ChartingTest();
	    XYChart chart = f.getChart();
	    new SwingWrapper<XYChart>(chart).displayChart();
	  }
	 
	  public XYChart getChart() {
	 
	    // Create Chart
	    XYChart chart = new XYChartBuilder().width(800).height(600).theme(ChartTheme.Matlab).title("Matlab Theme").xAxisTitle("X").yAxisTitle("Y").build();
	 
	    // Customize Chart
	    chart.getStyler().setPlotGridLinesVisible(false);
	    chart.getStyler().setXAxisTickMarkSpacingHint(100);
	    
	    // Series
	    List<Integer> xData = new ArrayList<Integer>();
	    List<Integer> y1Data = new ArrayList<Integer>();
	    for (int i = 0; i < 20; i++) {
	      xData.add(i);
	      y1Data.add(3*i+2);
	    }
	    
	    XYSeries series = chart.addSeries("Linee", xData, y1Data);
	    series.setMarker(SeriesMarkers.NONE);
	    //series = chart.addSeries("Gaussian 2", xData, y2Data);
	    //series.setMarker(SeriesMarkers.NONE);
	    //series = chart.addSeries("Difference", xData, y3Data);
	    //series.setMarker(SeriesMarkers.NONE);
	 
	    //chart.getStyler().setPlotGridLinesVisible(false);
	   //chart.getStyler().setXAxisTickMarkSpacingHint(100);
	    
	    //chart.getStyler().setDefaultSeriesRenderStyle(XYSeriesRenderStyle.Scatter);
	    chart.getStyler().setChartTitleVisible(false);
	    chart.getStyler().setLegendPosition(LegendPosition.InsideSE);
	    chart.getStyler().setMarkerSize(16);
	    
	    
	    // Series
	    List<Integer> y2Data = new ArrayList<Integer>();
	    for (int i = 0; i < 20; i++) {
	      y2Data.add(5*i+2);
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
