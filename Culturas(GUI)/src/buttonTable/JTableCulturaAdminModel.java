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
		switch (columnIndex) {
		case 0:
			//return "Nome Cultura";
			if (rowIndex == 0) {
				return "Não existe cultura para este id";
			} else {
				ResultSet cultura = funcAdmin.consultarCulturas();
				try {
					while(cultura.next()) {
						int idC = Integer.valueOf(cultura.getObject("IdCultura").toString());
						if (idC == rowIndex) {
							return cultura.getObject("NomeCultura").toString();
						} else {
							return "";
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
				ResultSet culturaD = funcAdmin.consultarCulturas();
				try {
					while(culturaD.next()) {
						int idC = Integer.valueOf(culturaD.getObject("IdCultura").toString());
						if (idC == rowIndex) {
							return culturaD.getObject("DescricaoCultura").toString();
						} else {
							return "";
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
