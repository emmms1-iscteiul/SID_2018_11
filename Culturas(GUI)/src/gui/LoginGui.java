package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

public class LoginGui {

	private JFrame frame;
	FuncionalidadesAdmin funcAdmin = new FuncionalidadesAdmin();
	FuncionalidadesInvestigador funcInv = new FuncionalidadesInvestigador();

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
		JTextField usernameText = new JTextField("", 10);

		JLabel passwordLabel = new JLabel("Password: ");
		passwordLabel.setFont(new Font("Arial", Font.BOLD, 13));
		JPasswordField passwordText = new JPasswordField("", 10);

		Font font = usernameText.getFont();
		float size = font.getSize() + 1.0f;
		usernameText.setFont( font.deriveFont(size) );
		passwordText.setFont( font.deriveFont(size) );

		centerPanel.add(usernameLabel);
		centerPanel.add(usernameText);
		centerPanel.add(passwordLabel);
		centerPanel.add(passwordText);

		JButton loginButton = new JButton("Login");

		loginButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (checkUser(usernameText).equals("Administrador")) {
					funcAdmin.login(usernameText, passwordText);
					AdminGui adminFrame = new AdminGui("Monotoriza��o de Culturas", funcAdmin);
					frame.dispose();
					adminFrame.open();
				}
				else if (checkUser(usernameText).equals("Investigador")) {
					funcInv.login(usernameText, passwordText);
					InvestigadorGui investigadorFrame = new InvestigadorGui("Monotoriza��o de Culturas", funcInv);
					frame.dispose();
					investigadorFrame.open();
				}
				else {
					JOptionPane.showMessageDialog(frame, "Utilizador n�o existe", "ERRO - NECESS�RIO REGISTAR PRIMEIRO", JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		centerPanel.add(loginButton);

		JButton registerButton = new JButton("Register");

		registerButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				RegistGui registFrame = new RegistGui("Monotoriza��o de Culturas", funcAdmin);
				frame.dispose();
				registFrame.open();

			}
		});

		bottomPanel.add(registerButton);

		frame.add(topPanel, BorderLayout.PAGE_START);
		frame.add(centerPanel, BorderLayout.CENTER);
		frame.add(bottomPanel,BorderLayout.SOUTH);

	}

	public void open() {
		frame.setVisible(true);
	}

	public String checkUser(JTextField usernameText) {

		String tipo = "";

		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/monotorizacao_de_culturas", "root", "root");
			CallableStatement cs = myConn.prepareCall("{call obterTipoUtilizador(?)}");
			cs.setString(1, usernameText.getText());
			cs.execute();
			ResultSet tipoU = cs.getResultSet();
			while (tipoU.next()) {
				tipo = tipoU.getString("TipoUtilizador");
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return tipo;
	}

	public static void main(String[] args) {
		LoginGui frame = new LoginGui("Monotoriza��o de Culturas");
		frame.open();
	}
}
