package Main;

import Nodes.Node;

public class Tensor {
	
	//init vals:
	public int[] vals;
	public Node creator;
	public int
	

	//init with vals
	public Tensor(int[] vals) {
		this.vals = vals;
		this.creator = null;
	}
	
	//init by opperation
	public Tensor(Node creator) {
		this.vals = creator.fowardPass();
		this.creator = creator; 
	}
	
	//init by scalar
	public Tensor(int scalar) {
			this.vals = creator.fowardPass();
			this.creator = creator; 
	}
	
	public void backprop() {
		if(creator != null) {
			creator.backprop();
		}
	}
}
