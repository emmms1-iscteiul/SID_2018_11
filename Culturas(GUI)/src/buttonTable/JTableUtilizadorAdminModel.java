package buttonTable;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.table.AbstractTableModel;

import gui.CriarUtilizadorGUI;
import gui.EditarUtilizadorGUI;
import gui.FuncionalidadesAdmin;

public class JTableUtilizadorAdminModel extends AbstractTableModel {
	private static final long serialVersionUID = 1L;
	private static final String[] COLUMN_NAMES = new String[] { "Nome Utilizador", "Mail",
			"Password", "Criar", "Editar", "Apagar" };
	FuncionalidadesAdmin funcAdmin;

	public JTableUtilizadorAdminModel(FuncionalidadesAdmin funcAdmin) {
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
//			return "Nome Utilizador";
			ResultSet utilizadores = funcAdmin.consultarUtilizadores();
			try {
				while (utilizadores.next()) {
					int id = Integer.valueOf(utilizadores.getObject("IDUtilizador").toString());
					if (id == rowIndex) {
						return utilizadores.getObject("NomeUtilizador").toString();
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
//			return "Mail";
			ResultSet utilizadoresM = funcAdmin.consultarUtilizadores();
			try {
				while (utilizadoresM.next()) {
					int id = Integer.valueOf(utilizadoresM.getObject("IDUtilizador").toString());
					if (id == rowIndex) {
						return utilizadoresM.getObject("Email").toString();
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
//			return "Password";
			ResultSet utilizadoresP = funcAdmin.consultarUtilizadores();
			try {
				while (utilizadoresP.next()) {
					int id = Integer.valueOf(utilizadoresP.getObject("IDUtilizador").toString());
					if (id == rowIndex) {
						return utilizadoresP.getObject("Passwor").toString();
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
			final JButton botaoCriar = new JButton(COLUMN_NAMES[columnIndex]);
			botaoCriar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					CriarUtilizadorGUI frameC = new CriarUtilizadorGUI("Criar Utilizador", funcAdmin);
					frameC.open();
				}
			});
			return botaoCriar;
		case 4:
			final JButton botaoEditar = new JButton(COLUMN_NAMES[columnIndex]);
			botaoEditar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					EditarUtilizadorGUI frameE = new EditarUtilizadorGUI("Editar Utilizador", funcAdmin, rowIndex);
					frameE.open();
				}
			});
			return botaoEditar;
		case 6:
			final JButton botaoApagar = new JButton(COLUMN_NAMES[columnIndex]);
			botaoApagar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					funcAdmin.apagarUtilizador(rowIndex);
				}
			});
			return botaoApagar;
		default:
			return "Error";
		}
	}
}