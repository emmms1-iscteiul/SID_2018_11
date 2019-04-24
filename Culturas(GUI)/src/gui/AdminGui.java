package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

public class AdminGui {


	private JFrame frame;

	public AdminGui(String frameTitle) {
		frame = new JFrame(frameTitle);
		frame.setSize(350, 350);
		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (int) ((dimension.getWidth() - frame.getWidth()) / 2);
		int y = (int) ((dimension.getHeight() - frame.getHeight()) / 2);
		frame.setLocation(x, y);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		addContent();
	}

	public JFrame getFrame() {
		return frame;
	}

	public void addContent() {
		frame.setLayout(new BorderLayout());

		
		JPanel topPanel=new JPanel();
		
		JLabel frameLabel = new JLabel("Manutenção De Variáveis e Utilizadores");
		frameLabel.setFont(new Font("Arial", Font.CENTER_BASELINE, 15));

		topPanel.add(frameLabel);
		
		JPanel centerPanel=new JPanel(new GridLayout(4,2));
		
		
		centerPanel.add(new JButton("Consultar Variável"));
		centerPanel.add(new JButton("Consultar Utilizador"));
		centerPanel.add(new JButton("Criar Variável"));
		centerPanel.add(new JButton("Criar Utilizador"));
		
		
		centerPanel.add(new JButton("Editar Variável"));
		centerPanel.add(new JButton("Editar Utilizador"));
		centerPanel.add(new JButton("Apagar Variável"));
		centerPanel.add(new JButton("Apagar Utilizador"));
		
		
		
		frame.add(topPanel,BorderLayout.NORTH);
		frame.add(centerPanel,BorderLayout.CENTER);

	}

	public void open() {
		frame.setVisible(true);
	}

	public static void main(String[] args) {
		AdminGui frame = new AdminGui("Monotorização de Culturas");
		frame.open();
	}

	
	
}
