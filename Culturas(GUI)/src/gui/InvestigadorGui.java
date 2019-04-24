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


public class InvestigadorGui {

	private JFrame frame;

	public InvestigadorGui(String frameTitle) {
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
		
		JLabel frameLabel = new JLabel("Manutenção De Culturas e Medições");
		frameLabel.setFont(new Font("Arial", Font.CENTER_BASELINE, 15));

		topPanel.add(frameLabel);
		
		JPanel centerPanel=new JPanel(new GridLayout(4,2));
		
		centerPanel.add(new JButton("Consultar Cultura"));
		centerPanel.add(new JButton("Consultar Medição"));
		centerPanel.add(new JButton("Criar Cultura"));
		centerPanel.add(new JButton("Criar Medição"));
		

		centerPanel.add(new JButton("Editar Cultura"));
		centerPanel.add(new JButton("Editar Medição"));
		centerPanel.add(new JButton("Apagar Cultura"));
		centerPanel.add(new JButton("Apagar Medição"));
		
		
		frame.add(topPanel,BorderLayout.NORTH);
		frame.add(centerPanel,BorderLayout.CENTER);

	}

	public void open() {
		frame.setVisible(true);
	}

	public static void main(String[] args) {
		InvestigadorGui frame = new InvestigadorGui("Monotorização de Culturas");
		frame.open();
	}

}
