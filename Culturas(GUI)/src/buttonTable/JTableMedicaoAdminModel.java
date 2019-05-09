package buttonTable;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.table.AbstractTableModel;

import gui.FuncionalidadesAdmin;

public class JTableMedicaoAdminModel  extends AbstractTableModel {

	private static final long serialVersionUID = 1L;
	private static final String[] COLUMN_NAMES = new String[] { "Data e Hora Medição",	"Valor Medição", "Nome Cultura", "Nome Variavel"};
	
	FuncionalidadesAdmin funcAdmin;

	public JTableMedicaoAdminModel(FuncionalidadesAdmin funcAdmin) {
		this.funcAdmin=funcAdmin;
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
		ResultSet medicoes = funcAdmin.consultarMedicoes();
		switch (columnIndex) {
		case 0:
			try {
				while (medicoes.next()) {
					if (medicoes.getRow()-1 == rowIndex) {
						return medicoes.getObject("DataHoraMedicao").toString();
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
//			return "Valor Medição";
			try {
				while (medicoes.next()) {
					if (medicoes.getRow()-1 == rowIndex) {
						return medicoes.getObject("ValorMedicao").toString();
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
//			return "Nome Cultura";
			try {
				while (medicoes.next()) {
					if (medicoes.getRow()-1== rowIndex) {
						return medicoes.getObject("NomeCultura").toString();
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
//			return "Nome Variavel";
			try {
				while (medicoes.next()) {
					if (medicoes.getRow()-1 == rowIndex) {
						return medicoes.getObject("NomeVariavel").toString();
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