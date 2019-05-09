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


public class InvestigadorGui {

	private JFrame frame;
	FuncionalidadesInvestigador funcInv;
	FuncionalidadesAdmin funcAdmin = null;

	public InvestigadorGui(String frameTitle, FuncionalidadesInvestigador funcInv) {
		this.funcInv = funcInv;
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


		JPanel topPanel=new JPanel();

		JLabel frameLabel = new JLabel("Manutenção De Culturas e Medições");
		frameLabel.setFont(new Font("Arial", Font.CENTER_BASELINE, 15));

		topPanel.add(frameLabel);

		JPanel centerPanel=new JPanel(new GridLayout(4,1));

		JLabel selecionarTabela=new JLabel("Selecionar Tabela ");

		JComboBox<String> consultarComboBox=new JComboBox<String>();
		consultarComboBox.addItem("");
		consultarComboBox.addItem("Cultura");
		consultarComboBox.addItem("Medição");
		consultarComboBox.addItem("Variável");
		consultarComboBox.addItem("Variável Medida");

		consultarComboBox.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String option = (String) consultarComboBox.getSelectedItem();

				switch (option) {
				case "":
					JOptionPane.showMessageDialog(null, "Tem que escolher umas das tabelas definidas!");
					break;
				case "Cultura":
					final SelecionarTabelaGui guiConsulta = new SelecionarTabelaGui("Tabela Cultura-Investigador", funcAdmin, funcInv);
					javax.swing.SwingUtilities.invokeLater(new Runnable() {
						public void run() {
							guiConsulta.addContent();
							guiConsulta.open();
						}
					});
					break;
				case "Medição":
					final SelecionarTabelaGui guiMedicao = new SelecionarTabelaGui("Tabela Medicao", funcAdmin, funcInv);
					javax.swing.SwingUtilities.invokeLater(new Runnable() {
						public void run() {
							guiMedicao.addContent();
							guiMedicao.open();
						}
					});
					break;	
				case "Variável Medida":
					final SelecionarTabelaGui guiVariavelMedida = new SelecionarTabelaGui("Tabela Variavel Medida", funcAdmin, funcInv);
					javax.swing.SwingUtilities.invokeLater(new Runnable() {
						public void run() {
							guiVariavelMedida.addContent();
							guiVariavelMedida.open();
						}
					});
					break;
				case "Variável":
					final SelecionarTabelaGui guiVariavel = new SelecionarTabelaGui("Tabela Variavel-Investigador", funcAdmin, funcInv);
					javax.swing.SwingUtilities.invokeLater(new Runnable() {
						public void run() {
							guiVariavel.addContent();
							guiVariavel.open();
						}
					});
					break;
				default:
					System.out.println("Erro");
					break;
				}

			}
		});


		JButton logoutButton = new JButton("Logout");

		logoutButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				funcInv.logout();
				frame.dispose();
				LoginGui login = new LoginGui("MonotorizaÃ§Ã£o de Culturas");
				login.open();
			}
		});

		centerPanel.add(selecionarTabela);
		centerPanel.add(consultarComboBox);


		JPanel bottomPanel = new JPanel();

		bottomPanel.add(logoutButton);


		frame.add(topPanel,BorderLayout.NORTH);
		frame.add(centerPanel,BorderLayout.CENTER);
		frame.add(bottomPanel, BorderLayout.SOUTH);

	}

	public void open() {
		frame.setVisible(true);
	}

}

