package Main;

import Nodes.Mult;
import Nodes.Node;

public class tempMain {
	
	public static void main(String[] args) {
		int[] temp1 = {1,2,3,4,5};
		Tensor X = new Tensor(temp1);
		
		int[] temp2 = {2,4,6,8,10};
		Tensor Y = new Tensor(temp1);
		
		
		int[] temp3 = {3,3,3,3,3};
		Tensor m = new Tensor(temp3);
		
		Node n1 = new Mult(X, m);
		Tensor c = new Tensor(n1);
		
		parr(c.vals);
		
		
		//backpropigate
		
		
		
	}
	
	public static void parr(int[] a) {
		for(int temp: a) {
			System.out.println(temp);
		}
	}
}
