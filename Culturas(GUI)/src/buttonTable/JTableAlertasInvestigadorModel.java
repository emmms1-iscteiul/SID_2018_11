package buttonTable;



import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.table.AbstractTableModel;


import gui.FuncionalidadesInvestigador;
/**
 * Tabela Alertas Investigador
 * @author Eduardo
 *
 */
public class JTableAlertasInvestigadorModel extends AbstractTableModel{

	private static final long serialVersionUID = 1L;
	private static final String[] COLUMN_NAMES = new String[] { "Data e Hora do Alerta", "Nome Variável","Limite Superior","Limite Inferior","Valor Medição","Descrição" };
	
	FuncionalidadesInvestigador funcInv;
/**
 * Funcionalidades do Investigador
 * @param funcInv
 */
	public JTableAlertasInvestigadorModel(FuncionalidadesInvestigador funcInv) {
		this.funcInv = funcInv;
	}
/**
 * Nomes das Colunas
 */
	@Override
	public int getColumnCount() {
		return COLUMN_NAMES.length;
	}
/**
 * Número de Linhas
 */
	@Override
	public int getRowCount() {
		return 100;
	}
/**
 * Nome das Colunas
 */
	@Override
	public String getColumnName(int columnIndex) {
		return COLUMN_NAMES[columnIndex];
	}
/**
 * Valor da célula em causa
 */
	@Override
	public Object getValueAt(final int rowIndex, final int columnIndex) {
		ResultSet alertas = funcInv.filtrarAlertasCultura();
		switch (columnIndex) {
		case 0:
			try {
				while(alertas.next()) {
					if (alertas.getRow()-1 == rowIndex) {
						return alertas.getObject("DataHoraAlerta").toString();
					}				
				}
				return "";
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		case 1:
			try {
				while(alertas.next()) {
					if (alertas.getRow()-1 == rowIndex) {
						return alertas.getObject("NomeVariavelAlerta").toString();
					}				
				}
				return "";
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		case 2:
			try {
				while(alertas.next()) {
					if (alertas.getRow()-1 == rowIndex) {
						return alertas.getObject("LimiteSuperiorAlerta").toString();
					}				
				}
				return "";
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		case 3:
			try {
				while(alertas.next()) {
					if (alertas.getRow()-1 == rowIndex) {
						return alertas.getObject("LimiteInferiorAlerta").toString();
					}				
				}
				return "";
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		case 4:
			try {
				while(alertas.next()) {
					if (alertas.getRow()-1 == rowIndex) {
						return alertas.getObject("ValorMedicaoAlerta").toString();
					}				
				}
				return "";
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		case 5:
			try {
				while(alertas.next()) {
					if (alertas.getRow()-1 == rowIndex) {
						return alertas.getObject("Descricao").toString();
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

