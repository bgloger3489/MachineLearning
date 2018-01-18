package Main;

import org.knowm.xchart.SwingWrapper; 
import org.knowm.xchart.XYChart;
import org.knowm.xchart.demo.charts.ExampleChart;


import Nodes.Add;
import Nodes.AddSeq;
import Nodes.Exponent;
import Nodes.Mult;
import Nodes.Node;
import Nodes.Sub;

public class tempMain {
	
	public static int EMERGENCY_LENGTH;
	public static double LEARNING_RATE;
	
	
	public static void main(String[] args) {
		LEARNING_RATE = 0.001;
		test2();
	}
	
	public static void test2() {
		//y = (X*M1+B1)M2 + b2
		
		
		double[][] temp1 = {{1,2,3},{4,5,6},{7,8,9},{10,11,12}};//{{0},{1},{2}}; (3,4)
		Tensor X = new Tensor(new Matrix(temp1));
		EMERGENCY_LENGTH = temp1.length;
			
						// dog predictor, cat predictor//, pig predictor
		double[][] temp2 = {{1,1,1},{2,2,2}};
		Tensor m1 = new Tensor((new Matrix(temp2)).T());
				
		Node n1 = new Mult(X,m1);
		Tensor z1 = new Tensor(n1);		
		
		//should be an array of predicitons
		// dog prediction, cat prediction
		// {.50, .60}
		// {.30, .90}
		// {.10, .20}
		// {.90, ,20}
		
		prarr(z1.matrix.vals);
		double[][] temp3 = {{10},{20}};//{{0},{1},{2}};
		Tensor b1 = new Tensor(new Matrix(temp3));
			
		
		Node n2 = new Add(z1,b1);
		Tensor z2 = new Tensor(n2);
		
		
		p("");
		prarr(z2.matrix.vals);
		//predictions for each {{1},{2},{0},{1}}

						// dog, cat, pig, pig
		double[][] temp4 = {{1},{0},{2},{2}};
		Tensor Y = new Tensor(new Matrix (temp4));
		//make a new Y tensor everytime you forward pass??? so you can easily subtract
		//new Tensor -> fill it with the correct answer in all slots, then make the correct one (-margin)
		
		//
		//double[][] temp5 = fill{{},{},{}}
		
		
		//SumMax
		
		
		String[] YLabels = {"cat","dog","pig"};
	}
	
	public static void test1() {
		
		//y=3x+2
		
		
		double[][] temp1 = {{0},{1},{2},{3},{4},{5}};
		Tensor X = new Tensor(new Matrix(temp1));
		EMERGENCY_LENGTH = temp1.length;
		
		//double[][] temp2 = {{2},{5},{8},{11},{14},{17}};//{{1},{6},{7},{13},{11},{16}};
		double[][] temp2 = {{1},{6},{7},{13},{11},{17}};
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
}
