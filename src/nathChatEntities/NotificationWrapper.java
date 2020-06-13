/*
 * Copyright (C) Jonathan Lazar 2019-Present
 * All Rights Reserved 2019
 */
package nathChatEntities;

import java.io.Serializable;

/**
 *
 * @author Jonathan Lazar <jonathanlazar17@gmail.com>
 */
public class NotificationWrapper implements Serializable{
    
    private Constants type;
    private String body;
    private String user;
    private String notifID;
    
    public NotificationWrapper(Constants type,String body,String user,String notifID){
        
        this.type=type;
        this.body=body;
        this.user=user;
        this.notifID=notifID;
        
    }
    
    public Constants getNotifType(){
        
        return this.type;
        
    }
    
    public String getBody(){
        
        return this.body;
        
    }
    
    public String getUser(){
        
        return this.user;
        
    }
    
    public String getNotifID(){
        
        return this.notifID;
        
    }
    
}
