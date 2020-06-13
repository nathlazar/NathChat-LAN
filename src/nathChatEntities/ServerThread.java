/* 
 * Copyright (C) Jonathan Lazar 2019-Present
 * All Rights Reserved 2019
 */
package nathChatEntities;

/**
 *
 * @author Lazar
 */

import java.net.InetAddress;
import java.net.DatagramSocket;
import java.net.UnknownHostException;
import java.net.DatagramPacket;
import java.io.IOException;
import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;
import serialized.*;

public class ServerThread extends Thread{
    
    private String multicastAddr="230.1.1.1";
    private int port=4545;
    private InetAddress addr,hostAddr;
    private DatagramSocket serverSocket;
    private ByteArrayOutputStream baos;
    private ObjectOutputStream oos;
    
    @Override
    public void run(){
        
        try{
            
            addr=InetAddress.getByName(multicastAddr);
            hostAddr=InetAddress.getLocalHost();
            serverSocket=new DatagramSocket();
            
            while(true){
               //Creating byte array
               baos=new ByteArrayOutputStream();
               //Creating object output stream to send in byte array
               oos=new ObjectOutputStream(baos);
               //Write object
               oos.writeObject(new NetUser(hostAddr.getHostAddress(),hostAddr.getHostName()));
               //converting object to byte array
               byte[] dataByte=baos.toByteArray();
               //Send object through multicast
               serverSocket.send(new DatagramPacket(dataByte,dataByte.length,addr,port));
               baos.close();
               oos.close();
               this.sleep(5000);
              
           }
           
        }
        
        catch(UnknownHostException ue){
            
            ue.printStackTrace();
            javax.swing.JOptionPane.showMessageDialog(null,ue,"Error",javax.swing.JOptionPane.ERROR_MESSAGE);
            
        }
        
        catch(IOException ie){
            
            ie.printStackTrace();
            javax.swing.JOptionPane.showMessageDialog(null,ie,"Error",javax.swing.JOptionPane.ERROR_MESSAGE);
            
        }
        
        catch(InterruptedException ie){
            
            ie.printStackTrace();
            javax.swing.JOptionPane.showMessageDialog(null,ie,"Error",javax.swing.JOptionPane.ERROR_MESSAGE);
            
        }
        
        
    }
    
}
