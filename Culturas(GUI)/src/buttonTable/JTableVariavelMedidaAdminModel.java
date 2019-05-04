package buttonTable;


import java.sql.ResultSet;
import java.sql.SQLException;

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
//			return "LimiteInferior"	;
			ResultSet variaveisMedidas = funcAdmin.consultarVariaveisMedidas();
			try {
				while (variaveisMedidas.next()) {
					int id = Integer.valueOf(variaveisMedidas.getObject("IdVariavelMedida").toString());
					if (id == rowIndex) {
						return variaveisMedidas.getObject("LimiteInferior").toString();
					}
				}
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		case 1:
//			return "LimiteSuperior";
			ResultSet variaveisMedidasLS = funcAdmin.consultarVariaveisMedidas();
			try {
				while (variaveisMedidasLS.next()) {
					int id = Integer.valueOf(variaveisMedidasLS.getObject("IdVariavelMedida").toString());
					if (id == rowIndex) {
						return variaveisMedidasLS.getObject("LimiteSuperior").toString();
					}
				}
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		case 2:
//			return "Nome Cultura";
			ResultSet variaveisMedidasC = funcAdmin.consultarVariaveisMedidas();
			try {
				while (variaveisMedidasC.next()) {
					int id = Integer.valueOf(variaveisMedidasC.getObject("IdVariavelMedida").toString());
					if (id == rowIndex) {
						return variaveisMedidasC.getObject("NomeCultura").toString();
					}
				}
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		case 3:
//			return "Nome Variavel";
			ResultSet variaveisMedidasV = funcAdmin.consultarVariaveisMedidas();
			try {
				while (variaveisMedidasV.next()) {
					int id = Integer.valueOf(variaveisMedidasV.getObject("IdVariavelMedida").toString());
					if (id == rowIndex) {
						return variaveisMedidasV.getObject("NomeVariavel").toString();
					}
				}
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		default:
			return "Vazio";
		}
	}
}
