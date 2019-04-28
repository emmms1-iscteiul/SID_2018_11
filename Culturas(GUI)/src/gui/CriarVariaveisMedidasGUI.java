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

public class CriarVariaveisMedidasGUI {

	private JFrame frame;
	
	FuncionalidadesInvestigador funcInv;

	public CriarVariaveisMedidasGUI(String frameTitle, FuncionalidadesInvestigador funcInv) {
		this.funcInv = funcInv;
		frame = new JFrame(frameTitle);
		frame.setSize(250, 240);
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
		JPanel centerPanel = new JPanel(new GridLayout(4,1));
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
		
		JLabel NomeCultura = new JLabel("Nome Cultura:");
		NomeCultura.setFont(new Font("Arial", Font.BOLD, 13));
		JTextField NomeCulturaText = new JTextField("",10);
		
		JLabel NomeVariavel = new JLabel("Nome Variável:");
		NomeVariavel.setFont(new Font("Arial", Font.BOLD, 13));
		JTextField NomeVariavelText = new JTextField("", 10);
		
		
		Font font = LimiteInferiorText.getFont();
		float size = font.getSize() + 1.0f;
		LimiteInferiorText.setFont( font.deriveFont(size) );
		LimiteSuperiorText.setFont( font.deriveFont(size) );
		NomeCulturaText.setFont( font.deriveFont(size) );
		NomeVariavelText.setFont( font.deriveFont(size) );

		centerPanel.add(LimiteInferior);
		centerPanel.add(LimiteInferiorText);
		
		centerPanel.add(LimiteSuperior);
		centerPanel.add(LimiteSuperiorText);
		
		centerPanel.add(NomeCultura);
		centerPanel.add(NomeCulturaText);
		
		centerPanel.add(NomeVariavel);
		centerPanel.add(NomeVariavelText);
	

		JButton registerButton = new JButton("inserir");
		registerButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(LimiteInferiorText.getText().equals("")) {
					JOptionPane.showMessageDialog(frame, "Campo LIMITE INFERIOR não foi preenchido", "ERRO - NECESSÁRIO PREENCHER TODOS OS CAMPOS", JOptionPane.ERROR_MESSAGE);  
				}else if(LimiteSuperiorText.getText().equals("")) {
					JOptionPane.showMessageDialog(frame, "Campo LIMITE SUPERIOR não foi preenchido", "ERRO - NECESSÁRIO PREENCHER TODOS OS CAMPOS", JOptionPane.ERROR_MESSAGE);
				}else if(NomeCulturaText.getText().equals("")) {
					JOptionPane.showMessageDialog(frame, "Campo NOME CULTURA não foi preenchido", "ERRO - NECESSÁRIO PREENCHER TODOS OS CAMPOS", JOptionPane.ERROR_MESSAGE);
				}else if(NomeVariavelText.getText().equals("")) {
					JOptionPane.showMessageDialog(frame, "Campo NOME VARIÁVEL não foi preenchido", "ERRO - NECESSÁRIO PREENCHER TODOS OS CAMPOS", JOptionPane.ERROR_MESSAGE);
				}else{
				funcInv.inserirVariavelMedida(LimiteInferiorText, LimiteSuperiorText, NomeCulturaText, NomeVariavelText);
				JOptionPane.showMessageDialog(frame, "Inserção com sucesso");
				frame.dispose();
				InvestigadorGui frame = new InvestigadorGui("Monotorização de Culturas", funcInv);
				frame.open();
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