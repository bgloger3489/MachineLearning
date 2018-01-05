package Nodes;

import Main.Matrix;
import Main.Tensor;

public class Node {
	public Tensor a;
	public Tensor b;
	
	
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
	
	public Matrix grada() {
		return b.matrix;
	}
	
	public Matrix gradb() {
		return a.matrix;
	}

	public void backprop() {
		a.grad = b.matrix;
		b.grad = a.matrix;
		a.backprop();
		b.backprop();
		
	}
}
