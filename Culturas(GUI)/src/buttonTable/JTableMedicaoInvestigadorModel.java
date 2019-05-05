package buttonTable;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.table.AbstractTableModel;

import gui.FuncionalidadesInvestigador;

public class JTableMedicaoInvestigadorModel  extends AbstractTableModel{
	private static final long serialVersionUID = 1L;
	private static final String[] COLUMN_NAMES = new String[] { "Data e Hora Medição",	"Valor Medição", "Nome Cultura", "Nome Variavel"};

	FuncionalidadesInvestigador funcInv;
	int id = 0;

	public JTableMedicaoInvestigadorModel(FuncionalidadesInvestigador funcInv) {
		// TODO Auto-generated constructor stub
		this.funcInv = funcInv;
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
		ResultSet medicoes = funcInv.filtrarMedicaoTudo();
		switch (columnIndex) {
		case 0:
			//				return "Data e Hora Medição";
			if (rowIndex == 0) {
				return "Não existe data e hora para este id";
			} else {
				try {
					while (medicoes.next()) {
						id = Integer.valueOf(medicoes.getObject("IdMedicao").toString());
						if (medicoes.getRow() == rowIndex) {
							return medicoes.getObject("DataHoraMedicao").toString();
						}
					}
				} catch (NumberFormatException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		case 1:
			//				return "Valor Medição";
			if (rowIndex == 0) {
				return "Não existe valor para este id";
			} else {
				try {
					while (medicoes.next()) {
						if (medicoes.getRow() == rowIndex) {
							return medicoes.getObject("ValorMedicao").toString();
						}
					}
				} catch (NumberFormatException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		case 2:
			//				return "Nome Cultura";
			if (rowIndex == 0) {
				return "Não existe cultura para este id";
			} else {
				try {
					while (medicoes.next()) {
						if (medicoes.getRow() == rowIndex) {
							return medicoes.getObject("NomeCultura").toString();
						}
					}
				} catch (NumberFormatException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		case 3:
			//				return "Nome Variavel";
			if (rowIndex == 0) {
				return "Não existe variável para este id";
			} else {
				try {
					while (medicoes.next()) {
						if (medicoes.getRow() == rowIndex) {
							return medicoes.getObject("NomeVariavel").toString();
						}
					}
				} catch (NumberFormatException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		default:
			return "";
		}
	}
}
