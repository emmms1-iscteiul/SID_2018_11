package buttonTable;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.table.AbstractTableModel;

import gui.CriarVariaveisGUI;

public class JTableVariavelAdminModel extends AbstractTableModel {
	private static final long serialVersionUID = 1L;
	private static final String[] COLUMN_NAMES = new String[] { "Nome Vari�vel","Criar","Editar","Apagar" };


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
			return "Nome Vari�vel";
		case 1: 
			final JButton botaoCriar = new JButton(COLUMN_NAMES[columnIndex]);
			botaoCriar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					CriarVariaveisGUI frame = new CriarVariaveisGUI("Inserir Variavel");
					frame.open();
						
				}
			});
			return botaoCriar;	
		case 2: 
			final JButton botaoEditar = new JButton(COLUMN_NAMES[columnIndex]);
			botaoEditar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					System.out.println("Linha " + rowIndex+" editada!");
						
				}
			});
			return botaoEditar;
		case 3:
			final JButton botaoApagar = new JButton(COLUMN_NAMES[columnIndex]);
			botaoApagar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					System.out.println("Linha " + rowIndex+" apagada!");
						
				}
			});
			return botaoApagar;
		default:
			return "Error";
		}
	}
}