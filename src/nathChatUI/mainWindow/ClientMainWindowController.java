/* 
 * Copyright (C) Jonathan Lazar 2019-Present
 * All Rights Reserved 2019
 */
package nathChatUI.mainWindow;

import nathChatUI.NathChatClient;

/**
 *
 * @author Jonathan Lazar <jonathanlazar17@gmail.com>
 */
public class ClientMainWindowController {
    
    private ClientMainWindow mainWindow;
    
    public ClientMainWindowController(ClientMainWindow mainWindow){
        
        this.mainWindow=mainWindow;
        
    }
    
    protected void messageLabelMouseEntr(){
        
        javax.swing.ImageIcon messageLabelIcon=new javax.swing.ImageIcon(NathChatClient.path+"\\icons\\message-icon-h.png");
        java.awt.Image messageLabelImage=messageLabelIcon.getImage().getScaledInstance(35,35,java.awt.Image.SCALE_SMOOTH);
        messageLabelIcon.setImage(messageLabelImage);
        mainWindow.messageLabel.setIcon(messageLabelIcon);
        
    }
    
    protected void messageLabelMouseExt(){
        
        javax.swing.ImageIcon messageLabelIcon=new javax.swing.ImageIcon(NathChatClient.path+"\\icons\\message-icon-uh.png");
        java.awt.Image messageLabelImage=messageLabelIcon.getImage().getScaledInstance(35,35,java.awt.Image.SCALE_SMOOTH);
        messageLabelIcon.setImage(messageLabelImage);
        mainWindow.messageLabel.setIcon(messageLabelIcon);
        
    }
    
    protected void menuLabelMouseEntr(){
        
        javax.swing.ImageIcon menuLabelIcon=new javax.swing.ImageIcon(NathChatClient.path+"\\icons\\menu-icon-h.png");
        java.awt.Image menuLabelImage=menuLabelIcon.getImage().getScaledInstance(30,30,java.awt.Image.SCALE_SMOOTH);
        menuLabelIcon.setImage(menuLabelImage);
        mainWindow.menuLabel.setIcon(menuLabelIcon);
        
    }
    
    protected void menuLabelMouseExt(){
        
        javax.swing.ImageIcon menuLabelIcon=new javax.swing.ImageIcon(NathChatClient.path+"\\icons\\menu-icon-uh.png");
        java.awt.Image menuLabelImage=menuLabelIcon.getImage().getScaledInstance(30,30,java.awt.Image.SCALE_SMOOTH);
        menuLabelIcon.setImage(menuLabelImage);
        mainWindow.menuLabel.setIcon(menuLabelIcon);
        
    }
    
    protected void groupsLabelMouseEntr(){
        
        javax.swing.ImageIcon groupsLabelIcon=new javax.swing.ImageIcon(NathChatClient.path+"\\icons\\group-icon-h.png");
        java.awt.Image groupsLabelImage=groupsLabelIcon.getImage().getScaledInstance(30,30,java.awt.Image.SCALE_SMOOTH);
        groupsLabelIcon.setImage(groupsLabelImage);
        mainWindow.groupsLabel.setIcon(groupsLabelIcon);
        
    }
    
    protected void groupsLabelMouseExt(){
        
        javax.swing.ImageIcon groupsLabelIcon=new javax.swing.ImageIcon(NathChatClient.path+"\\icons\\group-icon-uh.png");
        java.awt.Image groupsLabelImage=groupsLabelIcon.getImage().getScaledInstance(30,30,java.awt.Image.SCALE_SMOOTH);
        groupsLabelIcon.setImage(groupsLabelImage);
        mainWindow.groupsLabel.setIcon(groupsLabelIcon);
        
    }
    
    protected void notifLabelClk(){
        
    
        
    }
    
    protected void searchLabelMouseEntr(){
        
        javax.swing.ImageIcon searchLabelIcon=new javax.swing.ImageIcon(NathChatClient.path+"\\icons\\search-icon-h.png");
        java.awt.Image searchLabelImage=searchLabelIcon.getImage().getScaledInstance(30,30,java.awt.Image.SCALE_SMOOTH);
        searchLabelIcon.setImage(searchLabelImage);
        mainWindow.searchLabel.setIcon(searchLabelIcon);
        
    }
    
    protected void searchLabelMouseExt(){
        
        javax.swing.ImageIcon searchLabelIcon=new javax.swing.ImageIcon(NathChatClient.path+"\\icons\\search-icon-uh.png");
        java.awt.Image searchLabelImage=searchLabelIcon.getImage().getScaledInstance(30,30,java.awt.Image.SCALE_SMOOTH);
        searchLabelIcon.setImage(searchLabelImage);
        mainWindow.searchLabel.setIcon(searchLabelIcon);
        
    }
    
}
