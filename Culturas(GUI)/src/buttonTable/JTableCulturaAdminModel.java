package buttonTable;


import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.table.AbstractTableModel;

import gui.FuncionalidadesAdmin;
/**
 * Tabela Cultura Admin
 * @author Eduardo
 *
 */
public class JTableCulturaAdminModel extends AbstractTableModel {
	private static final long serialVersionUID = 1L;
	private static final String[] COLUMN_NAMES = new String[] { "Nome Cultura", "Descrição Cultura", "Nome Utilizador Responsável" };
	
	FuncionalidadesAdmin funcAdmin;
/**
 * Funcionalidades Admin
 * @param funcAdmin
 */
	public JTableCulturaAdminModel(FuncionalidadesAdmin funcAdmin) {
		// TODO Auto-generated constructor stub
		this.funcAdmin = funcAdmin;
	}
/**
 * Número de Colunas 
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
 * Nome da coluna
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
		ResultSet cultura = funcAdmin.consultarCulturas();
		switch (columnIndex) {
		case 0:
				try {
					while(cultura.next()) {
						if (cultura.getRow()-1 == rowIndex) {
							return cultura.getObject("NomeCultura").toString();
						}				
					}
					return "";
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		case 1:
			//return "Descrição Cultura";
				try {
					while(cultura.next()) {
						if (cultura.getRow()-1 == rowIndex) {
							return cultura.getObject("DescricaoCultura").toString();
						}
					}
					return "";
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
		case 2:
				try {
					while(cultura.next()) {
						if (cultura.getRow()-1 == rowIndex) {
							return cultura.getObject("NomeUtilizador").toString();
						}
					}
					return "";
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		default:
			return "";
		}
	}
}
