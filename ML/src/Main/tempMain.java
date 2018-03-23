package Main;

import org.knowm.xchart.SwingWrapper; 
import org.knowm.xchart.XYChart;
import org.knowm.xchart.demo.charts.ExampleChart;


import Nodes.Add;
import Nodes.AddSeq;
import Nodes.AddToRow;
import Nodes.Compare;
import Nodes.Exponent;
import Nodes.MatMult;
import Nodes.Max;
import Nodes.Mult;
import Nodes.Node;
import Nodes.Sigma;
import Nodes.SpecialNode;
import Nodes.Sub;

public class tempMain {
	
	public static  double MARGIN;
	public static int EMERGENCY_LENGTH;
	public static Matrix LEARNING_RATE;
	public static int NUM_CLASSIFICATIONS;
	public static int NUM_PICTURES;
	public static int NUM_FEATURES;
	public static double[][] CURRENT_PICTURE;
	
	public static void main(String[] args) {
		//------------------< SETTING UP >--------------------
		
		double[][] tempLR = {{.01}};
		LEARNING_RATE = new Matrix(tempLR);
		
		Tensor X = new Tensor(Matrix.random(1, 3), true);
		
		Tensor m1 = new Tensor(Matrix.random(3, 10));
		p("m1");
		m1.printShape();
		prarr(m1.matrix.vals);
		
		Node nn1 = new Mult(X,m1);
		Tensor zz1 = new Tensor(nn1);
		
		Node n1 = new Exponent(zz1,2);
		Tensor z1 = new Tensor(n1);
		
		int[][] temp = {{-1}};
		Node n2 = new Sigma(z1,temp);
		Tensor z2 = new Tensor(n2);
		
		
		p("\n\nz2.components");
		prarr(z2.components(m1).vals);
		
		m1.matrix = Matrix.subMatrix(m1.matrix, Matrix.multScalar(z2.components(m1), LEARNING_RATE));
	
		p("m1'");
		m1.printShape();
		prarr(m1.matrix.vals);
		
		
		/*p("SETTING UP:\n");
			
		double[][] temp = {{.01}};
		LEARNING_RATE = new Matrix(temp);
		double[][] xtemp = {{11,22,33,44,1}};
		//double[][] mtemp = {{0,1,2},{3,4,5},{6,7,8},{9,10,11},{.1,.2,.3}};
		
		NUM_PICTURES = 1;
		NUM_FEATURES = xtemp[0].length;
		NUM_CLASSIFICATIONS = 3;
		MARGIN = 10; 
		
		CURRENT_PICTURE = xtemp;
		
		Tensor X = new Tensor(new Matrix(CURRENT_PICTURE), true);
		p("X:");
		X.printShape();
		prarr(X.matrix.vals);
		
		Tensor m1 = new Tensor(Matrix.random(NUM_FEATURES, NUM_CLASSIFICATIONS));
		p("m1");
		m1.printShape();
		prarr(m1.matrix.vals);
		
		Node n1 = new Mult(X,m1);
		Tensor z1 = new Tensor(n1);		

		p("z1");
		z1.printShape();
		prarr(z1.matrix.vals);
		
		z1.components(m1);
		
		//Tensor b1 = new Tensor(Matrix.random(1, NUM_FEATURES));
		//p("b1");
		//b1.printShape();
		//prarr(b1.matrix.vals);
		
		//Node n2 = new Add(z1,b1);
		//Tensor z2 = new Tensor(n2);
		//p("z2");
		//z2.printShape();
		//prarr(z2.matrix.vals);
		
		int[][] Yi = {{0}};
		
		Node n3 = new Compare(z1, Yi);
		Tensor z3 = new Tensor(n3);
		p("z3");
		z3.printShape();
		prarr(z3.matrix.vals);
		
		Node n4 = new Max(z3);
		Tensor z4 = new Tensor(n4);
		p("z4");
		z4.printShape();
		prarr(z4.matrix.vals);
		
		Node n5 = new Sigma(z4, Yi);
		Tensor z5 = new Tensor(n5);
		p("z5");
		z5.printShape();
		prarr(z5.matrix.vals);
		
		
		
		Node nn1 = new Exponent(m1, 2);
		Tensor zz1 = new Tensor(nn1);
		
		int[][] invalid = {{-1}};
		Node nn2 = new Sigma (zz1, invalid);
		Tensor zz2 = new Tensor(nn2);
		
		Node n6 = new Add(z5,zz2);
		Tensor z6 = new Tensor(n6);
		
		
		
		p("z6.components");
		prarr(z6.components(m1).vals);
		
		m1.matrix = Matrix.subMatrix(m1.matrix, Matrix.multScalar(z6.components(m1), LEARNING_RATE));
	
		p("m1");
		m1.printShape();
		prarr(m1.matrix.vals);/*/
		
		
		
		
	}
	
