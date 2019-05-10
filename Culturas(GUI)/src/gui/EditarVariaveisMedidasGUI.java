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

import buttonTable.JTableVariavelMedidaInvestigadorModel;

public class EditarVariaveisMedidasGUI {
	private JFrame frame;

	FuncionalidadesInvestigador funcInv;
	private JTableVariavelMedidaInvestigadorModel variavelMedidaModel;
	int idVM = 0;

	public EditarVariaveisMedidasGUI(String frameTitle, FuncionalidadesInvestigador funcInv, int rowIndex,JTableVariavelMedidaInvestigadorModel variavelMedidaModel) {
		this.funcInv = funcInv;
		this.idVM = rowIndex;
		this.variavelMedidaModel=variavelMedidaModel;
		frame = new JFrame(frameTitle);
		frame.setSize(450, 300);
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
		JPanel centerPanel = new JPanel(new GridLayout(6,1));
		JPanel bottomPanel = new JPanel();
		JLabel registerLabel = new JLabel("Editar Variaveis Medidas");
		registerLabel.setFont(new Font("Arial", Font.CENTER_BASELINE, 30));

		topPanel.add(registerLabel);

		JLabel newVI = new JLabel("Novo Limite Inferior:");
		newVI.setFont(new Font("Arial", Font.BOLD, 13));
		JTextField newInf = new JTextField("",10);

		JLabel newVS = new JLabel("Novo Limite Superior:");
		newVS.setFont(new Font("Arial", Font.BOLD, 13));
		JTextField newSup = new JTextField("",10);
		
		JLabel newVAI = new JLabel("Nova Regularidade Alertas do Limite Inferior:");
		newVAI.setFont(new Font("Arial", Font.BOLD, 13));
		JTextField newAInf = new JTextField("",10);
		
		JLabel newVAS = new JLabel("Nova Regularidade Alertas do Limite Superior:");
		newVS.setFont(new Font("Arial", Font.BOLD, 13));
		JTextField newASup = new JTextField("",10);

		JLabel newVariavel = new JLabel("Novo Nome Variavel:");
		newVariavel.setFont(new Font("Arial", Font.BOLD, 13));
		JTextField newV = new JTextField("",10);

		JLabel newCultura = new JLabel("Novo Nome Cultura:");
		newCultura.setFont(new Font("Arial", Font.BOLD, 13));
		JTextField newC = new JTextField("",10);

		Font font = newInf.getFont();
		float size = font.getSize() + 1.0f;
		newInf.setFont(font.deriveFont(size));
		newSup.setFont(font.deriveFont(size));
		newC.setFont(font.deriveFont(size));
		newV.setFont(font.deriveFont(size));

		centerPanel.add(newVI);
		centerPanel.add(newInf);

		centerPanel.add(newVS);
		centerPanel.add(newSup);
		
		centerPanel.add(newVAI);
		centerPanel.add(newAInf);
		
		centerPanel.add(newVAS);
		centerPanel.add(newASup);

		centerPanel.add(newCultura);
		centerPanel.add(newC);

		centerPanel.add(newVariavel);
		centerPanel.add(newV);


		JButton registerButton = new JButton("Alterar");
		registerButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				if(!newInf.getText().isEmpty()) {
					funcInv.alterarVariavelMedidaLimiteInferior(newInf, idVM);
				}
				if(!newSup.getText().isEmpty()) {
					funcInv.alterarVariavelMedidaLimiteSuperior(newSup, idVM);
				}
				if(!newAInf.getText().isEmpty()) {
					funcInv.alterarVariavelMedidaAlertaLimiteInferior(newAInf, idVM);
				}
				if(!newASup.getText().isEmpty()) {
					funcInv.alterarVariavelMedidaAlertaLimiteSuperior(newASup, idVM);
				}
				if(!newC.getText().isEmpty()) {
					funcInv.alterarVariavelMedidaCultura(newC, idVM);
				}
				if(!newV.getText().isEmpty()) {
					funcInv.alterarVariavelMedidaVariavel(newV, idVM);
				}
				JOptionPane.showMessageDialog(frame, "Alterado com sucesso");
				variavelMedidaModel.fireTableDataChanged();
				frame.dispose();
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