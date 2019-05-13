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

import buttonTable.JTableCulturaInvestigadorModel;
/**
 * GUI Editar Cultura
 * @author Eduardo
 *
 */
public class EditarCulturaGUI {

	private JFrame frame;

	FuncionalidadesInvestigador funcInv;
	private JTableCulturaInvestigadorModel culturaModel;
	int idC = 0;
/**
 * GUI editar cultura
 * @param frameTitle
 * @param funcInv
 * @param rowIndex
 * @param culturaModel
 */
	public EditarCulturaGUI(String frameTitle, FuncionalidadesInvestigador funcInv, int rowIndex,JTableCulturaInvestigadorModel culturaModel) {
		this.funcInv = funcInv;
		this.culturaModel=culturaModel;
		this.idC = rowIndex;
		frame = new JFrame(frameTitle);
		frame.setSize(350, 240);
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
		JPanel centerPanel = new JPanel(new GridLayout(3,1));
		JPanel bottomPanel = new JPanel();
		JLabel registerLabel = new JLabel("Editar Cultura");
		registerLabel.setFont(new Font("Arial", Font.CENTER_BASELINE, 30));

		topPanel.add(registerLabel);

		JLabel nomeUtilizador = new JLabel("Novo Nome Utilizador:");
		nomeUtilizador.setFont(new Font("Arial", Font.BOLD, 13));
		JTextField nomeUtil = new JTextField("",10);

		JLabel nomeCultura = new JLabel("Novo Nome Cultura:");
		nomeCultura.setFont(new Font("Arial", Font.BOLD, 13));
		JTextField nomeC = new JTextField("",10);

		JLabel descricaoCultura = new JLabel("Nova Descricao:");
		descricaoCultura.setFont(new Font("Arial", Font.BOLD, 13));
		JTextField descricaoC = new JTextField("",10);


		Font font = descricaoC.getFont();
		float size = font.getSize() + 1.0f;
		nomeC.setFont( font.deriveFont(size) );
		descricaoC.setFont( font.deriveFont(size) );

		centerPanel.add(nomeUtilizador);
		centerPanel.add(nomeUtil);

		centerPanel.add(nomeCultura);
		centerPanel.add(nomeC);

		centerPanel.add(descricaoCultura);	
		centerPanel.add(descricaoC);


		JButton registerButton = new JButton("Alterar");
		registerButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(!descricaoC.getText().isEmpty()) {
					funcInv.alterarCulturaDescricao(descricaoC, idC);
				}
				if(!nomeC.getText().isEmpty()) {
					funcInv.alterarCulturaNome(nomeC, idC);
				}
				if(!nomeUtil.getText().isEmpty()) {
					funcInv.alterarCulturaUtilizador(nomeUtil, idC);
				}
				JOptionPane.showMessageDialog(frame, "Alterado com sucesso");
				culturaModel.fireTableDataChanged();
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
