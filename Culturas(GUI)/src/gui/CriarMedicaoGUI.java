package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

import buttonTable.JTableMedicaoInvestigadorModel;
/**
 * GUI Criar Medi��o
 * @author Eduardo
 *
 */
public class CriarMedicaoGUI {

	private JFrame frame;
	FuncionalidadesInvestigador funcInv;
	private JTableMedicaoInvestigadorModel medicaoModel;
	/**
	 * Criar medi��o GUI
	 * @param frameTitle
	 * @param funcInv
	 * @param medicaoModel
	 */
	public CriarMedicaoGUI(String frameTitle, FuncionalidadesInvestigador funcInv,JTableMedicaoInvestigadorModel medicaoModel) {
		this.funcInv=funcInv;
		this.medicaoModel=medicaoModel;
		frame = new JFrame(frameTitle);
		frame.setSize(250, 240);
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
	 * Adicionar conte�do � frame
	 */
	public void addContent() {
		frame.setLayout(new BorderLayout());

		JPanel topPanel = new JPanel();
		JPanel centerPanel = new JPanel(new GridLayout(4,1));
		JPanel bottomPanel = new JPanel();

		JLabel registerLabel = new JLabel("Fazer Medi��o");
		registerLabel.setFont(new Font("Arial", Font.CENTER_BASELINE, 30));

		topPanel.add(registerLabel);

		JLabel ValorMedicao = new JLabel("Valor Medi��o:");
		ValorMedicao.setFont(new Font("Arial", Font.BOLD, 13));
		JTextField ValorMedicaoText = new JTextField("",10);

		JLabel NomeCultura = new JLabel("Nome Cultura:");
		NomeCultura.setFont(new Font("Arial", Font.BOLD, 13));
		JTextField NomeCulturaText = new JTextField("",10);

		JLabel NomeVariavel = new JLabel("Nome Variavel:");
		NomeVariavel.setFont(new Font("Arial", Font.BOLD, 13));
		JTextField NomeVariavelText = new JTextField("",10);

		Font font = ValorMedicaoText.getFont();
		float size = font.getSize() + 1.0f;
		ValorMedicaoText.setFont( font.deriveFont(size) );
		NomeCulturaText.setFont( font.deriveFont(size) );
		NomeVariavelText.setFont( font.deriveFont(size) );

		centerPanel.add(ValorMedicao);
		centerPanel.add(ValorMedicaoText);

		centerPanel.add(NomeCultura);
		centerPanel.add(NomeCulturaText);

		centerPanel.add(NomeVariavel);
		centerPanel.add(NomeVariavelText);


		JButton registerButton = new JButton("Inserir");
		registerButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(ValorMedicaoText.getText().equals("")) {
					JOptionPane.showMessageDialog(frame, "Campo VALOR MEDI��O n�o foi preenchido", "ERRO - NECESS�RIO PREENCHER TODOS OS CAMPOS", JOptionPane.ERROR_MESSAGE);  
				}else if(NomeCulturaText.getText().equals("")) {
					JOptionPane.showMessageDialog(frame, "Campo NOME CULTURA n�o foi preenchido", "ERRO - NECESS�RIO PREENCHER TODOS OS CAMPOS", JOptionPane.ERROR_MESSAGE);
				}else if(NomeVariavelText.getText().equals("")) {
					JOptionPane.showMessageDialog(frame, "Campo NOME VARI�VEL n�o foi preenchido", "ERRO - NECESS�RIO PREENCHER TODOS OS CAMPOS", JOptionPane.ERROR_MESSAGE);
				}else{
					funcInv.inserirMedicao(ValorMedicaoText, NomeCulturaText, NomeVariavelText, frame);
					medicaoModel.fireTableDataChanged();
					double valorMedicao = Double.valueOf(ValorMedicaoText.getText());
					ResultSet alertas = funcInv.filtrarAlertasCultura();
					try {
						while (alertas.next()) {
							double valorMedicaoAlerta = Double.valueOf(alertas.getObject("ValorMedicaoAlerta").toString());
							String nomeVariavelAlerta = alertas.getObject("NomeVariavelAlerta").toString();
							if (alertas.isLast() && valorMedicao == valorMedicaoAlerta && nomeVariavelAlerta.equals(NomeVariavelText.getText())) {
								JOptionPane.showMessageDialog(frame, "Tem um alerta, verifique tabela de Alertas", "ALERTA", JOptionPane.ERROR_MESSAGE);
							}
						} 
					}catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
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