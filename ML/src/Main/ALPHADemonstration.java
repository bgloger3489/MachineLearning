package Main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ALPHADemonstration {
	public static void main(String[] args) {

		//JFRAME FOR USER INPUT
		JFrame f = new JFrame();
	    
		JTextField x = new JTextField("0,1,2,3,4", 30);
		JTextField y = new JTextField("0,1,4,9,16", 30);
		
		double[][] temp1;
		double[][] temp2;
		JPanel p = new JPanel();
		
		p.add(x);
		p.add(y);
		
		
	    JButton b = new JButton("Submit");
	    
	    b.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		if(checkValid(x.getText()) && checkValid(y.getText())) {
	    			double[][] temp1 = parseInput(x.getText());
	    			double[][] temp2 = parseInput(y.getText());
	    			f.dispose();
	    			QuadraticRegression lr = new QuadraticRegression(temp1,temp2);
	    			lr.start();
	    			
	    			
	    		}
	    	}
	    });
	    
	    p.add(b);
	    
	    f.add(p);
	    f.pack();
	    //setSize(220, 400);
	    f.setLocationRelativeTo(null);  
	    f.setVisible(true);
		
	}
	
	//Utility Functions
		
		public static boolean checkValid(String s) {
			
			for(int i =0; i< s.length(); i++) {
				String temp = s.substring(i,i+1);
				try {
					Integer.parseInt(temp);
				}catch(Exception e) {
					if(!(temp.compareTo(",") == 0 || temp.compareTo(".") == 0|| temp.compareTo("-") == 0 || temp.compareTo(" ") == 0))
						return false;
				}
				
			}
			return true;
		}
	
	
		public static double[][] parseInput(String s){
			String[] temp = s.split(",");
			
			double[][] finall = new double[temp.length][1];
			
			for(int i =0; i < temp.length; i++) {
				finall[i][0] = Double.parseDouble(temp[i]);
			}
			return finall;
		}
	
		public static void p(String s) {
			System.out.println(s);
		}
		
		public static void prarr(double[][] temp) {
			for(int i =0; i < temp.length; i++) {
				if(temp.length> 1)
					System.out.print("\n{");
				for(int j = 0; j < temp[0].length; j++) {
					System.out.print(temp[i][j] + ", ");
				}
				if(temp.length> 1)
					System.out.println("}");
				else
					p("");
			}
		}
		
		public static double[][] T(double[][] asd){
			double[][] temp = new double[asd[0].length][asd.length];
			
			for(int i = 0; i < asd.length; i++) {
				for(int j = 0; j < asd[0].length; j++) {
					temp[j][i] = asd[i][j];
				}
			}
			
			return temp;
		}
}
