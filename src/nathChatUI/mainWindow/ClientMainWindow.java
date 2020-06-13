/* 
 * Copyright (C) Jonathan Lazar 2019-Present
 * All Rights Reserved 2019
 */
package nathChatUI.mainWindow;

import javax.swing.ImageIcon;
import jbl.javaDev.swingSupport.ToggleButton;
import nathChatUI.groupListPanel.GroupListPanel;
import nathChatUI.MessagePanel;
import nathChatUI.NathChatClient;
import nathChatUI.notificationPanel.NotificationPanel;
import nathChatUI.menuDialog.NathChatMenuDialog;
import nathChatUI.SearchPanel;
/**
 *
 * @author Anjon Rolo Lazar
 */
public class ClientMainWindow extends javax.swing.JFrame {
    
    /**
     * Creates new form ClientMainWindow
     */
    
    private ClientMainWindowController controller;
    
    public ClientMainWindow() {
        this.setUndecorated(true);
        initComponents();
        NathChatClient.notifLabelRef=notifLabel;
        NathChatClient.core.updateNotifLabel(NathChatClient.core.getNotifNumber());
        this.setSize(new java.awt.Dimension(240,NathChatClient.windowSize.height-((int)(NathChatClient.windowSize.height*0.25))));
        //this.setSize(new java.awt.Dimension(240,400));
        messagePanel=new MessagePanel();
        groupListPanel=new GroupListPanel();
        notificationPanel=new NotificationPanel();
        searchPanel=new SearchPanel();
        carouselPanel.setPreferredSize(new java.awt.Dimension(carouselPanel.getWidth(),carouselPanel.getHeight()));
        carouselPanel.add(messageLabel,messagePanel);
        carouselPanel.add(groupsLabel,groupListPanel);
        carouselPanel.add(notifLabel,notificationPanel);
        carouselPanel.add(searchLabel,searchPanel);
        nathChatMenuDialog=new NathChatMenuDialog();
        nathChatMenuDialog.setDialogAppearance(jbl.javaDev.swingSupport.MenuDialog.DIALOG_APPEARANCE_DOWN_SIDE_RIGHT_ALIGN);
        menuLabel.setMenuDialog(nathChatMenuDialog);
        toggleButton=new ToggleButton(this,35,35);
        toggleButton.setToggleIcon(new ImageIcon(NathChatClient.path+"\\icons\\hide-right-icon.png"),new ImageIcon(NathChatClient.path+"\\icons\\show-left-icon.png"));
        controller=new ClientMainWindowController(this);
        this.getRootPane().setBorder(javax.swing.BorderFactory.createLineBorder(java.awt.Color.decode("#385D8A"),1));
        this.setVisible(true);
        
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        upperPanel = new javax.swing.JPanel();
        titleLabel = new javax.swing.JLabel();
        closeLabel = new javax.swing.JLabel();
        menuLabel = new jbl.javaDev.swingSupport.MenuHover("");
        messageLabel = new javax.swing.JLabel();
        groupsLabel = new javax.swing.JLabel();
        searchLabel = new javax.swing.JLabel();
        logoLabel = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        notifLabel = new javax.swing.JLabel();
        carouselPanel = new jbl.javaDev.swingSupport.Carousel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        upperPanel.setBackground(new java.awt.Color(31, 73, 114));

        titleLabel.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        titleLabel.setForeground(new java.awt.Color(255, 255, 255));
        titleLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        titleLabel.setText("NathChat Client");

        closeLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        javax.swing.ImageIcon closeIconImageIcon=new javax.swing.ImageIcon(NathChatClient.path+"\\icons\\close-icon-uh.png");
        java.awt.Image closeIconImage=closeIconImageIcon.getImage().getScaledInstance(15,15,java.awt.Image.SCALE_SMOOTH);
        closeIconImageIcon.setImage(closeIconImage);
        closeLabel.setIcon(closeIconImageIcon);
        closeLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                closeLabelMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                closeLabelMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                closeLabelMouseExited(evt);
            }
        });

        menuLabel.setText("jLabel2");
        javax.swing.ImageIcon menuLabelIcon=new javax.swing.ImageIcon(NathChatClient.path+"\\icons\\menu-icon-uh.png");
        java.awt.Image menuLabelImage=menuLabelIcon.getImage().getScaledInstance(30,30,java.awt.Image.SCALE_SMOOTH);
        menuLabelIcon.setImage(menuLabelImage);
        menuLabel.setIcon(menuLabelIcon);
        menuLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                menuLabelMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                menuLabelMouseExited(evt);
            }
        });

        messageLabel.setText("jLabel3");
        javax.swing.ImageIcon messageLabelIcon=new javax.swing.ImageIcon(NathChatClient.path+"\\icons\\message-icon-uh.png");
        java.awt.Image messageLabelImage=messageLabelIcon.getImage().getScaledInstance(35,35,java.awt.Image.SCALE_SMOOTH);
        messageLabelIcon.setImage(messageLabelImage);
        messageLabel.setIcon(messageLabelIcon);
        messageLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                messageLabelMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                messageLabelMouseExited(evt);
            }
        });

        groupsLabel.setText("jLabel2");
        javax.swing.ImageIcon groupsLabelIcon=new javax.swing.ImageIcon(NathChatClient.path+"\\icons\\group-icon-uh.png");
        java.awt.Image groupsLabelImage=groupsLabelIcon.getImage().getScaledInstance(30,30,java.awt.Image.SCALE_SMOOTH);
        groupsLabelIcon.setImage(groupsLabelImage);
        groupsLabel.setIcon(groupsLabelIcon);
        groupsLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                groupsLabelMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                groupsLabelMouseExited(evt);
            }
        });

        searchLabel.setText("jLabel2");
        javax.swing.ImageIcon searchLabelIcon=new javax.swing.ImageIcon(NathChatClient.path+"\\icons\\search-icon-uh.png");
        java.awt.Image searchLabelImage=searchLabelIcon.getImage().getScaledInstance(30,30,java.awt.Image.SCALE_SMOOTH);
        searchLabelIcon.setImage(searchLabelImage);
        searchLabel.setIcon(searchLabelIcon);
        searchLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                searchLabelMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                searchLabelMouseExited(evt);
            }
        });

        logoLabel.setText("jLabel2");

        jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);

        notifLabel.setText("jLabel1");
        javax.swing.ImageIcon notifLabelIcon=new javax.swing.ImageIcon(NathChatClient.path+"\\icons\\notif-icon-uh.png");
        java.awt.Image notifLabelImage=notifLabelIcon.getImage().getScaledInstance(30,30,java.awt.Image.SCALE_SMOOTH);
        notifLabelIcon.setImage(notifLabelImage);
        notifLabel.setIcon(notifLabelIcon);
        notifLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                notifLabelMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                notifLabelMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                notifLabelMouseExited(evt);
            }
        });

        javax.swing.GroupLayout upperPanelLayout = new javax.swing.GroupLayout(upperPanel);
        upperPanel.setLayout(upperPanelLayout);
        upperPanelLayout.setHorizontalGroup(
            upperPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(upperPanelLayout.createSequentialGroup()
                .addGroup(upperPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(upperPanelLayout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addComponent(logoLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(upperPanelLayout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addComponent(menuLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(5, 5, 5)
                .addGroup(upperPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(upperPanelLayout.createSequentialGroup()
                        .addComponent(titleLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20)
                        .addComponent(closeLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(upperPanelLayout.createSequentialGroup()
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(messageLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(groupsLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(notifLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(searchLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
        );
        upperPanelLayout.setVerticalGroup(
            upperPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(upperPanelLayout.createSequentialGroup()
                .addGroup(upperPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(closeLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(upperPanelLayout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addGroup(upperPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(titleLabel)
                            .addComponent(logoLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGroup(upperPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, upperPanelLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(upperPanelLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(upperPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(searchLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(upperPanelLayout.createSequentialGroup()
                                .addGroup(upperPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(notifLabel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(upperPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(menuLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(messageLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(groupsLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addGap(6, 6, 6))))
        );

        javax.swing.ImageIcon logoLabelIcon=new javax.swing.ImageIcon(NathChatClient.path+"\\icons\\nathChatLogo.png");
        java.awt.Image logoLabelImage=logoLabelIcon.getImage().getScaledInstance(30,30,java.awt.Image.SCALE_SMOOTH);
        logoLabelIcon.setImage(logoLabelImage);
        logoLabel.setIcon(logoLabelIcon);

        carouselPanel.setLayout(null);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(upperPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(carouselPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(upperPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(carouselPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 198, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void closeLabelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_closeLabelMouseExited
        // TODO add your handling code here:
        javax.swing.ImageIcon closeIconImageIcon=new javax.swing.ImageIcon(NathChatClient.path+"\\icons\\close-icon-uh.png");
        java.awt.Image closeIconImage=closeIconImageIcon.getImage().getScaledInstance(15,15,java.awt.Image.SCALE_SMOOTH);
        closeIconImageIcon.setImage(closeIconImage);
        closeLabel.setIcon(closeIconImageIcon);
    }//GEN-LAST:event_closeLabelMouseExited

    private void closeLabelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_closeLabelMouseEntered
        // TODO add your handling code here:
        javax.swing.ImageIcon closeIconImageIcon=new javax.swing.ImageIcon(NathChatClient.path+"\\icons\\close-icon-h.png");
        java.awt.Image closeIconImage=closeIconImageIcon.getImage().getScaledInstance(15,15,java.awt.Image.SCALE_SMOOTH);
        closeIconImageIcon.setImage(closeIconImage);
        closeLabel.setIcon(closeIconImageIcon);
    }//GEN-LAST:event_closeLabelMouseEntered

    private void closeLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_closeLabelMouseClicked
        // TODO add your handling code here:
        System.exit(0);
    }//GEN-LAST:event_closeLabelMouseClicked

    private void menuLabelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuLabelMouseEntered
        // TODO add your handling code here:
        
        controller.menuLabelMouseEntr();
        
    }//GEN-LAST:event_menuLabelMouseEntered

    private void messageLabelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_messageLabelMouseEntered
        // TODO add your handling code here:
        
        controller.messageLabelMouseEntr();
        
    }//GEN-LAST:event_messageLabelMouseEntered

    private void messageLabelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_messageLabelMouseExited
        // TODO add your handling code here:
        
        controller.messageLabelMouseExt();
        
    }//GEN-LAST:event_messageLabelMouseExited

    private void menuLabelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuLabelMouseExited
        // TODO add your handling code here:
        
        controller.menuLabelMouseExt();
        
    }//GEN-LAST:event_menuLabelMouseExited

    private void groupsLabelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_groupsLabelMouseExited
        // TODO add your handling code here:
        
        controller.groupsLabelMouseExt();
        
    }//GEN-LAST:event_groupsLabelMouseExited

    private void groupsLabelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_groupsLabelMouseEntered
        // TODO add your handling code here:
        
        controller.groupsLabelMouseEntr();
        
    }//GEN-LAST:event_groupsLabelMouseEntered

    private void searchLabelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_searchLabelMouseEntered
        // TODO add your handling code here:
        
        controller.searchLabelMouseEntr();
        
    }//GEN-LAST:event_searchLabelMouseEntered

    private void searchLabelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_searchLabelMouseExited
        // TODO add your handling code here:
        
        controller.searchLabelMouseExt();
        
    }//GEN-LAST:event_searchLabelMouseExited

    private void notifLabelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_notifLabelMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_notifLabelMouseEntered

    private void notifLabelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_notifLabelMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_notifLabelMouseExited

    private void notifLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_notifLabelMouseClicked
        // TODO add your handling code here:
        
        controller.notifLabelClk();
        
    }//GEN-LAST:event_notifLabelMouseClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
    /*
    private javax.swing.JPanel carouselPanel;
    */
    private jbl.javaDev.swingSupport.Carousel carouselPanel;
    protected javax.swing.JLabel closeLabel;
    protected javax.swing.JLabel groupsLabel;
    private javax.swing.JSeparator jSeparator1;
    protected javax.swing.JLabel logoLabel;
    /*
    protected javax.swing.JLabel menuLabel;
    */
    protected jbl.javaDev.swingSupport.MenuHover menuLabel;
    protected javax.swing.JLabel messageLabel;
    protected javax.swing.JLabel notifLabel;
    protected javax.swing.JLabel searchLabel;
    private javax.swing.JLabel titleLabel;
    private javax.swing.JPanel upperPanel;
    // End of variables declaration//GEN-END:variables

    
    //Defined Variables
    private jbl.javaDev.swingSupport.MenuDialog nathChatMenuDialog;
    private MessagePanel messagePanel;
    private GroupListPanel groupListPanel;
    private NotificationPanel notificationPanel;
    private SearchPanel searchPanel;
    private ToggleButton toggleButton;
    //
    
}
