/* 
 * Copyright (C) Jonathan Lazar 2019-Present
 * All Rights Reserved 2019
 */
package nathChatEntities;

import java.util.HashMap;

/**
 *
 * @author Lazar
 */
public class MessageWrapper implements java.io.Serializable{
    
    private HashMap<Integer,TextPaneElement> characterMap;
    private String sender,msgID;
    
    public MessageWrapper(HashMap<Integer,TextPaneElement> characterMap,String msgID,String sender){
        
        this.characterMap=characterMap;
        this.msgID=msgID;
        this.sender=sender;
        
    }
    
    public HashMap<Integer,TextPaneElement> getCharacterMap(){
        
        return this.characterMap;
        
    }
    
    public String getMsgID(){
        
        return this.msgID;
        
    }
    
    public String getSender(){
        
        return this.sender;
        
    }
    
}
