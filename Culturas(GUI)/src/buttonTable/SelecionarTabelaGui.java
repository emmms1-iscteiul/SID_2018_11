package buttonTable;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

import gui.CriarCulturaGUI;
import gui.CriarMedicaoGUI;
import gui.CriarUtilizadorGUI;
import gui.CriarVariaveisMedidasGUI;
import gui.CriarVariavelGUI;
import gui.FuncionalidadesAdmin;
import gui.FuncionalidadesInvestigador;
/**
 * Selecionar Tabela
 * @author Eduardo
 *
 */
public class SelecionarTabelaGui {

	private JFrame frame;
	private String frameTitle;
	
	FuncionalidadesAdmin funcAdmin;
	FuncionalidadesInvestigador funcInv;
/**
 * Selecionar tabela
 * @param frameTitle
 * @param funcAdmin
 * @param funcInv
 */
	public SelecionarTabelaGui(String frameTitle, FuncionalidadesAdmin funcAdmin, FuncionalidadesInvestigador funcInv) {
		this.funcAdmin=funcAdmin;
		this.funcInv=funcInv;
		frame = new JFrame(frameTitle);
		this.frameTitle = frameTitle;
		frame.setSize(800, 500);
		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (int) ((dimension.getWidth() - frame.getWidth()) / 2);
		int y = (int) ((dimension.getHeight() - frame.getHeight()) / 2);
		frame.setLocation(x, y);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
/**
 * Adicionar content � frame
 */
	public void addContent() {
		frame.setLayout(new BorderLayout());

		JPanel topPanel = new JPanel();

		JLabel frameLabel = new JLabel(frame.getTitle());
		frameLabel.setFont(new Font("Arial", Font.CENTER_BASELINE, 15));

		topPanel.add(frameLabel);

		JPanel centerPanel = new JPanel();
		
		JPanel bottomPanel = new JPanel();

		if (frameTitle == "Tabela Utilizador") {
			JTableUtilizadorAdminModel utilizadorModel=new JTableUtilizadorAdminModel(funcAdmin, frame);
			JTable table = new JTable(utilizadorModel);
			JScrollPane scrollPane = new JScrollPane(table);
			scrollPane.setPreferredSize(new Dimension(700, 400));
			table.setFillsViewportHeight(true);
			

			TableCellRenderer buttonRenderer = new JTableButtonRenderer();
			table.getColumn("Editar").setCellRenderer(buttonRenderer);
			table.getColumn("Apagar").setCellRenderer(buttonRenderer);
			table.addMouseListener(new JTableButtonMouseListener(table));

			centerPanel.add(scrollPane);
			
			JButton createButton=new JButton("Criar");
			bottomPanel.add(createButton);
			
			createButton.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {

					CriarUtilizadorGUI frameC = new CriarUtilizadorGUI("Criar Utilizador", funcAdmin,utilizadorModel);
					frameC.open();

				}
			});
		}

