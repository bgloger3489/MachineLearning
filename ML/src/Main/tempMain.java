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
	public static double LEARNING_RATE;
	public static int NUM_CLASSIFICATIONS;
	public static int NUM_PICTURES;
	
	public static void main(String[] args) {
		LEARNING_RATE = 0.001;
		MARGIN = 10;
		test3();
	}
	
	
	/**
	 * 
	 * //compareY = {
		//{32, lambda, 32}, 
		//{lambda, 25, 25}, 
		//{102, 012, lambda}, 
		//{129, 129, lambda}}
		
		//compareY.backprop(Tensor a)
		// ->>> returns picked out gradient{
		//{z2.grad[0][1], 0, z2.grad[0][1]},
		//{0, z2.grad[1][0], z2.grad[1][0]},
		//{z2.grad[2][2], z2.grad[2][2], 0},
		//{z2.grad[3][2], z2.grad[3][2], 0}}
		
		
		//
		//CONSTRUCTOR:
		//createSpecialTensor(double[] allY, Tensor whereToFind){
		//  **Creates compareY shown above**
		//	double[][] emptyArray = new double[numClassifications][allY.length]
		//
		//	for(int i = 0; i < allY.length; i++){
		//		
		//		for(int j = 0; j < emptyGradArray.lentj; j ++){
		//			if(j == allY[i])
		//				emptyArray[i][j] = lambda;
		//			else
		//				emptyArray[i][j] = whereToFind.matrix.vals[i][allY[i]];
		//		}
		//	}
		//
		//
		//	super(new Matrix(emptyArray));
		//	**stores allY & wherToFind, for when .backprop is called
		//	this.allY = allY;
		//	this.whereToFind = whereToFind
		//	}
		//
		//
		//
		//
		//	@overide
		//	backprop(Tensor goal){
		//
		//	double[][] emptyGradArray = new double[numClassifiers][allY.length] OR [emptyArray.length][emptyArray[0].length]
		//	
		//	//**Creates instruction array for how to calculate gradient as shown above (Where to find them -> z2 -> z2.backprop)
		//	
		//	z2Grad = whereToFind.backprop(goal)
		//
		//	for(int i = 0; i < allY.length; i++){
		//		
		//		for(int j = 0; j < numClassifications; j ++){
		//			if(j == allY[i])
		//				emptyGradArray[i][j] = 0;
		//			else
		//				emptyGradArray[i][j] = z2Grad[i][allY[i]]
		//		}
		//	}
		//	return emptyGradArray; 
		//}
		
		//make a new Y tensor everytime you forward pass??? so you can easily subtract
		//new Tensor -> fill it with the correct answer in all slots, then make the correct one (-margin)
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * */
	
	//MAKE THE CREATION OF EMPTY ARRAYS IN THE TENSOR CLASS RATHER THAN IN THE NODE CLASSES -> MAKE MATRIX>ONES ? MATRIX.ZEROS (SAME SHAPE AS ORGINONAL)
	public static void test3() {
		
		//double[][] allX = {{1,2,3},{4,5,6},{7,8,9},{10,11,12}};
		//NUM_PICTURES = allX.length;
		
		//ONE PICTURE temp1 = allX[currentPictureIndex];
		double[][] temp1 = {{1,2},{4,5},{7,8},{10,11}};
		Tensor X = new Tensor(new Matrix(temp1));
		EMERGENCY_LENGTH = temp1.length;
		NUM_PICTURES = temp1.length;
		
						// dog predictor, cat predictor, pig predictor
		double[][] temp2 = {{1,1},{2,2},{3,3}};
		Tensor m1 = new Tensor(new Matrix(T(temp2)));
		NUM_CLASSIFICATIONS = temp2.length;
		
		Node n1 = new MatMult(X,m1);
		Tensor z1 = new Tensor(n1);		
		
		//should be an array of predicitons
		// dog prediction, cat prediction
		// {.50, .60}

		p("z1");
		prarr(z1.matrix.vals);
		double[][] temp3 = {{.1,.2,.3}};//{{0},{1},{2}};
		Tensor b1 = new Tensor(new Matrix(temp3));
			
		
		Node n2 = new Add(z1,b1);
		Tensor z2 = new Tensor(n2);
		
		
		p("z2");
		prarr(z2.matrix.vals);
		
		//p("z2back");
		//prarr(z2.backprop(m1).vals);


		int[] allY = {1,0,2,2};
		
		Node specialLink = new SpecialNode(z2,  allY);
		
		Tensor Ycompare = new Tensor(specialLink);
		p("Ycompare");
		prarr(Ycompare.matrix.vals);
		
		//p("b1.bak");
		//prarr(b1.backprop(m1).vals);
		
		//p("z1.back");
		//prarr(z1.backprop(m1).vals);
		
		p("z2back");
		prarr(z2.backprop(m1).vals);
		
		p("YcompareBack");
		prarr(Ycompare.backprop(m1).vals);
		
		Node n3 = new Sub(z2, Ycompare);
		Tensor z3 = new Tensor(n3);

		p("z3");
		prarr(z3.matrix.vals);
		
		

		p("z3.back");
		prarr(z3.backprop(m1).vals);
		
		
		Node n4 = new Max(z3);
		Tensor z4 = new Tensor(n4);
		
		p("z4:");
		prarr(z4.matrix.vals);
		
		p("z4.back:");
		prarr(z4.backprop(m1).vals);
		
		Node n5 = new AddToRow(z4);
		Tensor z5 = new Tensor(n5);
		
		p("z5:");
		prarr(z5.matrix.vals);
		
		p("z5.back:");
		prarr(z5.backprop(m1).vals);
		
		
		
		
		
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
