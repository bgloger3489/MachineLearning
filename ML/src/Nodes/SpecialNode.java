package Nodes;

import Main.Matrix;
import Main.Tensor;
import Main.tempMain;

public class SpecialNode extends Node{
	Tensor whereToFind;
	int[] allY;
	public SpecialNode(Tensor whereToFind, int[] allY) {
		super(null, null);
		this.whereToFind = whereToFind;
		this.allY = allY;
	}
	
	//allY.length IS NUM PICTURES
	
	public Matrix fowardPass() {
		double[][] emptyArray = new double[tempMain.NUM_PICTURES][tempMain.NUM_CLASSIFICATIONS];
		
		for(int i = 0; i < tempMain.NUM_PICTURES; i++){
			for(int j = 0; j < tempMain.NUM_CLASSIFICATIONS; j ++){
				if(j == allY[i])
					emptyArray[i][j] = whereToFind.matrix.vals[i][allY[i]];//0;//tempMain.MARGIN;
				else
					emptyArray[i][j] = whereToFind.matrix.vals[i][allY[i]] - tempMain.MARGIN;
			}
		}
		return new Matrix(emptyArray);
	}
	
	
	

	public Matrix backprop(Tensor goal){

		double[][] emptyGradArray = new double[tempMain.NUM_PICTURES][tempMain.NUM_CLASSIFICATIONS];
		//**Creates instruction array for how to calculate gradient as shown above (Where to find them -> z2 -> z2.backprop)
				
		double[][] z2Grad = whereToFind.backprop(goal).vals;
	
		for(int i = 0; i < tempMain.NUM_PICTURES; i++){
			for(int j = 0; j < tempMain.NUM_CLASSIFICATIONS; j ++){
				if(j == allY[i])
					emptyGradArray[i][j] = 0;
				else
					emptyGradArray[i][j] = z2Grad[i][allY[i]];
			}
		}
		
		return new Matrix(emptyGradArray); 
	}
	
	
	
}