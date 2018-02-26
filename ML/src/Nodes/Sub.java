package Nodes;

import Main.Matrix;
import Main.Tensor;
import Main.tempMain;

public class Sub extends Node{

	public Sub(Tensor a, Tensor b) {
		super(a, b);
	}
	
	public String tos() {
		return "(-)";
	}
	
	//ALWAYS a - b !
	public Matrix backprop(Tensor goal) {
		
		//if(a == goal) {
			//double[][] temp = {{1}};
			//return Matrix.subMatrix(new Matrix(temp), b.backprop(goal));
		//}
		//if(b == goal) {
			//double[][] temp = {{1}};
			//return Matrix.subMatrix(a.backprop(goal), new Matrix(temp));
		//}
		//tempMain.prarr(a.backprop(goal).vals);
		//tempMain.prarr(b.backprop(goal).vals);
		
		return Matrix.subMatrix(a.backprop(goal), b.backprop(goal));
	}
	
	public Matrix fowardPass() {
		return Matrix.subMatrix(a.matrix,b.matrix);
	}

}
