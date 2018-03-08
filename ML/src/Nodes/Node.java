package Nodes;

import Main.Matrix;
import Main.Tensor;

public abstract class Node {
	public Tensor a;
	public Tensor b;
	//public Matrix grad;
	
	
	public Node(Tensor a, Tensor b) {
		this.a = a;
		this.b = b;
	}

	public abstract Matrix fowardPass();
	
	public String tos() {
		return "()";
	}

	//everytime you backprop,  check goal
	public abstract Matrix backprop(Tensor goal);
}
