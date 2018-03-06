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
		Matrix grada = a.backprop(goal);
		Matrix gradb = b.backprop(goal);
		
		if(grada == null && gradb ==null) {
			return null;
		}else if(grada == null) {
			return b.matrix;
		}else if(gradb==null) {
			return a.matrix;
		}else {
			return Matrix.addMatrix(Matrix.multiplyMatricies(a.backprop(goal), b.matrix), Matrix.multiplyMatricies(a.matrix, b.backprop(goal)));
		}
		
		//if(a == goal) 
			//return b.matrix;
		//MAKE THIS return Matrix.multiplyMatriacies(b.matrix ,Matrix.ones( shape of goal ));
		
		//if(b == goal)
			//return a.matrix;
		
		//System.out.println("\nHLEPPP");
		//Matrix.multiplyMatricies(a.backprop(goal), b.matrix).printShape();
		//Matrix.multiplyMatricies(a.matrix, b.backprop(goal)).printShape();
		
		}
	
	public Matrix acutallBackprop(Tensor goal) {
		
		double[][] aback = a.backprop(goal).vals;
		double[][] bback = b.backprop(goal).vals;
		
		//aback*b + a*bback -> aback rows with b columns
		//zij = E(t, Xitmtj)
		
		//MIGHT CAUSE ISSUES: empty grad should be the shape of aback dot b+bback dot a
		//instead I am cutting corners and only doing aback dot b -> both terms above should yeild shame shape in order for addition anyway REDUNDANT!
		double[][] emptyGrad = new double[aback.length][b.matrix.vals[0].length];
		
		//ALOT OF REDUNCANCY BECAUSE OF THE NATURE OF MATRIX MULTIPLICATION (4,3) (3,2) - > 3s
		for(int i = 0; i < aback.length; i++) {
			for(int j = 0; j < b.matrix.vals[0].length; j++) {
				for(int t = 0; t < aback[0].length; t++) {
					//zij = E(t, Xitmtj) -> 'X'm + X'm' 
					emptyGrad[i][j] = (aback[i][t]*b.matrix.vals[t][j] + a.matrix.vals[i][t]*bback[t][j]);
				}
			}
		}
		
		return new Matrix(emptyGrad);
	}
	
}
