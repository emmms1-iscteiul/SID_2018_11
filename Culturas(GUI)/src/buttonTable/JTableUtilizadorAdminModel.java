package buttonTable;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.table.AbstractTableModel;

import gui.CriarUtilizadorGUI;
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
			return "Nome Utilizador";
		case 1:
			return "Mail";
		case 2:
			return "Password";
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
					System.out.println("Linha " + rowIndex + " editada!");

				}
			});
			return botaoEditar;
		case 6:
			final JButton botaoApagar = new JButton(COLUMN_NAMES[columnIndex]);
			botaoApagar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					System.out.println("Linha " + rowIndex + " apagada!");

				}
			});
			return botaoApagar;
		default:
			return "Error";
		}
	}
}