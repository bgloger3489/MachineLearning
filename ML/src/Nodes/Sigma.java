package Nodes;

import Main.Matrix;
import Main.Tensor;

public class Sigma extends Node{

	public Sigma(Tensor a) {
		super(a, null);
		// TODO Auto-generated constructor stub
	}

	public Matrix fowardPass() {
		double[][] totalLoss = {{0}};
		for(int i = 0; i < a.matrix.vals[0].length; i++) {
			totalLoss[0][0]+= a.matrix.vals[0][i];
		}
		return new Matrix(totalLoss);
	}
	
	public Matrix components(Tensor goal) {
		Matrix grada = a.components(goal);
		double[][] grad = new double[1][grada.vals[0].length];
		for(int i = 0; i < grada.vals.length; i ++) {
			for(int j = 0; j < grada.vals[0].length; j++) {
				grad[0][j] += grada.vals[i][j];
			}
		}
		
		return new Matrix(grad);
	}
}
