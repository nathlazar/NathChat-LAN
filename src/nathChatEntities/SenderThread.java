/* 
 * Copyright (C) Jonathan Lazar 2019-Present
 * All Rights Reserved 2019
 */
package nathChatEntities;

import java.net.Socket;
import java.net.UnknownHostException;
import java.net.ConnectException;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.util.HashMap;
import nathChatUI.NathChatClient;

/**
 *
 * @author Lazar
 */
public class SenderThread extends Thread{
    
    private Socket senderSocket;
    private int port=4755;
    private String host;
    private MessageWrapper msgWrapper;
    private ObjectOutputStream out;
    private ObjectInputStream in;
    private HashMap<Integer,TextPaneElement> tpe;
    
    public SenderThread(String host,HashMap<Integer,TextPaneElement> tpe,MessageWrapper msgWrapper){
        
        this.host=host;
        this.msgWrapper=msgWrapper;
        this.tpe=tpe;
        
    }
    
    @Override
    public void run(){
        
        try{
            
            NathChatClient.core.getNewMessage(NathChatClient.composeMessageList.get(this.host).getGLM(),this.host);
            senderSocket=new Socket(this.host,port);
            out=new ObjectOutputStream(senderSocket.getOutputStream());
            out.writeObject(this.msgWrapper);
            in=new ObjectInputStream(senderSocket.getInputStream());
            String msg=(String)in.readObject();
            NathChatClient.composeMessageList.get(this.host).reloadScrollPane();
            System.out.println(msg);
            out.close();
            in.close();
            senderSocket.close();
            
        }
        catch(ConnectException ce){
            
            //Added to outbox queue after receiver can't reached
            ce.printStackTrace();
            
        }
        catch(UnknownHostException ue){
            
            ue.printStackTrace();
            
        }
        catch(IOException ioe){
            
            ioe.printStackTrace();
            
        }
        catch(ClassNotFoundException cfe){
            
            cfe.printStackTrace();
            
        }
        
        tpe.clear();
        
    }
    
}