		if (frameTitle == "Tabela Variavel") {
			frame.setSize(500, 500);
			Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
			int x = (int) ((dimension.getWidth() - frame.getWidth()) / 2);
			int y = (int) ((dimension.getHeight() - frame.getHeight()) / 2);
			frame.setLocation(x, y);
			frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

			JTableVariavelAdminModel variavelModel=new JTableVariavelAdminModel(funcAdmin, frame);
			JTable table = new JTable(variavelModel);
			JScrollPane scrollPane = new JScrollPane(table);
			scrollPane.setPreferredSize(new Dimension(400, 400));
			table.setFillsViewportHeight(true);

			TableCellRenderer buttonRenderer = new JTableButtonRenderer();


			table.getColumn("Editar").setCellRenderer(buttonRenderer);
			table.getColumn("Apagar").setCellRenderer(buttonRenderer);
			table.addMouseListener(new JTableButtonMouseListener(table));

			centerPanel.add(scrollPane);
			
			JButton createButton=new JButton("Criar");
			bottomPanel.add(createButton);
			
			createButton.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {

					CriarVariavelGUI frameC = new CriarVariavelGUI("Criar Variavel", funcAdmin,variavelModel);
					frameC.open();

				}
			});
		}

		if (frameTitle == "Tabela Cultura-Investigador") {
			frame.setSize(600, 500);
			Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
			int x = (int) ((dimension.getWidth() - frame.getWidth()) / 2);
			int y = (int) ((dimension.getHeight() - frame.getHeight()) / 2);
			frame.setLocation(x, y);
			frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

			JTableCulturaInvestigadorModel culturaModel=new JTableCulturaInvestigadorModel(funcInv, frame);
			JTable table = new JTable(culturaModel);
			JScrollPane scrollPane = new JScrollPane(table);
			scrollPane.setPreferredSize(new Dimension(500, 400));
			table.setFillsViewportHeight(true);

			TableCellRenderer buttonRenderer = new JTableButtonRenderer();
			table.getColumn("Editar").setCellRenderer(buttonRenderer);
			table.getColumn("Apagar").setCellRenderer(buttonRenderer);
			table.addMouseListener(new JTableButtonMouseListener(table));

			centerPanel.add(scrollPane);
			
			JButton createButton=new JButton("Criar");
			bottomPanel.add(createButton);
			
			createButton.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {

					CriarCulturaGUI frameC = new CriarCulturaGUI("Criar Cultura", funcInv,culturaModel);
					frameC.open();

				}
			});
		}

		if (frameTitle == "Tabela Medicao") {
			frame.setSize(650, 500);
			Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
			int x = (int) ((dimension.getWidth() - frame.getWidth()) / 2);
			int y = (int) ((dimension.getHeight() - frame.getHeight()) / 2);
			frame.setLocation(x, y);
			frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

			JTableMedicaoInvestigadorModel medicaoModel=new JTableMedicaoInvestigadorModel(funcInv);
			JTable table = new JTable(medicaoModel);
			JScrollPane scrollPane = new JScrollPane(table);
			scrollPane.setPreferredSize(new Dimension(600, 400));
			table.setFillsViewportHeight(true);


			centerPanel.add(scrollPane);
			

			JButton createButton=new JButton("Criar");
			bottomPanel.add(createButton);
			
			createButton.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {

					CriarMedicaoGUI frameC = new CriarMedicaoGUI("Criar Medicao", funcInv,medicaoModel);
					frameC.open();

				}
			});
		}

		if (frameTitle == "Tabela Variavel Medida") {
			frame.setSize(1350, 600);
			Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
			int x = (int) ((dimension.getWidth() - frame.getWidth()) / 2);
			int y = (int) ((dimension.getHeight() - frame.getHeight()) / 2);
			frame.setLocation(x, y);
			frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

			JTableVariavelMedidaInvestigadorModel variavelMedidaModel=new JTableVariavelMedidaInvestigadorModel(funcInv);
			JTable table = new JTable(variavelMedidaModel);
			JScrollPane scrollPane = new JScrollPane(table);
			scrollPane.setPreferredSize(new Dimension(1300, 500));
			table.setFillsViewportHeight(true);

			TableCellRenderer buttonRenderer = new JTableButtonRenderer();
			table.getColumn("Editar").setCellRenderer(buttonRenderer);
			table.addMouseListener(new JTableButtonMouseListener(table));

			centerPanel.add(scrollPane);
			

			JButton createButton=new JButton("Criar");
			bottomPanel.add(createButton);
			
			createButton.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {

					CriarVariaveisMedidasGUI frameC = new CriarVariaveisMedidasGUI("Criar Variavel Medida", funcInv,variavelMedidaModel);
					frameC.open();

				}
			});
			
			
		}
		
		if (frameTitle == "Tabela Cultura-Admin") {
			frame.setSize(650, 500);
			Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
			int x = (int) ((dimension.getWidth() - frame.getWidth()) / 2);
			int y = (int) ((dimension.getHeight() - frame.getHeight()) / 2);
			frame.setLocation(x, y);
			frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

			JScrollPane scrollPane = scrollPane();
			centerPanel.add(scrollPane);
		}
		
		if (frameTitle == "Tabela Medicao-Admin") {
			frame.setSize(700, 500);
			Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
			int x = (int) ((dimension.getWidth() - frame.getWidth()) / 2);
			int y = (int) ((dimension.getHeight() - frame.getHeight()) / 2);
			frame.setLocation(x, y);
			frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

			JScrollPane scrollPane = scrollPane2();
			centerPanel.add(scrollPane);
		}
		
		if (frameTitle == "Tabela Variavel Medida-Admin") {
			frame.setSize(1350, 600);
			Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
			int x = (int) ((dimension.getWidth() - frame.getWidth()) / 2);
			int y = (int) ((dimension.getHeight() - frame.getHeight()) / 2);
			frame.setLocation(x, y);
			frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

			JScrollPane scrollPane = scrollPane3();
			centerPanel.add(scrollPane);
		}		

		if (frameTitle == "Tabela Variavel-Investigador") {
			frame.setSize(300, 500);
			Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
			int x = (int) ((dimension.getWidth() - frame.getWidth()) / 2);
			int y = (int) ((dimension.getHeight() - frame.getHeight()) / 2);
			frame.setLocation(x, y);
			frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

			JScrollPane scrollPane = scrollPane4();
			centerPanel.add(scrollPane);
		}
		
		if (frameTitle == "Tabela Alertas-Investigador") {
			frame.setSize(1000, 600);
			Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
			int x = (int) ((dimension.getWidth() - frame.getWidth()) / 2);
			int y = (int) ((dimension.getHeight() - frame.getHeight()) / 2);
			frame.setLocation(x, y);
			frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

			JScrollPane scrollPane = scrollPane5();
			centerPanel.add(scrollPane);
		}
		
		
		frame.add(topPanel, BorderLayout.NORTH);
		frame.add(centerPanel, BorderLayout.CENTER);
		frame.add(bottomPanel,BorderLayout.SOUTH);

	}
