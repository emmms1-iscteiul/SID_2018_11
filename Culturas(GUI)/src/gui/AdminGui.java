package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import buttonTable.SelecionarTabelaGui;

public class AdminGui {

	private JFrame frame;
	FuncionalidadesAdmin funcAdmin;
	FuncionalidadesInvestigador funcInv = null;

	public AdminGui(String frameTitle, FuncionalidadesAdmin funcAdmin) {
		this.funcAdmin=funcAdmin;
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

		JLabel frameLabel = new JLabel("Manuten��o De Vari�veis e Utilizadores");
		frameLabel.setFont(new Font("Arial", Font.CENTER_BASELINE, 15));

		topPanel.add(frameLabel);

		JPanel centerPanel = new JPanel(new GridLayout(4, 1));

		JLabel selecionarTabela = new JLabel("Selecionar Tabela : ");
		selecionarTabela.setFont(new Font("Arial", Font.PLAIN, 12));

		JComboBox<String> consultarComboBox = new JComboBox<String>();
		consultarComboBox.addItem("");
		consultarComboBox.addItem("Cultura");
		consultarComboBox.addItem("Tipo de Cultura");
		consultarComboBox.addItem("Medi��o");
		consultarComboBox.addItem("Vari�vel");
		consultarComboBox.addItem("Vari�vel Medida");
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
				case "Vari�vel":
					final SelecionarTabelaGui guiVariavel = new SelecionarTabelaGui("Tabela Vari�vel");
					javax.swing.SwingUtilities.invokeLater(new Runnable() {
						public void run() {
							guiVariavel.addContent();
							guiVariavel.open();
						}
					});
					break;
				case "Cultura":
					final SelecionarTabelaGui guiCulturaAdmin = new SelecionarTabelaGui("Tabela Cultura-Admin");
					javax.swing.SwingUtilities.invokeLater(new Runnable() {
						public void run() {
							guiCulturaAdmin.addContent();
							guiCulturaAdmin.open();
						}
					});
					break;
				case "Tipo de Cultura":
					final SelecionarTabelaGui guiTipoCultura = new SelecionarTabelaGui("Tabela Tipo Cultura");
					javax.swing.SwingUtilities.invokeLater(new Runnable() {
						public void run() {
							guiTipoCultura.addContent();
							guiTipoCultura.open();
						}
					});
					break;
				case "Medi��o":
					final SelecionarTabelaGui guiMedicaoAdmin = new SelecionarTabelaGui("Tabela Medi��o-Admin");
					javax.swing.SwingUtilities.invokeLater(new Runnable() {
						public void run() {
							guiMedicaoAdmin.addContent();
							guiMedicaoAdmin.open();
						}
					});
					break;
				case "Vari�vel Medida":
					final SelecionarTabelaGui guiVariavelMedidaAdmin = new SelecionarTabelaGui(
							"Tabela Variavel Medida-Admin");
					javax.swing.SwingUtilities.invokeLater(new Runnable() {
						public void run() {
							guiVariavelMedidaAdmin.addContent();
							guiVariavelMedidaAdmin.open();
						}
					});
					break;
				case "Tipo de Utilizador":
					final SelecionarTabelaGui guiTipoUtilizador = new SelecionarTabelaGui("Tabela Tipo Utilizador");
					javax.swing.SwingUtilities.invokeLater(new Runnable() {
						public void run() {
							guiTipoUtilizador.addContent();
							guiTipoUtilizador.open();
						}
					});
					break;
				default:
					System.out.println("Falta o Guilherme fazer");
					break;
				}

			}
		});

		JButton logoutButton = new JButton("Logout");
		
		logoutButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("Admin Logout");
				System.exit(0);
			}
		});

		centerPanel.add(selecionarTabela);
		centerPanel.add(consultarComboBox);

		JPanel bottomPanel = new JPanel();

		bottomPanel.add(logoutButton);

		frame.add(topPanel, BorderLayout.NORTH);
		frame.add(centerPanel, BorderLayout.CENTER);
		frame.add(bottomPanel, BorderLayout.SOUTH);

	}

	public void open() {
		frame.setVisible(true);
	}


}
