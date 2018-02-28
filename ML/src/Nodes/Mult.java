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
	
	public Matrix acutallBackprop(Tensor goal) {
		//NEED TO FICURE OUT HOW IT WORKS IN M(B + X)!!!!!!
		//Tensor respectTo = goal;
		int NUMPICTURES = 0;
		Tensor notGoal = null;
		
		if(a == goal)
			b = notGoal;
		else if(b == goal)
			a = notGoal;
		else
			System.out.println("FIX BACKPROP IN MULT!!!");
		
		
		//double[][] goalback = a.backprop(goal).vals;
		double[][] notGoalBack = notGoal.backprop(goal).vals;
		
		double[][] emptyGrad = new double[goal.matrix.vals.length][goal.matrix.vals[0].length];
		
		for(int i = 0; i < emptyGrad.length; i++) {
			for(int j = 0; j< emptyGrad[0].length; j++) {
				for(int p = 0; p < NUMPICTURES; p++) {
					emptyGrad[i][j]+= notGoal.matrix.vals[p][i]*goal.matrix.vals[i][j];
				}
			}
		}	
		
		return new Matrix(emptyGrad);
	}
	
}
