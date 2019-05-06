package buttonTable;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;

import gui.EditarUtilizadorGUI;
import gui.FuncionalidadesAdmin;

public class JTableUtilizadorAdminModel extends AbstractTableModel {
	private static final long serialVersionUID = 1L;

	private static final String[] COLUMN_NAMES = new String[] { "Nome Utilizador", "Mail",
			"Password", "Editar", "Apagar" };
  
	FuncionalidadesAdmin funcAdmin;
	int id = 0;
	JFrame frame;

	public JTableUtilizadorAdminModel(FuncionalidadesAdmin funcAdmin, JFrame frame) {
		// TODO Auto-generated constructor stub
		this.funcAdmin=funcAdmin;
		this.frame=frame;
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
		ResultSet utilizadores = funcAdmin.consultarUtilizadores();
		switch (columnIndex) {
		case 0:
			//			return "Nome Utilizador";
			if (rowIndex == 0) {
				return "Não existe utilizador para este id";
			}
			else {
				try {
					while (utilizadores.next()) {
						id = Integer.valueOf(utilizadores.getObject("IdUtilizador").toString());
						if (utilizadores.getRow() == rowIndex) {
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
			}
		case 1:
			//			return "Mail";
			if (rowIndex == 0) {
				return "Não existe mail para este id";
			} else {
				try {
					while (utilizadores.next()) {
						if (utilizadores.getRow() == rowIndex) {
							return utilizadores.getObject("Email").toString();
						} else {
							return "";
						}
					}
				} catch (NumberFormatException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		case 2:
			//			return "Password";
			if (rowIndex == 0) {
				return "Não existe password para este id";
			} else {
				try {
					while (utilizadores.next()) {
						if (utilizadores.getRow() == rowIndex) {
							return utilizadores.getObject("Passwor").toString();
						} else {
							return "";
						}
					}
				} catch (NumberFormatException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		case 3:
			final JButton botaoEditar = new JButton(COLUMN_NAMES[columnIndex]);
			botaoEditar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					EditarUtilizadorGUI frameE = new EditarUtilizadorGUI("Editar Utilizador", funcAdmin, rowIndex);
					frameE.open();
				}
			});
			return botaoEditar;
		case 4:
			final JButton botaoApagar = new JButton(COLUMN_NAMES[columnIndex]);
			botaoApagar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					funcAdmin.apagarUtilizador(rowIndex);
					JOptionPane.showMessageDialog(frame, "Apagado com sucesso");
					frame.dispose();
				}
			});
			return botaoApagar;
		default:
			return "";
		}
	}
}