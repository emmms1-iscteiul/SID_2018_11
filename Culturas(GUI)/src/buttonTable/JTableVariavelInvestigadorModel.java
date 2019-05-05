package buttonTable;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.table.AbstractTableModel;

import gui.FuncionalidadesInvestigador;

public class JTableVariavelInvestigadorModel  extends AbstractTableModel {

	private static final long serialVersionUID = 1L;
	private static final String[] COLUMN_NAMES = new String[] { "Nome Variável" };
	FuncionalidadesInvestigador funcInv;


	public JTableVariavelInvestigadorModel(FuncionalidadesInvestigador funcInv) {
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
		switch (columnIndex) {
		case 0:
			//			return "Nome Variável";
			if (rowIndex == 0) {
				return "Não existe variável para este id";
			} else {
				ResultSet variaveis = funcInv.consultarVariaveisInvestigador();
				try {
					while (variaveis.next()) {
						int id = Integer.valueOf(variaveis.getObject("IdVariavel").toString());
						if (id == rowIndex) {
							return variaveis.getObject("NomeVariavel").toString();
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
