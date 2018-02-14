package Nodes;

import Main.Matrix;
import Main.Tensor;
import Main.tempMain;

public class MatMult extends Node{
	
	public MatMult(Tensor a, Tensor b) {
		super(a, b);
		// TODO Auto-generated constructor stub
	}
	
	public Matrix fowardPass() {
		return Matrix.multiplyMatricies(a.matrix, b.matrix);
	}
	
	
	

	public Matrix backprop(Tensor goal){
		
		Tensor notGoal;
		
		if(this.a == goal)
			notGoal = this.b;
		else if(this.b == goal)
			notGoal = this.a;
		else {
			System.out.println("You aren't using MatMult correctly");
			notGoal = null;
		}
		
		
		
		//return new Matrix Matrix.MultMateixies(notGoal, this.matrix.vals filled with ones)
		
		return Matrix.multiplyMatricies(notGoal.matrix, Matrix.ones(goal.matrix.getShape()[0], goal.matrix.getShape()[1]));
	}

}
