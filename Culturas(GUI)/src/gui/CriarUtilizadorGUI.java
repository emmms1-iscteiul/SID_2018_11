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

import buttonTable.JTableUtilizadorAdminModel;

public class CriarUtilizadorGUI {

	private JFrame frame;
	FuncionalidadesAdmin funcAdmin;
	private JTableUtilizadorAdminModel utilizadorModel;

	public CriarUtilizadorGUI(String frameTitle, FuncionalidadesAdmin funcAdmin,JTableUtilizadorAdminModel utilizadorModel) {
		this.funcAdmin=funcAdmin;
		this.utilizadorModel=utilizadorModel;
		frame = new JFrame(frameTitle);
		frame.setSize(250, 240);
		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (int) ((dimension.getWidth() - frame.getWidth()) / 2);
		int y = (int) ((dimension.getHeight() - frame.getHeight()) / 2);
		frame.setLocation(x, y);
		frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		addContent();
	}

	public JFrame getFrame() {
		return frame;
	}

	public void addContent() {
		frame.setLayout(new BorderLayout());

		JPanel topPanel = new JPanel();
		JPanel centerPanel = new JPanel(new GridLayout(4,1));
		JPanel bottomPanel = new JPanel();

		JLabel registerLabel = new JLabel("Criar User");
		registerLabel.setFont(new Font("Arial", Font.CENTER_BASELINE, 30));

		topPanel.add(registerLabel);
		
		JLabel eMail = new JLabel("Email:");
		eMail.setFont(new Font("Arial", Font.BOLD, 13));
		JTextField eMailUser = new JTextField("",10);
		
		JLabel nomeUtilizador = new JLabel("NomeUtilizador:");
		nomeUtilizador.setFont(new Font("Arial", Font.BOLD, 13));
		JTextField nomeUser = new JTextField("",10);
		
		JLabel userPass = new JLabel("Password:");
		userPass.setFont(new Font("Arial", Font.BOLD, 13));
		JPasswordField passwordText = new JPasswordField("",10);
		
		JLabel userPassConf = new JLabel("Confirm Pass:");
		userPassConf.setFont(new Font("Arial", Font.BOLD, 13));
		JPasswordField passwordConfText = new JPasswordField("", 10);
		
		
		Font font = eMailUser.getFont();
		float size = font.getSize() + 1.0f;
		eMailUser.setFont( font.deriveFont(size) );
		passwordText.setFont( font.deriveFont(size) );
		nomeUser.setFont( font.deriveFont(size) );
		passwordConfText.setFont( font.deriveFont(size) );

		centerPanel.add(eMail);
		centerPanel.add(eMailUser);
		
		centerPanel.add(nomeUtilizador);
		centerPanel.add(nomeUser);
		
		centerPanel.add(userPass);
		centerPanel.add(passwordText);
		
		centerPanel.add(userPassConf);
		centerPanel.add(passwordConfText);

		JButton registerButton = new JButton("Criar");
		registerButton.addActionListener(new ActionListener() {
			
			@SuppressWarnings("deprecation")
			@Override
			public void actionPerformed(ActionEvent e) {
				if(eMailUser.getText().equals("")) {
					JOptionPane.showMessageDialog(frame, "Campo EMAIL não foi preenchido", "ERRO - NECESSÁRIO PREENCHER TODOS OS CAMPOS", JOptionPane.ERROR_MESSAGE);  
				}else if(nomeUser.getText().equals("")) {
					JOptionPane.showMessageDialog(frame, "Campo NOME UTILIZADOR não foi preenchido", "ERRO - NECESSÁRIO PREENCHER TODOS OS CAMPOS", JOptionPane.ERROR_MESSAGE);
				}else if(passwordText.getText().equals("")) {
					JOptionPane.showMessageDialog(frame, "Campo PASSWORD não foi preenchido", "ERRO - NECESSÁRIO PREENCHER TODOS OS CAMPOS", JOptionPane.ERROR_MESSAGE);
				}else if(passwordConfText.getText().equals("")) {
					JOptionPane.showMessageDialog(frame, "Campo CONFIRM PASSWORD não foi preenchido", "ERRO - NECESSÁRIO PREENCHER TODOS OS CAMPOS", JOptionPane.ERROR_MESSAGE);
				}else if(!passwordText.getText().equals(passwordConfText.getText()))	{
					JOptionPane.showMessageDialog(frame, "Password não coincidem", "ERRO - PASSWORDS DO NOT MATCH", JOptionPane.ERROR_MESSAGE);
				}else{
				funcAdmin.inserirInvestigador(nomeUser, eMailUser, passwordText);
				utilizadorModel.fireTableDataChanged();
				JOptionPane.showMessageDialog(frame, "Criação com sucesso");
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