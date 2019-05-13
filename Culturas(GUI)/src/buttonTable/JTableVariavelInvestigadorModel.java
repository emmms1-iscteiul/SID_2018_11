package buttonTable;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.table.AbstractTableModel;

import gui.FuncionalidadesInvestigador;
/**
 * Tabela Variavel Investigador
 * @author Eduardo
 *
 */
public class JTableVariavelInvestigadorModel  extends AbstractTableModel {

	private static final long serialVersionUID = 1L;
	private static final String[] COLUMN_NAMES = new String[] { "Nome Variável" };
	FuncionalidadesInvestigador funcInv;

/**
 * Funcionalidades investigador
 * @param funcInv
 */
	public JTableVariavelInvestigadorModel(FuncionalidadesInvestigador funcInv) {
		// TODO Auto-generated constructor stub
		this.funcInv = funcInv;
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
		ResultSet variaveis = funcInv.consultarVariaveisInvestigador();
		switch (columnIndex) {
		case 0:
				try {
					while (variaveis.next()) {
						if (variaveis.getRow()-1 == rowIndex) {
							return variaveis.getObject("NomeVariavel").toString();
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
