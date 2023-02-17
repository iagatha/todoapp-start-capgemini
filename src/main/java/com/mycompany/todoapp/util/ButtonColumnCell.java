/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.todoapp.util;

import java.awt.Component;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author iagatha
 */
public class ButtonColumnCell extends DefaultTableCellRenderer {
    
    private String buttonType;

    public String getButtonType() {
        return buttonType;
    }

    public void setButtonType(String buttonType) {
        this.buttonType = buttonType;
    }
    
    public ButtonColumnCell(String buttonType){
        this.buttonType = buttonType;
    }
    
     @Override
     public Component getTableCellRendererComponent(JTable table, Object value,
     boolean isSelected, boolean hasFocus, int row, int column) {
         
         JLabel label;
          label = (JLabel) super.getTableCellRendererComponent(table, value,
                  isSelected, hasFocus, row, column);
          label.setHorizontalAlignment(CENTER);
          label.setIcon(new javax.swing.ImageIcon(getClass().getResource("/" +buttonType + ".png")));
      
          return label;
     }
    
}
