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
		if(a == goal) 
			return Matrix.subMatrix(Matrix.ones(b.matrix.vals.length), b.backprop(goal));
		
		if(b == goal)
			return Matrix.subMatrix(a.backprop(goal), Matrix.ones(a.matrix.vals.length));
		
		//tempMain.prarr(a.backprop(goal).vals);
		//tempMain.prarr(b.backprop(goal).vals);
		
		return Matrix.subMatrix(a.backprop(goal), b.backprop(goal));
	}
	
	public Matrix fowardPass() {
		return Matrix.subMatrix(a.matrix,b.matrix);
	}

}
