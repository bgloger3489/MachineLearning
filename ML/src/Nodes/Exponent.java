package Nodes;

import Main.Matrix;
import Main.Tensor;
import Main.tempMain;

public class Exponent extends Node{
	public int power;
	
	public Exponent(Tensor a, int power) {
		super(a, null);
		this.power = power;
	}
	
	public String tos() {
		return "(^)";
	}
	
	public Matrix backprop(Tensor goal) {
		//bring the exponent down, the multiply by the grad of that
		
		double[][] m2 = {{power}};
		
		Matrix temp = Matrix.multiplyMatricies(Matrix.raisePowerMatrix(a.matrix, power-1), new Matrix(m2));
		
		return Matrix.multiplyMatricies(temp.T(), a.backprop(goal));
		
	}
	
	public Matrix components(Tensor goal) {
		//ONLY FOR ^2
		double[][] m2 = {{power}};
		Matrix grada = a.components(goal);
		return Matrix.raisePowerMatrix(grada, power);
		
	}
	
	public Matrix fowardPass() {
		return Matrix.raisePowerMatrix(a.matrix,power);
	}

}
