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

/**
 * GUI Editar Utilizador
 * @author Eduardo
 *
 */
public class EditarUtilizadorGUI {

	private JFrame frame;
	FuncionalidadesAdmin funcAdmin;
	private JTableUtilizadorAdminModel utilizadorModel;
	int idU = 0;
/**
 * GUI editar user
 * @param frameTitle
 * @param funcAdmin
 * @param rowIndex
 * @param utilizadorModel
 */
	public EditarUtilizadorGUI(String frameTitle, FuncionalidadesAdmin funcAdmin, int rowIndex,JTableUtilizadorAdminModel utilizadorModel) {
		this.funcAdmin=funcAdmin;
		this.idU = rowIndex;
		this.utilizadorModel=utilizadorModel;
		frame = new JFrame(frameTitle);
		frame.setSize(400, 300);
		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (int) ((dimension.getWidth() - frame.getWidth()) / 2);
		int y = (int) ((dimension.getHeight() - frame.getHeight()) / 2);
		frame.setLocation(x, y);
		frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		addContent();
	}
/**
 * Frame
 * @return
 */
	public JFrame getFrame() {
		return frame;
	}
/**
 * Adicionar conteúdo à frame
 */
	public void addContent() {

		frame.setLayout(new BorderLayout());

		JPanel topPanel = new JPanel();
		JPanel centerPanel = new JPanel(new GridLayout(8,1));
		JPanel bottomPanel = new JPanel();

		JLabel registerLabel = new JLabel("Editar Utilizador");
		registerLabel.setFont(new Font("Arial", Font.CENTER_BASELINE, 30));

		topPanel.add(registerLabel);

		JLabel eMail = new JLabel("Novo Email:");
		eMail.setFont(new Font("Arial", Font.BOLD, 13));
		JTextField eMailUser = new JTextField("",10);

		JLabel nomeUtilizador = new JLabel("Novo Nome Utilizador:");
		nomeUtilizador.setFont(new Font("Arial", Font.BOLD, 13));
		JTextField nomeUser = new JTextField("",10);

		JLabel userPass = new JLabel("Novo Password:");
		userPass.setFont(new Font("Arial", Font.BOLD, 13));
		JPasswordField passwordText = new JPasswordField("",10);


		Font font = eMailUser.getFont();
		float size = font.getSize() + 1.0f;
		eMailUser.setFont( font.deriveFont(size) );
		passwordText.setFont( font.deriveFont(size) );
		nomeUser.setFont( font.deriveFont(size) );

		centerPanel.add(eMail);
		centerPanel.add(eMailUser);

		centerPanel.add(nomeUtilizador);
		centerPanel.add(nomeUser);

		centerPanel.add(userPass);
		centerPanel.add(passwordText);


		JButton registerButton = new JButton("Editar");
		registerButton.addActionListener(new ActionListener() {

			@SuppressWarnings("deprecation")
			@Override
			public void actionPerformed(ActionEvent e) {
					if(!eMailUser.getText().isEmpty()) {
						funcAdmin.alterarUtilizadorEmail(eMailUser, idU);	
					}
					if(!nomeUser.getText().isEmpty()) {
						funcAdmin.alterarUtilizadorNome(nomeUser, idU);
					}
					if(!passwordText.getText().isEmpty()) {
						funcAdmin.alterarUtilizadorPass(passwordText, idU);
					}
					JOptionPane.showMessageDialog(frame, "Alterado com sucesso");
					utilizadorModel.fireTableDataChanged();
					frame.dispose();
			}
		});

		bottomPanel.add(registerButton);

		frame.add(topPanel, BorderLayout.PAGE_START);
		frame.add(centerPanel, BorderLayout.CENTER);
		frame.add(bottomPanel,BorderLayout.SOUTH);

	}
/**
 * Abrir frame
 */
	public void open() {
		frame.setVisible(true);
	}
}