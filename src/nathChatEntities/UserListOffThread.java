/* 
 * Copyright (C) Jonathan Lazar 2019-Present
 * All Rights Reserved 2019
 */
package nathChatEntities;

import nathChatUI.NathChatClient;

/**
 *
 * @author Lazar
 */
public class UserListOffThread extends Thread{
    
    @Override
    public void run(){
        
        while(true){
            
            try{
                
                for(int i=0;i<NathChatClient.core.getDetectedUserList().size();i++){
                    
                    NathChatClient.core.getDetectedUserList().get(i).setUserStatus(Constants.USER_OFF);
                    
                }
                
                
                synchronized(this){
                    
                    this.wait();
                    
                }
                     
            }
            
            catch(InterruptedException ie){
                
                javax.swing.JOptionPane.showMessageDialog(null,ie,"Error",javax.swing.JOptionPane.ERROR_MESSAGE);
                
            }
        }
        
    }
    
}
