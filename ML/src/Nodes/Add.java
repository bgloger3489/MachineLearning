package Nodes;

import Main.Matrix;
import Main.Tensor;

public class Add extends Node{

	public Add(Tensor a, Tensor b) {
		super(a, b);
	}
	
	public String tos() {
		return "(+)";
	}
	
	public Matrix backprop(Tensor goal) {
		//if(a == goal) {
			//double[][] temp = {{1}};
			
			//return Matrix.addMatrix(new Matrix(temp), b.backprop(goal));
		//}
		//if(b == goal) {
			//double[][] temp = {{1}};
			//return Matrix.addMatrix(new Matrix(temp), a.backprop(goal));
			
		//}
		return Matrix.addMatrix(a.backprop(goal), b.backprop(goal));
	}
	
	public Matrix fowardPass() {
		return Matrix.addMatrix(a.matrix,b.matrix);
	}

}
