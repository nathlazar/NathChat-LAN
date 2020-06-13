/*
 * Copyright (C) Jonathan Lazar 2019-Present
 * All Rights Reserved 2019
 */
package nathChatEntities;

import java.awt.Color;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import javax.swing.JTextField;

/**
 *
 * @author Jonathan Lazar <jonathanlazar17@gmail.com>
 */
public class TextFieldPlaceHolder implements FocusListener{
    
    private JTextField textField;
    private String placeHolder;
    
    public TextFieldPlaceHolder(JTextField textField,String placeHolder){
        
        this.textField=textField;
        this.placeHolder=placeHolder;
        textField.setForeground(Color.decode("#90949C"));
        textField.setText(placeHolder);
        textField.addFocusListener(this);
        
    }
    
    public JTextField getJLabel(){
        
        return textField;
        
    }
    
    public String getPlaceHolder(){
        
        return placeHolder;
        
    }
    
    @Override
    public void focusGained(FocusEvent e){
        
        if(textField.getText().equals(placeHolder)&&textField.getForeground().equals(Color.decode("#90949C"))){
            
            textField.setText("");
            textField.setForeground(Color.BLACK);
            
        }
        
    }
    
    @Override
    public void focusLost(FocusEvent e){
        
        if(textField.getText().length()==0){
            
            textField.setText(placeHolder);
            textField.setForeground(Color.decode("#90949C"));
            
        }
        
    }
    
}
