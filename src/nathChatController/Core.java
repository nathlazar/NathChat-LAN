/* 
 * Copyright (C) Jonathan Lazar 2019-Present
 * All Rights Reserved 2019
 */
package nathChatController;

//<editor-fold defaultstate="collapsed">

import nathChatEntities.Constants;
import nathChatEntities.GroupLayoutManager;
import nathChatEntities.TextPaneElement;
import nathChatEntities.CellRenderer;
import nathChatUI.NathChatClient;
import nathChatUI.composedMessagePanel.ComposeMessagePanel;
import jbl.javaDev.swingSupport.*;
import serialized.NetUser;
import java.util.ArrayList;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.StyledDocument;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.BadLocationException;
import java.util.HashMap;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;
import java.io.File;
import java.io.IOException;
import javax.swing.text.StyleConstants;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import nathChatEntities.NotificationWrapper;
import nathChatUI.dynamicUI.NotificationBlock;

//</editor-fold>

/**
 *
 * @author nathan
 */
public class Core {
    
    //Engine for managing chat users
    //<editor-fold defaultstate="collapsed">
    public synchronized ArrayList<NetUser> getDetectedUserList(){
        //<editor-fold defaultstate="collapsed">
        
        return NathChatClient.detectedUserList;
        
        //</editor-fold>
    }
       
    public void usersListUpdater(javax.swing.JPanel viewPort,int scrollPaneWidth,javax.swing.Timer usersListUpdaterTimer){
        //<editor-fold defaultstate="collapsed">
        
        viewPort.removeAll();
        int count=0;
        java.util.ArrayList<javax.swing.JLabel> jlabelArray=new java.util.ArrayList<javax.swing.JLabel>();
        java.util.ArrayList<String> fetchedUsersList=NathChatClient.core.fetchUsersList();
        
        for(int i=0;i<fetchedUsersList.size();i++){
            
            JLabel label,label1,label2,label3,label4;
            ImageIcon statusLabelIcon,menuLabelIcon;
            boolean online=false;
            
            for(int j=0;j<NathChatClient.core.getDetectedUserList().size();j++){
                
                if(fetchedUsersList.get(i).equals(NathChatClient.core.getDetectedUserList().get(j).getHostName())&&
                   NathChatClient.core.getDetectedUserList().get(j).getUserStatus()==Constants.USER_ON){
                    
                    online=true;
                    break;
                    
                }
                
            }
            if(online){
                
                statusLabelIcon=new ImageIcon(NathChatClient.path+"\\icons\\online-icon.png");
                
            }
            else{
                
                statusLabelIcon=new ImageIcon(NathChatClient.path+"\\icons\\offline-icon.png");
                
            }
            
            label=new JLabel();
            label1=new JLabel();
            label2=new JLabel(fetchedUsersList.get(i));
            label3=new JLabel();
            
            Image statusLabelImage=statusLabelIcon.getImage().getScaledInstance(25,25,Image.SCALE_SMOOTH);
            statusLabelIcon.setImage(statusLabelImage);
            label1.setIcon(statusLabelIcon);
            
            menuLabelIcon=new ImageIcon(NathChatClient.path+"\\icons\\menu-dots-icon.png");
            Image menuLabelImage=menuLabelIcon.getImage().getScaledInstance(18,18,Image.SCALE_SMOOTH);
            menuLabelIcon.setImage(menuLabelImage);
            label3.setIcon(menuLabelIcon);
            
            label.setLayout(new BoxLayout(label,BoxLayout.X_AXIS));
            label.setBounds(5,count*30,228,30);
            label.add(label1);
            label.add(Box.createRigidArea(new Dimension(10,0)));
            label.add(label2);
            label.add(Box.createRigidArea(new Dimension(((228-(25+10+label2.getPreferredSize().width))-30),0)));
            label.add(label3);
            //label.setBorder(javax.swing.BorderFactory.createLineBorder(java.awt.Color.BLACK,1));
            //label1.setBorder(javax.swing.BorderFactory.createLineBorder(java.awt.Color.BLACK,1));
            //label2.setBorder(javax.swing.BorderFactory.createLineBorder(java.awt.Color.BLACK,1));
            //label3.setBorder(javax.swing.BorderFactory.createLineBorder(java.awt.Color.BLACK,1));
            label.addMouseListener(
                    new MouseAdapter(){
                        
                        @Override
                        public void mouseClicked(MouseEvent e){
                            
                            if(e.getClickCount()==2){
                                
                                if(NathChatClient.composeMessageList.get(label2.getText())==null){
                                    
                                    addMessagePanel(label2.getText());     
                                    NathChatClient.core.composeMessageDialogAutoAlign(
                                        NathChatClient.composeMessageList.get(label2.getText()).getAnimatedDiag()
                                    );
                                    
                                }
                                else{
                                    
                                    if(!NathChatClient.composeMessageList.get(label2.getText()).getAnimatedDiag().isVisible()){
                                        
                                        NathChatClient.core.composeMessageDialogAutoAlign(
                                            NathChatClient.composeMessageList.get(label2.getText()).getAnimatedDiag()
                                        );
                                        
                                    }
                                    else{
                                        
                                        NathChatClient.composeMessageDialogCount--;
                                        
                                    }
                                    
                                }
                                    
                                NathChatClient.composeMessageList.get(label2.getText()).toggleMessagePanel();
                                
                            }
                            
                        }
                        
                    }
            );
            
            jlabelArray.add(label);
            count++;

        }
        
        for(int i=0;i<jlabelArray.size();i++){

            viewPort.add(jlabelArray.get(i));

        }

        viewPort.setPreferredSize(new java.awt.Dimension(scrollPaneWidth,count*30));
        viewPort.repaint();
        viewPort.revalidate();
        
        synchronized(NathChatClient.userListOffThread){
            
            NathChatClient.userListOffThread.notify();
            
        }
         
        //</editor-fold>
    }
    
