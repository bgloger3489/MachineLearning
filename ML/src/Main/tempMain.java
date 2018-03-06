package Main;

import org.knowm.xchart.SwingWrapper; 
import org.knowm.xchart.XYChart;
import org.knowm.xchart.demo.charts.ExampleChart;


import Nodes.Add;
import Nodes.AddSeq;
import Nodes.AddToRow;
import Nodes.Exponent;
import Nodes.MatMult;
import Nodes.Max;
import Nodes.Mult;
import Nodes.Node;
import Nodes.SpecialNode;
import Nodes.Sub;

public class tempMain {
	
	public static  double MARGIN;
	public static int EMERGENCY_LENGTH;
	public static Matrix LEARNING_RATE;
	public static int NUM_CLASSIFICATIONS;
	public static int NUM_PICTURES;
	
	public static void main(String[] args) {
		double[][] temp = {{.001}};
		LEARNING_RATE = new Matrix(temp);
		
		double[][] temp1 = {{11,22,33,44}};
		Tensor X = new Tensor(new Matrix(temp1));
		EMERGENCY_LENGTH = temp1.length;
		NUM_PICTURES = temp1.length;
		NUM_CLASSIFICATIONS = 10;

		p("X:");
		X.printShape();
		prarr(X.matrix.vals);
		
		double[][] temp2 = {{0,1,2},{3,4,5},{6,7,8},{9,10,11}};
		Tensor m1 = new Tensor(new Matrix(temp2));
		p("m1");
		m1.printShape();
		prarr(m1.matrix.vals);
		
		Node n1 = new Mult(X,m1);
		Tensor z1 = new Tensor(n1);		

		p("z1");
		z1.printShape();
		prarr(z1.matrix.vals);
		
		double[][] temp3 = {{0.1,0.2, 0.3}};
		Tensor b1 = new Tensor(new Matrix(temp3));	
		
		Node n2 = new Add(z1,b1);
		Tensor z2 = new Tensor(n2);
		
		
		p("z2");
		z2.printShape();
		z2.prarr();
		
		
		System.out.println("backprop:/n");
		z2.backprop(m1).prarr();
	}
	
	
	public static void test4(double[][] pictureArray, int[] labelArray) {
		
		//------------------< SETTING UP >--------------------
		p("SETTING UP:\n");
		
		double[][] temp = {{.001}};
		
		LEARNING_RATE = new Matrix(temp);
		
		
		//double[][] temp1 = {{1,2},{4,5},{7,8},{10,11}};
		Tensor X = new Tensor(new Matrix(pictureArray));
		EMERGENCY_LENGTH = pictureArray.length;
		NUM_PICTURES = pictureArray.length;
		NUM_CLASSIFICATIONS = 10;
		
						// dog predictor, cat predictor, pig predictor
		//double[][] temp2 = {{1,1},{2,2},{3,3}};
		
		p("X:");
		X.printShape();
		
		
		Matrix tempp2 = Matrix.ones(3072, NUM_CLASSIFICATIONS);
		Tensor m1 = new Tensor(tempp2);
		p("m1");
		m1.printShape();
		
		Node n1 = new Mult(X,m1);
		Tensor z1 = new Tensor(n1);		
		
		//should be an array of predicitons
		// dog prediction, cat prediction
		// {.50, .60}

		p("z1");
		z1.printShape();
		//prarr(z1.matrix.vals);
		//double[][] temp3 = {{.1,.2,.3}};//{{0},{1},{2}};
		Tensor b1 = new Tensor(Matrix.ones(1, NUM_CLASSIFICATIONS));	
		
		Node n2 = new Add(z1,b1);
		Tensor z2 = new Tensor(n2);
		
		
		p("z2");
		z2.printShape();

		int[] allY = labelArray;
		
		Node specialLink = new SpecialNode(z2,  allY);
		
		Tensor Ycompare = new Tensor(specialLink);
		p("Ycompare");
		Ycompare.printShape();

		
		Node n3 = new Sub(z2, Ycompare);
		Tensor z3 = new Tensor(n3);

		p("z3");
		z3.printShape();

		
		Node n4 = new Max(z3);
		Tensor z4 = new Tensor(n4);
		
		p("z4:");
		z4.printShape();

		
		Node n5 = new AddToRow(z4);
		Tensor z5 = new Tensor(n5);
		
		p("z5:");
		z5.printShape();

		
		String[] YLabels = {"cat","dog","pig"};
		
		
		
		//-----------< BACKPROP TEST >----------------
		p("\n\n\nBACKPROP TEST:\n");
		
		
		
		p("z1.back:");
		z1.backprop(m1).printShape();

		p("z2.back:");
		z2.backprop(m1).printShape();

		p("Ycompare.back:");
		Ycompare.backprop(m1).printShape();

		p("z3.back:");
		z3.backprop(m1).printShape();

		p("z4.back:");
		z4.backprop(m1).printShape();

		p("z5.back:");
		z5.backprop(m1).printShape();
		
		
		
		
		//-----------< LOOPING >-----------
		
		Tensor[] tenArr = {z1,z2,Ycompare,z3,z4,z5};
		Matrix gradm;
		Matrix gradb;
		//double prevLoss = z5.matrix.vals[0][0];
		/*for(int i = 0; i < 10000; i++) {
			
			//prevLoss = z5.matrix.vals[0][0];
			gradm = z5.backprop(m1);
			gradb = z5.backprop(b1);
			
			m1.matrix = Matrix.subMatrix(m1.matrix, Matrix.multiplyMatricies(gradm, LEARNING_RATE));
			
			b1.matrix = Matrix.subMatrix(b1.matrix,  Matrix.multiplyMatricies(gradb, LEARNING_RATE));
			
			Tensor.updateTensors(tenArr);
			//if(prevLoss <= z5.matrix.vals[0][0]) {
			//	break;
			//}
		}*/
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
