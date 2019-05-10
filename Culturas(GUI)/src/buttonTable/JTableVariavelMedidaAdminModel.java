package buttonTable;


import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.table.AbstractTableModel;

import gui.FuncionalidadesAdmin;

public class JTableVariavelMedidaAdminModel extends AbstractTableModel{
	private static final long serialVersionUID = 1L;
	private static final String[] COLUMN_NAMES = new String[] { "LimiteInferior",	"LimiteSuperior", "LimiteRegularidadeAlertasInferior", "LimiteRegularidadeAlertasSuperior", "Nome Cultura", "Nome Variavel" };
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
		return 100;
	}

	@Override
	public String getColumnName(int columnIndex) {
		return COLUMN_NAMES[columnIndex];
	}

	@Override
	public Object getValueAt(final int rowIndex, final int columnIndex) {
		ResultSet variaveisMedidas = funcAdmin.consultarVariaveisMedidas();
		switch (columnIndex) {
		case 0:
			try {
				while (variaveisMedidas.next()) {
					if (variaveisMedidas.getRow()-1 == rowIndex) {
						return variaveisMedidas.getObject("LimiteInferior").toString();
					}
				}
				return "";
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		case 1:
			try {
				while (variaveisMedidas.next()) {
					if (variaveisMedidas.getRow()-1 == rowIndex) {
						return variaveisMedidas.getObject("LimiteSuperior").toString();
					}
				}
				return "";
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		case 2:
			try {
				while (variaveisMedidas.next()) {
					if (variaveisMedidas.getRow()-1 == rowIndex) {
						return variaveisMedidas.getObject("LimiteRegularidadeAlertasInferior").toString();
					}
				}
				return "";
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		case 3:
			try {
				while (variaveisMedidas.next()) {
					if (variaveisMedidas.getRow()-1 == rowIndex) {
						return variaveisMedidas.getObject("LimiteRegularidadeAlertasSuperior").toString();
					}
				}
				return "";
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		case 4:
			try {
				while (variaveisMedidas.next()) {
					if (variaveisMedidas.getRow()-1 == rowIndex) {
						return variaveisMedidas.getObject("NomeCultura").toString();
					}
				}
				return "";
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		case 5:
			try {
				while (variaveisMedidas.next()) {
					if (variaveisMedidas.getRow()-1 == rowIndex) {
						return variaveisMedidas.getObject("NomeVariavel").toString();
					}
				}
				return "";
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		default:
			return "";
		}
	}
}
