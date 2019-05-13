package buttonTable;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.table.AbstractTableModel;

import gui.FuncionalidadesAdmin;
/**
 * Tabela Medição Admin
 * @author Eduardo
 *
 */
public class JTableMedicaoAdminModel  extends AbstractTableModel {

	private static final long serialVersionUID = 1L;
	private static final String[] COLUMN_NAMES = new String[] { "Data e Hora Medição",	"Valor Medição", "Nome Cultura", "Nome Variavel"};
	
	FuncionalidadesAdmin funcAdmin;
/**
 * Funcionalidades do Admin
 * @param funcAdmin
 */
	public JTableMedicaoAdminModel(FuncionalidadesAdmin funcAdmin) {
		this.funcAdmin=funcAdmin;
	}
/**
 * Número de colunas
 */
	@Override
	public int getColumnCount() {
		return COLUMN_NAMES.length;
	}
/**
 * Número de linhas
 */
	@Override
	public int getRowCount() {
		return 100;
	}
/**
 * Nome das colunas
 */
	@Override
	public String getColumnName(int columnIndex) {
		return COLUMN_NAMES[columnIndex];
	}
/**
 * Valor da célula
 */
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