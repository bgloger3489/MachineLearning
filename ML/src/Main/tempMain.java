package Main;

import Nodes.AddSeq;
import Nodes.Mult;
import Nodes.Node;

public class tempMain {
	
	public static void main(String[] args) {
		//p(Matrix.multiplyMatricies(m1, m2).getShape()[0] + "," + Matrix.multiplyMatricies(m1, m2).getShape()[1] + "\n\n");
		//prarr(Matrix.multiplyMatricies(m1, m2).vals);
		
		
		double[][] temp1  = {{1.0},{2.0},{1.0}};
		//Matrix m1 = new Matrix(temp1);
		Tensor t1 = new Tensor(new Matrix(temp1));
		
		
		double[][] temp2  = {{2.0}};
		//Matrix m2 = new Matrix(temp2);
		Tensor t2 = new Tensor(new Matrix(temp2));
		
		
		Node n1 = new Mult(t1,t2);
		Tensor t3 = new Tensor(n1);
		
		prarr(t3.matrix.vals);
		
	}
	
	public static void p(String s) {
		System.out.println(s);
	}
	
	public static void prarr(double[][] temp) {
		for(int i =0; i < temp.length; i++) {
			p("\n");
			for(int j = 0; j < temp[0].length; j++) {
				p(""+temp[i][j]);
			}
		}
	}
}
