/* 
 * Copyright (C) Jonathan Lazar 2019-Present
 * All Rights Reserved 2019
 */
package nathChatEntities;


import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.UnknownHostException;
import java.net.DatagramPacket;
import java.io.IOException;
import java.io.ByteArrayInputStream;
import java.io.ObjectInputStream;
import java.lang.InterruptedException;
import nathChatUI.NathChatClient;
import serialized.*;

/**
 *
 * @author KNSI EXT1
 */
public class ClientThread extends Thread{
    
    private String multicastAddr="230.1.1.1";
    private int port=4545;
    private InetAddress addr;
    private MulticastSocket clientSocket;
    private ByteArrayInputStream bais;
    private ObjectInputStream ois;
    
    
    @Override
    public void run(){
        
        try{
            
            addr = InetAddress.getByName(multicastAddr);
            clientSocket = new MulticastSocket(port);
            clientSocket.joinGroup(addr);
            
            while(true){
                
                byte[] buf=new byte[1024*4];
                //Receiving object in byte array and filling buf with object's byte array
                clientSocket.receive(new DatagramPacket(buf,buf.length));
                //Read buf as input stream
                bais=new ByteArrayInputStream(buf);
                //Byte array in input stream deserialized as object
                ois=new ObjectInputStream(bais);
                //cast to NetUser
                NetUser netUser=(NetUser)ois.readObject();
                if(!netUser.getHostName().equals(InetAddress.getLocalHost().getHostName())){
                    
                    if(!NathChatClient.core.getDetectedUserList().isEmpty()){
                        
                        boolean notListed=false;
                        int pos=0;
                        
                        for(int i=0;i<NathChatClient.core.getDetectedUserList().size();i++){
                            
                            pos=i;
                            
                            if(NathChatClient.core.getDetectedUserList().get(i).getHostName().equals(netUser.getHostName())){
                                
                                notListed=false;
                                break;
                                
                            }
                            
                            else{
                                
                                notListed=true;
                                
                            }
                            
                        }
                        
                        if(notListed){
                            
                            netUser.setUserStatus(Constants.USER_ON);
                            NathChatClient.core.getDetectedUserList().add(netUser);
                            NathChatClient.core.addToUsersList(netUser);
                            
                        }
                        
                        else{
                            
                            NathChatClient.core.getDetectedUserList().get(pos).setUserStatus(Constants.USER_ON);
                            
                        }
                        
                    }
                    else{
                        
                        netUser.setUserStatus(Constants.USER_ON);
                        NathChatClient.core.getDetectedUserList().add(netUser);
                        NathChatClient.core.addToUsersList(netUser);
                        
                    }
                }
                
                bais.close();
                ois.close();
                this.sleep(1000);
                
            }
            
        }
        
        catch(UnknownHostException ue){
            
            ue.printStackTrace();
            javax.swing.JOptionPane.showMessageDialog(null,ue,"Error",javax.swing.JOptionPane.ERROR_MESSAGE);
            
        }
        
        catch(IOException ioe){
            
            ioe.printStackTrace();
            javax.swing.JOptionPane.showMessageDialog(null,ioe,"Error",javax.swing.JOptionPane.ERROR_MESSAGE);
            
        }
        
        catch(ClassNotFoundException ce){
            
            ce.printStackTrace();
            javax.swing.JOptionPane.showMessageDialog(null,ce,"Error",javax.swing.JOptionPane.ERROR_MESSAGE);
            
        }
        
        catch(InterruptedException ie){
            
            ie.printStackTrace();
            javax.swing.JOptionPane.showMessageDialog(null,ie,"Error",javax.swing.JOptionPane.ERROR_MESSAGE);
            
        }
        
    }
    
    
}
