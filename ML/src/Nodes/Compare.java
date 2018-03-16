package Nodes;

import Main.Matrix;
import Main.Tensor;
import Main.tempMain;

public class Compare extends Node{
	int yLabel;
	public Compare(Tensor a, int yLabel) {
		super(a, null);
		this.yLabel = yLabel;
	}
	
	public Matrix fowardPass() {
		//assumes that a is a vector of labels
		double[][] temp = new double[1][a.matrix.vals[0].length];
		
		//b is yLabel
		for(int j = 0; j < temp[0].length; j ++){
			if(j == yLabel)
				temp[0][j] = 0;// whereToFind.matrix.vals[i][allY[i]];//0;//tempMain.MARGIN;
			else
				temp[0][j] = a.matrix.vals[0][j] -  a.matrix.vals[0][yLabel] + tempMain.MARGIN;
			
		}
		return new Matrix(temp);
	}
	
	public Matrix components(Tensor goal) {
		//assumes that a is a vector of labels
		double[][] grad = new double[goal.matrix.vals.length][goal.matrix.vals[0].length];//a.components(goal).vals; //new double[0][a.matrix.vals.length];
		double[][] grada = a.components(goal).vals;
		//b is yLabel
		for(int i = 0; i < grad.length; i ++) {
			for(int j = 0; j < grad[0].length; j ++){
				if(j == yLabel)
					grad[i][j] = 0;
				else
					grad[i][j] = grada[i][j] -  grada[i][yLabel];
			
			}
		}
		return new Matrix(grad);
	}
}