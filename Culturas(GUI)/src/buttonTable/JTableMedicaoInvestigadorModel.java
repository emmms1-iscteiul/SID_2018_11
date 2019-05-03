package buttonTable;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.table.AbstractTableModel;

	public class JTableMedicaoInvestigadorModel  extends AbstractTableModel{
		private static final long serialVersionUID = 1L;
		private static final String[] COLUMN_NAMES = new String[] { "Data e Hora Medição",	"Valor Medição", "Editar" };

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
				return "Data e Hora Medição";	
			case 1:
				return "Valor Medição";
			case 2:
				final JButton botãoApagar = new JButton(COLUMN_NAMES[columnIndex]);
				botãoApagar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						System.out.println("Linha " + rowIndex + " editada!");

					}
				});
				return botãoApagar;
			default:
				return "Error";
			}
		}
}
