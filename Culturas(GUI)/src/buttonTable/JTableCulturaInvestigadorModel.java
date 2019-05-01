package buttonTable;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.table.AbstractTableModel;

import gui.CriarCulturaGUI;
import gui.EditarCulturaGUI;
import gui.FuncionalidadesInvestigador;

public class JTableCulturaInvestigadorModel  extends AbstractTableModel{
	private static final long serialVersionUID = 1L;
	private static final String[] COLUMN_NAMES = new String[] { "Nome Cultura", "Descrição Cultura", "Criar","Editar", "Apagar" };
	private FuncionalidadesInvestigador funcInv;

	public JTableCulturaInvestigadorModel(FuncionalidadesInvestigador funcInv) {
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
//			return "Nome Cultura";
			ResultSet cultura = funcInv.filtrarCulturaTudo();
			try {
				while (cultura.next()) {
					int idC = Integer.valueOf(cultura.getObject("IDCultura").toString());
					if (idC == rowIndex) {
						return cultura.getObject("NomeCultura").toString();
					}
				}
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		case 1:
//			return "Descrição Cultura";
			ResultSet culturaD = funcInv.filtrarCulturaTudo();
			try {
				while (culturaD.next()) {
					int idC = Integer.valueOf(culturaD.getObject("IDCultura").toString());
					if (idC == rowIndex) {
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
		case 2:
//			return "Nome Utilizador Responsável";
			ResultSet culturaU = funcInv.filtrarCulturaTudo();
			try {
				while (culturaU.next()) {
					int idC = Integer.valueOf(culturaU.getObject("IDCultura").toString());
					if (idC == rowIndex) {
						return culturaU.getObject("NomeUtilizador").toString();
					}
				}
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		case 3:
			final JButton botaoCriar = new JButton(COLUMN_NAMES[columnIndex]);
			botaoCriar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					CriarCulturaGUI frameC = new CriarCulturaGUI("Criar Cultura", funcInv);
					frameC.open();
				}
			});
			return botaoCriar;
		case 4:
			final JButton botaoEditar = new JButton(COLUMN_NAMES[columnIndex]);
			botaoEditar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					EditarCulturaGUI frameE = new EditarCulturaGUI("Editar Cultura", funcInv, rowIndex);
					frameE.open();
				}
			});
			return botaoEditar;
		case 5:
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
