package buttonTable;



import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.table.AbstractTableModel;


import gui.FuncionalidadesInvestigador;

public class JTableAlertasInvestigadorModel extends AbstractTableModel{

	private static final long serialVersionUID = 1L;
	private static final String[] COLUMN_NAMES = new String[] { "Data e Hora do Alerta", "Nome Variável","Limite Superior","Limite Inferior","Valor Medição","Descrição" };
	
	FuncionalidadesInvestigador funcInv;

	public JTableAlertasInvestigadorModel(FuncionalidadesInvestigador funcInv) {
		this.funcInv = funcInv;
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
						return alertas.getObject("NomeVariavel").toString();
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
						return alertas.getObject("LimiteSuperior").toString();
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
						return alertas.getObject("LimiteInferior").toString();
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
						return alertas.getObject("ValorMedicao").toString();
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

