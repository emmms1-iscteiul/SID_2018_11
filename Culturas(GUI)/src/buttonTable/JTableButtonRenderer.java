package buttonTable;

import java.awt.Component;

import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;
/**
 * Render de Botões
 * @author Eduardo
 *
 */
class JTableButtonRenderer implements TableCellRenderer {	
/**
 * Render da célula
 */
	   @Override public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
           JButton button = (JButton)value;
           return button;  
       }
   }

