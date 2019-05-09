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
		return 20;
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
				try {
					while (utilizadores.next()) {
						id = Integer.valueOf(utilizadores.getObject("IdUtilizador").toString());
						if (utilizadores.getRow()-1 == rowIndex) {
							return utilizadores.getObject("NomeUtilizador").toString();
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
			//			return "Mail";
				try {
					while (utilizadores.next()) {
						if (utilizadores.getRow()-1 == rowIndex) {
							return utilizadores.getObject("Email").toString();
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
			//			return "Password";
				try {
					while (utilizadores.next()) {
						if (utilizadores.getRow()-1 == rowIndex) {
							return utilizadores.getObject("Passwor").toString();
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
			final JButton botaoEditar = new JButton(COLUMN_NAMES[columnIndex]);
			botaoEditar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					if(!getValueAt(rowIndex, 0).toString().equals(""))	{
						EditarUtilizadorGUI frameE = new EditarUtilizadorGUI("Editar Utilizador", funcAdmin, rowIndex);
						frameE.open();	
					}
					else	{
						JOptionPane.showMessageDialog(null, "Este campo está vazio logo não pode ser editado!");
					}
					
				}
			});
			return botaoEditar;
		case 4:
			final JButton botaoApagar = new JButton(COLUMN_NAMES[columnIndex]);
			botaoApagar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					if(!getValueAt(rowIndex, 0).toString().equals(""))	{
						funcAdmin.apagarUtilizador(rowIndex);
						JOptionPane.showMessageDialog(frame, "Apagado com sucesso");
						frame.dispose();	
					}
					else	{
						JOptionPane.showMessageDialog(null, "Este campo está vazio logo não pode ser apagado!");
					}
				}
			});
			return botaoApagar;
		default:
			return "";
		}
	}
}