package Main;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.XChartPanel;
import org.knowm.xchart.XYChart;

import Nodes.AddSeq;
import Nodes.Exponent;
import Nodes.Mult;
import Nodes.Node;
import Nodes.Sub;

public class QuadraticRegression extends Thread{
	public static int EMERGENCY_LENGTH;
	public static double LEARNING_RATE;
	public static double MARGIN;
	public double[][] temp1;
	public double[][] temp2;
	
	public QuadraticRegression(double[][] tempp1, double[][] tempp2) {
		prarr(tempp1);
		prarr(tempp2);
		
		this.temp1 = tempp1;
		this.temp2 = tempp2;
		
	}
	
	public void run() {
		
		//hyper parameters
		LEARNING_RATE = 0.001;
		MARGIN = 1;
		
		
		//double[][] temp1 = {{0},{1},{2},{3},{4},{5}};
		Tensor X = new Tensor(new Matrix(temp1));
		EMERGENCY_LENGTH = temp1.length;
		
		//double[][] temp2 = {{2},{5},{8},{11},{14},{17}};//{{1},{6},{7},{13},{11},{16}};
		//double[][] temp2 = {{1},{6},{7},{13},{11},{17}};
		Tensor Y = new Tensor(new Matrix(temp2));
		
		double[][] temp3 = {{-10}};
		Tensor b = new Tensor(new Matrix(temp3));
		
		double[][] temp4 = {{3}};
		Tensor m = new Tensor(new Matrix(temp4));
		
		double[][] temp5 = {{4}};
		Tensor a = new Tensor(new Matrix(temp4));
		
		Node n1 = new Mult(X, m);
		Tensor z1 = new Tensor(n1);
		
		////					////
		Node nn1 = new Exponent(X,2);
		Tensor zz1 = new Tensor(nn1);
		
		Node nn2 = new Mult(zz1,a);
		Tensor zz2 = new Tensor(nn2);
		////					////
		
		Node n2 = new Sub(Y, z1);
		Tensor z2 = new Tensor(n2);
		
		Node n3 = new Sub(z2, b);
		Tensor z3 = new Tensor(n3);
		
		////					////
		Node nn3 = new Sub(z3, zz2);
		Tensor zz3 = new Tensor(nn3);
		
		Node n4 = new Exponent(zz3, 2);
		Tensor z4 = new Tensor(n4);
		
		Node n5 = new AddSeq(z4);
		Tensor z5 = new Tensor(n5);
		
		prarr(z2.backprop(m).vals);
		
		double[][] temppp = {{0}};
		prarr(Matrix.subMatrix(new Matrix(temppp), X.matrix).vals);
		
		
		
		
		Tensor[] tenArr = {z1,zz1,zz2,z2,z3,zz3,z4,z5};
		double gradm;
		double gradb;
		double grada;
		double prevLoss = z5.matrix.vals[0][0];
		
		ChartingTest f = new ChartingTest();
		
		JFrame frame = new JFrame();
		XYChart chart = f.gettChart(temp1, a.matrix.vals, m.matrix.vals, b.matrix.vals, temp2);
		XChartPanel<XYChart> chartPanel = new XChartPanel<XYChart>(chart);
		frame.add(chartPanel);
		chartPanel.setBounds(0,0,700,700);
	   
		//JTextArea tf = new JTextArea("Text field 1");
		
		//tf.setBounds(800, 800,50,50);
		//frame.add(tf);
		frame.setSize(900,900);
		frame.revalidate();
	    frame.setVisible(true);
		
		
		for(int i = 0; i < 23000; i++) {
			
			prevLoss = z5.matrix.vals[0][0];
			gradm = z5.backprop(m).vals[0][0];
			gradb = z5.backprop(b).vals[0][0];
			grada = z5.backprop(a).vals[0][0];
			
			if(i%100 == 0) {
				System.out.println(i);
				p("m:"+m.matrix.vals[0][0]);
				p("b:"+b.matrix.vals[0][0]);
				p("a:"+a.matrix.vals[0][0]);
				p("m':"+z5.backprop(m).vals[0][0]);
				p("b':"+z5.backprop(b).vals[0][0]);
				p("a':"+z5.backprop(a).vals[0][0]);
				p("----------------------------");


				chart = f.gettChart(temp1, a.matrix.vals, m.matrix.vals, b.matrix.vals, temp2);
				frame.remove(chartPanel);
				chartPanel = new XChartPanel<XYChart>(chart);
				frame.add(chartPanel);
			    frame.revalidate();
			}
			
			double[][] newM = {{(m.matrix.vals[0][0] - gradm *LEARNING_RATE/EMERGENCY_LENGTH)}};
			m.matrix = new Matrix(newM);
			
			double[][] newB = {{(b.matrix.vals[0][0] - gradb *LEARNING_RATE/EMERGENCY_LENGTH)}};
			b.matrix = new Matrix(newB);
			
			double[][] newA = {{(a.matrix.vals[0][0] - grada *LEARNING_RATE/EMERGENCY_LENGTH)}};
			a.matrix = new Matrix(newA);
			
			//p("m':"+m.matrix.vals[0][0]);
			//p("b':"+b.matrix.vals[0][0]);
			
			Tensor.updateTensors(tenArr);
			if(prevLoss + MARGIN <= z5.matrix.vals[0][0]) {
				p("prevloss" + (prevLoss + MARGIN));
				p("currloss" + z5.matrix.vals[0][0]);
				break;
			}
			
			
			
			
			
		}
		System.out.println("final:");
		p("m:"+m.matrix.vals[0][0]);
		p("b':"+b.matrix.vals[0][0]);
		p("a':"+a.matrix.vals[0][0]);
		
		chart = f.gettChart(temp1, a.matrix.vals, m.matrix.vals, b.matrix.vals, temp2);
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
