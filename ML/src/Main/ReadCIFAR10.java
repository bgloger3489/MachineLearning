package Main;

import java.awt.Color;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

public class ReadCIFAR10 {
	

	public static void main(String[] args) throws IOException {
		FileInputStream inputStream = new FileInputStream("C:\\Users\\BT_1N3_11\\Desktop\\cifar-10-batches-bin\\data_batch_1.bin");
		
		int desiredImage = 3;//10000;
		
	    byte[] b = new byte[3073*desiredImage];
	    inputStream.read(b);

	    	
	    //
	   // byte temp = b[3072];
	    
	    //int temp = b[1+(10000-1)*3073+1024*2 + 31*32 + 31] & 0xFF;
	    double[][] pictureArray = makePictureArray(b, desiredImage);
	    
	    BufferedImage image = new BufferedImage(32, 32, BufferedImage.TYPE_INT_RGB);
	    
	    for (int row = 0; row < 32; row++) {
	    	for (int col = 0; col < 32; col++) {
	    		Color color = new Color(
	    			b[1 + (desiredImage-1)*3073 + 1024 * 0 + row * 32 + col] & 0xFF,
	                b[1 + (desiredImage-1)*3073 + 1024 * 1 + row * 32 + col] & 0xFF,
	                b[1 + (desiredImage-1)*3073 + 1024 * 2 + row * 32 + col] & 0xFF);
	                image.setRGB(col, row, color.getRGB());
	            ///System.out.println((1 + (desiredImage-1)*3073 + 1024 * 2 + row * 32 + col )+ "");
	        }
	    }
	    
	    String path = "./outt.jpg";

	   //boolean result = ImageIO.write(image, "jpeg", new FileOutputStream(path));
	   //if (!result) {
	   //   System.err.println("failed");
	   //}
	   
	 //displayImage(image);
	  
	  //System.out.println("aSDasdasd: "+ pictureArray.length+", "+ pictureArray[0].length);
	  
	  sendToTempMain(pictureArray);
	  
	}
	
	public static void displayImage(BufferedImage image) {
		 JFrame frame = new JFrame();
		  JPanel panel = new JPanel();
		  JLabel label = new JLabel();
		  
		  panel.add(label);
		  ImageIcon imageIcon = new ImageIcon(image);
		  Image image1 = imageIcon.getImage(); // transform it 
		  Image newimg = image1.getScaledInstance(200, 200,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
		  imageIcon = new ImageIcon(newimg);
		  label.setIcon(imageIcon);
		  
		  frame.add(panel);
		  frame.setSize(300,300);
		  frame.setVisible(true);
		  frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	}
	
	public static double[][] makePictureArray(byte[] b, int numPictures){
		double[][] pictureArray = new double[numPictures][3072];
		
		for(int i = 0; i < numPictures; i++) {
			for(int j = 0; j < 3072; j++) {
				pictureArray[i][j] = b[1+ i*(3072 + 1) + j]  & 0xFF;
			}
		}
		
		return pictureArray;
	}
	
	public static void sendToTempMain(double[][] pictureArray) {
		tempMain.test4(pictureArray);
	}
}

