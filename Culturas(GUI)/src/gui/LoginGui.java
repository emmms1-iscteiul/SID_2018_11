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

public class LoginGui {

	private JFrame frame;

	public LoginGui(String frameTitle) {
		frame = new JFrame(frameTitle);
		frame.setSize(550, 170);
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

		JLabel loginLabel = new JLabel("Login");
		loginLabel.setFont(new Font("Arial", Font.CENTER_BASELINE, 30));

		topPanel.add(loginLabel);

		
		JLabel usernameLabel = new JLabel("Username: ");
		usernameLabel.setFont(new Font("Arial", Font.BOLD, 13));
		JTextArea usernameText = new JTextArea("", 1, 10);
		JLabel passwordLabel = new JLabel("Password: ");
		passwordLabel.setFont(new Font("Arial", Font.BOLD, 13));
		JTextArea passwordText = new JTextArea("", 1, 10);
		
		Font font = usernameText.getFont();
		float size = font.getSize() + 4.0f;
		usernameText.setFont( font.deriveFont(size) );
		passwordText.setFont( font.deriveFont(size) );

		centerPanel.add(usernameLabel);
		centerPanel.add(usernameText);
		centerPanel.add(passwordLabel);
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
		LoginGui frame = new LoginGui("Monotorização de Culturas");
		frame.open();
	}
}
