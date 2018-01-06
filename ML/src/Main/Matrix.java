package Main;

public class Matrix {

	public double[][] vals;
	
	public Matrix(double[][] d) {
		vals = d;
	}
	
	public int[] getShape() {
		int[] temp = {vals.length, vals[0].length};
		return temp;
	}
	
	/**
	 * 4,3
	 * [---]
	 * [---]
	 * [---]
	 * [---]
	 * 
	 * 3,2
	 * [--]
	 * [--]
	 * [--]
	 *
	 * 4,2
	 * [--]
	 * [--]
	 * [--]
	 * [--]
	 *
	 * [a00*b00 + a01*b10 + a02*b20, a00*b01 + a01*b11 + a02*b21]
	 * [a10*b00 + a11*b10 + a12*b20, a10*b01 + a11*b11 + a12*b21]
	 * [a20*b00 + a21*b10 + a22*b20, a20*b01 + a21*b11 + a22*b21]
	 * [a30*b00 + a31*b10 + a32*b20, a30*b01 + a31*b11 + a32*b21]
	 * 
	 * **/
	public static Matrix multiplyMatricies(Matrix m1, Matrix m2) {
		
		if(m1.vals.length != m2.vals[0].length) {
			tempMain.p("INVALID INPUT1");
		}

		double[][] temp = new double[m1.vals.length][m2.vals[0].length];
		
		for(int i = 0; i < m1.vals.length; i++) { //loop through m1 going down
			for(int j = 0; j < m2.vals.length; j++) { //m2 == m1[0]    loop through m1 going across OR m2 going down
				for(int t = 0; t < m2.vals[0].length; t++) { //loop through m2 going across
					temp[i][t] += m1.vals[i][j]*m2.vals[j][t];
				
				}
			}
		}
		
		return new Matrix(temp);
	}
	
	public static Matrix addMatricies(Matrix m1, Matrix m2) {
		if(!m1.getShape().equals(m2.getShape())) {
			tempMain.p("INVALID INPUT2");
		}
		
	}
}
