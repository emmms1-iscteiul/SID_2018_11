package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;


import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.WindowConstants;

public class CriarGui {

	private JFrame frame;

	public CriarGui(String frameTitle) {
		frame = new JFrame(frameTitle);
		frame.setSize(550, 550);
		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (int) ((dimension.getWidth() - frame.getWidth()) / 2);
		int y = (int) ((dimension.getHeight() - frame.getHeight()) / 2);
		frame.setLocation(x, y);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	}

	public JFrame getFrame() {
		return frame;
	}

	public void addContentUtilizador() {
		frame.setLayout(new BorderLayout());

		JPanel topPanel = new JPanel();

		JLabel frameLabel = new JLabel(frame.getTitle());
		frameLabel.setFont(new Font("Arial", Font.CENTER_BASELINE, 15));

		topPanel.add(frameLabel);

		JPanel centerPanel = new JPanel();
		
		 Object[] columnNames = { "Nome Utilizador","Email", "Tipo Utilizador", "Password","Editar"};
		
		 Object[][] data = { 
		            { "Kundan Kumar Jha", "4031", "CSE","s",true}, 
		            { "Anand Jha", "6014", "IT","a",false} 
		        };
		 
		 
		 
	 
		
		JTable tabelaConsulta=new JTable(data,columnNames);
		
		
		centerPanel.add(new JScrollPane(tabelaConsulta));
		
		frame.add(topPanel, BorderLayout.NORTH);
		frame.add(centerPanel, BorderLayout.CENTER);

	}

	public void open() {
		frame.setVisible(true);
	}

	public static void main(String[] args) {
		CriarGui frame = new CriarGui("Criar");
		frame.addContentUtilizador();
		frame.open();
	}

	
	
}
