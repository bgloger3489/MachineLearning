package Nodes;

import Main.Tensor;

public class AddSeq extends Node{

	public AddSeq(Tensor a) {
		super(a, a);
		// TODO Auto-generated constructor stub
	}
	
	public Matrix fowardPass() {
	
	}
	
	public String tos() {
		return "(+++)";
	}

}
