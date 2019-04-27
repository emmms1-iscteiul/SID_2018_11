package buttonTable;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

public class SelecionarTabelaGui {

	private JFrame frame;
	private String frameTitle;

	public SelecionarTabelaGui(String frameTitle) {
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

		if (frameTitle == "Tabela Utilizador") {
			JTable table = new JTable(new JTableUtilizadorAdminModel());
			JScrollPane scrollPane = new JScrollPane(table);
			scrollPane.setPreferredSize(new Dimension(700, 400));
			table.setFillsViewportHeight(true);

			TableCellRenderer buttonRenderer = new JTableButtonRenderer();
			table.getColumn("Criar").setCellRenderer(buttonRenderer);
			table.getColumn("Editar").setCellRenderer(buttonRenderer);
			table.getColumn("Apagar").setCellRenderer(buttonRenderer);
			table.addMouseListener(new JTableButtonMouseListener(table));

			centerPanel.add(scrollPane);
		}

		if (frameTitle == "Tabela Variável") {
			frame.setSize(500, 500);
			Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
			int x = (int) ((dimension.getWidth() - frame.getWidth()) / 2);
			int y = (int) ((dimension.getHeight() - frame.getHeight()) / 2);
			frame.setLocation(x, y);
			frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

			JTable table = new JTable(new JTableVariavelAdminModel());
			JScrollPane scrollPane = new JScrollPane(table);
			scrollPane.setPreferredSize(new Dimension(400, 400));
			table.setFillsViewportHeight(true);

			TableCellRenderer buttonRenderer = new JTableButtonRenderer();

			table.getColumn("Criar").setCellRenderer(buttonRenderer);
			table.getColumn("Editar").setCellRenderer(buttonRenderer);
			table.getColumn("Apagar").setCellRenderer(buttonRenderer);
			table.addMouseListener(new JTableButtonMouseListener(table));

			centerPanel.add(scrollPane);
		}

		if (frameTitle == "Tabela Cultura-Investigador") {
			frame.setSize(600, 500);
			Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
			int x = (int) ((dimension.getWidth() - frame.getWidth()) / 2);
			int y = (int) ((dimension.getHeight() - frame.getHeight()) / 2);
			frame.setLocation(x, y);
			frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

			JTable table = new JTable(new JTableCulturaInvestigadorModel());
			JScrollPane scrollPane = new JScrollPane(table);
			scrollPane.setPreferredSize(new Dimension(500, 400));
			table.setFillsViewportHeight(true);

			TableCellRenderer buttonRenderer = new JTableButtonRenderer();
			table.getColumn("Criar").setCellRenderer(buttonRenderer);
			table.getColumn("Editar").setCellRenderer(buttonRenderer);
			table.getColumn("Apagar").setCellRenderer(buttonRenderer);
			table.addMouseListener(new JTableButtonMouseListener(table));

			centerPanel.add(scrollPane);
		}

		if (frameTitle == "Tabela Medição") {
			frame.setSize(500, 500);
			Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
			int x = (int) ((dimension.getWidth() - frame.getWidth()) / 2);
			int y = (int) ((dimension.getHeight() - frame.getHeight()) / 2);
			frame.setLocation(x, y);
			frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

			JTable table = new JTable(new JTableMedicaoInvestigadorModel());
			JScrollPane scrollPane = new JScrollPane(table);
			scrollPane.setPreferredSize(new Dimension(400, 400));
			table.setFillsViewportHeight(true);

			TableCellRenderer buttonRenderer = new JTableButtonRenderer();
			table.getColumn("Criar").setCellRenderer(buttonRenderer);
			table.getColumn("Editar").setCellRenderer(buttonRenderer);
			table.addMouseListener(new JTableButtonMouseListener(table));

			centerPanel.add(scrollPane);
		}

		if (frameTitle == "Tabela Variável Medida") {
			frame.setSize(500, 500);
			Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
			int x = (int) ((dimension.getWidth() - frame.getWidth()) / 2);
			int y = (int) ((dimension.getHeight() - frame.getHeight()) / 2);
			frame.setLocation(x, y);
			frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

			JTable table = new JTable(new JTableVariavelMedidaInvestigadorModel());
			JScrollPane scrollPane = new JScrollPane(table);
			scrollPane.setPreferredSize(new Dimension(400, 400));
			table.setFillsViewportHeight(true);

			TableCellRenderer buttonRenderer = new JTableButtonRenderer();
			table.getColumn("Criar").setCellRenderer(buttonRenderer);
			table.getColumn("Editar").setCellRenderer(buttonRenderer);
			table.addMouseListener(new JTableButtonMouseListener(table));

			centerPanel.add(scrollPane);
		}
		
		if (frameTitle == "Tabela Cultura-Admin") {
			frame.setSize(500, 500);
			Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
			int x = (int) ((dimension.getWidth() - frame.getWidth()) / 2);
			int y = (int) ((dimension.getHeight() - frame.getHeight()) / 2);
			frame.setLocation(x, y);
			frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

			JTable table = new JTable(new JTableCulturaAdminModel());
			JScrollPane scrollPane = new JScrollPane(table);
			scrollPane.setPreferredSize(new Dimension(400, 400));
			table.setFillsViewportHeight(true);

			centerPanel.add(scrollPane);
		}
		
		if (frameTitle == "Tabela Tipo Cultura") {
			frame.setSize(300, 500);
			Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
			int x = (int) ((dimension.getWidth() - frame.getWidth()) / 2);
			int y = (int) ((dimension.getHeight() - frame.getHeight()) / 2);
			frame.setLocation(x, y);
			frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

			JTable table = new JTable(new JTableTipoCulturaModel());
			JScrollPane scrollPane = new JScrollPane(table);
			scrollPane.setPreferredSize(new Dimension(200, 400));
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

			JTable table = new JTable(new JTableMedicaoAdminModel());
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

			JTable table = new JTable(new JTableVariavelMedidaAdminModel());
			JScrollPane scrollPane = new JScrollPane(table);
			scrollPane.setPreferredSize(new Dimension(400, 400));
			table.setFillsViewportHeight(true);

			centerPanel.add(scrollPane);
		}
		
		if (frameTitle == "Tabela Tipo Utilizador") {
			frame.setSize(300, 500);
			Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
			int x = (int) ((dimension.getWidth() - frame.getWidth()) / 2);
			int y = (int) ((dimension.getHeight() - frame.getHeight()) / 2);
			frame.setLocation(x, y);
			frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

			JTable table = new JTable(new JTableTipoUtilizadorModel());
			JScrollPane scrollPane = new JScrollPane(table);
			scrollPane.setPreferredSize(new Dimension(200, 400));
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

			JTable table = new JTable(new JTableVariavelInvesigadorModel());
			JScrollPane scrollPane = new JScrollPane(table);
			scrollPane.setPreferredSize(new Dimension(200, 400));
			table.setFillsViewportHeight(true);

			centerPanel.add(scrollPane);
		}
		
		frame.add(topPanel, BorderLayout.NORTH);
		frame.add(centerPanel, BorderLayout.CENTER);

	}

	public void open() {
		frame.setVisible(true);
	}

//	public static void main(String[] args) {
////		final ConsultarTabelaGui example = new ConsultarTabelaGui("Consultar Utilizador");
////		final ConsultarTabelaGui example = new ConsultarTabelaGui("Consultar Variável");
////		final ConsultarTabelaGui example = new ConsultarTabelaGui("Consultar Cultura");
////		final ConsultarTabelaGui example = new ConsultarTabelaGui("Consultar Medição");
//		final ConsultarTabelaGui example = new ConsultarTabelaGui("Consultar Variável Medida");
//		javax.swing.SwingUtilities.invokeLater(new Runnable() {
//			public void run() {
//				example.addContent();
//				example.open();
//			}
//		});
//	}
}