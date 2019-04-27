package buttonTable;

import javax.swing.table.AbstractTableModel;

public class JTableTipoUtilizadorModel extends AbstractTableModel {
	
	private static final long serialVersionUID = 1L;
	private static final String[] COLUMN_NAMES = new String[] { "Tipo Utilizador" };

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
			return "Tipo Utilizador"	;	
		default:
			return "Error";
		}
	}
}
