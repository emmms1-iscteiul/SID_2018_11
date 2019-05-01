package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.WindowConstants;


public class EditarUtilizadorGUI {
	private JFrame frame;

	FuncionalidadesAdmin funcAdmin;
	int idU = 0;

	public EditarUtilizadorGUI(String frameTitle, FuncionalidadesAdmin funcAdmin, int rowIndex) {
		this.funcAdmin=funcAdmin;
		this.idU = rowIndex;
		frame = new JFrame(frameTitle);
		frame.setSize(400, 300);
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
		JPanel centerPanel = new JPanel(new GridLayout(8,1));
		JPanel bottomPanel = new JPanel();

		JLabel registerLabel = new JLabel("Editar Utilizador");
		registerLabel.setFont(new Font("Arial", Font.CENTER_BASELINE, 30));

		topPanel.add(registerLabel);

		JLabel nomeAntigo = new JLabel("Current Name:");
		nomeAntigo.setFont(new Font("Arial", Font.BOLD,13));
		JTextField nomeAntigoUser = new JTextField("", 10);

		JLabel passwordAntiga = new JLabel("Current Password:");
		passwordAntiga.setFont(new Font("Arial", Font.BOLD,13));
		JTextField passwordAntigaUser = new JTextField("", 10);

		JLabel tipoU = new JLabel("New Tipo:");
		tipoU.setFont(new Font("Arial", Font.BOLD, 13));
		JTextField tipoUser = new JTextField("",10);

		JLabel eMail = new JLabel("New Email:");
		eMail.setFont(new Font("Arial", Font.BOLD, 13));
		JTextField eMailUser = new JTextField("",10);

		JLabel nomeUtilizador = new JLabel("New NomeUtilizador:");
		nomeUtilizador.setFont(new Font("Arial", Font.BOLD, 13));
		JTextField nomeUser = new JTextField("",10);

		JLabel userPass = new JLabel("New Password:");
		userPass.setFont(new Font("Arial", Font.BOLD, 13));
		JPasswordField passwordText = new JPasswordField("",10);

		JLabel userPassConf = new JLabel("Confirm Pass:");
		userPassConf.setFont(new Font("Arial", Font.BOLD, 13));
		JPasswordField passwordConfText = new JPasswordField("", 10);

		JLabel apagarU = new JLabel("Apagar Utilizador:");
		apagarU.setFont(new Font("Arial", Font.BOLD, 13));
		JTextField apagarUser = new JTextField("", 50);


		Font font = eMailUser.getFont();
		float size = font.getSize() + 1.0f;
		passwordAntigaUser.setFont(font.deriveFont(size));
		nomeAntigoUser.setFont(font.deriveFont(size));
		eMailUser.setFont( font.deriveFont(size) );
		passwordText.setFont( font.deriveFont(size) );
		nomeUser.setFont( font.deriveFont(size) );
		passwordConfText.setFont( font.deriveFont(size) );
		tipoUser.setFont(font.deriveFont(size));
		apagarUser.setFont(font.deriveFont(size));

		centerPanel.add(nomeAntigo);
		centerPanel.add(nomeAntigoUser);

		centerPanel.add(passwordAntiga);
		centerPanel.add(passwordAntigaUser);

		centerPanel.add(tipoU);
		centerPanel.add(tipoUser);

		centerPanel.add(eMail);
		centerPanel.add(eMailUser);

		centerPanel.add(nomeUtilizador);
		centerPanel.add(nomeUser);

		centerPanel.add(userPass);
		centerPanel.add(passwordText);

		centerPanel.add(userPassConf);
		centerPanel.add(passwordConfText);

		centerPanel.add(apagarU);
		centerPanel.add(apagarUser);


		JButton registerButton = new JButton("Criar");
		registerButton.addActionListener(new ActionListener() {

			@SuppressWarnings("deprecation")
			@Override
			public void actionPerformed(ActionEvent e) {
				if(passwordAntigaUser.getText().equals("")) {
					JOptionPane.showMessageDialog(frame, "Campo Current Password não foi preenchido", "ERRO - NECESSÁRIO PREENCHER TODOS OS CAMPOS", JOptionPane.ERROR_MESSAGE);
				}
				if(!passwordText.getText().equals(passwordConfText.getText()))	{
					JOptionPane.showMessageDialog(frame, "Passwords não coincidem", "ERRO - PASSWORDS DO NOT MATCH", JOptionPane.ERROR_MESSAGE);
				}else{
					funcAdmin = new FuncionalidadesAdmin();
					if(!eMailUser.getText().isEmpty()) {
						funcAdmin.alterarUtilizadorEmail(eMailUser, idU);	
					}
					if(!nomeUser.getText().isEmpty()) {
						funcAdmin.alterarUtilizadorNome(nomeUser, idU);
					}
					if(!passwordText.getText().isEmpty() && nomeUtilizador.getText().isEmpty()) {
						funcAdmin.alterarUtilizadorPass(passwordText, idU);
					}
					JOptionPane.showMessageDialog(frame, "Alterado com sucesso");
					frame.dispose();
				}
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
}