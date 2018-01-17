package Main;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;

import org.knowm.xchart.standalone.SwingDemo;

public class JframeTest {

	public static void main(String[] args) {
		JFrame frame = new JFrame();

		JPanel panel = new JPanel();
		
		JButton button = new JButton();
		button.setText("asdasd");
		panel.add(button);
		
		JLabel label = new JLabel();
		panel.add(label);
		
		button.addActionListener(new ActionListener(){

			
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser jfc = new JFileChooser();
				
		        int result = jfc.showSaveDialog(null);
		        
		        
		        String path = jfc.getSelectedFile().getAbsolutePath();
						
		        ImageIcon temp = new ImageIcon(path);
						
		        label.setIcon(temp);
		       
			}
		});
		
		
		frame.add(panel);
		frame.setSize(300,300);
		frame.setVisible(true);
	}
	
	
	

}
