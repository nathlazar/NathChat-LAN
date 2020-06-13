/*
 * Copyright (C) Jonathan Lazar 2019-Present
 * All Rights Reserved 2019
 */
package nathChatEntities;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 *
 * @author Jonathan Lazar <jonathanlazar17@gmail.com>
 */
public class SendNotifThread extends Thread{
    
    private Socket senderSocket;
    private int port=4765;
    private String host;
    private NotificationWrapper notifWrapper;
    private ObjectOutputStream out;
    private ObjectInputStream in;
    
    public SendNotifThread(NotificationWrapper notifWrapper,String host){
        
        this.notifWrapper=notifWrapper;
        this.host=host;
        
    }
    
    @Override
    public void run(){
        
        try{
            
            senderSocket=new Socket(this.host,port);
            out=new ObjectOutputStream(senderSocket.getOutputStream());
            out.writeObject(this.notifWrapper);
            in=new ObjectInputStream(senderSocket.getInputStream());
            String msg=(String)in.readObject();
            System.out.println(msg);
            out.close();
            in.close();
            senderSocket.close();
            
        }
        catch(UnknownHostException e){
            
            e.printStackTrace();
            
        }
        catch(IOException e){
            
            e.printStackTrace();
            
        }
        catch(ClassNotFoundException e){
            
            e.printStackTrace();
            
        }
        
    }
    
}
