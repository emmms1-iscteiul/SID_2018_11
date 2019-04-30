package buttonTable;


import javax.swing.table.AbstractTableModel;

import gui.FuncionalidadesAdmin;

public class JTableVariavelMedidaAdminModel extends AbstractTableModel{
	private static final long serialVersionUID = 1L;
	private static final String[] COLUMN_NAMES = new String[] { "LimiteInferior",	"LimiteSuperior", "Nome Cultura", "Nome Variavel" };
	FuncionalidadesAdmin funcAdmin;

	public JTableVariavelMedidaAdminModel(FuncionalidadesAdmin funcAdmin) {
		// TODO Auto-generated constructor stub
		this.funcAdmin = funcAdmin;
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
			return "LimiteInferior"	;	
		case 1:
			return "LimiteSuperior";
		case 2:
			return "Nome Cultura";
		case 3:
			return "Nome Variavel";
		default:
			return "Error";
		}
	}
}