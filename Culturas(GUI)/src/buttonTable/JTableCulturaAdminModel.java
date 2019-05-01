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
					int idC = Integer.valueOf(cultura.getObject("IDCultura").toString());
					if (idC == rowIndex) {
						return cultura.getObject("NomeCultura").toString();
					}
					
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
					int idC = Integer.valueOf(culturaD.getObject("IDCultura").toString());
					if (idC == rowIndex) {
						return culturaD.getObject("DescricaoCultura").toString();
					}
					
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
					int idC = Integer.valueOf(culturaU.getObject("IDCultura").toString());
					if (idC == rowIndex) {
						return culturaU.getObject("NomeUtilizador").toString();
					}
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
