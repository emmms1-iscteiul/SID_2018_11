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
import javax.swing.WindowConstants;
import javax.swing.table.TableCellRenderer;

public class ConsultarTabelaGui {

	private JFrame frame;
	private String frameTitle;

	public ConsultarTabelaGui(String frameTitle) {
		frame = new JFrame(frameTitle);
		this.frameTitle=frameTitle;
		frame.setSize(800, 500);
		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (int) ((dimension.getWidth() - frame.getWidth()) / 2);
		int y = (int) ((dimension.getHeight() - frame.getHeight()) / 2);
		frame.setLocation(x, y);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	}

	public void addContentUtilizador() {
		frame.setLayout(new BorderLayout());

		JPanel topPanel = new JPanel();

		JLabel frameLabel = new JLabel(frame.getTitle());
		frameLabel.setFont(new Font("Arial", Font.CENTER_BASELINE, 15));

		topPanel.add(frameLabel);
		
		JPanel centerPanel = new JPanel();
		
		if(frameTitle=="Consultar Utilizador")	{
		JTable table = new JTable(new JTableUtilizadorModel());
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setPreferredSize(new Dimension(700, 400));
		table.setFillsViewportHeight(true);
		
		TableCellRenderer buttonRenderer = new JTableButtonRenderer();
		table.getColumn("Editar").setCellRenderer(buttonRenderer);
		table.getColumn("Apagar").setCellRenderer(buttonRenderer);
		table.addMouseListener(new JTableButtonMouseListener(table));

		centerPanel.add(scrollPane);
		}

		

		

		frame.add(topPanel, BorderLayout.NORTH);
		frame.add(centerPanel, BorderLayout.CENTER);

	}

	public void open() {
		frame.setVisible(true);
	}

	

	public static void main(String[] args) {
		final ConsultarTabelaGui example = new ConsultarTabelaGui("Consultar Utilizador");
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				example.addContentUtilizador();
				example.open();
			}
		});
	}
}