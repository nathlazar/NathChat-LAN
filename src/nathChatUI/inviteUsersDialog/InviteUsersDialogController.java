/*
 * Copyright (C) Jonathan Lazar 2019-Present
 * All Rights Reserved 2019
 */
package nathChatUI.inviteUsersDialog;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import jbl.javaDev.util.RandomizedCharacters;
import nathChatEntities.Constants;
import nathChatEntities.NotificationWrapper;
import nathChatEntities.SendNotifThread;
import nathChatUI.NathChatClient;
import nathChatUI.inviteUsersDialog.InviteUsersDialog;

/**
 *
 * @author Jonathan Lazar <jonathanlazar17@gmail.com>
 */
public class InviteUsersDialogController {
    
    private InviteUsersDialog inviteUsersDialog;
    
    public InviteUsersDialogController(InviteUsersDialog inviteUsersDialog){
        
        this.inviteUsersDialog=inviteUsersDialog;
        
    }
    
    protected void searchTextFieldKP(KeyEvent evt){
        String str=inviteUsersDialog.searchTextField.getText();
        if(evt.getKeyCode()==KeyEvent.VK_BACK_SPACE&&str.length()!=0){
            
            str=str.substring(0,str.length()-1);
            NathChatClient.core.searchUsers(inviteUsersDialog.table,str);
            
        }
        else{
            
            NathChatClient.core.searchUsers(inviteUsersDialog.table,str+Character.toString(evt.getKeyChar()));
            
        }
        
    }
    
    protected void tableMouseClk(MouseEvent evt){
        
        inviteUsersDialog.searchTextField.setText((String)inviteUsersDialog.table.getModel().getValueAt(inviteUsersDialog.table.getSelectedRow(),0));
        inviteUsersDialog.searchTextField.setForeground(Color.BLACK);
        
    }
    
    protected void btnActionPerformed(ActionEvent evt){
        
        if(!inviteUsersDialog.searchTextField.getText().isEmpty()){
            
            String notifID=new RandomizedCharacters(5).getRandomizedCharacters();
            SendNotifThread thread=new SendNotifThread(
            
                new NotificationWrapper(
                        
                    Constants.NOTIF_INVITE,
                    NathChatClient.deviceName+" invites you to join "+inviteUsersDialog.groupNameLabel.getText()+" group",
                    inviteUsersDialog.searchTextField.getText(),
                    notifID
                        
                ),
                inviteUsersDialog.searchTextField.getText()
                    
            );
            
            thread.start();
            
        }
        
    }
    
}
