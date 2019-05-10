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

import gui.EditarCulturaGUI;
import gui.FuncionalidadesInvestigador;

public class JTableCulturaInvestigadorModel  extends AbstractTableModel{
	private static final long serialVersionUID = 1L;

	private static final String[] COLUMN_NAMES = new String[] { "Nome Cultura", "Descrição Cultura","Editar", "Apagar" };
	private FuncionalidadesInvestigador funcInv;
	List<Integer> idsCultura = new ArrayList<Integer>();
	JFrame frame;
	private JTableCulturaInvestigadorModel culturaModel=this;

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
		return 100;
	}

	@Override
	public String getColumnName(int columnIndex) {
		return COLUMN_NAMES[columnIndex];
	}

	@Override
	public Object getValueAt(final int rowIndex, final int columnIndex) {
		switch (columnIndex) {
		case 0:
				try {
					ResultSet cultura = funcInv.filtrarCulturaTudo();
					while (cultura.next()) {
						idsCultura.add(rowIndex, Integer.valueOf(cultura.getObject("IdCultura").toString()));
						if (cultura.getRow()-1 == rowIndex) {
							return cultura.getObject("NomeCultura").toString();
						}
					}
					return "";
				} catch (SQLException e1) {
					e1.printStackTrace();
				} catch (NumberFormatException e) {
					e.printStackTrace();
				}
		case 1:
				try {
					ResultSet culturaD = funcInv.filtrarCulturaTudo();
					while (culturaD.next()) {
						if (culturaD.getRow()-1 == rowIndex) {
							return culturaD.getObject("DescricaoCultura").toString();
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
			final JButton botaoEditar = new JButton(COLUMN_NAMES[columnIndex]);
			botaoEditar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					if(!getValueAt(rowIndex, 0).toString().equals(""))	{
						int idAEditar = idsCultura.get(rowIndex);
						EditarCulturaGUI frameE = new EditarCulturaGUI("Editar Cultura", funcInv, idAEditar,culturaModel);
						frameE.open();
					}
					else	{
						JOptionPane.showMessageDialog(null, "Este campo está vazio logo não pode ser editado!");
					}
				}
			});
			return botaoEditar;
		case 3:
			final JButton botaoApagar = new JButton(COLUMN_NAMES[columnIndex]);
			botaoApagar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					if(!getValueAt(rowIndex, 0).toString().equals("")) {
						int idAApagar = idsCultura.get(rowIndex);
						funcInv.apagarCultura(idAApagar);
						idsCultura.remove(rowIndex);
						JOptionPane.showMessageDialog(frame, "Apagado com sucesso");
						culturaModel.fireTableDataChanged();	
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
