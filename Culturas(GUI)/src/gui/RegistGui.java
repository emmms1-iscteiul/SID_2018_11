package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.WindowConstants;

public class RegistGui {

	private JFrame frame;

	public RegistGui(String frameTitle) {
		frame = new JFrame(frameTitle);
		frame.setSize(550, 300);
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
		JPanel centerPanel = new JPanel();
		JPanel bottomPanel = new JPanel();

		JLabel registerLabel = new JLabel("Register");
		registerLabel.setFont(new Font("Arial", Font.CENTER_BASELINE, 30));

		topPanel.add(registerLabel);

		
		JLabel adminName = new JLabel("Username: ");
		adminName.setFont(new Font("Arial", Font.BOLD, 13));
		JTextArea adminNameText = new JTextArea("", 1, 10);
		JLabel adminPassLabel = new JLabel("Password: ");
		adminPassLabel.setFont(new Font("Arial", Font.BOLD, 13));
		JTextArea passwordText = new JTextArea("", 1, 10);
		
		Font font = adminNameText.getFont();
		float size = font.getSize() + 4.0f;
		adminNameText.setFont( font.deriveFont(size) );
		passwordText.setFont( font.deriveFont(size) );

		centerPanel.add(adminName);
		centerPanel.add(adminNameText);
		centerPanel.add(adminPassLabel);
		centerPanel.add(passwordText);

		JButton loginButton = new JButton("Login");

		centerPanel.add(loginButton);
		
		JButton registerButton = new JButton("Register");
		
		bottomPanel.add(registerButton);

		frame.add(topPanel, BorderLayout.PAGE_START);
		frame.add(centerPanel, BorderLayout.CENTER);
		frame.add(bottomPanel,BorderLayout.SOUTH);

	}

	public void open() {
		frame.setVisible(true);
	}

	public static void main(String[] args) {
		RegistGui frame = new RegistGui("Monotorização de Culturas");
		frame.open();
	}
	
	
}
