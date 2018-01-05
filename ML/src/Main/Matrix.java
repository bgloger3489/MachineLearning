package Main;

public class Matrix {

	public Double[][] vals;
	
	public Matrix(Double[][] d) {
		vals = d;
	}
	
	public int[] getShape() {
		int[] temp = {vals.length, vals[0].length};
		return temp;
	}
	
	public static Matrix multiplyMatricies(Matrix m1, Matrix m2) {
		if(m1.vals.length != m2.vals[0].length) {
			tempMain.p("INVALID INPUT1");
		}

		Double[][] temp = new Double[m1.vals.length][m2.vals[0].length];
		
		return new Matrix(temp);
	}
	
	public static Matrix addMatricies(Matrix m1, Matrix m2) {
		if()
	}
}
