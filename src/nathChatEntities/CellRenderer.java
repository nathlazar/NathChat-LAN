/*
 * Copyright (C) Jonathan Lazar 2019-Present
 * All Rights Reserved 2019
 */
package nathChatEntities;

import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.JTable;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;

/**
 *
 * @author Jonathan Lazar <jonathanlazar17@gmail.com>
 */
public class CellRenderer extends DefaultTableCellRenderer{
    
    private JLabel label=new JLabel();
    
    public Component getTableCellRendererComponent(JTable table,Object value,boolean isSelected,boolean hasFocus,int row,int column){
        
        table.setRowHeight(30);
        label.setOpaque(true);
        label.setText(value.toString());
        label.setFont(new Font("Arial",Font.PLAIN,12));
        if(isSelected){
            
            label.setBackground(Color.decode("#28ABB9"));
            
        }
        else{
            
            label.setBackground(row%2==0?Color.WHITE:Color.decode("#EEEEEE"));
            
        }
        return label;
    }
    
}
