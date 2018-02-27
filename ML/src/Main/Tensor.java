package Main;

import Nodes.Node;

public class Tensor {
	
	//init vals:
	public Matrix matrix;
	public Node creator;
	public Matrix grad;
	

	//init with vals
	public Tensor(Matrix matrix) {
		this.matrix = matrix;
		this.creator = null;
	}
	
	//init by opperation
	public Tensor(Node creator) {
		this.matrix = creator.fowardPass();
		this.creator = creator; 
	}
	
	public static void updateTensors(Tensor[] n) {
		for(int i = 0; i < n.length; i++) {
			//tempMain.p("z"+(i+1));
			n[i].updateTensor();
		}
	}
	
	public void updateTensor() {
		this.matrix = creator.fowardPass();
		//tempMain.prarr(this.matrix.vals);
	}
	
	public Matrix backprop(Tensor goal) {
		
		if(this == goal) {
			return this.matrix;//Matrix.ones(this.matrix.getShape()[0], this.matrix.getShape()[1]);
		}else if(creator == null) {
			//return Matrix.zeros(tempMain.EMERGENCY_LENGTH);
			
			//double[][] temp = {{0}};
			//aASDASDASDASDASDASD
			double[][] temp = new double[this.matrix.getShape()[0]][this.matrix.getShape()[1]];
			//System.out.println("CREATING EMPTY MATRIX WITH SHAPE:" + this.matrix.getShape()[0] + "," + this.matrix.getShape()[1]);
			
			//double[][]temp = new double[1][1];
			return new Matrix(temp);
		}
		return creator.backprop(goal);
	}
	
	public void printShape() {
		System.out.println(this.matrix.vals.length + ", " +this.matrix.vals[0].length);
	}
}
