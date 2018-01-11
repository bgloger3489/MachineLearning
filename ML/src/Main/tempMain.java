package Main;

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
		double[][] temp1 = {{0},{1},{2},{3},{4},{5}};
		Tensor X = new Tensor(new Matrix(temp1));
		EMERGENCY_LENGTH = temp1.length;
		
		//double[][] temp2 = {{2},{5},{8},{11},{14},{17}};//{{1},{6},{7},{13},{11},{16}};
		double[][] temp2 = {{1},{6},{7},{13},{11},{16}};
		Tensor Y = new Tensor(new Matrix(temp2));
		Y.matrix = Y.T();
		Node n1 = new Mult(X,Y);
		Tensor z1 = new Tensor(n1);
	}
	
	public static void test1() {
		
		//y=3x+2
		
		
		double[][] temp1 = {{0},{1},{2},{3},{4},{5}};
		Tensor X = new Tensor(new Matrix(temp1));
		EMERGENCY_LENGTH = temp1.length;
		
		//double[][] temp2 = {{2},{5},{8},{11},{14},{17}};//{{1},{6},{7},{13},{11},{16}};
		double[][] temp2 = {{1},{6},{7},{13},{11},{16}};
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
		
		
		Tensor[] tenArr = {z1,z2,z3,z4,z5};
		double gradm;
		double gradb;
		double prevLoss = z5.matrix.vals[0][0];
		for(int i = 0; i < 10000; i++) {
			prevLoss = z5.matrix.vals[0][0];
			gradm = z5.backprop(m).vals[0][0];
			gradb = z5.backprop(b).vals[0][0];
			
			p("m:"+m.matrix.vals[0][0]);
			p("b:"+b.matrix.vals[0][0]);
			
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
			p("----------------------------");
			
			
		}
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
