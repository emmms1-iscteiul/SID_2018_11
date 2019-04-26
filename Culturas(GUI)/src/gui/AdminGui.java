package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import buttonTable.SelecionarTabelaGui;

public class AdminGui {

	private JFrame frame;

	public AdminGui(String frameTitle) {
		frame = new JFrame(frameTitle);
		frame.setSize(450, 200);
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

		JLabel frameLabel = new JLabel("Manutenção De Variáveis e Utilizadores");
		frameLabel.setFont(new Font("Arial", Font.CENTER_BASELINE, 15));

		topPanel.add(frameLabel);

		JPanel centerPanel = new JPanel(new GridLayout(4, 1));

		JLabel selecionarTabela = new JLabel("Selecionar Tabela : ");
		selecionarTabela.setFont(new Font("Arial", Font.PLAIN, 12));
		

		JComboBox<String> consultarComboBox = new JComboBox<String>();
		consultarComboBox.addItem("");
		consultarComboBox.addItem("Cultura");
		consultarComboBox.addItem("Tipo de Cultura");
		consultarComboBox.addItem("Medição");
		consultarComboBox.addItem("Variável");
		consultarComboBox.addItem("Variável Medida");
		consultarComboBox.addItem("Utilizador");
		consultarComboBox.addItem("Tipo de Utilizador");
		
consultarComboBox.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				 String option = (String) consultarComboBox.getSelectedItem();
				 
				 switch (option) {
				 case "":
					 JOptionPane.showMessageDialog(null, "Tem que escolher umas das tabelas definidas!");
						break;
				case "Utilizador":
					final SelecionarTabelaGui guiUtilizador = new SelecionarTabelaGui("Tabela Utilizador");
					javax.swing.SwingUtilities.invokeLater(new Runnable() {
						public void run() {
							guiUtilizador.addContent();
							guiUtilizador.open();
						}
					});
					break;	
				case "Variável":
					final SelecionarTabelaGui guiVariavel = new SelecionarTabelaGui("Tabela Variável");
					javax.swing.SwingUtilities.invokeLater(new Runnable() {
						public void run() {
							guiVariavel.addContent();
							guiVariavel.open();
						}
					});
					break;		
				default:
					System.out.println("Falta o Guilherme fazer");
					break;
				}
				
			}
		});


		centerPanel.add(selecionarTabela);
		centerPanel.add(consultarComboBox);
		

		frame.add(topPanel, BorderLayout.NORTH);
		frame.add(centerPanel, BorderLayout.CENTER);

	}

	public void open() {
		frame.setVisible(true);
	}

	public static void main(String[] args) {
		AdminGui frame = new AdminGui("Monotorização de Culturas");
		frame.open();
	}

}
