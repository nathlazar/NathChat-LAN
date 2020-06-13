/*
 * Copyright (C) Jonathan Lazar 2019-Present
 * All Rights Reserved 2019
 */
package nathChatUI.groupListPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import nathChatUI.inviteUsersDialog.InviteUsersDialog;
import nathChatUI.NathChatClient;

/**
 *
 * @author Jonathan Lazar <jonathanlazar17@gmail.com>
 */
public class GroupListTreePopup extends JPopupMenu implements ActionListener{
    
    private JMenuItem messageMenuItem,inviteUsersMenuItem;
    private GroupListTree tree;
    
    public GroupListTreePopup(GroupListTree tree){
        
        this.tree=tree;
        messageMenuItem=new JMenuItem("Message");
        messageMenuItem.addActionListener(this);
        inviteUsersMenuItem=new JMenuItem("Invite users");
        inviteUsersMenuItem.addActionListener(this);
        add(messageMenuItem);
        add(inviteUsersMenuItem);
        
    }
    
    @Override
    public void actionPerformed(ActionEvent e){
        
        if(e.getSource()==messageMenuItem){
            
        }
        else if(e.getSource()==inviteUsersMenuItem){
            
            InviteUsersDialog dialog=new InviteUsersDialog(NathChatClient.clientMainWindow,true,tree.getTreePath().getPath()[1].toString());
            dialog.setVisible(true);
            
        }
        
    }
    
}
