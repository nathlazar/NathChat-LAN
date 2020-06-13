/*
 * Copyright (C) Jonathan Lazar 2019-Present
 * All Rights Reserved 2019
 */
package nathChatEntities;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;
import nathChatUI.NathChatClient;

/**
 *
 * @author Jonathan Lazar <jonathanlazar17@gmail.com>
 */
public class ReceiveNotifThread extends Thread{
    
    private ServerSocket receiverServerSocket;
    private Socket receiverSocket;
    private ObjectOutputStream out;
    private ObjectInputStream in;
    private int port=4765;
    
    @Override
    public void run(){
        
        try{
            
            receiverServerSocket=new ServerSocket(port);
            
            while(true){
                
                receiverSocket=receiverServerSocket.accept();
                in=new ObjectInputStream(receiverSocket.getInputStream());
                NotificationWrapper notifWrapper=(NotificationWrapper)in.readObject();
                SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yy HH:mm:ss");
                Date date=new Date();
                NathChatClient.core.notifWriteToXML(notifWrapper,sdf.format(date));
                out=new ObjectOutputStream(receiverSocket.getOutputStream());
                out.writeObject(new String("Thanks"));
                out.close();
                in.close();
                receiverSocket.close();
                NathChatClient.core.updateNotifLabel(NathChatClient.core.getNotifNumber());
                
            }
            
        }
        catch(IOException e){
            
            e.printStackTrace();
            
        }
        catch(ClassNotFoundException e){
            
            e.printStackTrace();
            
        }
        
    }
    
}
