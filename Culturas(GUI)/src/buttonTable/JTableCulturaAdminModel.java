package buttonTable;


import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.table.AbstractTableModel;

import gui.FuncionalidadesAdmin;

public class JTableCulturaAdminModel extends AbstractTableModel {
	private static final long serialVersionUID = 1L;
	private static final String[] COLUMN_NAMES = new String[] { "Nome Cultura", "Descrição Cultura", "Nome Utilizador Responsável" };
	
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
			//return "Nome Cultura";
			ResultSet cultura = funcAdmin.consultarCulturas();
			try {
				while(cultura.next()) {
					return cultura.getObject(rowIndex);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		case 1:
			//return "Descrição Cultura";
			ResultSet culturaD = funcAdmin.consultarCulturas();
			try {
				while(culturaD.next()) {
					return culturaD.getObject(rowIndex);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		case 2:
			//return "Tipo Cultura";
			ResultSet culturaU = funcAdmin.consultarCulturas();
			try {
				while(culturaU.next()) {
					return culturaU.getObject(rowIndex);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		default:
			return "Error";
		}
	}
}
