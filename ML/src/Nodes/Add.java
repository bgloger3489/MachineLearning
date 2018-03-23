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
		Matrix grada = a.backprop(goal);
		Matrix gradb = b.backprop(goal);
		
		if(grada == null && gradb ==null) {
			return null;
		}else if(grada == null) {
			return b.matrix;
		}else if(gradb==null) {
			return a.matrix;
		}else {
			return Matrix.addMatrix(a.backprop(goal), b.backprop(goal));
		}
	}
	
	public Matrix components(Tensor goal) {
		Matrix grada = a.components(goal);
		Matrix gradb = b.components(goal);
		if(grada == null && gradb == null) {
			System.out.println("check add compoenents");
			return null;
		}
		if(grada == null)
			return gradb;
		if(gradb == null)
			return grada;
		//System.out.println("return grada+gradb");
		return Matrix.addMatrix(grada, gradb);
	}
	
	public Matrix fowardPass() {
		return Matrix.addMatrix(a.matrix,b.matrix);
	}

}
