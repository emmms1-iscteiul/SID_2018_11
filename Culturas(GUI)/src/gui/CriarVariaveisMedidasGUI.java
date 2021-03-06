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
/**
 * GUI Criar Variaveis Medidas
 * @author Eduardo
 *
 */
public class CriarVariaveisMedidasGUI {

	private JFrame frame;
	
	FuncionalidadesInvestigador funcInv;
	private JTableVariavelMedidaInvestigadorModel variavelMedidaModel;
/**
 * GUI criar variaveis medidas
 * @param frameTitle
 * @param funcInv
 * @param variavelMedidaModel
 */
	public CriarVariaveisMedidasGUI(String frameTitle, FuncionalidadesInvestigador funcInv,JTableVariavelMedidaInvestigadorModel variavelMedidaModel) {
		this.funcInv = funcInv;
		this.variavelMedidaModel=variavelMedidaModel;
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
		JPanel centerPanel = new JPanel(new GridLayout(6,1));
		JPanel bottomPanel = new JPanel();

		JLabel registerLabel = new JLabel("Inserir");
		registerLabel.setFont(new Font("Arial", Font.CENTER_BASELINE, 30));

		topPanel.add(registerLabel);
		
		JLabel LimiteInferior = new JLabel("Limite Inferior:");
		LimiteInferior.setFont(new Font("Arial", Font.BOLD, 13));
		JTextField LimiteInferiorText = new JTextField("",10);
		
		JLabel LimiteSuperior = new JLabel("Limite Superior:");
		LimiteSuperior.setFont(new Font("Arial", Font.BOLD, 13));
		JTextField LimiteSuperiorText = new JTextField("",10);
		
		JLabel RegularidadeLimiteInferior = new JLabel("Regularidade Alertas do Limite Inferior:");
		RegularidadeLimiteInferior.setFont(new Font("Arial", Font.BOLD, 13));
		JTextField RegularidadeLimiteInferiorText = new JTextField("",10);
		
		JLabel RegularidadeLimiteSuperior = new JLabel("Regularidade Alertas do Limite Superior:");
		RegularidadeLimiteSuperior.setFont(new Font("Arial", Font.BOLD, 13));
		JTextField RegularidadeLimiteSuperiorText = new JTextField("",10);
		
		JLabel NomeCultura = new JLabel("Nome Cultura:");
		NomeCultura.setFont(new Font("Arial", Font.BOLD, 13));
		JTextField NomeCulturaText = new JTextField("",10);
		
		JLabel NomeVariavel = new JLabel("Nome Vari�vel:");
		NomeVariavel.setFont(new Font("Arial", Font.BOLD, 13));
		JTextField NomeVariavelText = new JTextField("", 10);
		
		
		Font font = LimiteInferiorText.getFont();
		float size = font.getSize() + 1.0f;
		LimiteInferiorText.setFont( font.deriveFont(size) );
		LimiteSuperiorText.setFont( font.deriveFont(size) );
		NomeCulturaText.setFont( font.deriveFont(size) );
		NomeVariavelText.setFont( font.deriveFont(size) );
		RegularidadeLimiteInferiorText.setFont( font.deriveFont(size) );
		RegularidadeLimiteSuperiorText.setFont( font.deriveFont(size) );

		centerPanel.add(LimiteInferior);
		centerPanel.add(LimiteInferiorText);
		
		centerPanel.add(LimiteSuperior);
		centerPanel.add(LimiteSuperiorText);
		
		centerPanel.add(RegularidadeLimiteInferior);
		centerPanel.add(RegularidadeLimiteInferiorText);
		
		centerPanel.add(RegularidadeLimiteSuperior);
		centerPanel.add(RegularidadeLimiteSuperiorText);
		
		centerPanel.add(NomeCultura);
		centerPanel.add(NomeCulturaText);
		
		centerPanel.add(NomeVariavel);
		centerPanel.add(NomeVariavelText);
			

		JButton registerButton = new JButton("inserir");
		registerButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(LimiteInferiorText.getText().equals("")) {
					JOptionPane.showMessageDialog(frame, "Campo LIMITE INFERIOR n�o foi preenchido", "ERRO - NECESS�RIO PREENCHER TODOS OS CAMPOS", JOptionPane.ERROR_MESSAGE);  
				}else if(LimiteSuperiorText.getText().equals("")) {
					JOptionPane.showMessageDialog(frame, "Campo LIMITE SUPERIOR n�o foi preenchido", "ERRO - NECESS�RIO PREENCHER TODOS OS CAMPOS", JOptionPane.ERROR_MESSAGE);
				}else if(RegularidadeLimiteInferiorText.getText().equals("")) {
					JOptionPane.showMessageDialog(frame, "Campo REGULARIDADE ALERTAS LIMITE INFERIOR n�o foi preenchido", "ERRO - NECESS�RIO PREENCHER TODOS OS CAMPOS", JOptionPane.ERROR_MESSAGE);
				}else if(RegularidadeLimiteSuperiorText.getText().equals("")) {
					JOptionPane.showMessageDialog(frame, "Campo REGULARIDADE ALERTAS LIMITE SUPERIOR n�o foi preenchido", "ERRO - NECESS�RIO PREENCHER TODOS OS CAMPOS", JOptionPane.ERROR_MESSAGE);
				}else if(NomeCulturaText.getText().equals("")) {
					JOptionPane.showMessageDialog(frame, "Campo NOME CULTURA n�o foi preenchido", "ERRO - NECESS�RIO PREENCHER TODOS OS CAMPOS", JOptionPane.ERROR_MESSAGE);
				}else if(NomeVariavelText.getText().equals("")) {
					JOptionPane.showMessageDialog(frame, "Campo NOME VARI�VEL n�o foi preenchido", "ERRO - NECESS�RIO PREENCHER TODOS OS CAMPOS", JOptionPane.ERROR_MESSAGE);
				}else{
				funcInv.inserirVariavelMedida(LimiteInferiorText, LimiteSuperiorText, RegularidadeLimiteInferiorText, RegularidadeLimiteSuperiorText, NomeCulturaText, NomeVariavelText, frame);
				variavelMedidaModel.fireTableDataChanged();
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