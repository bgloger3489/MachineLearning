package Main;

import javax.swing.JFrame;

import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.XChartPanel;
import org.knowm.xchart.XYChart;

import Nodes.AddSeq;
import Nodes.Exponent;
import Nodes.Mult;
import Nodes.Node;
import Nodes.Sub;

public class LinearRegression extends Thread{
	
	public static int EMERGENCY_LENGTH;
	public static double LEARNING_RATE;
	public static double MARGIN;
	public double[][] temp1;
	public double[][] temp2;
	
	public LinearRegression(double[][] tempp1, double[][] tempp2) {
		prarr(tempp1);
		prarr(tempp2);
		
		this.temp1 = tempp1;
		this.temp2 = tempp2;
		
	}
	
	public void run() {
		
		//hyper parameters
		LEARNING_RATE = 0.01;
		MARGIN = 1;
		
		
		//double[][] temp1 = {{0},{1},{2},{3},{4},{5}};
		Tensor X = new Tensor(new Matrix(temp1));
		EMERGENCY_LENGTH = temp1.length;
		
		//double[][] temp2 = {{2},{5},{8},{11},{14},{17}};//{{1},{6},{7},{13},{11},{16}};
		//double[][] temp2 = {{1},{6},{7},{13},{11},{17}};
		Tensor Y = new Tensor(new Matrix(temp2));
		
		double[][] temp3 = {{-10}};
		Tensor b = new Tensor(new Matrix(temp3));
		
		double[][] temp4 = {{49}};
		Tensor m = new Tensor(new Matrix(temp4));
		
		Node n1 = new Mult(X, m);
		Tensor z1 = new Tensor(n1);
		
		Node n2 = new Sub(Y, z1);
		Tensor z2 = new Tensor(n2);
		
		Node n3 = new Sub(z2, b);
		Tensor z3 = new Tensor(n3);
		
		Node n4 = new Exponent(z3, 2);
		Tensor z4 = new Tensor(n4);
		
		Node n5 = new AddSeq(z4);
		Tensor z5 = new Tensor(n5);
		
		prarr(z2.backprop(m).vals);
		
		double[][] temppp = {{0}};
		prarr(Matrix.subMatrix(new Matrix(temppp), X.matrix).vals);
		
		
		
		
		Tensor[] tenArr = {z1,z2,z3,z4,z5};
		double gradm;
		double gradb;
		double prevLoss = z5.matrix.vals[0][0];
		
		ChartingTest f = new ChartingTest();
		
		JFrame frame = new JFrame();
		XYChart chart = f.gettChart(temp1, m.matrix.vals, b.matrix.vals, temp2);
		XChartPanel<XYChart> chartPanel = new XChartPanel<XYChart>(chart);
		frame.add(chartPanel);
		chartPanel.setBounds(0,0,700,700);
		
		frame.setSize(900,900);
		frame.revalidate();
	    frame.setVisible(true);
		
		for(int i = 0; i < 1000; i++) {
			
			prevLoss = z5.matrix.vals[0][0];
			gradm = z5.backprop(m).vals[0][0];
			gradb = z5.backprop(b).vals[0][0];
			
			if(i%10 == 0) {
				System.out.println(i);
				p("m:"+m.matrix.vals[0][0]);
				p("b:"+b.matrix.vals[0][0]);
				p("m':"+z5.backprop(m).vals[0][0]);
				p("b':"+z5.backprop(b).vals[0][0]);
				p("----------------------------");
				//ChartingTest f = new ChartingTest();
				chart = f.gettChart(temp1, m.matrix.vals, b.matrix.vals, temp2);
				frame.remove(chartPanel);
				chartPanel = new XChartPanel<XYChart>(chart);
				frame.add(chartPanel);
			    frame.revalidate();
			}
			
			double[][] newM = {{(m.matrix.vals[0][0] - gradm *LEARNING_RATE)}};
			m.matrix = new Matrix(newM);
			
			double[][] newB = {{(b.matrix.vals[0][0] - gradb *LEARNING_RATE)}};
			b.matrix = new Matrix(newB);
			
			//p("m':"+m.matrix.vals[0][0]);
			//p("b':"+b.matrix.vals[0][0]);
			
			Tensor.updateTensors(tenArr);
			if(prevLoss + MARGIN <= z5.matrix.vals[0][0]) {
				break;
			}
			
			
			
		}
		System.out.println("final:");
		p("m:"+m.matrix.vals[0][0]);
		p("b':"+b.matrix.vals[0][0]);
		
		chart = f.gettChart(temp1, m.matrix.vals, b.matrix.vals, temp2);
		frame.remove(chartPanel);
		chartPanel = new XChartPanel<XYChart>(chart);
		frame.add(chartPanel);
	    frame.revalidate();
		
	}
	
	//Utility Functions
			
			public static void p(String s) {
				System.out.println(s);
			}
			
			public static void prarr(double[][] temp) {
				for(int i =0; i < temp.length; i++) {
					if(temp.length> 1)
						System.out.print("\n{");
					for(int j = 0; j < temp[0].length; j++) {
						System.out.print(temp[i][j] + ", ");
					}
					if(temp.length> 1)
						System.out.println("}");
					else
						p("");
				}
			}
			
			public static double[][] T(double[][] asd){
				double[][] temp = new double[asd[0].length][asd.length];
				
				for(int i = 0; i < asd.length; i++) {
					for(int j = 0; j < asd[0].length; j++) {
						temp[j][i] = asd[i][j];
					}
				}
				
				return temp;
			}
}
