package Main;

import java.awt.Color;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ReadCIFAR10 {
	

	public static void main(String[] args) throws IOException {
		FileInputStream inputStream = new FileInputStream("C:\\Users\\BT_1N3_11\\Desktop\\cifar-10-batches-bin\\data_batch_1.bin");
		
		int desiredImage = 2;
		
	    byte[] b = new byte[3072*desiredImage+1];
	    inputStream.read(b);

	    BufferedImage image = new BufferedImage(32, 32, BufferedImage.TYPE_INT_RGB);
	    
	    
	    
	    for (int row = 0; row < 32; row++) {
	    	for (int col = 0; col < 32; col++) {
	    		Color color = new Color(
	    			b[1 + (desiredImage-1)*1024*3 +  1024 * 0 + row * 32 + col] & 0xFF,
	                b[1 + (desiredImage-1)*1024*3 + 1024 * 1 + row * 32 + col] & 0xFF,
	                b[1 + (desiredImage-1)*1024*3 + 1024 * 2 + row * 32 + col] & 0xFF);
	                image.setRGB(col, row, color.getRGB());
	            }
	    }
	    
	    String path = "./outt.jpg";

	   //boolean result = ImageIO.write(image, "jpeg", new FileOutputStream(path));
	   //if (!result) {
	   //   System.err.println("failed");
	   //}
	   
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
	   
	   
	   
	   
	}
}