private JScrollPane scrollPane5() {
	JTable table = new JTable(new JTableAlertasInvestigadorModel(funcInv));
	JScrollPane scrollPane = new JScrollPane(table);
	scrollPane.setPreferredSize(new Dimension(950, 500));
	table.setFillsViewportHeight(true);
	return scrollPane;
}
private JScrollPane scrollPane4() {
	JTable table = new JTable(new JTableVariavelInvestigadorModel(funcInv));
	JScrollPane scrollPane = new JScrollPane(table);
	scrollPane.setPreferredSize(new Dimension(200, 400));
	table.setFillsViewportHeight(true);
	return scrollPane;
}
private JScrollPane scrollPane3() {
	JTable table = new JTable(new JTableVariavelMedidaAdminModel(funcAdmin));
	JScrollPane scrollPane = new JScrollPane(table);
	scrollPane.setPreferredSize(new Dimension(1300, 500));
	table.setFillsViewportHeight(true);
	return scrollPane;
}
private JScrollPane scrollPane2() {
	JTable table = new JTable(new JTableMedicaoAdminModel(funcAdmin));
	JScrollPane scrollPane = new JScrollPane(table);
	scrollPane.setPreferredSize(new Dimension(650, 400));
	table.setFillsViewportHeight(true);
	return scrollPane;
}
private JScrollPane scrollPane() {
	JTable table = new JTable(new JTableCulturaAdminModel(funcAdmin));
	JScrollPane scrollPane = new JScrollPane(table);
	scrollPane.setPreferredSize(new Dimension(600, 400));
	table.setFillsViewportHeight(true);
	return scrollPane;
}
/**
 * Abrir frame
 */
	public void open() {
		frame.setVisible(true);
	}
}