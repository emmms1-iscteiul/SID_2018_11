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
 * GUI Criar Cultura
 * @author Eduardo
 *
 */
public class CriarCulturaGUI {

	private JFrame frame;
	FuncionalidadesInvestigador funcInv;
	private JTableCulturaInvestigadorModel culturaModel;
/**
 * GUI cria��o de cultura
 * @param frameTitle
 * @param funcInv
 * @param culturaModel
 */
	public CriarCulturaGUI(String frameTitle, FuncionalidadesInvestigador funcInv,JTableCulturaInvestigadorModel culturaModel) {
		this.funcInv = funcInv;
		this.culturaModel=culturaModel;
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

		JLabel registerLabel = new JLabel("Criar Cultura");
		registerLabel.setFont(new Font("Arial", Font.CENTER_BASELINE, 30));

		topPanel.add(registerLabel);
		
		JLabel NomeCultura = new JLabel("Nome Cultura:");
		NomeCultura.setFont(new Font("Arial", Font.BOLD, 13));
		JTextField NomeCulturaText = new JTextField("",30);
		
		JLabel DescricaoCultura = new JLabel("Descric�o Cultura:");
		DescricaoCultura.setFont(new Font("Arial", Font.BOLD, 13));
		JTextField DescricaoCulturaText = new JTextField("",100);
		
		Font font = NomeCulturaText.getFont();
		float size = font.getSize() + 1.0f;
		NomeCulturaText.setFont( font.deriveFont(size) );
		DescricaoCulturaText.setFont( font.deriveFont(size) );

		centerPanel.add(NomeCultura);
		centerPanel.add(NomeCulturaText);
		
		centerPanel.add(DescricaoCultura);
		centerPanel.add(DescricaoCulturaText);

		JButton registerButton = new JButton("Criar");
		registerButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(NomeCulturaText.getText().equals("")) {
					JOptionPane.showMessageDialog(frame, "Campo NOME CULTURA n�o foi preenchido", "ERRO - NECESS�RIO PREENCHER TODOS OS CAMPOS", JOptionPane.ERROR_MESSAGE);  
				}else if(DescricaoCulturaText.getText().equals("")) {
					JOptionPane.showMessageDialog(frame, "Campo DESCRI��O CULTURA n�o foi preenchido", "ERRO - NECESS�RIO PREENCHER TODOS OS CAMPOS", JOptionPane.ERROR_MESSAGE);
				}else{
				funcInv.inserirCultura(NomeCulturaText, DescricaoCulturaText);
				JOptionPane.showMessageDialog(frame, "Cria��o com sucesso");
				culturaModel.fireTableDataChanged();
				frame.dispose();
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