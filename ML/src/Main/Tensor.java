package Main;

import Nodes.Node;

public class Tensor {
	
	//init vals:
	public Matrix matrix;
	public Node creator;
	public Matrix grad;
	public boolean vector;

	//init with vals
	public Tensor(Matrix matrix) {
		this.matrix = matrix;
		this.creator = null;
		this.vector = false;
	}
	public Tensor(Matrix matrix, boolean isVector ) {
		this.matrix = matrix;
		this.creator = null;
		this.vector = isVector;
	}
	
	//init by opperation
	public Tensor(Node creator) {
		this.matrix = creator.fowardPass();
		this.creator = creator;
		if(this.matrix.vals[0].length == 0 || this.matrix.vals[0].length == 0) {
			this.vector = true;
		}else {
			this.vector = false;
		}
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
			
			return null;
			//return Matrix.zeros(tempMain.EMERGENCY_LENGTH);
			
			//double[][] temp = {{0}};
			//aASDASDASDASDASDASD
			//double[][] temp = new double[this.matrix.getShape()[0]][this.matrix.getShape()[1]];
			//System.out.println("CREATING EMPTY MATRIX WITH SHAPE:" + this.matrix.getShape()[0] + "," + this.matrix.getShape()[1]);
			
			//double[][]temp = new double[1][1];
			//return new Matrix(temp);
		}
		return creator.backprop(goal);
	}
	
	public Matrix components(Tensor goal) {
		if(this != goal && creator != null) {
			return creator.components(goal);
		}if(this != goal && this.creator == null){
			return null;
		}if(this == goal && this.creator == null){
			System.out.println("FOR ADD");
			return Matrix.ones(this.matrix.vals.length, this.matrix.vals[0].length);
		}else {
			System.out.println("check Tensor compeonents");
			return null;
		}
	}
	
	public void printShape() {
		System.out.println(this.matrix.vals.length + ", " +this.matrix.vals[0].length);
	}
	
	public void prarr() {
		double[][] temp = this.matrix.vals;
		for(int i =0; i < temp.length; i++) {
			if(temp.length> 1)
				System.out.print("\n{");
			for(int j = 0; j < temp[0].length; j++) {
				System.out.print(temp[i][j] + ", ");
			}
			if(temp.length> 1)
				System.out.println("}");
			else
				System.out.println("");
		}
	}
}
