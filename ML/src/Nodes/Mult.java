package Nodes;

import Main.Tensor;

public class Mult extends Node{

	public Mult(Tensor a, Tensor b) {
		super(a, b);
	}
	
	public Matrix fowardPass() {
		
	}
	
	public String tos() {
		return "(*)";
	}
}
