package buttonTable;



import javax.swing.table.AbstractTableModel;


import gui.FuncionalidadesInvestigador;

public class JTableAlertasInvestigadorModel extends AbstractTableModel{

	private static final long serialVersionUID = 1L;
	private static final String[] COLUMN_NAMES = new String[] { "Id Alerta", "Data e Hora do Alerta", "Nome Variável","Limite Superior","Limite Inferior","Valor Medição","Descrição" };
	
	FuncionalidadesInvestigador funcInv;

	public JTableAlertasInvestigadorModel(FuncionalidadesInvestigador funcInv) {
		this.funcInv = funcInv;
	}

	@Override
	public int getColumnCount() {
		return COLUMN_NAMES.length;
	}

	@Override
	public int getRowCount() {
		return 100;
	}

	@Override
	public String getColumnName(int columnIndex) {
		return COLUMN_NAMES[columnIndex];
	}

	@Override
	public Object getValueAt(final int rowIndex, final int columnIndex) {
		switch (columnIndex) {
		case 0:
			return "Id";
		case 1:
			return "DataHora";
			
		case 2:
			return "Nome Variável";
		case 3:
			return "Limite Superior";
		case 4:
			return "Limite Inferior";
		case 5:
			return "Valor Medição";
		case 6:
			return "Descrição";
		default:
			return "";
		}
	}
}

