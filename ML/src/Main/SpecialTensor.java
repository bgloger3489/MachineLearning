package Main;

public class SpecialTensor extends Tensor{
	int[] allY;
	
	public SpecialTensor(Matrix matrix, int[] allY, Tensor whereToFind) {
		super(matrix);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public Matrix backprop(Tensor goal) {
		
	}

}
