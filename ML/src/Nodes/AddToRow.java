package Nodes;

import Main.Matrix;
import Main.Tensor;

public class AddToRow extends Node{

	public AddToRow(Tensor a) {
		super(a, null);
		// TODO Auto-generated constructor stub
	}
	
	public Matrix fowardPass() {
		return Matrix.addCollapseMatrixToRow(a.matrix);
	}
	
	public String tos() {
		return "(+++)";
	}
	
	public Matrix backprop(Tensor goal) {

		return Matrix.addCollapseMatrixToRow(a.backprop(goal));
	}

}
