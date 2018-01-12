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
		
		if(m1.vals[0].length != m2.vals.length) {
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
	
	public static Matrix raisePowerMatrix(Matrix m1, double power) {
		//m1.getShape() is [length][1]
		double[][] temp = new double[m1.vals.length][1];
		
		for(int i = 0;  i < m1.vals.length; i++) {
			temp[i][0] = java.lang.Math.pow((m1.vals[i][0]), power);
		}
		
		return new Matrix(temp);
	}
	
	public static Matrix addMatrix(Matrix m1, Matrix m2) {
		if(m1.getShape().equals(m2.getShape())) {
			tempMain.p("INVALID IMPUT3");
		}
		
		double[][] temp = new double[m1.vals.length][1];
		
		for(int i = 0;  i < m1.vals.length; i++) {
			temp[i][0] = m1.vals[i][0] + m2.vals[i%m2.vals.length][0];
		}
		
		return new Matrix(temp);
	}
	
	public static Matrix addCollapseMatrix(Matrix m1) {
		double[][] temp = new double[1][1];
		
		for(int i = 0; i < m1.vals.length; i++) {
			temp[0][0] += m1.vals[i][0];
		}
		
		return new Matrix(temp);
	}

	public static Matrix subMatrix(Matrix m1, Matrix m2) {
		//m1 - m2
		
		if(!(m1.getShape()[1]%m2.getShape()[1] == 0)) {
			tempMain.p("INVALID IMPUT4");
		}
		
		double[][] temp = new double[m1.vals.length][m1.vals[0].length];
		
		for(int i = 0;  i < m1.vals.length; i++) {
			for(int j = 0; j < m1.vals[0].length; j++) {
				temp[i][j] = m1.vals[i][j] - m2.vals[i%m2.vals.length][j%m2.vals[0].length];
			}
		}
		
		return new Matrix(temp);
	}
	
	public Matrix T() {
		double[][] temp = new double[this.getShape()[1]][this.getShape()[0]];
		
		for(int i = 0; i < this.getShape()[0]; i++) {
			for(int j = 0; j < this.getShape()[1]; j++) {
				temp[j][i] = this.vals[i][j];
			}
		}
		
		return new Matrix(temp);
	}
	
	public static Matrix ones(int n) {
		double[][] temp = new double[n][1];
		for(int i = 0; i < n; i++) {
			temp[i][0] = 1.0;
		}
		
		return new Matrix(temp);
	}
	
	public static Matrix zeros(int n) {
		double[][] temp = new double[n][1];
		for(int i = 0; i < n; i++) {
			temp[i][0] = 0.0;
		}
		
		return new Matrix(temp);
	}
	
}
