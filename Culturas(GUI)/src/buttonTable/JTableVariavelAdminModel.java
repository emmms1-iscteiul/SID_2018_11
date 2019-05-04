package buttonTable;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.table.AbstractTableModel;

import gui.EditarVariaveisGUI;
import gui.FuncionalidadesAdmin;

public class JTableVariavelAdminModel extends AbstractTableModel {
	private static final long serialVersionUID = 1L;
	private static final String[] COLUMN_NAMES = new String[] { "Nome Variável","Editar","Apagar" };

	FuncionalidadesAdmin funcAdmin;
	
	public JTableVariavelAdminModel(FuncionalidadesAdmin funcAdmin) {
		// TODO Auto-generated constructor stub
		this.funcAdmin = funcAdmin;
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
//			return "Nome Variável";
			ResultSet variaveis = funcAdmin.consultarVariaveis();
			try {
				while (variaveis.next()) {
					int id = Integer.valueOf(variaveis.getObject("IdVariavel").toString());
					if (id == rowIndex) {
						return variaveis.getObject("NomeVariavel").toString();
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
			final JButton botaoEditar = new JButton(COLUMN_NAMES[columnIndex]);
			botaoEditar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					EditarVariaveisGUI frameE = new EditarVariaveisGUI("Editar Variavel", funcAdmin, rowIndex);
					frameE.open();
				}
			});
			return botaoEditar;
		case 2:
			final JButton botaoApagar = new JButton(COLUMN_NAMES[columnIndex]);
			botaoApagar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					funcAdmin.apagarVariavel(rowIndex);
				}
			});
			return botaoApagar;
		default:
			return "Error";
		}
	}
}