package buttonTable;


import javax.swing.table.AbstractTableModel;

import gui.FuncionalidadesAdmin;

public class JTableCulturaAdminModel extends AbstractTableModel {
	private static final long serialVersionUID = 1L;
	private static final String[] COLUMN_NAMES = new String[] { "Nome Cultura", "Descrição Cultura", "Tipo Cultura" };

	public JTableCulturaAdminModel(FuncionalidadesAdmin funcAdmin) {
		// TODO Auto-generated constructor stub
	}

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
			return "Nome Cultura";
		case 1:
			return "Descrição Cultura";
		case 2:
			return "Tipo Cultura";
		default:
			return "Error";
		}
	}
}