	public static void loadNextPicture(double[] newPic) {
		for(int i = 0; i< newPic.length; i++) {
			CURRENT_PICTURE[0][i] = newPic[i];
		}
	}
	
	public static void test5(double[][] pictureArray, int[] labelArray) {
		//------------------< SETTING UP >--------------------
			p("SETTING UP:\n");
				
			double[][] temp = {{.01}};
			LEARNING_RATE = new Matrix(temp);
			
			EMERGENCY_LENGTH = pictureArray.length;
			NUM_PICTURES = pictureArray.length;
			NUM_FEATURES = pictureArray[0].length;
			NUM_CLASSIFICATIONS = 10;
			MARGIN = 10; 
			
			CURRENT_PICTURE = new double[1][NUM_FEATURES];
			//Loads next picture into CURRENT_PICTURE
			loadNextPicture(pictureArray[0]);
			
			Tensor X = new Tensor(new Matrix(CURRENT_PICTURE), true);
			p("X:");
			X.printShape();
			prarr(X.matrix.vals);
			
			Tensor m1 = new Tensor(Matrix.random(NUM_FEATURES, NUM_CLASSIFICATIONS));
			p("m1");
			m1.printShape();
			prarr(m1.matrix.vals);
			
			Node n1 = new Mult(X,m1);
			Tensor z1 = new Tensor(n1);		

			p("z1");
			z1.printShape();
			prarr(z1.matrix.vals);
			
			z1.components(m1);
			
			//Tensor b1 = new Tensor(Matrix.random(1, NUM_FEATURES));
			//p("b1");
			//b1.printShape();
			//prarr(b1.matrix.vals);
			
			//Node n2 = new Add(z1,b1);
			//Tensor z2 = new Tensor(n2);
			//p("z2");
			//z2.printShape();
			//prarr(z2.matrix.vals);
			
			int[][] Yi = {{labelArray[0]}};
			
			Node n3 = new Compare(z1, Yi);
			Tensor z3 = new Tensor(n3);
			p("z3");
			z3.printShape();
			prarr(z3.matrix.vals);
			
			Node n4 = new Max(z3);
			Tensor z4 = new Tensor(n4);
			p("z4");
			z4.printShape();
			prarr(z4.matrix.vals);
			
			Node n5 = new Sigma(z4, Yi);
			Tensor z5 = new Tensor(n5);
			p("z5");
			z5.printShape();
			prarr(z5.matrix.vals);
			
			
			
			Node nn1 = new Exponent(m1, 2);
			Tensor zz1 = new Tensor(nn1);
			
			int[][] invalid = {{-1}};
			Node nn2 = new Sigma (zz1, invalid);
			Tensor zz2 = new Tensor(nn2);
			
			Node n6 = new Add(z5,zz2);
			Tensor z6 = new Tensor(n6);
			
			
			
			p("z6.components");
			prarr(z6.components(m1).vals);
			
			m1.matrix = Matrix.subMatrix(m1.matrix, Matrix.multScalar(z6.components(m1), LEARNING_RATE));
		
			p("m1'");
			m1.printShape();
			//prarr(m1.matrix.vals);
			
			
			
			
			
			
		Tensor[] tenArr = {z1,z3,z4,z5,zz1,zz2,z6};

		for(int i = 1; i < 1/*NUM_PICTURES -1*/; i++) {
			System.out.println("Type"+i + "Yi: "+ Yi[0][0]);
			
			m1.matrix = Matrix.subMatrix(m1.matrix, Matrix.multScalar(z6.components(m1), LEARNING_RATE));
			//prarr(m1.matrix.vals);
			loadNextPicture(pictureArray[i]);
			
			X = new Tensor(new Matrix(CURRENT_PICTURE), true);
			Yi[0][0] = labelArray[i];
			
			Tensor.updateTensors(tenArr);
			//if(prevLoss <= z5.matrix.vals[0][0]) {
			//	break;
			//}
		}
		
		loadNextPicture(pictureArray[NUM_PICTURES - 1]);
		X = new Tensor(new Matrix(CURRENT_PICTURE), true);
		Yi[0][0] = labelArray[NUM_PICTURES - 1];
		
		Tensor.updateTensors(tenArr);
		
		//GUESS;
		double greatest = 0;
		int idx = 0;
		for(int i =0; i < z1.matrix.vals[0].length; i++) {
			if(z1.matrix.vals[0][i] > greatest) {
				idx = i;
				greatest = z1.matrix.vals[0][i];
			}
		}
		
		System.out.println("________________________________");
		System.out.println("Guess: "+ idx + " Correct: " + Yi[0][0]);
		prarr(z1.matrix.vals);
		//ReadCIFAR10.createImage(m1.matrix.vals);
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
