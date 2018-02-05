package Main;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;

import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.XYChart;

import Nodes.AddSeq;
import Nodes.Exponent;
import Nodes.Mult;
import Nodes.Node;
import Nodes.Sub;

public class BETADemonstation {
	
	public static int EMERGENCY_LENGTH;
	public static double LEARNING_RATE;
	public static boolean ready;
	
	
	public static void main(String[] args) {
		///tempMain.LEARNING_RATE = 0.001;
		//tempMain.MARGIN = 10;
		//tempMain.test1();
		LEARNING_RATE = 0.001;
		//runML(new double[1][1], new double[1][1]);
		
		//runML(new double[1][1], new double[1][1]);
		//JFRAME FOR USER INPUT
		
		JFrame f = new JFrame();
	    
		JTextField x = new JTextField("2,5,8,11,14", 30);
		JTextField y = new JTextField("0,1,2,3,4", 30);
		
		double[][] temp1;
		double[][] temp2;
		JPanel p = new JPanel();
		
		p.add(x);
		p.add(y);
		
		//f.getContentPane().add(x);
		//f.getContentPane().add(y);
	    
	    JButton b = new JButton("Submit");
	    
	    
	    
	    b.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		if(checkValid(x.getText()) && checkValid(y.getText()))
	    			runML(parseInput(x.getText()),parseInput(y.getText()));
	    	}
	    });
	    
	    p.add(b);
	    
	    f.add(p);
	    f.pack();
	    //setSize(220, 400);
	    f.setLocationRelativeTo(null);  
	    f.setVisible(true);
		
	}
	
	public static void runML(double[][] tempp1, double[][] tempp2) {
		//y=3x+2
		
		
		
		
				double[][] temp1 = {{0},{1},{2},{3},{4},{5}};
				Tensor X = new Tensor(new Matrix(tempp1));
				EMERGENCY_LENGTH = temp1.length;
				
				//double[][] temp2 = {{2},{5},{8},{11},{14},{17}};//{{1},{6},{7},{13},{11},{16}};
				double[][] temp2 = {{1},{6},{7},{13},{11},{17}};
				Tensor Y = new Tensor(new Matrix(tempp2));
				
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
				for(int i = 0; i < 10000; i++) {
					
					prevLoss = z5.matrix.vals[0][0];
					gradm = z5.backprop(m).vals[0][0];
					gradb = z5.backprop(b).vals[0][0];
					
					if(i%100 == 0) {
						System.out.println(i);
						p("m:"+m.matrix.vals[0][0]);
						p("b':"+b.matrix.vals[0][0]);
						p("----------------------------");
						ChartingTest f = new ChartingTest();
					    XYChart chart = f.gettChart(temp1, m.matrix.vals, b.matrix.vals, temp2);
					    new SwingWrapper<XYChart>(chart).displayChart();
					}
					
					double[][] newM = {{(m.matrix.vals[0][0] - gradm *LEARNING_RATE)}};
					m.matrix = new Matrix(newM);
					
					double[][] newB = {{(b.matrix.vals[0][0] - gradb *LEARNING_RATE)}};
					b.matrix = new Matrix(newB);
					
					//p("m':"+m.matrix.vals[0][0]);
					//p("b':"+b.matrix.vals[0][0]);
					
					Tensor.updateTensors(tenArr);
					if(prevLoss <= z5.matrix.vals[0][0]) {
						break;
					}
					
					
					
				}
				System.out.println("final:");
				p("m:"+m.matrix.vals[0][0]);
				p("b':"+b.matrix.vals[0][0]);
				
				ChartingTest f = new ChartingTest();
			    XYChart chart = f.gettChart(temp1, m.matrix.vals, b.matrix.vals, temp2);
			    new SwingWrapper<XYChart>(chart).displayChart();
		
	}
	
	//Utility Functions
		
		public static boolean checkValid(String s) {
	
			for(int i =0; i< s.length(); i++) {
				String temp = s.substring(i,i+1);
				try {
					Integer.parseInt(temp);
				}catch(Exception e) {
					if(!(temp.compareTo(",") == 0))
						return false;
				}
				
			}
			return true;
		}
	
	
		public static double[][] parseInput(String s){
			String[] temp = s.split(",");
			
			double[][] finall = new double[1][temp.length];
			
			for(int i =0; i < temp.length; i++) {
				finall[0][i] = Double.parseDouble(temp[i]);
			}
			return finall;
		}
	
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
