package gui;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;

public class InvestigadorGUI 
{
	
	private static	JFrame frame= new JFrame("Ecrã Inicial");
 
	
	
	public static void main(String[] args) 
	{
		frame.setSize(300, 200);
		frame.setLocation(200, 100);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		frame.setLayout(new GridLayout(3,1));
		frame.add(new JButton("1"));
		frame.add(new JButton("2"));
		frame.add(new JButton("3"));
		frame.setSize(300, 200);
		frame.setLocation(200, 100);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(
		JFrame.EXIT_ON_CLOSE);
		
		
		
		
		frame.setVisible(true);
	}
	
	
	
}
