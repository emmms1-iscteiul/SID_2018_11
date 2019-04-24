package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;


import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.WindowConstants;

public class Cons_Edit_Del_Gui {

	private JFrame frame;

	public Cons_Edit_Del_Gui(String frameTitle) {
		frame = new JFrame(frameTitle);
		frame.setSize(450, 200);
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

		JPanel topPanel = new JPanel();

		JLabel frameLabel = new JLabel(frame.getTitle());
		frameLabel.setFont(new Font("Arial", Font.CENTER_BASELINE, 15));

		topPanel.add(frameLabel);

		JPanel centerPanel = new JPanel();

		JTable tabelaConsulta=new JTable();
		
		centerPanel.add(tabelaConsulta);

		frame.add(topPanel, BorderLayout.NORTH);
		frame.add(centerPanel, BorderLayout.CENTER);

	}

	public void open() {
		frame.setVisible(true);
	}

	public static void main(String[] args) {
		Cons_Edit_Del_Gui frame = new Cons_Edit_Del_Gui("Consultar");
		frame.open();
	}

	
	
}
