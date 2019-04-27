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

public class CriarVariaveisGUI {

	private JFrame frame;
	private JTextField nomeVariavelText = new JTextField("",10);

	public CriarVariaveisGUI(String frameTitle) {
		frame = new JFrame(frameTitle);
		frame.setSize(300, 250);
		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (int) ((dimension.getWidth() - frame.getWidth()) / 2);
		int y = (int) ((dimension.getHeight() - frame.getHeight()) / 2);
		frame.setLocation(x, y);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		addContent();
		getName();
	}

	public JFrame getFrame() {
		return frame;
	}

	public void addContent() {
		frame.setLayout(new BorderLayout());

		JPanel topPanel = new JPanel();
		JPanel centerPanel = new JPanel(new GridLayout(4,1));
		JPanel bottomPanel = new JPanel();

		JLabel registerLabel = new JLabel("Criar Variável");
		registerLabel.setFont(new Font("Arial", Font.CENTER_BASELINE, 30));

		topPanel.add(registerLabel);

		
		JLabel nomeVariavel = new JLabel("Nome Variável:");
		nomeVariavel.setFont(new Font("Arial", Font.BOLD, 13));
//		JTextField nomeVariavelText = new JTextField("",10);
		
		
		Font font = nomeVariavel.getFont();
		float size = font.getSize() + 1.0f;
		nomeVariavel.setFont( font.deriveFont(size) );

		centerPanel.add(nomeVariavel);
		centerPanel.add(nomeVariavelText);
	

		JButton registerButton = new JButton("Inserir");
		registerButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(nomeVariavelText.getText().equals("")) {
					JOptionPane.showMessageDialog(frame, "Campo NOME VARIAVEL não foi preenchido", "ERRO - NECESSÁRIO PREENCHER TODOS OS CAMPOS", JOptionPane.ERROR_MESSAGE);  
				}else{
				JOptionPane.showMessageDialog(frame, "Inserção com sucesso");
				frame.dispose();
				}
			}
		});
		
		bottomPanel.add(registerButton);

		frame.add(topPanel, BorderLayout.PAGE_START);
		frame.add(centerPanel, BorderLayout.CENTER);
		frame.add(bottomPanel,BorderLayout.SOUTH);

	}

	public String getName() {
		return nomeVariavelText.getText();
	}

	public void open() {
		frame.setVisible(true);
	}

	public static void main(String[] args) {
		CriarVariaveisGUI frame = new CriarVariaveisGUI("Inserir Variavel");
		frame.open();
	}
	
	
}