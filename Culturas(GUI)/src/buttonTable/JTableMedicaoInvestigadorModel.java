package buttonTable;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.table.AbstractTableModel;

import gui.CriarMedicaoGUI;
import gui.FuncionalidadesInvestigador;

	public class JTableMedicaoInvestigadorModel  extends AbstractTableModel{
		private static final long serialVersionUID = 1L;
		private static final String[] COLUMN_NAMES = new String[] { "Data e Hora Medição",	"Valor Medição", "Nome Cultura", "Nome Variavel","Criar"};
		
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
				return "Data e Hora Medição";	
			case 1:
				return "Valor Medição";
			case 2:
				return "Nome Cultura";
			case 3:
				return "Nome Variavel";
			case 4:
				final JButton botãoCriar = new JButton(COLUMN_NAMES[columnIndex]);
				botãoCriar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						CriarMedicaoGUI frameC = new CriarMedicaoGUI("Criar Medicao", funcInv);
						frameC.open();
					}
				});
				return botãoCriar;
			default:
				return "Error";
			}
		}
}
