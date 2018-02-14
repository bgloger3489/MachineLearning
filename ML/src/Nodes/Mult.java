package Nodes;

import Main.Matrix;
import Main.Tensor;

public class Mult extends Node{

	public Mult(Tensor a, Tensor b) {
		super(a, b);
	}
	
	public Matrix fowardPass() {
		return Matrix.multiplyMatricies(a.matrix, b.matrix);
	}
	
	public String tos() {
		return "(*)";
	}
	
	public Matrix backprop(Tensor goal) {
		if(a == goal) 
			return b.matrix;
		//MAKE THIS return Matrix.multiplyMatriacies(b.matrix ,Matrix.ones( shape of goal ));
		
		if(b == goal)
			return a.matrix;
		
		return Matrix.addMatrix(a.backprop(goal), b.backprop(goal));
	}
}
