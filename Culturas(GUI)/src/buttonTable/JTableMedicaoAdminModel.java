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
		// TODO Auto-generated constructor stub
		this.funcAdmin=funcAdmin;
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
//			return "Data e Hora Medição";
			ResultSet medicoes = funcAdmin.consultarMedicoes();
			try {
				while (medicoes.next()) {
					int id = Integer.valueOf(medicoes.getObject("IDMedicao").toString());
					if (id == rowIndex) {
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
		case 1:
//			return "Valor Medição";
			ResultSet medicoesVM = funcAdmin.consultarMedicoes();
			try {
				while (medicoesVM.next()) {
					int id = Integer.valueOf(medicoesVM.getObject("IDMedicao").toString());
					if (id == rowIndex) {
						return medicoesVM.getObject("ValorMedicao").toString();
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
			ResultSet medicoesC = funcAdmin.consultarMedicoes();
			try {
				while (medicoesC.next()) {
					int id = Integer.valueOf(medicoesC.getObject("IDMedicao").toString());
					if (id == rowIndex) {
						return medicoesC.getObject("NomeCultura").toString();
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
			ResultSet medicoesV = funcAdmin.consultarMedicoes();
			try {
				while (medicoesV.next()) {
					int id = Integer.valueOf(medicoesV.getObject("IDMedicao").toString());
					if (id == rowIndex) {
						return medicoesV.getObject("NomeVariavel").toString();
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
			return "Error";
		}
	}
}