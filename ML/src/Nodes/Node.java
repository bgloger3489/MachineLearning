package Nodes;

import Main.Matrix;
import Main.Tensor;

public class Node {
	public Tensor a;
	public Tensor b;
	//public Matrix grad;
	
	
	public Node(Tensor a, Tensor b) {
		this.a = a;
		this.b = b;
	}

	public Matrix fowardPass() {
		return null;
	}
	
	public String tos() {
		return "()";
	}

	//everytime you backprop,  check goal
	public Matrix backprop(Tensor goal) {
		return null;
	}
}
