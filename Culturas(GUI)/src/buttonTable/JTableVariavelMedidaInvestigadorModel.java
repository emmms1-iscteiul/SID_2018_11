package buttonTable;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.table.AbstractTableModel;

import gui.EditarVariaveisMedidasGUI;
import gui.FuncionalidadesInvestigador;

public class JTableVariavelMedidaInvestigadorModel  extends AbstractTableModel{
	private static final long serialVersionUID = 1L;
	private static final String[] COLUMN_NAMES = new String[] { "LimiteInferior",	"LimiteSuperior", "Nome Cultura", "Nome Variavel","Editar" };
	FuncionalidadesInvestigador funcInv;
	int id = 0;

	public JTableVariavelMedidaInvestigadorModel(FuncionalidadesInvestigador funcInv) {
		// TODO Auto-generated constructor stub
		this.funcInv = funcInv;
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
		ResultSet variaveisMedidas = funcInv.filtrarVariaveisMedidasTudo();
		switch (columnIndex) {
		case 0:
			//				return "LimiteInferior"	;
			if (rowIndex == 0) {
				return "Não existe limite inferior para este id";
			} else {
				try {
					while (variaveisMedidas.next()) {
						id = Integer.valueOf(variaveisMedidas.getObject("IdVariavelMedida").toString());
						if (variaveisMedidas.getRow() == rowIndex) {
							return variaveisMedidas.getObject("LimiteInferior").toString();
						}
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		case 1:
			//				return "LimiteSuperior";
			if (rowIndex == 0) {
				return "Não existe limite superior para este id";
			} else {
				try {
					while (variaveisMedidas.next()) {
						if (variaveisMedidas.getRow() == rowIndex) {
							return variaveisMedidas.getObject("LimiteSuperior").toString();
						}
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		case 2:
			//				return "Nome Cultura";
			if (rowIndex == 0) {
				return "Não existe cultura para este id";
			} else {
				try {
					while (variaveisMedidas.next()) {
						if (variaveisMedidas.getRow() == rowIndex) {
							return variaveisMedidas.getObject("NomeCultura").toString();
						}
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		case 3:
			//				return "Nome Variavel";
			if (rowIndex == 0) {
				return "Não existe variável para este id";
			} else {
				try {
					while (variaveisMedidas.next()) {
						if (variaveisMedidas.getRow() == rowIndex) {
							return variaveisMedidas.getObject("NomeCultura").toString();
						}
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		case 4:
			final JButton botaoEditar = new JButton(COLUMN_NAMES[columnIndex]);
			botaoEditar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					EditarVariaveisMedidasGUI frameE = new EditarVariaveisMedidasGUI("Editar Variavel Medida", funcInv, rowIndex);
					frameE.open();
				}
			});
			return botaoEditar;
		default:
			return "";
		}
	}
}
