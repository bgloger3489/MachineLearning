package Main;

import Nodes.Node;

public class Tensor {
	
	//init vals:
	public Matrix matrix;
	public Node creator;
	public Matrix grad;
	

	//init with vals
	public Tensor(Matrix matrix) {
		this.matrix = matrix;
		this.creator = null;
	}
	
	//init by opperation
	public Tensor(Node creator) {
		this.matrix = creator.fowardPass();
		this.creator = creator; 
	}
	
	public void backprop() {
		if(creator != null) {
			creator.backprop();
		}
	}
}
