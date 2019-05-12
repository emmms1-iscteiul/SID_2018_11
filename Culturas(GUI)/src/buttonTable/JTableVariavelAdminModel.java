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


import gui.EditarVariaveisGUI;
import gui.FuncionalidadesAdmin;
/**
 * Tabea Variavel Admin
 * @author Eduardo
 *
 */
public class JTableVariavelAdminModel extends AbstractTableModel {
	private static final long serialVersionUID = 1L;
	private JTableVariavelAdminModel variavelModel=this;
	private static final String[] COLUMN_NAMES = new String[] { "Nome Vari�vel", "Editar", "Apagar" };
	JFrame frame;
	FuncionalidadesAdmin funcAdmin;
	List<Integer> idsVariavel = new ArrayList<Integer>();
/**
 * Frame funcionalidades admin
 * @param funcAdmin
 * @param frame
 */
	public JTableVariavelAdminModel(FuncionalidadesAdmin funcAdmin, JFrame frame) {
		// TODO Auto-generated constructor stub
		this.funcAdmin = funcAdmin;
		this.frame = frame;
	}
/**
 * N�mero de colunas
 */
	@Override
	public int getColumnCount() {
		return COLUMN_NAMES.length;
	}
/**
 * N�mero de linhas
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
 * Valor da c�lula
 */
	@Override
	public Object getValueAt(final int rowIndex, final int columnIndex) {
		ResultSet variaveis = funcAdmin.consultarVariaveis();
		switch (columnIndex) {
		case 0:
			try {
				while (variaveis.next()) {
					idsVariavel.add(rowIndex, Integer.valueOf(variaveis.getObject("IdVariavel").toString()));
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
		case 1:
			final JButton botaoEditar = new JButton(COLUMN_NAMES[columnIndex]);
			botaoEditar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					if (!getValueAt(rowIndex, 0).toString().equals("")) {
						int idAEditar = idsVariavel.get(rowIndex);
						EditarVariaveisGUI frameE = new EditarVariaveisGUI("Editar Variavel", funcAdmin, idAEditar,variavelModel);
						frameE.open();
					} else {
						JOptionPane.showMessageDialog(null, "Este campo est� vazio logo n�o pode ser editado!");
					}
				}
			});
			return botaoEditar;
		case 2:
			final JButton botaoApagar = new JButton(COLUMN_NAMES[columnIndex]);
			botaoApagar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					if (!getValueAt(rowIndex, 0).toString().equals("")) {
						int idAApagar = idsVariavel.get(rowIndex);
						funcAdmin.apagarVariavel(idAApagar);
						idsVariavel.remove(rowIndex);
						JOptionPane.showMessageDialog(frame, "Apagado com sucesso");
						variavelModel.fireTableDataChanged();
					} else {
						JOptionPane.showMessageDialog(null, "Este campo est� vazio logo n�o pode ser apagado!");
					}
				}
			});
			return botaoApagar;
		default:
			return "";
		}
	}
}