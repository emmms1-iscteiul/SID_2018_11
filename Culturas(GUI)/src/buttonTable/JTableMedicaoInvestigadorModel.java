package buttonTable;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.table.AbstractTableModel;

import gui.CriarMedicaoGUI;
import gui.FuncionalidadesInvestigador;

	public class JTableMedicaoInvestigadorModel  extends AbstractTableModel{
		private static final long serialVersionUID = 1L;
		private static final String[] COLUMN_NAMES = new String[] { "Data e Hora Medi��o",	"Valor Medi��o", "Criar", "Editar" };
		
		FuncionalidadesInvestigador funcInv;

		public JTableMedicaoInvestigadorModel(FuncionalidadesInvestigador funcInv) {
			// TODO Auto-generated constructor stub
			this.funcInv = funcInv;
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
				return "Data e Hora Medi��o";	
			case 1:
				return "Valor Medi��o";
			case 2:
				final JButton bot�oCriar = new JButton(COLUMN_NAMES[columnIndex]);
				bot�oCriar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						CriarMedicaoGUI frameC = new CriarMedicaoGUI("Criar Medicao", funcInv);
						frameC.open();

					}
				});
				return bot�oCriar;
			case 3:
				final JButton bot�oEditar = new JButton(COLUMN_NAMES[columnIndex]);
				bot�oEditar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						System.out.println("Linha " + rowIndex + " editada!");

					}
				});
				return bot�oEditar;
			default:
				return "Error";
			}
		}
}