    public void composeMessageDialogAutoAlign(AnimatedDiag diag){
        //<editor-fold defaultstate="collapsed">
        
        if(NathChatClient.composeMessageDialogCount!=0){
            
            int spaceAllocated=0;
            java.util.ArrayList<String> fetchedUsersList=NathChatClient.core.fetchUsersList();
            
            for(int i=0;i<fetchedUsersList.size();i++){
                
                if(NathChatClient.composeMessageList.get(fetchedUsersList.get(i))!=null){
                    
                    if(NathChatClient.composeMessageList.get(fetchedUsersList.get(i)).getAnimatedDiag().isVisible()){
                    
                        spaceAllocated+=(NathChatClient.composeMessageList.get(fetchedUsersList.get(i)).getAnimatedDiag().getWidth());
                    
                    }
                    
                }
                
            }
                                            
            if(!((NathChatClient.windowSize.width-240)<=(spaceAllocated+440))){
                                                
                diag.setFinalPoint(new Point(
                        NathChatClient.windowSize.width-(NathChatClient.clientMainWindow.getWidth()+(spaceAllocated+440))
                        ,NathChatClient.windowSize.height-371));
                                                
            }
            else{
                
                //diag.setFinalPoint(new Point(0,0));
                
            }
                                            
        }
                                    
        NathChatClient.composeMessageDialogCount++;
        
        //</editor-fold>
    }
    
    public ArrayList<String> fetchUsersList(){
        //<editor-fold defaultstate="collapsed">
        
        ArrayList<String> usersList;
        
        if(new File(NathChatClient.path+"\\data\\sys\\usersList.xml").exists()){
            
            usersList=new ArrayList<String>();
            
            try{
                
                DocumentBuilderFactory dbf=DocumentBuilderFactory.newInstance();
                DocumentBuilder db=dbf.newDocumentBuilder();
                Document doc;
                doc=db.parse(new File(NathChatClient.path+"\\data\\sys\\usersList.xml"));
                doc.getDocumentElement().normalize();
                NodeList nodeList=doc.getDocumentElement().getElementsByTagName("hostName");
                
                for(int i=0;i<nodeList.getLength();i++){
                    
                    Node node=nodeList.item(i);
                    usersList.add(node.getTextContent());
                    
                }
                
                return usersList;
                
            }
            catch(SAXException|IOException|ParserConfigurationException e){
             
                e.printStackTrace();
                
                return null;
                
            }
            
        }
        else{
            
            return null;
            
        }
        
        //</editor-fold>
    }
    
