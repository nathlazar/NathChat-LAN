/* 
 * Copyright (C) Jonathan Lazar 2019-Present
 * All Rights Reserved 2019
 */
package nathChatUI;

import java.awt.Color;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.Timer;
import javax.swing.Box;
import java.awt.Dimension;
import javax.swing.ImageIcon;
import java.awt.Image;
import javax.swing.GroupLayout;

/**
 *
 * @author nathan
 */
public class MessagePanel extends javax.swing.JPanel {

    /**
     * Creates new form MessagePanel
     */
    public MessagePanel(){
        initComponents();
        usersListScrollPane=new javax.swing.JScrollPane(javax.swing.JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,javax.swing.JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        usersListScrollPane.setBorder(null);
        usersListScrollPane.setPreferredSize(new java.awt.Dimension(229,480));
        viewPort=new javax.swing.JPanel();
        viewPort.setLayout(null);
        
        this.usersListUpdaterTimer=new javax.swing.Timer(10000,
                
                new java.awt.event.ActionListener(){
                    
                    @Override
                    public void actionPerformed(java.awt.event.ActionEvent ae){
                        
                        NathChatClient.core.usersListUpdater(viewPort,usersListScrollPane.getWidth(),MessagePanel.this.usersListUpdaterTimer);
                            
                    }
                    
                }
                
        );
        
        this.usersListUpdaterTimer.start();
        usersListScrollPane.setViewportView(viewPort);
        //usersListScrollPane.setBorder(javax.swing.BorderFactory.createLineBorder(java.awt.Color.BLUE,1));
        downPanel=new JPanel();
        downPanel.setBackground(Color.decode("#1F4972"));
        downPanel.setPreferredSize(new Dimension(229,40));
        //downPanel.setBorder(javax.swing.BorderFactory.createLineBorder(java.awt.Color.BLUE,1));
        downPanel.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEADING));
        downPanel.add(Box.createRigidArea(new Dimension(5,0)));
        this.createMsgLabel=new JLabel();
        this.createMsgLabel.setPreferredSize(new Dimension(30,30));
        ImageIcon createMsgIcon=new ImageIcon(NathChatClient.path+"\\icons\\create-msg.png");
        Image createMsgImage=createMsgIcon.getImage().getScaledInstance(30,30,Image.SCALE_SMOOTH);
        createMsgIcon.setImage(createMsgImage);
        createMsgLabel.setIcon(createMsgIcon);
        downPanel.add(createMsgLabel);
        downPanel.add(Box.createRigidArea(new Dimension(5,0)));
        this.addUserLabel=new JLabel();
        this.addUserLabel.setPreferredSize(new Dimension(30,30));
        ImageIcon addUserIcon=new ImageIcon(NathChatClient.path+"\\icons\\add-user-icon.png");
        Image addUserImage=addUserIcon.getImage().getScaledInstance(25,25,Image.SCALE_SMOOTH);
        addUserIcon.setImage(addUserImage);
        addUserLabel.setIcon(addUserIcon);
        downPanel.add(addUserLabel);
        
        GroupLayout messagePanelLayout=new GroupLayout(this);
        this.setLayout(messagePanelLayout);
        messagePanelLayout.setHorizontalGroup(
                messagePanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(usersListScrollPane,GroupLayout.DEFAULT_SIZE,GroupLayout.DEFAULT_SIZE,Short.MAX_VALUE)
                    .addComponent(downPanel,GroupLayout.DEFAULT_SIZE,GroupLayout.DEFAULT_SIZE,Short.MAX_VALUE)

        );
        messagePanelLayout.setVerticalGroup(
                messagePanelLayout.createSequentialGroup()
                    .addComponent(usersListScrollPane,0,GroupLayout.DEFAULT_SIZE,Short.MAX_VALUE)
                    .addComponent(downPanel,50,GroupLayout.DEFAULT_SIZE,Short.MAX_VALUE)
        );
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 266, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 435, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables

    private JScrollPane usersListScrollPane;
    private JPanel viewPort,downPanel;
    private JLabel createMsgLabel,addUserLabel;
    private Timer usersListUpdaterTimer;

}
