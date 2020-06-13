/* 
 * Copyright (C) Jonathan Lazar 2019-Present
 * All Rights Reserved 2019
 */
package nathChatUI;
//<editor-fold defaultstate="collapsed">

import nathChatUI.groupListPanel.GroupListTreePopup;
import nathChatUI.groupListPanel.GroupListTree;
import nathChatUI.addGroupDialog.AddGroupDialog;
import nathChatUI.composedMessagePanel.ComposeMessagePanel;
import nathChatUI.mainWindow.ClientMainWindow;
import nathChatController.Core;
import nathChatEntities.UserListOffThread;
import nathChatEntities.ClientThread;
import nathChatEntities.ServerThread;
import nathChatEntities.ReceiverThread;

import java.awt.*;
import java.util.*;
import javax.swing.JLabel;
import nathChatEntities.ReceiveNotifThread;
import nathChatEntities.StringNotif;
import serialized.*;

//</editor-fold>

/**
 *
 * @author Anjon Rolo Lazar
 */
public class NathChatClient{
    //<editor-fold defaultstate="collapsed">
    
    public static ClientMainWindow clientMainWindow;
    public static AddGroupDialog addGroupDialog;
    public static Dimension screenSize=Toolkit.getDefaultToolkit().getScreenSize();
    public static Rectangle windowSize=GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds();
    public static String path;
    public static Core core=new Core();
    public static String userSessionId="0";
    public static String deviceName;
    public static ArrayList<NetUser> detectedUserList=new ArrayList<NetUser>();
    public static HashMap<String,ComposeMessagePanel> composeMessageList=new HashMap<String,ComposeMessagePanel>();
    public static final ServerThread serverThread=new ServerThread();
    public static final ClientThread clientThread=new ClientThread();
    public static final UserListOffThread userListOffThread=new UserListOffThread();
    public static final ReceiverThread receiverThread=new ReceiverThread();
    public static final ReceiveNotifThread receiveNotifThread=new ReceiveNotifThread();
    public static int composeMessageDialogCount=0;
    public static GroupListTree groupListTree;
    public static GroupListTreePopup groupListTreePopup;
    public static StringNotif stringNotif;
    public static JLabel notifLabelRef;
    
    //</editor-fold>
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws java.io.IOException{
        
        deviceName=java.net.InetAddress.getLocalHost().getHostName();
        stringNotif=new StringNotif();
        
        //Run by JAR file
        //java.io.File file=new java.io.File(System.getProperty("user.dir")+"/..");
        //path=file.getCanonicalPath()+"\\assets";
        
        //Run by IDE
        java.io.File file=new java.io.File(System.getProperty("user.dir"));
        path=file.getCanonicalPath()+"\\assets";
        groupListTree=new GroupListTree(core.getGroupTree());
        groupListTreePopup=new GroupListTreePopup(groupListTree);
        
        try{
            
            SplashWindow splashWindow=new SplashWindow();
            splashWindow.setVisible(true);
            Thread.sleep(3000);
            splashWindow.setVisible(false);
            splashWindow.dispose();
            NathChatClient.clientThread.start();
            NathChatClient.serverThread.start();
            NathChatClient.userListOffThread.start();
            clientMainWindow=new ClientMainWindow();
            addGroupDialog=new AddGroupDialog(clientMainWindow,true);
            receiverThread.start();
            receiveNotifThread.start();
        
        }
        catch(InterruptedException e){
            
            e.printStackTrace();
            
        } 
        
    }
    
}
