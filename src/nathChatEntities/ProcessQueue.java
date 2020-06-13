/*
 * Copyright (C) Jonathan Lazar 2019-Present
 * All Rights Reserved 2019
 */
package nathChatEntities;

/**
 *
 * @author Jonathan Lazar <jonathanlazar17@gmail.com>
 */
public class ProcessQueue extends Thread{
    
    @Override
    public void run(){
        
        try{
        
            this.sleep(10000);
            
        }
        catch(InterruptedException e){
            
            e.printStackTrace();
            
        }
    
    }

}