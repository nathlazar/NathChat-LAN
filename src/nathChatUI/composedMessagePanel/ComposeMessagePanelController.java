/*
 * Copyright (C) Jonathan Lazar 2019-Present
 * All Rights Reserved 2019
 */
package nathChatUI.composedMessagePanel;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyledDocument;
import javax.swing.text.BadLocationException;
import jbl.javaDev.util.RandomizedCharacters;
import nathChatEntities.Constants;
import nathChatEntities.MessageWrapper;
import nathChatEntities.SenderThread;
import nathChatEntities.TextPaneElement;
import nathChatUI.NathChatClient;

/**
 *
 * @author Jonathan Lazar <jonathanlazar17@gmail.com>
 */
public class ComposeMessagePanelController {
    
    private ComposeMessagePanel messagePanel;
    private HashMap<Integer,TextPaneElement> textPaneElements=new HashMap<Integer,TextPaneElement>();
    private Constants fontFamily=Constants.FONT_FAM_CALIBRI;
    private Constants boldStatus=Constants.BOLD_OFF,italicStatus=Constants.ITALIC_OFF;
    
    
    public ComposeMessagePanelController(ComposeMessagePanel messagePanel){
        
        this.messagePanel=messagePanel;
        
    }
    
    public void composeMessageTextPaneKT(KeyEvent evt)throws BadLocationException{
        
        String s=""+evt.getKeyChar();
        StyledDocument doc=messagePanel.composedMessageTextPane.getStyledDocument();
        SimpleAttributeSet attrSet=new SimpleAttributeSet();
        HashMap<Integer,TextPaneElement> tpeTemp=new HashMap<Integer,TextPaneElement>();
        NathChatClient.core.setStyleConstants(attrSet,fontFamily,getBoldStatus(),getItalicStatus());
        
        if(evt.getKeyChar()==KeyEvent.VK_BACK_SPACE){
            
            if(doc.getLength()!=0){
                
                if(messagePanel.composedMessageTextPane.getCaretPosition()!=0){
                    
                    if(textPaneElements.containsKey(messagePanel.composedMessageTextPane.getCaretPosition()+1)){
                        
                        textPaneElements.remove(messagePanel.composedMessageTextPane.getCaretPosition());
                        int loopLimit=textPaneElements.size()+1;
                        for(int i=messagePanel.composedMessageTextPane.getCaretPosition()+1;i<=loopLimit;i++){
                          
                            tpeTemp.put((i-1), textPaneElements.get(i));
                            textPaneElements.remove(i);
                                
                        }
                        
                        textPaneElements.putAll(tpeTemp);
                        doc.remove(messagePanel.composedMessageTextPane.getCaretPosition()-1,1);
                        
                    }
                    
                    else{
                        
                        textPaneElements.remove(messagePanel.composedMessageTextPane.getCaretPosition());
                        doc.remove(messagePanel.composedMessageTextPane.getCaretPosition()-1,1);
                        
                    }
                    
                }
                
                
            }
            
        }
        
        else if(evt.getKeyChar()==KeyEvent.VK_ENTER){
            
            TextPaneElement textPaneElement;
            textPaneElement=new TextPaneElement("\\n",null,null);
            sortCharacterPosition(textPaneElement,tpeTemp);
            doc.insertString(messagePanel.composedMessageTextPane.getCaretPosition(),"\n",attrSet);
            
        }
         
        else{
            
            TextPaneElement textPaneElement;
            textPaneElement=new TextPaneElement(s,new Constants[]{this.getBoldStatus(),this.getItalicStatus()},this.fontFamily);
            sortCharacterPosition(textPaneElement,tpeTemp);
            doc.insertString(messagePanel.composedMessageTextPane.getCaretPosition(),s,attrSet);
            
        }
        
    }
    
    public void boldLabelMouseClk(){
        
        switch(this.getBoldStatus()){
            
            case BOLD_OFF:
                messagePanel.boldLabel.setBackground(Color.decode("#DFDFDF"));
                setBoldStatus(Constants.BOLD_ON);
                break;
            
            case BOLD_ON:
                messagePanel.setBackground(Color.decode("#EEEEEE"));
                setBoldStatus(Constants.BOLD_OFF);
                break;
                
        }
        
    }
    
    public void boldLabelMouseEntr(){
        
        messagePanel.boldLabel.setBackground(Color.decode("#DFDFDF"));
        
    }
    
