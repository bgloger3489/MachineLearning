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
		//if(a == goal) 
			//return b.matrix;
		//MAKE THIS return Matrix.multiplyMatriacies(b.matrix ,Matrix.ones( shape of goal ));
		
		//if(b == goal)
			//return a.matrix;
		
		System.out.println("\nHLEPPP");
		Matrix.multiplyMatricies(a.backprop(goal), b.matrix).printShape();
		Matrix.multiplyMatricies(a.matrix, b.backprop(goal)).printShape();
		
		return Matrix.addMatrix(Matrix.multiplyMatricies(a.backprop(goal), b.matrix), Matrix.multiplyMatricies(a.matrix, b.backprop(goal)));
	}
}
