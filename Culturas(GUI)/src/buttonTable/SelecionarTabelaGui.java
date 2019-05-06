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

public class SelecionarTabelaGui {

	private JFrame frame;
	private String frameTitle;
	
	FuncionalidadesAdmin funcAdmin;
	FuncionalidadesInvestigador funcInv;

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

	public void addContent() {
		frame.setLayout(new BorderLayout());

		JPanel topPanel = new JPanel();

		JLabel frameLabel = new JLabel(frame.getTitle());
		frameLabel.setFont(new Font("Arial", Font.CENTER_BASELINE, 15));

		topPanel.add(frameLabel);

		JPanel centerPanel = new JPanel();
		
		JPanel bottomPanel = new JPanel();

		if (frameTitle == "Tabela Utilizador") {
			JTable table = new JTable(new JTableUtilizadorAdminModel(funcAdmin, frame));
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
					CriarUtilizadorGUI frameC = new CriarUtilizadorGUI("Criar Utilizador", funcAdmin);
					frameC.open();
				}
			});
		}

		if (frameTitle == "Tabela Variável") {
			frame.setSize(500, 500);
			Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
			int x = (int) ((dimension.getWidth() - frame.getWidth()) / 2);
			int y = (int) ((dimension.getHeight() - frame.getHeight()) / 2);
			frame.setLocation(x, y);
			frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

			JTable table = new JTable(new JTableVariavelAdminModel(funcAdmin, frame));
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
					CriarVariavelGUI frameC = new CriarVariavelGUI("Criar Variavel", funcAdmin);
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

			JTable table = new JTable(new JTableCulturaInvestigadorModel(funcInv, frame));
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
					CriarCulturaGUI frameC = new CriarCulturaGUI("Criar Cultura", funcInv);
					frameC.open();
				}
			});
		}

		if (frameTitle == "Tabela Medição") {
			frame.setSize(500, 500);
			Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
			int x = (int) ((dimension.getWidth() - frame.getWidth()) / 2);
			int y = (int) ((dimension.getHeight() - frame.getHeight()) / 2);
			frame.setLocation(x, y);
			frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

			JTable table = new JTable(new JTableMedicaoInvestigadorModel(funcInv));
			JScrollPane scrollPane = new JScrollPane(table);
			scrollPane.setPreferredSize(new Dimension(400, 400));
			table.setFillsViewportHeight(true);

			centerPanel.add(scrollPane);
			

			JButton createButton=new JButton("Criar");
			bottomPanel.add(createButton);
			
			createButton.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					CriarMedicaoGUI frameC = new CriarMedicaoGUI("Criar Medicao", funcInv);
					frameC.open();
				}
			});
		}

		if (frameTitle == "Tabela Variável Medida") {
			frame.setSize(500, 500);
			Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
			int x = (int) ((dimension.getWidth() - frame.getWidth()) / 2);
			int y = (int) ((dimension.getHeight() - frame.getHeight()) / 2);
			frame.setLocation(x, y);
			frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

			JTable table = new JTable(new JTableVariavelMedidaInvestigadorModel(funcInv));
			JScrollPane scrollPane = new JScrollPane(table);
			scrollPane.setPreferredSize(new Dimension(400, 400));
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
					CriarVariaveisMedidasGUI frameC = new CriarVariaveisMedidasGUI("Criar Variavel Medida", funcInv);
					frameC.open();
				}
			});
			
			
		}
		
		if (frameTitle == "Tabela Cultura-Admin") {
			frame.setSize(500, 500);
			Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
			int x = (int) ((dimension.getWidth() - frame.getWidth()) / 2);
			int y = (int) ((dimension.getHeight() - frame.getHeight()) / 2);
			frame.setLocation(x, y);
			frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

			JTable table = new JTable(new JTableCulturaAdminModel(funcAdmin));
			JScrollPane scrollPane = new JScrollPane(table);
			scrollPane.setPreferredSize(new Dimension(400, 400));
			table.setFillsViewportHeight(true);

			centerPanel.add(scrollPane);
		}
		
		if (frameTitle == "Tabela Medição-Admin") {
			frame.setSize(500, 500);
			Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
			int x = (int) ((dimension.getWidth() - frame.getWidth()) / 2);
			int y = (int) ((dimension.getHeight() - frame.getHeight()) / 2);
			frame.setLocation(x, y);
			frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

			JTable table = new JTable(new JTableMedicaoAdminModel(funcAdmin));
			JScrollPane scrollPane = new JScrollPane(table);
			scrollPane.setPreferredSize(new Dimension(400, 400));
			table.setFillsViewportHeight(true);

			centerPanel.add(scrollPane);
		}
		
		if (frameTitle == "Tabela Variavel Medida-Admin") {
			frame.setSize(500, 500);
			Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
			int x = (int) ((dimension.getWidth() - frame.getWidth()) / 2);
			int y = (int) ((dimension.getHeight() - frame.getHeight()) / 2);
			frame.setLocation(x, y);
			frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

			JTable table = new JTable(new JTableVariavelMedidaAdminModel(funcAdmin));
			JScrollPane scrollPane = new JScrollPane(table);
			scrollPane.setPreferredSize(new Dimension(400, 400));
			table.setFillsViewportHeight(true);

			centerPanel.add(scrollPane);
		}		

		if (frameTitle == "Tabela Variavel-Investigador") {
			frame.setSize(300, 500);
			Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
			int x = (int) ((dimension.getWidth() - frame.getWidth()) / 2);
			int y = (int) ((dimension.getHeight() - frame.getHeight()) / 2);
			frame.setLocation(x, y);
			frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

			JTable table = new JTable(new JTableVariavelInvestigadorModel(funcInv));
			JScrollPane scrollPane = new JScrollPane(table);
			scrollPane.setPreferredSize(new Dimension(200, 400));
			table.setFillsViewportHeight(true);

			centerPanel.add(scrollPane);
		}
		
		frame.add(topPanel, BorderLayout.NORTH);
		frame.add(centerPanel, BorderLayout.CENTER);
		frame.add(bottomPanel,BorderLayout.SOUTH);

	}

	public void open() {
		frame.setVisible(true);
	}
}