package buttonTable;

import javax.swing.table.AbstractTableModel;

import gui.FuncionalidadesInvestigador;

public class JTableVariavelInvesigadorModel  extends AbstractTableModel {

	private static final long serialVersionUID = 1L;
	private static final String[] COLUMN_NAMES = new String[] { "Nome Vari�vel" };


	public JTableVariavelInvesigadorModel(FuncionalidadesInvestigador funcInv) {
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
			return "Nome Vari�vel";
		default:
			return "Error";
		}
	}
	
	
}
