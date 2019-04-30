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

public class EditarVariaveisGUI {
	private JFrame frame;
	
	FuncionalidadesAdmin funcAdmin;

	public EditarVariaveisGUI(String frameTitle, FuncionalidadesAdmin funcAdmin) {
		this.funcAdmin = funcAdmin;
		frame = new JFrame(frameTitle);
		frame.setSize(350, 200);
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
		JPanel centerPanel = new JPanel(new GridLayout(2,1));
		JPanel bottomPanel = new JPanel();
		JLabel registerLabel = new JLabel("Editar Variaveis");
		registerLabel.setFont(new Font("Arial", Font.CENTER_BASELINE, 30));
		
		topPanel.add(registerLabel);
		
		JLabel oldV = new JLabel("Nome Variavel antiga:");
		oldV.setFont(new Font("Arial", Font.BOLD, 13));
		JTextField oldVar = new JTextField("",10);
		
		JLabel newV = new JLabel("Nome Variavel nova:");
		newV.setFont(new Font("Arial", Font.BOLD, 13));
		JTextField newVar = new JTextField("",10);
		
		
		Font font = oldVar.getFont();
		float size = font.getSize() + 1.0f;
		oldVar.setFont(font.deriveFont(size));
		newVar.setFont(font.deriveFont(size));

		
		centerPanel.add(oldV);
		centerPanel.add(oldVar);
		
		centerPanel.add(newV);
		centerPanel.add(newVar);
		
		
		JButton registerButton = new JButton("Alterar");
		registerButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(!oldVar.getText().isEmpty() && newVar.getText().isEmpty()) {
					JOptionPane.showMessageDialog(frame, "Ambos os campos Nome tem que ser preenchidos", "ERRO - NECESSÁRIO PREENCHER TODOS OS CAMPOS", JOptionPane.ERROR_MESSAGE);  
				}else if(oldVar.getText().isEmpty() && !newVar.getText().isEmpty()) {
					JOptionPane.showMessageDialog(frame, "Ambos os campos Nome tem que ser preenchidos", "ERRO - NECESSÁRIO PREENCHER TODOS OS CAMPOS", JOptionPane.ERROR_MESSAGE);  
				}else{
					
					if(!newVar.getText().isEmpty()) {
						funcAdmin.alterarVariavelNome(oldVar, newVar);
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