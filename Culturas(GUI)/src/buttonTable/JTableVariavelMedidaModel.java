package buttonTable;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.table.AbstractTableModel;

public class JTableVariavelMedidaModel  extends AbstractTableModel{
		private static final long serialVersionUID = 1L;
		private static final String[] COLUMN_NAMES = new String[] { "LimiteInferior",	"LimiteSuperior", "Editar" };

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
				return "LimiteInferior"	;	
			case 1:
				return "LimiteSuperior";
			case 2:
				final JButton button = new JButton(COLUMN_NAMES[columnIndex]);
				button.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						System.out.println("Linha " + rowIndex + " editada!");

					}
				});
				return button;
			default:
				return "Error";
			}
		}
}
