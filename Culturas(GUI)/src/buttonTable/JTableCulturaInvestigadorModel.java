package buttonTable;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;

import gui.EditarCulturaGUI;
import gui.FuncionalidadesInvestigador;

public class JTableCulturaInvestigadorModel  extends AbstractTableModel{
	private static final long serialVersionUID = 1L;

	private static final String[] COLUMN_NAMES = new String[] { "Nome Cultura", "Descrição Cultura","Editar", "Apagar" };
	private FuncionalidadesInvestigador funcInv;
	int id = 0;
	JFrame frame;

	public JTableCulturaInvestigadorModel(FuncionalidadesInvestigador funcInv, JFrame frame) {
		// TODO Auto-generated constructor stub
		this.funcInv = funcInv;
		this.frame = frame;
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
			//			return "Nome Cultura";
			if (rowIndex == 0) {
				return "Não existe cultura para este id";
			} else {
				try {
					ResultSet cultura = funcInv.filtrarCulturaTudo();
					while (cultura.next()) {
						id = Integer.valueOf(cultura.getObject("IdCultura").toString());
						if (cultura.getRow() == rowIndex) {
							return cultura.getObject("NomeCultura").toString();
						}
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				} catch (NumberFormatException e) {
					e.printStackTrace();
				}
			}
		case 1:
			//			return "Descrição Cultura";
			if (rowIndex == 0) {
				return "Não existe descrição de cultura para este id";
			} else {
				try {
					ResultSet culturaD = funcInv.filtrarCulturaTudo();
					while (culturaD.next()) {
						if (culturaD.getRow() == rowIndex) {
							return culturaD.getObject("DescricaoCultura").toString();
						}
					}
				} catch (NumberFormatException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
			}
		case 2:
			final JButton botaoEditar = new JButton(COLUMN_NAMES[columnIndex]);
			botaoEditar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					EditarCulturaGUI frameE = new EditarCulturaGUI("Editar Cultura", funcInv, id);
					frameE.open();
				}
			});
			return botaoEditar;
		case 3:
			final JButton botaoApagar = new JButton(COLUMN_NAMES[columnIndex]);
			botaoApagar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					funcInv.apagarCultura(id);
					JOptionPane.showMessageDialog(frame, "Apagado com sucesso");
					frame.dispose();
				}
			});
			return botaoApagar;	
		default:
			return "";
		}
	}
}
