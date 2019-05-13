package buttonTable;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;

import gui.EditarUtilizadorGUI;
import gui.FuncionalidadesAdmin;
/**
 * Tabela Utilizador Admin
 * @author Eduardo
 *
 */
public class JTableUtilizadorAdminModel extends AbstractTableModel {
	private static final long serialVersionUID = 1L;
	private JTableUtilizadorAdminModel utilizadorModel=this;

	private static final String[] COLUMN_NAMES = new String[] { "Nome Utilizador", "Mail",
			"Password", "Editar", "Apagar" };
  
	FuncionalidadesAdmin funcAdmin;
	List<Integer> idsUtilizador = new ArrayList<Integer>();
	JFrame frame;
/**
 * Frame Funcionalidades Admin
 * @param funcAdmin
 * @param frame
 */
	public JTableUtilizadorAdminModel(FuncionalidadesAdmin funcAdmin, JFrame frame) {
		// TODO Auto-generated constructor stub
		this.funcAdmin=funcAdmin;
		this.frame=frame;
	}

/**
 * N+umero de colunas
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
		ResultSet utilizadores = funcAdmin.consultarUtilizadores();
		switch (columnIndex) {
		case 0:
				try {
					while (utilizadores.next()) {
						idsUtilizador.add(rowIndex, Integer.valueOf(utilizadores.getObject("IdUtilizador").toString()));
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
						int idAEditar = idsUtilizador.get(rowIndex);
						EditarUtilizadorGUI frameE = new EditarUtilizadorGUI("Editar Utilizador", funcAdmin, idAEditar,utilizadorModel);
						frameE.open();	
					}
					else {
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
						int idAApagar = idsUtilizador.get(rowIndex);
						funcAdmin.apagarUtilizador(idAApagar);
						idsUtilizador.remove(rowIndex);
						JOptionPane.showMessageDialog(frame, "Apagado com sucesso");
						utilizadorModel.fireTableDataChanged();	
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