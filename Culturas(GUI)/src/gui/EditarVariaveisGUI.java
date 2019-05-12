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
import javax.swing.JTextField;
import javax.swing.WindowConstants;

import buttonTable.JTableVariavelAdminModel;
/**
 * GUI Editar Variaveis
 * @author Eduardo
 *
 */
public class EditarVariaveisGUI {
	private JFrame frame;
	
	FuncionalidadesAdmin funcAdmin;
	private JTableVariavelAdminModel variavelModel;
	int idV = 0;
/*
 * GUI editar variaveis
 */
	public EditarVariaveisGUI(String frameTitle, FuncionalidadesAdmin funcAdmin, int rowIndex,JTableVariavelAdminModel variavelModel) {
		this.funcAdmin = funcAdmin;
		this.idV = rowIndex;
		this.variavelModel=variavelModel;
		frame = new JFrame(frameTitle);
		frame.setSize(350, 200);
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
		JPanel centerPanel = new JPanel(new GridLayout(2,1));
		JPanel bottomPanel = new JPanel();
		JLabel registerLabel = new JLabel("Editar Variaveis");
		registerLabel.setFont(new Font("Arial", Font.CENTER_BASELINE, 30));
		
		topPanel.add(registerLabel);
		
		JLabel newV = new JLabel("Nome Variavel Nova:");
		newV.setFont(new Font("Arial", Font.BOLD, 13));
		JTextField newVar = new JTextField("",10);
		
		
		Font font = newVar.getFont();
		float size = font.getSize() + 1.0f;
		newVar.setFont(font.deriveFont(size));

		
		centerPanel.add(newV);
		centerPanel.add(newVar);
		
		
		JButton registerButton = new JButton("Alterar");
		registerButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {					
					if(!newVar.getText().isEmpty()) {
						funcAdmin.alterarVariavelNome(newVar, idV);
					}
					JOptionPane.showMessageDialog(frame, "Alterado com sucesso");
					variavelModel.fireTableDataChanged();
					frame.dispose();	
			}
		});

		bottomPanel.add(registerButton);

		frame.add(topPanel, BorderLayout.PAGE_START);
		frame.add(centerPanel, BorderLayout.CENTER);
		frame.add(bottomPanel,BorderLayout.SOUTH);

	}
/**
 * Abrir a frame
 */
	public void open() {
		frame.setVisible(true);
	}
}