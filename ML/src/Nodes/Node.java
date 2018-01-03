package Nodes;

import Main.Tensor;

public class Node {
	public Tensor a;
	public Tensor b;
	public int[] newVals;
	
	public Node(Tensor a, Tensor b) {
		this.a = a;
		this.b = b;
	}

	public int[] fowardPass() {
		this.newVals = null;
		return null;
	}
	
	public String tos() {
		return "()";
	}
	
	public int[] grada() {
		return b.vals;
	}
	
	public int[] gradb() {
		return a.vals;
	}
}
