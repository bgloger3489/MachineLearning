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
					//tempMain.p("" +m1.vals[i][j]*m2.vals[j][t] +" @i="+i+" j="+j +" t="+t);
				}
			}
		}
		
		return new Matrix(temp);
	}
	
	public static Matrix raisePowerMatrix(Matrix m1, double power) {
		//m1.getShape() is [length][1]
		double[][] temp = new double[m1.vals.length][m1.vals[0].length];
		
		for(int i = 0;  i < m1.vals.length; i++) {
			for(int j = 0; j < m1.vals[0].length; j++) {
				temp[i][j] = java.lang.Math.pow((m1.vals[i][j]), power);
		
			}
		}
		return new Matrix(temp);
	}
	
	public static Matrix addMatrix(Matrix m1, Matrix m2) {
		if(!(m1.getShape()[1]%m2.getShape()[1] == 0)) {
			tempMain.p("INVALID IMPUT3");
		}
		
		double[][] temp = new double[m1.vals.length][m1.vals[0].length];
		
		for(int i = 0;  i < m1.vals.length; i++) {
			for(int j = 0; j < m1.vals[0].length; j++) {
				temp[i][j] = m1.vals[i][j] + m2.vals[i%m2.vals.length][j%m2.vals[0].length];
			}
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
	
	public static Matrix addCollapseMatrixToRow(Matrix mat) {
		double[][] temp = new double[1][mat.vals[0].length];
		
		for(int i = 0; i < mat.vals.length; i++) {
			for(int j = 0; j < mat.vals[0].length; j++) {
				temp[0][j] += mat.vals[i][j];
			}
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
	
	public static Matrix ones(int rows, int cols) {
		double[][] temp = new double[rows][cols];
		for(int i = 0; i < rows; i++) {
			for(int j = 0; j < cols; j++) {
				temp[i][j] = 1.0;
			}
		}
		
		return new Matrix(temp);
	}
	
	public static Matrix random(int rows, int cols) {
		double[][] temp = new double[rows][cols];
		for(int i = 0; i < rows; i++) {
			for(int j = 0; j < cols; j++) {
				temp[i][j] = Math.random();
			}
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
	
	public void printShape() {
		System.out.println(""+this.vals.length + ", " + +this.vals[0].length);
		
	}
	
	public static Matrix multScalar(Matrix a, Matrix b) {
		double[][] temp = new double[a.vals.length][a.vals[0].length];
		for(int i = 0; i < a.vals.length; i++) {
			for(int j = 0; j < a.vals[0].length; j++) {
				temp[i][j] = a.vals[i][j]*b.vals[0][0];
			}
		}
		return new Matrix(temp);
	}
	
	public  void prarr() {
		double[][] temp = this.vals;
		for(int i =0; i < temp.length; i++) {
			if(temp.length> 1)
				System.out.print("\n{");
			for(int j = 0; j < temp[0].length; j++) {
				System.out.print(temp[i][j] + ", ");
			}
			if(temp.length> 1)
				System.out.println("}");
			else
				System.out.println("");
		}
		
	}
	
}
