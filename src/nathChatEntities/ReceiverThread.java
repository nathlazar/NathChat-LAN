/* 
 * Copyright (C) Jonathan Lazar 2019-Present
 * All Rights Reserved 2019
 */
package nathChatEntities;

import java.net.ServerSocket;
import java.net.Socket;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import nathChatUI.NathChatClient;

/**
 *
 * @author Lazar
 */
public class ReceiverThread extends Thread{
    
    private ServerSocket receiverServerSocket;
    private Socket receiverSocket;
    private ObjectOutputStream out;
    private ObjectInputStream in;
    private int port=4755;
    
    @Override
    public void run(){
        
        try{
            
            receiverServerSocket=new ServerSocket(port);
            
            while(true){
                
                receiverSocket=receiverServerSocket.accept();
                in=new ObjectInputStream(receiverSocket.getInputStream());
                MessageWrapper msgWrapper=(MessageWrapper)in.readObject();
                SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yy HH:mm:ss");
                Date date=new Date();
                NathChatClient.core.writeToXML(msgWrapper.getCharacterMap(),msgWrapper.getSender(),Constants.MESSAGE_TYPE_RECEIVER,sdf.format(date),msgWrapper.getMsgID());
                out=new ObjectOutputStream(receiverSocket.getOutputStream());
                out.writeObject(new String("Thanks"));
                out.close();
                in.close();
                receiverSocket.close();
                if(NathChatClient.composeMessageList.containsKey(msgWrapper.getSender())){
                    
                    NathChatClient.core.getNewMessage(NathChatClient.composeMessageList.get(msgWrapper.getSender()).getGLM(),msgWrapper.getSender());
                    NathChatClient.composeMessageList.get(msgWrapper.getSender()).reloadScrollPane();
                    
                }
                
            }
            
        }
        catch(IOException ioe){
            
            ioe.printStackTrace();
            
        }
        catch(ClassNotFoundException cfe){
            
            cfe.printStackTrace();
            
        }
            
        
    }
        
        
    
}
