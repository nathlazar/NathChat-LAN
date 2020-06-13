/*
 * Copyright (C) Jonathan Lazar 2019-Present
 * All Rights Reserved 2019
 */
package nathChatUI.groupListPanel;

import nathChatUI.addGroupDialog.AddGroupDialog;
import nathChatUI.NathChatClient;

/**
 *
 * @author Jonathan Lazar <jonathanlazar17@gmail.com>
 */
public class GroupListPanelController {
    
    private GroupListPanel groupListPanel;
    
    public GroupListPanelController(GroupListPanel groupListPanel){
        
        this.groupListPanel=groupListPanel;
        
    }
    
    protected void addGroupMouseClk(){
        
        NathChatClient.addGroupDialog.setVisible(true);
        
    }
    
}
