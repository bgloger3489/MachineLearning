package Nodes;

import Main.Tensor;

public class Mult extends Node{

	public Mult(Tensor a, Tensor b) {
		super(a, b);
	}
	
	public int[] fowardPass() {
		int[] temp = new int[a.vals.length];
		
		for(int i = 0; i < a.vals.length; i++) {
			temp[i] = a.vals[i] * b.vals[i];
		}
		
		this.newVals = temp;
		
		return temp;
	}
	
	public String tos() {
		return "(*)";
	}
}
