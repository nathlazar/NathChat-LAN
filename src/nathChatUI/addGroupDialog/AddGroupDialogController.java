/*
 * Copyright (C) Jonathan Lazar 2019-Present
 * All Rights Reserved 2019
 */
package nathChatUI.addGroupDialog;

import jbl.javaDev.util.RandomizedCharacters;
import nathChatUI.NathChatClient;

/**
 *
 * @author Jonathan Lazar <jonathanlazar17@gmail.com>
 */
public class AddGroupDialogController {
    
    public AddGroupDialog dialog;
    
    public AddGroupDialogController(AddGroupDialog dialog){
        
        this.dialog=dialog;
        
    }
    
    protected void addGroupBtnClk(String groupName){
        
        String grpID=new RandomizedCharacters(5).getRandomizedCharacters();
        if(NathChatClient.core.addToGroupList(groupName,grpID,NathChatClient.deviceName)){
            
            dialog.groupNameTextField.setText("");
            NathChatClient.core.displayGroupList(dialog.groupListTable);
            NathChatClient.core.addNodeToGroupTree(groupName);
            
        }
        else{
            
        }
        
    }
    
}
