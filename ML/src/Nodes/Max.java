package Nodes;

import Main.Matrix;
import Main.Tensor;
import Main.tempMain;

public class Max extends Node{
	Tensor whereToFind;
	
	public Max(Tensor a) {
		super(null, null);
		this.whereToFind = a;
	
	}
	
	public Matrix fowardPass() {
		double[][] emptyArray = new double[tempMain.NUM_PICTURES][tempMain.NUM_CLASSIFICATIONS];
		
		for(int i = 0; i < tempMain.NUM_PICTURES; i++){
			for(int j = 0; j < tempMain.NUM_CLASSIFICATIONS; j ++){
				if(whereToFind.matrix.vals[i][j] > 0)
					emptyArray[i][j] = whereToFind.matrix.vals[i][j];
				else
					emptyArray[i][j] = 0;
			}
		}
		return new Matrix(emptyArray);
	}
	
	
	
	public Matrix components(Tensor goal) {
		double[][] grad = new double[goal.matrix.vals.length][goal.matrix.vals[0].length];
		double[][] grada = a.components(goal).vals;
		
		
		for(int i = 0; i < grad.length; i++){
			for(int j = 0; j < grad[0].length; j ++){
				if(a.matrix.vals[i][j] > 0)
					grad[i][j] = grada[i][j];
				else
					grad[i][j] = 0;
			}
		}
		return new Matrix(grad);
	}
	

	public Matrix backprop(Tensor goal){

		double[][] emptyGradArray = new double[tempMain.NUM_PICTURES][tempMain.NUM_CLASSIFICATIONS];
		//**Creates instruction array for how to calculate gradient as shown above (Where to find them -> z2 -> z2.backprop)
				
		double[][] z3Grad = whereToFind.backprop(goal).vals;
		
		//System.out.println("where to finsd");
		//tempMain.prarr(z3Grad);
		
		for(int i = 0; i < tempMain.NUM_PICTURES; i++){
			for(int j = 0; j < tempMain.NUM_CLASSIFICATIONS; j ++){
				//System.out.println(""+whereToFind.matrix.vals[i][j]);
				if(whereToFind.matrix.vals[i][j] > 0)
					emptyGradArray[i][j] = z3Grad[i][j];
				else
					emptyGradArray[i][j] = 0;
			}
		}
		
		return new Matrix(emptyGradArray); 
	}

}
