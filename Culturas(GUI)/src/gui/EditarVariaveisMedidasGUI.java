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

public class EditarVariaveisMedidasGUI {
	private JFrame frame;
	
	FuncionalidadesInvestigador funcInv;

	public EditarVariaveisMedidasGUI(String frameTitle, FuncionalidadesInvestigador funcInv) {
		this.funcInv = funcInv;
		frame = new JFrame(frameTitle);
		frame.setSize(450, 300);
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
		JPanel centerPanel = new JPanel(new GridLayout(6,1));
		JPanel bottomPanel = new JPanel();
		JLabel registerLabel = new JLabel("Editar Variaveis Medidas");
		registerLabel.setFont(new Font("Arial", Font.CENTER_BASELINE, 30));
		
		topPanel.add(registerLabel);
		
		JLabel newVI = new JLabel("Limite Inferior:");
		newVI.setFont(new Font("Arial", Font.BOLD, 13));
		JTextField newInf = new JTextField("",10);
		
		JLabel newVS = new JLabel("Limite Superior:");
		newVS.setFont(new Font("Arial", Font.BOLD, 13));
		JTextField newSup = new JTextField("",10);
		
		JLabel nomeCultura = new JLabel("Nome cultura a modificar:");
		nomeCultura.setFont(new Font("Arial", Font.BOLD, 13));
		JTextField nomeC = new JTextField("",10);
		
		JLabel nomeVariavel = new JLabel("Nome variavel a modificar:");
		nomeVariavel.setFont(new Font("Arial", Font.BOLD, 13));
		JTextField nomeV = new JTextField("",10);
		
		JLabel newVariavel = new JLabel("Novo nome variavel a modificar:");
		newVariavel.setFont(new Font("Arial", Font.BOLD, 13));
		JTextField newV = new JTextField("",10);
		
		JLabel newCultura = new JLabel("Novo nome variavel a modificar:");
		newCultura.setFont(new Font("Arial", Font.BOLD, 13));
		JTextField newC = new JTextField("",10);
		
		Font font = newInf.getFont();
		float size = font.getSize() + 1.0f;
		newInf.setFont(font.deriveFont(size));
		newSup.setFont(font.deriveFont(size));
		nomeC.setFont(font.deriveFont(size));
		nomeV.setFont(font.deriveFont(size));
		newC.setFont(font.deriveFont(size));
		newV.setFont(font.deriveFont(size));

		centerPanel.add(newVI);
		centerPanel.add(newInf);
		
		centerPanel.add(newVS);
		centerPanel.add(newSup);
		
		centerPanel.add(nomeCultura);
		centerPanel.add(nomeC);
		
		centerPanel.add(nomeVariavel);
		centerPanel.add(nomeV);
		
		centerPanel.add(newCultura);
		centerPanel.add(newC);
		
		centerPanel.add(newVariavel);
		centerPanel.add(newV);
		
		
		JButton registerButton = new JButton("Alterar");
		registerButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("hello");
				if(!newInf.getText().isEmpty() && nomeC.getText().isEmpty() && nomeV.getText().isEmpty()) {
					JOptionPane.showMessageDialog(frame, "Ambos os campos Nome Cultura e Nome Variavel tem que ser preenchidos", "ERRO - NECESSÁRIO PREENCHER TODOS OS CAMPOS", JOptionPane.ERROR_MESSAGE); 
				}else if(!newSup.getText().isEmpty() && nomeC.getText().isEmpty() && nomeV.getText().isEmpty() ) {
					JOptionPane.showMessageDialog(frame, "Ambos os campos Nome Cultura e Nome Variavel tem que ser preenchidos", "ERRO - NECESSÁRIO PREENCHER TODOS OS CAMPOS", JOptionPane.ERROR_MESSAGE);  
				}else if(!newC.getText().isEmpty() && nomeC.getText().isEmpty() && nomeV.getText().isEmpty() ) {
					JOptionPane.showMessageDialog(frame, "Ambos os campos Nome Cultura e Nome Variavel tem que ser preenchidos", "ERRO - NECESSÁRIO PREENCHER TODOS OS CAMPOS", JOptionPane.ERROR_MESSAGE);  
				}else if(!newV.getText().isEmpty() && nomeC.getText().isEmpty() && nomeV.getText().isEmpty() ) {
					JOptionPane.showMessageDialog(frame, "Ambos os campos Nome Cultura e Nome Variavel tem que ser preenchidos", "ERRO - NECESSÁRIO PREENCHER TODOS OS CAMPOS", JOptionPane.ERROR_MESSAGE);  
				}else{
					
					if(!newInf.getText().isEmpty() && !nomeCultura.getText().isEmpty() && !nomeVariavel.getText().isEmpty()) {
						funcInv.alterarVariavelMedidaLimiteInferior(newInf, nomeC, nomeV);
					}
					if(!newSup.getText().isEmpty() && !nomeCultura.getText().isEmpty() && !nomeVariavel.getText().isEmpty()) {
						funcInv.alterarVariavelMedidaLimiteSuperior(newSup, nomeC, nomeV);
					}
					if(!newC.getText().isEmpty() && !nomeCultura.getText().isEmpty() && !nomeVariavel.getText().isEmpty()) {
						funcInv.alterarVariavelMedidaLimiteSuperior(newC, nomeC, nomeV);
					}
					if(!newV.getText().isEmpty() && !nomeCultura.getText().isEmpty() && !nomeVariavel.getText().isEmpty()) {
						funcInv.alterarVariavelMedidaLimiteSuperior(newV, nomeC, nomeV);
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