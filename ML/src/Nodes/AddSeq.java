package Nodes;

import Main.Matrix;
import Main.Tensor;
import Main.tempMain;

public class AddSeq extends Node{

	public AddSeq(Tensor a) {
		super(a, null);
		// TODO Auto-generated constructor stub
	}
	
	public Matrix fowardPass() {
		return Matrix.addCollapseMatrix(a.matrix);
	}
	
	public String tos() {
		return "(+++)";
	}
	
	public Matrix backprop(Tensor goal) {

		return Matrix.addCollapseMatrix(a.backprop(goal));
	}

}
