package buttonTable;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.table.AbstractTableModel;

import gui.FuncionalidadesInvestigador;

public class JTableVariavelMedidaInvestigadorModel  extends AbstractTableModel{
		private static final long serialVersionUID = 1L;
		private static final String[] COLUMN_NAMES = new String[] { "LimiteInferior",	"LimiteSuperior","Criar", "Editar" };

		public JTableVariavelMedidaInvestigadorModel(FuncionalidadesInvestigador funcInv) {
			// TODO Auto-generated constructor stub
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
				return "LimiteInferior"	;	
			case 1:
				return "LimiteSuperior";
			case 2:
				final JButton botaoCriar = new JButton(COLUMN_NAMES[columnIndex]);
				botaoCriar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						System.out.println("Linha " + rowIndex + " criada!");

					}
				});
				return botaoCriar;
			case 3:
				final JButton botaoEditar = new JButton(COLUMN_NAMES[columnIndex]);
				botaoEditar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						System.out.println("Linha " + rowIndex + " editada!");

					}
				});
				return botaoEditar;
			default:
				return "Error";
			}
		}
}
