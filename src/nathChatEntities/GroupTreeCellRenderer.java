/*
 * Copyright (C) Jonathan Lazar 2019-Present
 * All Rights Reserved 2019
 */
package nathChatEntities;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JTree;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.tree.DefaultTreeCellRenderer;
import nathChatUI.NathChatClient;

/**
 *
 * @author Jonathan Lazar <jonathanlazar17@gmail.com>
 */
public class GroupTreeCellRenderer extends DefaultTreeCellRenderer{

    private ImageIcon labelIcon=new ImageIcon(NathChatClient.path+"\\icons\\group-icon-uh.png");
    private ImageIcon groupChatIcon=new ImageIcon(NathChatClient.path+"\\icons\\group-chat-icon2.png");
    private Image labelImage=labelIcon.getImage().getScaledInstance(20,20,java.awt.Image.SCALE_SMOOTH);
    private Image groupChatImage=groupChatIcon.getImage().getScaledInstance(20,20,java.awt.Image.SCALE_SMOOTH);
    
    public GroupTreeCellRenderer(){
        
        setOpaque(true);
        setIconTextGap(15);
        setPreferredSize(new Dimension(200,30));
        
    }
    
    @Override
    public Component getTreeCellRendererComponent(JTree tree,Object value,boolean selected,boolean expanded,boolean leaf, int row,boolean hasFocus){
        
        tree.setRowHeight(30);
        
        if(selected){
            
            setBackground(Color.decode("#28ABB9"));
            
        }
        else{
            
            setBackground(Color.WHITE);
            
        }
        
        if(value.toString().equals("Groups")){
            
            labelIcon.setImage(labelImage);
            setIcon(labelIcon);
            
            
        }
        else{
            
            groupChatIcon.setImage(groupChatImage);
            setIcon(groupChatIcon);
            setText(value.toString());
            
        }
 
        setText(value.toString());
        return this;
        
    }
}