    public void boldLabelMouseExt(){
        
        
        messagePanel.boldLabel.setBackground(Color.decode("#EEEEEE"));
        
        switch(this.getBoldStatus()){
            
            case BOLD_OFF:
                messagePanel.boldLabel.setBackground(Color.decode("#EEEEEE"));
                break;
            
            case BOLD_ON:
                messagePanel.boldLabel.setBackground(Color.decode("#DFDFDF"));
                break;
                
        }
        
    }
    
    public void italicLabelMouseClk(){
        
        switch(this.getItalicStatus()){
            
            case ITALIC_OFF:
                messagePanel.italicLabel.setBackground(Color.decode("#DFDFDF"));
                this.setItalicStatus(Constants.ITALIC_ON);
                break;
            
            case ITALIC_ON:
                messagePanel.setBackground(Color.decode("#EEEEEE"));
                this.setItalicStatus(Constants.ITALIC_OFF);
                break;
                
        }
        
    }
    
    public void italicLabelMouseEntr(){
        
        messagePanel.italicLabel.setBackground(Color.decode("#DFDFDF"));
        
    }
    
    public void italicLabelMouseExt(){
        
        messagePanel.italicLabel.setBackground(Color.decode("#EEEEEE"));
        
        switch(this.getItalicStatus()){
            
            case ITALIC_OFF:
                messagePanel.italicLabel.setBackground(Color.decode("#EEEEEE"));
                break;
            
            case ITALIC_ON:
                messagePanel.italicLabel.setBackground(Color.decode("#DFDFDF"));
                break;
                
        }
        
    }
    
    public void fontComboBoxAP(){
        
        if(messagePanel.fontComboBox.getSelectedItem().equals("Calibri")){
            
            this.fontFamily=Constants.FONT_FAM_CALIBRI;
            
        }
        
        else if(messagePanel.fontComboBox.getSelectedItem().equals("Times New Roman")){
            
            this.fontFamily=Constants.FONT_FAM_TIMES_NEW_ROMAN;
            
        }
        
        else if(messagePanel.fontComboBox.getSelectedItem().equals("Sans Serif")){
            
            this.fontFamily=Constants.FONT_FAM_SANS_SERIF;
            
        }
        
        else if(messagePanel.fontComboBox.getSelectedItem().equals("Monospaced")){
            
            this.fontFamily=Constants.FONT_FAM_MONOSPACED;
            
        }
        
        else if(messagePanel.fontComboBox.getSelectedItem().equals("Helvetica")){
            
            this.fontFamily=Constants.FONT_FAM_HELVETICA;
            
        }
        
    }
    
    public void sendLabelMouseClk(String userKey){
        
        if(!textPaneElements.isEmpty()){
            
            SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yy HH:mm:ss");
            Date date=new Date();
            String msgID=new RandomizedCharacters(5).getRandomizedCharacters();
            NathChatClient.core.writeToXML(textPaneElements,userKey,Constants.MESSAGE_TYPE_SENDER,sdf.format(date),msgID);
            SenderThread senderThread=new SenderThread(userKey,textPaneElements,new MessageWrapper(textPaneElements,msgID,NathChatClient.deviceName));
            senderThread.start();
            messagePanel.composedMessageTextPane.setText("");
            
        }
        
    }
    
    private Constants getBoldStatus(){
        
        return boldStatus;
        
    }
    
    private void setBoldStatus(Constants boldStatus){
        
        this.boldStatus=boldStatus;
        
    }
    
    private Constants getItalicStatus(){
        
        return italicStatus;
        
    }
    
    private void setItalicStatus(Constants italicStatus){
        
        this.italicStatus=italicStatus;
        
    }
    
    private void sortCharacterPosition(TextPaneElement textPaneElement,HashMap<Integer,TextPaneElement> tpeTemp){
        
        if(textPaneElements.containsKey(messagePanel.composedMessageTextPane.getCaretPosition()+1)){
                
            for(int i=messagePanel.composedMessageTextPane.getCaretPosition()+1;i<=textPaneElements.size();i++){
                
                tpeTemp.put((i+1), textPaneElements.get(i));
  
            }
                
            textPaneElements.putAll(tpeTemp);
                
        }
            
        textPaneElements.put(messagePanel.composedMessageTextPane.getCaretPosition()+1,textPaneElement);
        
    }
    
}
