package buttonTable;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JOptionPane;
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
		return 20;
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
				try {
					while (variaveisMedidas.next()) {
						if (variaveisMedidas.getRow()-1 == rowIndex) {
							return variaveisMedidas.getObject("LimiteInferior").toString();
						}
					}
					return "";
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		case 1:
			//				return "LimiteSuperior";
				try {
					while (variaveisMedidas.next()) {
						if (variaveisMedidas.getRow()-1 == rowIndex) {
							return variaveisMedidas.getObject("LimiteSuperior").toString();
						}
					}
					return "";
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		case 2:
			//				return "Nome Cultura";
				try {
					while (variaveisMedidas.next()) {
						if (variaveisMedidas.getRow()-1 == rowIndex) {
							return variaveisMedidas.getObject("NomeCultura").toString();
						}
					}
					return "";
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		case 3:
			//				return "Nome Variavel";
				try {
					while (variaveisMedidas.next()) {
						if (variaveisMedidas.getRow()-1 == rowIndex) {
							return variaveisMedidas.getObject("NomeCultura").toString();
						}
					}
					return "";
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		case 4:
			final JButton botaoEditar = new JButton(COLUMN_NAMES[columnIndex]);
			botaoEditar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					if(!getValueAt(rowIndex, 0).toString().equals(""))	{
						EditarVariaveisMedidasGUI frameE = new EditarVariaveisMedidasGUI("Editar Variavel Medida", funcInv, rowIndex);
						frameE.open();
					}
					else	{
						JOptionPane.showMessageDialog(null, "Este campo está vazio logo não pode ser editado!");
					}
				}
			});
			return botaoEditar;
		default:
			return "";
		}
	}
}
