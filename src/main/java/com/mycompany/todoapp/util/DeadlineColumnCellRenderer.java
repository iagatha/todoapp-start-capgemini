
package com.mycompany.todoapp.util;

import com.mycompany.todoapp.model.Tasks;
import java.awt.Color;
import java.awt.Component;
import java.util.Date;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author iagatha
 */
public class DeadlineColumnCellRenderer extends DefaultTableCellRenderer {
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value,
    boolean isSelected, boolean hasFocus, int row, int column) {
         
         JLabel label;
          label = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
          label.setHorizontalAlignment(CENTER);
          
         TaskTableModel taskModel =  (TaskTableModel) table.getModel();
         Tasks tasks = taskModel.getTasks().get(row);
         
         if(tasks.getDeadline().after(new Date())){
             label.setBackground(Color.green);
         }else {
             label.setBackground(Color.red);
         }
            return label;
     }
    
}
