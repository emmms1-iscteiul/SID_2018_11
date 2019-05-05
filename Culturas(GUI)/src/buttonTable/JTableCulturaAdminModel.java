package buttonTable;


import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.table.AbstractTableModel;

import gui.FuncionalidadesAdmin;

public class JTableCulturaAdminModel extends AbstractTableModel {
	private static final long serialVersionUID = 1L;
	private static final String[] COLUMN_NAMES = new String[] { "Nome Cultura", "Descrição Cultura" };
	
	FuncionalidadesAdmin funcAdmin;

	public JTableCulturaAdminModel(FuncionalidadesAdmin funcAdmin) {
		// TODO Auto-generated constructor stub
		this.funcAdmin = funcAdmin;
	}

	@Override
	public int getColumnCount() {
		return COLUMN_NAMES.length;
	}

	@Override
	public int getRowCount() {
		return 20;
	}

	@Override
	public String getColumnName(int columnIndex) {
		return COLUMN_NAMES[columnIndex];
	}

	@Override
	public Object getValueAt(final int rowIndex, final int columnIndex) {
		ResultSet cultura = funcAdmin.consultarCulturas();
		switch (columnIndex) {
		case 0:
			//return "Nome Cultura";
			if (rowIndex == 0) {
				return "Não existe cultura para este id";
			} else {
				try {
					while(cultura.next()) {
						if (cultura.getRow() == rowIndex) {
							return cultura.getObject("NomeCultura").toString();
						}				
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		case 1:
			//return "Descrição Cultura";
			if (rowIndex == 0) {
				return "Não existe descrição para este id";
			} else {
				try {
					while(cultura.next()) {
						if (cultura.getRow() == rowIndex) {
							return cultura.getObject("DescricaoCultura").toString();
						}
					}
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
