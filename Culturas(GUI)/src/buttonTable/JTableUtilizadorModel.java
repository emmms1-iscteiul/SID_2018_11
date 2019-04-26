package buttonTable;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.table.AbstractTableModel;

public class JTableUtilizadorModel extends AbstractTableModel {
	private static final long serialVersionUID = 1L;
	private static final String[] COLUMN_NAMES = new String[] { "Nome Utilizador","Mail","Tipo Utilizador", "Password","Editar","Apagar" };


	@Override
	public int getColumnCount() {
		return COLUMN_NAMES.length;
	}

	@Override
	public int getRowCount() {
		return 10;
	}

	@Override
	public String getColumnName(int columnIndex) {
		return COLUMN_NAMES[columnIndex];
	}

	@Override
	public Object getValueAt(final int rowIndex, final int columnIndex) {
		switch (columnIndex) {
		case 0:
			return "Nome Utilizador";
		case 1:
			return "Mail";
		case 2:
			return "Tipo Utilizador";
		case 3:
			return "Password";
		case 4: 
			final JButton button = new JButton(COLUMN_NAMES[columnIndex]);
			button.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					System.out.println("Linha " + rowIndex+" editada!");
						
				}
			});
			return button;
		case 5:
			final JButton button2 = new JButton(COLUMN_NAMES[columnIndex]);
			button2.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					System.out.println("Linha " + rowIndex+" apagada!");
						
				}
			});
			return button2;
		default:
			return "Error";
		}
	}
}