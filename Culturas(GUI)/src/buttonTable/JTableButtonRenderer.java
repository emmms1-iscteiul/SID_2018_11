package buttonTable;

import java.awt.Component;

import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;
/**
 * Render de Bot�es
 * @author Eduardo
 *
 */
class JTableButtonRenderer implements TableCellRenderer {	
/**
 * Render da c�lula
 */
	   @Override public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
           JButton button = (JButton)value;
           return button;  
       }
   }

