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
		if(a == goal) 
			return Matrix.addMatrix(Matrix.ones(b.matrix.vals.length), b.backprop(goal));
		
		if(b == goal)
			return Matrix.addMatrix(Matrix.ones(a.matrix.vals.length), a.backprop(goal));
		
		return Matrix.addMatrix(a.backprop(goal), b.backprop(goal));
	}
	
	public Matrix fowardPass() {
		return Matrix.addMatrix(a.matrix,b.matrix);
	}

}
