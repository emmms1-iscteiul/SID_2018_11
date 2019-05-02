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

public class EditarCulturaGUI {

	private JFrame frame;
	
	FuncionalidadesInvestigador funcInv;
	int idC = 0;

	public EditarCulturaGUI(String frameTitle, FuncionalidadesInvestigador funcInv, int rowIndex) {
		this.funcInv = funcInv;
		this.idC = rowIndex;
		frame = new JFrame(frameTitle);
		frame.setSize(350, 240);
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
		JPanel centerPanel = new JPanel(new GridLayout(3,1));
		JPanel bottomPanel = new JPanel();
		JLabel registerLabel = new JLabel("Editar Cultura");
		registerLabel.setFont(new Font("Arial", Font.CENTER_BASELINE, 30));

		topPanel.add(registerLabel);

		JLabel nomeCAntiga = new JLabel("Nome da Cultura atual:");
		nomeCAntiga.setFont(new Font("Arial", Font.BOLD, 13));
		JTextField nomeCA = new JTextField("",10);

		JLabel nomeCultura = new JLabel("Novo nome da Cultura:");
		nomeCultura.setFont(new Font("Arial", Font.BOLD, 13));
		JTextField nomeC = new JTextField("",10);

		JLabel descricaoCultura = new JLabel("Descricao:");
		descricaoCultura.setFont(new Font("Arial", Font.BOLD, 13));
		JTextField descricaoC = new JTextField("",10);


		Font font = descricaoC.getFont();
		float size = font.getSize() + 1.0f;
		nomeC.setFont( font.deriveFont(size) );
		descricaoC.setFont( font.deriveFont(size) );

		centerPanel.add(nomeCAntiga);
		centerPanel.add(nomeCA);

		centerPanel.add(nomeCultura);
		centerPanel.add(nomeC);

		centerPanel.add(descricaoCultura);	
		centerPanel.add(descricaoC);


		JButton registerButton = new JButton("Alterar");
		registerButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(!nomeCA.getText().isEmpty() && nomeC.getText().isEmpty()) {
					JOptionPane.showMessageDialog(frame, "Ambos os campos Nome tem que ser preenchidos", "ERRO - NECESSÁRIO PREENCHER TODOS OS CAMPOS", JOptionPane.ERROR_MESSAGE);  
				}
				if(nomeCA.getText().isEmpty() && !nomeC.getText().isEmpty()) {
					JOptionPane.showMessageDialog(frame, "Ambos os campos Nome tem que ser preenchidos", "ERRO - NECESSÁRIO PREENCHER TODOS OS CAMPOS", JOptionPane.ERROR_MESSAGE);  
				}else{
					if(!descricaoC.getText().isEmpty() && !nomeCA.getText().isEmpty()) {
						funcInv.alterarCulturaDescricao(descricaoC, idC);
					}

					if(!nomeC.getText().isEmpty()) {
						funcInv.alterarCulturaNome(nomeC, idC);
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