    public void addToUsersList(NetUser netUser){
        //<editor-fold defaultstate="collapsed">
        
        try{
            
            DocumentBuilderFactory dbf=DocumentBuilderFactory.newInstance();
            DocumentBuilder db=dbf.newDocumentBuilder();
            Document doc;
            Element root;
            
            if(!new File(NathChatClient.path+"\\data\\sys\\usersList.xml").exists()){
                
                doc=db.newDocument();
                root=doc.createElement("users");
                doc.appendChild(root);
                Element hostName=doc.createElement("hostName");
                hostName.appendChild(doc.createTextNode(netUser.getHostName()));
                root.appendChild(hostName);
                
            }
            else{
                
                doc=db.parse(new File(NathChatClient.path+"\\data\\sys\\usersList.xml"));
                root=doc.getDocumentElement();
                NodeList nodeList=root.getChildNodes();
                boolean listed=false;
                
                for(int i=0;i<nodeList.getLength();i++){
                    
                    Node node=nodeList.item(i);
                    
                    if(node.getTextContent().equals(netUser.getHostName())){
                        
                        listed=true;
                        break;
                        
                    }
                    
                }
                
                if(!listed){
                    
                    Element hostName=doc.createElement("hostName");
                    root.appendChild(hostName);
                    hostName.appendChild(doc.createTextNode(netUser.getHostName()));
                    
                }
                
            }
            
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(javax.xml.transform.OutputKeys.INDENT, "yes");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "1");
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File(NathChatClient.path+"\\data\\sys\\usersList.xml"));
            transformer.transform(source, result);
            
        }
        catch(ParserConfigurationException|TransformerException|SAXException|IOException e){
            
            e.printStackTrace();
            
        }
        
        //</editor-fold>
    }
    
    public void addMessagePanel(String userKey){
        //<editor-fold defaultstate="collapsed">
        
        AnimatedDiag diag;
        diag=new AnimatedDiag(new Point((NathChatClient.windowSize.width-440)
                -NathChatClient.clientMainWindow.getWidth(),NathChatClient.windowSize.height-371));
        diag.setLocation(new Point(NathChatClient.clientMainWindow.getLocationOnScreen().x,NathChatClient.clientMainWindow.getLocationOnScreen().y));
        diag.setSize(440,371);
        ((JPanel)diag.getContentPane()).setBorder(BorderFactory.createLineBorder(Color.decode("#3C99FF"),1));
        
        UndecoratedResize undecoratedResize=new UndecoratedResize(diag);
        
        ComposeMessagePanel composeMessagePanel=new ComposeMessagePanel(diag,userKey);
        
        GroupLayout diagLayout=new GroupLayout(diag.getContentPane());
        diag.getContentPane().setLayout(diagLayout);
        diagLayout.setHorizontalGroup(
            diagLayout.createSequentialGroup()
            .addContainerGap(0,20)
            .addComponent(composeMessagePanel,100,javax.swing.GroupLayout.PREFERRED_SIZE,Short.MAX_VALUE)
            .addContainerGap(0,20)
        );
        diagLayout.setVerticalGroup(
           
            diagLayout.createSequentialGroup()
                .addComponent(composeMessagePanel,100,javax.swing.GroupLayout.PREFERRED_SIZE,Short.MAX_VALUE)
                .addContainerGap(0,20) 
        );
        
        NathChatClient.composeMessageList.put(userKey,composeMessagePanel);
        
        //</editor-fold>
    }
    //</editor-fold>
    
    //Engine for group list display
    //<editor-fold defaultstate="collapsed">
    public boolean addToGroupList(String groupName,String grpID,String creator){
        //<editor-fold defaultstate="collapsed">
        
        try{
           
           DocumentBuilderFactory dbf=DocumentBuilderFactory.newInstance();
           DocumentBuilder db=dbf.newDocumentBuilder();
           Document doc;
           Element root;
           Attr attr;
           
           if(!new File(NathChatClient.path+"\\data\\sys\\groupList.xml").exists()){
               
               doc=db.newDocument();
               root=doc.createElement("groupList");
               doc.appendChild(root);
               
               
           }
           else{
               
               doc=db.parse(new File(NathChatClient.path+"\\data\\sys\\groupList.xml"));
               root=doc.getDocumentElement();
               
           }
            
           Element group=doc.createElement("group");
           attr=doc.createAttribute("groupID");
           attr.setValue(grpID);
           group.setAttributeNode(attr);
           attr=doc.createAttribute("status");
           attr.setValue("active");
           group.setAttributeNode(attr);
           attr=doc.createAttribute("creator");
           attr.setValue(creator);
           group.setAttributeNode(attr);
           Element gName=doc.createElement("groupName");
           gName.setTextContent(groupName);
           group.appendChild(gName);
           root.appendChild(group);  
           
           TransformerFactory transformerFactory = TransformerFactory.newInstance();
           Transformer transformer = transformerFactory.newTransformer();
           transformer.setOutputProperty(javax.xml.transform.OutputKeys.INDENT, "yes");
           transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "1");
           DOMSource source = new DOMSource(doc);
           StreamResult result = new StreamResult(new File(NathChatClient.path+"\\data\\sys\\groupList.xml"));
           transformer.transform(source, result);
           return true;
           
        }
        catch(ParserConfigurationException|TransformerException|SAXException|IOException ex){
            
            ex.printStackTrace();
            return false;
        }
        
        //</editor-fold>
    }
    
    public void displayGroupList(JTable table){
        //<editor-fold defaultstate="collapsed">
        
        if(new File(NathChatClient.path+"\\data\\sys\\groupList.xml").exists()){
            
            DefaultTableModel dftm=new DefaultTableModel(new Object[]{"Group"},0);
            
            try{
                
                DocumentBuilderFactory dbf=DocumentBuilderFactory.newInstance();
                DocumentBuilder db=dbf.newDocumentBuilder();
                Document doc=db.parse(new File(NathChatClient.path+"\\data\\sys\\groupList.xml"));
                NodeList nodeList=doc.getDocumentElement().getElementsByTagName("group");
                for(int i=0;i<nodeList.getLength();i++){
                    
                    NodeList nl=((Element)nodeList.item(i)).getElementsByTagName("groupName");
                    
                    for(int j=0;j<nl.getLength();j++){
                        
                        Node node=nl.item(j);
                        dftm.addRow(new Object[]{node.getTextContent()});
                        
                    }
                    
                            
                }
                
                table.setModel(dftm);
                
            }
            catch(ParserConfigurationException|SAXException|IOException ex){
                
                ex.printStackTrace();
                
            }
            
        }
        table.getColumnModel().getColumn(0).setCellRenderer(new CellRenderer());
        //</editor-fold>
    }
    
    public void searchUsers(JTable table,String searchVal){
        //<editor-fold defaultstate="collapsed">
        
        ArrayList<String> usersArray=fetchUsersList();
        DefaultTableModel dftm=new DefaultTableModel(new Object[]{"Group"},0);
       
        for(int i=0;i<usersArray.size();i++){
            
            if((usersArray.get(i).contains(searchVal))
                ||(usersArray.get(i).contains(searchVal.toUpperCase()))){
                
                dftm.addRow(new Object[]{usersArray.get(i)});
                
            }
            
        }
        
        table.setModel(dftm);
        table.getColumnModel().getColumn(0).setCellRenderer(new CellRenderer());
        //</editor-fold>
    }
    
    public DefaultMutableTreeNode getGroupTree(){
        //<editor-fold defaultstate="collapsed">
        
        DefaultMutableTreeNode treeNode=new DefaultMutableTreeNode("Groups");
        
        if(new File(NathChatClient.path+"\\data\\sys\\groupList.xml").exists()){
                
            try{
                
                DocumentBuilderFactory dbf=DocumentBuilderFactory.newInstance();
                DocumentBuilder db=dbf.newDocumentBuilder();
                Document doc=db.parse(new File(NathChatClient.path+"\\data\\sys\\groupList.xml"));
                NodeList nodeList=doc.getDocumentElement().getElementsByTagName("group");
                
                for(int i=0;i<nodeList.getLength();i++){
                    
                    NodeList n1=((Element)nodeList.item(i)).getElementsByTagName("groupName");
                    
                    for(int j=0;j<n1.getLength();j++){
                        
                        Node node=n1.item(j);
                        treeNode.add(new DefaultMutableTreeNode(node.getTextContent()));
                        
                    }
                    
                }
                
            }
            catch(ParserConfigurationException|SAXException|IOException ex){
                
                ex.printStackTrace();
                
            }
                
        }
        else{
                
        }
        
        return treeNode;
        
        //</editor-fold>
    }
    
    public void addNodeToGroupTree(String node){
        //<editor-fold defaultstate="collapsed">
        
        DefaultTreeModel model=(DefaultTreeModel)NathChatClient.groupListTree.getModel();
        DefaultMutableTreeNode root=(DefaultMutableTreeNode)model.getRoot();
        root.add(new DefaultMutableTreeNode(node));
        model.reload();
        
        //</editor-fold>
    }
    
    public void reloadGroupTree(){
        //<editor-fold defaultstate="collapsed">
        
        DefaultTreeModel model=(DefaultTreeModel)NathChatClient.groupListTree.getModel();
        model.reload();
        
        //</editor-fold>
    }
    //</editor-fold>
    
    // Engine for messaging system
    //<editor-fold defaultstate="collapsed">
    public synchronized void writeToXML(HashMap<Integer,TextPaneElement> tpe,String userKey,Constants type,String dateTimeStr,String msgID){
        //<editor-fold defaultstate="collapsed">
        
        try{
        
            DocumentBuilderFactory dbf=DocumentBuilderFactory.newInstance();
            DocumentBuilder db=dbf.newDocumentBuilder();
            Document doc;
            Attr attr;
            Element root;
            String status="";
            
            if(!new File(NathChatClient.path+"\\data\\conv\\indv\\"+userKey+".conv").exists()){

                doc=db.newDocument();
                    
                root=doc.createElement("conversation");
                doc.appendChild(root);
                
                attr=doc.createAttribute("assoc-user");
                attr.setValue(userKey);
                root.setAttributeNode(attr);

            }

            else{
               
                doc = db.parse(new File(NathChatClient.path+"\\data\\conv\\indv\\"+userKey+".conv"));
                root=doc.getDocumentElement();
                    
            }
            
            Element message=doc.createElement("message");
            attr=doc.createAttribute("user");
            attr.setValue(userKey);
            message.setAttributeNode(attr);
            attr=doc.createAttribute("type");

            switch(type){

                case MESSAGE_TYPE_SENDER:
                    attr.setValue("sender");
                    status="sent";
                    break;

                case MESSAGE_TYPE_RECEIVER:
                    attr.setValue("receiver");
                     status="unseen";
                    break;
            }

            message.setAttributeNode(attr);

            attr=doc.createAttribute("id");
            attr.setValue(msgID);
            message.setAttributeNode(attr);
            
            attr=doc.createAttribute("status");
            attr.setValue(status);
            message.setAttributeNode(attr);

            root.appendChild(message);

            Element dateTime=doc.createElement("date-time");
            dateTime.appendChild(doc.createTextNode(dateTimeStr));
            message.appendChild(dateTime);

            Element body=doc.createElement("body");
            message.appendChild(body);

            Constants[] currentBIStatus={Constants.BOLD_UNSET,Constants.ITALIC_UNSET};
            Constants currentFontFamily=Constants.FONT_FAM_UNSET;
            boolean isNewLineCreated=false;

            for(int i:tpe.keySet()){

                Element node;
                
                if(tpe.get(i).getTPEString().equals("\\n")){
                    
                    node=doc.createElement("new-line");
                    body.appendChild(node);
                    isNewLineCreated=true;
                    
                }
                
                else if(tpe.get(i).getTPEBIStatus()[0]==Constants.BOLD_ON&&tpe.get(i).getTPEBIStatus()[1]==Constants.ITALIC_ON){

                    if((currentBIStatus[0]==Constants.BOLD_ON&&currentBIStatus[1]==Constants.ITALIC_ON)&&(currentFontFamily==tpe.get(i).getTPEFontFamily())&&(!isNewLineCreated)){

                        body.getLastChild().setTextContent(body.getLastChild().getTextContent()+tpe.get(i).getTPEString());

                    }

                    else{

                        node=doc.createElement("string");
                        node.appendChild(doc.createTextNode(tpe.get(i).getTPEString()));
                        attr=doc.createAttribute("font-family");
                        attr.setValue(tpe.get(i).getTPEFontFamily().toString());
                        node.setAttributeNode(attr);
                        attr=doc.createAttribute("bold");
                        attr.setValue(tpe.get(i).getTPEBIStatus()[0].name());
                        node.setAttributeNode(attr);
                        attr=doc.createAttribute("italic");
                        attr.setValue(tpe.get(i).getTPEBIStatus()[1].name());
                        node.setAttributeNode(attr);
                        currentBIStatus[0]=Constants.BOLD_ON;
                        currentBIStatus[1]=Constants.ITALIC_ON;
                        body.appendChild(node);
                        currentFontFamily=tpe.get(i).getTPEFontFamily();
                        isNewLineCreated=false;
                       
                     }

                }

                else if(tpe.get(i).getTPEBIStatus()[0]==Constants.BOLD_ON&&tpe.get(i).getTPEBIStatus()[1]==Constants.ITALIC_OFF){

                    if((currentBIStatus[0]==Constants.BOLD_ON&&currentBIStatus[1]==Constants.ITALIC_OFF)&&(currentFontFamily==tpe.get(i).getTPEFontFamily())&&(!isNewLineCreated)){

                        body.getLastChild().setTextContent(body.getLastChild().getTextContent()+tpe.get(i).getTPEString());

                    }

                    else{

                        node=doc.createElement("string");
                        node.appendChild(doc.createTextNode(tpe.get(i).getTPEString()));
                        attr=doc.createAttribute("font-family");
                        attr.setValue(tpe.get(i).getTPEFontFamily().toString());
                        node.setAttributeNode(attr);
                        attr=doc.createAttribute("bold");
                        attr.setValue(tpe.get(i).getTPEBIStatus()[0].name());
                        node.setAttributeNode(attr);
                        attr=doc.createAttribute("italic");
                        attr.setValue(tpe.get(i).getTPEBIStatus()[1].name());
                        node.setAttributeNode(attr);
                        currentBIStatus[0]=Constants.BOLD_ON;
                        currentBIStatus[1]=Constants.ITALIC_OFF;
                        body.appendChild(node);
                        currentFontFamily=tpe.get(i).getTPEFontFamily();
                        isNewLineCreated=false;
                        
                    }

                }

                else if(tpe.get(i).getTPEBIStatus()[0]==Constants.BOLD_OFF&&tpe.get(i).getTPEBIStatus()[1]==Constants.ITALIC_ON){

                    if((currentBIStatus[0]==Constants.BOLD_OFF&&currentBIStatus[1]==Constants.ITALIC_ON)&&(currentFontFamily==tpe.get(i).getTPEFontFamily())&&(!isNewLineCreated)){

                        body.getLastChild().setTextContent(body.getLastChild().getTextContent()+tpe.get(i).getTPEString());

                    }

                    else{

                        node=doc.createElement("string");
                        node.appendChild(doc.createTextNode(tpe.get(i).getTPEString()));
                        attr=doc.createAttribute("font-family");
                        attr.setValue(tpe.get(i).getTPEFontFamily().toString());
                        node.setAttributeNode(attr);
                        attr=doc.createAttribute("bold");
                        attr.setValue(tpe.get(i).getTPEBIStatus()[0].name());
                        node.setAttributeNode(attr);
                        attr=doc.createAttribute("italic");
                        attr.setValue(tpe.get(i).getTPEBIStatus()[1].name());
                        node.setAttributeNode(attr);
                        currentBIStatus[0]=Constants.BOLD_OFF;
                        currentBIStatus[1]=Constants.ITALIC_ON;
                        body.appendChild(node);
                        currentFontFamily=tpe.get(i).getTPEFontFamily();
                        isNewLineCreated=false;
                        
                    }

                }

                else{

                    if((currentBIStatus[0]==Constants.BOLD_OFF&&currentBIStatus[1]==Constants.ITALIC_OFF)&&(currentFontFamily==tpe.get(i).getTPEFontFamily())&&(!isNewLineCreated)){

                        body.getLastChild().setTextContent(body.getLastChild().getTextContent()+tpe.get(i).getTPEString());

                    }

                    else{

                        node=doc.createElement("string");
                        node.appendChild(doc.createTextNode(tpe.get(i).getTPEString()));
                        attr=doc.createAttribute("font-family");
                        attr.setValue(tpe.get(i).getTPEFontFamily().toString());
                        node.setAttributeNode(attr);
                        attr=doc.createAttribute("bold");
                        attr.setValue(tpe.get(i).getTPEBIStatus()[0].name());
                        node.setAttributeNode(attr);
                        attr=doc.createAttribute("italic");
                        attr.setValue(tpe.get(i).getTPEBIStatus()[1].name());
                        node.setAttributeNode(attr);
                        currentBIStatus[0]=Constants.BOLD_OFF;
                        currentBIStatus[1]=Constants.ITALIC_OFF;
                        body.appendChild(node);
                        currentFontFamily=tpe.get(i).getTPEFontFamily();
                        isNewLineCreated=false;

                    }

                }
                
            }

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(javax.xml.transform.OutputKeys.INDENT, "yes");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "1");
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File(NathChatClient.path+"\\data\\conv\\indv\\"+userKey+".conv"));
            transformer.transform(source, result);
            
        }
        catch(ParserConfigurationException|TransformerException|SAXException|IOException ex){
            
            ex.printStackTrace();
            
        }
        
        //</editor-fold>
    }
    
    public void convFetcher(String userKey,GroupLayoutManager<GroupLayout.SequentialGroup,GroupLayout.ParallelGroup> groupLayoutManager){
        //<editor-fold defaultstate="collapsed">
        
        if(new File(NathChatClient.path+"\\data\\conv\\indv\\"+userKey+".conv").exists()){
            
            try{
                
                DocumentBuilderFactory dbf=DocumentBuilderFactory.newInstance();
                DocumentBuilder db=dbf.newDocumentBuilder();
                Document doc=db.parse(new File(NathChatClient.path+"\\data\\conv\\indv\\"+userKey+".conv"));
                doc.getDocumentElement().normalize();
                NodeList nodeList=doc.getDocumentElement().getElementsByTagName("message");
                for(int i=0;i<nodeList.getLength();i++){
                    
                    NathChatClient.core.parseXMLMsg(nodeList.item(i),groupLayoutManager);
                            
                }
                
            }
            catch(ParserConfigurationException|SAXException|IOException ex){
            
                ex.printStackTrace();
            
            }
             
        }
        
        //</editor-fold>
    }
    
    public synchronized void getNewMessage(GroupLayoutManager<GroupLayout.SequentialGroup,GroupLayout.ParallelGroup> groupLayoutManager,String userKey){
        //<editor-fold defaultstate="collapsed">
        
        try{
            
            DocumentBuilderFactory dbf=DocumentBuilderFactory.newInstance();
            DocumentBuilder db=dbf.newDocumentBuilder();
            Document doc = db.parse(new File(NathChatClient.path+"\\data\\conv\\indv\\"+userKey+".conv"));
            doc.getDocumentElement().normalize();
            NodeList nodeList=doc.getDocumentElement().getElementsByTagName("message");
         
            NathChatClient.core.parseXMLMsg(nodeList.item(nodeList.getLength()-1),
                                            NathChatClient.composeMessageList.get(userKey).getGLM());
            
        }
        catch(ParserConfigurationException|SAXException|IOException e){
            
            e.printStackTrace();
            
        }
        
        //</editor-fold>
    }
    
    private void parseXMLMsg(Node nodeF,GroupLayoutManager<GroupLayout.SequentialGroup,GroupLayout.ParallelGroup> groupLayoutManager){
        //<editor-fold defaultstate="collapsed">
        
        NodeList nodeSList=nodeF.getChildNodes();
        JTextPane textPane=new JTextPane();
        textPane.setEditable(false);
                            
        GroupLayout.SequentialGroup sGroup=groupLayoutManager.getLayout().createSequentialGroup();
        GroupLayout.ParallelGroup pGroup=groupLayoutManager.getLayout().createParallelGroup(GroupLayout.Alignment.LEADING);
        
        for(int j=0;j<nodeSList.getLength();j++){
                        
            Node nodeS=nodeSList.item(j);

            if(nodeS.getNodeName().equals("body")){
                            
                NodeList nodeList=nodeS.getChildNodes();
                StyledDocument doc=textPane.getStyledDocument();
                SimpleAttributeSet attrSet=new SimpleAttributeSet();
                
                javax.swing.SwingUtilities.invokeLater(
                    new Runnable(){
                            
                        @Override
                            
                        public void run(){
                                
                            for(int i=0;i<nodeList.getLength();i++){
                        
                                NathChatClient.core.parseXMLBody(textPane,nodeList.item(i),doc,attrSet);
                        
                            }
                            
                        }
                            
                });
                            
            }
                        
        }

        if(((Element)nodeF).getAttribute("type").equals("sender")){

            textPane.setBackground(Color.decode("#0084FF"));
            textPane.setForeground(Color.WHITE);
            sGroup.addContainerGap(0,Short.MAX_VALUE);
            sGroup.addComponent(textPane,20,GroupLayout.PREFERRED_SIZE,GroupLayout.PREFERRED_SIZE);
            sGroup.addGap(10);

        }

        else{

            textPane.setBackground(Color.decode("#FFFFFF"));
            JLabel userLabel=new JLabel();
            userLabel.setPreferredSize(new Dimension(25,25));

            javax.swing.ImageIcon userIcon=new javax.swing.ImageIcon(NathChatClient.path+"\\icons\\user-icon.png");
            java.awt.Image userImage=userIcon.getImage().getScaledInstance(25,25,java.awt.Image.SCALE_SMOOTH);
            userIcon.setImage(userImage);
            userLabel.setIcon(userIcon);

            sGroup.addComponent(userLabel,GroupLayout.PREFERRED_SIZE,GroupLayout.PREFERRED_SIZE,GroupLayout.PREFERRED_SIZE);
            sGroup.addContainerGap(5,5);
            sGroup.addComponent(textPane,20,GroupLayout.PREFERRED_SIZE,GroupLayout.PREFERRED_SIZE);
            pGroup.addComponent(userLabel,GroupLayout.PREFERRED_SIZE,GroupLayout.PREFERRED_SIZE,GroupLayout.PREFERRED_SIZE);

        }
            
        pGroup.addComponent(textPane,20,GroupLayout.PREFERRED_SIZE,GroupLayout.PREFERRED_SIZE);
        groupLayoutManager.getHGroup().addGroup(sGroup);
        groupLayoutManager.getVGroup().addGroup(pGroup);
        groupLayoutManager.getVGroup().addGap(20);
        
        //</editor-fold>
    }
    
    private void parseXMLBody(JTextPane textPane,Node node,StyledDocument doc,SimpleAttributeSet attrSet){
        //<editor-fold defaultstate="collapsed">   
        
        String s="";
        
        if(node.getNodeType()==Node.ELEMENT_NODE){
                
            if(node.getNodeName().equals("new-line")){
                
                s="\n";
                
            }
                
            else{
                
                Element element=(Element)node;
                s=element.getTextContent();
                NathChatClient.core.setStyleConstants(attrSet,Constants.valueOf(element.getAttribute("font-family")),
                                                              Constants.valueOf(element.getAttribute("bold")),
                                                              Constants.valueOf(element.getAttribute("italic")));
                
            }

        }
    
        try{
                
            doc.insertString(textPane.getCaretPosition(),s,attrSet);
        
        }
        catch(BadLocationException be){
                
            be.printStackTrace();
                
        }
   
        //</editor-fold>
    }
    public void setStyleConstants(SimpleAttributeSet attrSet,Constants fontFamily,Constants boldStatus,Constants italicStatus){
        //<editor-fold defaultstate="collapsed">
        
        switch(fontFamily){
            
            case FONT_FAM_CALIBRI:
                
                StyleConstants.setFontFamily(attrSet,"Calibri");
                break;
                
            case FONT_FAM_TIMES_NEW_ROMAN:
                
                StyleConstants.setFontFamily(attrSet,"Times New Roman");
                break;    
                
            case FONT_FAM_SANS_SERIF:
                StyleConstants.setFontFamily(attrSet,"SansSerif");
                break;
                
            case FONT_FAM_MONOSPACED:
                StyleConstants.setFontFamily(attrSet,"Monospaced");
                break;
            
            case FONT_FAM_HELVETICA:
                StyleConstants.setFontFamily(attrSet,"Helvetica");
                break;
            
        }
        
        switch(boldStatus){
            
            case BOLD_OFF:
                
                StyleConstants.setBold(attrSet, false);
                break;
                
            case BOLD_ON:
                StyleConstants.setBold(attrSet, true);
                break;
                
        }
        
        switch(italicStatus){
            
            case ITALIC_OFF:
                
                StyleConstants.setItalic(attrSet, false);
                break;
                
            case ITALIC_ON:
                StyleConstants.setItalic(attrSet, true);
                break;
                
        }
        
        //</editor-fold>
    }
    //</editor-fold>
    
    //Engine for notification system
    //<editor-fold defaultstate="collapsed">
    public void updateNotifLabel(int n){
        //<editor-fold defaultstate="collapsed">
        
        NathChatClient.notifLabelRef.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        NathChatClient.notifLabelRef.setText(NathChatClient.stringNotif.getStringBuilder(n).toString());
            
        //</editor-fold>
    }
    
    public void notifWriteToXML(NotificationWrapper notifWrapper,String date){
        //<editor-fold defaultstate="collapsed">   
        
        try{
            
            DocumentBuilderFactory dbf=DocumentBuilderFactory.newInstance();
            DocumentBuilder db=dbf.newDocumentBuilder();
            Document doc;
            Attr attr;
            Element root;
            
            if(!new File(NathChatClient.path+"\\data\\sys\\notifList.xml").exists()){
                
                doc=db.newDocument();
                root=doc.createElement("notification");
                doc.appendChild(root);
                attr=doc.createAttribute("number");
                attr.setValue("1");
                root.setAttributeNode(attr);
                
            }
            else{
                
                doc = db.parse(new File(NathChatClient.path+"\\data\\sys\\notifList.xml"));
                root=doc.getDocumentElement();
                attr=root.getAttributeNode("number");
                attr.setValue(String.valueOf(Integer.valueOf(attr.getNodeValue())+1));
                
            }
            
            Element notificationNode=doc.createElement("notification-node");
            attr=doc.createAttribute("id");
            attr.setValue(notifWrapper.getNotifID());
            notificationNode.setAttributeNode(attr);
            attr=doc.createAttribute("assoc-user");
            attr.setValue(notifWrapper.getUser());
            notificationNode.setAttributeNode(attr);
            attr=doc.createAttribute("type");
            attr.setValue(notifWrapper.getNotifType().toString());
            notificationNode.setAttributeNode(attr);
            Element dateTime=doc.createElement("date-time");
            dateTime.setTextContent(date);
            notificationNode.appendChild(dateTime);
            Element body=doc.createElement("body");
            body.setTextContent(notifWrapper.getBody());
            notificationNode.appendChild(body);
            root.appendChild(notificationNode);
            
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(javax.xml.transform.OutputKeys.INDENT, "yes");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "1");
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File(NathChatClient.path+"\\data\\sys\\notifList.xml"));
            transformer.transform(source, result);
            
        }
        catch(ParserConfigurationException|TransformerException|SAXException|IOException e){
            
            e.printStackTrace();
            
        }
        
        //</editor-fold>
    }
    
    public synchronized int getNotifNumber(){
        //<editor-fold defaultstate="collapsed">   
        
        try{
            
            DocumentBuilderFactory dbf=DocumentBuilderFactory.newInstance();
            DocumentBuilder db=dbf.newDocumentBuilder();
            Document doc;
            Attr attr;
            Element root;
            if(new File(NathChatClient.path+"\\data\\sys\\notifList.xml").exists()){
            
                doc = db.parse(new File(NathChatClient.path+"\\data\\sys\\notifList.xml"));
                root=doc.getDocumentElement();
                attr=root.getAttributeNode("number");
                return Integer.valueOf(attr.getNodeValue());
                
            }
            else{
                
                return 0;
                
            }
        
            
        }
        catch(ParserConfigurationException|SAXException|IOException e){
            
            e.printStackTrace();
            return 0;
            
        }
        
        //</editor-fold>
    }
    
    public void notifFetcher(GroupLayoutManager<GroupLayout.SequentialGroup,GroupLayout.ParallelGroup> glm){
        //<editor-fold defaultstate="collapsed">   
        
        if(new File(NathChatClient.path+"\\data\\sys\\notifList.xml").exists()){
            
            try{
                
                DocumentBuilderFactory dbf=DocumentBuilderFactory.newInstance();
                DocumentBuilder db=dbf.newDocumentBuilder();
                Document doc=db.parse(new File(NathChatClient.path+"\\data\\sys\\notifList.xml"));
                doc.getDocumentElement().normalize();
                NodeList nodeList=doc.getElementsByTagName("notification-node");
                for(int i=0;i<nodeList.getLength();i++){
                    
                    Node node=nodeList.item(i);
                    NodeList nList=((Element)node).getElementsByTagName("body");
                    
                    for(int j=0;j<nList.getLength();j++){
                        
                        Node n=nList.item(j);
                        NathChatClient.core.parseXMLNotif(n,glm);
                        
                    }
                    
                }
                
            }
            catch(ParserConfigurationException|SAXException|IOException e){
                
                e.printStackTrace();
                
            }
 
        }
        
        //</editor-fold>   
    }
    
    public void parseXMLNotif(Node node,GroupLayoutManager<GroupLayout.SequentialGroup,GroupLayout.ParallelGroup> glm){
        //<editor-fold defaultstate="collapsed">   
        
        NotificationBlock notifPanel=new NotificationBlock();
        notifPanel.setDescription("<html><body><p style='font-size:8px'>"+node.getTextContent()+"<p></body><html>");
        
        GroupLayout.SequentialGroup sGroup=glm.getLayout().createSequentialGroup();
        GroupLayout.ParallelGroup pGroup=glm.getLayout().createParallelGroup(GroupLayout.Alignment.LEADING);
        
        sGroup.addComponent(notifPanel,10,GroupLayout.PREFERRED_SIZE,GroupLayout.PREFERRED_SIZE);
        pGroup.addComponent(notifPanel,GroupLayout.PREFERRED_SIZE,GroupLayout.PREFERRED_SIZE,GroupLayout.PREFERRED_SIZE);
        
        glm.getVGroup().addGroup(sGroup);
        glm.getHGroup().addGroup(pGroup);
        
        
        //</editor-fold>   
    }
    //</editor-fold>
}
   
